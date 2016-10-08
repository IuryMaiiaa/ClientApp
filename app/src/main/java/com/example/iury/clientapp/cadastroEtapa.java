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

import HuntApi.ControleComunicacaoServidor.ControleQuest.QuestHttpController;
import HuntApi.Model.Etapa;
import HuntApi.Model.QuestGeolocalizada;

public class cadastroEtapa extends AppCompatActivity {
    public static QuestGeolocalizada quest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_etapa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button confirmacao = (Button) findViewById(R.id.ConcluirQuestButton);
        assert confirmacao != null;
        confirmacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarEtapa(getIntent());
                QuestHttpController questHttpController = new QuestHttpController();
                questHttpController.adicionarQuest(quest);
                Intent intent = new Intent(cadastroEtapa.this,MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button NovaEtapa = (Button) findViewById(R.id.NotaEtapaButton);
        assert NovaEtapa != null;
        NovaEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarEtapa(getIntent());
                Intent intent = new Intent(cadastroEtapa.this,cadastroEtapa.class);
                intent.putExtra("posicao",intent.getIntExtra("posicao",0)+1);
                startActivity(intent);
                finish();
            }
        });
    }

    public void adicionarEtapa(Intent intent) {
        EditText editDescriEditText = (EditText) findViewById(R.id.EditDescricaoEtapa);
        EditText editDica = (EditText) findViewById(R.id.editDica);
        EditText editPalavraChave = (EditText) findViewById(R.id.editPalavraChave);
        Etapa etapa = new Etapa();
        etapa.setQuestGeolocalizada(quest);
        etapa.setDescricao(editDescriEditText.getText().toString());
        etapa.setDica(editDica.getText().toString());
        etapa.setPalavraChave(editPalavraChave.getText().toString());
        etapa.setPosicao(intent.getIntExtra("posicao",0));
        quest.addEtapaPosicao(etapa,etapa.getPosicao());
    }

}
