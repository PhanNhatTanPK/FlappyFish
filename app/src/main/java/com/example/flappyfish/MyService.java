package com.example.flappyfish;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    MediaPlayer player;

    public MyService() {
    }

    @Override
    public void onCreate() {
        // khởi tạo cho biến player từ bài nhạc lưu trong thư mục raw
        player = MediaPlayer.create(this, R.raw.fish1);
        player.setLooping(true);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // chạy file nhạc
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (player.isPlaying()) {
            // dừng phát nhạc
            player.stop();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}