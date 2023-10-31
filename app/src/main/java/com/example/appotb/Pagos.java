package com.example.appotb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pagos extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        listView = findViewById(R.id.listaPagos);

        obtenerCobros("http://192.168.0.9:9098/aguaotb/remoto_devolverCobros.php");
    }

    private void obtenerCobros(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Procesa la respuesta JSON
                            ArrayList<String> cobrosList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject cobro = response.getJSONObject(i);
                                // Agrega la información del cobro a la lista
                                cobrosList.add("Fecha: " + cobro.getString("fecha") +
                                        ", Monto: " + cobro.getString("monto") +
                                        ", Descripción: " + cobro.getString("descripcion"));
                            }

                            // Muestra los cobros en un ListView
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                                    Pagos.this, android.R.layout.simple_list_item_1, cobrosList);
                            listView.setAdapter(arrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Maneja el error de la solicitud aquí
                        Toast.makeText(Pagos.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}