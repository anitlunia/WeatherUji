package com.example.oxana.weather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Hecate on 23/04/2015.
 */
public class NetworkHelper {

    private  Context mContext;

    public NetworkHelper (Context con){
        mContext = con;

    }

    boolean isNetworkConnected(){

        ConnectivityManager connMr = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
           return true;
        } else {
            return false;
        }

    }

    private static String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
// Starts the query
            conn.connect();
            is = conn.getInputStream();
            Scanner s = new Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }



    public void fetchWebPage(final String url,
                             final ResponseReceiver<String> pageProcessor) {
        if (!isNetworkConnected())
            pageProcessor.onErrorReceived("No network access");
        else {

            new AsyncTask<String, Void, Boolean>(){
                String result;
                @Override
                protected Boolean doInBackground(String... urls) {
                    try{
                    result = downloadUrl(urls[0]);
                    return true;
                    }

                    catch (IOException e){
                        return false;
                    }

                }
                @Override
                protected void onPostExecute(Boolean taskOk) {
                    if (taskOk)
                        pageProcessor.onResponseReceived(result);
                    else
                        pageProcessor.onErrorReceived(result);
                }

            }
                    .execute(url);
        }
    }

}
