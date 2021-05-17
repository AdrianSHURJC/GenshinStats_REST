FROM openjdk:8-jdk-alpine
RUN addgroup -S restapp && adduser -S genshinV1 -G restapp
USER genshinV1:restapp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]