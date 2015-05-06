package com.example.oxana.weather.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.oxana.weather.R;
import com.example.oxana.weather.WeatherPredictionAdapter;
import com.example.oxana.weather.viewModel.ViewModel;
import com.example.oxana.weather.weather_info.WeatherPrediction;

/**
 * Created by Oxana on 03/05/2015.
 */
public class WeatherForecastFragment extends Fragment {

    private ListView forecastList;
    private ViewModel vm;


    public WeatherForecastFragment(){
        vm = ViewModel.getInstance();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_weather_forecast, viewGroup, false);
        forecastList = (ListView) v.findViewById(R.id.forecast_list_view);

        vm = ViewModel.getInstance();

        return v;
    }

    public void onResume(){
        super.onResume();
        requestWeatherData();
    }

    public void requestWeatherData(){
        vm.requestWeatherForecast();
    }

    public void showWeatherForecast(WeatherPrediction[] predictions){
        if(forecastList != null)
            forecastList.setAdapter(new WeatherPredictionAdapter(getActivity().getApplicationContext(), R.layout.weather_condition_row, predictions));
    }

}

