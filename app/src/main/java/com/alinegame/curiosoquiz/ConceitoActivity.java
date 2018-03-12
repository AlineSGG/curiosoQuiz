package com.alinegame.curiosoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ConceitoActivity extends AppCompatActivity {

    private ImageView imgPerson1, imgPerson2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conceito);

        imgPerson1 = (ImageView) findViewById(R.id.imgPerson1);
        imgPerson2 = (ImageView) findViewById(R.id.imgPerson2);

        imgPerson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConceitoActivity.this, InicioTramaActivity.class));
                finish();
            }
        });

        imgPerson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
