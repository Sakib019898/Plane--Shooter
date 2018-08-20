package com.noobgamers.planeshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;


public class NegPower {
    private Bitmap bitmap;
    private int x;
    private int y;

    private Rect detectCollision;

    public NegPower(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.negpow);
        x = 1800;
        y = 50;
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

    }

    public void update() {
        x -= 20;
        y += 9;
        if (x < -200) {
            x = 1800;
            y = 50;
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
