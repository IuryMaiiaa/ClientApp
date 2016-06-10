package HuntApi.ControleInteracaoJogo.UsuarioControlers;

import HuntApi.ControleComunicacaoServidor.ControleUsuario.UsuarioHttpController;
import HuntApi.Model.Usuario;

/**
 * Created by Iury on 4/3/2016.
 */
public class UsuarioControlerInteracao {
    private UsuarioHttpController usuarioHttpController;
    private static Usuario usuarioSessao;

    public UsuarioControlerInteracao () {
        usuarioSessao = null;
        usuarioHttpController = new UsuarioHttpController();
    }

    public void adicionarUsuario (Usuario usuario) {
        usuarioHttpController.adicionarUsuario(usuario);
    }

    public static Usuario validarUsuario(String email, String senha) {
        UsuarioHttpController usuarioController = new UsuarioHttpController();
        usuarioSessao = usuarioController.validarUsuario(email,senha);
        return usuarioSessao;
    }

    public static Usuario getUsuarioSessao() {
        return usuarioSessao;
    }
}
