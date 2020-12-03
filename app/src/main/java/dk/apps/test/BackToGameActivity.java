package dk.apps.test;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class BackToGameActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, GameActivity.class));
    }
}
