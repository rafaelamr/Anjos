package com.example.anjoslogin.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anjoslogin.Controle.ActivityTelaInicial;
import com.example.anjoslogin.FirebaseConf.Conexao;
import com.example.anjoslogin.Modelo.Anjo;
import com.example.anjoslogin.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManterAnjo extends AppCompatActivity {
    public EditText aliasnomeanjo, aliascpfanjo, aliascelularanjo;
    public Button aliasalvaranjo;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    private List<Anjo> anjos = new ArrayList<Anjo>();

    Anjo anjo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_anjo);

        aliasnomeanjo= findViewById(R.id.editNomeAnjo);
        aliascpfanjo= findViewById(R.id.editCpfAnjo);
        aliascelularanjo=findViewById(R.id.editCelularAnjo);
        aliasalvaranjo=findViewById(R.id.buttonSalvarAnjo);

        anjo = new Anjo();

        inicializarFirebase();
        eventoDatabase();


        aliasalvaranjo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   if (anjo.get_id() == null) {
                       anjo = new Anjo();
                       anjo.set_id(auth.getUid());
                   }
                anjo.setNome(aliasnomeanjo.getText().toString());
                anjo.setCpf(aliascpfanjo.getText().toString());
                anjo.setCelular(aliascelularanjo.getText().toString());
                databaseReference.child("Anjo").child(anjo.get_id()).setValue(anjo);
                Toast.makeText(getBaseContext(),"Dados Gravados com Sucesso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ManterAnjo.this, ActivityTelaInicial.class);
                startActivity(intent);

            }
        });

    }

    private void eventoDatabase() {
        databaseReference.child("Anjo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Anjo anjo = objSnapshot.getValue(Anjo.class);
                    anjos.add(anjo);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(ManterAnjo.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth= Conexao.getFirebaseAuth();
    }

}
