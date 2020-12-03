package dk.apps.test.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread mainThread;
    private Player player;
    private ArrayList<Car> cars = new ArrayList<>();
    private Vector2 canvasSize;
    private boolean gameOver = false;
    private float diff = 1;
    private int spacing = 300;
    private Float score = 0f;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        mainThread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mainThread = new MainThread(mainThread);
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void setCanvasSize(Canvas canvas) {
        canvasSize = new Vector2(canvas.getWidth(), canvas.getHeight());
    }

    public void init() {
        score = 0f;
        diff = 1;
        gameOver = false;
        cars.clear();
        player = new Player(this.getContext(), canvasSize);
        Car.spawnCars(getContext(), cars, canvasSize);
    }

    public void update() {
        if (gameOver) return;
        diff += 0.001;
        score += 0.2f;
            Car.updateCars(cars, canvasSize, diff);
        if (cars.get(cars.size()-1).position.Y > player.sprite.getHeight()+spacing)
            Car.spawnCars(getContext(), cars, canvasSize);
        checkCollision();
    }

    public void checkCollision() {
        Iterator<Car> i = cars.iterator();
        while (i.hasNext()) {
            Car car = i.next();
            if (player.third == car.third && player.position.Y <= car.position.Y+car.sprite.getHeight()
                && player.position.Y+player.sprite.getHeight() >= car.position.Y + 24) {
                player.lives--;
                if (player.lives > 0) i.remove();
                else gameOver = true;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        Car.drawCars(cars, canvas);
        player.draw(canvas);
        paint.setColor(Color.WHITE);
        if (gameOver) {
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(96);
            canvas.drawText("GAME OVER", canvasSize.X/2, canvasSize.Y/2, paint);
            canvas.drawText("Score: "+score.intValue(), canvasSize.X/2, canvasSize.Y/2+100, paint);
            paint.setTextSize(64);
            canvas.drawText("Tap to restart", canvasSize.X/2, canvasSize.Y/2+200, paint);
        } else {
            paint.setTextSize(64);
            canvas.drawText("Score: "+ score.intValue(), (player.live.getWidth()+20)*3, canvasSize.Y - 10, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!gameOver) player.move(event.getX(), canvasSize);
        else init();
        return super.onTouchEvent(event);
    }



}
