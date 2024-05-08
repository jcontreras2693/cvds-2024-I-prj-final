package co.edu.eci.cvds.controller;


import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Category;
import co.edu.eci.cvds.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/category/getAllCategories")
    public void getAllCategories(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
    }

    @PostMapping("/category/putCategory")
    public void addCategory(@RequestBody Category category, Model model){
        categoryService.addCategory(category);
        //model.addAttribute("categories", categoryService.getAllCategories());
    }

    @GetMapping("/category/getCategory/{id}")
    public void getCategory(@PathVariable int id, Model model){
        try {
            model.addAttribute("category", categoryService.getCategory(id));
        }
        catch (ServiceException e){
            System.out.println("error");
        }
    }

    @PostMapping("/category/updateCategory")
    public void updateCategory(@RequestBody Category category){
        try {
            categoryService.updateCategory(category);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/category/deleteCategory")
    public void deleteCategory(@RequestBody Category category){
        categoryService.deleteCategory(category);
    }

    @PostMapping("/category/deleteCategoryById/{id}")
    public void deleteCategoryById(@PathVariable int id){
        try {
            categoryService.deleteCategory(id);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }


}
