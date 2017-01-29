package com.example.daleshkabecerra.ahorcado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RankingActivity extends AppCompatActivity {
    SQLiteDatabase db;
    final RankingActivity m=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        Cursor c=db.rawQuery("SELECT * FROM student ORDER BY puntos DESC", null);
        if(c.getCount()==0)
        {
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("Name: "+c.getString(0)+"\n");
            buffer.append("Puntaje: "+c.getString(1)+"\n");
            buffer.append("---------------------"+"\n");

        }
        String todo=buffer.toString();
        TextView d=(TextView) findViewById(R.id.textran);
        d.setText(todo);

        Button regre=(Button)findViewById(R.id.button10);
        regre.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent=new Intent(m,MenuActivity.class);
                startActivity(intent);
            }

        });
    }
}
