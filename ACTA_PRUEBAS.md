# ACTA DE PRUEBAS Y ACEPTACIÓN

**Sistema de Gestión de Usuarios — Backend Java**

---

## Objetivo de las Pruebas

Validar el comportamiento del sistema completo bajo condiciones controladas de ejecución, verificando:

* funcionamiento de la interfaz web JSP
* operación de la API REST
* persistencia correcta en MySQL
* validación de datos de entrada
* coherencia del flujo entre capas del sistema

Las pruebas buscan confirmar cumplimiento funcional, consistencia de datos y estabilidad del flujo de ejecución.

---

## Configuración del Ambiente de Pruebas

### Entorno de ejecución

| Componente               | Configuración            |
| ------------------------ | ------------------------ |
| Sistema Operativo        | Windows                  |
| JDK                      | Java 21                  |
| Servidor de Aplicaciones | Apache Tomcat 10.1       |
| Base de Datos            | MySQL 8.3                |
| IDE                      | IntelliJ IDEA / NetBeans |

El entorno reproduce condiciones de desarrollo estándar sin optimizaciones de producción.

---

### Parámetros de conexión

| Recurso        | Dirección                                   |
| -------------- | ------------------------------------------- |
| Base de datos  | `jdbc:mysql://localhost:3306/proyecto`      |
| Aplicación web | `http://localhost:8080/ProyectoBackend`     |
| API REST       | `http://localhost:8080/ProyectoBackend/api` |

Todas las pruebas se ejecutaron sobre estas rutas.

---

## Pruebas Funcionales — Aplicación Web

---

### Crear Usuario mediante Formulario

**Propósito**
Verificar la creación de usuarios desde la interfaz web y su persistencia en base de datos.

**Procedimiento**

1. Acceder a `formUsuario.jsp`.
2. Ingresar nombre: `Juan David Peralta`.
3. Ingresar email: `juan@mail.com`.
4. Ejecutar acción de guardado.
5. Verificar redirección a listado.

**Resultado esperado**

* registro persistido en base de datos
* redirección correcta a vista de listado
* usuario visible en interfaz

**Resultado obtenido**
CORRECTO.

**Evidencia**

* registro almacenado en tabla `usuario`
* generación automática de ID
* redirección sin errores

---

### Listado de Usuarios desde JSP

**Propósito**
Validar recuperación y renderizado de registros almacenados.

**Procedimiento**

1. Acceder a `listaUsuario.jsp`.
2. Verificar contenido de tabla.

**Resultado esperado**

* visualización de todos los registros
* columnas ID, nombre y email
* renderizado correcto sin errores

**Resultado obtenido**
CORRECTO.

**Evidencia**

* datos visibles y consistentes con base de datos
* estructura HTML correcta

---

### Validación de Campos Obligatorios

**Propósito**
Verificar rechazo de datos incompletos.

**Procedimiento**

1. Acceder al formulario.
2. Enviar campos vacíos.

**Resultado esperado**

* validación en cliente o servidor
* rechazo de solicitud inválida
* mensaje de error

**Resultado obtenido**
CORRECTO.

**Evidencia**

* solicitud rechazada
* respuesta HTTP 400 en API
* no se genera registro en base de datos

---

## Pruebas — API REST

---

### API POST — Creación de Usuario

**Endpoint**

```
POST /api/usuarios
```

**Herramienta**
Postman.

**Entrada**

```http
POST /api/usuarios
Content-Type: application/json
{
  "nombre": "Leona",
  "email": "Leona@sun.com"
}
```

**Resultado esperado**

* código HTTP 201
* confirmación de creación

**Resultado obtenido**
CORRECTO.

**Evidencia**

```
Status: 201 Created
{"mensaje":"Usuario creado exitosamente"}
```

Registro persistido correctamente.

---

### API GET — Listado de Usuarios

**Endpoint**

```
GET /api/usuarios
```

**Resultado esperado**

* código HTTP 200
* lista JSON de usuarios
* estructura de datos consistente

**Resultado obtenido**
CORRECTO.

**Evidencia**

* respuesta JSON válida
* datos coherentes con base de datos

---

### API POST — Validación de Datos Inválidos

**Propósito**
Verificar control de entradas incorrectas.

**Entrada**

```http
POST /api/usuarios
{
  "nombre": "",
  "email": ""
}
```

**Resultado esperado**

* código HTTP 400
* mensaje de error

**Resultado obtenido**
CORRECTO.

**Evidencia**

```
Status: 400 Bad Request
{"error":"Datos inválidos. Nombre y email son obligatorios."}
```

No se genera persistencia.

---

## Pruebas de Base de Datos

---

### Persistencia de Datos

**Propósito**
Confirmar almacenamiento físico de registros.

**Procedimiento**

1. Crear usuario.
2. Consultar directamente en MySQL.

```sql
SELECT * FROM usuario WHERE email = 'test@mail.com';
```

**Resultado esperado**

* registro existente
* datos consistentes
* ID autoincrementado

**Resultado obtenido**
CORRECTO.

Persistencia confirmada.

---

### Integridad Estructural

**Propósito**
Validar restricciones definidas en esquema.

**Resultado**
CORRECTO.

**Verificaciones**

* clave primaria funcional
* autoincremento operativo
* restricciones NOT NULL respetadas

---

## Pruebas de Integración

---

### Flujo Completo — Interfaz Web

**Propósito**
Validar flujo end-to-end.

**Secuencia evaluada**

```
acceso → formulario → creación → listado → verificación en BD
```

**Resultado**
CORRECTO.
Flujo completo ejecutado sin inconsistencias.

---

### Flujo Completo — API

**Propósito**
Validar consistencia entre API y persistencia.

**Secuencia evaluada**

```
POST → almacenamiento → GET → verificación en BD
```

**Resultado**
CORRECTO.

---

## Resumen de Resultados

| Categoría       | Total | Pasadas | Falladas | Éxito |
| --------------- | ----- | ------- | -------- | ----- |
| Funcionales Web | 3     | 3       | 0        | 100%  |
| API REST        | 3     | 3       | 0        | 100%  |
| Base de Datos   | 2     | 2       | 0        | 100%  |
| Integración     | 2     | 2       | 0        | 100%  |
| TOTAL           | 10    | 10      | 0        | 100%  |

---

## Incidencias Detectadas

Cantidad: 0.

No se identificaron errores críticos ni fallos funcionales.

---

## Observaciones Técnicas

* comportamiento consistente en ambiente de desarrollo
* integración correcta entre capas del sistema
* validaciones ejecutadas según diseño
* persistencia confiable
* flujo determinista sin estados inconsistentes

---

## Recomendaciones Técnicas

### Para entorno productivo

* autenticación y autorización
* pool de conexiones a base de datos
* logging estructurado
* pruebas automatizadas
* HTTPS
* control transaccional

---

### Evolución funcional

* operaciones UPDATE y DELETE
* paginación
* búsqueda y filtrado
* mejora de mensajes de error

---

## Conclusión

El sistema cumple los requisitos funcionales definidos para su alcance actual.
Las pruebas confirman funcionamiento correcto, persistencia consistente y comportamiento estable en el entorno evaluado.

El sistema se considera aceptado en su versión actual bajo condiciones de desarrollo controlado.

---
