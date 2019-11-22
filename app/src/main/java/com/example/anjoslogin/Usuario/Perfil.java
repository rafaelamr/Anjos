package com.example.anjoslogin.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anjoslogin.FirebaseConf.Conexao;
import com.example.anjoslogin.MainActivity;
import com.example.anjoslogin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {

    public TextView aliasemail,aliasid;
    public Button aliasLogout, aliascrudcomautenticacao;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        aliasemail=(TextView)findViewById(R.id.textEmailPerfil);
        aliasid=(TextView)findViewById(R.id.textIdPerfil);
        aliasLogout=(Button)findViewById(R.id.buttonLogout);
        aliascrudcomautenticacao=(Button)findViewById(R.id.buttonPerfilCrud);

        aliascrudcomautenticacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        aliasLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexao.logOut();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth=Conexao.getFirebaseAuth();
        user=Conexao.getFirebaseUser();
        verificarUsurio();
    }

    private void verificarUsurio() {
        if (user==null){
            finish();
        }
        else
        {
            aliasemail.setText(""+user.getEmail());
            aliasid.setText(""+user.getUid());
        }
    }
}
