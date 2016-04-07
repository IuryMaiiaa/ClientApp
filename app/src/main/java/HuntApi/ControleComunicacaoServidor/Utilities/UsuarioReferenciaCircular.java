package HuntApi.ControleComunicacaoServidor.Utilities;

import HuntApi.Model.QuestGeolocalizada;
import HuntApi.Model.Usuario;

/**
 * Created by Iury on 3/15/2016.
 */
public class UsuarioReferenciaCircular {
    private QuestReferenciaCircular questsReferenciaCircular;

    public UsuarioReferenciaCircular(){
        questsReferenciaCircular = new QuestReferenciaCircular();
    }

    public Usuario removendoReferenciasCirculares(Usuario usuario) {
        if(usuario != null) {
            if(usuario.getMinhasQuests() != null) {
                for(QuestGeolocalizada quest : usuario.getMinhasQuests()) {
                    quest.setUsuario(null);
                    quest = questsReferenciaCircular.removendoReferenciasCirculares(quest);
                }
            }
        }


        return usuario;
    }

    public Usuario adicionandoReferenciasCirculares(Usuario usuario) {
        if(usuario != null) {
            if(usuario.getMinhasQuests() != null) {
                for(QuestGeolocalizada quest : usuario.getMinhasQuests()) {
                    quest.setUsuario(usuario);
                    quest = questsReferenciaCircular.adicionarReferencaisCirculares(quest);
                }
            }
        }

        return usuario;
    }

}