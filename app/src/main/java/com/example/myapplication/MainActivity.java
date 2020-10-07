package com.example.myapplication;
import android.content.Intent;

import com.example.myapplication.models.NotaModel;
import com.example.myapplication.operations.NotaOperations;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv_main_datos;
    private NotaOperations notaOperations;
    private ArrayList<NotaModel> list;
    private ArrayList<String> listString;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button obtnIrregistro = (Button) findViewById(R.id.btn_registro);

        lv_main_datos = findViewById(R.id.lv_main_datos);
        notaOperations = new NotaOperations(getApplicationContext());

        listString = notaOperations.selectAllString();
        notaOperations.close();

        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listString);
        lv_main_datos.setAdapter(itemsAdapter);

        // Metodo para ir a un activity al darle clic
        lv_main_datos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = listString.get(position);
                Intent detalle = new Intent(MainActivity.this, DetalleActivity.class);
                detalle.putExtra("item", seleccionado);
                startActivity(detalle);
            }
        });

        lv_main_datos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texto = listString.get(position);
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
            }
        });

        obtnIrregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oLanzarFormulario = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(oLanzarFormulario);
            }
        });
    }
}