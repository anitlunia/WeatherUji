package com.example.oxana.weather;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.oxana.weather.weather_info.WeatherPrediction;

import java.util.Calendar;
import java.util.List;

/**
 * Created by jvilar on 4/04/15.
 */
public class WeatherPredictionAdapter extends ArrayAdapter<WeatherPrediction> {

    public WeatherPredictionAdapter(Context context, int resource) {
        super(context, resource);
    }

    public WeatherPredictionAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public WeatherPredictionAdapter(Context context, int resource, WeatherPrediction[] objects) {
        super(context, resource, objects);
    }

    public WeatherPredictionAdapter(Context context, int resource, int textViewResourceId, WeatherPrediction[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public WeatherPredictionAdapter(Context context, int resource, List<WeatherPrediction> objects) {
        super(context, resource, objects);
    }

    public WeatherPredictionAdapter(Context context, int resource, int textViewResourceId, List<WeatherPrediction> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Context context = getContext();

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.weather_prediction_row, null);
        }

        WeatherPrediction weatherPrediction = getItem(position);
        if (weatherPrediction != null) {
            TextView label = (TextView) view.findViewById(R.id.predictionLabel);
            ImageView icon = (ImageView) view.findViewById(R.id.predictionIcon);

            if (label != null) {
                Calendar when = weatherPrediction.getTimeStamp();
                String text = String.format("%02d/%02d: %.1f, %.1f, %s",
                        when.get(Calendar.DAY_OF_MONTH),
                        when.get(Calendar.MONTH)+1,
                        weatherPrediction.getMinTemperature(),
                        weatherPrediction.getMaxTemperature(),
                        weatherPrediction.getCondition().toString()
                );
                label.setText(text);
            }
            if (icon != null) {
                String mDrawableName = "icon" + weatherPrediction.getCondition().getIcon();
                Resources resources = context.getResources();
                int resID = resources.getIdentifier(mDrawableName, "drawable", context.getPackageName());
                icon.setImageResource(resID);
            }
        }
        return view;
    }
}
