package com.alinegame.curiosoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingActivity.this, PerguntaActivity.class));
                finish();
            }
        }, 3500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SplashScreenActivity.ring.stop();
    }
}