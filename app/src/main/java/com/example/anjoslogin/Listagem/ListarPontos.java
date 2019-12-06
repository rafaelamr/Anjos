package com.example.anjoslogin.Listagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anjoslogin.Controle.ConfirmarDoacao;
import com.example.anjoslogin.Modelo.Bairro;
import com.example.anjoslogin.Modelo.Familia;
import com.example.anjoslogin.Modelo.Necessidade;
import com.example.anjoslogin.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarPontos extends AppCompatActivity {

    ListView aliaslista;

    private List<Familia> familias = new ArrayList<Familia>();
    private ArrayAdapter<Familia> familiaArrayAdapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Familia familia;
    Bairro bairro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pontos);
        aliaslista = findViewById(R.id.lvPontos);
        inicializarFirebase();
        verificarparametro();
        eventoDatabase();

        aliaslista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                familia = (Familia) parent.getAdapter().getItem(position);
                Intent nova = new Intent(getBaseContext(), ListarPontos.class);
                nova.putExtra("ObjetoFamilia", familia);
                startActivity(nova);
            }
        });


    }

    private void verificarparametro() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra("ObjetoPontos")== null){
            Toast.makeText(this, "Vazio", Toast.LENGTH_SHORT).show();
            finish();

        }else {
            bairro=(Bairro) intent.getSerializableExtra("ObjetoPontos");
        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        familia = (Familia) parent.getItemAtPosition(position);
//
//    }

    private void eventoDatabase() {
        //Query query = databaseReference.child("Familia").child("necessidade").orderByChild("necessidade").equalTo("Roupas");
        Query query = databaseReference.child("Familia");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                familias.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Familia familia = objSnapshot.getValue(Familia.class);
                    if (familia.getBairro().getBairro().equals(bairro.getPntEntrega()))
                        familias.add(familia);
                }
                familiaArrayAdapter = new ArrayAdapter<Familia>(ListarPontos.this, android.R.layout.simple_list_item_1, familias);
                aliaslista.setAdapter(familiaArrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        databaseReference.child("Familia").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                familias.clear();
//                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
//                    Familia familia = objSnapshot.getValue(Familia.class);
//                    familias.add(familia);
//                }
//                familiaArrayAdapter = new ArrayAdapter<Familia>(ListarAlimentos.this, android.R.layout.simple_list_item_1, familias);
//                aliaslista.setAdapter(familiaArrayAdapter);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(ListarPontos.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();

    }
}
