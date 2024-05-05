package co.edu.eci.cvds.service;

import co.edu.eci.cvds.model.Vehicle;
import co.edu.eci.cvds.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle addVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public List<String> getBrands(){
        return vehicleRepository.findDistinctBrand();
    }

    public List<String> getModels(String brand){
        return vehicleRepository.findDistinctModelByBrand(brand);
    }

    public List<Integer> getYears(String brand, String model){
        return vehicleRepository.findDistinctYearByBrandAndModel(brand, model);
    }

    public List<String> getCylinders(String brand, String model, int year){
        return vehicleRepository.findDistinctcylinderCapacityByBrandAndModelAndYear(brand, model, year);
    }

    public Vehicle getVehicle(int id){
        Optional<Vehicle> result = vehicleRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    public Vehicle updateVehicle(Vehicle vehicle){
        Vehicle update = getVehicle(vehicle.getVehicleId());
        if(vehicle!=null){
            vehicleRepository.delete(update);
        }
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle){
        vehicleRepository.delete(vehicle);
    }

    public void deleteVehicle(int id){
        Vehicle vehicle = getVehicle(id);
        deleteVehicle(vehicle);
    }

}
