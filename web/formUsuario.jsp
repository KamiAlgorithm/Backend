<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Formulario Usuario</title>
</head>
<body>

<h2>Crear Usuario</h2>

<form action="usuarios" method="post">
    <input type="text" name="nombre" placeholder="Nombre" required>
    <br><br>
    <input type="email" name="email" placeholder="Email" required>
    <br><br>
    <button type="submit">Guardar</button>
</form>

</body>
</html>
