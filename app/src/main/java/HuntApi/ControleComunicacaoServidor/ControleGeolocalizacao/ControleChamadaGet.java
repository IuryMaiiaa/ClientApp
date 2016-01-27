package HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import HuntApi.Model.CordenadaGeografica;

/**
 * Created by Iury on 1/2/2016.
 */
public class ControleChamadaGet extends AsyncTask<String, Void, ArrayList> {

    @Override
    protected ArrayList doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection = null;
        String dado;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);


            BufferedReader br = new BufferedReader(isw);

            dado = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            urlConnection.disconnect();
        }

        return tranformarJsonCordenadas(dado);
    }

    private ArrayList<CordenadaGeografica> tranformarJsonCordenadas(String dado) {
        ArrayList<CordenadaGeografica> cordenadas = new ArrayList<CordenadaGeografica>();

        try {
            JSONArray jsonCordenadas = new JSONArray(dado);
            JSONObject jsonObject;

            for(int cont=0;cont<jsonCordenadas.length();cont++) {
                jsonObject = new JSONObject(jsonCordenadas.getString(cont));

                CordenadaGeografica cordenada = new CordenadaGeografica();
                cordenada.setID(jsonObject.getInt("id"));
                cordenada.setLat(jsonObject.getDouble("lat"));
                cordenada.setLon(jsonObject.getDouble("lon"));

                cordenadas.add(cordenada);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cordenadas;
    }

}
