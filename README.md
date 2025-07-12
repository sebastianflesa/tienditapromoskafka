# Tiendita Promociones Kafka - Microservicio de Gestión de Promociones

## Descripción
Este microservicio se encarga de:
1. **Consumir eventos de Kafka** de ventas y stock
2. **Registrar ventas** en la base de datos Oracle
3. **Generar promociones** automáticamente basadas en los eventos recibidos

## Funcionalidades Principales

### 1. Registro de Ventas
- Consume mensajes del topic `ventas` de Kafka
- Registra automáticamente cada venta en la tabla `VENTAS` de Oracle
- Mapea los datos del mensaje JSON a la estructura de base de datos

### 2. Generación de Promociones
- Procesa eventos de ventas y cambios de stock
- Genera promociones automáticas basadas en reglas de negocio
- Almacena promociones en la tabla `PROMOCIONES`

## Estructura de Base de Datos

### Tabla VENTAS
```sql
CREATE TABLE VENTAS (
    ID NUMBER(19,0) PRIMARY KEY,
    PRODUCTO_ID NUMBER(19,0) NOT NULL,
    CANTIDAD NUMBER(19,0) NOT NULL,
    USUARIO_ID NUMBER(19,0) NOT NULL
);
```

### Tabla PROMOCIONES
```sql
CREATE TABLE PROMOCIONES (
    ID NUMBER(19,0) PRIMARY KEY,
    PRODUCTO_ID VARCHAR2(50 CHAR) NOT NULL,
    CLIENTE_ID VARCHAR2(50 CHAR),
    TIPO_PROMOCION VARCHAR2(50 CHAR) NOT NULL,
    -- ... otros campos
);
```

## API Endpoints

### Ventas
- `GET /api/ventas/estadisticas/producto/{productoId}` - Obtener estadísticas de ventas por producto
- `GET /api/ventas/health` - Verificar estado del servicio

## Configuración

### Kafka Topics
- `ventas` - Topic para eventos de ventas
- `stock` - Topic para eventos de cambios de stock

### Base de Datos
- Oracle Database con conexión via Wallet
- Pool de conexiones HikariCP
- JPA/Hibernate para ORM

## Ejecución
1. Asegurar que Oracle DB y Kafka estén ejecutándose
2. Ejecutar el script `schema.sql` para crear las tablas
3. Iniciar el microservicio: `mvn spring-boot:run`