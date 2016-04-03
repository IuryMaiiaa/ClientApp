package HuntApi.ControleInteracaoJogo.UsuarioControlers;

import HuntApi.ControleComunicacaoServidor.ControleUsuario.UsuarioHttpController;
import HuntApi.Model.Usuario;

/**
 * Created by Iury on 4/3/2016.
 */
public class UsuarioControlerInteracao {
    UsuarioHttpController usuarioHttpController;

    public UsuarioControlerInteracao () {
        usuarioHttpController = new UsuarioHttpController();
    }

    public void adicionarUsuario (Usuario usuario) {
        usuarioHttpController.adicionarUsuario(usuario);
    }
}
