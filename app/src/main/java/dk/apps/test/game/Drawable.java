package dk.apps.test.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Drawable {

    protected Bitmap sprite;
    public Vector2 position;
    public int third;

    public Drawable() {
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite, position.X, position.Y, null);
    }

    public int getCenterX() {
        return position.X + sprite.getWidth()/2;
    }

}
