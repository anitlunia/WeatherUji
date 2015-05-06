package com.example.oxana.weather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.graphics.Rect;
import android.view.WindowManager;

/**
 * Created by Fernando on 03/05/2015.
 */
public class TemperatureGraph extends View {
    private static final int RADIUS = 5;
    private float[] temperatures;
    private float maxTemp;
    private float minTemp;

    public TemperatureGraph(Context context) {
        super(context);
    }
    public TemperatureGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TemperatureGraph(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onDraw(Canvas canvas){


        Rect bounds = new Rect();
        canvas.getClipBounds(bounds);
        int centerX = (bounds.left + bounds.right) / 2;
        int centerY = (bounds.top + bounds.bottom) / 2;
        int radius = Math.min(bounds.width(), bounds.height()) / 5;
        Paint paint = new Paint();
        float sq3r = radius * (float)Math.sqrt(3);
        paint.setARGB(128, 255, 0, 0);
        canvas.drawCircle(centerX, centerY + sq3r / 3, radius, paint);
        paint.setARGB(128, 0, 255, 0);
        canvas.drawCircle(centerX + radius / 2, centerY - sq3r / 6,
                radius, paint);
        paint.setARGB(128, 0, 0, 255);
        canvas.drawCircle(centerX - radius / 2, centerY - sq3r / 6,
                radius, paint);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager =
                (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        float density = displayMetrics.density;
        float scaledDensity = displayMetrics.scaledDensity;




    }

    public void getTemperatures(float[] temperatures){

    }



}
