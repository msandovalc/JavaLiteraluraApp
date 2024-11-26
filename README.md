
# Literalura

**Literalura** es una aplicación de consola desarrollada en Java y Spring Boot que permite gestionar un catálogo de libros. Permite buscar libros en una base de datos local y en la API externa de Gutendex, y realizar diversas operaciones con la información obtenida.

---

## **Estructura del Proyecto**

El proyecto está organizado utilizando las buenas prácticas de arquitectura de software y OOP:

```
src/main/java/com/alura/literalura
├── config          # Configuraciones generales del proyecto.
├── controller      # Clases encargadas de manejar las interacciones de los usuarios.
├── dto             # Objetos de transferencia de datos (BookDTO).
├── model           # Clases que representan las entidades del dominio (Book).
├── repository      # Interfaces para la interacción con la base de datos (BookRepository).
├── service         # Lógica de negocio (BookService, GutendexService).
├── util            # Utilidades generales (formateos, helpers, etc.).
└── LiteraluraApplication.java  # Clase principal para iniciar la aplicación.
```

---

## **Tecnologías Utilizadas**

- **Java 17**: Lenguaje principal del proyecto.
- **Spring Boot 3.2.4**: Framework para la configuración y desarrollo de la aplicación.
- **Spring Data JPA**: Para interactuar con la base de datos.
- **PostgreSQL**: Base de datos relacional utilizada.
- **Gutendex API**: Servicio externo para buscar libros en el Proyecto Gutenberg.

---

## **Requisitos**

1. **Java**: Asegúrate de tener instalado Java 17 o superior.
2. **PostgreSQL**: Configura una base de datos PostgreSQL con las siguientes credenciales:
   - **Base de datos**: `alura_literalura`
   - **Usuario**: `postgres`
   - **Contraseña**: `password` (ajustar según tu configuración local).

---

## **Configuración**

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-repositorio/literalura.git
   cd literalura
   ```

2. Configura la conexión a la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/alura_literalura
   spring.datasource.username=postgres
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Instala las dependencias y compila el proyecto:
   ```bash
   ./mvnw clean install
   ```

4. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## **Uso de la Aplicación**

Cuando ejecutes la aplicación, se presentará un menú interactivo con las siguientes opciones:

1. **Buscar libro por título en la base de datos**  
   Permite buscar libros almacenados en la base de datos local por su título.

2. **Buscar libro por idioma en la base de datos**  
   Busca libros según el idioma. Introduce códigos como `EN`, `ES`, `FR`, `PT`.

3. **Listar todos los libros registrados**  
   Muestra todos los libros almacenados en la base de datos local.

4. **Buscar libro por título en la web y almacenarlo en la base de datos**  
   Usa la API de Gutendex para buscar libros por título, los muestra en pantalla, y los guarda en la base de datos local.

5. **Salir**  
   Finaliza la ejecución de la aplicación.

---

## **Ejemplo de Uso**

### **Búsqueda de libro por título en la web**
1. Selecciona la opción `4`.
2. Introduce un título, por ejemplo, `Pride`.
3. La aplicación:
   - Consulta la API de Gutendex y muestra los resultados.
   - Verifica si el libro ya está en la base de datos.
   - Si no existe, lo almacena en la base de datos local.

---

## **Pruebas**

El proyecto incluye pruebas unitarias para la lógica de negocio en los servicios y pruebas de integración para los repositorios de JPA. Ejecuta las pruebas con:

```bash
./mvnw test
```

---

## **Contribuciones**

Si deseas contribuir al proyecto:

1. Haz un fork del repositorio.
2. Crea una rama para tu característica (`feature/nueva-funcionalidad`).
3. Realiza un pull request.

---

## **Créditos**

Desarrollado por **Manuel Sandoval** como parte del desafío **LiterAlura**.
