package HuntApi.ControleComunicacaoServidor.Utilities;

import HuntApi.Model.Etapa;
import HuntApi.Model.QuestGeolocalizada;

/**
 * Created by Iury on 3/15/2016.
 */
public class QuestReferenciaCircular {

    public QuestGeolocalizada removendoReferenciasCirculares(QuestGeolocalizada quest) {
        for(Etapa etapa: quest.getEtapas()) {
            etapa.setQuestGeolocalizada(null);
        }
        return quest;
    }

    public QuestGeolocalizada adicionarReferencaisCirculares(QuestGeolocalizada quest) {
        for(Etapa etapa : quest.getEtapas()) {
            etapa.setQuestGeolocalizada(quest);
        }
        return quest;
    }

}
