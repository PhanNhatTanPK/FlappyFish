package com.example.flappyfish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingFishView extends View {
    private Bitmap fish[] = new Bitmap[6];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;
    private int maxFishY;
    String name;

    private Bitmap smallFish;
    private int smallFishX, smallFishY, smallFishSpeed = 5;

    private Bitmap mediumFish;
    private int mediumFishX, mediumFishY, mediumFishSpeed = 10;

    private Bitmap largeFish;
    private int largeFishX, largeFishY, largeFishSpeed = 15;

    private Bitmap veryLargeFish1;
    private int veryLargeFish1X, veryLargeFish1Y, veryLargeFish1Speed = 30;

    private Bitmap veryLargeFish2;
    private int veryLargeFish2X, veryLargeFish2Y, veryLargeFish2Speed = 35;

    private Bitmap backgroundImage;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    private int canavasWidth, canavasHeight;
    private boolean touch = false;
    private int score, lifeCounter;
    //    Phương thức khởi tạo view
    public FlyingFishView(Context context) {
        super(context);
//        Tạo hình cho nhân vật chính
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish0);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[2] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);
        fish[3] = BitmapFactory.decodeResource(getResources(), R.drawable.fish3);
        fish[4] = BitmapFactory.decodeResource(getResources(), R.drawable.fish4);
        fish[5] = BitmapFactory.decodeResource(getResources(), R.drawable.fish5);

//        Tạo hình nền cho game
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundmain);

//        Tạo cá bé
        smallFish = BitmapFactory.decodeResource(getResources(),R.drawable.smallfish);

//        Tạo cá vừa
        mediumFish = BitmapFactory.decodeResource(getResources(),R.drawable.mediumfish);

//        Tạo cá lớn
        largeFish = BitmapFactory.decodeResource(getResources(),R.drawable.largefish);

//        Tạo cá siêu lớn 1
        veryLargeFish1 = BitmapFactory.decodeResource(getResources(),R.drawable.verylargefish1);

//        Tạo cá siêu lớn 2
        veryLargeFish2 = BitmapFactory.decodeResource(getResources(),R.drawable.verylargefish2);

//        Tạo bảng điểm số
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(80);
        scorePaint.setTypeface(Typeface.DEFAULT);
        scorePaint.setAntiAlias(true);

//        Tạo hình mạng
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.heart1);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart2);

//        Điểm bắt đầu trên trục Y của nhân vật
        fishY = 300;

//        Điểm số ban đầu
        score = 0;

//        Số mạng
        lifeCounter = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Lấy chiều rộng, cao của khung hình
        canavasWidth = canvas.getWidth();
        canavasHeight = canvas.getHeight();

//        Vẽ hình nền
        canvas.drawBitmap(backgroundImage,0,0,null);

//        Thiết lập biên độ di chuyển tối đa và tối thiểu của cá theo trục Y
        int minFishY = 50;
        fishY = fishY + fishSpeed;

        if(score < 30) {
            maxFishY = canavasHeight - fish[0].getHeight();
        }
        else if (30 <= score && score < 120) {
            maxFishY = canavasHeight - fish[2].getHeight();
        }
        else if(score >= 120) {
            maxFishY = canavasHeight - fish[4].getHeight();
        }

        if(fishY < minFishY) {

            fishY = minFishY;
        }

        if(fishY > maxFishY) {
            fishY = maxFishY;
        }
        fishSpeed += 2;
//        Làm nhân vật chuyển động khi chạm
        if(score < 30) {
            if(touch) {
                canvas.drawBitmap(fish[1],fishX, fishY, null);
                touch = false;
            }
            else {
                canvas.drawBitmap(fish[0],fishX,fishY,null);
            }
        }
        else if(30 <= score && score < 120) {
            if(touch) {
                canvas.drawBitmap(fish[3],fishX, fishY, null);
                touch = false;
            }
            else {
                canvas.drawBitmap(fish[2],fishX,fishY,null);
            }
        }
        else if(score >= 120) {
            if(touch) {
                canvas.drawBitmap(fish[5],fishX, fishY, null);
                touch = false;
            }
            else {
                canvas.drawBitmap(fish[4],fishX,fishY,null);
            }
        }

//  Tăng level của game
        if(30 <= score && score < 120) {
            smallFishSpeed = 10;
            mediumFishSpeed = 15;
            largeFishSpeed = 20;
        }
        else if(120 <= score && score < 200 ) {
            smallFishSpeed = 15;
            mediumFishSpeed = 20;
            largeFishSpeed = 25;
        }
        else if(score >= 200) {
            smallFishSpeed = 20;
            mediumFishSpeed = 25;
            largeFishSpeed = 30;
            veryLargeFish1Speed = 35;
            veryLargeFish2Speed = 40;
        }
        else if(score >= 300) {
            smallFishSpeed = 25;
            mediumFishSpeed = 30;
            largeFishSpeed = 35;
            veryLargeFish1Speed = 40;
            veryLargeFish2Speed = 45;
        }

//        Thiết lập vị trí cho cá nhỏ
        smallFishX = smallFishX - smallFishSpeed;
        if(smallFishX < 0) {
            smallFishX = canavasWidth + 21;
            smallFishY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
//        Cộng điểm nếu ăn cá nhỏ
        if(hitFishChecker(smallFishX, smallFishY)) {
            score += 10;
            smallFishX = -100;
        }
//        Vẽ cá nhỏ
        canvas.drawBitmap(smallFish, smallFishX, smallFishY,null);

//        Thiết lập vị trí cho cá vừa
        mediumFishX = mediumFishX - mediumFishSpeed;
        if(mediumFishX < 0) {
            mediumFishX = canavasWidth + 21;
            mediumFishY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
//        Cộng điểm nếu ăn cá vừa
        if(hitFishChecker(mediumFishX, mediumFishY)) {
            if(score < 30) {
                lifeCounter--;
                if(lifeCounter == 0){
                    gameOver();
                }
            }
            else
                score += 20;
            mediumFishX = -100;
        }
//        Vẽ cá vừa
        canvas.drawBitmap(mediumFish, mediumFishX, mediumFishY,null);

//         Thiết lập vị trí cho cá lớn
        largeFishX = largeFishX - largeFishSpeed;
        if(largeFishX < 0) {
            largeFishX = canavasWidth + 21;
            largeFishY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
//         Trừ mạng nếu ăn cá lớn
        if(hitFishChecker(largeFishX, largeFishY)) {
            if(score >= 120) {
                score += 25;
            }
            else {
                lifeCounter--;
                if(lifeCounter == 0) {
                    gameOver();
                }
            }
            largeFishX = -100;
        }
//        Vẽ cá lớn
        canvas.drawBitmap(largeFish, largeFishX, largeFishY,null);

        if(score > 120) {
//         Thiết lập vị trí cho cá siêu lớn 1
            veryLargeFish1X = veryLargeFish1X - veryLargeFish1Speed;
            if (veryLargeFish1X < 0) {
                veryLargeFish1X = canavasWidth + 21;
                veryLargeFish1Y = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
            }
//         Trừ mạng nếu ăn cá siêu lớn 1

            if (hitFishChecker(veryLargeFish1X, veryLargeFish1Y)) {
                lifeCounter--;

                if (lifeCounter == 0) {
                    gameOver();
                }
                veryLargeFish1X = -100;
            }
//        Vẽ cá siêu lớn 1
            canvas.drawBitmap(veryLargeFish1, veryLargeFish1X, veryLargeFish1Y, null);

//         Thiết lập vị trí cho cá siêu lớn 2
            veryLargeFish2X = veryLargeFish2X - veryLargeFish2Speed;
            if (veryLargeFish2X < 0) {
                veryLargeFish2X = canavasWidth + 21;
                veryLargeFish2Y = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
            }
//         Trừ mạng nếu ăn cá siêu lớn 2

            if (hitFishChecker(veryLargeFish2X, veryLargeFish2Y)) {
                lifeCounter--;

                if (lifeCounter == 0) {
                    gameOver();
                }
                veryLargeFish2X = -100;
            }
//        Vẽ cá siêu lớn 2
            canvas.drawBitmap(veryLargeFish2, veryLargeFish2X, veryLargeFish2Y, null);
        }
//        Vẽ điểm số
        canvas.drawText("Score: " + score,20,70,scorePaint);

//        Vẽ số mạng còn và mất
        for(int i=0; i<3; i++) {
            int x = (int) (1000 + life[0].getWidth() * 1.5 *i );
            int y =30;

            if(i < lifeCounter) {
                canvas.drawBitmap(life[0], x, y,null);
            }
            else {
                canvas.drawBitmap(life[1], x, y,null);
            }
        }
    }

    //      Phương thức kiểm tra có chạm vào các con cá khác hay không
    public boolean hitFishChecker(int x, int y) {
        if(score < 30) {
            if(fishX < x && x < (fishX + fish[0].getWidth()) &&
                    fishY < y && y < (fishY + fish[0].getHeight())) {
                return true;
            }
        }
        else if(30 <= score && score < 120) {
            if(fishX < x && x < (fishX + fish[2].getWidth()) &&
                    fishY < y && y < (fishY + fish[2].getHeight())) {
                return true;
            }
        }
        else if(score >= 120) {
            if (fishX < x && x < (fishX + fish[4].getWidth()) &&
                    fishY < y && y < (fishY + fish[4].getHeight())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;
            fishSpeed = -22;
        }
        return true;
    }

    public void gameOver() {
        Toast.makeText(getContext(),"Game Over", Toast.LENGTH_LONG).show();

//      Chuyển dữ liệu sang Chart1Activity
        Intent scoreIntent = new Intent(getContext(),PlayerInfoActivity.class);
        scoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        scoreIntent.putExtra("score", score);
        getContext().startActivity(scoreIntent);
    }

}
