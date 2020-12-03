package dk.apps.test.game;

public class Vector2 {
    public int X;
    public int Y;

    public Vector2() {
        X = 0;
        Y = 0;
    }

    public Vector2(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public void setXY(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
