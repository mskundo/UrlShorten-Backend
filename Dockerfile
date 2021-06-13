FROM maven:3-openjdk-8-slim  AS MAVEN_BUILD


COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/urlshorten-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "urlshorten-0.0.1-SNAPSHOT.jar"]