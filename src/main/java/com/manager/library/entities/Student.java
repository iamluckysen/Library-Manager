package com.manager.library.entities;

import jakarta.persistence.*;

@Entity(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;

    @Column(name = "student_name", nullable = false,length = 100)
    private String name;

    @Column(name = "student_email" , nullable = false,length = 100 , unique = true)

    private String email;

    @Column(name = "phone_number" , nullable = false,length = 10 , unique = true)
    private String phone;

    @Column (name = "obtained_book")
    private Integer obtainedBook;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getObtainedBook() {
        return obtainedBook == null ? 0 : obtainedBook;
    }

    public void setObtainedBook(Integer obtainedBook) {
        this.obtainedBook = obtainedBook;
    }
    @Override
    public String toString() {
        return "Student Id : "+ id+"\nStudent Name: " + name + "\nmail: " + email + "\nPhone: " + phone;
    }
}
