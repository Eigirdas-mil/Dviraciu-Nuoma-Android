package com.eigirdasmil.dviraciunuoma;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText prisijungimoVardas;
    EditText slaptazodis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prisijungimoVardas = findViewById(R.id.editTextTextPersonName);
        slaptazodis = findViewById(R.id.editTextTextPassword);
    }
    public void prisijungti(View view) {
        if (prisijungimoVardas.getText().toString().equals("admin")  && slaptazodis.getText().toString().equals("admin")) {
            Intent intent = new Intent(this, AdminControl.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Pagrindinis.class);
            startActivity(intent);
        }
    }
    public void Registracija(View view) {
        Intent intent = new Intent(this, Registracija.class);
        startActivity(intent);
    }
}