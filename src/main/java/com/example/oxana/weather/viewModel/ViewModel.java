package com.example.oxana.weather.viewModel;

import com.example.oxana.weather.Location;
import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

/**
 * Created by Oxana on 29/04/2015.
 */
public class ViewModel implements IView {
    @Override
    public void askForLocation() {

    }

    @Override
    public void showLocation(Location location) {

    }

    @Override
    public void warnWrongLocation(String locationName) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showCurrentWeatherData(CurrentWeatherData currentWeatherData) {

    }

    @Override
    public void showWeatherForecast(WeatherPrediction[] forecast) {

    }

    @Override
    public void showHourlyForecast(HourlyPrediction[] forecast) {

    }

    @Override
    public void startShowLocationSearchInProgress() {

    }

    @Override
    public void stopShowLocationSearchInProgress() {

    }

    @Override
    public void startShowWeatherSearchInProgress() {

    }

    @Override
    public void stopShowWeatherSearchInProgress() {

    }
}
