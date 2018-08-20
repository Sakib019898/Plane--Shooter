package com.noobgamers.planeshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameLife {

    private Bitmap bitmap;

    private int x;
    private int y;

    public GameLife(Context context) {
        bitmap = BitmapFactory.decodeResource
                (context.getResources(), R.drawable.lifebar);

        x = 50;
        y = 25;
    }
    void update(Context context,int gameBar){
        if(gameBar==1){
            bitmap = BitmapFactory.decodeResource
                    (context.getResources(), R.drawable.life1);

        }
        else if(gameBar==2){
            bitmap = BitmapFactory.decodeResource
                    (context.getResources(), R.drawable.life2);

        }
        else if(gameBar==3){
            bitmap = BitmapFactory.decodeResource
                    (context.getResources(), R.drawable.life3);

        }
        else if(gameBar==4){
            bitmap = BitmapFactory.decodeResource
                    (context.getResources(), R.drawable.life4);

        }
        else {
            bitmap = BitmapFactory.decodeResource
                    (context.getResources(), R.drawable.life5);
        }

    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

}
