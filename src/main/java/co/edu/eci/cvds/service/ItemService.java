package co.edu.eci.cvds.service;

import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Category;
import co.edu.eci.cvds.model.Item;
import co.edu.eci.cvds.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item){
        return itemRepository.save(item);
    }

    public Item getItem(int id) throws ServiceException{
        Optional<Item> result = itemRepository.findById(id);
        if(result.isEmpty()){
            throw new ServiceException(ServiceException.nonExistentItem);
        }
        return result.get();
    }

    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }

    public Item updateItem(Item item) throws ServiceException {
        Item update = getItem(item.getItemId());
        itemRepository.delete(update);
        return addItem(item);
    }

    public void deleteItem(Item item){
        itemRepository.delete(item);
    }

    public void deleteItem(int id) throws ServiceException {
        Item item = getItem(id);
        itemRepository.delete(item);
    }

    public double calculateSubtotal(Item item){
        return item.getValue();
    }

    public double calculateSubtotal(int id) throws ServiceException {
        Item item = getItem(id);
        double value = item.getValue();
        double discount = value * (item.getDiscount() / 100);
        return value - discount;
    }

    public double calculateTotal(Item item){
        double value = item.getValue();
        double discount = value * (item.getDiscount() / 100);
        double valueWithDiscount = value - discount;
        double tax = value * (item.getTax() / 100);
        return valueWithDiscount + tax;
    }

    public boolean isAvalible(Item item){
        return item.getAvailability();
    }

    public boolean isAvalible(int id) throws ServiceException {
        Item item = getItem(id);
        return item.getAvailability();
    }

    public void addCategory(Item item, Category category){
        item.addCategory(category);
    }

    public void addCategory(int id, Category category) throws ServiceException {
        Item item = getItem(id);
        item.addCategory(category);
    }

}