package com.example.anjoslogin.Controle;

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
import com.example.anjoslogin.Listagem.ListarVestuario;
import com.example.anjoslogin.R;
import com.example.anjoslogin.Usuario.Perfil;

public class SelecionarAcao extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public ListView aliaslistview;
    public String[] menu = new String[]{"Alimentos", "Vestuário", "Higiene/Limpeza", "Calçados", "Material Escolar", "Cama, Mesa e Banho", "Sair"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_acao);
        aliaslistview = findViewById(R.id.listViewSA);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, menu);
        aliaslistview.setAdapter(adapter);
        aliaslistview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent intent = new Intent(getBaseContext(), ListarAlimentos.class);
                startActivity(intent);
                break;

            case 1:
                Intent intent1 = new Intent(getBaseContext(), ListarVestuario.class);
                startActivity(intent1);
                break;

            case 2:
                Intent intent2 = new Intent(getBaseContext(), ListarHigLimp.class);
                startActivity(intent2);
                break;

            case 3:
                Intent intent3 = new Intent(getBaseContext(), ListarCalcados.class);
                startActivity(intent3);
                break;

            case 4:
                Intent intent4 = new Intent(getBaseContext(), ListarMatEscolar.class);
                startActivity(intent4);
                break;

            case 5:
                Intent intent5 = new Intent(getBaseContext(), ListarCMB.class);
                startActivity(intent5);
                break;

            case 6:
                finish();
                break;

            default:
                break;
        }

    }
}
