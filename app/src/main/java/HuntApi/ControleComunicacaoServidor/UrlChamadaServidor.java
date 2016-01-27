package HuntApi.ControleComunicacaoServidor;

/**
 * Created by Iury on 1/6/2016.
 */
public class UrlChamadaServidor {
    private static final String servidorIPorta = "http://192.168.0.100:8080";
    private static final String servidorUrlCordenadas = "/Servidor/cordenada/";

    public String getUrlCordenada() {
        return servidorIPorta + servidorUrlCordenadas;
    }


}
