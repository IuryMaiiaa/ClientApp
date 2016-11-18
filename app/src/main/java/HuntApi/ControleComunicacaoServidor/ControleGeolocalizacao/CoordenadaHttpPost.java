package HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao;

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
import java.net.URLConnection;

import HuntApi.ControleComunicacaoServidor.Utilities.UrlChamadaServidor;
import HuntApi.Model.CoordenadaGeografica;

/**
 * Created by Iury on 1/8/2016.
 */
public class CoordenadaHttpPost extends AsyncTask<String, Void, String> {
    public static String addCordenada = "addCordenada";
    public static String removeCordenada = "removeCordenada";
    public static String updateCordenada = "updateCordenada";

    CoordenadaGeografica cordenada;
    private Gson gson;

    UrlChamadaServidor urlChamadaServidor;
    URLConnection conn = null;

    public CoordenadaHttpPost() {
        gson = new Gson();
        urlChamadaServidor = new UrlChamadaServidor();
    }

    public void addCordenada(CoordenadaGeografica cordenada) {
        this.cordenada = cordenada;
        this.execute(addCordenada);
    }

    public void updateCordenada(CoordenadaGeografica cordenada) {
        this.cordenada = cordenada;
        this.execute(updateCordenada);
    }

    public void removeCordenada(CoordenadaGeografica cordenada) {
        this.cordenada = cordenada;
        this.execute(removeCordenada);
    }

    @Override
    protected String doInBackground(String... strings) {
        String message = gson.toJson(cordenada);

        try {
            URL url = new URL(urlChamadaServidor.getUrlCordenada() + strings[0]);
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
