# Etapa de Construção
FROM maven:3.9.8-eclipse-temurin-21 AS build

RUN mkdir /opt/app

COPY . /opt/app

WORKDIR /opt/app

# Compila o projeto e gera o .jar
RUN mvn clean package

# Etapa de Execução
FROM eclipse-temurin:21-jre-alpine

RUN mkdir /opt/app

# Copia o arquivo .jar gerado para a nova imagem
COPY --from=build /opt/app/target/app.jar /opt/app/app.jar

WORKDIR /opt/app

ENV PROFILE=prd

EXPOSE 8080

# Configura o ponto de entrada do contêiner
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
