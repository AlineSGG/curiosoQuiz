package com.alinegame.curiosoquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.io.IOException;

public class SplashScreenActivity extends AppCompatActivity {

    public static MediaPlayer ring;
    private ImageView idImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        idImg = (ImageView) findViewById(R.id.idImg);

        ring = MediaPlayer.create(this,R.raw.menu);
        ring.setLooping(true);
        try {
            ring.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ring.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e) {

                }
                startActivity(new Intent(SplashScreenActivity.this, MenuActivity.class));
                finish();
            }
        }).start();

        Animation deslocamento = new TranslateAnimation(0, 0, 1000, 0);
        deslocamento.setDuration(3000);
        idImg.startAnimation(deslocamento);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ring.pause();
    }
}
