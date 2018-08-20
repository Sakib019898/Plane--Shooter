package com.noobgamers.planeshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.util.Random;

public class BombBlast {
    private Bitmap bitmap;
    private int x;
    private int y;
    private Rect detectCollision;

    public BombBlast(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.powerbomb);
        x = 3000;
        y = 110;
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

    }
    public void update(int u,int v){
        x=u;
        y=v;
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Rect getDetectCollision() {
        return detectCollision;
    }

}
