package com.example.mysmall;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import net.wequick.small.Small;

import java.util.logging.Handler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LaunchActivity extends Activity {
    private View mContentView;
    private android.os.Handler handler = new android.os.Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        mContentView = findViewById(R.id.fullscreen_content);


        // Remove the status and navigation bar
        if (Build.VERSION.SDK_INT < 14) return;
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        if (Small.getIsNewHostApp()) {
            TextView tvPrepare = (TextView) findViewById(R.id.prepare_text);
            tvPrepare.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = this.getSharedPreferences("profile", 0);
        final SharedPreferences.Editor se = sp.edit();
        se.putLong("setUpStart", System.nanoTime());

        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                se.putLong("setUpFinish", System.nanoTime()).apply();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Small.openUri("main", LaunchActivity.this);
                       finish();
                   }
               }, 3000);
            }
        });
    }
}
