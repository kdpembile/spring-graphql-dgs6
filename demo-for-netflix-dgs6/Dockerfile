FROM eclipse-temurin:17-jdk-jammy

WORKDIR /demo-for-netflix-dgs6

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY ./src ./src

RUN ./mvnw dependency:resolve

CMD ["./mvnw", "spring-boot:run"]
