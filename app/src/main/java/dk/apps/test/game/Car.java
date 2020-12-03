package dk.apps.test.game;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import dk.apps.test.R;

public class Car extends Drawable {
    static public int[] res = {R.drawable.ambulance, R.drawable.audi, R.drawable.car,
            R.drawable.mini_truck, R.drawable.mini_van, R.drawable.police, R.drawable.taxi, R.drawable.truck};

    private float speed = 10;

    public Car(Context context, Vector2 canvas, int third) {
        Random rnd = new Random();
        this.third = third;
        sprite = BitmapFactory.decodeResource(context.getResources(), res[rnd.nextInt(Car.res.length)]);
        position = new Vector2(((canvas.X/3)*third - canvas.X/6) - sprite.getWidth()/2, -1*sprite.getHeight());
    }

    public void update(float diff) {
        position.Y+=speed*diff;
    }

    static void updateCars(ArrayList<Car> cars, Vector2 canvasSize, float diff) {
        Iterator<Car> i = cars.iterator();
        while (i.hasNext())
        {
            Car car = i.next();
            car.update(diff);
            if (car.position.Y > canvasSize.Y) {
                i.remove();
            }
        }
    }

    static void drawCars(ArrayList<Car> cars, Canvas canvas) {
        Iterator<Car> i = cars.iterator();
        while (i.hasNext())
        {
            Car car = i.next();
            car.draw(canvas);
        }
    }

    static void spawnCars(Context context, ArrayList<Car> cars, Vector2 canvasSize) {
        Random rnd = new Random();
        boolean[] mask = {false, false, false};
        for (int i = 0; i < 2; i++) {
            mask[rnd.nextInt(3)] = true;
        }
        for (int i = 0; i < mask.length ; i++) {
            if (mask[i]) cars.add(new Car(context, canvasSize, i+1));
        }
    }

}
