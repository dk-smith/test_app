package dk.apps.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HelloActivity extends BackToGameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }
}