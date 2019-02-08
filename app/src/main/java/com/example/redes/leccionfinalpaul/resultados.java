package com.example.redes.leccionfinalpaul;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultados extends AppCompatActivity {
    String intentos;
    String aciertos;
    String fallas;

    TextView txtintentosgui;
    TextView txtaciertosgui;
    TextView txtfallasgui;

    Button btnGuardargui;

    MediaPlayer mediaplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null){
            intentos = parametros.getString("intentos");
            aciertos = parametros.getString("aciertos");
            fallas = parametros.getString("fallas");
        }
        mediaplayer = MediaPlayer.create(this, R.raw.sonido);

        txtintentosgui = (TextView) findViewById(R.id.txtIntentos);
        txtaciertosgui = (TextView) findViewById(R.id.txtAciertos);
        txtfallasgui = (TextView) findViewById(R.id.txtFallas);

        txtintentosgui.setText(intentos);
        txtaciertosgui.setText(aciertos);
        txtfallasgui.setText(fallas);

        btnGuardargui = (Button) findViewById(R.id.btnGuardar);
        btnGuardargui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.start();
            }
            });

    }

}
