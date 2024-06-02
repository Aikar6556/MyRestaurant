package com.example.myrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    Button iniciar, registrar, registrarRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iniciar = findViewById(R.id.entrar);
        registrar = findViewById(R.id.registro_usu);
        registrarRest = findViewById(R.id.registro_rest);


        registrarRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro_restaurante(v);
            }
        });

    }

    public void registro_usuario(View view){

        Intent i = new Intent(this, Activity_registrar.class);
        startActivity(i);
    }

    public void registro_restaurante(View view){

        Intent i = new Intent(this, Activity_registrar_restaurant.class);
        startActivity(i);
    }
}