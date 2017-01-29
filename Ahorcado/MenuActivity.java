package com.example.daleshkabecerra.ahorcado;

import
        android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {
    final MenuActivity m=this;
    final MenuActivity mu=this;
    String op="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        Button jugar=(Button) findViewById(R.id.button4);
        final EditText name=(EditText)findViewById(R.id.editName);


        jugar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(m,MainActivity.class);
                    intent.putExtra("nombre",name.getText().toString());
                    Intent intent2=getIntent();


                    if(intent2.getBooleanExtra("op",true)){
                        op =intent2.getStringExtra("op");
                    }


                    intent.putExtra("op",op);
                    startActivity(intent);
                }

        });

        Button music=(Button) findViewById(R.id.button6);


        music.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mu,MusicActivity.class);
                startActivity(intent);
            }

        });


        Button ranking=(Button) findViewById(R.id.button9);


        ranking.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(m,RankingActivity.class);
                startActivity(intent);
            }

        });

        Button repertorio=(Button) findViewById(R.id.button11);

        repertorio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(m,Diccionario.class);
                startActivity(intent);
            }

        });

    }



}
