package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.models.NotaModel;
import com.example.myapplication.operations.NotaOperations;


import android.os.Bundle;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    private NotaOperations notaOperations;
    private EditText titulo,contenido;
    private Button guardar;
    private NotaModel model;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        notaOperations = new NotaOperations(getApplicationContext());
        titulo = findViewById(R.id.titulo);
        contenido = findViewById(R.id.contenido);

        guardar = findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulos, contenidos;

                titulos = titulo.getText().toString();
                contenidos = contenido.getText().toString();

                model = new NotaModel(titulos, contenidos);
                int a = notaOperations.insertModel(model);
                notaOperations.close();
                if (a > 0) {
                    Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {

                }
            }
        });
   }
}