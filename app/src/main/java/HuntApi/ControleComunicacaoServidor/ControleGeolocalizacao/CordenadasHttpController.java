package HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import HuntApi.ControleComunicacaoServidor.Utilities.UrlChamadaServidor;
import HuntApi.Model.CordenadaGeografica;

/**
 * Created by Iury on 1/6/2016.
 */
public class CordenadasHttpController {
    UrlChamadaServidor urlServidor;
    CordenadaHttpPost cordenadaHttpPost;
    CordenadaHttpGet cordenadaHttpGet;

    public CordenadasHttpController() {
        urlServidor = new UrlChamadaServidor();
        cordenadaHttpPost = new CordenadaHttpPost();
        cordenadaHttpGet = new CordenadaHttpGet();
    }

    public ArrayList<CordenadaGeografica> getTodasCordenadas() {

        return cordenadaHttpGet.getTodasCordenadas();
    }

    public void adicionarCordenada(CordenadaGeografica cordenada) {
        cordenadaHttpPost.addCordenada(cordenada);
    }

    public void removerCordenada(CordenadaGeografica cordenada) {
        cordenadaHttpPost.removeCordenada(cordenada);
    }

}
