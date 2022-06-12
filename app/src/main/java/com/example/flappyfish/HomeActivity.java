package com.example.flappyfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button btnbatdau;
    private Button btnthoat;
    private Button btnBangXepHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnbatdau = (Button) findViewById(R.id.btn_batdau);
        btnbatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnBangXepHang = (Button) findViewById(R.id.btn_bangxephang);
        btnBangXepHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ChartsActivity.class);
                startActivity(intent);
            }
        });

        btnthoat = (Button) findViewById(R.id.btn_thoat);
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                Intent intentMusic = new Intent(HomeActivity.this,MyService.class);

                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                stopService(intentMusic);
                finish();
            }
        });
    }
}