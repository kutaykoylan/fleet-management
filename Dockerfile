FROM openjdk:11-jdk-slim
COPY target/fleet-management-0.0.1-SNAPSHOT.jar fleet-management.jar
ENTRYPOINT ["java","-jar","/fleet-management.jar"]
