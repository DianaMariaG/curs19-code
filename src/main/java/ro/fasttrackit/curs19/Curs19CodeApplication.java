package ro.fasttrackit.curs19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs19.homework.model.Transaction;
import ro.fasttrackit.curs19.homework.model.Type;
import ro.fasttrackit.curs19.service.CountryProvider;

import java.util.ArrayList;
import java.util.List;

import static ro.fasttrackit.curs19.homework.model.Type.*;

@SpringBootApplication
public class Curs19CodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Curs19CodeApplication.class, args);
	}


	@Bean
	@ConditionalOnProperty(name = "source", havingValue = "!file", matchIfMissing = true)
	CountryProvider emptyCountryProvider() {
		return List::of;
	}

}
