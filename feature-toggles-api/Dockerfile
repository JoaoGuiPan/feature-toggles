FROM maven:3.9.0-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM amazoncorretto:17
RUN mkdir -p /app
WORKDIR /app
COPY --from=build /home/app/target/*.jar ./app.jar
EXPOSE $PORT
CMD [ "java", "-jar", "./app.jar" ]