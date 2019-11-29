package com.example.anjoslogin.Controle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anjoslogin.R;
import com.example.anjoslogin.Usuario.Perfil;

public class ActivityTelaInicial extends AppCompatActivity {
    public Button aliasEfetuarDoacao, aliasVerPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        aliasEfetuarDoacao = findViewById(R.id.buttonefetuardoacao);
        aliasVerPerfil = findViewById(R.id.buttonverperfil);

        aliasEfetuarDoacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelecionarAcao.class);
                startActivity(intent);
            }
        });

        aliasVerPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Perfil.class);
                startActivity(intent);
            }
        });
    }
}
