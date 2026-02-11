package dao;

import model.Usuario;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void guardar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre, email) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.executeUpdate();
            
            System.out.println("Usuario guardado: " + usuario.getNombre());

        } catch (Exception e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
            throw new RuntimeException("Error al guardar usuario en BD", e);
        }
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                lista.add(u);
            }
            
            System.out.println("Usuarios encontrados: " + lista.size());

        } catch (Exception e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
            throw new RuntimeException("Error al listar usuarios de BD", e);
        }

        return lista;
    }
}
