package com.example.oxana.weather.model;

import com.example.oxana.weather.IWeatherProvider;
import com.example.oxana.weather.Location;
import com.example.oxana.weather.ResponseReceiver;
import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

import org.json.JSONException;

import java.util.Calendar;

/**
 * Created by Oxana on 29/04/2015.
 */
public class Model implements IModel {
    private static final long UPDATE_INTERVAL = 10*60*1000;
    private Location currentLocation;

    private IWeatherProvider provider;

    private CurrentWeatherData current_data;
    private WeatherPrediction[] weatherPredictions;
    private HourlyPrediction[] hourlyPredictions;

    private Calendar currentDataCalendar;
    private Calendar weatherPredictionCalendar;
    private Calendar hourlyPredictionCalendar;



    public Model( IWeatherProvider weatherProvider){
        this.provider = weatherProvider;
    }
    @Override
    public Location getCurrentLocation() { return currentLocation;    }


    @Override
    public void setCurrentLocation(Location location) {

        current_data = null;
        currentLocation= location;

    }

    @Override
    public void findLocationByName(String name, ResponseReceiver<Location> receiver) {

            provider.getLocationFromName(name,receiver);

    }


    @Override
    public void getCurrentWeatherData(final ResponseReceiver<CurrentWeatherData> receiver) {

        if (currentLocation == null){
            receiver.onErrorReceived("Not valid Location");
        }
        else {
            if (current_data != null && Calendar.getInstance().getTimeInMillis()- currentDataCalendar.getTimeInMillis() <= UPDATE_INTERVAL ) {
                receiver.onResponseReceived(current_data);
            }
            else {

                provider.getCurrentWeatherData(currentLocation,new ResponseReceiver<CurrentWeatherData>() {
                    @Override
                    public void onResponseReceived(CurrentWeatherData currentWeatherData) {
                        currentDataCalendar = Calendar.getInstance();
                        current_data = currentWeatherData;
                        receiver.onResponseReceived(currentWeatherData);
                    }

                    @Override
                    public void onErrorReceived(String message) {
                        receiver.onErrorReceived(message);

                    }
                });
            }
        }





    }

    @Override
    public void getWeatherForecast(final ResponseReceiver<WeatherPrediction[]> receiver) {

        if (currentLocation == null) {
            receiver.onErrorReceived("Not valid Location");
        } else {
            if (weatherPredictions != null && Calendar.getInstance().getTimeInMillis() - weatherPredictionCalendar.getTimeInMillis() <= UPDATE_INTERVAL ) {

                receiver.onResponseReceived(weatherPredictions);
            }
                else {

                provider.getForecast(currentLocation, new ResponseReceiver<WeatherPrediction[]>() {


                    @Override
                    public void onResponseReceived(WeatherPrediction[] response) {

                        weatherPredictionCalendar = Calendar.getInstance();
                        weatherPredictions = response;
                        receiver.onResponseReceived(weatherPredictions);
                    }


                    @Override
                    public void onErrorReceived(String message) {

                        receiver.onErrorReceived(message);
                    }


                });
            }


        }
    }

    @Override
    public void getHourlyForecast(final ResponseReceiver<HourlyPrediction[]> receiver) {

        if (currentLocation == null) {
            receiver.onErrorReceived("Not valid Location");
        } else {
            if (hourlyPredictions != null && Calendar.getInstance().getTimeInMillis() - hourlyPredictionCalendar.getTimeInMillis() <= UPDATE_INTERVAL) {
                receiver.onResponseReceived(hourlyPredictions);
            } else {

                provider.getHourlyForecast(currentLocation, new ResponseReceiver<HourlyPrediction[]>() {


                    @Override
                    public void onResponseReceived(HourlyPrediction[] responsed) {

                        hourlyPredictionCalendar = Calendar.getInstance();
                        hourlyPredictions = responsed;
                        receiver.onResponseReceived(hourlyPredictions);
                    }


                    @Override
                    public void onErrorReceived(String message) {

                        receiver.onErrorReceived(message);
                    }


                });
            }


        }


    }
}
