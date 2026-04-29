package com.manager.library.entities;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "issued_books")
public class IssuedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int issue_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    public int getIssue_id() {
        return issue_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getIssue_date() {
        return issue_date;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @CreationTimestamp
    private LocalDate issue_date;


    @Override
    public String toString() {
        return "Issued ID : "+this.issue_id+"\n"+this.book.toString() +"\n"+ this.student.toString()+ "\n" +"IssuedDate : " + this.issue_date.toString();
    }
}
