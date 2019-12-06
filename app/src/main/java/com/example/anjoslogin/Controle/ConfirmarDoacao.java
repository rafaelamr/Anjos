package com.example.anjoslogin.Controle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anjoslogin.FirebaseConf.Conexao;
import com.example.anjoslogin.Listagem.ListarCalcados;
import com.example.anjoslogin.Modelo.Anjo;
import com.example.anjoslogin.Modelo.Bairro;
import com.example.anjoslogin.Modelo.Doacao;
import com.example.anjoslogin.Modelo.Familia;
import com.example.anjoslogin.R;
import com.example.anjoslogin.Usuario.CadastrarUsuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConfirmarDoacao extends AppCompatActivity {
    public Button aliasconfirmar, aliasvisualizar;
    public EditText aliasdoacao;
    public TextView aliasfamilia;
    public ListView listViewConfirmar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    private List<Doacao> doacoes = new ArrayList<Doacao>();
    private ArrayAdapter<Doacao> doacaoArrayAdapter;

    private List<Anjo> anjos = new ArrayList<Anjo>();
    private ArrayAdapter<Anjo> anjoArrayAdapter;

    Familia familia;
    Anjo anjo, anjoquery;
    Doacao doacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_doacao);
        aliasconfirmar= findViewById(R.id.buttonConfirmar);
        aliasdoacao= findViewById(R.id.editDoacao);
        aliasfamilia= findViewById(R.id.textViewConfirmar);
        listViewConfirmar = findViewById(R.id.listViewConfirmar);

        doacao=new Doacao();

        iniciarFirebase();
        verificarparametro();
        recuperarAnjo();
        eventoDatabase();



        aliasconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aliasdoacao.getText() != null){
                String doar = aliasdoacao.getText().toString().trim();
                criarDoacao(doar);
                }else
                {
                    Toast.makeText(ConfirmarDoacao.this, "Informe o que vai doar.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void recuperarAnjo() {

        Query query = databaseReference.child("Anjo");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                anjos.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    anjo =(Anjo) objSnapshot.getValue(Anjo.class);
                    if(anjo.get_id().equals(auth.getCurrentUser().getUid())) {
                        anjos.add(anjo);
                        anjoquery = anjos.get(0);
                        //Toast.makeText(ConfirmarDoacao.this, ""+auth.getUid(), Toast.LENGTH_SHORT).show();
                     }
                }
//                if (anjos.size()<0){
//                    Toast.makeText(ConfirmarDoacao.this, "Nao achei", Toast.LENGTH_SHORT).show();
//                }else {
//
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//
//        databaseReference.child("Anjo").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                anjos.clear();
//                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
//
//                    Anjo anjo = objSnapshot.getValue(Anjo.class);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    private void verificarparametro() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra("ObjetoFamilia")== null){
            Toast.makeText(this, "Vazio", Toast.LENGTH_SHORT).show();
            finish();

        }else {
            familia=(Familia) intent.getSerializableExtra("ObjetoFamilia");
        }
    }


    protected void criarDoacao(String doar){
        if (doacao.get_id() == null){
            doacao = new Doacao();
            doacao.set_id(databaseReference.push().getKey());
        }
            doacao.setFamilia(familia);
            doacao.setAnjo(anjoquery);
            doacao.setDoacao(doar);
            databaseReference.child("Familia").child(familia.get_id()).child("Doacao").child(doacao.get_id()).setValue(doacao);
            Toast.makeText(getBaseContext(), "Doação Registrada com Sucesso", Toast.LENGTH_SHORT).show();
            //eventoDatabase();
            limparCampos();

    }


    private void iniciarFirebase() {
        FirebaseApp.initializeApp(ConfirmarDoacao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    private void eventoDatabase() {
        databaseReference.child("Familia").child(familia.get_id()).child("Doacao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                doacoes.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Doacao doacao = objSnapshot.getValue(Doacao.class);
                    doacoes.add(doacao);
                }
                doacaoArrayAdapter = new ArrayAdapter<Doacao>(ConfirmarDoacao.this, android.R.layout.simple_list_item_1, doacoes);
                listViewConfirmar.setAdapter(doacaoArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        doacao = (Doacao) parent.getItemAtPosition(position);
//        aliasdoacao.setText(doacao.getDoacao().toString());
//    }

    @Override
    protected void onStart() {
        super.onStart();
        auth= Conexao.getFirebaseAuth();

        verificarparametro();
        recuperarAnjo();

//        Toast.makeText(this, ""+familia, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ""+anjo, Toast.LENGTH_SHORT).show();
    }

    private void limparCampos() {
        aliasdoacao.setText("");
    }
}
