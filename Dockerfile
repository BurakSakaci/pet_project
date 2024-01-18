FROM amazoncorretto:21-alpine-jdk
RUN mkdir /app
COPY target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
