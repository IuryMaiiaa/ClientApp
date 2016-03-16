package HuntApi.ControleComunicacaoServidor.ControleUsuario;

import HuntApi.Model.Usuario;

/**
 * Created by Iury on 3/14/2016.
 */
public class UsuarioHttpController {
    private UsuarioControllerGet usuarioControllerGet;
    private UsuarioControllerPost usuarioControllerPost;

    public UsuarioHttpController() {
        usuarioControllerGet = new UsuarioControllerGet();
        usuarioControllerPost = new UsuarioControllerPost();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarioControllerPost.adicionarUsuario(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuarioControllerPost.removerUsuario(usuario);
    }

    public void updateUsuario(Usuario usuario) {
        usuarioControllerPost.updateUsuario(usuario);
    }

}
