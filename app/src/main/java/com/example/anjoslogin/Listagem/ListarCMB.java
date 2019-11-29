package com.example.anjoslogin.Listagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anjoslogin.Modelo.Familia;
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

public class ListarCMB extends AppCompatActivity {

    ListView aliaslista;

    private List<Familia> familias = new ArrayList<Familia>();
    private ArrayAdapter<Familia> familiaArrayAdapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Familia familia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cmb);
        aliaslista = findViewById(R.id.lvListarCMB);
        inicializarFirebase();
        eventoDatabase();

//        aliaslista.setOnItemClickListener(this);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        familia = (Familia) parent.getItemAtPosition(position);
//
//    }

    private void eventoDatabase() {
        //Query query = databaseReference.child("Familia").child("necessidade").orderByChild("necessidade").equalTo("Roupas");
        Query query4 = databaseReference.child("Familia");
        query4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                familias.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Familia familia = objSnapshot.getValue(Familia.class);
                    if(familia.getNecessidade().getNecessidade().equals("Cama, Mesa e Banho"))
                        familias.add(familia);
                }
                familiaArrayAdapter = new ArrayAdapter<Familia>(ListarCMB.this, android.R.layout.simple_list_item_1, familias);
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
        FirebaseApp.initializeApp(ListarCMB.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();

    }
}
