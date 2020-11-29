FROM maven:3.6.3-jdk-8 AS java-build

COPY cope-domain /build/cope-domain
COPY cope-server/pom.xml /build/
COPY cope-server/src /build/src/

WORKDIR /build/cope-domain
RUN mvn install

WORKDIR /build

RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jre-alpine AS java-run

WORKDIR /app

COPY --from=java-build /build/target/cope-server-1.0.0-SNAPSHOT.jar/ /app/

ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "cope-server-1.0.0-SNAPSHOT.jar"]