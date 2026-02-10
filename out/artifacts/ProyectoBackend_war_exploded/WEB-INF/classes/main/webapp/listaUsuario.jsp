<%@ page import="java.util.List" %>
<%@ page import="model.Usuario" %>

<html>
<head>
    <title>Lista de Usuarios</title>
</head>
<body>

<h2>Usuarios registrados</h2>

<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Email</th>
    </tr>

    <%
        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
        if (usuarios != null) {
            for (Usuario u : usuarios) {
    %>
    <tr>
        <td><%= u.getNombre() %></td>
        <td><%= u.getEmail() %></td>
    </tr>
    <%
            }
        }
    %>

</table>

</body>
</html>
