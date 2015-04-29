package com.example.oxana.weather.weather_info;

import com.example.oxana.weather.WeatherCondition;

import java.util.Calendar;

/**
 * Created by Oxana on 17/04/2015.
 * Prediction for a Single Day
 *
 */
public class WeatherPrediction {

    private Calendar timeStamp; //time of prediction
    private double minTemperature;
    private double maxTemperature;
    private WeatherCondition condition;

    public WeatherPrediction(Calendar time, double mintemp,double maxtemp,WeatherCondition cond){
        timeStamp=time;
        minTemperature=mintemp;
        maxTemperature = maxtemp;
        condition= cond;

    }
}
