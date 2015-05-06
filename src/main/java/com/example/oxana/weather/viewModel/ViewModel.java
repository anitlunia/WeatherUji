package com.example.oxana.weather.viewModel;

import android.view.View;

import com.example.oxana.weather.IWeatherProvider;
import com.example.oxana.weather.Location;
import com.example.oxana.weather.ResponseReceiver;
import com.example.oxana.weather.model.IModel;
import com.example.oxana.weather.model.Model;
import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

import static com.example.oxana.weather.viewModel.IView.DisplayedInformation;
import static com.example.oxana.weather.viewModel.IView.DisplayedInformation.CURRENT;
import static com.example.oxana.weather.viewModel.IView.DisplayedInformation.FORECAST;
import static com.example.oxana.weather.viewModel.IView.DisplayedInformation.HOURLY;

/**
 * Created by Oxana on 29/04/2015.
 *
 *The user wants to change the location.
  The user wants to change the kind of data displayed
 */
public class ViewModel{

    private IView view;
    private IModel model;



    private static ViewModel instance;

    private DisplayedInformation currentDisplay;


    private ViewModel(IModel model){
        this.model = model;
        currentDisplay = DisplayedInformation.CURRENT;

    }
    public static ViewModel buildInstance(final IModel model,
                                          final String locationName,
                                          final ResponseReceiver<String> receiver) {
        instance = new ViewModel(model);
        model.findLocationByName(locationName,
                new ResponseReceiver<Location>() {
                    @Override
                    public void onResponseReceived(Location location) {
                        if (location != null) {
                            model.setCurrentLocation(location);
                            receiver.onResponseReceived(location.getName());
                        } else
                            receiver.onErrorReceived(locationName + " not found");
                    }
                    @Override
                    public void onErrorReceived(String message) {
                        receiver.onErrorReceived(message);
                    }
                }
        );
        return instance;
    }

    public static ViewModel getInstance(){
        return instance;
    }

    public void connectView(IView view){
        this.view = view;
        this.view.switchWeatherInfo(currentDisplay);

        update();
    }


    public void onChangelocationRequested(){
        view.askForLocation();
    }

    public void onLocationNameEntered(final String name) {
        view.startShowLocationSearchInProgress();
        view.startShowWeatherSearchInProgress();
        model.findLocationByName(name, new ResponseReceiver<Location>() {
            @Override
            public void onResponseReceived(Location location) {
                if (location == null) {
                    view.warnWrongLocation(name);
                }
                else{
                    model.setCurrentLocation(location);
                    requestCurrentWeatherData();
                    view.showLocation(location);
                    switch (currentDisplay){
                        case FORECAST:
                            requestWeatherForecast();
                            break;
                        case HOURLY:
                            requestHourlyForecast();
                            break;
                    }
                }


            }

            @Override
            public void onErrorReceived(String message) {

                view.warnWrongLocation(name);
            }
        });

        view.stopShowLocationSearchInProgress();
        view.stopShowWeatherSearchInProgress();
    }

    public void onShowCurrentWeatherRequested(){
        currentDisplay = CURRENT;
        view.switchWeatherInfo(currentDisplay);
    }

    public void onShowWeatherForecastRequested(){
        currentDisplay = FORECAST;
        view.switchWeatherInfo(currentDisplay);
    }

    public void onShowHourlyForecastRequested(){
        currentDisplay = HOURLY;
        view.switchWeatherInfo(currentDisplay);
    }

    public void requestCurrentWeatherData() {
        if (view == null)
            return;
        view.startShowWeatherSearchInProgress();
        model.getCurrentWeatherData(
                new ResponseReceiver<CurrentWeatherData>() {
                    @Override
                    public void onResponseReceived(CurrentWeatherData response) {
                        view.showCurrentWeatherData(response);
                        view.stopShowWeatherSearchInProgress();
                    }

                    @Override
                    public void onErrorReceived(String message) {
                        view.stopShowWeatherSearchInProgress();
                        view.showError(message);
                    }
                }
        );
    }

    public void requestWeatherForecast(){
        if (view == null)
            return;
        view.startShowWeatherSearchInProgress();
        model.getWeatherForecast(
                new ResponseReceiver<WeatherPrediction[]>() {
                    @Override
                    public void onResponseReceived(WeatherPrediction[] response) {
                        view.stopShowWeatherSearchInProgress();
                        view.showWeatherForecast(response);
                    }

                    @Override
                    public void onErrorReceived(String message) {
                        view.stopShowWeatherSearchInProgress();
                        view.showError(message);
                    }
                });

    }

    public void requestHourlyForecast(){
        if (view == null)
            return;
        view.startShowWeatherSearchInProgress();
        model.getHourlyForecast(
                new ResponseReceiver<HourlyPrediction[]>() {
                    @Override
                    public void onResponseReceived(HourlyPrediction[] response) {
                        view.stopShowWeatherSearchInProgress();
                        view.showHourlyForecast(response);
                    }

                    @Override
                    public void onErrorReceived(String message) {
                        view.stopShowWeatherSearchInProgress();
                        view.showError(message);

                    }
                }
        );
    }

    public DisplayedInformation getCurrentDisplay(){
        return currentDisplay;
    }

    public Location getLocation(){
        return model.getCurrentLocation();
    }

    private void update (){
        view.showLocation(model.getCurrentLocation());
        requestCurrentWeatherData();

        switch (currentDisplay){
            case CURRENT:
                requestCurrentWeatherData();
                break;
            case HOURLY:
                requestHourlyForecast();
                break;
            case FORECAST:
                requestWeatherForecast();
                break;

        }
    }



}
