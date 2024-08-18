package academy.atl.webscapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebscapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebscapperApplication.class, args);
	}

}
