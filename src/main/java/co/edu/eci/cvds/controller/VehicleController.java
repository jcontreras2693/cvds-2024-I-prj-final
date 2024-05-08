package co.edu.eci.cvds.controller;


import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Vehicle;
import co.edu.eci.cvds.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicle/addVehicle")
    public void addVehicle(@RequestBody Vehicle vehicle){
        vehicleService.addVehicle(vehicle);
    }

    @GetMapping("/vehicle/getBrands")
    public String getBrands(Model model){
        List<String> brands = vehicleService.getBrands();
        model.addAttribute("brands", brands);
        return "vehicles";
    }

    @GetMapping("/vehicle/getModels/{brand}")
    public String getModels(@PathVariable String brand, Model model){
        List<String> models = vehicleService.getModels(brand);
        model.addAttribute("models", models);
        return "vehicles";
    }

    @GetMapping("/vehicle/getYears/{brand}/{model}")
    public String getYears(@PathVariable String brand, @PathVariable String modelVehicle, Model model){
        List<Integer> years = vehicleService.getYears(brand, modelVehicle);
        model.addAttribute("years", years);
        return "vehicles";
    }

    @GetMapping("/vehicle/getCylinderCapacity/{brand}/{model}/{year}")
    public String getCylinderCapacity(@PathVariable String brand, @PathVariable String modelVehicle, @PathVariable int year, Model model){
        List<String> cylinders = vehicleService.getCylinders(brand, modelVehicle, year);
        model.addAttribute("cylinders", cylinders);
        return "vehicles";
    }

    @GetMapping("/vehicle/getAllVehicles")
    public String getAllVehicles(Model model){
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "vehicles";
    }

    @GetMapping("/vehicle/getVehicleById/{id}")
    public String getVehicleById(@PathVariable int id, Model model){
        try{
            Vehicle vehicle = vehicleService.getVehicle(id);
            model.addAttribute("vehicle", vehicle);
            return "vehicles";
        }
        catch(ServiceException serviceException){
            throw new RuntimeException(serviceException);
        }
    }

    @PostMapping("/vehicle/updateVehicle")
    public String updateVehicle(@RequestBody Vehicle vehicle, Model model){
        try{
            vehicleService.updateVehicle(vehicle);
            model.addAttribute("vehicle", vehicle);
            return "vehicles";
        }
        catch(ServiceException serviceException){
            throw new RuntimeException(serviceException);
        }
    }

    @PostMapping("/vehicle/deleteVehicle")
    public String deleteVehicle(@RequestBody Vehicle vehicle){
        vehicleService.deleteVehicle(vehicle);
        return "vehicles";
    }

    @PostMapping("/vehicle/deleteVehicle/{id}")
    public String deleteVehicleById(@PathVariable int id){
        try{
            vehicleService.deleteVehicle(id);
            return "vehicles";
        }
        catch(ServiceException serviceException){
            throw new RuntimeException(serviceException);
        }
    }
}

