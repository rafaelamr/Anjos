package com.example.anjoslogin.Usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anjoslogin.Controle.ActivityTelaInicial;
import com.example.anjoslogin.FirebaseConf.Conexao;
//import com.example.anjoslogin.MainActivity;
import com.example.anjoslogin.R;
import com.example.anjoslogin.Controle.ResetarSenha;
import com.example.anjoslogin.Controle.SelecionarAcao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.iid.FirebaseInstanceId;


public class Login extends AppCompatActivity {
    public EditText aliasemail,aliassenha;
    public Button aliaslogar,aliasNovoUsuario;
    public TextView aliasresetarsenha;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        aliasemail=(EditText)findViewById(R.id.editEmailLogin);
        aliassenha=(EditText)findViewById(R.id.editSenhaLogin);
        aliaslogar=(Button)findViewById(R.id.buttonLogar);
        aliasNovoUsuario=(Button)findViewById(R.id.buttonNovoUsuario);
        aliasresetarsenha=(TextView)findViewById(R.id.tVResetar);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);
        progressBar.setVisibility(View.INVISIBLE);




        aliasNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CadastrarUsuario.class);
                startActivity(intent);

            }
        });



        aliaslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = aliasemail.getText().toString().trim();
                String senha = aliassenha.getText().toString().trim();
                login(email, senha);
            }
        });

        aliasresetarsenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResetarSenha.class);
                startActivity(intent);
            }
        });

        aliasNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CadastrarUsuario.class);
                startActivity(intent);


            }
        });
    }

    private void login(String email, String senha) {
        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent i=new Intent(Login.this, ActivityTelaInicial.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth= Conexao.getFirebaseAuth();
    }
}
