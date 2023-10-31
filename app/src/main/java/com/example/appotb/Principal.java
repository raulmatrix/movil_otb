package com.example.appotb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void pagoAgua(View v){
        Intent intent = new Intent(getApplicationContext(),Pagos.class);
        startActivity(intent);
    }
}