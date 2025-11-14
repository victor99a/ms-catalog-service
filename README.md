📦 Catalog Service (Microservicio de Productos y Categorías)

Este microservicio forma parte de una arquitectura basada en microservicios.
Su responsabilidad principal es gestionar productos y categorías del sistema de e-commerce.

Incluye:

CRUD de Categorías
CRUD de Productos
Uso de DTOs + MapStruct
Persistencia con Spring Data JPA
Base de datos MySQL
Arquitectura limpia (Controller – Service – Repository – Mapper – DTO – Entity)


🧱 Tecnologías utilizadas
Tecnología	Uso
Spring Boot 3
Spring Web	Controladores REST
Spring Data JPA	Persistencia y repositorios
MySQL 5.7	Base de datos
MapStruct	Mapeo entre Entity ↔ DTO
Lombok	Reducción de código
Gradle	Construcción del proyecto


🗂️ Arquitectura del proyecto
src/main/java/com/victor/catalog
│
├── controller/       → Controladores REST
├── dto/              → DTOs para comunicación externa
├── mapper/           → Mappers MapStruct
├── model/            → Entidades JPA
├── repository/       → Repositorios JPA
└── service/          → Lógica de negocio



📌 application.properties
spring.application.name=catalog-service

spring.datasource.url=jdbc:mysql://localhost:3306/catalogdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


Asegúrate de tener MySQL corriendo y una base de datos creada:

CREATE DATABASE catalogdb;


📚 Endpoints principales
🔹 Categorías
Método	Endpoint	Descripción
POST	/api/categorias	Crear categoría
GET	/api/categorias	Listar categorías
GET	/api/categorias/{id}	Obtener por ID
PUT	/api/categorias/{id}	Actualizar
DELETE	/api/categorias/{id}	Eliminar

Ejemplo de JSON para crear categoría:

{
  "nombreCategoria": "Ropa",
  "descripcionCategoria": "Ropa de temporada"
}

🔹 Productos
Método	Endpoint	Descripción
POST	/api/productos	Crear producto
GET	/api/productos	Listar productos
GET	/api/productos/{id}	Obtener producto
PUT	/api/productos/{id}	Actualizar producto
DELETE	/api/productos/{id}	Eliminar producto

JSON para crear producto:

{
  "nombreProducto": "Polera Nike",
  "descripcionProducto": "Algodón 100%",
  "precioProducto": 26990,
  "categoriaId": 1
}

⚙️ Flujo interno de datos

ProductoDTO → Mapper → Entity → Service → Repository → DB
DB → Repository → Entity → Mapper → DTO → Controller → Cliente

Gracias a MapStruct, no se expone la entidad directamente y se evita la carga perezosa de relaciones.

Pruebas en Postman

Crear categoría ✔️

Crear producto ✔️

Actualizar ✔️

Listar ✔️

Eliminar ✔️
