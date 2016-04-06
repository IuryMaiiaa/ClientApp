package HuntApi.ControleComunicacaoServidor.ControleUsuario;

import android.os.AsyncTask;

import com.google.gson.Gson;

import HuntApi.ControleComunicacaoServidor.Utilities.UrlChamadaServidor;
import HuntApi.ControleComunicacaoServidor.Utilities.UsuarioReferenciaCircular;
import HuntApi.Model.Usuario;

/**
 * Created by Iury on 3/15/2016.
 */
public class UsuarioControllerGet extends AsyncTask<String, Void, String> {
    private static String urlUsuarioValidacao = "getUsuario";

    private UsuarioReferenciaCircular usuarioReferenciaCircular;
    private UrlChamadaServidor urlServidor;
    private Usuario usuario;
    private Gson gson;

    public UsuarioControllerGet() {
        usuarioReferenciaCircular = new UsuarioReferenciaCircular();
        urlServidor = new UrlChamadaServidor();
        gson = new Gson();
    }

    public Usuario getUsuario(String email,int senha) {
        UsuarioValidacao usarioValidacao = new UsuarioValidacao();
        Usuario usuario = usarioValidacao.getUsuario(urlServidor+urlUsuarioValidacao,email,senha);
        usuario = usuarioReferenciaCircular.adicionandoReferenciasCirculares(usuario);
        return usuario;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }


}
