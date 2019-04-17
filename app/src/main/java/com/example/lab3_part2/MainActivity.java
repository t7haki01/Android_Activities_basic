package com.example.lab3_part2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);

        mainLayout.setLayoutParams(layoutParams);

        Button mapBtn = new Button(this);

        mapBtn.setText("OPEN MAP");

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapBtnClicked();
            }
        });

        LinearLayout.LayoutParams mapBtnLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mapBtn.setLayoutParams(mapBtnLayoutParam);

        Button callBtn = new Button(this);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBtnClicked();
            }
        });

        callBtn.setText("CREATE CALL");

        LinearLayout.LayoutParams callBtnLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        callBtn.setLayoutParams(callBtnLayoutParam);


        LinearLayout webLayout = new LinearLayout(this);

        ViewGroup.LayoutParams webParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        webLayout.setOrientation(LinearLayout.HORIZONTAL);

        webLayout.setLayoutParams(webParams);

        editText = new EditText(this);
        ViewGroup.LayoutParams editParams = new LinearLayout.LayoutParams(600, ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(editParams);


        Button webBtn = new Button(this);
        webBtn.setText("GO");

        LinearLayout.LayoutParams webBtnLayoutParam = new LinearLayout.LayoutParams(180, ViewGroup.LayoutParams.WRAP_CONTENT);
        webBtn.setLayoutParams(webBtnLayoutParam);

        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webBtnClicked();
            }
        });


        mainLayout.addView(mapBtn);
        mainLayout.addView(callBtn);

        webLayout.addView(editText);
        webLayout.addView(webBtn);

        mainLayout.addView(webLayout);

        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams imageLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageLayout);

        String imageAddress = "https://www.oamk.fi/files/3115/2887/8059/Toimistokayttoon_Suomeksi-06.png";

        new DownLoadImageTask(imageView).execute(imageAddress);

        mainLayout.addView(imageView);

        setContentView(mainLayout);
    }

    public void mapBtnClicked(){

        Uri location = Uri.parse("https://www.google.com/maps/search/?api=1&query=oamk+kotkantie");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);


        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        String googleMap = "com.google.android.apps.maps/com.google.android.maps.MapsActivity";

        Intent gMapIntent = Intent.createChooser(mapIntent, googleMap);

        Log.d("From List", ""+activities);

        for(int i = 0; i<activities.size(); i++){
            ResolveInfo resolveInfo = activities.get(i);
            String packageName = resolveInfo.activityInfo.packageName;
            if(packageName.contains("android.maps")){
                gMapIntent.setPackage(packageName);
            }
        }

        Log.d("Map intent info", ""+mapIntent);

        if (isIntentSafe) {
//            This will popups the available options for the map
            startActivity(mapIntent);
        }
    }

    public void callBtnClicked(){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if(isIntentSafe)
            startActivity(callIntent);
    }

    public void webBtnClicked(){
        String address = editText.getText().toString();
        String httpAddress = "http://" + address ;
        Uri webpage = Uri.parse(httpAddress);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(webIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        Log.d("From webBtn", ""+activities);

        if(isIntentSafe)
            startActivity(webIntent);
    }
}

class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>{
    ImageView imageView;

    public DownLoadImageTask(ImageView imageView){
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String...urls){

        URL imageUri = null;

        try{
            imageUri = new URL(urls[0]);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        Bitmap bitmap = null;
        try{
            bitmap = BitmapFactory.decodeStream(imageUri.openConnection() .getInputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }
    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}




