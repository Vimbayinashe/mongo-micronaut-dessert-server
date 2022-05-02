FROM maven:3.8.5-eclipse-temurin-17-alpine
COPY ./ /app
RUN mvn -f /app/pom.xml clean package
ENV MAVEN_CONFIG ""
RUN chmod +x /app/mvnw
RUN cd /app \
  && ./mvnw package -Dpackaging=docker-native -Pgraalvm

