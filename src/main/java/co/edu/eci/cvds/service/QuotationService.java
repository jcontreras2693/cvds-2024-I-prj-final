package co.edu.eci.cvds.service;

import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Item;
import co.edu.eci.cvds.model.Quotation;
import co.edu.eci.cvds.model.QuotationStatus;
import co.edu.eci.cvds.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;

    @Autowired
    public QuotationService(QuotationRepository quotationRepository){
        this.quotationRepository = quotationRepository;
    }

    public Quotation addQuotation(Quotation quotation){
        return  quotationRepository.save(quotation);
    }

    public Quotation getQuotation(int id) throws ServiceException{
        Optional<Quotation> result = quotationRepository.findById(id);
        if(result.isEmpty()){
            throw new ServiceException(ServiceException.nonExistentQuotation);
        }
        return result.get();
    }

    public List<Quotation> getAllQuotation(){
        return quotationRepository.findAll();
    }

    public Quotation updateQuotation(Quotation quotation) throws ServiceException {
        Quotation update = getQuotation(quotation.getQuotationId());
        quotationRepository.delete(update);
        return quotationRepository.save(quotation);
    }

    public void deleteQuotation(Quotation quotation){
        quotationRepository.delete(quotation);
    }

    public void deleteQuotation(int id) throws ServiceException {
        Quotation quotation = getQuotation(id);
        quotationRepository.delete(quotation);
    }

    public double calculateTotal(Quotation quotation){
        List<Item> items = quotation.getItems();
        double total = 0;
        for(Item item : items){
            double value = item.getValue();
            double discount = value * (item.getDiscount() / 100);
            double valueWithDiscount = value - discount;
            double tax = valueWithDiscount * (item.getTax() / 100);
            total += valueWithDiscount + tax;
        }
        return total;
    }

    public double calculateTotal(int id) throws ServiceException {
        Quotation quotation = getQuotation(id);
        return calculateTotal(quotation);
    }

    public double calculateSubTotal(Quotation quotation){
        List<Item> items = quotation.getItems();
        double total=0;
        for(Item i: items){
            double value = i.getValue();
            double discount = value * (i.getDiscount() / 100);
            total += value - discount;
        }
        return total;
    }

    public double calculateSubTotal(int id) throws ServiceException {
        Quotation quotation = getQuotation(id);
        return calculateSubTotal(quotation);
    }

    public void updateStatus(Quotation quotation, QuotationStatus status){
        quotation.updateStatus(status);
    }

    public void updateStatus(int id, QuotationStatus status) throws ServiceException {
        Quotation quotation = getQuotation(id);
        quotation.updateStatus(status);
    }

    public void addItem(Quotation quotation, Item item){
        quotation.addItem(item);
    }

    public void addItem(int id, Item item) throws ServiceException {
        Quotation quotation = getQuotation(id);
        quotation.addItem(item);
    }

    public void deleteItem(Quotation quotation, Item item){
        if(quotation != null){
            List<Item> items = quotation.getItems();
            for(Item i:items) {  //Implementar metodo que retorne un unico item
                if (i == item) {
                    quotation.deleteItem(item);
                    break; //evitar eliminar todos los elementos agregados de ese item
                }
            }
        }
    }

    public void deleteItem(int id, Item item) throws ServiceException {
        Quotation quotation = getQuotation(id);
        deleteItem(quotation, item);
    }
}
