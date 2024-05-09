package co.edu.eci.cvds.controller;


import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Category;
import co.edu.eci.cvds.model.Item;
import co.edu.eci.cvds.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/addItem")
    public void addItem(@RequestBody Item item){
        itemService.addItem(item);
    }

    @GetMapping("/getItem/{id}")
    public void getItem(@PathVariable int id, Model model) {
        try {
            model.addAttribute("item", itemService.getItem(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAllItems")
    public void getAllItems(Model model){
        model.addAttribute("items", itemService.getAllItems());
    }

    @PostMapping("/updateItem")
    public void updateItem(@RequestBody Item item){
        try {
            itemService.updateItem(item);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/deleteItem")
    public void deleteItem(@RequestBody Item item){
        itemService.deleteItem(item);
    }

    @PostMapping("/deleteItemById/{id}")
    public void deleteItemById(@PathVariable int id){
        try {
            itemService.deleteItem(id);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getSubTotal")
    public void getSubTotal(@RequestBody Item item, Model model){
        model.addAttribute("subTotal", itemService.calculateSubtotal(item));
    }

    @GetMapping("/getSubTotalById/{id}")
    public void getSubtotalById(@PathVariable int id, Model model){
        try {
            model.addAttribute("subTotal", itemService.calculateSubtotal(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getTotal")
    public void getTotal(@RequestBody Item item, Model model){
        model.addAttribute("total", itemService.calculateTotal(item));
    }

    @GetMapping("/getTotalById/{id}")
    public void getTotalById(@PathVariable int id, Model model){
        try {
            model.addAttribute("total", itemService.calculateTotal(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addCategory")
    public void addCategory(@RequestBody Item item, @RequestBody Category category){
        itemService.addCategory(item, category);
    }

    @PostMapping("/addCategoryById/{id}")
    public void addCategoryById(@PathVariable int id, @RequestBody Category category){
        try {
            itemService.addCategory(id, category);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    
}

