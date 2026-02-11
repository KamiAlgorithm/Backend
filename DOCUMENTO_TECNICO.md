# DOCUMENTO TÉCNICO DEL SISTEMA

**Sistema de Gestión de Usuarios — Backend Java**

---

## Descripción del Sistema

Sistema backend orientado a la gestión persistente de usuarios mediante dos interfaces de acceso:

* interfaz web basada en JSP
* API REST para integración externa

El sistema permite crear y consultar usuarios garantizando separación de responsabilidades, consistencia de datos y comportamiento determinista.

### Capacidades implementadas

* interfaz web JSP para interacción directa
* API REST para consumo programático
* persistencia en MySQL
* arquitectura modular en capas con responsabilidades definidas
* validación básica de datos de entrada
* manejo controlado de errores

### Alcance funcional

El sistema implementa únicamente operaciones de creación y consulta.
No incluye autenticación, autorización ni operaciones avanzadas de gestión.

---

## Arquitectura del Sistema

El sistema implementa arquitectura en capas para aislar responsabilidades y reducir acoplamiento entre componentes.

### Modelo de flujo arquitectónico

```
┌─────────────┐
│   Cliente   │ (Navegador / Postman)
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Servlet    │ (Controller)
│  - JSP      │
│  - REST API │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│   Service   │ (Lógica de negocio)
└──────┬──────┘
       │
       ▼
┌─────────────┐
│     DAO     │ (Acceso a datos)
└──────┬──────┘
       │
       ▼
┌─────────────┐
│    MySQL    │ (Base de datos)
└─────────────┘
```

---

### Capas del sistema y responsabilidades

#### Capa de Presentación

Responsable únicamente de interacción con el cliente.

* **JSP:** renderizado de interfaz web.
* **REST API:** exposición de endpoints JSON.

No contiene lógica de negocio ni acceso directo a base de datos.

---

#### Capa de Control

Gestiona el flujo de ejecución.

* `UsuarioServlet.java` → manejo de formularios web.
* `UsuarioApiServlet.java` → manejo de solicitudes REST.

Responsabilidades:

* recepción de solicitudes HTTP
* transformación de datos
* delegación al Service

---

#### Capa de Negocio

Implementa reglas del sistema.

* `UsuarioService.java`

Responsabilidades:

* validación de datos
* control de operaciones permitidas
* coordinación de persistencia

No contiene lógica de infraestructura.

---

#### Capa de Datos

Acceso directo a persistencia.

* `UsuarioDAO.java`

Responsabilidades:

* ejecución de consultas SQL
* transformación de resultados

---

#### Capa de Persistencia

* MySQL como almacenamiento relacional.

Garantiza consistencia ACID y control estructural de datos.

---

## Tecnologías Utilizadas

### Backend

* **Java 21** → ejecución estable y soporte extendido.
* **Jakarta Servlet API 6.0** → control explícito del ciclo HTTP.
* **JSP** → generación directa de vistas.
* **Maven** → gestión de dependencias y construcción reproducible.

Criterio de selección: estabilidad y comportamiento predecible.

---

### Base de Datos

* **MySQL 8.3** → persistencia relacional consistente.
* **MySQL Connector/J** → integración JDBC.

---

### Herramientas de Desarrollo

* IntelliJ IDEA / NetBeans → desarrollo y depuración.
* Apache Tomcat 10+ → contenedor de ejecución.
* Git → control de versiones.
* Postman → validación de API.

---

### Librerías

* **Gson 2.10.1** → serialización y deserialización JSON.

---

## Estructura del Proyecto

Estructura organizada por responsabilidad funcional.

```
ProyectoBackend/
│
├── src/main/java/
│   ├── api/
│   │   └── UsuarioApiServlet.java
│   ├── controller/
│   │   └── UsuarioServlet.java
│   ├── service/
│   │   └── UsuarioService.java
│   ├── dao/
│   │   └── UsuarioDAO.java
│   ├── model/
│   │   └── Usuario.java
│   └── util/
│       └── DBConnection.java
│
├── src/main/webapp/
│   ├── formUsuario.jsp
│   ├── listaUsuario.jsp
│   ├── index.jsp
│   └── WEB-INF/web.xml
│
├── database.sql
├── pom.xml
├── README.md
├── API_DOCUMENTATION.md
└── ACTA_PRUEBAS.md
```

Organización orientada a mantener separación clara entre capas.

---

## Modelo de Datos

### Entidad principal

```
Usuario
- id (PK)
- nombre
- email
- fecha_creacion
```

El sistema opera sobre una única entidad persistente.

---

### Esquema de Base de Datos

**Tabla: usuario**

| Campo          | Tipo         | Restricciones               |
| -------------- | ------------ | --------------------------- |
| id             | INT          | PRIMARY KEY, AUTO_INCREMENT |
| nombre         | VARCHAR(100) | NOT NULL                    |
| email          | VARCHAR(100) | NOT NULL                    |
| fecha_creacion | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   |

No se implementa restricción de unicidad de email en esta versión.

---

## Endpoints del Sistema

### API REST

Base URL:

```
http://localhost:8080/ProyectoBackend/api
```

---

#### Crear Usuario

```
POST /usuarios
```

Entrada JSON:

```
{
  "nombre": "string",
  "email": "string"
}
```

Respuestas:

* 201 → usuario creado
* 400 → datos inválidos
* 500 → error interno

---

#### Listar Usuarios

```
GET /usuarios
```

Respuestas:

* 200 → lista JSON
* 500 → error interno

No se implementan filtros ni paginación.

---

### Interfaz Web

Base:

```
http://localhost:8080/ProyectoBackend
```

| Ruta                    | Función          |
| ----------------------- | ---------------- |
| /index.jsp              | acceso principal |
| /formUsuario.jsp        | creación         |
| /listaUsuario.jsp       | listado          |
| /usuarios?accion=crear  | operación POST   |
| /usuarios?accion=listar | operación GET    |

---

## Flujo de Datos

### Creación de Usuario — Web

```
usuario envía formulario
→ servlet recibe solicitud
→ service valida datos
→ DAO ejecuta INSERT
→ base de datos persiste
→ respuesta enviada
```

---

### Creación de Usuario — API

```
cliente envía JSON
→ servlet REST procesa
→ JSON convertido a objeto
→ validación
→ persistencia
→ respuesta HTTP
```

Flujo determinista sin pasos ocultos.

---

## Configuración del Sistema

### Requisitos Previos

* JDK 21+
* Tomcat 10.1+
* MySQL 8+
* Maven 3.8+

---

### Configuración de Base de Datos

Archivo:

```
src/main/java/util/DBConnection.java
```

```
jdbc:mysql://localhost:3306/proyecto
```

Credenciales definidas en código para entorno de desarrollo.

---

### Variables de Entorno

No requeridas.

---

## Instalación y Ejecución

### Instalación

```
git clone [URL]
cd ProyectoBackend
mysql -u root -p < database.sql
mvn clean install
```

Despliegue en Tomcat.

---

### Ejecución

Iniciar MySQL → iniciar Tomcat → acceder vía navegador o API.

---

## Pruebas

### Pruebas Ejecutadas

| ID | Tipo                   | Resultado |
| -- | ---------------------- | --------- |
| P1 | creación web           | aprobado  |
| P2 | listado web            | aprobado  |
| P3 | POST API               | aprobado  |
| P4 | GET API                | aprobado  |
| P5 | validación obligatoria | aprobado  |
| P6 | persistencia BD        | aprobado  |

---

### Herramientas

* Postman
* Navegador
* MySQL Workbench

---

## Seguridad

Implementado:

* PreparedStatement (prevención SQL injection)
* validación de entrada
* manejo de excepciones

No implementado:

* autenticación
* autorización
* HTTPS

Sistema destinado a entorno controlado.

---

## Mantenimiento y Soporte

### Logs

* Tomcat: `catalina.out`
* consola de aplicación

---

### Fallos comunes

| Problema | Acción                 |
| -------- | ---------------------- |
| 404      | verificar contexto     |
| 500      | revisar logs           |
| error BD | verificar credenciales |

---

## Versionamiento

Control mediante Git con commits incrementales por funcionalidad.

Repositorio:

```
https://github.com/KamiAlgorithm/Backend
```

---

## Conclusiones

### Resultado del sistema

* arquitectura desacoplada
* persistencia consistente
* comportamiento predecible
* base extensible

---

### Mejoras lógicas del sistema

* autenticación
* autorización
* operaciones CRUD completas
* validación estricta
* paginación
* logging estructurado
* pruebas automatizadas
* HTTPS

---

## Referencias

* Jakarta EE
* MySQL
* Apache Tomcat
* Gson

---


