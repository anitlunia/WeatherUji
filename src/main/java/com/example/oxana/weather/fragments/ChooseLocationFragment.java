package com.example.oxana.weather.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.content.DialogInterface;

import com.example.oxana.weather.R;
import com.example.oxana.weather.activities.WeatherActivity;

/**
 * Created by Hecate on 05/05/2015.
 */
public class ChooseLocationFragment extends DialogFragment {

    public interface DialogListener {
        public void onPositiveButtonClick(String locationName);
    }

    private DialogListener mListener;
    EditText editTextLocation;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mListener = ((WeatherActivity) activity).getLocationListener();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        LayoutInflater inflater = this.getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ask_location, null);
        editTextLocation = (EditText) view.findViewById(R.id.input_location);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity(),
                AlertDialog.THEME_HOLO_DARK);
        builder.setMessage("Insert location").setPositiveButton("Find", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                String locationName = editTextLocation.getText().toString();
                mListener.onPositiveButtonClick(locationName);

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    }







