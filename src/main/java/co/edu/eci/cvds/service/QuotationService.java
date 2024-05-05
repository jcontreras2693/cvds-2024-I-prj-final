package co.edu.eci.cvds.service;

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

    public Quotation getQuotation(int id){
        Optional<Quotation> result = quotationRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    public List<Quotation> getAllQuotation(){
        return quotationRepository.findAll();
    }

    public Quotation updateQuotation(Quotation quotation){
        Quotation update = getQuotation(quotation.getQuotationId());
        if(update != null){
            quotationRepository.delete(update);
        }
        return quotationRepository.save(quotation);
    }

    public void deleteQuotation(Quotation quotation){
        quotationRepository.delete(quotation);
    }

    public void deleteQuotation(int id){
        Quotation quotation = getQuotation(id);
        if(quotation!=null){
            quotationRepository.delete(quotation);
        }
    }

    public double calculateTotal(Quotation quotation){
        List<Item> items = quotation.getItems();
        double total = 0;
        for(Item item : items){
            double value = item.getValue();
            double tax = value * (item.getTax() / 100);
            double discount = value * (item.getDiscount() / 100);
            double totalValue = value + tax - discount;
            total += totalValue;
        }
        return total;
    }

    public double calculateTotal(int id){
        Quotation quotation = getQuotation(id);
        if(quotation == null){
            return 0; //excepcion
        }
        return calculateTotal(quotation);
    }

    public void updateStatus(Quotation quotation, QuotationStatus status){
        quotation.updateStatus(status);
    }

    public void updateStatus(int id, QuotationStatus status){
        Quotation quotation = getQuotation(id);
        if(quotation != null){
            quotation.updateStatus(status);
        }
    }

    public void addItem(Quotation quotation, Item item){
        quotation.addItem(item);
    }

    public void addItem(int id, Item item){
        Quotation quotation = getQuotation(id);
        if(quotation != null) {
            quotation.addItem(item);
        }
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

    public void deleteItem(int id, Item item){
        Quotation quotation = getQuotation(id);
        deleteItem(quotation, item);
    }
}
