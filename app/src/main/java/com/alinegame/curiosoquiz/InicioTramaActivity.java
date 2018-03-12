package com.alinegame.curiosoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class InicioTramaActivity extends AppCompatActivity {

    private ImageView idImagemDialogInterativa, idImagemInterativaP, idImagemInterativaA, idImagemInterativaF, idImagemInterativaCiclo, imgv1, imgv2, imgv3, imgv4;
    private FrameLayout showSimNao;
    private LinearLayout showVetor;
    private Button btnNao, btnSim;
    private TextView idDialogo;
    private static int cont = 0;
    private String[] dialogList = {"Que bom que você decidiu jogar esse jogo porque afinal ele trata de um assunto sério.",
                                   "Agora me responda, você já ouviu falar sobre a Leishmaniose Tegumentar Americana (LTA)?",
                                    "Esse jogo tem o próposito de te deixar esperto sobre a LTA, assim como te prevenir desse mal.",
                                    "Então vamos começar!",
                                    "A doença é passada através da picada do mosquito-palha ou flebotomíneos quando infectados pelo protozoário Leishmania. A infecção acontece quando estes insetos picam primeiro animais silvestres ou cães que possuem o parasita.",
                                    "O causador da LTA é o parasita do gênero Leishmania. Os cientistas denominaram como formas Promastigotas.",
                                    "Essas formas vivem em organismos de insetos chamados flebotomíneos que é o vetor da LTA.",
                                    "E existe também o parasita do gênero Leishmania em formas amastigotas que parasitam organismos humanos. A forma é mostrada na imagem",
                                    "Só mais um pouco...",
                                    "A animação mostra como surge a doença preste atenção...",
                                    "O mosquito palha fémea, da familia dos flebotomíneos, é o vetor transmissor do protozoário Leishmania chagasi para os hospedeiros, dentre eles destacam-se os cães e o homem. Através da picada, são inoculados parasitas na forma Promastigota os quais invadem os macrófagos.",
                                    "No ambiente intracelular, o parasita assume a forma amastigota (sem flagelo) e inicia uma replicação binária (assexuada). Quando as células rompem, são liberados promastigotas os quais podem jnfectar novos macrófagos ou inocular um mosquito palha, fechando o ciclo. ",
                                    "No interior do mosquito palha, o Leishmania também realiza reprodução assexuada, alternando entre amastigota e promastigota.",
                                    "Agora eu irei fazer um teste com você. Vamos jogar!",
                                    "Escolha qual dentre os vetores é o responsável pela doença LTA."
                                    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_trama);

        idDialogo = (TextView) findViewById(R.id.idDialog);
        showSimNao = (FrameLayout) findViewById(R.id.showSimNao);
        btnNao = (Button) findViewById(R.id.btnNao);
        btnSim = (Button) findViewById(R.id.btnSim);
        idImagemInterativaP = (ImageView) findViewById(R.id.idImagemInterativaP);
        idImagemInterativaA = (ImageView) findViewById(R.id.idImagemInterativaA);
        idImagemInterativaF = (ImageView) findViewById(R.id.idImagemInterativaF);
        idImagemInterativaCiclo = (ImageView) findViewById(R.id.idImagemInterativaCiclo);
        showVetor = (LinearLayout) findViewById(R.id.showVetor);
        imgv1 = (ImageView) findViewById(R.id.img1);
        imgv2 = (ImageView) findViewById(R.id.img2);
        imgv3 = (ImageView) findViewById(R.id.img3);
        imgv4 = (ImageView) findViewById(R.id.img4);

        idDialogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String conversa = getDialog(cont);
                    idDialogo.setText(conversa);

                    if (cont == 1) {
                        showSimNao.setVisibility(View.VISIBLE);
                        idDialogo.setClickable(false);
                        btnSim.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showSimNao.setVisibility(View.GONE);
                                idDialogo.setClickable(true);
                                idDialogo.setText("Você já me surpreendeu em responder sim.");
                            }
                        });

                        btnNao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showSimNao.setVisibility(View.GONE);
                                idDialogo.setClickable(true);
                                idDialogo.setText("Eu esperava que você não conhecesse essa doença...Mas não se preocupe, irei te ajudar a saber sobre a LTA.");
                            }
                        });
                    } else

                    if (cont == 4) {
                        idImagemInterativaF.setVisibility(View.VISIBLE);
                    } else if (cont == 5){
                        idImagemInterativaF.setVisibility(View.GONE);
                        idImagemInterativaP.setVisibility(View.VISIBLE);
                    } else if(cont == 7){
                        idImagemInterativaP.setVisibility(View.GONE);
                        idImagemInterativaA.setVisibility(View.VISIBLE);
                    } else if (cont == 8) {
                        idImagemInterativaA.setVisibility(View.GONE);
                        idImagemInterativaCiclo.setVisibility(View.VISIBLE);
                    } else if (cont == 13) {
                        idImagemInterativaCiclo.setVisibility(View.GONE);
                    } else if (cont == 14){

                        showVetor.setVisibility(View.VISIBLE);

                        imgv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                        imgv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                        imgv3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                        imgv4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                    cont++;
                } catch (Exception e) {

                }
            }
        });
    }

    public String getDialog(int tamanho){
        for (int i = 0; i <= dialogList.length; i++){
            if (i == tamanho) {
                return dialogList[i];
            }
        }
        return null;
    }
}
