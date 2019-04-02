package com.example.lab3_part3;

import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ConstraintLayout imageLayout;
    ImageView firstImage;
    ImageView secondImage;
    ImageView thirdImage;
    ImageView fourthImage;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        imageLayout = findViewById(R.id.imageView);
        firstImage = findViewById(R.id.firstAnimal);
        secondImage = findViewById(R.id.secondAnimal);
        thirdImage = findViewById(R.id.thirdAnimal);
        fourthImage = findViewById(R.id.fourthAnimal);

        mediaPlayer = new MediaPlayer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.option_mammals) {
            mammalsOption();

        } else if (item.getItemId() == R.id.option_birds) {
            birdOption();
        }
        return true;
    }

    public void mammalsOption(){
        firstImage.setImageResource(R.drawable.bear);
        firstImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bearClicked();
            }
        });
        secondImage.setImageResource(R.drawable.wolf);
        secondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wolfClicked();
            }
        });
        thirdImage.setImageResource(R.drawable.elephant);
        thirdImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elephantClicked();
            }
        });
        fourthImage.setImageResource(R.drawable.lamb);
        fourthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lambClicked();
            }
        });
    }

    public void birdOption(){
        firstImage.setImageResource(R.drawable.huuhkaja);
        firstImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huuhkajaClicked();
            }
        });
        secondImage.setImageResource(R.drawable.peippo);
        secondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peippoClicked();
            }
        });
        thirdImage.setImageResource(R.drawable.peukaloinen);
        thirdImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peukaloinenClicked();
            }
        });
        fourthImage.setImageResource(R.drawable.punatulkku);
        fourthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                punatulkkuClicked();
            }
        });
    }

    public void bearClicked(){
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.bear);
        mediaPlayer.start();
    }

    public void wolfClicked(){
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.wolf);
        mediaPlayer.start();
    }

    public void elephantClicked(){
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.elephant);
        mediaPlayer.start();
    }

    public void lambClicked(){
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.lamb);
        mediaPlayer.start();
    }

    public void huuhkajaClicked(){
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.huuhkaja_norther_eagle_owl);
        mediaPlayer.start();
    }

    public void peippoClicked(){
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.peippo_chaffinch);
        mediaPlayer.start();
    }

    public void peukaloinenClicked(){
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.peukaloinen_wren);
        mediaPlayer.start();
    }

    public void punatulkkuClicked(){
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.punatulkku_northern_bullfinch);
        mediaPlayer.start();
    }
}