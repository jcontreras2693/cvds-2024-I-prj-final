package co.edu.eci.cvds.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "VEHICLE")
public class Vehicle {

    @Id
    @GeneratedValue
    @Column(name = "VEHICLE_ID", nullable = false, unique = true)
    private int vehicleId;

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "MODEL", nullable = false)
    private String model;

    @Column(name = "VEHICLE_YEAR", nullable = false)
    private int year;

    @Column(name = "CYLINDER_CAPACITY", nullable = false)
    private String cylinderCapacity;

    @ManyToMany(mappedBy = "vehicles")
    private List<Item> items;

    public Vehicle() {
    }

    public Vehicle(int vehicleId, String brand, String model, int year, String cylinderCapacity) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.cylinderCapacity = cylinderCapacity;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(String cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }
}
