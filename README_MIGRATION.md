# ForceTrack Backend

Este es el backend local para la aplicación ForceTrack, migrado desde Xano.

## Requisitos

- Java 17
- Maven (incluido via mvnw)

## Ejecución

Para iniciar el servidor:

```bash
./mvnw spring-boot:run
```

El servidor se iniciará en `http://localhost:8080`.

## Endpoints

La API está disponible en `/api/`.

- Auth: `/api/auth`
- Usuarios: `/api/usuario`
- Bloques: `/api/bloque`
- Semanas: `/api/semana`
- Días: `/api/dia`
- Ejercicios: `/api/ejercicio`
- Series: `/api/serie`

## Base de Datos

Por defecto utiliza H2 (en memoria/archivo).
Para ver la consola de H2, ve a `http://localhost:8080/h2-console` (si está habilitado en properties).
