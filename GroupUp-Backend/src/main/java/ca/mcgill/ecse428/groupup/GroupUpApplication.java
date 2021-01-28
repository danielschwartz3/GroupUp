package ca.mcgill.ecse428.groupup;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class GroupUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupUpApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

}