package com.example.flappyfish;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOPlayer {
    private DatabaseReference databaseReference;

    public DAOPlayer() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public Task<Void> add(Player player){
        return databaseReference.push().setValue(player);
    }

    public Query get(){
        return databaseReference.orderByKey();
    }
}
