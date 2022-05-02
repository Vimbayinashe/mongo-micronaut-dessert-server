FROM alpine:latest
COPY ./ /src
RUN chmod +x /src/mvnw && \
     /src/mvnw package -Dpackaging=docker-native -Pgraalvm

