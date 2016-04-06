package HuntApi.ControleComunicacaoServidor.ControleUsuario;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import HuntApi.ControleComunicacaoServidor.Utilities.UrlChamadaServidor;
import HuntApi.ControleComunicacaoServidor.Utilities.UsuarioReferenciaCircular;
import HuntApi.Model.Usuario;

/**
 * Created by Iury on 3/14/2016.
 */
public class UsuarioControllerPost extends AsyncTask<String, Void, String> {
    private static String urlUsuarioAdicionar = "addUsuario";
    private static String urlUsuarioRemover = "removeUsuario";
    private static String urlUsuarioUpdate = "updateUsuario";

    private UsuarioReferenciaCircular usuarioReferenciaCircular;
    private UrlChamadaServidor urlServidor;
    private Usuario usuario;
    private Gson gson;


    public UsuarioControllerPost() {
        gson = new Gson();
        usuarioReferenciaCircular = new UsuarioReferenciaCircular();
        urlServidor = new UrlChamadaServidor();
    }


    public void adicionarUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.execute(urlUsuarioAdicionar);
    }

    public void removerUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.execute(urlUsuarioRemover);
    }

    public void updateUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.execute(urlUsuarioUpdate);
    }

    @Override
    protected String doInBackground(String... strings) {
        usuario = usuarioReferenciaCircular.removendoReferenciasCirculares(usuario);
        String message = gson.toJson(usuario);

        try {
            URL url = new URL(urlServidor.getUrlUsuario() + strings[0]);
            HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout( 100000 );
            conn.setConnectTimeout( 150000 );
            conn.setRequestMethod("POST");

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);

            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            conn.connect();

            OutputStream wr = new BufferedOutputStream(conn.getOutputStream());
            wr.write(message.getBytes());
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            wr.close();
            rd.close();
            conn.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
