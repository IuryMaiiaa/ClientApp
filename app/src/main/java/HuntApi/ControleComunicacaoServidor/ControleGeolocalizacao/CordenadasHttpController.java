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

        ArrayList<CordenadaGeografica> cordenadas = new ArrayList<CordenadaGeografica>();
        cordenadaHttpGet.execute(urlServidor.getUrlCordenada() + "listarTodos");

        try {
            cordenadas = cordenadaHttpGet.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return cordenadas;
    }

    public void adicionarCordenada(CordenadaGeografica cordenada) {
        cordenadaHttpPost.addCordenada(cordenada);
    }

}
