package com.example.lab3_part4;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    TextView guideText;
    EditText counter;
    Button startBtn;
    ConstraintLayout mainLayout;
    int COUNT_NUMBER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainLayout = new ConstraintLayout(this);
        guideText = new TextView(this);
        counter = new EditText(this);
        startBtn = new Button(this);

        int mainId = ViewCompat.generateViewId();
        mainLayout.setId(mainId);

        int guideId = ViewCompat.generateViewId();
        guideText.setId(guideId);

        int counterId = ViewCompat.generateViewId();
        counter.setId(counterId);

        int startId = ViewCompat.generateViewId();
        startBtn.setId(startId);

        guideText.setText("Enter Time (Seconds)");
        guideText.setTextSize(30);

        ConstraintLayout.LayoutParams guideParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        guideText.setLayoutParams(guideParams);


        mainLayout.addView(guideText);

        ConstraintSet guideTextSet = new ConstraintSet();
        guideTextSet.clone(mainLayout);
        guideTextSet.connect(guideText.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        guideTextSet.connect(guideText.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        guideTextSet.connect(guideText.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 10);
        guideTextSet.connect(guideText.getId(), ConstraintSet.BOTTOM, counter.getId(), ConstraintSet.TOP, 0);
        guideTextSet.applyTo(mainLayout);

        counter.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

        mainLayout.addView(counter);


        ConstraintLayout.LayoutParams counterParams = new ConstraintLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);

        counter.setLayoutParams(counterParams);
        counter.setGravity(Gravity.CENTER_HORIZONTAL);
        counter.setTextSize(30);

        ConstraintSet counterSet = new ConstraintSet();
        counterSet.clone(mainLayout);
        counterSet.connect(counter.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        counterSet.connect(counter.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        counterSet.connect(counter.getId(), ConstraintSet.TOP, guideText.getId(), ConstraintSet.BOTTOM, 0);
        counterSet.connect(counter.getId(), ConstraintSet.BOTTOM, startBtn.getId(), ConstraintSet.TOP, 0);
        counterSet.applyTo(mainLayout);

        startBtn.setText("START");
        startBtn.setTextSize(30);


        ConstraintLayout.LayoutParams startParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        startBtn.setLayoutParams(startParams);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passCounter(v);
            }
        });

        mainLayout.addView(startBtn);
        ConstraintSet startSet = new ConstraintSet();
        startSet.clone(mainLayout);
        startSet.connect(startBtn.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        startSet.connect(startBtn.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        startSet.connect(startBtn.getId(), ConstraintSet.TOP, counter.getId(), ConstraintSet.BOTTOM, 10);
        startSet.connect(startBtn.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);

        startSet.applyTo(mainLayout);

        setContentView(mainLayout);
    }

    public void passCounter(View view){
        Intent intent = new Intent(this, OtherAcitivity.class);
        String count = counter.getText().toString();
        intent.putExtra("COUNTER", count);
        startActivity(intent);
    }
}
