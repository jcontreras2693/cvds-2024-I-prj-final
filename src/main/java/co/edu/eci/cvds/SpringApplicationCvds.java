package co.edu.eci.cvds;

import co.edu.eci.cvds.exceptions.ModelException;
import co.edu.eci.cvds.model.*;
import co.edu.eci.cvds.repository.CategoryRepository;
import co.edu.eci.cvds.repository.ItemRepository;
import co.edu.eci.cvds.repository.QuotationRepository;
import co.edu.eci.cvds.repository.UserRepository;
import co.edu.eci.cvds.service.ConfigurationService;
import co.edu.eci.cvds.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class SpringApplicationCvds {
	private final ConfigurationService configurationService;

	@Autowired
	UserRepository userRepository;
	@Autowired
	QuotationRepository quotationRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	CategoryRepository categoryRepository;

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
	public CommandLineRunner run() throws ModelException {
		User user = new User("Juan", 123, 123456789, "juan@mail.com", "juan");
		userRepository.save(user);
		Quotation quotation = new Quotation();
		quotation.setTotal(25000.0d);
		quotationRepository.save(quotation);
		Category category = new Category("categoria");
		categoryRepository.save(category);
		Item item = new Item("Nombre del artículo",
				"Descripción corta del artículo",
				"Descripción técnica del artículo",
				"Ruta/de/la/imagen.jpg",
				100.0, // valor
				1.0,   // moneda
				0.0,   // descuento
				true,  // disponibilidad
				0.0,   // impuesto
				category);
		itemRepository.save(item);
		return (args) -> {
			System.out.println("Running...");
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
