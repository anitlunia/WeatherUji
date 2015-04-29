package com.example.oxana.weather.model;

import com.example.oxana.weather.IWeatherProvider;
import com.example.oxana.weather.Location;
import com.example.oxana.weather.ResponseReceiver;
import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

import org.json.JSONException;

/**
 * Created by Oxana on 29/04/2015.
 */
public class Model implements IModel {
    private Location location;
    private IWeatherProvider provider;
    private CurrentWeatherData current_data;
    private WeatherPrediction[] weatherPredictions;
    private HourlyPrediction[] hourlyPredictions;



    public Model( IWeatherProvider weatherProvider){

    }
    @Override
    public Location getCurrentLocation() {
        return null;
    }

    @Override
    public void findLocationByName(String name, ResponseReceiver<Location> receiver) {

    }

    @Override
    public void setCurrentLocation(Location location) {

    }

    @Override
    public void getCurrentWeatherData(ResponseReceiver<CurrentWeatherData> receiver) {






    }

    @Override
    public void getWeatherForecast(ResponseReceiver<WeatherPrediction[]> receiver) {

    }

    @Override
    public void getHourlyForecast(ResponseReceiver<HourlyPrediction[]> receiver) {

    }
}
