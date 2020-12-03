package dk.apps.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import dk.apps.test.game.GameView;

public class GameActivity extends Activity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    boolean singleBack = false;

    @Override
    public void onBackPressed() {
        Log.d("Test", "Back pressed");
        super.onBackPressed();
//        super.onBackPressed();
//        try {
//            gameView.stop();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        GameActivity.this.finish();
//        Log.d("Test", "Exiting");
//        System.exit(0);
//        Log.d("Test", "Exit");
//        if (singleBack) {
//            finish();
//            System.exit(0);
//            super.onBackPressed();
//            return;
//        }
//
//        this.singleBack = true;
//        Toast.makeText(this, "Double Back to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                singleBack=false;
//            }
//        }, 2000);
    }
}