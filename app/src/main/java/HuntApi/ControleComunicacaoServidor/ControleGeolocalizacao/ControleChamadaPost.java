package HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import HuntApi.ControleComunicacaoServidor.UrlChamadaServidor;
import HuntApi.Model.CordenadaGeografica;

/**
 * Created by Iury on 1/8/2016.
 */
public class ControleChamadaPost extends AsyncTask<String, Void, String> {
    CordenadaGeografica cordenada;
    URL url;
    HttpURLConnection urlConnection = null;

    @Override
    protected String doInBackground(String... strings) {

        try {

            Log.d("Client", strings[0]);
            URL url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Request-Method", "POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("charset", "utf-8");
            urlConnection.connect();

            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
            String parametros = URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(cordenada.getLat()), "UTF-8");
            parametros+= "&" + URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(cordenada.getLon()), "UTF-8");
            writer.write(URLEncoder.encode(parametros,"UTF-8"));
            Log.d("Client", parametros);

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            urlConnection.disconnect();
        }
        Log.d("Client",cordenada.toString() + "aqui");
        return  null;
    }

    public void addCordenada(String url, CordenadaGeografica cordenada) {
        this.cordenada = cordenada;
        this.execute(url);
    }
}
