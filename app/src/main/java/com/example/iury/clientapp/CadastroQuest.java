package com.example.iury.clientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import HuntApi.ControleGeolocalizacao.GoogleMaps.HuntApiGoogleServiceGerente;
import HuntApi.ControleInteracaoJogo.UsuarioControlers.UsuarioControlerInteracao;
import HuntApi.Model.QuestGeolocalizada;

public class CadastroQuest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_quest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button adicionarEtapa = (Button)findViewById(R.id.AdicionarEtapaButton);
        assert adicionarEtapa != null;
        if (adicionarEtapa != null) {
            adicionarEtapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QuestGeolocalizada quest = new QuestGeolocalizada();
                    EditText editName = (EditText)findViewById(R.id.editNomeQuest);
                    EditText editDescricao = (EditText)findViewById(R.id.EditQuestDescricao);
                    quest.setNome(editName.getText().toString());
                    quest.setDescricao(editDescricao.getText().toString());
                    quest.setUsuario(UsuarioControlerInteracao.getUsuarioSessao());
                    quest.setCordenada(HuntApiGoogleServiceGerente.getPosicaoAtual());
                    Intent intent = new Intent(CadastroQuest.this,cadastroEtapa.class);
                    cadastroEtapa.quest = quest;
                    intent.putExtra("posicao",0);
                    startActivity(intent);
                    //finish();
                }
            });
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
