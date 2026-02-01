package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {

    private UsuarioDAO dao = new UsuarioDAO();

    public void crearUsuario(Usuario usuario) {
        dao.guardar(usuario);
    }
}
