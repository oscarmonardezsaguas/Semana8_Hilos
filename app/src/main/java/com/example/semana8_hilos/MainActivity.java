package com.example.semana8_hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.security.KeyStore;

public class MainActivity extends AppCompatActivity
{
    private boolean is_comenzando=false;  // declaracion variable booleana
    private int contador=0;               // declaracion variable  entero
    private TextView tvContador;          // declaracion varivla de tipo textview

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContador=findViewById(R.id.tvContador); // obtencion valor de componente textview
    }

    // boton para demostrar que sucede cuando usamos el hilo pricipal para ejecutar un bucle
    public void onClickHiloPrincipal(View view)
    {
        is_comenzando=true;
        while (is_comenzando){
            contador++;
            tvContador.setText(String.valueOf(tvContador));
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    // boton para demostrar que sucede cuando usamos un hilo segundario
    public void onClickHiloSegundario(View view)
    {
        is_comenzando=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (is_comenzando)
                {
                    contador++;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvContador.setText(String.valueOf(contador));
                        }
                    });
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    // boton para demostrar que sucede cuando usamos un hilo segundario
    public void onClickSegundarioFinal(View view)
    {
        if (!is_comenzando)
        {
            is_comenzando = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (is_comenzando) {
                        contador++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvContador.setText(String.valueOf(contador));
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}