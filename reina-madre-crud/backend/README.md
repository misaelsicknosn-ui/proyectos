
# Microservicio de Citas (ms-citas)

## Descripción
Microservicio encargado de gestionar las Citas. Este servicio proporciona endpoints para consultar, crear, actualizar y eliminar citas.

## Tecnologías
- Java 11
- Spring Boot 2.7.9
- Spring Data JPA
- Swagger/OpenAPI para documentación de API
- JUnit 5 y Mockito para pruebas
- JaCoCo para cobertura de código
- Maven para gestión de dependencias y construcción

## Estructura del Proyecto
El proyecto sigue una arquitectura de capas estándar:

- **Controller**: Contiene los controladores REST que exponen los endpoints de la API.
- **Service**: Implementa la lógica de negocio.
- **Repository**: Interfaces para acceso a datos mediante Spring Data JPA.
- **Entity**: Clases de entidad que mapean a tablas de la base de datos.
- **DTO**: Objetos de transferencia de datos para las respuestas y solicitudes de la API.
- **Configuration**: Configuraciones de Spring Boot, incluyendo fuentes de datos y Swagger.
- **Exception**: Manejo de excepciones personalizado.
- **Enumeration**: Enumeraciones utilizadas en el proyecto.
- **Utility**: Clases de utilidad.