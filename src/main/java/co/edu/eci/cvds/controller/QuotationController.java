package co.edu.eci.cvds.controller;

import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Item;
import co.edu.eci.cvds.model.Quotation;
import co.edu.eci.cvds.model.QuotationStatus;
import co.edu.eci.cvds.model.Vehicle;
import co.edu.eci.cvds.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/quotation")
public class QuotationController {

    QuotationService quotationService;

    @Autowired
    public QuotationController(QuotationService quotationService){
        this.quotationService = quotationService;
    }

    @GetMapping("/addQuotation")
    public String addQuotation(@ModelAttribute("vehicle") Vehicle vehicle, @ModelAttribute("categoryId") Integer categoryId,
                               Model model, RedirectAttributes redirectAttributes){
        Quotation quotation = new Quotation();
        quotationService.addQuotation(quotation);
        redirectAttributes.addFlashAttribute("quotation", quotation);
        redirectAttributes.addFlashAttribute("vehicle", vehicle);
        redirectAttributes.addFlashAttribute("categoryId", categoryId);
        return "redirect:/category/getAllCategories";
    }

    @GetMapping("/getQuotation/{id}")
    public void getQuotation(@PathVariable int id, Model model){
        try {
            model.addAttribute("quotation", quotationService.getQuotation(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAllQuotations")
    public void getAllQuotations(Model model){
        model.addAttribute("quotations", quotationService.getAllQuotation());
    }

    @PostMapping("/updateQuotation")
    public void updateQuotation(@RequestBody Quotation quotation, Model model){
        try {
            quotationService.updateQuotation(quotation);
            model.addAttribute("quotation", quotation);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/deleteQuotation")
    public void deleteQuotation(@RequestBody Quotation quotation){
        quotationService.deleteQuotation(quotation);
    }

    @PostMapping("/deleteQuotationById/{id}")
    public void deleteQuotation(@PathVariable int id){
        try {
            quotationService.deleteQuotation(id);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getTotal")
    public void calculateTotal(Quotation quotation, Model model){
        model.addAttribute("total", quotationService.calculateTotal(quotation));
    }

    @GetMapping("/getTotalById")
    public String calculateTotalById(@ModelAttribute("quotation") Quotation quotation, @ModelAttribute("vehicle") Vehicle vehicle,
                                   @ModelAttribute("categoryId") Integer categoryId, @ModelAttribute("item") Item item, Model model,
                                   RedirectAttributes redirectAttributes){
        try {
            redirectAttributes.addFlashAttribute("vehicle", vehicle);
            redirectAttributes.addFlashAttribute("item", item);
            redirectAttributes.addFlashAttribute("categoryId", categoryId);
            quotation.setTotal(quotationService.calculateTotal(quotation));
            quotationService.updateQuotation(quotation);
            redirectAttributes.addFlashAttribute("quotation", quotationService.getQuotation(quotation.getQuotationId()));
            return "redirect:/category/getAllCategories";
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getSubTotal")
    public void calculateSubTotal(@RequestBody Quotation quotation, Model model){
        model.addAttribute("subTotal", quotationService.calculateSubTotal(quotation));
    }

    @GetMapping("/getSubTotalById")
    public String calculateSubTotalById(@ModelAttribute("quotation") Quotation quotation, @ModelAttribute("vehicle") Vehicle vehicle,
                                        @ModelAttribute("categoryId") Integer categoryId, @ModelAttribute("item") Item item, Model model,
                                      RedirectAttributes redirectAttributes){
        try {
            redirectAttributes.addFlashAttribute("vehicle", vehicle);
            redirectAttributes.addFlashAttribute("item", item);
            redirectAttributes.addFlashAttribute("categoryId", categoryId);
            quotation.setSubtotal(quotationService.calculateSubTotal(quotation));
            quotationService.updateQuotation(quotation);
            System.out.println(quotationService.calculateSubTotal(quotation));
            redirectAttributes.addFlashAttribute("quotation", quotationService.getQuotation(quotation.getQuotationId()));
            return "redirect:/quotation/getTotalById";
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/updateStatus")
    public void updateStatus(@RequestBody Quotation quotation, @RequestBody QuotationStatus status, Model model){
        quotationService.updateStatus(quotation, status);
        model.addAttribute("quotation", quotation);
    }

    @GetMapping("/updateStatusById")
    public String updateStatusById(@ModelAttribute("quotation") Quotation quotation, @ModelAttribute("vehicle") Vehicle vehicle,
                                   @ModelAttribute("item") Item item, @ModelAttribute("categoryId") String categoryId,
                                   Model model, RedirectAttributes redirectAttributes){
        try {
            quotationService.updateStatus(quotation.getQuotationId(), QuotationStatus.EN_PROCESO);
            redirectAttributes.addFlashAttribute("quotation", quotationService.getQuotation(quotation.getQuotationId()));
            redirectAttributes.addFlashAttribute("vehicle", vehicle);
            redirectAttributes.addFlashAttribute("item", item);
            Integer newCategoryId = Integer.parseInt(categoryId);
            redirectAttributes.addFlashAttribute("categoryId", newCategoryId);
            return "redirect:/quotation/addItemById";
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addItem")
    public void addItem(@RequestBody Quotation quotation, @RequestBody Item item, Model model){
        quotationService.addItem(quotation, item);
        model.addAttribute("quotation", quotation);
    }

    @GetMapping("/addItemById")
    public String addItemById(@ModelAttribute("quotation") Quotation quotation, @ModelAttribute("vehicle") Vehicle vehicle,
                            @ModelAttribute("categoryId") Integer categoryId, @ModelAttribute("item") Item item, Model model,
                            RedirectAttributes redirectAttributes){
        try {
            quotationService.addItem(quotation.getQuotationId(), item);
            redirectAttributes.addFlashAttribute("quotation", quotationService.getQuotation(quotation.getQuotationId()));
            redirectAttributes.addFlashAttribute("vehicle", vehicle);
            redirectAttributes.addFlashAttribute("item", item);
            redirectAttributes.addFlashAttribute("categoryId", categoryId);
            return "redirect:/quotation/getSubTotalById";
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/deleteItem")
    public void deleteItem(@RequestBody Quotation quotation, @RequestBody Item item, Model model){
        quotationService.deleteItem(quotation, item);
        model.addAttribute("quotation", quotation);
    }

    @GetMapping("/deleteItemById")
    public String deleteItemById(@ModelAttribute("quotation") Quotation quotation, @ModelAttribute("vehicle") Vehicle vehicle,
                                 @ModelAttribute("item") Item item, @ModelAttribute("categoryId") String categoryId, Model model,
                                 RedirectAttributes redirectAttributes){
        try {
            quotationService.deleteItem(quotation.getQuotationId(), item);
            redirectAttributes.addFlashAttribute("quotation", quotationService.getQuotation(quotation.getQuotationId()));
            redirectAttributes.addFlashAttribute("vehicle", vehicle);
            redirectAttributes.addFlashAttribute("item", item);
            Integer newCategoryId = Integer.parseInt(categoryId);
            redirectAttributes.addFlashAttribute("categoryId", newCategoryId);
            return "redirect:/quotation/getSubTotalById";
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
