package com.shodev.Mobile.Game;

import android.app.Application;

import lombok.Getter;

@Getter
public class Game extends Application {
    private int maxLives;

    @Override
    public void onCreate() {
        maxLives = 10;
    }

}