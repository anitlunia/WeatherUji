package com.example.oxana.weather;


import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

/**
 * Created by Oxana on 17/04/2015.
 */
public class MockWeather implements IWeatherProvider{

    @Override
    public void getLocationFromName(String name, ResponseReceiver<Location> receiver) {
        String fullName = null;
        char charAt0 = Character.toLowerCase(name.charAt(0));
        switch (charAt0) {
            case 'c': fullName = "Castellon";
                break;
            case 'v': fullName = "Valencia";
                break;
// ...
        }
        if (fullName != null)
            receiver.onResponseReceived(new com.example.oxana.weather.Location(fullName,"Spain",0,10)); //  antes la funcion era new Location(fullName, "Spain", 0, 10)
        else
            receiver.onErrorReceived("Error: " + name + "unknown");

    }


    @Override
    public void getCurrentWeatherData(Location location, ResponseReceiver<CurrentWeatherData> receiver) {

    }

    @Override
    public void getHourlyForecast(Location location,ResponseReceiver<HourlyPrediction[]> receiver) {

    }


    @Override
    public void getForecast(Location location, ResponseReceiver<WeatherPrediction[]> receiver) {

    }








}
