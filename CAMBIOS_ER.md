# Resumen de Ajustes al Proyecto DENTVISION Backend

## Cambios Realizados Según Diagrama ER

### 1. **Nuevas Entidades Creadas**

#### EmpleadoRol (Tabla de Relación Many-to-Many)
- **Ubicación**: `entity/EmpleadoRol.java`
- **Tabla**: `empleados_roles`
- **Campos**:
  - `id_empleado_rol` (PK)
  - `id_empleado` (FK)
  - `id_rol` (FK)
- **Relación**: Empleados ↔ Roles (many-to-many)

#### CitaServicio (Tabla de Relación Many-to-Many)
- **Ubicación**: `entity/CitaServicio.java`
- **Tabla**: `citas_servicios`
- **Campos**:
  - `id_cita_servicio` (PK)
  - `id_cita` (FK)
  - `id_servicio` (FK)
  - `precio_acordado` (BigDecimal)
- **Relación**: Citas ↔ Servicios (many-to-many)

#### OrdenDetalle (Detalles de Órdenes)
- **Ubicación**: `entity/OrdenDetalle.java`
- **Tabla**: `orden_detalle`
- **Campos**:
  - `id_orden_detalle` (PK)
  - `id_orden` (FK)
  - `id_servicio` (FK)
  - `cantidad` (Integer)
  - `precio_unitario` (BigDecimal)
  - `observaciones` (String)
- **Relación**: Órdenes → Detalles (one-to-many)

#### Pago (Pagos de Facturas)
- **Ubicación**: `entity/Pago.java`
- **Tabla**: `pagos`
- **Campos**:
  - `id_pago` (PK)
  - `id_factura` (FK)
  - `fecha_pago` (LocalDate)
  - `metodo_pago` (String)
  - `valor` (BigDecimal)
  - `estado` (String)
- **Relación**: Facturas → Pagos (one-to-many)

### 2. **Entidades Modificadas**

#### Empleado
- **Cambio**: Relación directa con `Rol` → Many-to-many mediante `EmpleadoRol`
- **Anterior**: `@ManyToOne private Rol rol;`
- **Nuevo**: `@OneToMany private List<EmpleadoRol> empleadoRoles;`

#### Cita
- **Cambio**: Relación directa con `Servicio` → Many-to-many mediante `CitaServicio`
- **Anterior**: `@ManyToOne private Servicio servicio;`
- **Nuevo**: `@OneToMany private List<CitaServicio> citaServicios;`

#### Rol
- **Cambio**: Agregada relación inversa con `EmpleadoRol`
- **Nuevo**: `@OneToMany private List<EmpleadoRol> empleadoRoles;`

#### Orden
- **Cambio**: Agregada relación one-to-many con `OrdenDetalle`
- **Nuevo**: `@OneToMany private List<OrdenDetalle> detalles;`

#### Factura
- **Cambio**: Agregada relación one-to-many con `Pago`
- **Nuevo**: `@OneToMany private List<Pago> pagos;`

### 3. **Nuevos Repositorios Creados**

1. **EmpleadoRolRepository**
   - Ubicación: `repository/EmpleadoRolRepository.java`
   - Métodos: `findByEmpleado_IdEmpleado()`, `findByRol_IdRol()`

2. **CitaServicioRepository**
   - Ubicación: `repository/CitaServicioRepository.java`
   - Métodos: `findByCita_IdCita()`, `findByServicio_IdServicio()`

3. **OrdenDetalleRepository**
   - Ubicación: `repository/OrdenDetalleRepository.java`
   - Métodos: `findByOrden_IdOrden()`, `findByServicio_IdServicio()`

4. **PagoRepository**
   - Ubicación: `repository/PagoRepository.java`
   - Métodos: `findByFactura_IdFactura()`

### 4. **Nuevos Servicios Creados**

1. **EmpleadoRolService** - `service/EmpleadoRolService.java`
2. **CitaServicioService** - `service/CitaServicioService.java`
3. **OrdenDetalleService** - `service/OrdenDetalleService.java`
4. **PagoService** - `service/PagoService.java`

### 5. **Nuevos DTOs Creados**

1. **EmpleadoRolDTO** - `dto/EmpleadoRolDTO.java`
2. **CitaServicioDTO** - `dto/CitaServicioDTO.java`
3. **OrdenDetalleDTO** - `dto/OrdenDetalleDTO.java`
4. **PagoDTO** - `dto/PagoDTO.java`

### 6. **Nuevos Controladores Creados**

1. **EmpleadoRolController** - `controller/EmpleadoRolController.java`
   - Rutas: `/api/empleados-roles`

2. **CitaServicioController** - `controller/CitaServicioController.java`
   - Rutas: `/api/citas-servicios`

3. **OrdenDetalleController** - `controller/OrdenDetalleController.java`
   - Rutas: `/api/ordenes-detalles`

4. **PagoController** - `controller/PagoController.java`
   - Rutas: `/api/pagos`

### 7. **DTOs Modificados**

#### CitaDTO
- **Cambio**: Removido campo `idServicio`
- **Razón**: Ya no existe relación directa con Servicio

#### EmpleadoDTO
- **Cambio**: Removido campo `idRol`
- **Razón**: Ya no existe relación directa con Rol (es many-to-many)

### 8. **Controladores Modificados**

#### AuthController
- **Cambio**: Actualizado para usar relación many-to-many con roles
- **Método**: Ahora obtiene el primer rol de la lista de `EmpleadoRol`
- **Inyección**: Agregado `EmpleadoRolRepository`

### 9. **Estados de Compilación**

✅ **Compilación Exitosa**
- Todos los archivos compilados correctamente
- No hay errores de estructura o dependencias
- Solo advertencias sobre APIs deprecated en `JwtUtil.java` (no críticas)

## Próximos Pasos Recomendados

1. **Actualizar Base de Datos**: Ejecutar los scripts SQL (ver archivo `db-migration.sql`)
2. **Testing**: Realizar pruebas de integración con los nuevos endpoints
3. **Migración de Datos**: Si hay datos existentes, migrar según nueva estructura
4. **Documentación API**: Actualizar documentación Swagger/OpenAPI
5. **Frontend**: Actualizar solicitudes API según nuevos DTOs y endpoints

## Endpoints Disponibles

### EmpleadoRol
- `POST /api/empleados-roles` - Crear relación
- `GET /api/empleados-roles` - Listar todas
- `GET /api/empleados-roles/{id}` - Obtener por ID
- `GET /api/empleados-roles/empleado/{idEmpleado}` - Por empleado
- `GET /api/empleados-roles/rol/{idRol}` - Por rol
- `PUT /api/empleados-roles/{id}` - Actualizar
- `DELETE /api/empleados-roles/{id}` - Eliminar

### CitaServicio
- `POST /api/citas-servicios` - Crear
- `GET /api/citas-servicios` - Listar
- `GET /api/citas-servicios/{id}` - Obtener por ID
- `GET /api/citas-servicios/cita/{idCita}` - Por cita
- `GET /api/citas-servicios/servicio/{idServicio}` - Por servicio
- `PUT /api/citas-servicios/{id}` - Actualizar
- `DELETE /api/citas-servicios/{id}` - Eliminar

### OrdenDetalle
- `POST /api/ordenes-detalles` - Crear
- `GET /api/ordenes-detalles` - Listar
- `GET /api/ordenes-detalles/{id}` - Obtener por ID
- `GET /api/ordenes-detalles/orden/{idOrden}` - Por orden
- `GET /api/ordenes-detalles/servicio/{idServicio}` - Por servicio
- `PUT /api/ordenes-detalles/{id}` - Actualizar
- `DELETE /api/ordenes-detalles/{id}` - Eliminar

### Pago
- `POST /api/pagos` - Crear
- `GET /api/pagos` - Listar
- `GET /api/pagos/{id}` - Obtener por ID
- `GET /api/pagos/factura/{idFactura}` - Por factura
- `PUT /api/pagos/{id}` - Actualizar
- `DELETE /api/pagos/{id}` - Eliminar

## Notas Importantes

1. La relación many-to-many entre Empleados y Roles permite que un empleado tenga múltiples roles.
2. La relación many-to-many entre Citas y Servicios permite que una cita tenga múltiples servicios con precios acordados diferentes.
3. Los DTOs fueron simplificados removiendo campos de relaciones que cambiaron de estructura.
4. El AuthController obtiene el primer rol disponible del empleado durante el login.
