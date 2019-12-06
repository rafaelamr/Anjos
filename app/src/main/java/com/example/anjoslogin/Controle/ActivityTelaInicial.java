package com.example.anjoslogin.Controle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anjoslogin.FirebaseConf.Conexao;
import com.example.anjoslogin.Modelo.Bairro;
import com.example.anjoslogin.R;
import com.example.anjoslogin.Usuario.Perfil;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityTelaInicial extends AppCompatActivity {
    public Button aliasEfetuarDoacao, aliasVerPerfil;
    public TextView aliaspontos;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        aliasEfetuarDoacao = findViewById(R.id.buttonefetuardoacao);
        aliaspontos= findViewById(R.id.textViewPontos);
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
    @Override
    protected void onStart() {
        super.onStart();

        auth= Conexao.getFirebaseAuth();
        Toast.makeText(this, ""+auth.getUid(), Toast.LENGTH_SHORT).show();
    }
}
