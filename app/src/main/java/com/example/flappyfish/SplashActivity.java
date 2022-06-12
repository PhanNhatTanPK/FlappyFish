package com.example.flappyfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private  Animation topAnim, bottomAnim;
    private ImageView image;
    private TextView logo;
    private Intent intentMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        intentMusic = new Intent(SplashActivity.this, MyService.class);
        startService(intentMusic);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.topanimation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottomaimation);

        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView3);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3500);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {

                    Intent homeIntent  = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}