package ru.dmitriy.selectioncommittee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.dmitriy.selectioncommittee.configs.ConfigService;

@SpringBootApplication
@Import({ConfigService.class})
public class SelectioncommitteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelectioncommitteApplication.class, args);
	}
}
