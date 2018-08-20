package com.noobgamers.planeshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class GameOverPic {
    private Bitmap bitmap;

    private int x;
    private int y;

    public GameOverPic(Context context) {
        bitmap = BitmapFactory.decodeResource
                (context.getResources(), R.drawable.gameover);

        x = 0;
        y = 0;
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

}
