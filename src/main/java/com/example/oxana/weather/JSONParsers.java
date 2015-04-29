package com.example.oxana.weather;

import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Hecate on 23/04/2015.
 */
public class JSONParsers {

    private static final String CITY_NAME_ID = "name";
    private static final String MAIN_ID = "main";
    private static final String TEMPERATURE_ID = "temp";
    private static final String COORD_ID = "coord";
    private static final String SYS_ID = "sys";
    private static final String LONGITUDE_ID = "lon";
    private static final String LATITUDE_ID = "lat";
    private static final String COUNTRY_ID = "country";
    private static final String HUMIDITY_ID = "humidity";
    private static final String WIND_ID = "wind";
    private static final String SPEED_ID = "speed";
    private static final String CLOUDS_ID = "clouds";
    private static final String CLOUDINESS_ID = "all";
    private static final String WEATHER_ID = "weather";
    private static final String DESCRIPTION_ID = "description";
    private static final String ICON_ID = "icon";
    private static final String TIME_ID = "dt";

    private static final String RESPONSE_CODE_ID = "cod";
    private static final int UNKNOWN = 404;
    private static final String LIST_ID = "list";
    private static final String MIN_ID = "min";
    private static final String MAX_ID = "max";

    private static final String UNKNOWN_TOWN = "Unknown town";
    public static final double KELVIN_BASE = 273.15;

    public static void getCurrentWeatherFromJSON(JSONObject JSONPage, ResponseReceiver<CurrentWeatherData> receiver) throws JSONException {
        int responseCode = JSONPage.getInt(RESPONSE_CODE_ID);
        if (responseCode == UNKNOWN) {
            receiver.onErrorReceived(UNKNOWN_TOWN);
            return;
        }
        JSONObject main = JSONPage.getJSONObject(MAIN_ID);
        double temperature = main.getDouble(TEMPERATURE_ID) - KELVIN_BASE;
        Calendar time = getTime(JSONPage);
        int humidity = main.getInt(HUMIDITY_ID);
        JSONObject wind = JSONPage.getJSONObject(WIND_ID);
        double windSpeed = wind.getDouble(SPEED_ID);
        JSONObject clouds = JSONPage.getJSONObject(CLOUDS_ID);
        int cloudiness = clouds.getInt(CLOUDINESS_ID);
        JSONArray weather = JSONPage.getJSONArray(WEATHER_ID);
        WeatherCondition weatherConditions = getWeatherConditions(weather)[0];

        CurrentWeatherData currentWeatherData = new CurrentWeatherData(
                time,
                temperature,
                windSpeed,
                humidity,
                cloudiness,
                weatherConditions
        );
        receiver.onResponseReceived(currentWeatherData);
    }

    private static Calendar getTime(JSONObject JSONPage) throws JSONException {
        long seconds = JSONPage.getInt(TIME_ID);
        Date date = new Date(seconds * 1000);
        Calendar time = Calendar.getInstance();
        TimeZone zone = time.getTimeZone();
        time.setTimeZone(TimeZone.getTimeZone("UTC"));
        time.setTime(date);
        time.setTimeZone(zone);
        return time;
    }

    private static WeatherCondition[] getWeatherConditions(JSONArray conditions) throws JSONException {
        WeatherCondition[] weatherConditions = new WeatherCondition[conditions.length()];
        for (int i = 0 ; i < weatherConditions.length ; i++) {
            JSONObject condition = conditions.getJSONObject(i);
            weatherConditions[i] = getWeatherCondition(condition);
        }
        return weatherConditions;
    }

    private static WeatherCondition getWeatherCondition(JSONObject condition) throws JSONException {
        String mainCondition = condition.getString(MAIN_ID);
        String description = condition.getString(DESCRIPTION_ID);
        String icon = condition.getString(ICON_ID);
        return new WeatherCondition(mainCondition, description, icon);
    }

    public static void getLocationFromJSON(JSONObject JSONPage, ResponseReceiver<Location> receiver) throws JSONException {
        int responseCode = JSONPage.getInt(RESPONSE_CODE_ID);
        if (responseCode == UNKNOWN) {
            receiver.onErrorReceived(UNKNOWN_TOWN);
            return;
        }
        String name = JSONPage.getString(CITY_NAME_ID);
        JSONObject coords = JSONPage.getJSONObject(COORD_ID);
        double longitude = coords.getDouble(LONGITUDE_ID);
        double latitude = coords.getDouble(LATITUDE_ID);
        JSONObject sys = JSONPage.getJSONObject(SYS_ID);
        String country = sys.getString(COUNTRY_ID);
        receiver.onResponseReceived(new Location(name, country, longitude, latitude));
    }

    public static void getWeatherForecastFromJSON(JSONObject JSONPage, ResponseReceiver<WeatherPrediction[]> receiver) throws JSONException {
        int responseCode = JSONPage.getInt(RESPONSE_CODE_ID);
        if (responseCode == UNKNOWN) {
            receiver.onErrorReceived(UNKNOWN_TOWN);
            return;
        }
        JSONArray predictions = JSONPage.getJSONArray(LIST_ID);
        WeatherPrediction[] forecast = new WeatherPrediction[predictions.length()];
        for (int i = 0 ; i < forecast.length ; i++) {
            JSONObject predictionJSON = predictions.getJSONObject(i);
            Calendar timeStamp = getTime(predictionJSON);
            JSONObject temperatures = predictionJSON.getJSONObject(TEMPERATURE_ID);
            double minTemperature = temperatures.getDouble(MIN_ID) - KELVIN_BASE;
            double maxTemperature = temperatures.getDouble(MAX_ID) - KELVIN_BASE;
            JSONArray weather = predictionJSON.getJSONArray(WEATHER_ID);
            WeatherCondition weatherConditions = getWeatherConditions(weather)[0];
            WeatherPrediction prediction = new WeatherPrediction(
                    timeStamp,
                    minTemperature,
                    maxTemperature,
                    weatherConditions
            );
            forecast[i] = prediction;
        }
        receiver.onResponseReceived(forecast);
    }

    public static void getHourlyForecastFromJSON(JSONObject JSONPage, ResponseReceiver<HourlyPrediction[]> receiver) throws JSONException {
        int responseCode = JSONPage.getInt(RESPONSE_CODE_ID);
        if (responseCode == UNKNOWN) {
            receiver.onErrorReceived(UNKNOWN_TOWN);
            return;
        }
        JSONArray predictions = JSONPage.getJSONArray(LIST_ID);
        HourlyPrediction[] forecast = new HourlyPrediction[predictions.length()];
        for (int i = 0 ; i < forecast.length ; i++) {
            JSONObject predictionJSON = predictions.getJSONObject(i);
            Calendar timeStamp = getTime(predictionJSON);
            JSONObject main = predictionJSON.getJSONObject(MAIN_ID);
            double temperature = main.getDouble(TEMPERATURE_ID) - KELVIN_BASE;
            JSONArray weather = predictionJSON.getJSONArray(WEATHER_ID);
            WeatherCondition weatherConditions = getWeatherConditions(weather)[0];
            HourlyPrediction prediction = new HourlyPrediction(
                    timeStamp,
                    temperature,
                    weatherConditions
            );
            forecast[i] = prediction;
        }
        receiver.onResponseReceived(forecast);
    }





}
