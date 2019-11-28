package com.example.anjoslogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anjoslogin.Listagem.ListarFamilias;
import com.example.anjoslogin.Recycler.ActivityRecycler;
import com.example.anjoslogin.Usuario.Perfil;

public class SelecionarAcao extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public ListView aliaslistview;
    public String[] menu=new String[]{"Familias Cadastradas", "Visualizar Perfil","Sair"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_acao);
        aliaslistview=findViewById(R.id.listViewSA);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,menu);
        aliaslistview.setAdapter(adapter);
        aliaslistview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent intent = new Intent(getBaseContext(), ListarFamilias.class);
                startActivity(intent);
                break;

            case 1:
                Intent intent1 = new Intent(getBaseContext(), Perfil.class);
                startActivity(intent1);
                break;

            case 2:
                finish();
                break;
            default: break;
        }

    }
}
