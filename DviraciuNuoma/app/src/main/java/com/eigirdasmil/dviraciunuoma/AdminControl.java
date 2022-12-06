package com.eigirdasmil.dviraciunuoma;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.eigirdasmil.dviraciunuoma.deserializer.BikesDeserializer;
import com.eigirdasmil.dviraciunuoma.deserializer.LinkedHashMapAdapter;
import com.eigirdasmil.dviraciunuoma.ds.Bikes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import android.widget.AdapterView;
import android.widget.Spinner;


import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.eigirdasmil.dviraciunuoma.Pagrindinis.*;

public class AdminControl extends AppCompatActivity {

    Spinner dviratis;
    Context admin;
    List<Bikes> allBikesFromJson;
    UUID selectedId;
    public static String DELETE_BIKE_URL = HOST_URL + "DeleteBike/?dviracioId=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control);
        dviratis = findViewById(R.id.spinner);
        admin = this;

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
                Map.Entry <UUID, String > item = (Map.Entry < UUID, String > ) dviratis.getSelectedItem();
                selectedId = item.getKey();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getDviraciai() {
        httpCall(GET_DVIRACIAI_URL, Request.Method.GET, new VolleyCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(String response){
                System.out.println(response);
                Gson builder = new GsonBuilder()
                        .registerTypeAdapter(Bikes.class, new BikesDeserializer())
                        .create();
                Type dviraciai = new TypeToken<List<Bikes>>() {
                }.getType();
                allBikesFromJson = builder.fromJson(response, dviraciai);
                LinkedHashMap<UUID,String> arraySpinneris = new LinkedHashMap<UUID,String>();
                for(Bikes b: allBikesFromJson) {
                    arraySpinneris.put(b.id,b.dviracioPavadinimas + " | " + b.dviracioStatusas);
                }
                LinkedHashMapAdapter<UUID, String> adapter = new LinkedHashMapAdapter<UUID, String>(admin,
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

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void pridetiNauja(View view) {
        Intent intent = new Intent(this, AdminPridetiDvirati.class);
        startActivity(intent);
    }

    public void istrintiDvirati(View view) {
        httpCall(DELETE_BIKE_URL+selectedId, Request.Method.DELETE, new VolleyCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(String response){
                String text = "Dviračio statusas pakeistas į IŠTRINTAS";
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(VolleyError result) {
                String text = "Įvyko klaida";
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }
        }, this);

    }

}