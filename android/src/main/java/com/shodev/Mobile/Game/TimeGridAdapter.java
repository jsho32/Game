package com.shodev.Mobile.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
        final TextView shapeView;
        if (view == null) {
            shapeView = new TextView(context);
        } else {
            shapeView = (TextView) view;
        }

        shapeView.setText(String.valueOf(data[position]));
        shapeView.setGravity(Gravity.CENTER);
        shapeView.setClickable(true);

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
}
