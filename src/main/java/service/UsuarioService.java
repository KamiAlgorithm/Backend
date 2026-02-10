package service;

import dao.UsuarioDAO;
import model.Usuario;

import java.util.List;

public class UsuarioService {

    private UsuarioDAO dao = new UsuarioDAO();

    public void crearUsuario(Usuario usuario) {
        dao.guardar(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return dao.listar();
    }
}
