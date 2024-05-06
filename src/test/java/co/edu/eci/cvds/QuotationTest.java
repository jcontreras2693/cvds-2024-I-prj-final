package co.edu.eci.cvds;

import co.edu.eci.cvds.exceptions.ModelException;
import co.edu.eci.cvds.model.Category;
import co.edu.eci.cvds.model.Item;
import co.edu.eci.cvds.model.Quotation;
import co.edu.eci.cvds.model.QuotationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*import org.junit.jupiter.api.Test;*/

@SpringBootTest
class QuotationTest {

    @Test
    void contextLoads() {
    }

    /**
     * Creates an item and verifies if it was instantiated
     */
    @Test
    void shouldCreateQuotation() {
        Quotation quotation = new Quotation();
        assertInstanceOf(Quotation.class, quotation);
    }

    @Test
    void shouldBeginWithStatusCreated(){
        Quotation quotation = new Quotation();
        assertEquals(quotation.getStatus(), QuotationStatus.CREADO);
    }

    @Test
    void shouldChangeStatus(){
        Quotation quotation = new Quotation();
        quotation.updateStatus(QuotationStatus.EN_PROCESO);
        assertEquals(quotation.getStatus(), QuotationStatus.EN_PROCESO);
    }

    @Test
    void shouldCalculateTotal(){
        Quotation quotation = new Quotation();
        Category category = new Category("category");
        try{
            Item item1 = new Item("Item1", "shortDescription", "image", "technical", 205000.0, 10.0, 15.0, true, 19.0, category);
            Item item2 = new Item("Item2", "shortDescription", "image", "technical", 150000.0, 10.0, 0.0, true, 19.0, category);
            Item item3 = new Item("Item3", "shortDescription", "image", "technical", 125000.0, 10.0, 50.0, true, 19.0, category);
            quotation.addItem(item1);
            quotation.addItem(item2);
            quotation.addItem(item3);
            Double total = quotation.calculateTotal();
            assertEquals(total, 460232.5, 0.01);
        }
        catch(ModelException modelException) {
            fail();
        }
    }

    @Test
    void shouldCalculateSubtotal(){
        Quotation quotation = new Quotation();
        Category category = new Category("category");
        try{
            Item item1 = new Item("Item1", "shortDescription", "image", "technical", 205000.0, 10.0, 15.0, true, 19.0, category);
            Item item2 = new Item("Item2", "shortDescription", "image", "technical", 150000.0, 10.0, 0.0, true, 19.0, category);
            Item item3 = new Item("Item3", "shortDescription", "image", "technical", 125000.0, 10.0, 50.0, true, 19.0, category);
            quotation.addItem(item1);
            quotation.addItem(item2);
            quotation.addItem(item3);
            Double subtotal = quotation.calculateSubtotal();
            assertEquals(subtotal, 386750.0, 0.01);
        }
        catch(ModelException modelException){
            fail();
        }
    }

    @Test
    void shouldAddItem(){
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 10.0, 10.0, 10.0, true, 10.0, category);
            Quotation quotation = new Quotation();
            quotation.addItem(item);
            assertFalse(quotation.getItems().isEmpty());
        }
        catch(ModelException modelException){
            fail();
        }
    }

    @Test
    void shouldDeleteItem(){
        Category category = new Category("category");
        try {
            Item item = new Item("name", "shortDescription", "image", "technical", 10.0, 10.0, 10.0, true, 10.0, category);
            Quotation quotation = new Quotation();
            quotation.addItem(item);
            quotation.deleteItem(item);
            assertTrue(quotation.getItems().isEmpty());
        }
        catch(ModelException modelException){
            fail();
        }
    }
}
