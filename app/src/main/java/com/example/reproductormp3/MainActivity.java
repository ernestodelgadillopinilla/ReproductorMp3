package com.example.reproductormp3;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    TextView tv1;
    Switch s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=findViewById(R.id.tv1);
        s=findViewById(R.id.switch1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp!=null)
        {
            mp.release();
            mp=null;
        }
    }

    public void iniciar (View v)
    {
        if (mp!=null)
            mp.release();
        mp=MediaPlayer.create(this,R.raw.iorde);
        if (s.isChecked())
            mp.setLooping(true);
        mp.start();
        tv1.setText("Estado: reproduciendo");
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                tv1.setText("Estado: Detenido");
            }
        });

    }

    public void pausar (View v)
    {
        if (mp!=null && mp.isPlaying())
        {
            mp.pause();
            tv1.setText("Estado: pausado");
        }
    }

    public void continuar (View v)
    {
        if (mp!=null && !mp.isPlaying())
        {
            mp.start();
            tv1.setText("Estado: reproduciendo");
        }
    }

    public void avanzar5 (View v)
    {
        if (mp!=null)
        {
            int posicion=mp.getCurrentPosition();
            mp.seekTo(posicion + 20000);
        }
    }

    public void reproducirBucle (View V)
    {
        Switch s=findViewById(R.id.switch1);
        if (s.isChecked())
            mp.setLooping(true);
        else
            mp.setLooping(false);

    }

}