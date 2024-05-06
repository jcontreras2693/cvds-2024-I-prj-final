package co.edu.eci.cvds.model;

import jakarta.persistence.*;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "QUOTATION")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUOTATION_ID")
    private int quotationId;

    @Column(name = "CREATION_DATE", nullable = false, columnDefinition = "DATE")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private QuotationStatus status;

    @Column(name = "TOTAL", nullable = false)
    private Double total;

    @ManyToMany
    @JoinTable(
            name = "QUOTATION_ITEM",
            joinColumns = @JoinColumn(name = "QUOTATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Item> items;

    public Quotation() {
        this.creationDate = LocalDate.now();
        this.status = QuotationStatus.CREADO;
        this.total = 0.0;
        this.items = new ArrayList<Item>();
    }

    public int getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(int quotationId) {
        this.quotationId = quotationId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public QuotationStatus getStatus() {
        return status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double calculateTotal(){
        List<Item> items = getItems();
        double totalCost = 0;
        for(Item item : items){
            totalCost += item.calculateTotal();
        }
        return totalCost;
    }

    public Double calculateSubtotal(){
        List<Item> items = getItems();
        double subtotal = 0;
        for(Item item : items){
            subtotal += item.calculateSubtotal();
        }
        return subtotal;
    }

    public void updateStatus(QuotationStatus status){
        this.status = status;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void deleteItem(Item item){
        items.remove(item);
    }

    public List<Item> getItems(){
        return items;
    }

}
