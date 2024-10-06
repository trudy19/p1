# Utiliser l'image de base OpenJDK 17 JRE
FROM openjdk:17-jre-slim
COPY "./target/hinking-0.0.1-SNAPSHOT.jar"  "hinking-0.0.1-SNAPSHOT.jar"
ENTRYPOINT ["java", "-jar","hinking-0.0.1-SNAPSHOT.jar"]