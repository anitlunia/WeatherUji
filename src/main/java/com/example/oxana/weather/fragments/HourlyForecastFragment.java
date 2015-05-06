package com.example.oxana.weather.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.oxana.weather.R;
import com.example.oxana.weather.viewModel.ViewModel;
import com.example.oxana.weather.weather_info.HourlyPrediction;

/**
 * Created by Fernando on 03/05/2015.
 */
public class HourlyForecastFragment extends Fragment {

    ListView forecastList;
    ViewModel vm;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_weather_forecast, viewGroup, false);
        forecastList = (ListView) v.findViewById(R.id.forecast_list_view);
        return v;
    }

    public void requestWeatherData(){
        vm.requestHourlyForecast();
    }

    public void showHourlyForecast(HourlyPrediction[] forecast) {
        //Aqui falta el temperature graph

    }
}
