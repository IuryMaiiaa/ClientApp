package HuntApi.ControleComunicacaoServidor.ControleGeolocalizacao;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import HuntApi.Model.CoordenadaGeografica;

/**
 * Created by Iury on 3/24/2016.
 */
public class BuscaCordenadaProximas extends AsyncTask<String, Void, ArrayList> {
    private static String urlListarProximas = "listarProximas";

    public BuscaCordenadaProximas() {

    }

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

    private ArrayList<CoordenadaGeografica> tranformarJsonCordenadas(String dado) {
        ArrayList<CoordenadaGeografica> cordenadas = new ArrayList<CoordenadaGeografica>();

        try {
            JSONArray jsonCordenadas = new JSONArray(dado);
            JSONObject jsonObject;

            for(int cont=0;cont<jsonCordenadas.length();cont++) {
                jsonObject = new JSONObject(jsonCordenadas.getString(cont));

                CoordenadaGeografica cordenada = new CoordenadaGeografica();
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
