package com.example.oxana.weather;

/**
 * Created by Oxana on 17/04/2015.
 */


public interface ResponseReceiver<T> {
    void onResponseReceived (T response);
    void onErrorReceived(String message);
}


