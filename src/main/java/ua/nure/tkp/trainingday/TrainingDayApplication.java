package ua.nure.tkp.trainingday;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainingDayApplication {

    public static void main(String[] args) {
       Flyway flyway = Flyway.configure()
               .dataSource("jdbc:mysql://localhost/training_day", "root", "Agent8998Agent_007")
               .baselineOnMigrate(true)
               .locations("db/migration")
               .load();
       flyway.migrate();
        SpringApplication.run(TrainingDayApplication.class, args);
    }

}
