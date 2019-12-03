package com.example.anjoslogin.Usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anjoslogin.Controle.ActivityTelaInicial;
import com.example.anjoslogin.FirebaseConf.Conexao;
import com.example.anjoslogin.MainActivity;
import com.example.anjoslogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.UUID;

public class CadastrarUsuario extends AppCompatActivity {
    private EditText aliasemail,aliassenha;
    private Button aliassalvar,aliasvoltar;
    private FirebaseAuth auth;


    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        aliasemail=(EditText)findViewById(R.id.editEmail);
        aliassenha=(EditText)findViewById(R.id.editSenha);
        aliassalvar=(Button)findViewById(R.id.btntest);
        aliasvoltar=(Button)findViewById(R.id.buttonvoltarcadastro);

        aliassalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = aliasemail.getText().toString().trim();
                String senha = aliassenha.getText().toString().trim();
                criarUsuario(email, senha);

            }
        });



        aliasvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void criarUsuario(String email, String senha) {

        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(CadastrarUsuario.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CadastrarUsuario.this, "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CadastrarUsuario.this, ActivityTelaInicial.class);
                            startActivity(intent);
                            finish();
                        }else
                        {
                            Toast.makeText(CadastrarUsuario.this, "Erro no Cadastro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    protected void onStart() {
        super.onStart();
        auth= Conexao.getFirebaseAuth();
    }
}
