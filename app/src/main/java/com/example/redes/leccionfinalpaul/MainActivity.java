package com.example.redes.leccionfinalpaul;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String resultado;
    int intentos = 0;
    int aciertos = 0;
    int fallas = 0;

    TextView resultadogui;
    Button btnValidargui;

    MediaPlayer mediaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultadogui = (TextView) findViewById(R.id.txtRespuesta);

        btnValidargui = (Button) findViewById(R.id.btnValidar);

        mediaplayer = MediaPlayer.create(this, R.raw.sonido);

        btnValidargui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado = String.valueOf(resultadogui.getText());
                if (intentos < 3){
                    if(resultado.equals("5")){
                        intentos ++;
                        aciertos++;
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Respuesta Correcta, Aciertos:" + aciertos+ " Fallas: "+ fallas, Toast.LENGTH_SHORT);
                        //toast1.show();
                        mediaplayer.start();
                        creaNotificacion(0,"Notificación Android!","Como llamar a una alerta o notificación para el usuario en la aplicación de Android?", "http://es.stackoverflow.com", getApplicationContext());
                    }else{
                        intentos++;
                        fallas++;
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Respuesta Incorrecta, Aciertos:" + aciertos+ " Fallas: "+ fallas, Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                }else{
                    //intent
                    Intent itemintent = new Intent(MainActivity.this, resultados.class);

                    itemintent.putExtra("intentos" , String.valueOf(intentos));
                    itemintent.putExtra("aciertos" , String.valueOf(aciertos));
                    itemintent.putExtra("fallas" , String.valueOf(fallas));

                    startActivity(itemintent);

            }

            }


        });
    }

    public static void creaNotificacion(long when, String notificationTitle,
                                        String notificationContent, String notificationUrl, Context ctx) {
        try {

            Intent notificationIntent;


            Bitmap largeIcon = BitmapFactory.decodeResource(ctx.getResources(),
                    R.drawable.acierto);
            int smalIcon = R.drawable.acierto;

            /* Valida la url y crea un Intent */
            if (!"".equals(notificationUrl)) {
                notificationIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(notificationUrl));
            } else {
                notificationIntent = new Intent();
            }

            /* Crea PendingIntent */
            PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            NotificationManager notificationManager = (NotificationManager) ctx
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            /* Construye la notificacion */
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                    ctx).setWhen(when).setContentText(notificationContent)
                    .setContentTitle(notificationTitle).setSmallIcon(smalIcon)
                    .setAutoCancel(true).setTicker(notificationTitle)
                    .setLargeIcon(largeIcon)
                    .setContentIntent(pendingIntent);

            notificationManager.notify((int) when, notificationBuilder.build());

        } catch (Exception e) {
            Log.e("Notificacion", "createNotification::" + e.getMessage());
        }

    }

}
