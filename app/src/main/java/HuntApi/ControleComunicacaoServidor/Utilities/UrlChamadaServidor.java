package HuntApi.ControleComunicacaoServidor.Utilities;

/**
 * Created by Iury on 1/6/2016.
 */
public class UrlChamadaServidor {
    private static final String servidorIPorta = "http://10.0.157.176:8081";
    private static final String servidorUrlCordenadas = "/Servidor/Cordenada/";
    private static final String servidorUrlUsuario = "/Servidor/Usuario/";
    private static final String servidorUrlQuest = "/Servidor/Quest/";

    public String getUrlCordenada() {
        return servidorIPorta + servidorUrlCordenadas;
    }

    public String getUrlUsuario() {
        return  servidorIPorta + servidorUrlUsuario;
    }

    public String getUrlQuest() {
        return servidorIPorta + servidorUrlQuest;
    }


}
