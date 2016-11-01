package com.ank.dypo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import in.arjsna.swipecardlib.SwipeCardView;
import in.arjsna.*;

public class Main4Activity extends AppCompatActivity {


    static String TAG ="COOL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,0,new String[]{"aaaaa","bbbbbb","ccccc"
        });
        SwipeCardView swipeCardView = (SwipeCardView) findViewById(R.id.frame);
        swipeCardView.setAdapter(arrayAdapter);
        swipeCardView.setFlingListener(new SwipeCardView.OnCardFlingListener() {
            @Override
            public void onCardExitLeft(Object dataObject) {
                Log.i(TAG, "Left Exit");
            }

            @Override
            public void onCardExitRight(Object dataObject) {
                Log.i(TAG, "Right Exit");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                Log.i(TAG, "Adater to be empty");
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                Log.i(TAG, "Scroll");
            }

            @Override
            public void onCardExitTop(Object dataObject) {
                Log.i(TAG, "Top Exit");
            }

            @Override
            public void onCardExitBottom(Object dataObject) {
                Log.i(TAG, "Bottom Exit");
            }
        });
    }
}
