package com.example.coffeepedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.droidsonroids.gif.GifImageView;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        GifImageView image = findViewById(R.id.mexicans);
        image.setVisibility(View.INVISIBLE);
    }

    public void play(View v)
    {
        GifImageView mex = findViewById(R.id.mexicans);
        mex.setVisibility(View.VISIBLE);
        startService(new Intent(this, MyService.class));
    }
    public void stop(View v)
    {
        GifImageView mex = findViewById(R.id.mexicans);
        mex.setVisibility(View.INVISIBLE);
        stopService(new Intent(this, MyService.class));
    }
    public void goHome(View v)
    {
        stopService(new Intent(this, MyService.class));
        Intent in = new Intent(this, MainNavActivity.class);
        startActivity(in);
    }
}
