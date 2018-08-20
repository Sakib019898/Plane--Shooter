package com.noobgamers.planeshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Bullet {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 1;

    private int maxX;
    private int minX;

    private int maxY;
    private int minY;

    private Rect detectCollision;
    private Player player;

    public Bullet(Context context, int screenX, int screenY,int u,int v) {

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.gulli);
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;
        speed = 15;
        player = new Player(context, screenX, screenY);
        x = u+300;
        y = v+100;

        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

    }
    public void update(int u,int v,int speed){
        x += speed;
        if (x > maxX - bitmap.getWidth()) {
            x = u+300;
            y = v+100;
        }


        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
    public Rect getDetectCollision() {
        return detectCollision;
    }
}
