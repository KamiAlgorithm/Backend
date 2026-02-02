<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Usuario" %>

<html>
<head>
    <title>Lista de Usuarios</title>
</head>
<body>

<h2>Usuarios Registrados</h2>

<ul>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    if (usuarios != null) {
        for (Usuario u : usuarios) {
%>
    <li><%= u.getNombre() %> - <%= u.getEmail() %></li>
<%
        }
    }
%>
</ul>

<a href="formUsuario.jsp">Crear nuevo usuario</a>

</body>
</html>
