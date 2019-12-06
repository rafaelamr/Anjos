package com.example.anjoslogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anjoslogin.FirebaseConf.Conexao;
import com.example.anjoslogin.Modelo.Anjo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public EditText alias_id, aliasnome, aliascpf, aliasendereco;
    public Button aliassalvar, aliasapagar, aliaslogout;
    public ListView aliaslista;

    private List<Anjo> anjos = new ArrayList<Anjo>();
    private ArrayAdapter<Anjo> arrayAdapterAnjo;

    //Firebase Database Realtime
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //Firebase Autenticação (classe conexão simplifaca a codificação
    private FirebaseAuth auth;
    private FirebaseUser user;

    Anjo anjoSelecionada, anjo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alias_id = (EditText) findViewById(R.id.edit_Id);
        aliasnome = (EditText) findViewById(R.id.editNome);
        aliascpf = (EditText) findViewById(R.id.editCpf);
        aliasendereco = (EditText) findViewById(R.id.editEndereco);
        aliassalvar = (Button) findViewById(R.id.buttonsalvar);
        aliasapagar = (Button) findViewById(R.id.buttonapagar);
        aliaslogout = (Button) findViewById(R.id.buttonlogout);
        aliaslista = (ListView) findViewById(R.id.listview);
        anjo = new Anjo();
        aliaslista.setOnItemClickListener(this);

        inicializarFirebase();
        eventoDatabase();

        aliassalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anjo = new Anjo();
                anjo.set_id(alias_id.getText().toString());
                anjo.setNome(aliasnome.getText().toString());
                anjo.setCpf(aliascpf.getText().toString());
                anjo.setCelular(aliasendereco.getText().toString());
                anjo.setToken(FirebaseInstanceId.getInstance().getToken());
                //databaseReference.child("Cachorro").child(String.valueOf(cachorro.get_id())).setValue(cachorro);
                // o segundo child, separa os cachorros pelo Uid do usuário, fornecido pelo Firebase Authentication
                databaseReference.child("Anjo").child(user.getUid()).child(anjo.get_id().toString()).setValue(anjo);
                Toast.makeText(getBaseContext(), "Dados Gravados com Sucesso", Toast.LENGTH_SHORT).show();
                limparCampos();
            }
        });

        aliasapagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Anjo anjo = new Anjo();
                anjo.set_id(anjoSelecionada.get_id());
                //databaseReference.child("Cachorro").child(cachorro.get_id().toString()).removeValue();
                // o segundo child, separa os cachorros pelo Uid do usuário, fornecido pelo Firebase Authentication
                databaseReference.child("Cachorro").child(user.getUid()).child(anjo.get_id().toString()).removeValue();
                Toast.makeText(getBaseContext(), "Dados Excluidos com Sucesso", Toast.LENGTH_SHORT).show();
                limparCampos();
            }
        });

        aliaslogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexao.logOut();
                finish();
            }
        });

    }

    private void eventoDatabase() {
        databaseReference.child("Anjo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (user != null) {
                    anjos.clear();
                    //for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    // o for anterior pegava os nodes da árvode do Json à partir do Id do cachorro
                    // o novo for busca somente os cachorros dos usuário // serve como filtro.
                    for (DataSnapshot objSnapshot : dataSnapshot.child(user.getUid()).getChildren()) {
                        Anjo anjo = objSnapshot.getValue(Anjo.class);
                        anjos.add(anjo);
                    }
                    arrayAdapterAnjo = new ArrayAdapter<Anjo>(MainActivity.this, android.R.layout.simple_list_item_1, anjos);
                    aliaslista.setAdapter(arrayAdapterAnjo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        //utilizado para funcionar Offline - testar
        //firebaseDatabase.setPersistenceEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        anjoSelecionada = (Anjo) adapterView.getItemAtPosition(i);
        alias_id.setText(anjoSelecionada.get_id().toString());
        aliascpf.setText(anjoSelecionada.getCpf().toString());
        aliasendereco.setText(anjoSelecionada.getCelular().toString());

    }

    private void limparCampos() {
        alias_id.setText("");
        aliasnome.setText("");
        aliascpf.setText("");
        aliasendereco.setText("");
    }

    // O OnStart é utilizado para pegar a instancia do firebase autentication
    // e também o usuário do firebase --> olhe a conexão.
    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();

    }
}
