package co.edu.eci.cvds.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID", nullable = false, unique = true)
    private int categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Item> items;

    public Category() {
    }

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
