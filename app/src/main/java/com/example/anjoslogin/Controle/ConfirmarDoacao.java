package com.example.anjoslogin.Controle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anjoslogin.R;

public class ConfirmarDoacao extends AppCompatActivity {
    public Button aliasconfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_doacao);
        aliasconfirmar= findViewById(R.id.button);

        aliasconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
