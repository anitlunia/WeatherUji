package com.example.oxana.weather;

import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Hecate on 23/04/2015.
 */
public class OpenWeather implements IWeatherProvider {

    private String prefix = "http://api.openweathermap.org/data/2.5/weather?APPID=2748012c541b050eb47e9f88de0dbc57&q=";
    private NetworkHelper networkHelper;
    String url = null;

    private static final String CURRENT_WEATHER = "http://api.openweathermap.org/data/2.5/weather?APPID=2748012c541b050eb47e9f88de0dbc57&q=";
    private static final String FORECAST_WEATHER = "http://api.openweathermap.org/data/2.5/forecast/daily?APPID=2748012c541b050eb47e9f88de0dbc57&q=";
    private static final String HOURLY_WEATHER = "http://api.openweathermap.org/data/2.5/forecast?APPID=2748012c541b050eb47e9f88de0dbc57&q=";

    public OpenWeather (NetworkHelper networkHelper){
        this.networkHelper = networkHelper;

    }

    @Override
    public void getForecast(Location location, final ResponseReceiver<WeatherPrediction[]> receiver) {
        try {
            url = FORECAST_WEATHER + URLEncoder.encode(location.getNameCountry(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        networkHelper.fetchWebPage(url,
                new ResponseReceiver<String>() {
                    @Override
                    public void onResponseReceived(String response) {
                        try {
                            JSONParsers.getWeatherForecastFromJSON(
                                    new JSONObject(response), receiver);
                        } catch (JSONException e) {
                            receiver.onErrorReceived("Bad format in JSON");
                        }
                    }
                    @Override
                    public void onErrorReceived(String message) {
                        receiver.onErrorReceived(message);
                    }
                }
        );

    }

    @Override
    public void getLocationFromName(String name, final ResponseReceiver<Location> receiver) {


        try {
            url = CURRENT_WEATHER + URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        networkHelper.fetchWebPage(url,
                        new ResponseReceiver<String>() {
                            @Override
                            public void onResponseReceived(String response) {
                                try {
                                    JSONParsers.getLocationFromJSON(
                                            new JSONObject(response), receiver);
                                } catch (JSONException e) {
                                    receiver.onErrorReceived("Bad format in JSON");
                                }
                            }
                            @Override
                            public void onErrorReceived(String message) {
                                receiver.onErrorReceived(message);
                            }
                        }
                );


    }

    @Override
    public void getCurrentWeatherData(Location location, final ResponseReceiver<CurrentWeatherData> receiver) {

        try {
            url = CURRENT_WEATHER + URLEncoder.encode(location.getNameCountry(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        networkHelper.fetchWebPage(url,
                new ResponseReceiver<String>() {
                    @Override
                    public void onResponseReceived(String response) {
                        try {
                            JSONParsers.getCurrentWeatherFromJSON(
                                    new JSONObject(response), receiver);
                        } catch (JSONException e) {
                            receiver.onErrorReceived("Bad format in JSON");
                        }
                    }
                    @Override
                    public void onErrorReceived(String message) {
                        receiver.onErrorReceived(message);
                    }
                }
        );

    }

    @Override
    public void getHourlyForecast(Location location,final  ResponseReceiver<HourlyPrediction[]> receiver) {

        try {
            url = HOURLY_WEATHER + URLEncoder.encode(location.getNameCountry(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        networkHelper.fetchWebPage(url,
                new ResponseReceiver<String>() {
                    @Override
                    public void onResponseReceived(String response) {
                        try {
                            JSONParsers.getHourlyForecastFromJSON(
                                    new JSONObject(response), receiver);
                        } catch (JSONException e) {
                            receiver.onErrorReceived("Bad format in JSON");
                        }
                    }
                    @Override
                    public void onErrorReceived(String message) {
                        receiver.onErrorReceived(message);
                    }
                }
        );

    }
}
