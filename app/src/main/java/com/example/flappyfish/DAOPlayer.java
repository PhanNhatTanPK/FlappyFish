package com.example.flappyfish;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOPlayer {
    private DatabaseReference databaseReference;

//    Khởi tạo DAOPlayer
    public DAOPlayer() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

//    Thêm người chơi
    public Task<Void> add(Player player){
        return databaseReference.push().setValue(player);
    }

//    Lấy dữ liệu
    public Query get(){
        return databaseReference.orderByChild("score");
    }
}
