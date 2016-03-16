package HuntApi.ControleComunicacaoServidor.ControleQuest;

import HuntApi.ControleComunicacaoServidor.UrlChamadaServidor;
import HuntApi.Model.QuestGeolocalizada;

/**
 * Created by Iury on 3/14/2016.
 */
public class QuestHttpController {
    private QuestControllerPost questControllerPost;
    private QuestControllerGet questControllerGet;

    public QuestHttpController() {
        questControllerPost = new QuestControllerPost();
        questControllerGet = new QuestControllerGet();
    }
    public void adicionarQuest(QuestGeolocalizada quest) {
        questControllerPost.adicionarQuest(quest);
    }

    public void removerQuest(QuestGeolocalizada quest) {
        questControllerPost.removerQuest(quest);
    }

    public void atualizarQuest(QuestGeolocalizada quest) {
        questControllerPost.atualizarQuest(quest);
    }
}
