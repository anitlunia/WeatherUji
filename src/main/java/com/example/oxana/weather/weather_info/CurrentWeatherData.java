package com.example.oxana.weather.weather_info;

import com.example.oxana.weather.WeatherCondition;

import java.util.Calendar;

/**
 * Created by Oxana on 17/04/2015.
 */
public class CurrentWeatherData {

    private Calendar timeStamp;
    private double temperature = 15;
    private double windSpeed = 15;
    private int humidity = 15;
    private int cloudiness = 15;
    private WeatherCondition condition = new WeatherCondition("Cloudy", "Very Cloudy", "icon01d.png");
     // store the description of the current weather.

    public CurrentWeatherData(Calendar time, double temp,double win, int humi,int cloud,WeatherCondition  weather){
        timeStamp = time;
        temperature =temp;
        windSpeed = win;
        humidity = humi;
        cloudiness = cloud;
        condition = weather;
    }

    public Calendar getTimeStamp()
    {
        return timeStamp;
    }

    public double getTemperature()
    {
        return temperature;
    }

    public double getWindSpeed()
    {
        return windSpeed;
    }

    public int getHumidity()
    {
        return humidity;
    }


    public int getCloudiness()
    {
        return cloudiness;
    }



    public WeatherCondition getConditions()
    {
        return condition;
    }








}
