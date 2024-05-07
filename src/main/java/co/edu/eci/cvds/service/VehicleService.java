package co.edu.eci.cvds.service;

import co.edu.eci.cvds.exceptions.ServiceException;
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
        return vehicleRepository.findDistinctCylinderCapacityByBrandAndModelAndYear(brand, model, year);
    }

    public Vehicle getVehicle(int id) throws ServiceException {
        Optional<Vehicle> result = vehicleRepository.findById(id);
        if(result.isEmpty()){
            throw new ServiceException(ServiceException.nonExistentVehicle);
        }
        return result.get();
    }

    public Vehicle updateVehicle(Vehicle vehicle) throws ServiceException {
        Vehicle update = getVehicle(vehicle.getVehicleId());
        vehicleRepository.delete(update);
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle){
        vehicleRepository.delete(vehicle);
    }

    public void deleteVehicle(int id) throws ServiceException {
        Vehicle vehicle = getVehicle(id);
        deleteVehicle(vehicle);
    }

}