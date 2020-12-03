package dk.apps.test.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private Boolean running;
    private Canvas canvas;
    private boolean init = true;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    public MainThread(MainThread thread) {
        surfaceHolder = thread.surfaceHolder;
        gameView = thread.gameView;
        canvas = thread.canvas;
        init = thread.init;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                if (init) {
                    gameView.setCanvasSize(canvas);
                    gameView.init();
                    init = false;
                }
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {}
            finally {
                if (canvas != null) {
                    try { surfaceHolder.unlockCanvasAndPost(canvas); }
                    catch (Exception e) { e.printStackTrace(); }
                }
            }
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
