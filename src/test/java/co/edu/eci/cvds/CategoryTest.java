package co.edu.eci.cvds;

import co.edu.eci.cvds.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*import org.junit.jupiter.api.Test;*/

@SpringBootTest
class CategoryTest {

    @Test
    void contextLoads() {
    }

    /**
     * Creates an item and verifies if it was instantiated
     */
    @Test
    void shouldCreateCategory() {
        Category category = new Category("category");
        assertInstanceOf(Category.class, category);
    }

}

