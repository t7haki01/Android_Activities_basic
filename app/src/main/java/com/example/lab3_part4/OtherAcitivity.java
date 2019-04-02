package com.example.lab3_part4;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

public class OtherAcitivity extends AppCompatActivity {
    ConstraintLayout otherLayout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String count = intent.getStringExtra("COUNTER");
        otherLayout = new ConstraintLayout(this);
        textView = new TextView(this);
        int textViewId = ViewCompat.generateViewId();
        textView.setId(textViewId);
        textView.setText(count);
        textView.setTextSize(24);

        ConstraintLayout.LayoutParams textParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.topMargin = 30;
        textView.setLayoutParams(textParams);

        otherLayout.addView(textView);

        ConstraintSet textSet = new ConstraintSet();
        textSet.clone(otherLayout);
        textSet.connect(textView.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        textSet.connect(textView.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        textSet.connect(textView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        textSet.connect(textView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        textSet.applyTo(otherLayout);

        setContentView(otherLayout);

        new CountDownTimer(Integer.parseInt(count)*1000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(""+ (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView.setText("Done!");
            }
        }.start();


    }
}
