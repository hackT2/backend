package hackathon.dragon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DragonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DragonApplication.class, args);
    }

}
