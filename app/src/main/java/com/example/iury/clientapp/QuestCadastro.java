package com.example.iury.clientapp;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuestCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button confirmacao = (Button) findViewById(R.id.ConfirmacaoPassos);
        assert confirmacao != null;
        confirmacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //LinearLayout layout = (LinearLayout) view.inflate(view.getContext(), R.layout.activity_quest_cadastro,null);

                LinearLayout layout = (LinearLayout) view.findViewById(R.id.layoutLinerMaps);
                TextView NomeDoPasso = new TextView(view.getContext());
                NomeDoPasso.setLayoutParams(new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                layout.addView(NomeDoPasso);
                setContentView(view);
            }
        });

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
