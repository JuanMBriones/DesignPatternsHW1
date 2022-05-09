FROM diamol/maven AS builder
WORKDIR /usr/src/output
COPY pom.xml .
RUN mvn dependency:resolve-plugins
RUN mvn dependency:go-offline

COPY . .
RUN mvn package

# app
FROM diamol/openjdk

WORKDIR /app
COPY --from=builder /usr/src/output/target/design_patterns-0.0.1-SNAPSHOT-jar-with-dependencies.jar ./design_patterns-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/design_patterns-0.0.1-SNAPSHOT.jar"]