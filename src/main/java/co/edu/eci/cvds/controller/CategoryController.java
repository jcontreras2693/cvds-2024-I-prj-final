package co.edu.eci.cvds.controller;


import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Category;
import co.edu.eci.cvds.model.Vehicle;
import co.edu.eci.cvds.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

//    @GetMapping("/getAllCategories")
//    public String getAllCategories(@ModelAttribute("vehicle") Vehicle vehicle, Model model){
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "quote";
//    }

    @GetMapping("/getAllCategories")
    public String getAllCategories(@ModelAttribute("vehicle") Vehicle vehicle,
                                   @ModelAttribute("categoryId") Integer categoryId, Model model, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("categories", categoryService.getAllCategories());
        redirectAttributes.addFlashAttribute("vehicle", vehicle);
        categoryId = categoryId != null ? categoryId : 1;
        return "redirect:/item/" + categoryId + "/" + vehicle.getVehicleId();
    }

    @PostMapping("/putCategory")
    public String addCategory(@RequestBody Category category, Model model){
        categoryService.addCategory(category);
        //model.addAttribute("categories", categoryService.getAllCategories());
        return "redirect://quote";
    }


    @GetMapping("/getCategory/{id}")
    public void getCategory(@PathVariable int id, Model model){
        try {
            model.addAttribute("category", categoryService.getCategory(id));
        }
        catch (ServiceException e){
            System.out.println("error");
        }
    }

    
    @PostMapping("/updateCategory")
    public void updateCategory(@RequestBody Category category){
        try {
            categoryService.updateCategory(category);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/deleteCategory")
    public void deleteCategory(@RequestBody Category category){
        categoryService.deleteCategory(category);
    }

    @PostMapping("/deleteCategoryById/{id}")
    public void deleteCategoryById(@PathVariable int id){
        try {
            categoryService.deleteCategory(id);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    
}
