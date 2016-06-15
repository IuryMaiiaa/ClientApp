package com.example.iury.clientapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao.CordenadasHttpController;
import HuntApi.ControleInteracaoJogo.UsuarioControlers.UsuarioControlerInteracao;
import HuntApi.Model.CordenadaGeografica;
import HuntApi.Model.Usuario;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button botao = (Button)findViewById(R.id.login);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioControlerInteracao usuarioControlerInteracao = new UsuarioControlerInteracao();
                EditText valorCompoEmail = (EditText) findViewById(R.id.LoginEdit);
                EditText valorCompoSenha = (EditText) findViewById(R.id.SenhaEdit);
                Usuario usuario = usuarioControlerInteracao.validarUsuario(valorCompoEmail.getText().toString(),
                                                                            valorCompoSenha.getText().toString());

                if(usuario!= null) {
                    Log.d("client",usuario.getEmail());
                    Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("client","usuario nulo");
                }

            }
        });

        Button botaoPost = (Button)findViewById(R.id.registrar);
        botaoPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
