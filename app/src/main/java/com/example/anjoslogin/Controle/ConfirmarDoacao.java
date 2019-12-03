package com.example.anjoslogin.Controle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anjoslogin.Modelo.Familia;
import com.example.anjoslogin.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConfirmarDoacao extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public Button aliasconfirmar;
    public EditText aliasdoacao;
    public TextView aliasfamilia;
    public ListView listViewConfirmar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Familia> familias = new ArrayList<Familia>();
    private ArrayAdapter<Familia> familiaArrayAdapter;

    Familia familia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_doacao);
        aliasconfirmar= findViewById(R.id.buttonConfirmar);
        aliasdoacao= findViewById(R.id.editDoacao);
        aliasfamilia= findViewById(R.id.textViewConfirmar);
        listViewConfirmar = findViewById(R.id.listViewConfirmar);

        iniciarFirebase();
        eventoDatabase();

        aliasconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void iniciarFirebase() {
        FirebaseApp.initializeApp(ConfirmarDoacao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }
    private void eventoDatabase() {
        databaseReference.child("Familia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }
}
