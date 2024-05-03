package co.edu.eci.cvds.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "QUOTATION")
public class Quotation {

    @Id
    @GeneratedValue
    @Column(name = "QUOTATION_ID", nullable = false, unique = true)
    private int quotationId;

    @Column(name = "CREATION_DATE", nullable = false, columnDefinition = "DATE")
    private LocalDate creationDate;

    @Column(name = "STATUS", nullable = false)
    private String status;

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
    }

    public Quotation(int quotationId, LocalDate creationDate, String status, Double total) {
        this.quotationId = quotationId;
        this.creationDate = creationDate;
        this.status = status;
        this.total = total;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double calculateTotal(){
        return -1.0;
    }

    public void updateStatus(){

    }

    public void addItem(){

    }

    public void deleteItem(){

    }
}
