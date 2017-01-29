package com.example.daleshkabecerra.ahorcado;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.media.MediaPlayer;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.media.MediaPlayer;
import android.os.Handler;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import android.widget.ImageView;
import android.content.Context;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static java.lang.Character.*;


public class MainActivity extends AppCompatActivity {
    String adivinanza;
    int cantPista = 3;
    private String msgs[];
    TextView mostrar;
    TextView n;
    TextView p;
    private String res[];
    int ran = 0;
    int errors = 0;
    String name = "Anonimo";
    int puntaje = 0;
    SQLiteDatabase db;
    SQLiteDatabase fra;
    MediaPlayer mps;
    String op = "1";
    final MainActivity m = this;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent p = getIntent();
        String names = p.getStringExtra("nombre");
        if (p.getBooleanExtra("op", true)) {
            op = p.getStringExtra("op");
        }
        //

        mps = MediaPlayer.create(this, R.raw.levan);
        mps.start();
        mps.stop();
        repro(op);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR,puntos INTEGER);");


        if (names.length() != 0) {
            name = names;
        }
        Button regre = (Button) findViewById(R.id.buttonreg);
        regre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(m, MenuActivity.class);
                startActivity(intent);
            }

        });
        mostrar = (TextView) findViewById(R.id.textView3);
        msgs = new String[10];
        //MENSAJES PARA ADIVINAR
        msgs[0] = "Supernatural".toUpperCase();
        msgs[1] = "The walking dead".toUpperCase();
        msgs[2] = "El Angel Castiel".toUpperCase();
        msgs[3] = "Los shinigamis comen manzanas".toUpperCase();
        msgs[4] = "La vida es bella".toUpperCase();
        msgs[5] = "Indiana Jones y la última cruzada".toUpperCase();
        msgs[6] = "Juego de Tronos".toUpperCase();
        msgs[7] = "Los programadores nunca mueren tan solo se pierden en el Proceso".toUpperCase();
        msgs[8] = "La máscara del zorro".toUpperCase();
        msgs[9] = "Cuidense de los programadores que lleven destornilladores".toUpperCase();
        iniciar();
        fra = openOrCreateDatabase("RepDB", Context.MODE_PRIVATE, null);
        final Cursor cursor = fra.rawQuery("SELECT * FROM rep ", null);
        if (cursor.getCount() == 0) {
            return;
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < cursor.getCount(); i++) {
            buffer.append(cursor.getString(i) + "\n");
        }
        String pal = buffer.toString();


        Button reiniciar = (Button) findViewById(R.id.button2);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                puntaje = 0;
                ran = 0;
                iniciar();
            }
        });
        Button aceptar = (Button) findViewById(R.id.button3);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //SE SACA LA LETRA
                EditText letra = (EditText) findViewById(R.id.editText4);
                char c;
                String cd = letra.getText().toString().toUpperCase();
                c = cd.charAt(0);

//BUSCAMOS QUE LA LETRA SE ENCUENTRE EN LA FRASE

                boolean esta = false;
                for (int j = 0; j < msgs[ran].length(); j++) {
                    if (c == msgs[ran].charAt(j)) {
                        res[j] = c + "";
                        esta = true;
                    }
                }
//SI LA LETRA ESTA EN EL MENSAJE SE MUESTRA EN EL TEXTPANEL
                if (esta) {
                    mostrar.setText("");
                    for (String re : res) {
                        if (" ".equals(re)) mostrar.setText(mostrar.getText() + "\n");
                        else mostrar.setText(mostrar.getText() + re + " ");
                    }
                    //SE COMPRUEBA QUE SE GANO CUANDO YA NO HAYA _ EN EL MENSAGE
                    boolean gano = true;
                    for (String re : res) {
                        if (re.equals("_")) {
                            gano = false;
                            break;
                        }
                    }
                    //SI SE GANO SE MANDA UN MENSAGE Y SE REINIICIA EL JUEGO
                    //SI LA LETRA NO ESTA EN EL MENSAGE, SE INCREMENTA EL ERROR Y SE CAMBIA LA IMAGEN
                } else {
                    errors++;
                    final ImageView imagen_Appbar = (ImageView) findViewById(R.id.imageView);
                    if (errors == 0) {
                        imagen_Appbar.setImageResource(R.drawable.im1);
                    }
                    if (errors == 1) {
                        imagen_Appbar.setImageResource(R.drawable.im2);
                    }
                    if (errors == 2) {
                        imagen_Appbar.setImageResource(R.drawable.im3);
                    }
                    if (errors == 3) {
                        imagen_Appbar.setImageResource(R.drawable.im4);
                    }
                    if (errors == 4) {
                        imagen_Appbar.setImageResource(R.drawable.im5);
                    }
                    if (errors == 5) {
                        imagen_Appbar.setImageResource(R.drawable.im6);
                        errors = 0;
                        perdiste();
                    }


                }

            }
        });


        Button adivinar = (Button) findViewById(R.id.button);
        adivinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in();


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void insert() {
        db.execSQL("INSERT INTO student VALUES('" + name + "','" + puntaje + "');");
    }


    private void in() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("ADIVINANDO!");
        alert.setMessage("Ingrese su respuesta:");


        // Create EditText for entry
        final EditText input = new EditText(this);

        alert.setView(input);

        // Make an "OK" button to save the name
        alert.setPositiveButton("OK", new OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {

                // Grab the EditText's input
                String inputt = input.getText().toString().toUpperCase();
                boolean esta = true;
                for (int j = 0; j < msgs[ran].length() && msgs[ran].length() <= inputt.length(); j++) {
                    if (inputt.charAt(j) != msgs[ran].charAt(j)) {
                        esta = false;
                    }
                }
                if (msgs[ran].length() != inputt.length()) {
                    esta = false;
                }

                alert1(esta);

                // Put it into memory (don't forget to commit!)
            }
        });

        // Make a "Cancel" button
        // that simply dismisses the alert
        alert.setNegativeButton("Cancel", new OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }

    private void alert1(boolean estado) {
        if (estado) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("ADIVINANDO!");
            alert.setMessage("SU RESPUESTA FUE CORRECTA");
            puntaje = puntaje + 100;
            alert.show();
            ran++;
            errors = 0;
            final ImageView imagen_Appbar = (ImageView) findViewById(R.id.imageView);
            imagen_Appbar.setImageResource(R.drawable.im1);

            iniciar();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("ADIVINANDO!");
            alert.setMessage("SU RESPUESTA FUE INCORRECTA");
            alert.show();
        }
    }

    private void perdiste() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("PERDISTE! ");
        alert.setMessage("Puntaje : " + puntaje);

        alert.setCancelable(true);
        alert.show();

        insert();

        final MainActivity m = this;
        Intent intent = new Intent(m, MenuActivity.class);
        startActivity(intent);


    }

    private void iniciar() {
        //ERRORES EN 0
        if (ran > 9) {
            insert();
            final MainActivity m = this;
            Intent intent = new Intent(m, MenuActivity.class);
            startActivity(intent);
        } else {
            n = (TextView) findViewById(R.id.textName);
            p = (TextView) findViewById(R.id.textPuntos);
            n.setText(name + " Puntaje: ");
            p.setText(puntaje + "");

            mostrar.setText("");
            //ACTIVAMOS TODAS LAS LETRAS
            //GENERAMOS UN MENGSAME RANDOM
            //SEPARAMOS EL MENSAJE POR PALABRAS
            String pal[] = msgs[ran].split(" ");
            res = new String[msgs[ran].length() + 1];
            int j = 0;
            //VAMOS IMPRIMIENDO EL NUMERO DE CARACTERES DEL MENSAGE CON _
            for (String pal1 : pal) {
                for (int i = 0; i < pal1.length(); i++) {
                    mostrar.setText(mostrar.getText() + "_ ");
                    res[j++] = "_";
                }
                mostrar.setText(mostrar.getText() + "\n");
                res[j++] = " ";
            }


        }
    }

    private void repro(String op) {
        mps = MediaPlayer.create(this, R.raw.levan);
        mps.start();
        mps.stop();

        if (op.equals("1")) {
            m11();
        } else if (op.equals("2")) {
            m12();
        } else {

            m13();

        }
    }


    private void m11() {
        if (mps.isPlaying()) {
            mps.stop();
        }
        mps = MediaPlayer.create(this, R.raw.levan);
        mps.start();
    }

    private void m12() {

        if (mps.isPlaying()) {
            mps.stop();
        }
        mps = MediaPlayer.create(this, R.raw.zakura);
        mps.start();
    }

    private void m13() {

        if (mps.isPlaying()) {
            mps.stop();
        }
        mps = MediaPlayer.create(this, R.raw.weekend);
        mps.start();
    }

    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        mps.stop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }


    private void manejarPista(String pista) {
        if (pista != null) {
            if (cantPista > 0) {
                darPista();
            }
        }
    }

    private void darPista() {
        for (int i = 0; i < msgs[ran].length(); i++) {
            if (res[ran].substring(i).equals("_")) {
                char letra = msgs[ran].charAt(i);
            }
            cantPista--;
        }


        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }
}
