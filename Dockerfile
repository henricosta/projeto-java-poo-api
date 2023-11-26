FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY target/plataforma_vagas.jar /app/plataforma_vagas.jar

CMD ["java", "-jar", "plataforma_vagas.jar"]