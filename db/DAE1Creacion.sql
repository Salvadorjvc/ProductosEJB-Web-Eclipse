-- =============================================
-- Script de creación de base de datos y tablas
-- Proyecto: DAE1
-- =============================================

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'DAE1')
BEGIN
    CREATE DATABASE [DAE1];
    PRINT('Base de datos DAE1 creada correctamente.');
END
ELSE
BEGIN
    PRINT('La base de datos DAE1 ya existe.');
END
GO

USE [DAE1];
GO

-- =============================================
-- Tabla: Productos
-- =============================================

IF OBJECT_ID('dbo.Productos', 'U') IS NOT NULL
    DROP TABLE dbo.Productos;
GO

SET ANSI_NULLS ON;
GO
SET QUOTED_IDENTIFIER ON;
GO

CREATE TABLE [dbo].[Productos](
    [id_producto]     INT IDENTITY(1,1) NOT NULL,
    [nombre]          VARCHAR(100) NOT NULL,
    [descripcion]     TEXT NULL,
    [precio]          DECIMAL(10,2) NOT NULL,
    [stock]           INT NOT NULL,
    [categoria]       VARCHAR(50) NULL,
    [fecha_creacion]  DATETIME NULL 
        CONSTRAINT DF_Productos_Fecha_Creacion DEFAULT (GETDATE()),
    CONSTRAINT PK_Productos PRIMARY KEY CLUSTERED ([id_producto] ASC)
);
GO

-- =============================================
-- Tabla: Usuarios
-- =============================================

IF OBJECT_ID('dbo.Usuarios', 'U') IS NOT NULL
    DROP TABLE dbo.Usuarios;
GO

SET ANSI_NULLS ON;
GO
SET QUOTED_IDENTIFIER ON;
GO

CREATE TABLE [dbo].[Usuarios](
    [id_usuario]      INT IDENTITY(1,1) NOT NULL,
    [nombre]          VARCHAR(50) NOT NULL,
    [apellido]        VARCHAR(50) NOT NULL,
    [email]           VARCHAR(100) NOT NULL,
    [password]        VARCHAR(255) NOT NULL,
    [fecha_creacion]  DATETIME NULL 
        CONSTRAINT DF_Usuarios_Fecha_Creacion DEFAULT (GETDATE()),
    CONSTRAINT PK_Usuarios PRIMARY KEY CLUSTERED ([id_usuario] ASC),
    CONSTRAINT UQ_Usuarios_Email UNIQUE NONCLUSTERED ([email] ASC)
);
GO

-- =============================================
-- Datos de prueba
-- =============================================

INSERT INTO dbo.Usuarios (nombre, apellido, email, [password])
VALUES 
('Juan', 'Pérez', 'juan.perez@example.com', '123456'),
('Ana', 'Ramírez', 'ana.ramirez@example.com', 'abc123'),
('Luis', 'Torres', 'luis.torres@example.com', 'qwerty'),
('María', 'López', 'maria.lopez@example.com', 'pass123'),
('Carlos', 'Gómez', 'carlos.gomez@example.com', 'admin123');

INSERT INTO dbo.Productos (nombre, descripcion, precio, stock, categoria)
VALUES 
('Teclado Mecánico', 'Teclado retroiluminado RGB con switches azules', 120.50, 10, 'Periféricos'),
('Mouse Gamer', 'Mouse óptico con 7200 DPI y luces RGB', 85.90, 15, 'Periféricos'),
('Monitor 24"', 'Monitor LED Full HD de 24 pulgadas', 650.00, 8, 'Pantallas'),
('Audífonos Inalámbricos', 'Audífonos Bluetooth con micrófono integrado', 150.75, 12, 'Audio'),
('Laptop i5', 'Laptop con procesador Intel Core i5 y 8GB RAM', 2500.00, 5, 'Computadoras'),
('Impresora Multifuncional', 'Impresora con escáner y conexión Wi-Fi', 890.00, 7, 'Equipos de Oficina'),
('Memoria USB 32GB', 'Unidad flash USB 3.0 de 32GB', 35.90, 25, 'Almacenamiento'),
('Disco Duro Externo 1TB', 'Disco duro externo USB 3.0 de 1TB', 320.00, 10, 'Almacenamiento'),
('Cámara Web HD', 'Cámara web con resolución 1080p', 210.00, 9, 'Accesorios'),
('Router Wi-Fi', 'Router inalámbrico de doble banda', 275.00, 6, 'Redes');
GO

PRINT('Base de datos DAE1 configurada correctamente con datos de prueba.');
GO
