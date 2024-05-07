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

    @GetMapping("/category/getCategories")
    @ResponseBody
    public List<Category> getCategories(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return categoryService.getAllCategories();
    }

    @PostMapping("/category/putCategory")
    @ResponseBody
    public List<Category> addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/getCategory/{id}")
    @ResponseBody
    public Category getCategory(@PathVariable int id){
        try {
            return categoryService.getCategory(id);
        }
        catch (ServiceException e){
            return null; //Verificar como se van a tratar las excepciones
        }
    }

}
