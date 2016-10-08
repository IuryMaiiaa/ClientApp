package HuntApi.ControleComunicacaoServidor.ControleUsuario;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import HuntApi.ControleComunicacaoServidor.Utilities.UrlChamadaServidor;
import HuntApi.Model.Usuario;

/**
 * Created by Iury on 3/16/2016.
 */
public class UsuarioValidacao extends AsyncTask<String, Void, Usuario> {
    private String email;
    private String senha;
    private String getUsuarioUrl = "getUsuario";

    private UrlChamadaServidor urlChamadaServidor;
    private Gson gson;

    public UsuarioValidacao() {
        gson = new Gson();
        urlChamadaServidor = new UrlChamadaServidor();
    }


    @Override
    protected Usuario doInBackground(String... strings) {
        Usuario usuario = new Usuario();
        try {
            URL url = new URL(urlChamadaServidor.getUrlUsuario() + getUsuarioUrl +"/"+email+"/"+senha);
            HttpURLConnection conn  = (HttpURLConnection) url.openConnection();

            InputStream in = conn.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            BufferedReader br = new BufferedReader(isw);

            String dado = br.readLine();

            usuario = gson.fromJson(dado,Usuario.class);
            //Log.d("usuario", usuario.getMinhasQuests().get(0).getNome());
            br.close();
            conn.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public Usuario getUsuario(String url, String email, String senha) {
        this.email = email;
        this.senha = senha;
        this.execute(url);
        Usuario usuario = new Usuario();

        try {
            usuario = this.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
