package co.edu.eci.cvds.repository;

import co.edu.eci.cvds.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<String> findDistinctBrand();

    List<String> findDistinctModelByBrand(String brand);

    List<Integer> findDistinctYearByBrandAndModel(String brand, String model);

    List<String> findDistinctcylinderCapacityByBrandAndModelAndYear(String brand, String model, int year);


}
