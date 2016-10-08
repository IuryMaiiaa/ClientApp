package HuntApi.ControleComunicacaoServidor.ControleQuest;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import HuntApi.ControleComunicacaoServidor.Utilities.QuestReferenciaCircular;
import HuntApi.ControleComunicacaoServidor.Utilities.UrlChamadaServidor;
import HuntApi.Model.CordenadaGeografica;
import HuntApi.Model.QuestGeolocalizada;

/**
 * Created by Iury on 10/6/2016.
 */

public class ListaTodasQuests extends AsyncTask<String, Void, List<QuestGeolocalizada>> {
    private static String urlListarTodas = "listarTodos";

    private QuestReferenciaCircular questReferenciaCircular;
    private List<QuestGeolocalizada> quests;
    private UrlChamadaServidor urlServidor;
    private Gson gson;

    public ListaTodasQuests() {
        gson = new Gson();
        urlServidor = new UrlChamadaServidor();
        questReferenciaCircular = new QuestReferenciaCircular();
        quests = new ArrayList<QuestGeolocalizada>();
    }

    public List<QuestGeolocalizada> listarProximas(CordenadaGeografica cordenadaGeografica,int raio) {
        this.execute(urlListarTodas);

        try {
            quests = this.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return quests;
    }

    @Override
    protected List<QuestGeolocalizada> doInBackground(String... strings) {

        try {
            URL url = new URL(urlServidor.getUrlQuest() + strings[0]);
            HttpURLConnection conn  = (HttpURLConnection) url.openConnection();

            InputStream in = conn.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            BufferedReader br = new BufferedReader(isw);

            String dado = br.readLine();
            if (dado!=null && !dado.isEmpty()) {
                quests = tranformarJsonQuests(dado);
            }


            br.close();
            conn.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quests;
    }

    private ArrayList<QuestGeolocalizada> tranformarJsonQuests(String dado) {
        ArrayList<QuestGeolocalizada> questsJson = new ArrayList<QuestGeolocalizada>();

        try {
            JSONArray jsonQuests = new JSONArray(dado);
            JSONObject jsonObject;

            for(int cont=0;cont<jsonQuests.length();cont++) {
                jsonObject = new JSONObject(jsonQuests.getString(cont));

                QuestGeolocalizada quest = gson.fromJson(jsonObject.toString(),QuestGeolocalizada.class);
                quest = questReferenciaCircular.adicionarReferencaisCirculares(quest);

                questsJson.add(quest);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return questsJson;
    }
}
