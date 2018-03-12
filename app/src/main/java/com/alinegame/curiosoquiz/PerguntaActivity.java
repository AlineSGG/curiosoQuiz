package com.alinegame.curiosoquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alinegame.curiosoquiz.terefa.TimerCont;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PerguntaActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    private TextView txtPergunta;
    private int marcada = -1;
    private static int cont = 0, jogada = 0, ponto = 0, chance = 3, flag = 1;
    private ImageView vida1, vida2, vida3;
    private MediaPlayer ring;
    private Button btnAnswer;
    private AlertDialog alert = null;
    ProgressBar progress;
    LinearLayout linearDinamico;
    private List<Tipo> respostasQuestao1;

    private RadioButton rbmarcada;
    private int selectedId;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
//        radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        txtPergunta = (TextView) findViewById(R.id.txtPergunta);
        btnAnswer = (Button) findViewById(R.id.btnAnswer);
        vida1 = (ImageView) findViewById(R.id.vida1);
        vida2 = (ImageView) findViewById(R.id.vida2);
        vida3 = (ImageView) findViewById(R.id.vida3);
        progress = (ProgressBar) findViewById(R.id.progress);
        linearDinamico = (LinearLayout) findViewById(R.id.linearDinamico);

        new TimerCont(this, this, progress).execute();

        ring = MediaPlayer.create(PerguntaActivity.this,R.raw.beep_button);

        animateFab(btnAnswer);

        respostasQuestao1 = new ArrayList<>();

        Tipo tp = teste("Isso funciona 1?", "sim", "nao", "talvez", "acho que sim", "sim");
        respostasQuestao1.add(tp);

        Tipo tp1 = teste("Isso funciona 2?", "sim", "na", "talvez", "acho que sim", "na");
        respostasQuestao1.add(tp1);

        Tipo tp2 = teste("Isso funciona 3?", "sim", "nao", "talvez", "acho que sim", "nao");
        respostasQuestao1.add(tp2);

        Tipo tp3 = teste("Isso funciona 4?", "sim", "nao", "talvez", "acho que sim", "sim");
        respostasQuestao1.add(tp3);

        txtPergunta.setText(respostasQuestao1.get(jogada).getQuestao());
        radioButton1.setText(respostasQuestao1.get(jogada).getLetraa());
        radioButton2.setText(respostasQuestao1.get(jogada).getLetrab());
        radioButton3.setText(respostasQuestao1.get(jogada).getLetrac());
        radioButton4.setText(respostasQuestao1.get(jogada).getLetrad());

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ring.start();
                    if (get() != null) {
                        if (chance > 0) {
                            if (jogada < 4) {
                                Log.i("dado", respostasQuestao1.get(jogada).getCorreta());
                                if (get().equals(respostasQuestao1.get(jogada).getCorreta())) {
                                    ponto = ponto + 10;
                                    correct(view);

                                    radioGroup.clearCheck();
                                    jogada++;
                                    txtPergunta.setText(respostasQuestao1.get(jogada).getQuestao());
                                    radioButton1.setText(respostasQuestao1.get(jogada).getLetraa());
                                    radioButton2.setText(respostasQuestao1.get(jogada).getLetrab());
                                    radioButton3.setText(respostasQuestao1.get(jogada).getLetrac());
                                    radioButton4.setText(respostasQuestao1.get(jogada).getLetrad());

                                    //radioButton5.setText(respostasQuestao1.get(jogada).getLetrad());

                                } else {
                                    chance--;
                                    jogada++;
                                    ponto = ponto - 10;
                                    error(view);
                                    if (vida1.isShown()) {
                                        vida1.setVisibility(View.GONE);
                                    } else if (vida2.isShown()){
                                        vida2.setVisibility(View.GONE);
                                    } else if(vida3.isShown()) {
                                        vida3.setVisibility(View.GONE);
                                    }

                                    radioGroup.clearCheck();

                                    if (jogada < 4) {
                                        txtPergunta.setText(respostasQuestao1.get(jogada).getQuestao());
                                        radioButton1.setText(respostasQuestao1.get(jogada).getLetraa());
                                        radioButton2.setText(respostasQuestao1.get(jogada).getLetrab());
                                        radioButton3.setText(respostasQuestao1.get(jogada).getLetrac());
                                        radioButton4.setText(respostasQuestao1.get(jogada).getLetrad());
                                    }
                                }
                            }
                        } else {
                            View vieww = getLayoutInflater().inflate(R.layout.sem_vidas, null);

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setView(vieww);

                            alert = builder.create();

                            alert.show();
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            alert.dismiss();
                                            startActivity(new Intent(PerguntaActivity.this, TabGameActivity.class));
                                            finish();

                                        }
                                    });
                                }
                            }, 1000);


                        }

                    }
            }

        });
    }

    public  void error(final View view){

        View vieww = getLayoutInflater().inflate(R.layout.layout_error, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
        builder.setView(vieww);

        alert = builder.create();

        alert.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alert.dismiss();
                    }
                });
            }
        }, 1000);
    }

    public void correct(View view){
        View vieww = getLayoutInflater().inflate(R.layout.layout_sucess, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
        builder.setView(vieww);

        alert = builder.create();

        alert.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alert.dismiss();
                    }
                });
            }
        }, 1000);
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

    public String get() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        String selectedtext = (String) radioButton.getText();
        //Log.i("teste", selectedtext);
        return selectedtext;
    }

    public Tipo teste(String questao, String letraa, String letrab, String letrac, String letrad, String correta){
        Tipo tp = new Tipo();
        tp.setQuestao(questao);
        tp.setLetraa(letraa);
        tp.setLetrab(letrab);
        tp.setLetrac(letrac);
        tp.setLetrad(letrad);
        tp.setCorreta(correta);

        return tp;
    }
}