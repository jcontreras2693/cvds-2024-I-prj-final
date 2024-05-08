package co.edu.eci.cvds.controller;

import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Item;
import co.edu.eci.cvds.model.Quotation;
import co.edu.eci.cvds.model.QuotationStatus;
import co.edu.eci.cvds.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quotation")
public class QuotationController {

    QuotationService quotationService;

    @Autowired
    public QuotationController(QuotationService quotationService){
        this.quotationService = quotationService;
    }

    @PostMapping("/quotation/addQuotation")
    public void addQuotation(@RequestBody Quotation quotation, Model model){
        quotationService.addQuotation(quotation);
    }

    @GetMapping("/quotation/getQuotation/{id}")
    public void getQuotation(@PathVariable int id, Model model){
        try {
            model.addAttribute("quotation", quotationService.getQuotation(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/quotation/getAllQuotations")
    public void getAllQuotations(Model model){
        model.addAttribute("quotations", quotationService.getAllQuotation());
    }

    @PostMapping("/quotation/updateQuotation")
    public void updateQuotation(@RequestBody Quotation quotation, Model model){
        try {
            quotationService.updateQuotation(quotation);
            model.addAttribute("quotation", quotation);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/quotation/deleteQuotation")
    public void deleteQuotation(@RequestBody Quotation quotation){
        quotationService.deleteQuotation(quotation);
    }

    @PostMapping("/quotation/deleteQuotationById/{id}")
    public void deleteQuotation(@PathVariable int id){
        try {
            quotationService.deleteQuotation(id);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/quotation/getTotal")
    public void calculateTotal(@RequestBody Quotation quotation, Model model){
        model.addAttribute("total", quotationService.calculateTotal(quotation));
    }

    @GetMapping("/quotation/getTotalById/{id}")
    public void calculateTotalById(@PathVariable int id, Model model){
        try {
            model.addAttribute("total", quotationService.calculateTotal(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/quotation/getSubTotal")
    public void calculateSubTotal(@RequestBody Quotation quotation, Model model){
        model.addAttribute("subTotal", quotationService.calculateSubTotal(quotation));
    }

    @GetMapping("/quotation/getSubTotalById/{id}")
    public void calculateSubTotalById(@PathVariable int id, Model model){
        try {
            model.addAttribute("subTotal", quotationService.calculateSubTotal(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/quotation/updateStatus")
    public void updateStatus(@RequestBody Quotation quotation, @RequestBody QuotationStatus status, Model model){
        quotationService.updateStatus(quotation, status);
        model.addAttribute("quotation", quotation);
    }

    @PostMapping("/quotation/updateStatusById/{id}")
    public void updateStatusById(@PathVariable int id, @RequestBody QuotationStatus status, Model model){
        try {
            quotationService.updateStatus(id, status);
            model.addAttribute("quotation", quotationService.getQuotation(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/quotation/addItem")
    public void addItem(@RequestBody Quotation quotation, @RequestBody Item item, Model model){
        quotationService.addItem(quotation, item);
        model.addAttribute("quotation", quotation);
    }

    @PostMapping("/quotation/addItemById/{id}")
    public void addItemById(@PathVariable int id, @RequestBody Item item, Model model){
        try {
            quotationService.addItem(id, item);
            model.addAttribute("quotation", quotationService.getQuotation(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/quotation/deleteItem")
    public void deleteItem(@RequestBody Quotation quotation, @RequestBody Item item, Model model){
        quotationService.deleteItem(quotation, item);
        model.addAttribute("quotation", quotation);
    }

    @PostMapping("/quotation/deleteItemById/{id}")
    public void deleteItemById(@PathVariable int id, Item item, Model model){
        try {
            quotationService.deleteItem(id, item);
            model.addAttribute("quotaion", quotationService.getQuotation(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
