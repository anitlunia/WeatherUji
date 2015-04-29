package com.example.oxana.weather.model;

import com.example.oxana.weather.Location;
import com.example.oxana.weather.ResponseReceiver;
import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

/**
 * Created by Oxana on 29/04/2015.
 */
public interface IModel {
    Location getCurrentLocation();
    void findLocationByName(String name, ResponseReceiver<Location> receiver);
    void setCurrentLocation(Location location);
    void getCurrentWeatherData(ResponseReceiver<CurrentWeatherData> receiver);
    void getWeatherForecast(ResponseReceiver<WeatherPrediction[]> receiver);
    void getHourlyForecast(ResponseReceiver<HourlyPrediction[]> receiver);
}
