FROM eclipse-temurin:17-jre-alpine
COPY ./ /src
RUN chmod +x /src/mvnw \
     &&  /src/mvnw package -Dpackaging=docker-native -Pgraalvm

