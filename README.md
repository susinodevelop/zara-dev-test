# Prueba técnica para Zara

## Datos básicos

El servicio se levanta en localhost puerto 8080 bajo el context-path: /v1/

Desarrollado en Java 17

## Documentación Swagger

Se puede acceder al Swagger API del controlador a través
del [siguiente enlace](http://localhost:8080/v1/swagger-ui.html)

## Consola H2 para acceder a la BBDD

Se puede acceder a la consola del H2 una vez levantada la aplicación con los siguientes datos:

- **URL**: http://localhost:8080/v1/h2-console

Datos del formulario:

- **JDBC URL**: jdbc:h2:mem:zaradb
- **Username**: admin
- **Password**: admin

## Datos del controlador

El controlador implementado para los precios se encuentra bajo el path:

- /v1/prices

El endpoint para consultar el precio para una fecha/producto/cadena es:

- Método: **GET**
- Path:   **/v1/prices/final-price**

## Validación de parámetros

- **Fecha**: Que no sea nulo (requerido) y tenga el formato: **yyyy-MM-dd'T'HH:mm:ss**.
- **ProductId**: Que no sea nulo ni String vacío (requerido).
- **BrandId**: Que no sea nulo ni String vació (requerido).
