package com.shodev.Mobile.Game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GameEndPopUp extends FragmentActivity {

    /** Called when activity is created */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_pop_activity);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setTextViews();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameEndPopUp.this, MainActivity.class);
        startActivity(intent);
    }

    private void setTextViews() {
        TextView rePlay = (TextView) findViewById(R.id.replay);
        rePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameEndPopUp.this, TimeMode.class);
                startActivity(intent);
            }
        });

        TextView mainMenu = (TextView) findViewById(R.id.pop_main);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameEndPopUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
