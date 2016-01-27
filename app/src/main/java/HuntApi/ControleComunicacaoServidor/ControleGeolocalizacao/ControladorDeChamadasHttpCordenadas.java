package HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import HuntApi.ControleComunicacaoServidor.UrlChamadaServidor;
import HuntApi.Model.CordenadaGeografica;
import HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao.ControleChamadaGet;

/**
 * Created by Iury on 1/6/2016.
 */
public class ControladorDeChamadasHttpCordenadas {
    UrlChamadaServidor urlServidor = new UrlChamadaServidor();

    public ArrayList<CordenadaGeografica> getTodasCordenadas() {
        ControleChamadaGet chamarCordenadas = new ControleChamadaGet();
        ArrayList<CordenadaGeografica> cordenadas = new ArrayList<CordenadaGeografica>();
        chamarCordenadas.execute(urlServidor.getUrlCordenada() + "listarTodos");

        try {
            cordenadas = chamarCordenadas.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return cordenadas;
    }

    public void adicionarCordenada(CordenadaGeografica cordenada) {
        ControleChamadaPost addCordenada = new ControleChamadaPost();
        addCordenada.addCordenada(urlServidor.getUrlCordenada() + "adicionarCordenada", cordenada);
    }

}
