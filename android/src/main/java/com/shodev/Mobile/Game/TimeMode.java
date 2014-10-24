package com.shodev.Mobile.Game;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimeMode extends Activity {
    private int[] shapeArray;
    private final int boardSize = 81;
    private final int columnSize = 9;
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
                final List<Integer> selectedValues = new ArrayList<Integer>();
                final List<Integer> selectedPositions = new ArrayList<Integer>();
                final GridView gridview = (GridView) findViewById(R.id.shapeGrid);
                final TimeGridAdapter gridadapter = new TimeGridAdapter(TimeMode.this, shapeArray);
                gridview.setAdapter(gridadapter);
                gridview.requestDisallowInterceptTouchEvent(true);
                gridview.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int position;
                        switch (event.getAction()) {
                            case (MotionEvent.ACTION_DOWN):
                                position = gridview.pointToPosition((int) event.getX(), (int) event.getY());
                                if (gridview.pointToPosition((int)event.getX(), (int)event.getY())!=-1) {
                                    gridview.getChildAt(position).setBackgroundColor(Color.parseColor("#1e6dc8"));
                                    if (!selectedPositions.contains(position)) {
                                        selectedValues.add(gridadapter.getPositionValue(position));
                                        selectedPositions.add(position);
                                    }
                                }
                                return true;

                            case (MotionEvent.ACTION_MOVE):
                                position = gridview.pointToPosition((int) event.getX(), (int) event.getY());
                                if (gridview.pointToPosition((int)event.getX(), (int)event.getY())!=-1) {
                                    gridview.getChildAt(position).setBackgroundColor(Color.parseColor("#1e6dc8"));
                                    if (!selectedPositions.contains(position)) {
                                        selectedValues.add(gridadapter.getPositionValue(position));
                                        selectedPositions.add(position);
                                    }
                                }
                                return true;

                            case (MotionEvent.ACTION_UP):
                                if (isBurstable(selectedValues)) {
                                    for (int ints : selectedPositions) {
                                        gridview.getChildAt(ints).setBackgroundColor(Color.parseColor("#000000"));
                                    }
                                    burstChips(gridview, gridadapter, selectedPositions);
                                } else {
                                    for (int ints : selectedPositions) {
                                        gridview.getChildAt(ints).setBackgroundColor(Color.TRANSPARENT);
                                    }
                                }
                                selectedPositions.clear();
                                selectedValues.clear();
                                break;
                        }

                        return false;
                    }
                });

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

    /** Checks to see that all values are identical and acceptable for bursting */
    private boolean isBurstable(List<Integer> values) {
        int currentValue;
        if (values.size() != 0) {
            currentValue = values.get(0);
        } else {
            return false;
        }

        if (values.size() >= 2) {
            for (int ints : values) {
                if (currentValue != ints) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /** Bursts all applicable chips and moves views above bursted view down */
    private void burstChips(GridView gridView, TimeGridAdapter gridAdapter, List<Integer> positions) {
        for (int ints : positions) {
            for (int i = ints; i >= 0; i -= columnSize) {
                if ((i - columnSize) >= 0) {
                    int value = gridAdapter.getPositionValue(i - columnSize);
                    gridAdapter.getView(i - columnSize, gridView.getChildAt(i), gridView);
                    gridAdapter.setPositionValue(i, value);
                } else {
                    Random rand = new Random();
                    int max = 5, min = 1;
                    int randomNumber = rand.nextInt((max - min) + 1) + min;
                    gridAdapter.setPositionValue(i, randomNumber);
                    gridAdapter.getView(i, gridView.getChildAt(i), gridView);
                }
            }
            clearHighlight(gridView, ints);
        }
    }

    /** clears background highlights of selected chips */
    private void clearHighlight(GridView gridView, int position) {
        gridView.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
    }

    /** Uses random number generator to set values in array that is displayed in grid */
    private void setShapeArray() {
        int max = 5;
        int min = 1;
        shapeArray = new int[boardSize];
        for (int i = 0; i < boardSize; i++) {
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
