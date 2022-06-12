package com.example.flappyfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerInfoActivity extends AppCompatActivity {

    private Button btnSave;
    private Button btnNext;
    private TextView DisplayScore;
    private EditText EdtTen;
    private int score;
    DAOPlayer daoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);

        Intent receiveIntent = getIntent();
        score = receiveIntent.getIntExtra("score",0);
        daoPlayer = new DAOPlayer();

        DisplayScore = (TextView) findViewById(R.id.Score);
        DisplayScore.setText("Score: "+score);

        EdtTen = (EditText) findViewById(R.id.edtten);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = new Player(EdtTen.getText().toString(), score);
                daoPlayer.add(player).addOnSuccessListener(suc ->{
                    Toast.makeText(PlayerInfoActivity.this,"Save player success",Toast.LENGTH_LONG).show();
                }).addOnFailureListener(er ->{
                    Toast.makeText(PlayerInfoActivity.this,"" + er.getMessage(),Toast.LENGTH_LONG).show();
                });
            }
        });

        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(PlayerInfoActivity.this, GameOverActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }
}