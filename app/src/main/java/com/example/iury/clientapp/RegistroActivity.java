package com.example.iury.clientapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import HuntApi.ControleInteracaoJogo.UsuarioControlers.UsuarioControlerInteracao;
import HuntApi.Model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button botaoRegistrar = (Button) findViewById(R.id.buttonRegistrar);
        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioControlerInteracao usuarioControlerInteracao = new UsuarioControlerInteracao();
                EditText valorCompo = (EditText) findViewById(R.id.editTextName);
                Usuario usuario = new Usuario();
                usuario.setName(valorCompo.getText().toString());
                valorCompo = (EditText) findViewById(R.id.editTextCep);
                usuario.setCep(valorCompo.getText().toString());
                valorCompo = (EditText) findViewById(R.id.editTextEndereco);
                usuario.setEndereco(valorCompo.getText().toString());
                valorCompo = (EditText) findViewById(R.id.editTextNacionalidade);
                usuario.setNacionalidade(valorCompo.getText().toString());
                valorCompo = (EditText) findViewById(R.id.editTextSenha);
                usuario.setSenha(valorCompo.getText().toString());
                valorCompo = (EditText) findViewById(R.id.editTextEmail);
                usuario.setEmail(valorCompo.getText().toString());
                usuarioControlerInteracao.adicionarUsuario(usuario);
            }
        });
    }

}
