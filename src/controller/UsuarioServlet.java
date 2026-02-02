package controller;

import model.Usuario;
import service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setEmail(email);

        usuarioService.crearUsuario(u);

        response.sendRedirect("usuarios");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> lista = usuarioService.listarUsuarios();
        request.setAttribute("usuarios", lista);
        request.getRequestDispatcher("listaUsuarios.jsp")
                .forward(request, response);
    }
}
