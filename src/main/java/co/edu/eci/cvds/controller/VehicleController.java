package co.edu.eci.cvds.controller;


import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Vehicle;
import co.edu.eci.cvds.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/addVehicle")
    public void addVehicle(@RequestBody Vehicle vehicle){
        vehicleService.addVehicle(vehicle);
    }

    @GetMapping("/")
    public String getBrands(Model model){
        List<String> brands = vehicleService.getBrands();
        model.addAttribute("brands", brands);
        return "brands";
    }

    @PostMapping("/getModels/{brand}")
    public String getModels(@PathVariable String brand, Model model){
        List<String> models = vehicleService.getModels(brand);
        model.addAttribute("brand", brand);
        model.addAttribute("models", models);
        return "models";
    }

    @PostMapping("/getYears/{modelVehicle}")
    public String getYears(String brand, @PathVariable String modelVehicle, Model model){
        List<Integer> years = vehicleService.getYears(brand, modelVehicle);
        model.addAttribute("brand", brand);
        model.addAttribute("model", modelVehicle);
        model.addAttribute("years", years);
        return "years";
    }

    @PostMapping("/getCylinderCapacity/{year}")
    public String getCylinderCapacity(String brand, String modelVehicle, @PathVariable int year, Model model){
        List<String> cylinders = vehicleService.getCylinders(brand, modelVehicle, year);
        model.addAttribute("brand", brand);
        model.addAttribute("modelVehicle", modelVehicle);
        model.addAttribute("year", year);
        model.addAttribute("cylinders", cylinders);
        return "cylinders";
    }

    @GetMapping("/quotation")
    public String quotation(String brand, String modelVehicle, int year, int cylinder, Model model){
        model.addAttribute("brand", brand);
        model.addAttribute("modelVehicle", modelVehicle);
        model.addAttribute("year", year);
        model.addAttribute("cylinder", cylinder);
        return "quote";
    }

    @GetMapping("/getAllVehicles")
    public String getAllVehicles(Model model){
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "/";
    }

    @GetMapping("/getVehicleById/{id}")
    public String getVehicleById(@PathVariable int id, Model model){
        try{
            Vehicle vehicle = vehicleService.getVehicle(id);
            model.addAttribute("vehicle", vehicle);
            return "/";
        }
        catch(ServiceException serviceException){
            throw new RuntimeException(serviceException);
        }
    }

    @PostMapping("/updateVehicle")
    public String updateVehicle(@RequestBody Vehicle vehicle, Model model){
        try{
            vehicleService.updateVehicle(vehicle);
            model.addAttribute("vehicle", vehicle);
            return "/";
        }
        catch(ServiceException serviceException){
            throw new RuntimeException(serviceException);
        }
    }

    @PostMapping("/deleteVehicle")
    public String deleteVehicle(@RequestBody Vehicle vehicle){
        vehicleService.deleteVehicle(vehicle);
        return "/";
    }

    @PostMapping("/deleteVehicle/{id}")
    public String deleteVehicleById(@PathVariable int id){
        try{
            vehicleService.deleteVehicle(id);
            return "/";
        }
        catch(ServiceException serviceException){
            throw new RuntimeException(serviceException);
        }
    }
}

