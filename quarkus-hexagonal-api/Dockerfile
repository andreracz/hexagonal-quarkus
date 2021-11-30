####
# This Dockerfile is used in order to build a distroless container that runs the Quarkus application in native (no JVM) mode
#
# Before building the container image run:
#
# ./mvnw package -Pnative
#
# Then, build the image with:
#
# docker build -f src/main/docker/Dockerfile.native-distroless -t quarkus/quarkus-hexagonal-test .
#
# Then run the container using:
#
# docker run -i --rm -p 8080:8080 quarkus/quarkus-hexagonal-test
#
###
FROM adoptopenjdk/maven-openjdk11 as BUILD

WORKDIR /usr/src/app
COPY ./pom.xml .
RUN mvn dependency:resolve 
COPY . .
RUN mvn package -Pnative

FROM quay.io/quarkus/quarkus-distroless-image:1.0
COPY --from=BUILD /usr/src/app/target/*-runner /application

EXPOSE 8080
USER nonroot

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
