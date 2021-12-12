package com.example.coffeepedia;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Comfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirmation);

        TextView tv = findViewById(R.id.result);
        Intent in = getIntent();
        String mes = in.getStringExtra(FindYourTasteActivity.mes);
        tv.setText(mes);

        //redirects user to home page in 15sec
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(Comfirmation.this,MainNavActivity.class);
                startActivity(i);
            }
        }, 15000);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Cafeteria is open from 7:00 until 18:00",Toast.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

        public void setRate(View v)
        {
            TextView tv = findViewById(R.id.RatingTv);
            RatingBar rb = findViewById(R.id.ratingBar);

            double value = rb.getRating();
            tv.setText("Rating = " + value +"/5");
            Toast.makeText(this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
        }

}
