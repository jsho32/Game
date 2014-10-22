package com.shodev.Mobile.Game;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimeMode extends Activity {
    private int[] shapeArray;
    private TextView points;
    private TextView time;
    private TextView lives;

    /** Called when activity is created */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_mode_activity);
        setShapeArray();
        setTextViews();
        gameRun();
    }


    /** runs on the thread */
    private void gameRun() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GridView gridview = (GridView) findViewById(R.id.shapeGrid);
                final TimeGridAdapter gridadapter = new TimeGridAdapter(TimeMode.this, shapeArray);
                gridview.setAdapter(gridadapter);
                new CountDownTimer(120000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        long minutes = TimeUnit.MINUTES.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                        long seconds = TimeUnit.SECONDS.convert((millisUntilFinished - (minutes * 60000)), TimeUnit.MILLISECONDS);
                        String divider = seconds < 10 ? ":0" : ":";
                        time.setText(String.valueOf(minutes) + divider + String.valueOf(seconds));
                    }

                    public void onFinish() {
                        time.setText("*0:00*");
                    }
                }.start();

            }
        });
    }

    /** Uses random number generator to set values in array that is displayed in grid */
    private void setShapeArray() {
        int max = 5;
        int min = 1;
        shapeArray = new int[100];
        for (int i = 0; i < 100; i++) {
            Random rand = new Random();
            int randomNumber = rand.nextInt((max - min) + 1) + min;
            shapeArray[i] = randomNumber;
        }
    }

    /** Sets the initial values of all text views in activity layout */
    private void setTextViews() {
        points = (TextView) findViewById(R.id.points);
        time = (TextView) findViewById(R.id.timer);
        lives = (TextView) findViewById(R.id.lives);
        points.setText("0");
        time.setText("2:00");
        lives.setText("10");
    }

}
