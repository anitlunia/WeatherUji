package com.example.oxana.weather.weather_info;

import com.example.oxana.weather.WeatherCondition;

import java.util.Calendar;

/**
 * Created by Oxana on 17/04/2015.
 */
public class CurrentWeatherData {

    private Calendar timeStamp;
    private double temperature;
    private double windSpeed;
    private int humidity;
    private int cloudiness;
    private WeatherCondition condition; // store the description of the current weather.

    public CurrentWeatherData(Calendar time, double temp,double win, int humi,int cloud,WeatherCondition weather){
        timeStamp = time;
        temperature =temp;
        windSpeed = win;
        humidity = humi;
        cloudiness = cloud;
        condition = weather;
    }
}
