package co.edu.eci.cvds;

import co.edu.eci.cvds.exceptions.ModelException;
import co.edu.eci.cvds.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*import org.junit.jupiter.api.Test;*/

@SpringBootTest
class VehicleTest {

    @Test
    void contextLoads() {
    }

    /**
     * Creates an item and verifies if it was instantiated
     */
    @Test
    void shouldCreateVehicle() {
        try {
            Vehicle vehicle = new Vehicle("AUDI", "A1", 2022, 150);
            assertInstanceOf(Vehicle.class, vehicle);
        }
        catch (ModelException modelException){
            fail();
        }
    }

    @Test
    void shouldNotCreateVehicleInvalidYear() {
        try {
            Vehicle vehicle = new Vehicle("AUDI", "A1", -2022, 150);
            fail();
        }
        catch (ModelException modelException){
            assertEquals(ModelException.VEHICLE_INVALID_YEAR, modelException.getMessage());
        }
    }

    @Test
    void shouldNotCreateVehicleInvalidYCylinderCapacity() {
        try {
            Vehicle vehicle = new Vehicle("AUDI", "A1", -2022, 150);
            fail();
        }
        catch (ModelException modelException){
            assertEquals(ModelException.VEHICLE_INVALID_CYLINDER_CAPACITY, modelException.getMessage());
        }
    }

}

