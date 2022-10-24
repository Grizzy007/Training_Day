FROM openjdk:latest
COPY ./target/TrainingDay-1.0.0.jar TrainingDay-1.0.0.jar
ENTRYPOINT ["java","-jar","TrainingDay-1.0.0.jar"]


