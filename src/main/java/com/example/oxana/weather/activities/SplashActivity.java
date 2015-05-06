package com.example.oxana.weather.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.oxana.weather.NetworkHelper;
import com.example.oxana.weather.OpenWeather;
import com.example.oxana.weather.R;
import com.example.oxana.weather.ResponseReceiver;
import com.example.oxana.weather.model.IModel;
import com.example.oxana.weather.model.Model;
import com.example.oxana.weather.viewModel.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Oxana on 17/04/2015.
 */
public class SplashActivity extends Activity {

    private boolean timeoutEnded = false;
    private boolean weatherReceived = false;
    private long TIMEOUT = 3000;
    private String LOCATION = "location";
    //private Context context;

    private ViewModel vm;
    private IModel mod;
    private String loc;
    private String PREFERENCES_ID = "Preferences";
    private String DEFAULT_LOCATION = "Sagunto";

    private ProgressBar bar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_activity);

        mod = new Model(new OpenWeather(new NetworkHelper(this.getBaseContext())));

        SharedPreferences preferences = getSharedPreferences(PREFERENCES_ID, MODE_PRIVATE);
        loc = preferences.getString(LOCATION, DEFAULT_LOCATION);

        vm = ViewModel.buildInstance(mod,loc, new ResponseReceiver<String>() {
            @Override
            public void onResponseReceived(String response) {
                weatherReceived = true;
                startWeatherActivity();
            }

            @Override
            public void onErrorReceived(String message) {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                weatherReceived =true;
                startWeatherActivity();

            }

        });



       //Timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeoutEnded = true;
                startWeatherActivity();
            }
        }, TIMEOUT);

    }


    private void startWeatherActivity() {
        if (!timeoutEnded || !weatherReceived)
            return;
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
        finish();
    }

    /*public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(,menu);
        return true;
    }*/
}
