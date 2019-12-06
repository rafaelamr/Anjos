package com.example.anjoslogin.Controle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anjoslogin.Listagem.ListarNecessidades;
import com.example.anjoslogin.Listagem.ListarPontos;
import com.example.anjoslogin.Modelo.Bairro;
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

public class VisualizarBairros extends AppCompatActivity {

    public ListView aliaslistview;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Bairro bairro;


    private List<Bairro> bairros = new ArrayList<Bairro>();
    private ArrayAdapter<Bairro> bairroArrayAdapter;
//    public String[] menu = new String[]{"Alimentos", "Vestuário", "Higiene/Limpeza", "Calçados", "Material Escolar", "Cama, Mesa e Banho"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_pontos);
        aliaslistview = findViewById(R.id.lvListarBairros);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, menu);
//        aliaslistview.setAdapter(adapter);
        aliaslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bairro = (Bairro) parent.getAdapter().getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(VisualizarBairros.this);
                alerta.setTitle("Ponto de Entrega do bairro " + bairro + ":");
                alerta.setMessage("Endereço: "+ bairro.getPntEntrega());
                alerta.show();

            }
        });

        inicializarFirebase();
        eventoDatabase();
    }

    private void eventoDatabase() {
        Query query = databaseReference.child("Bairro");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bairros.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Bairro bairro = objSnapshot.getValue(Bairro.class);
                    bairros.add(bairro);
                }
                bairroArrayAdapter = new ArrayAdapter<Bairro>(VisualizarBairros.this, android.R.layout.simple_list_item_1, bairros);
                aliaslistview.setAdapter(bairroArrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(VisualizarBairros.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
}
