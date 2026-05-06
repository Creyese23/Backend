-- ============================================================================
-- Script de Migración para DENTVISION Backend - Actualizar DB al Diagrama ER
-- ============================================================================
-- IMPORTANTE: Ejecutar en el siguiente orden y hacer backup antes de aplicar

-- ============================================================================
-- 1. CREAR TABLAS DE RELACIÓN MANY-TO-MANY
-- ============================================================================

-- Tabla: empleados_roles (Relación Many-to-Many)
CREATE TABLE IF NOT EXISTS empleados_roles (
    id_empleado_rol INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado INT NOT NULL,
    id_rol INT NOT NULL,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado) ON DELETE CASCADE,
    FOREIGN KEY (id_rol) REFERENCES role(id_rol) ON DELETE CASCADE,
    UNIQUE KEY unique_empleado_rol (id_empleado, id_rol)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: citas_servicios (Relación Many-to-Many)
CREATE TABLE IF NOT EXISTS citas_servicios (
    id_cita_servicio INT AUTO_INCREMENT PRIMARY KEY,
    id_cita INT NOT NULL,
    id_servicio INT NOT NULL,
    precio_acordado DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_cita) REFERENCES cita(id_cita) ON DELETE CASCADE,
    FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio) ON DELETE CASCADE,
    UNIQUE KEY unique_cita_servicio (id_cita, id_servicio)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: orden_detalle (Detalles de Órdenes)
CREATE TABLE IF NOT EXISTS orden_detalle (
    id_orden_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_orden INT NOT NULL,
    id_servicio INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    observaciones VARCHAR(500),
    FOREIGN KEY (id_orden) REFERENCES orden(id_orden) ON DELETE CASCADE,
    FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: pagos (Pagos de Facturas)
CREATE TABLE IF NOT EXISTS pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_factura INT NOT NULL,
    fecha_pago DATE NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_factura) REFERENCES facturas(id_factura) ON DELETE CASCADE,
    INDEX idx_factura (id_factura)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================================
-- 2. MIGRACIÓN: CONVERTIR RELACIÓN DIRECTA EMPLEADO-ROL A MANY-TO-MANY
-- ============================================================================

-- Opcional: Si ya existen datos de empleados con rol
-- Este script copia los datos existentes a la nueva tabla de relación
INSERT INTO empleados_roles (id_empleado, id_rol)
SELECT DISTINCT id_empleado, id_rol FROM empleado WHERE id_rol IS NOT NULL;

-- Después de verificar que los datos se copiaron correctamente, eliminar la columna
-- ALTER TABLE empleado DROP FOREIGN KEY empleado_ibfk_1;
-- ALTER TABLE empleado DROP COLUMN id_rol;

-- ============================================================================
-- 3. MIGRACIÓN: ACTUALIZAR TABLA CITA (Remover relación directa con Servicio)
-- ============================================================================

-- Opcional: Copiar datos existentes de cita-servicio
-- INSERT INTO citas_servicios (id_cita, id_servicio, precio_acordado)
-- SELECT DISTINCT id_cita, id_servicio, 0.00 FROM cita WHERE id_servicio IS NOT NULL;

-- Después de verificar: eliminar la columna de cita
-- ALTER TABLE cita DROP FOREIGN KEY cita_ibfk_3;
-- ALTER TABLE cita DROP COLUMN id_servicio;

-- ============================================================================
-- 4. ÍNDICES PARA MEJOR RENDIMIENTO
-- ============================================================================

-- Índices para empleados_roles
CREATE INDEX IF NOT EXISTS idx_empleado_rol_empleado ON empleados_roles(id_empleado);
CREATE INDEX IF NOT EXISTS idx_empleado_rol_rol ON empleados_roles(id_rol);

-- Índices para citas_servicios
CREATE INDEX IF NOT EXISTS idx_cita_servicio_cita ON citas_servicios(id_cita);
CREATE INDEX IF NOT EXISTS idx_cita_servicio_servicio ON citas_servicios(id_servicio);

-- Índices para orden_detalle
CREATE INDEX IF NOT EXISTS idx_orden_detalle_orden ON orden_detalle(id_orden);
CREATE INDEX IF NOT EXISTS idx_orden_detalle_servicio ON orden_detalle(id_servicio);

-- ============================================================================
-- 5. VERIFICACIÓN DE ESTRUCTURA
-- ============================================================================

-- Mostrar estructura de nuevas tablas
-- DESCRIBE empleados_roles;
-- DESCRIBE citas_servicios;
-- DESCRIBE orden_detalle;
-- DESCRIBE pagos;

-- Contar registros migrados
-- SELECT COUNT(*) FROM empleados_roles;
-- SELECT COUNT(*) FROM citas_servicios;

-- ============================================================================
-- NOTAS IMPORTANTES:
-- ============================================================================
-- 1. HACER BACKUP ANTES DE EJECUTAR ESTE SCRIPT
-- 2. El script crea las nuevas tablas automáticamente
-- 3. Las migraciones de datos están comentadas (comentar ALTER si es necesario)
-- 4. Verificar que los datos se migran correctamente antes de eliminar columnas
-- 5. Las transacciones pueden ayudar a revertir cambios si hay errores
-- 6. Algunos comentarios incluyen comandos de limpieza que requieren confirmación

-- ============================================================================
-- ROLLBACK (Si es necesario revertir):
-- ============================================================================
-- DROP TABLE IF EXISTS pagos;
-- DROP TABLE IF EXISTS orden_detalle;
-- DROP TABLE IF EXISTS citas_servicios;
-- DROP TABLE IF EXISTS empleados_roles;
-- ALTER TABLE cita ADD COLUMN id_servicio INT;
-- ALTER TABLE empleado ADD COLUMN id_rol INT;
