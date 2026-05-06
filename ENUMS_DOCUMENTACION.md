# ENUMS Creados para DENTVISION Backend

## 📋 Resumen

Se han creado 10 enums para tipificar y controlar los estados de las diferentes entidades del sistema. Esto mejora la validación de datos, previene errores y facilita el mantenimiento del código.

## 🔍 Enums por Entidad

### 1. **EmpleadoEstado**
**Ubicación**: `enums/EmpleadoEstado.java`  
**Entidad**: `Empleado`  
**Campo**: `estado`

| Valor | Descripción |
|-------|-------------|
| `ACTIVO` | Empleado activo en el sistema |
| `INACTIVO` | Empleado inactivo |
| `SUSPENDIDO` | Empleado suspendido temporalmente |

---

### 2. **PacienteEstado**
**Ubicación**: `enums/PacienteEstado.java`  
**Entidad**: `Paciente`  
**Campo**: `estado`

| Valor | Descripción |
|-------|-------------|
| `ACTIVO` | Paciente activo |
| `INACTIVO` | Paciente inactivo |

---

### 3. **CitaEstado**
**Ubicación**: `enums/CitaEstado.java`  
**Entidad**: `Cita`  
**Campo**: `estado`

| Valor | Descripción |
|-------|-------------|
| `PENDIENTE` | Cita pendiente de confirmación |
| `CONFIRMADA` | Cita confirmada |
| `REALIZADA` | Cita realizada |
| `CANCELADA` | Cita cancelada |

---

### 4. **ProcedimientoEstado**
**Ubicación**: `enums/ProcedimientoEstado.java`  
**Entidad**: `Procedimiento`  
**Campo**: `estado`

| Valor | Descripción |
|-------|-------------|
| `PENDIENTE` | Procedimiento pendiente de inicio |
| `EN_PROCESO` | Procedimiento en ejecución |
| `COMPLETADO` | Procedimiento completado |
| `CANCELADO` | Procedimiento cancelado |

---

### 5. **OrdenEstado**
**Ubicación**: `enums/OrdenEstado.java`  
**Entidad**: `Orden`  
**Campo**: `estado`

| Valor | Descripción |
|-------|-------------|
| `PENDIENTE` | Orden pendiente |
| `EN_PROCESO` | Orden en proceso |
| `COMPLETADA` | Orden completada |
| `CANCELADA` | Orden cancelada |

---

### 6. **EntregaEstado**
**Ubicación**: `enums/EntregaEstado.java`  
**Entidad**: `Entrega`  
**Campo**: `estado`

| Valor | Descripción |
|-------|-------------|
| `PENDIENTE` | Entrega pendiente |
| `ENTREGADA` | Entrega realizada |
| `RECHAZADA` | Entrega rechazada |

---

### 7. **EstadoPago**
**Ubicación**: `enums/EstadoPago.java`  
**Entidad**: `Factura`  
**Campo**: `estadoPago`

| Valor | Descripción |
|-------|-------------|
| `PENDIENTE` | Pago pendiente |
| `PARCIAL` | Pago parcial |
| `PAGADA` | Factura completamente pagada |
| `VENCIDA` | Factura vencida |

---

### 8. **InventarioEstado**
**Ubicación**: `enums/InventarioEstado.java`  
**Entidad**: `Inventario`  
**Campo**: `estado`

| Valor | Descripción |
|-------|-------------|
| `DISPONIBLE` | Insumo disponible |
| `AGOTADO` | Insumo agotado |
| `DAÑADO` | Insumo dañado |
| `DESCONTINUADO` | Insumo descontinuado |

---

### 9. **TipoMovimientoInventario**
**Ubicación**: `enums/TipoMovimientoInventario.java`  
**Entidad**: `MovimientoInventario`  
**Campo**: `tipoMovimiento`

| Valor | Descripción |
|-------|-------------|
| `ENTRADA` | Entrada de insumo |
| `SALIDA` | Salida de insumo |
| `AJUSTE` | Ajuste de inventario |

---

### 10. **PagoEstado**
**Ubicación**: `enums/PagoEstado.java`  
**Entidad**: `Pago`  
**Campo**: `estado`

| Valor | Descripción |
|-------|-------------|
| `PENDIENTE` | Pago pendiente de procesar |
| `PROCESADO` | Pago procesado exitosamente |
| `FALLIDO` | Pago fallido |
| `CANCELADO` | Pago cancelado |

---

## 🔧 Uso de los Enums

### Crear un objeto con enum
```java
Empleado empleado = new Empleado();
empleado.setEstado(EmpleadoEstado.ACTIVO);
```

### Obtener descripción del enum
```java
String descripcion = EmpleadoEstado.ACTIVO.getDescripcion(); // "Activo"
```

### Convertir String a enum
```java
String estadoStr = "ACTIVO";
EmpleadoEstado estado = EmpleadoEstado.valueOf(estadoStr);
```

### Usar en JPA Query
```java
List<Empleado> empleadosActivos = empleadoRepository
    .findByEstado(EmpleadoEstado.ACTIVO);
```

---

## 📊 Ventajas de Usar Enums

1. **Type-Safety**: El compilador valida los valores permitidos
2. **Prevención de Errores**: No es posible asignar valores inválidos
3. **Autocompletado**: Los IDEs sugieren los valores disponibles
4. **Documentación**: Los valores posibles son evidentes en el código
5. **Performance**: Las comparaciones de enum son más rápidas que las de String
6. **Refactoring**: Si necesitas cambiar un valor, es más fácil actualizar

---

## 🗄️ Mapeo en Base de Datos

Los enums se almacenan en la base de datos como STRING (nombre del enum):

```sql
-- Ejemplo de datos en BD
SELECT * FROM empleado;
-- estado = 'ACTIVO', 'INACTIVO', 'SUSPENDIDO'

SELECT * FROM cita;
-- estado = 'PENDIENTE', 'CONFIRMADA', 'REALIZADA', 'CANCELADA'
```

### Migración de Datos Existentes

Si ya tienes datos en la BD con valores String, necesitas actualizar:

```sql
UPDATE empleado SET estado = 'ACTIVO' WHERE estado = 'activo';
UPDATE empleado SET estado = 'INACTIVO' WHERE estado = 'inactivo';
-- ... y así para los demás enums
```

---

## ✅ Estado de Compilación

- ✅ **BUILD SUCCESS**
- **102 archivos compilados** sin errores
- **10 nuevos enums** creados
- **9 entidades modificadas** para usar los enums

---

## 📝 Próximos Pasos

1. Actualizar scripts SQL para establecer valores de enum correcto
2. Revisar y ajustar datos existentes en BD si es necesario
3. Implementar validación en servicios/controladores si es necesario
4. Actualizar DTOs si necesitan reflejar los enums
5. Escribir tests unitarios para validar estados

---

## 🔗 Relación de Enums con Entidades

```
Empleado → EmpleadoEstado
Paciente → PacienteEstado
Cita → CitaEstado
Procedimiento → ProcedimientoEstado
Orden → OrdenEstado
Entrega → EntregaEstado
Factura → EstadoPago
Inventario → InventarioEstado
MovimientoInventario → TipoMovimientoInventario
Pago → PagoEstado
```

---

## 🎯 Archivos Modificados

**Entidades Actualizadas:**
- Empleado.java - Cambio: `String estado` → `EmpleadoEstado estado`
- Paciente.java - Cambio: `String estado` → `PacienteEstado estado`
- Cita.java - Cambio: `String estado` → `CitaEstado estado`
- Procedimiento.java - Cambio: `String estado` → `ProcedimientoEstado estado`
- Orden.java - Cambio: `String estado` → `OrdenEstado estado`
- Entrega.java - Cambio: `String estado` → `EntregaEstado estado`
- Factura.java - Cambio: `String estadoPago` → `EstadoPago estadoPago`
- Inventario.java - Cambio: `String estado` → `InventarioEstado estado`
- MovimientoInventario.java - Cambio: `String tipoMovimiento` → `TipoMovimientoInventario tipoMovimiento`
- Pago.java - Cambio: `String estado` → `PagoEstado estado`

**Nuevos Archivos (Enums):**
- EmpleadoEstado.java
- PacienteEstado.java
- CitaEstado.java
- ProcedimientoEstado.java
- OrdenEstado.java
- EntregaEstado.java
- EstadoPago.java
- InventarioEstado.java
- TipoMovimientoInventario.java
- PagoEstado.java
