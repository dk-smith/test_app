package dk.apps.test.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import dk.apps.test.R;

public class Player extends Drawable {
    private int res = R.drawable.black_viper;
    private int liveRes = R.drawable.heart_pixel_art_64x64;
    public Bitmap live;
    public int lives = 3;
    private Vector2 livesPos;


    public Player(Context context, Vector2 canvasSize) {
        super();
        sprite = BitmapFactory.decodeResource(context.getResources(), res);
        live = Bitmap.createScaledBitmap
                (BitmapFactory.decodeResource(context.getResources(), liveRes), 64, 64, false);
        third = 2;
        position = new Vector2(((canvasSize.X/3)*third - canvasSize.X/6) - sprite.getWidth()/2,
                canvasSize.Y - sprite.getHeight() - 300);
        livesPos = new Vector2(10, canvasSize.Y - live.getHeight() - 10);
    }

    public void move(float touchX, Vector2 canvasSize) {
        if (touchX < getCenterX() && third > 1)
            third--;
        else if (touchX > getCenterX() && third < 3)
            third++;
        position.X = ((canvasSize.X/3)*third - canvasSize.X/6) - sprite.getWidth()/2;
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < lives ; i++) {
            canvas.drawBitmap(live, livesPos.X+(live.getWidth()+10)*i, livesPos.Y, null);
        }
        super.draw(canvas);
    }
}
