package com.example.flappyfish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChartsActivity extends AppCompatActivity {
    private Button BtnTroVe;
    private List<Player> players;
    private PlayerAdapter playerAdapter;
    private ListView listView;
    private DAOPlayer daoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        players = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        playerAdapter = new PlayerAdapter(this,R.layout.layout_items,players);
        listView.setAdapter(playerAdapter);
        daoPlayer = new DAOPlayer();
        loadData();

        BtnTroVe = (Button) findViewById(R.id.btnTroVe);
        BtnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ChartsActivity.this, HomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });


    }

    private void loadData(){
        daoPlayer.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                players.clear();
                for(DataSnapshot snap : snapshot.getChildren()) {
                    Player player = snap.getValue(Player.class);
                    players.add(player);
                }
                playerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}