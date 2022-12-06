package com.eigirdasmil.dviraciunuoma;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Registracija extends AppCompatActivity {

    EditText prisijungimoVardas;
    EditText elPastas;
    EditText slaptazodis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);
        prisijungimoVardas = findViewById(R.id.editTextTextPersonName2);
        elPastas = findViewById(R.id.editTextTextPersonName3);
        slaptazodis = findViewById(R.id.editTextTextPersonName4);
    }
    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}