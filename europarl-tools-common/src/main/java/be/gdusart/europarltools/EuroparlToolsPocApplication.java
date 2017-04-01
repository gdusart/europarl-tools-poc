package be.gdusart.europarltools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EuroparlToolsPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(EuroparlToolsPocApplication.class, args);
	}
}
