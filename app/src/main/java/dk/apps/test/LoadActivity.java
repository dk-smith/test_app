package dk.apps.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        SharedPreferences sP = this.getPreferences(Context.MODE_PRIVATE);
        byte activityIndex = (byte)sP.getInt("loadActivity", 0);

        Intent loadIntent;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Uri data = intent.getData();

        if ((activityIndex == 0 && data != null) || activityIndex == 2) {
            loadIntent = new Intent(this, WebActivity.class);
            activityIndex = 2;
        } else if ((extras != null && extras.getString("from").equals("/topics/testTopic"))
                || activityIndex == 3) {
            loadIntent = new Intent(this, HelloActivity.class);
            activityIndex = 3;
        } else {
            loadIntent = new Intent(this, GameActivity.class);
            activityIndex = 1;
        }

        SharedPreferences.Editor editor = sP.edit();
        editor.putInt("loadActivity", activityIndex);
        editor.apply();

        FirebaseMessaging.getInstance().subscribeToTopic("testTopic");
        startActivity(loadIntent);
        finish();
    }
}