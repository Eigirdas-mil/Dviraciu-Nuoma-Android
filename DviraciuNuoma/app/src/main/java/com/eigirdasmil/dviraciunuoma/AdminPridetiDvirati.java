package com.eigirdasmil.dviraciunuoma;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.Map;
import java.util.UUID;

import static com.eigirdasmil.dviraciunuoma.Pagrindinis.HOST_URL;
import static com.eigirdasmil.dviraciunuoma.Pagrindinis.httpCall;

public class AdminPridetiDvirati extends AppCompatActivity {

    EditText dviracioPavadinimas;
    Spinner dviracioTipas;
    EditText dviracioKaina;
    EditText dviracioSpecifikacija;
    int tipas;

    public static String ADD_BIKE_URL = HOST_URL + "CreateNewBike/?modelioPavadinimas=";
    private String TIPAS_URL = "&tipas=";
    private String KAINA_URL = "&kaina=";
    private String SPECIFIKACIJA_URL = "&specifikacija=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_prideti_dvirati_main2);
        dviracioPavadinimas = findViewById(R.id.editTextTextPersonName5);
        dviracioTipas = findViewById(R.id.spinner3);
        dviracioKaina = findViewById(R.id.editTextTextPersonName8);
        dviracioSpecifikacija = findViewById(R.id.editTextTextPersonName7);

        String[] arraySpinner = new String[] {
                "MIESTO", "PLENTINIS", "SPORTINIS"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dviracioTipas.setAdapter(adapter);

        dviracioTipas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (dviracioTipas.getSelectedItem().equals("MIESTO")) {
                    tipas = 0;
                } else if (dviracioTipas.getSelectedItem().equals("PLENTINIS"))
                {
                    tipas = 1;
                } else if (dviracioTipas.getSelectedItem().equals("SPORTINIS"))
                {
                    tipas = 2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void back(View view) {
        Intent intent = new Intent(this, AdminControl.class);
        startActivity(intent);
    }

    public void prideti(View view) {
        httpCall(ADD_BIKE_URL + dviracioPavadinimas.getText()
                + TIPAS_URL + tipas
                + KAINA_URL + dviracioKaina.getText()
                + SPECIFIKACIJA_URL + dviracioSpecifikacija.getText(), Request.Method.POST, new VolleyCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(String response){
                String text = "Dviratis pridėtas";
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(VolleyError result) {
                String text = "Įvyko klaida";
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }
        }, this);
        Intent intent = new Intent(this, AdminControl.class);
        startActivity(intent);
    }
}