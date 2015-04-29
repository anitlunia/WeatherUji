package com.example.oxana.weather;



import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

import static com.example.oxana.weather.ResponseReceiver.*;

/**
 * Created by Oxana on 17/04/2015.
 */
public interface IWeatherProvider {

    void getForecast(Location location,
                     ResponseReceiver<WeatherPrediction[]> receiver);

    void getLocationFromName(String name,
                             ResponseReceiver<Location> receiver);
    void getCurrentWeatherData(Location location,
                               ResponseReceiver<CurrentWeatherData> receiver);

    void getHourlyForecast(Location location,
                           ResponseReceiver<HourlyPrediction[]> receiver);


}
