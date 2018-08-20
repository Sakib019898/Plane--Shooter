package com.noobgamers.planeshooter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Color;
import android.widget.TextView;


public class GameView extends SurfaceView implements Runnable {
    public static final int WIDTH = 2048;
    public static final int HEIGHT = 1823;
    public static int p1=0;
    private Background bg;
    volatile boolean playing;
    private Thread gameThread = null;
    int bomb_flag=0;
    private Player player;
    int bullet_speed;
    private NegPower negPower;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Enemy[] enemies;
    int bombBlast_flag=0;
    int negpower_flag=0;
    private Bomb bomb;
    private Blast blast;
    private BombBlast bombBlast;
    private Power power;
    int screenX;
    int screenY;
    Context context;
    private Bitmap bitmap;
    int playerX;
    int playerY;
    int kit=0;
    int playerlife;
    int life_flag=0;
    int power_flag=0;
    boolean flag ;
    int enemy_speed;
    private boolean isGameOver ;
    private GameOverPic overPic;
    int score;
    private GameLife gamelife;
    int highScore[] = new int[4];

    SharedPreferences sharedPreferences;
    private Bullet[] bullet=new Bullet[5];
    private int enemyCount = 2;
    private Life life;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX = screenX;
        bullet_speed=15;
        isGameOver = false;
        player = new Player(context, screenX, screenY);
        playerX=player.getX();
        playerY=player.getY();

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.gameback));
        bg.setVector(-8);
        enemy_speed=13;
        overPic=new GameOverPic(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        enemies = new Enemy[enemyCount];
        for(int i=0; i<enemyCount; i++){
            enemies[i] = new Enemy(context, screenX, screenY);
        }
        blast = new Blast(context);
        bombBlast=new BombBlast(context);
        power = new Power(context, screenX, screenY);
        negPower=new NegPower(context);
        life=new Life(context, screenX, screenY);
        bomb= new Bomb(context);
        this.screenX = screenX;
        this.screenY=screenY;
        this.context=context;

        playerlife=5;
        isGameOver = false;
         score = 0;
         gamelife=new GameLife(context);
        bullet[0]=new Bullet(context,screenX,screenY,playerX,playerY);
        sharedPreferences = context.getSharedPreferences("SHAR_PREF_NAME",Context.MODE_PRIVATE);

        //initializing the array high scores with the previous values
        highScore[0] = sharedPreferences.getInt("score1",0);
        highScore[1] = sharedPreferences.getInt("score2",0);
        highScore[2] = sharedPreferences.getInt("score3",0);
        highScore[3] = sharedPreferences.getInt("score4",0);

    }
    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }
    private void update() {
          p1++;
         gamelife.update(context,playerlife);
         if(bombBlast_flag==0) {
             bombBlast.update(3000,110);
         }
          else{
             bombBlast.update(520,110);
         }
          if(p1==kit){
              bombBlast_flag=0;
          }
          blast.setX(-300);
          blast.setY(-300);
          player.update();
          playerX=player.getX();
          playerY=player.getY();

          if(negpower_flag==1) {
              negPower.update();
          }
          if(negPower.getX()<-150){
              negPower.setX(screenX+250);
              negpower_flag=0;
          }
          if(life_flag==1){
              life.update();
          }
          if(life.getX()<-150){
              life.setX(screenX+250);
              life_flag=0;
          }
          if(bomb_flag==1){
              bomb.update();
          }
          if(bomb.getX()<-150){
              bomb.setX(1800);
              bomb.setY(1000);
              bomb_flag=0;
          }
          if(power_flag==1){
              power.update();
          }
          if(power.getX()<-145){
              power.setX(screenX+230);
              power_flag=0;
          }
          bullet[0].update(playerX, playerY,bullet_speed);
          if(p1>=50){
            bullet[1].update(playerX, playerY,bullet_speed);
          }
          bg.update();
          if(bomb_flag==1) {
              if (Rect.intersects(player.getDetectCollision(), bomb.getDetectCollision())) {
                  bomb.setX(1800);
                  bomb.setY(1000);
                  bomb_flag = 0;
                  bullet_speed += 10;
                  System.out.println(bullet_speed);

              }
          }
          if(negpower_flag==1) {
              if (Rect.intersects(player.getDetectCollision(), negPower.getDetectCollision())) {
                  negPower.setX(1800);
                  negPower.setY(50);
                  negpower_flag = 0;
                  enemy_speed += 3;
                  System.out.println(enemy_speed);

              }
          }


              for(int i=0;i<enemyCount;i++) {
              if (Rect.intersects(bombBlast.getDetectCollision(), enemies[i].getDetectCollision())) {
                  blast.setX(enemies[i].getX());
                  blast.setY(enemies[i].getY());
                  enemies[i].setX(-300);

              }
          }
          if(life_flag==1) {
              if (Rect.intersects(player.getDetectCollision(), life.getDetectCollision())) {
                  life.setX(screenX+250);
                  if(playerlife<5) {
                      playerlife++;
                  }
                  score++;
                  life_flag = 0;
              }
          }
          if(power_flag==1) {
              if (Rect.intersects(player.getDetectCollision(), power.getDetectCollision())) {
                  power.setX(screenX+250);
                  bombBlast.setX(520);
                  bombBlast.setY(110);
                  kit=p1+20;
                  bombBlast_flag=1;
                  power_flag = 0;
              }
          }
          for (int i = 0; i < enemyCount; i++) {

              if (enemies[i].getX() == screenX) {
                  flag = true;
              }
              enemies[i].update(enemy_speed);

              if (Rect.intersects(player.getDetectCollision(), enemies[i].getDetectCollision())) {

                  blast.setX(enemies[i].getX());
                  blast.setY(enemies[i].getY());
                  playerlife--;
                  if(playerlife==0){
                      playing = false;
                      isGameOver = true;
                      for(int k=0;k<4;k++){
                          if(highScore[k]<score){

                              final int finalI = k;
                              highScore[k] = score;
                              break;
                          }
                      }

                      SharedPreferences.Editor e = sharedPreferences.edit();
                      for(int k=0;k<4;k++){
                          int j = k+1;
                          e.putInt("score"+j,highScore[k]);

                      }
                      e.apply();
                      for(int k=0;k<4;k++){
                          System.out.println("k: "+highScore[k]);

                      }

                  }

                  enemies[i].setX(-300);
              }
              if (Rect.intersects(bullet[0].getDetectCollision(), enemies[i].getDetectCollision())) {

                  blast.setX(enemies[i].getX());
                  blast.setY(enemies[i].getY());
                  score++;

                  if(score%8==0){
                      life_flag=1;
                  }
                  if(score%7==0){
                      power_flag=1;
                  }

                  if(score%6==0){
                      bomb_flag=1;
                  }

                  if(score%4==0){
                      negpower_flag=1;
                  }
                  enemies[i].setX(-300);
              }

              if (p1>=50 && Rect.intersects(bullet[1].getDetectCollision(), enemies[i].getDetectCollision())) {
                  blast.setX(enemies[i].getX());
                  blast.setY(enemies[i].getY());
                  score++;
                  if(score%8==0){
                      life_flag=1;
                  }
                  if(score%7==0){
                      power_flag=1;
                  }

                  if(score%6==0){
                      bomb_flag=1;
                  }

                  if(score%4==0){
                      negpower_flag=1;
                  }
                  enemies[i].setX(-300);
              }

          }

      }

    private void draw() {
        final float scaleFactorX = getWidth()/(float)WIDTH;
        final float scaleFactorY = getHeight()/(float)HEIGHT;

        if(p1==49){
           bullet[1]=new Bullet(context,screenX,screenY,playerX,playerY);
        }
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);

            paint.setTextSize(125);
            canvas.drawText("Score:" + score, 1300, 100, paint);

            canvas.restoreToCount(savedState);
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);
            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }
            canvas.drawBitmap(
                    blast.getBitmap(),
                    blast.getX(),
                    blast.getY(),
                    paint
            );
            canvas.drawBitmap(
                    bomb.getBitmap(),
                    bomb.getX(),
                    bomb.getY(),
                    paint
            );
            canvas.drawBitmap(
                    power.getBitmap(),
                    power.getX(),
                    power.getY(),
                    paint
            );
            canvas.drawBitmap(
                    bullet[0].getBitmap(),
                    bullet[0].getX(),
                    bullet[0].getY(),
                    paint
            );
            if(p1>=50){
                canvas.drawBitmap(
                        bullet[1].getBitmap(),
                        bullet[1].getX(),
                        bullet[1].getY(),
                        paint
                );
            }
                canvas.drawBitmap(
                        life.getBitmap(),
                        life.getX(),
                        life.getY(),
                        paint
                );
            canvas.drawBitmap(
                    power.getBitmap(),
                    power.getX(),
                    power.getY(),
                    paint
            );
            canvas.drawBitmap(
                    bombBlast.getBitmap(),
                    bombBlast.getX(),
                    bombBlast.getY(),
                    paint
            );
            canvas.drawBitmap(
                    negPower.getBitmap(),
                    negPower.getX(),
                    negPower.getY(),
                    paint
            );
            canvas.drawBitmap(
                    gamelife.getBitmap(),
                    gamelife.getX(),
                    gamelife.getY(),
                    paint
            );
            //draw game Over when the game is over
            if(isGameOver){

                canvas.drawBitmap(
                        overPic.getBitmap(),
                        overPic.getX(),
                        overPic.getY(),
                        paint
                );
            }

         surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
     }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:

                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:

                player.setBoosting();
                break;
        }
        return true;
    }

}