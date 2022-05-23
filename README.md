# Calculadora hecha con Apache Thrift

## 1. Crear contenedores
* Para crear el contenedor del servidor:

```
docker-compose build servidor
```

* Para crear el contenedor del cliente:
```
docker-compose build cliente
```

## 2. Ejecutar contenedores
* Primero debe ejecutar el servidor:
```
docker-compose run servidor
```

* Después debe ejecutar el cliente
```
docker-compose run cliente
```
