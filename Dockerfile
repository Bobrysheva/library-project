FROM eclipse-temurin:21-jre
ARG JAR_FILE=target/ -SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]