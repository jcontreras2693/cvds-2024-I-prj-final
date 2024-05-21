package co.edu.eci.cvds;

import co.edu.eci.cvds.model.Configuration;
import co.edu.eci.cvds.model.User;
import co.edu.eci.cvds.repository.UserRepository;
import co.edu.eci.cvds.service.ConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class SpringApplicationCvds {
	private final ConfigurationService configurationService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	public SpringApplicationCvds(
			ConfigurationService configurationService
	) {
		this.configurationService = configurationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationCvds.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		User user = new User("Juan", 123, 123456789, "juan@mail.com", "juan");
		userRepository.save(user);
		return (args) -> {
			System.out.println("Running...");
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
