FROM  adoptopenjdk/openjdk11:alpine-jre
LABEL com.example.version="0.0.1-bookingservice"
EXPOSE  8082
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

CMD   ["java","-jar","app.jar"]