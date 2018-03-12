package com.alinegame.curiosoquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button btnNew, btnContinue;
    private MediaPlayer ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnNew = (Button) findViewById(R.id.btnNew);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        ring = MediaPlayer.create(MenuActivity.this,R.raw.beep_button);

        animateFab(btnNew);
        animateFab(btnContinue);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ring.start();

                    View vieww = getLayoutInflater().inflate(R.layout.new_game_option, null);

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    builder.setView(vieww);

                    builder.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    final AlertDialog alert = builder.create();

                    Button btnEay = (Button) vieww.findViewById(R.id.btnEasy);
                    Button btnHard = (Button) vieww.findViewById(R.id.btnHard);

                    animateFab(btnEay);
                    animateFab(btnHard);

                    btnEay.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            ring.start();
                            alert.dismiss();
                            startActivity(new Intent(MenuActivity.this, ConceitoActivity.class));
                            finish();
                        }
                    });

                    btnHard.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            ring.start();
                            alert.dismiss();
                            startActivity(new Intent(MenuActivity.this, LoadingActivity.class));
                            finish();
                        }
                    });

                    alert.show();
                }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ring.start();
            }
        });
    }

    protected void animateFab(final Button btn) {
        btn.clearAnimation();
        // Scale down animation
        ScaleAnimation shrink =  new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(150);     // animation duration in milliseconds
        shrink.setInterpolator(new DecelerateInterpolator());
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                // Scale up animation
                ScaleAnimation expand =  new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                expand.setDuration(100);     // animation duration in milliseconds
                expand.setInterpolator(new AccelerateInterpolator());
                btn.startAnimation(expand);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btn.startAnimation(shrink);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
