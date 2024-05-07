package co.edu.eci.cvds;

import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.service.CategoryService;
import co.edu.eci.cvds.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*import org.junit.jupiter.api.Test;*/

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldAddACategory(){
        Category category = new Category("category");
        categoryService.addCategory(category);
        assertEquals(1, categoryService.getAllCategories().size());
    }

    @Test
    void shouldGetACategoryById() {
        Category category = new Category("category");
        categoryService.addCategory(category);
        try{
            Category newCategory = categoryService.getCategory(1);
            assertEquals(category.getName(), newCategory.getName());
        }
        catch (ServiceException serviceException){
            fail();
        }
    }

    @Test
    void shouldNotGetACategoryById() {
        try{
            categoryService.getCategory(1);
            fail();
        }
        catch (ServiceException serviceException){
            assertEquals(ServiceException.nonExistentCategory, serviceException.getMessage());
        }
    }

    @Test
    void shouldGetAllCategories() {
        Category category = new Category("category");
        Category category1 = new Category("category1");
        Category category2 = new Category("category2");
        categoryService.addCategory(category);
        categoryService.addCategory(category1);
        categoryService.addCategory(category2);
        int size = categoryService.getAllCategories().size();
        assertEquals(3, size);
    }

    @Test
    void shouldUpdateACategory() {
        Category category = new Category("category");
        categoryService.addCategory(category);
        try {
            category.setName("New_Category");
            categoryService.updateCategory(category);
            assertEquals("New_Category", categoryService.getCategory(1).getName());
        }
        catch(ServiceException serviceException){
            fail();
        }
    }

    @Test
    void shouldNotUpdateACategory() {
        try {
            categoryService.updateCategory(new Category("category"));
            fail();
        }
        catch(ServiceException serviceException){
            assertEquals(ServiceException.nonExistentCategory, serviceException.getMessage());
        }
    }

    @Test
    void shouldDeleteACategoryById(){
        Category category = new Category("category");
        categoryService.addCategory(category);
        try{
            categoryService.deleteCategory(1);
            assertEquals(0, categoryService.getAllCategories().size());
        }
        catch(ServiceException serviceException){
            fail();
        }
    }

    @Test
    void shouldNotDeleteACategoryById() {
        try{
            categoryService.deleteCategory(1);
            fail();
        }
        catch(ServiceException serviceException){
            assertEquals(ServiceException.nonExistentCategory, serviceException.getMessage());
        }
    }

    @Test
    void shouldDeleteACategory(){
        Category category = new Category("category");
        categoryService.addCategory(category);
        categoryService.deleteCategory(category);
        assertEquals(0,categoryService.getAllCategories().size());
    }

}

