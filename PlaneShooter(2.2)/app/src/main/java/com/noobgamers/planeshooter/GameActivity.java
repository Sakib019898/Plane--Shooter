package com.noobgamers.planeshooter;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class
GameActivity extends AppCompatActivity {
    private ImageButton bulletplay;

    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();


        Point size = new Point();
        display.getSize(size);

        gameView = new GameView(this, size.x, size.y);

        setContentView(gameView);
    }
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

}