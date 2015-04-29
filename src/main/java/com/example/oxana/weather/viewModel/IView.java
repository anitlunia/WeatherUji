package com.example.oxana.weather.viewModel;

import com.example.oxana.weather.Location;
import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

/**
 * Created by Oxana on 29/04/2015.
 */
public interface IView {
    public static enum DisplayedInformation {
        CURRENT, FORECAST, HOURLY
    }
    void askForLocation();
    void showLocation(Location location);
    void warnWrongLocation(String locationName);
    void showError(String message);
    void showCurrentWeatherData(CurrentWeatherData currentWeatherData);
    void showWeatherForecast(WeatherPrediction[] forecast);
    void showHourlyForecast(HourlyPrediction[] forecast);
    void startShowLocationSearchInProgress();
    void stopShowLocationSearchInProgress();
    void startShowWeatherSearchInProgress();
    void stopShowWeatherSearchInProgress();
    void switchWeatherInfo(DisplayedInformation displayedInformation);

}
