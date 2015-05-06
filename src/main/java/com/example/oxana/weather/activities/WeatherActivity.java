package com.example.oxana.weather.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.oxana.weather.Location;
import com.example.oxana.weather.R;
import com.example.oxana.weather.fragments.ChooseLocationFragment;
import com.example.oxana.weather.fragments.CurrentWeatherFragment;
import com.example.oxana.weather.fragments.HourlyForecastFragment;
import com.example.oxana.weather.fragments.WeatherForecastFragment;
import com.example.oxana.weather.viewModel.IView;
import com.example.oxana.weather.viewModel.ViewModel;
import com.example.oxana.weather.weather_info.CurrentWeatherData;
import com.example.oxana.weather.weather_info.HourlyPrediction;
import com.example.oxana.weather.weather_info.WeatherPrediction;

import static com.example.oxana.weather.fragments.ChooseLocationFragment.*;
import static com.example.oxana.weather.viewModel.IView.DisplayedInformation.CURRENT;
import static com.example.oxana.weather.viewModel.IView.DisplayedInformation.FORECAST;
import static com.example.oxana.weather.viewModel.IView.DisplayedInformation.HOURLY;


public class WeatherActivity  extends android.support.v7.app.ActionBarActivity implements IView{
    //location_information.xml
    ProgressBar locationProgress;
    LinearLayout locationInformation;
    //weather_information.xml
    ToggleButton currentWeatherToggle;
    ToggleButton hourlyWeatherToggle;
    ToggleButton forecastWeatherToggle;
    //weather_activity.xml
    TextView textwarning;
    TextView locationName;
    TextView coordinatesName;
    TextView temperatureName;
    ImageView iconName;
    ProgressBar weatherInfo;

    ViewModel viewModel;



    FrameLayout weather_fragmen_layout;

    CurrentWeatherFragment currentWeatherFragment;
    WeatherForecastFragment forecastWeatherFragment;
    HourlyForecastFragment hourlyForecastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        locationProgress = (ProgressBar) findViewById(R.id.locationProgress_info);
        textwarning = (TextView) findViewById(R.id.warningLabel);
        locationName = (TextView) findViewById(R.id.location);
        coordinatesName = (TextView) findViewById(R.id.coordinates);
        temperatureName = (TextView) findViewById(R.id.temperature);
        iconName = (ImageView) findViewById(R.id.bigicon);

        locationInformation = (LinearLayout) findViewById(R.id.layoutProgress);

        currentWeatherToggle = (ToggleButton)findViewById(R.id.current_toggle);


        hourlyWeatherToggle = (ToggleButton)findViewById(R.id.hourly_toggle);
        forecastWeatherToggle = (ToggleButton)findViewById(R.id.forecast_toggle);


        weather_fragmen_layout = (FrameLayout) findViewById(R.id.fragment_container);
        weatherInfo = (ProgressBar) findViewById(R.id.weatherProgress);



        hideErrorMessage();


        currentWeatherFragment = new CurrentWeatherFragment();
        forecastWeatherFragment = new WeatherForecastFragment();
        hourlyForecastFragment = new HourlyForecastFragment();



       /* FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, currentWeatherFragment);
        fragmentTransaction.commit();

        switchWeatherInfo(FORECAST);*/


        viewModel = ViewModel.getInstance();
        viewModel.connectView(this);




    }


    public void onCurrentToggleClicked (View v){

            viewModel.onShowCurrentWeatherRequested();


    }

    public void onForecastToggleClicked (View v){

            viewModel.onShowWeatherForecastRequested();


    }

    public void onHourlyToggleClicked (View v){


            viewModel.onShowHourlyForecastRequested();

    }

    public void toggleButtons(DisplayedInformation display){
        currentWeatherToggle.setChecked(display == DisplayedInformation.CURRENT);
        forecastWeatherToggle.setChecked(display == DisplayedInformation.FORECAST);
        hourlyWeatherToggle.setChecked(display == DisplayedInformation.HOURLY);

    }

    public void  onSaveInstanceState(Bundle bundle){


    }





    @Override
    public void onResume(){
        super.onResume();
        viewModel.connectView(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideErrorMessage(){
        weather_fragmen_layout.setVisibility(View.VISIBLE);
        weatherInfo.setVisibility((View.INVISIBLE));

        textwarning.setVisibility(View.INVISIBLE);

    }

    @Override
    public void askForLocation() {

        FragmentManager fm = getFragmentManager();
        ChooseLocationFragment locationFragment = new ChooseLocationFragment();
        locationFragment.show(fm, "Dialog");


    }

    public void onClicckTextLocation(View v){
            askForLocation();

    }

    @Override
    public void showLocation(Location location) {

        if(location != null){
            startShowLocationSearchInProgress();
            locationName.setText(location.getName()+ "," + location.getCountry());
            coordinatesName.setText(location.getCoordinateString());
            stopShowLocationSearchInProgress();

        }
        else {
            startShowLocationSearchInProgress();
            locationName.setText("Castellon, Spain");
            coordinatesName.setText("(39.99º N,0.05 W)");
            stopShowLocationSearchInProgress();

        }

    }

    @Override
    public void warnWrongLocation(String locationName) {
        String error = "No location found: ";
        textwarning.setText(error + locationName);
        Toast.makeText(this,error,Toast.LENGTH_SHORT);

    }

    @Override
    public void showError(String message) {

        textwarning.setVisibility(View.VISIBLE);
        weather_fragmen_layout.setVisibility(View.INVISIBLE);
        textwarning.setText(message);


    }

    @Override
    public void showCurrentWeatherData(CurrentWeatherData currentWeatherData) {

        startShowLocationSearchInProgress();
        String currentText = "big"+ currentWeatherData.getConditions().getIcon();
        int identifier = getResources().getIdentifier(currentText,"drawable",getPackageName());
        iconName.setImageResource(identifier);
        stopShowLocationSearchInProgress();

        if (currentWeatherData!=null) {
            temperatureName.setText(String.format("%.2f\272 C", currentWeatherData.getTemperature()));
        }



        else {
            currentText = "dunno";
            temperatureName.setText("ºC");

        }



        if(viewModel.getCurrentDisplay() == DisplayedInformation.CURRENT && currentWeatherFragment != null ){
            if (currentWeatherData !=null){
                //Log.d("Current weather data no es null y el viento es " + currentWeatherData.getConditions() , "funciona?" );
                hideErrorMessage();
                currentWeatherFragment.showCurrentWeatherData(currentWeatherData);

                Log.d("currentweatherfragment"+ currentWeatherData.getTemperature(), "show");
            }
            else{
                showError("Error");

            }

        }
    }

    @Override
    public void showWeatherForecast(WeatherPrediction[] forecast) {

        if(viewModel.getCurrentDisplay() == FORECAST && forecastWeatherFragment != null){
            if (forecast != null){
                hideErrorMessage();
                forecastWeatherFragment.showWeatherForecast(forecast);
            }
            else {
                showError("Errooor");
            }
        }

    }

    @Override
    public void showHourlyForecast(HourlyPrediction[] forecast) {

        if(viewModel.getCurrentDisplay() == DisplayedInformation.HOURLY && hourlyForecastFragment != null){
            if(forecast != null){
                hideErrorMessage();
                hourlyForecastFragment.showHourlyForecast(forecast);
            }
        }

    }

    @Override
    public void startShowLocationSearchInProgress() {
        locationProgress.setVisibility(View.VISIBLE);
        locationInformation.setVisibility(View.INVISIBLE);

    }

    @Override
    public void stopShowLocationSearchInProgress() {

        locationProgress.setVisibility(View.INVISIBLE);
        locationInformation.setVisibility(View.VISIBLE);

    }

    @Override
    public void startShowWeatherSearchInProgress() {

        weatherInfo.setVisibility(View.VISIBLE);
        weather_fragmen_layout.setVisibility(View.INVISIBLE);

    }

    @Override
    public void stopShowWeatherSearchInProgress() {
        weatherInfo.setVisibility(View.INVISIBLE);
        weather_fragmen_layout.setVisibility(View.VISIBLE);


    }

    @Override
    public void switchWeatherInfo(DisplayedInformation displayedInformation) {
/*
        FragmentManager fragmentManager = getFragmentManager();
        CalculatorFragment newFragment = new CalculatorFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.calculator_container, newFragment)
                .commit();

                */

        //Algo asi pero con fragmentos del tiempo

        FragmentManager fragmentManager = getFragmentManager();
        toggleButtons(displayedInformation);

        switch (viewModel.getCurrentDisplay()) {
            case CURRENT:
                fragmentManager.beginTransaction().replace(R.id.fragment_container, currentWeatherFragment).commit();

                break;
            case HOURLY:
                fragmentManager.beginTransaction().replace(R.id.fragment_container, hourlyForecastFragment).commit();

                break;
            case FORECAST:
                fragmentManager.beginTransaction().replace(R.id.fragment_container, forecastWeatherFragment).commit();

                break;

            default: fragmentManager.beginTransaction().replace(R.id.fragment_container, currentWeatherFragment).commit();

        }




    }

    public DialogListener getLocationListener() {
        return new DialogListener() {
            @Override
            public void onPositiveButtonClick(String locationName) {
                viewModel.onLocationNameEntered(locationName);

                startShowLocationSearchInProgress();

                showLocation(viewModel.getLocation());

                stopShowLocationSearchInProgress();

            }
        };
    }
}
