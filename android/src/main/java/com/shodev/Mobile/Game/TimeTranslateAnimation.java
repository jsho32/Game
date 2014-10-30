package com.shodev.Mobile.Game;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.GridView;

public class TimeTranslateAnimation implements Animation.AnimationListener {
    private int position;
    private GridView gridView;

    TimeTranslateAnimation(GridView gridView, int position) {
        this.position = position;
        this.gridView = gridView;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onAnimationEnd(Animation animation) {
        gridView.getChildAt(position).clearAnimation();
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(gridView.getChildAt(position).getWidth(), gridView.getChildAt(position).getHeight());
        gridView.getChildAt(position).setLayoutParams(lp);
        gridView.getChildAt(position).setAlpha(1);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
