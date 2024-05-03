package co.edu.eci.cvds;

import co.edu.eci.cvds.exceptions.ModelException;
import co.edu.eci.cvds.model.Category;
import co.edu.eci.cvds.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*import org.junit.jupiter.api.Test;*/

@SpringBootTest
class ItemTest {

    @Test
    void contextLoads() {
    }

    /**
     * Creates an item and verifies if it was instantiated
     */
    @Test
    void shouldCreateProduct() {
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 10.0, 10.0, 10.0, true, 10.0, category);
            assertInstanceOf(Item.class, item);
        }
        catch(ModelException modelException){
            fail();
        }
    }

    @Test
    void shouldNotCreateProductInvalidValue() {
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", -10.0, 10.0, 10.0, true, 10.0, category);
            fail();
        }
        catch(ModelException modelException){
            assertEquals(ModelException.ITEM_INVALID_VALUE, modelException.getMessage());
        }
    }

    @Test
    void shouldNotCreateProductInvalidCurrency() {
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 10.0, -10.0, 10.0,true,10.0, category);
            fail();
        }
        catch(ModelException modelException){
            assertEquals(ModelException.ITEM_INVALID_CURRENCY, modelException.getMessage());
        }
    }

    @Test
    void shouldNotCreateProductInvalidDiscount() {
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 10.0, 10.0, -10.0, true, 10.0, category);
            fail();
        }
        catch(ModelException modelException){
            assertEquals(ModelException.ITEM_INVALID_DISCOUNT, modelException.getMessage());
        }
    }

    @Test
    void shouldNotCreateProductTooMuchDiscount() {
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 10.0, 10.0, 101.0, true, 10.0, category);
            fail();
        }
        catch(ModelException modelException){
            assertEquals(ModelException.ITEM_INVALID_DISCOUNT, modelException.getMessage());
        }
    }

    @Test
    void shouldNotCreateProductInvalidTax() {
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 10.0, 10.0, 10.0,true,  -10.0, category);
            fail();
        }
        catch(ModelException modelException){
            assertEquals(ModelException.ITEM_INVALID_TAX, modelException.getMessage());
        }
    }

    @Test
    void shouldCalculateTotal() {
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 100000.0, 10.0, 10.0, true, 19.0, category);
            double total = item.calculateTotal();
            assertEquals(total, 107100.0, 0.01);
        }
        catch(ModelException modelException){
            fail();
        }
    }

    @Test
    void shouldCalculateSubTotal() {
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 100000.0, 10.0, 10.0, true, 10.0, category);
            double total = item.calculateSubtotal();
            assertEquals(total, 107100.0, 0.01);
        }
        catch(ModelException modelException){
            fail();
        }
    }

    @Test
    void shouldCalculateTotalFree() {
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 100000.0, 10.0, 100.0, true, 10.0, category);
            double total = item.calculateTotal();
            assertEquals(total, 0.0, 0.01);
        }
        catch(ModelException modelException){
            fail();
        }
    }

}
