FROM alpine as build
COPY ./ /src
COPY ./ /src
RUN mvn -f /src/pom.xml clean compile

FROM eclipse-temurin:17-jre-alpine
COPY --from=build /src /
COPY ./ /src/.mvn
RUN chmod +x /src/mvnw \
     &&  /src/mvnw package -Dpackaging=docker-native -Pgraalvm

