package com.eigirdasmil.dviraciunuoma;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eigirdasmil.dviraciunuoma.deserializer.BikesDeserializer;
import com.eigirdasmil.dviraciunuoma.deserializer.LinkedHashMapAdapter;
import com.eigirdasmil.dviraciunuoma.ds.Bikes;
import com.eigirdasmil.dviraciunuoma.ds.DviracioStatusasEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class Pagrindinis extends AppCompatActivity {

    Spinner dviratis;
    TextView dviracioSpecifikacija;
    Button kelione;
    Context pagrindinis;
    List<Bikes> allBikesFromJson;
    UUID selectedId;
    String kelionesId;
    public static String HOST_URL = "http://dnuoma.westeurope.cloudapp.azure.com:8000/api/Home/";
    public static String GET_DVIRACIAI_URL = HOST_URL + "GetAllBikes";
    public static String START_KELIONE_URL = HOST_URL + "StartKelione/?username=user&dviracioId=";
    public static String END_KELIONE_URL = HOST_URL + "EndKelione/?kelionesId=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagrindinis);
        dviratis = findViewById(R.id.spinner2);
        dviracioSpecifikacija = findViewById(R.id.textView8);
        kelione = findViewById(R.id.button3);
        pagrindinis = this;
        dviratis.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    getDviraciai();
                }
                return false;
            }
        });


        dviratis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Map.Entry < UUID, String > item = (Map.Entry < UUID, String > ) dviratis.getSelectedItem();
                selectedId = item.getKey();
                String specifikacija = allBikesFromJson.stream().filter(bike -> bike.id == selectedId).findAny().get().dviracioSpecifikacija;
                dviracioSpecifikacija.setText(specifikacija);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                dviracioSpecifikacija.setText("Kregždutė yra miesto dviratis, skirtas važinėti dviračių takais, jo ratų dydis yra 21, svoris 5,4 kg. Dažniausiai šį dviratį renkasi senjorai.");
            }

        });

    }
    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void getDviraciai() {
        httpCall(GET_DVIRACIAI_URL, Request.Method.GET, new VolleyCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(String response){
                Gson builder = new GsonBuilder()
                        .registerTypeAdapter(Bikes.class, new BikesDeserializer())
                        .create();
                Type dviraciai = new TypeToken<List<Bikes>>() {
                }.getType();
                allBikesFromJson = builder.fromJson(response, dviraciai);
                LinkedHashMap<UUID,String> arraySpinneris = new LinkedHashMap<UUID,String>();
                List<Bikes> freeBikes = allBikesFromJson.stream()
                        .filter(bikes -> bikes.dviracioStatusas == DviracioStatusasEnum.LAISVAS)
                        .collect(Collectors.toList());
                for(Bikes b: freeBikes) {
                    arraySpinneris.put(b.id,b.dviracioPavadinimas + " - " + b.dviracioKaina + "€/h");
                }
                LinkedHashMapAdapter<UUID, String> adapter = new LinkedHashMapAdapter<UUID, String>(pagrindinis,
                        android.R.layout.simple_spinner_item, arraySpinneris);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dviratis.setAdapter(adapter);

            }

            @Override
            public void onError(VolleyError result) {
                //TODO
            }
        }, this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void kelione(View view) {
        LocalDateTime now = LocalDateTime.now();
        if (kelione.getText().toString().equals("Pradėti kelionę")) {
            httpCall(START_KELIONE_URL+selectedId, Request.Method.GET, new VolleyCallback() {
                @Override
                public void onSuccess(String response){
                    String text = "Jūsų kelionė pradėta: " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond();
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                    dviratis.setEnabled(false);
                    kelionesId = response.replace("\"", "");;

                    kelione.setText("Baigti kelionę");
                }

                @Override
                public void onError(VolleyError result) {
                    String text = "Nepavyko pradėti kelionės. Serverio klaida";
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                    dviratis.setEnabled(true);
                }
            }, this);
        } else {
            httpCall(END_KELIONE_URL.concat(kelionesId), Request.Method.GET, new VolleyCallback() {
                @Override
                public void onSuccess(String response){
                    kelione.setText("Pradėti kelionę");
                    LocalDateTime after = LocalDateTime.now();
                    double kaina = 0.1*after.getMinute();
                    String text = "Jūsų kelionė pabaigta: " + after.getHour() + ":" + after.getMinute() + ":" + after.getSecond() + " Kaina: " + kaina + "€";
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                    dviratis.setEnabled(true);
                }

                @Override
                public void onError(VolleyError result) {
                    String text = "Nepavyko pabaigti kelionės. Serverio klaida";
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                    dviratis.setEnabled(false);
                }
            }, this);
        }
    }

    public static void httpCall(String url, int metodas, final VolleyCallback callback, Context context) {

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(metodas, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });

        queue.add(stringRequest);
    }
}