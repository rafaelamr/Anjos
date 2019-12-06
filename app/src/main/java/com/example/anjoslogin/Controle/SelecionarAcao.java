package com.example.anjoslogin.Controle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anjoslogin.Listagem.ListarAlimentos;
import com.example.anjoslogin.Listagem.ListarCMB;
import com.example.anjoslogin.Listagem.ListarCalcados;
import com.example.anjoslogin.Listagem.ListarHigLimp;
import com.example.anjoslogin.Listagem.ListarMatEscolar;
import com.example.anjoslogin.Listagem.ListarNecessidades;
import com.example.anjoslogin.Listagem.ListarVestuario;
import com.example.anjoslogin.Modelo.Familia;
import com.example.anjoslogin.Modelo.Necessidade;
import com.example.anjoslogin.R;
import com.example.anjoslogin.Usuario.Perfil;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelecionarAcao extends AppCompatActivity {
    public ListView aliaslistview;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Necessidade necessidade;


    private List<Necessidade> necessidades = new ArrayList<Necessidade>();
    private ArrayAdapter<Necessidade> necessidadeArrayAdapter;
//    public String[] menu = new String[]{"Alimentos", "Vestuário", "Higiene/Limpeza", "Calçados", "Material Escolar", "Cama, Mesa e Banho"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_acao);
        aliaslistview = findViewById(R.id.listViewSA);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, menu);
//        aliaslistview.setAdapter(adapter);
        aliaslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                necessidade = (Necessidade) parent.getAdapter().getItem(position);
                Intent nova = new Intent(SelecionarAcao.this, ListarNecessidades.class);
                nova.putExtra("ObjetoNecessidade", necessidade);
                startActivity(nova);
            }
        });

        inicializarFirebase();
        eventoDatabase();
    }

    private void eventoDatabase() {
        Query query = databaseReference.child("Necessidade");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                necessidades.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Necessidade necessidade = objSnapshot.getValue(Necessidade.class);
                    necessidades.add(necessidade);
                }
                necessidadeArrayAdapter = new ArrayAdapter<Necessidade>(SelecionarAcao.this, android.R.layout.simple_list_item_1, necessidades);
                aliaslistview.setAdapter(necessidadeArrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(SelecionarAcao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }



}
