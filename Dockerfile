FROM openjdk:latest
COPY ./target/TrainingDay-1.0.0.jar TrainingDay-1.0.0.jar
ENTRYPOINT ["java","-jar","TrainingDay-1.0.0.jar"]

#docker run --name td_mysql --network training_day_mysql -v td_mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=Agent8998Agent_007 -e MYSQL_DATABASE=training_day -e MYSQL_ROOT_USER=root -d db-td
#docker run --network training_day_mysql -d -p 8080:8080 --name training_day --link td_mysql:mysql td-app

#docker run --name td_mysql --network training_day_mysql -e MYSQL_ROOT_PASSWORD=Agent8998Agent_007 -e MYSQL_DATABASE=training_day -d td-db
#docker run --network training_day_mysql --name td_app -p 8080:8080 -d td_app

