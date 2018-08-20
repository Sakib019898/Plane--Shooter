package com.noobgamers.planeshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Life {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 1;

    private int maxX;
    private int minX;

    private int maxY;
    private int minY;
    private Rect detectCollision;

    public Life(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.life);
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX+250;
        y = generator.nextInt(maxY) - bitmap.getHeight();
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update() {
        System.out.println("x:"+x+"    y:"+y);
        x -= 20;
        if (x < -180) {
            Random generator = new Random();
            x = maxX+250;
            y = generator.nextInt(maxY) - bitmap.getHeight();
        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int bitX(){
        return bitmap.getWidth();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
