package com.shodev.Mobile.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TimeGridAdapter extends BaseAdapter {

    private Context context;
    private int[] data = null;

    public TimeGridAdapter(Context context, int[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View view, final ViewGroup parent) {
        final ImageView shapeView;
        if (view == null) {
            shapeView = new ImageView(context);
        } else {
            shapeView = (ImageView) view;
        }

        setImageResource(shapeView, data[position]);
        shapeView.setClickable(true);
        shapeView.setLayoutParams(new AbsListView.LayoutParams(70, 70));

        shapeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == shapeView.getId()) {
                    shapeView.setBackgroundColor(Color.parseColor("#1e6dc8"));
                }
                return false;
            }
        });

        return shapeView;
    }

    private void setImageResource(ImageView view, int value) {
        if (value == 1) {
            view.setImageResource(R.drawable.gold_circle);
        }
        else if (value == 2) {
            view.setImageResource(R.drawable.green_circle);
        }
        else if (value == 3) {
            view.setImageResource(R.drawable.blue_circle);
        }
        else if (value == 4) {
            view.setImageResource(R.drawable.purple_circle);
        }
        else {
            view.setImageResource(R.drawable.white_circle);
        }

    }

}
