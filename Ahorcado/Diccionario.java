package com.example.daleshkabecerra.ahorcado;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Diccionario extends AppCompatActivity {
    SQLiteDatabase db;
    final Diccionario m=this;
    private String msgs[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diccionario);
        db=openOrCreateDatabase("RepDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS rep(message VARCHAR);");
        msgs = new String[10];
        //MENSAJES PARA ADIVINAR
        msgs[0] = "Supernatural".toUpperCase();
        msgs[1] = "The walking dead".toUpperCase();
        msgs[2] = "El Angel Castiel".toUpperCase();
        msgs[3] = "Los shinigamis comen manzanas".toUpperCase();
        msgs[4] = "La vida es bella".toUpperCase();
        msgs[5] = "Indiana Jones y la ultima cruzada".toUpperCase();
        msgs[6] = "Juego de Tronos".toUpperCase();
        msgs[7] = "Los programadores nunca mueren tan solo se pierden en el Proceso".toUpperCase();
        msgs[8] = "La máscara del zorro".toUpperCase();
        msgs[9] = "Cuidense de los programadores que lleven destornilladores".toUpperCase();
        for(int i=0;i<msgs.length;i++){
            db.execSQL("INSERT INTO student VALUES('"+msgs[i]+"');");
        }

        db=openOrCreateDatabase("RepDB", Context.MODE_PRIVATE, null);
        Cursor c=db.rawQuery("SELECT * FROM rep ", null);
        if(c.getCount()==0) {
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append(c.getString(0)+"\n");
            buffer.append("---------------------"+"\n");

        }
        String todo=buffer.toString();
        TextView d=(TextView) findViewById(R.id.list);
        d.setText(todo);

        Button add = (Button) findViewById(R.id.butAdd);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                añade();


            }});

        Button regre=(Button)findViewById(R.id.butReg);
        regre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent=new Intent(m,MenuActivity.class);
                startActivity(intent);
            }

        });
    }
    private void añade(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("NUEVA FRASE!");
        alert.setMessage("Ingrese Frase:");


        // Create EditText for entry
        final EditText input = new EditText(this);

        alert.setView(input);

        // Make an "OK" button to save the name
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {

                // Grab the EditText's input
                String frase = input.getText().toString().toUpperCase();
                // Put it into memory (don't forget to commit!)
                db.execSQL("INSERT INTO student VALUES('"+frase+"');");
            }
        });

        // Make a "Cancel" button
        // that simply dismisses the alert
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {}
        });

        alert.show();
    }
}
