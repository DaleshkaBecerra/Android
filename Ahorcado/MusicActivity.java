package com.example.daleshkabecerra.ahorcado;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MusicActivity extends AppCompatActivity {
    MediaPlayer mp;
    String op="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mp = MediaPlayer.create(this, R.raw.levan);
        mp.start();
        mp.reset();
        final MusicActivity mu=this;
        Button reg=(Button) findViewById(R.id.button5);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent=new Intent(mu,MenuActivity.class);
                intent.putExtra("op",op);
                startActivity(intent);
            }
        });

        Button m1=(Button) findViewById(R.id.button6);
        Button m2=(Button) findViewById(R.id.button7);
       Button m3=(Button) findViewById(R.id.button8);
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m11();
            }
        });



        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m12();
            }
        });


        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m13();
            }
        });
    }



    private void m11(){
        if(mp.isPlaying()){
            mp.stop();
        }
        mp = MediaPlayer.create(this, R.raw.levan);
        op="1";
        mp.start();

    }

    private void m12(){

        if(mp.isPlaying()){
            mp.stop();
        }
        mp = MediaPlayer.create(this, R.raw.zakura);
        op="2";
        mp.start();
    }

    private void m13(){

        if(mp.isPlaying()){
            mp.stop();
        }
        mp = MediaPlayer.create(this, R.raw.weekend);
        op="3";
        mp.start();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }
}
