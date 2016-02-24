package com.example.mack.serviceproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


/*
********************************** Project statement************************************************
1. A single screen application with one button and a textview.
2. On click of the button, start a service, and while starting, pass the image url as string (some static image url from web) along with the intent (intent.putextra)
3. Since the service is doing just download task, declare the service as 'IntentService'
4.  Download the image for the passed image url and save it in sdcard
5. Once downloaded, communicate to the activity, that we downloaded the image through  broadcastreceiver. ( Search for service to activity through broadcastreceiver)
6. In activity once we receive the status that image downloaded, set the text in textview as successfully downloaded
 */



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    final String address = "https://i.ytimg.com/vi/UgBBitvVHAg/hqdefault.jpg";  //declraing a static link to downloa dthe image

        Button mackButton = (Button) findViewById(R.id.mackButton);  //created button object

        mackButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //type anythign that you want to do after button clicking
                        TextView mackText = (TextView) findViewById(R.id.mackText);
                        mackText.setText(address);
                        Intent intent = new Intent(MainActivity.this,downloadService.class);  //creating an intent for downloadService
                        intent.putExtra("link",address);
                        startService(intent);  //service is started
                    }
                }
        );


    }
}
