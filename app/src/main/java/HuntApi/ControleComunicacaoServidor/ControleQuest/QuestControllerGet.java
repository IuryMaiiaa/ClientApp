package HuntApi.ControleComunicacaoServidor.ControleQuest;

import java.util.ArrayList;

import HuntApi.Model.CoordenadaGeografica;
import HuntApi.Model.QuestGeolocalizada;

/**
 * Created by Iury on 3/14/2016.
 */
public class QuestControllerGet {
    private ListarQuestsProximas listarQuestsProximas;
    private ListaTodasQuests listarTodasQuests;

    public QuestControllerGet() {
        listarQuestsProximas = new ListarQuestsProximas();
        listarTodasQuests = new ListaTodasQuests();
    }

    public ArrayList<QuestGeolocalizada> getTodasQuest() {
        return null;
    }

    public ArrayList<QuestGeolocalizada> getQuestProxima(CoordenadaGeografica cordenada, int raio) {
        return (ArrayList<QuestGeolocalizada>) listarQuestsProximas.listarProximas(cordenada,raio);
    }
}
