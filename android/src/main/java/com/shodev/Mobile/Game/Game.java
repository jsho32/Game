package com.shodev.Mobile.Game;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.Getter;

@Getter
public class Game extends Application {
    private int maxLives;
    private int pointScore;
    private static Game inst;

    @Override
    public void onCreate() {
        super.onCreate();
        if (inst == null) {
            inst = new Game();
        }
        maxLives = 10;
        resetScore();
    }

    /** Rest score back to zero */
    public void resetScore() {
        pointScore = 0;
    }

    /** Increment score based on amount of bursted chips */
    public void incrementScore(List<Integer> positions, TextView points) {
        pointScore += (positions.size() - 1) * 10;
        points.setText(String.valueOf(pointScore));
    }

    public void countDown(final Context context, final Class destination, final TextView time) {
        new CountDownTimer(12000, 1000) {

            public void onTick(long millisUntilFinished) {
                long minutes = TimeUnit.MINUTES.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                long seconds = TimeUnit.SECONDS.convert((millisUntilFinished - (minutes * 60000)), TimeUnit.MILLISECONDS);
                String divider = seconds < 10 ? ":0" : ":";
                time.setText(String.valueOf(minutes) + divider + String.valueOf(seconds));
            }

            public void onFinish() {
                time.setText("*0:00*");
                Intent intent = new Intent(context, destination);
                context.startActivity(intent);
            }
        }.start();

    }


}