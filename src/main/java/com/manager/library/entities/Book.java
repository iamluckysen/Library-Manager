package com.manager.library.entities;

import jakarta.persistence.*;

@Entity(name = "book")
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "book_name", nullable = false,length = 200)
    private String name;

    @Column(name = "book_category" , nullable = false,length = 100)

    private String category;

    @Column(name = "book_author" , nullable = false,length = 200)
    private String author;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTotalQuantity() {
        return totalQuantity == null ? 0 : totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getSoldQuantity() {
        return soldQuantity == null ? 0 : soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    @Column (name = "total_quantity" ,  nullable = false)
    private Integer totalQuantity;

    @Column (name = "sold_quantity")
    private Integer soldQuantity;

    public Book(String name , String category, String author, Integer totalQuantity, Integer soldQuantity) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.totalQuantity = totalQuantity;
        this.soldQuantity = soldQuantity;
    }
    public Book(){}

    @Override
    public String toString() {
        return "Book Name: " + name + "\nCategory: " + category + "\nAuthor: " + author;
    }

}
