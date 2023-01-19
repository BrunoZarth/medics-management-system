FROM openjdk:17-jdk-alpine

WORKDIR "/home/medics-app/backend"

COPY . .

RUN apk add maven

WORKDIR "/home/medics-app/backend"

RUN mvn clean package

EXPOSE 8080

CMD [ "java", "-jar",  "/home/medics-app/backend/target/medics-management-0.0.1-SNAPSHOT.jar"]