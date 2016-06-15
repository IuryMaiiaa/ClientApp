package HuntApi.ControleComunicacaoServidor.ControleQuest;

import android.os.AsyncTask;
import android.util.Log;

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

import HuntApi.ControleComunicacaoServidor.Utilities.QuestReferenciaCircular;
import HuntApi.ControleComunicacaoServidor.Utilities.UrlChamadaServidor;
import HuntApi.Model.QuestGeolocalizada;

/**
 * Created by Iury on 3/14/2016.
 */
public class QuestControllerPost extends AsyncTask<String, Void, String> {
    private static String urlQuestAdicionar = "addQuest";
    private static String urlQuestRemover = "removeQuest";
    private static String urlQuestUpdate = "updateQuest";

    private QuestReferenciaCircular questReferenciaCircular;
    private QuestGeolocalizada quest;
    private UrlChamadaServidor urlServidor;
    private Gson gson;

    public QuestControllerPost() {
        gson = new Gson();
        urlServidor = new UrlChamadaServidor();
        questReferenciaCircular = new QuestReferenciaCircular();
    }


    public void adicionarQuest(QuestGeolocalizada quest) {
        this.quest = quest;
        this.execute(urlQuestAdicionar);
    }

    public void removerQuest(QuestGeolocalizada quest) {
        this.quest = quest;
        this.execute(urlQuestRemover);
    }

    public void atualizarQuest(QuestGeolocalizada quest) {
        this.quest = quest;
        this.execute(urlQuestUpdate);
    }

    @Override
    protected String doInBackground(String... strings) {
        quest = questReferenciaCircular.removendoReferenciasCirculares(quest);
        String message = gson.toJson(quest);
        try {
            URL url = new URL(urlServidor.getUrlQuest() + strings[0]);
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
            Log.d("quest","quest nome: " + quest.getNome() + " Url: " + conn.getURL().toString());
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
