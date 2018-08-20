package com.noobgamers.planeshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.util.Random;

public class Power {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int maxX;
    private int maxY;
    private Rect detectCollision;

    public Power(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb);
        maxX = screenX;
        maxY = screenY;

        Random generator = new Random();
        x = maxX+230;
        y = generator.nextInt(maxY) - bitmap.getHeight();
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

    }

    public void update() {

        x -= 20;
        if (x < -180) {
            Random generator = new Random();
            x = maxX+230;
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


