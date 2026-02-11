package api;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import service.UsuarioService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/usuarios")
public class UsuarioApiServlet extends HttpServlet {

    private final UsuarioService service = new UsuarioService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<Usuario> usuarios = service.listarUsuarios();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(gson.toJson(usuarios));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // 1. Leer el body COMPLETO como texto
        BufferedReader reader = request.getReader();
        StringBuilder body = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        // 2. Validar body vacío
        if (body.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Body vacío");
            return;
        }

        // 3. Convertir JSON → Usuario
        Usuario u = gson.fromJson(body.toString(), Usuario.class);

        // 4. Validaciones de negocio
        if (u == null ||
            u.getNombre() == null || u.getNombre().isBlank() ||
            u.getEmail() == null || u.getEmail().isBlank()) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos inválidos");
            return;
        }

        // 5. Persistir
        service.crearUsuario(u);

        // 6. Respuesta correcta REST
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(gson.toJson(u));
        out.flush();
    }
}
