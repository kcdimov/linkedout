package bg.linkedout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class LinkedoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkedoutApplication.class, args);
    }

}
