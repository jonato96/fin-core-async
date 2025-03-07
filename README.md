# fin-core-async
Spring Boot Financial Core

#  🌟 Sobre el Proyecto

Esta aplicación está enfocada en el core de un sistema de transacciones bancarias.
Para la implementación de este proyecto se ha empleado la siguiente arquitectura:

![ProjectArchitecture2](https://raw.githubusercontent.com/jonato96/fin-core-async/refs/heads/develop/deliverables/Architecture.jpg)

El dominio es el siguiente:

![DomainApp](https://raw.githubusercontent.com/jonato96/fin-core-async/refs/heads/develop/deliverables/Domain.jpg)

#  🔨 Construido con

- ![Spring Badge](https://img.shields.io/badge/SpringBoot-20232A?style=for-the-badge&logo=spring&logoColor=green)
- ![Swagger Badge](https://img.shields.io/badge/Swagger-20232A?style=for-the-badge&logo=swagger&logoColor=#68b618)
- ![Hibernate Badge](https://img.shields.io/badge/Hibernate-20232A?style=for-the-badge&logo=hibernate&logoColor=yellow)
- ![POSTGRES Badge](https://img.shields.io/badge/postgresql-4169e1?style=for-the-badge&logo=postgresql&logoColor=white)
- [Spring Cloud](https://www.springcloud.io/)
- [Eureka Server](https://cloud.spring.io/spring-cloud-netflix/reference/html/)
- [Config Server](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)
- [Gateway](https://cloud.spring.io/spring-cloud-gateway/reference/html/)

# 🤸 Para Iniciar

Es necesario tener Git, Maven y Docker instalado en tu computadora antes de correr este proyecto 👀
<br/>

## 💾 Ejecución Local

<br/>
Clonar el repositorio

```bash
  git clone https://github.com/jonato96/fin-core.asyn.git
```

Ir al directorio --services-- dentro del proyecto

```bash
  cd fin-core-async/services
```

Primero debemos instalar la libreria en comun para los microservicios

```bash
  cd commons-library
  mvn clean install
```

Regresamos a la carpeta principal y generamos los empaquetados de cada proyecto

```bash
  cd ..
  mvn clean package
```

En el directorio services encontraremos un archivo docker compose

```bash
  docker-compose build
  docker-compose up
```

Por defecto la aplicación se podrá consumir mediante la ruta http://localhost:9090 habilitada por el api gateway.
Para probar los recursos se adjunta en el directorio de deliverables la colleccion de postman.


## 🧪 Ejecutar Tests

Para ejecutar las pruebas de la aplicación, utilizar el siguiente comando

```bash
  mvn test
```
