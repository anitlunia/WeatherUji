package com.example.oxana.weather.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oxana.weather.R;
import com.example.oxana.weather.WeatherCondition;
import com.example.oxana.weather.viewModel.ViewModel;
import com.example.oxana.weather.weather_info.CurrentWeatherData;

/**
 * Created by Fernando on 03/05/2015.
 */
public class CurrentWeatherFragment extends Fragment{

    ViewModel vm;

    TextView currentTemperature;
    TextView currentHumidity;
    TextView currentWind;
    TextView currentCloud;
    TextView currentTimeStamp;
    TextView currentCondition;
    ImageView currentIcon;

    Context con;

    LinearLayout currentWeatherlayour;


    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_current_weather, viewGroup, false);

        currentTemperature = (TextView)  v.findViewById(R.id.current_temperature);
        currentHumidity = (TextView)  v.findViewById(R.id.current_humidity);
        currentWind = (TextView)  v.findViewById(R.id.current_wind);
        currentCloud = (TextView)  v.findViewById(R.id.current_cloud);
        currentTimeStamp = (TextView)  v.findViewById(R.id.current_timeStamp);
        currentCondition = (TextView)  v.findViewById(R.id.current_update);
        currentIcon= (ImageView)  v.findViewById(R.id.current_icon);

        return v;
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        con = activity.getApplicationContext();
        vm = ViewModel.getInstance();

    }
    public void onResume(){
        super.onResume();
        requestWeatherData();
    }

    public void showCurrentWeatherData(CurrentWeatherData currentWeatherData){

        if (currentWeatherData != null && currentTemperature !=null) {


            currentTemperature.setText("Temperature: " + currentWeatherData.getTemperature() + "ºC");
            currentHumidity.setText("Humidity: " + currentWeatherData.getHumidity());
            currentWind.setText("Wind Speed: " + currentWeatherData.getWindSpeed() + "m/s");
            currentCloud.setText("Cloudiness: " + currentWeatherData.getCloudiness() + "%");
            currentTimeStamp.setText("Latest Update: " + currentWeatherData.getTimeStamp().getTime());
            WeatherCondition condition = currentWeatherData.getConditions();
            currentCondition.setText(condition.toString());

            int identifier;
            String iconName = "icon" + currentWeatherData.getConditions().getIcon();
            identifier = con.getResources().getIdentifier(iconName, "drawable", con.getPackageName());

            currentIcon.setImageResource(identifier);
        }

    }

    public void requestWeatherData(){
        vm.requestCurrentWeatherData();
    }

}
