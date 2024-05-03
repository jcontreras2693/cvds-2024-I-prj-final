package co.edu.eci.cvds.service;

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

    public Item getItem(int id){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }

    public Item updateItem(Item item){
        Item update = getItem(item.getItemId());
        if(update!=null){
            itemRepository.delete(update);
        }
        return addItem(item);
    }

    public void deleteItem(Item item){
        itemRepository.delete(item);
    }

    public void deleteItem(int id){
        Item item = getItem(id);
        if(item!=null) {
            itemRepository.delete(item);
        }
    }

    public double calculateSubtotal(Item item){
        return item.getValue();
    }

    public double calculateSubtotal(int id){
        Item item = getItem(id);
        if(item==null){
            return 0; //excepcion
        }
        return item.getValue();
    }

    public double calculateTotal(Item item){
        double value = item.getValue();
        double discount = value * (item.getDiscount() / 100);
        double tax = value * (item.getTax() / 100);
        return value + tax - discount;
    }

    public boolean isAvalible(Item item){
        return item.getAvailability();
    }

    public boolean isAvalible(int id){
        Item item = getItem(id);
        if(item==null) {
            return false; //Excepcion
        }
        return item.getAvailability();
    }

    public void addCategory(Item item, Category category){
        item.addCategory(category);
    }

    public void addCategory(int id, Category category){
        Item item = getItem(id);
        if(item != null) {
            item.addCategory(category);
        }
    }

}
