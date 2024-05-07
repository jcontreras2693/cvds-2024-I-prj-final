package co.edu.eci.cvds;

import co.edu.eci.cvds.service.CategoryService;
import co.edu.eci.cvds.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*import org.junit.jupiter.api.Test;*/

@SpringBootTest
class CategoryTest {

    @Autowired
    private CategoryService categoryService;

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

