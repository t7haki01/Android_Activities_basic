package com.example.lab3;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout mainLayout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mainLayout = findViewById(R.id.con_layout);
        this.textView = findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.option_red){
            this.mainLayout.setBackgroundColor(Color.RED);
            this.textView.setText("RED");
        }
        else if(item.getItemId() == R.id.option_blue){
            this.mainLayout.setBackgroundColor(Color.BLUE);
            this.textView.setText("BLUE");
        }
        else if(item.getItemId() == R.id.option_yellow){
            this.mainLayout.setBackgroundColor(Color.YELLOW);
            this.textView.setText("YELLOW");
        }
        else if(item.getItemId() == R.id.option_green){
            this.mainLayout.setBackgroundColor(Color.GREEN);
            this.textView.setText("GREEN");
        }
        return true;
    }
}
