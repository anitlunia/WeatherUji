package com.example.oxana.weather.weather_info;

import com.example.oxana.weather.WeatherCondition;

import java.util.Calendar;

/**
 * Created by Oxana on 17/04/2015.
 * Prediction for a concrete hour
 */
public class HourlyPrediction {

    private Calendar timeStamp; //time of prediction
    private double temperature;
    private WeatherCondition condition;

    public HourlyPrediction(Calendar timeS,double temp,WeatherCondition cond){
        timeStamp = timeS;
        temperature = temp;
        condition = cond;
    }
}
