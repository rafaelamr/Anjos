package com.example.anjoslogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetarSenha extends AppCompatActivity {
    private EditText aliasemail;
    private Button aliasresetar, aliasvoltar;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetar_senha);
        aliasemail = (EditText) findViewById(R.id.editTextResetar);
        aliasresetar = (Button) findViewById(R.id.buttonsenhareset);
        aliasvoltar = (Button) findViewById(R.id.buttonVoltarReset);
        progressBar = (ProgressBar) findViewById(R.id.progressBarReset);
        progressBar.setVisibility(View.INVISIBLE);
        auth = FirebaseAuth.getInstance();
        aliasvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        aliasresetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = aliasemail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Digite o E-mail do seu usuário", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetarSenha.this, "Enviado com Sucesso", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ResetarSenha.this, "Falha no eenvio do e-mail !", Toast.LENGTH_SHORT).show();
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });
    } }
