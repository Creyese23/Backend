-- ============================================================================
-- Script de Actualización para Enums en DENTVISION Backend
-- ============================================================================
-- IMPORTANTE: Ejecutar después de actualizar el código a version con enums

-- ============================================================================
-- 1. VERIFICAR Y ACTUALIZAR TIPOS DE DATOS A VARCHAR (EnumType.STRING)
-- ============================================================================

-- Las columnas ya deberían ser VARCHAR, pero validamos

-- Empleado - estado
ALTER TABLE empleado MODIFY COLUMN estado VARCHAR(20) DEFAULT 'ACTIVO';

-- Paciente - estado
ALTER TABLE paciente MODIFY COLUMN estado VARCHAR(20) DEFAULT 'ACTIVO';

-- Cita - estado
ALTER TABLE cita MODIFY COLUMN estado VARCHAR(20) DEFAULT 'PENDIENTE';

-- Procedimiento - estado
ALTER TABLE procedimiento MODIFY COLUMN estado VARCHAR(20) DEFAULT 'PENDIENTE';

-- Orden - estado
ALTER TABLE orden MODIFY COLUMN estado VARCHAR(20) DEFAULT 'PENDIENTE';

-- Entrega - estado
ALTER TABLE entrega MODIFY COLUMN estado VARCHAR(20) DEFAULT 'PENDIENTE';

-- Factura - estado_pago
ALTER TABLE facturas MODIFY COLUMN estado_pago VARCHAR(20) DEFAULT 'PENDIENTE';

-- Inventario - estado
ALTER TABLE inventario MODIFY COLUMN estado VARCHAR(20) DEFAULT 'DISPONIBLE';

-- MovimientoInventario - tipo_movimiento
ALTER TABLE movimiento_inventario MODIFY COLUMN tipo_movimiento VARCHAR(20);

-- Pagos - estado
ALTER TABLE pagos MODIFY COLUMN estado VARCHAR(20) DEFAULT 'PENDIENTE';

-- ============================================================================
-- 2. ACTUALIZAR VALORES EXISTENTES A NOMBRES DE ENUMS (MAYÚSCULAS)
-- ============================================================================

-- EMPLEADO - Normalizar valores
UPDATE empleado SET estado = 'ACTIVO' WHERE LOWER(estado) IN ('activo', 'active', '1');
UPDATE empleado SET estado = 'INACTIVO' WHERE LOWER(estado) IN ('inactivo', 'inactive', '0');
UPDATE empleado SET estado = 'SUSPENDIDO' WHERE LOWER(estado) IN ('suspendido', 'suspended');

-- PACIENTE - Normalizar valores
UPDATE paciente SET estado = 'ACTIVO' WHERE LOWER(estado) IN ('activo', 'active', '1');
UPDATE paciente SET estado = 'INACTIVO' WHERE LOWER(estado) IN ('inactivo', 'inactive', '0');

-- CITA - Normalizar valores
UPDATE cita SET estado = 'PENDIENTE' WHERE LOWER(estado) IN ('pendiente', 'pending');
UPDATE cita SET estado = 'CONFIRMADA' WHERE LOWER(estado) IN ('confirmada', 'confirmed');
UPDATE cita SET estado = 'REALIZADA' WHERE LOWER(estado) IN ('realizada', 'completed');
UPDATE cita SET estado = 'CANCELADA' WHERE LOWER(estado) IN ('cancelada', 'cancelled', 'canceled');

-- PROCEDIMIENTO - Normalizar valores
UPDATE procedimiento SET estado = 'PENDIENTE' WHERE LOWER(estado) IN ('pendiente', 'pending');
UPDATE procedimiento SET estado = 'EN_PROCESO' WHERE LOWER(estado) IN ('en proceso', 'in process', 'en_proceso');
UPDATE procedimiento SET estado = 'COMPLETADO' WHERE LOWER(estado) IN ('completado', 'completed');
UPDATE procedimiento SET estado = 'CANCELADO' WHERE LOWER(estado) IN ('cancelado', 'cancelled', 'canceled');

-- ORDEN - Normalizar valores
UPDATE orden SET estado = 'PENDIENTE' WHERE LOWER(estado) IN ('pendiente', 'pending');
UPDATE orden SET estado = 'EN_PROCESO' WHERE LOWER(estado) IN ('en proceso', 'in process', 'en_proceso');
UPDATE orden SET estado = 'COMPLETADA' WHERE LOWER(estado) IN ('completada', 'completed');
UPDATE orden SET estado = 'CANCELADA' WHERE LOWER(estado) IN ('cancelada', 'cancelled', 'canceled');

-- ENTREGA - Normalizar valores
UPDATE entrega SET estado = 'PENDIENTE' WHERE LOWER(estado) IN ('pendiente', 'pending');
UPDATE entrega SET estado = 'ENTREGADA' WHERE LOWER(estado) IN ('entregada', 'delivered');
UPDATE entrega SET estado = 'RECHAZADA' WHERE LOWER(estado) IN ('rechazada', 'rejected');

-- FACTURA - estado_pago - Normalizar valores
UPDATE facturas SET estado_pago = 'PENDIENTE' WHERE LOWER(estado_pago) IN ('pendiente', 'pending');
UPDATE facturas SET estado_pago = 'PARCIAL' WHERE LOWER(estado_pago) IN ('parcial', 'partial');
UPDATE facturas SET estado_pago = 'PAGADA' WHERE LOWER(estado_pago) IN ('pagada', 'paid');
UPDATE facturas SET estado_pago = 'VENCIDA' WHERE LOWER(estado_pago) IN ('vencida', 'overdue');

-- INVENTARIO - Normalizar valores
UPDATE inventario SET estado = 'DISPONIBLE' WHERE LOWER(estado) IN ('disponible', 'available');
UPDATE inventario SET estado = 'AGOTADO' WHERE LOWER(estado) IN ('agotado', 'out of stock');
UPDATE inventario SET estado = 'DAÑADO' WHERE LOWER(estado) IN ('dañado', 'damaged');
UPDATE inventario SET estado = 'DESCONTINUADO' WHERE LOWER(estado) IN ('descontinuado', 'discontinued');

-- MOVIMIENTO INVENTARIO - Normalizar valores
UPDATE movimiento_inventario SET tipo_movimiento = 'ENTRADA' WHERE LOWER(tipo_movimiento) IN ('entrada', 'entry', 'in');
UPDATE movimiento_inventario SET tipo_movimiento = 'SALIDA' WHERE LOWER(tipo_movimiento) IN ('salida', 'exit', 'out');
UPDATE movimiento_inventario SET tipo_movimiento = 'AJUSTE' WHERE LOWER(tipo_movimiento) IN ('ajuste', 'adjustment');

-- PAGO - Normalizar valores
UPDATE pagos SET estado = 'PENDIENTE' WHERE LOWER(estado) IN ('pendiente', 'pending');
UPDATE pagos SET estado = 'PROCESADO' WHERE LOWER(estado) IN ('procesado', 'processed');
UPDATE pagos SET estado = 'FALLIDO' WHERE LOWER(estado) IN ('fallido', 'failed');
UPDATE pagos SET estado = 'CANCELADO' WHERE LOWER(estado) IN ('cancelado', 'cancelled', 'canceled');

-- ============================================================================
-- 3. VALIDAR DATOS ACTUALIZACIÓN
-- ============================================================================

-- Verificar que no hay valores nulos o inválidos
SELECT 'EMPLEADO' as tabla, COUNT(*) as registros_nulos FROM empleado WHERE estado IS NULL;
SELECT 'PACIENTE' as tabla, COUNT(*) as registros_nulos FROM paciente WHERE estado IS NULL;
SELECT 'CITA' as tabla, COUNT(*) as registros_nulos FROM cita WHERE estado IS NULL;
SELECT 'PROCEDIMIENTO' as tabla, COUNT(*) as registros_nulos FROM procedimiento WHERE estado IS NULL;
SELECT 'ORDEN' as tabla, COUNT(*) as registros_nulos FROM orden WHERE estado IS NULL;
SELECT 'ENTREGA' as tabla, COUNT(*) as registros_nulos FROM entrega WHERE estado IS NULL;
SELECT 'FACTURA' as tabla, COUNT(*) as registros_nulos FROM facturas WHERE estado_pago IS NULL;
SELECT 'INVENTARIO' as tabla, COUNT(*) as registros_nulos FROM inventario WHERE estado IS NULL;

-- Mostrar valores únicos por tabla
SELECT 'EMPLEADO' as tabla, DISTINCT estado FROM empleado;
SELECT 'PACIENTE' as tabla, DISTINCT estado FROM paciente;
SELECT 'CITA' as tabla, DISTINCT estado FROM cita;
SELECT 'PROCEDIMIENTO' as tabla, DISTINCT estado FROM procedimiento;
SELECT 'ORDEN' as tabla, DISTINCT estado FROM orden;
SELECT 'ENTREGA' as tabla, DISTINCT estado FROM entrega;
SELECT 'FACTURA' as tabla, DISTINCT estado_pago FROM facturas;
SELECT 'INVENTARIO' as tabla, DISTINCT estado FROM inventario;
SELECT 'MOVIMIENTO_INVENTARIO' as tabla, DISTINCT tipo_movimiento FROM movimiento_inventario;
SELECT 'PAGOS' as tabla, DISTINCT estado FROM pagos;

-- ============================================================================
-- 4. CONSTRAINTS Y VALIDACIÓN (Opcional)
-- ============================================================================

-- Comentar/descomentar si deseas agregar checks en la BD
-- ALTER TABLE empleado ADD CONSTRAINT chk_empleado_estado 
-- CHECK (estado IN ('ACTIVO', 'INACTIVO', 'SUSPENDIDO'));

-- ALTER TABLE cita ADD CONSTRAINT chk_cita_estado 
-- CHECK (estado IN ('PENDIENTE', 'CONFIRMADA', 'REALIZADA', 'CANCELADA'));

-- ============================================================================
-- NOTAS IMPORTANTES:
-- ============================================================================
-- 1. Ejecutar SELECT de validación ANTES de aplicar este script
-- 2. Hacer BACKUP de la BD antes de ejecutar actualizaciones
-- 3. Los enums en Java se almacenan como STRING en la BD
-- 4. Los nombres de los enums están en MAYÚSCULAS
-- 5. Si hay valores que no coinciden, actualizar manualmente o revisar
-- 6. Después de ejecutar, verificar que no hay inconsistencias
