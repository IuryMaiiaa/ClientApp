package HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import HuntApi.ControleComunicacaoServidor.UrlChamadaServidor;
import HuntApi.Model.CordenadaGeografica;

/**
 * Created by Iury on 1/8/2016.
 */
public class ControleChamadaPost extends AsyncTask<String, Void, String> {
    CordenadaGeografica cordenada;
    URL url;
    URLConnection conn = null;

    @Override
    protected String doInBackground(String... strings) {
        byte[] postData;
        try {


            String data = "?" + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(cordenada.getLat()), "UTF-8");
            data += "&" + URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(cordenada.getLon()), "UTF-8");

            Log.d("Client", strings[0] + data);
            URL url = new URL(strings[0]+data);
            //urlConnection.connect();

            conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            wr.close();
            rd.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        Log.d("Client",cordenada.toString() + "aqui");
        return  null;
    }

    public void addCordenada(String url, CordenadaGeografica cordenada) {
        this.cordenada = cordenada;
        this.execute(url);
    }
}
