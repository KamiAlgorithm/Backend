**Sistema de Gestión de Usuarios — Backend Java**
Especificación técnica y operativa del sistema.

---

### Propósito del sistema

Backend para gestión de usuarios con interfaz web y API REST.
Implementa persistencia en MySQL, validación de datos y arquitectura en capas orientada a separación estricta de responsabilidades.

El sistema permite:

* Registro de usuarios desde interfaz web o API
* Consulta de usuarios
* Validación de integridad de datos
* Persistencia transaccional
* Integración con sistemas externos mediante JSON

Arquitectura diseñada bajo patrón MVC con capa de acceso a datos (DAO).

---

### Alcance funcional

Capacidades implementadas:

* Creación de usuarios (Web / API)
* Consulta de usuarios (Web / API)
* Validación de campos obligatorios
* Persistencia en base de datos relacional
* Serialización JSON
* Arquitectura modular desacoplada
* Documentación técnica y de pruebas

El sistema no implementa autenticación, autorización ni operaciones de modificación/eliminación.

---

### Arquitectura del sistema

Flujo de ejecución:

```
Cliente (Navegador / Postman)
        ↓
Servlet (controladores)
        ↓
Capa de servicio (lógica de negocio)
        ↓
DAO (persistencia)
        ↓
MySQL (almacenamiento)
```

Responsabilidades por capa:

* **Cliente** → interacción usuario / consumo API
* **Servlet** → control de solicitudes HTTP
* **Service** → reglas de negocio y validación
* **DAO** → operaciones SQL
* **Base de datos** → persistencia

Separación estricta entre presentación, lógica y acceso a datos.

---

### Tecnologías utilizadas

Backend:

* Java 21
* Jakarta Servlet API 6.0
* JSP
* Maven

Persistencia:

* MySQL 8.3
* MySQL Connector/J

Herramientas:

* Apache Tomcat 10+
* Git
* Postman
* IntelliJ IDEA / NetBeans

Librerías:

* Gson 2.10.1 (serialización JSON)

---

### Estructura del proyecto

```
ProyectoBackend/

src/main/java/
  api/            → endpoints REST
  controller/     → controladores JSP
  service/        → lógica de negocio
  dao/            → acceso a datos
  model/          → entidades
  util/           → utilidades y conexión BD

src/main/webapp/
  formUsuario.jsp
  listaUsuario.jsp
  index.jsp
  WEB-INF/web.xml

database.sql
pom.xml
DOCUMENTO_TECNICO.md
API_DOCUMENTATION.md
ACTA_PRUEBAS.md
```

La estructura refleja aislamiento funcional y trazabilidad del flujo de ejecución.

---

### Requisitos de ejecución

Entorno mínimo:

* JDK 21+
* Apache Tomcat 10.1+
* MySQL 8+
* Maven 3.8+
* Git

Verificación:

```
java -version
mysql --version
mvn -version
git --version
```

---

### Preparación del entorno

Repositorio:

```bash
git clone https://github.com/KamiAlgorithm/Backend
cd ProyectoBackend
```

---

### Configuración de base de datos

Inicialización:

```sql
CREATE DATABASE IF NOT EXISTS proyecto;
USE proyecto;

CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

Configuración de conexión:

Archivo:

```
src/main/java/util/DBConnection.java
```

Parámetros:

```java
private static final String URL = "jdbc:mysql://localhost:3306/proyecto";
private static final String USER = "root";
private static final String PASS = "contraseña";
```

---

### Compilación

Terminal:

```bash
mvn clean install
```

Alternativa: build desde IDE.

---

### Despliegue en servidor

Métodos válidos:

* Despliegue desde IDE (configuración Tomcat local)
* Copia directa del archivo WAR en `/webapps`

Ejecución manual:

```bash
cp target/ProyectoBackend.war /tomcat/webapps/
startup.sh  # Linux/Mac
startup.bat # Windows
```

---

### Verificación operativa

Interfaz web:

```
http://localhost:8080/ProyectoBackend/index.jsp
```

API REST:

```
http://localhost:8080/ProyectoBackend/api/usuarios
```

Respuesta JSON válida confirma operación correcta.

---

### Interfaces disponibles

Aplicación web:

* `/index.jsp` → inicio
* `/formUsuario.jsp` → registro
* `/listaUsuario.jsp` → consulta

API REST:

```
POST /api/usuarios
GET  /api/usuarios
```

---

### Validación funcional básica

Flujo web:

1. Acceso a formulario
2. Registro de usuario
3. Verificación en lista

Flujo API:

* POST crea usuario
* GET devuelve colección JSON

---

### Documentación del proyecto

* Documento técnico del sistema
* Especificación de API
* Acta de pruebas
* Guía de resolución de problemas

Cada documento valida una dimensión distinta del sistema: diseño, interfaz, calidad y operación.

---

### Manejo de fallos

Error 404:

* Aplicación no desplegada o URL incorrecta
* Verificar ejecución de Tomcat y contexto

Error 500:

* Fallo en conexión a base de datos
* Verificar credenciales y existencia de esquema

Diagnóstico base de datos:

```sql
SHOW DATABASES;
USE proyecto;
SHOW TABLES;
```

Logs del servidor:

```
tomcat/logs/catalina.out
```

---

### Control de versiones

Evolución del sistema organizada por etapas:

* estructura del backend
* interfaz JSP
* validaciones
* implementación API
* pruebas y documentación
* integración final

Operaciones Git esenciales:

```bash
git log --oneline
git status
git add .
git commit -m "cambio"
git push origin main
```

---

### Contexto académico

Proyecto formativo orientado a integración de tecnologías orientadas a servicios.

Competencias demostradas:

* integración de módulos
* aplicación de estándares de codificación
* desarrollo de servicios web
* pruebas de integración
* construcción de sistema completo en capas

---

### Estado del sistema

Implementación funcional estable.
Arquitectura consistente.
Separación de responsabilidades validada.
Sistema apto para ejecución en entorno académico y base para extensión futura.
