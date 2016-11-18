package HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao;

import java.util.ArrayList;

import HuntApi.ControleComunicacaoServidor.Utilities.UrlChamadaServidor;
import HuntApi.Model.CoordenadaGeografica;

/**
 * Created by Iury on 1/6/2016.
 */
public class CoordenadasHttpController {
    UrlChamadaServidor urlServidor;
    CoordenadaHttpPost cordenadaHttpPost;
    CoordenadaHttpGet coordenadaHttpGet;

    public CoordenadasHttpController() {
        urlServidor = new UrlChamadaServidor();
        cordenadaHttpPost = new CoordenadaHttpPost();
        coordenadaHttpGet = new CoordenadaHttpGet();
    }

    public ArrayList<CoordenadaGeografica> getTodasCordenadas() {

        return coordenadaHttpGet.getTodasCordenadas();
    }

    public void adicionarCordenada(CoordenadaGeografica cordenada) {
        cordenadaHttpPost.addCordenada(cordenada);
    }

    public void removerCordenada(CoordenadaGeografica cordenada) {
        cordenadaHttpPost.removeCordenada(cordenada);
    }

}
