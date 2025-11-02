# Proyecto Productos EJB - Web (Java EE + TomEE + SQL Server)

Proyecto de ejemplo desarrollado en **arquitectura MVC** utilizando **Java EE (EJB, Servlets y JSP)** con **SQL Server** como base de datos.

Este proyecto está dividido en módulos:
- **ProductosEJB:** Contiene la lógica de negocio (Enterprise Java Beans).
- **ProductosWeb:** Contiene la capa web (Servlets, JSP y controladores).
- **db:** Incluye el script SQL para crear la base de datos, tablas y registros de ejemplo.

---

## Requisitos

- **JDK 17** o superior  
- **Apache TomEE 10.1.1 o 10.1.2** (versión Jakarta EE 10)  
- **SQL Server 2019 o superior**  
- **Eclipse IDE for Enterprise Java Developers** (recomendado)

---

##  Base de datos

1. Abre **SQL Server Management Studio (SSMS)**.  
2. Ejecuta el script SQL ubicado en:

## Importante:
Antes de ejecutar el proyecto, abre el archivo AppResources.java (ubicado dentro de ProductosEJB) y reemplaza TU_USUARIO_AQUI y TU_CONTRASEÑA_AQUI por tus credenciales reales de SQL Server.
Si no realizas este cambio, la aplicación no podrá conectarse a la base de datos.

## Ejecución del proyecto

Abre el proyecto en Eclipse.

Asegúrate de tener configurado el servidor Apache TomEE 10.1.1 o 10.1.2.

Ejecuta el módulo ProductosWeb con la opción Run on Server y selecciona el servidor TomEE.

Es común que la primera ejecución muestre un error. Si ocurre, vuelve a ejecutar Run on Server una segunda vez.

## Solución de errores comunes en Eclipse

Si aparecen errores en archivos .jsp, clases EJB o clases Web al importar el proyecto, puedes resolverlos de las siguientes formas:

Actualizar Maven:
Haz clic derecho en el proyecto → Maven → Update Project... → marca ambos proyectos (ProductosEJB y ProductosWeb) → OK.

Reaplicar el Dynamic Web Module:
Haz clic derecho en ProductosWeb → Properties → Project Facets → selecciona “Dynamic Web Module 6.0” (aunque ya esté marcado) → Apply and Close.
Esto corregirá los errores en todos los JSP sin necesidad de editarlos uno por uno.
