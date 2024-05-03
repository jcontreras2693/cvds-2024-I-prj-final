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
        Double total = quotation.calculateTotal();
        assertEquals(0, total, 0.01);
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
