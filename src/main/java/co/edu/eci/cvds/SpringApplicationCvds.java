package co.edu.eci.cvds;

import co.edu.eci.cvds.model.Category;
import co.edu.eci.cvds.model.Item;
import co.edu.eci.cvds.model.Vehicle;
import co.edu.eci.cvds.service.CategoryService;
import co.edu.eci.cvds.service.ItemService;
import co.edu.eci.cvds.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
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
	private final VehicleService vehicleService;
	private final ItemService itemService;
	private final CategoryService categoryService;


	@Autowired
	public SpringApplicationCvds(
			VehicleService vehicleService,
			ItemService itemService,
			CategoryService categoryService
	) {
		this.vehicleService = vehicleService;
		this.itemService = itemService;
		this.categoryService = categoryService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationCvds.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			System.out.println("Running...");
			Vehicle vehicle = new Vehicle("BMW", "A3-COU", 2022, 100);
			vehicleService.addVehicle(vehicle);
			Category category = new Category("Mantenimiento preventivo");
			categoryService.addCategory(category);
			Item item = new Item("name", "shortDescription", "image", "technical", 10.0, 10.0, 10.0, true, 10.0, category);
			itemService.addItem(item);
			vehicleService.addItem(vehicle, item);
			itemService.addVehicle(item, vehicle);
			System.out.println(vehicleService.getAllVehicles().get(0).getItems());
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
