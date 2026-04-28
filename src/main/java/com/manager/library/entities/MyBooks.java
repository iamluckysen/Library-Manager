package com.manager.library.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.time.LocalDate;

@Entity(name =  "my_books")
@Immutable
@Subselect("SELECT * FROM my_books")
public class MyBooks {

    @Id
    @Column(name = "issued_id")
    private int issuedId;

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public int getBookID(){
        return bookID;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getIssuedId() {
        return issuedId;
    }

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "book_id")
    private int bookID;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Override
    public String toString() {
        return "########MYBOOKS#########" +
                "Issued ID : " + this.issuedId + "\n" +
                "Student ID : " + this.studentId + "\n" +
                "Student Name : " + this.studentName + "\n" +
                "Book Name : " + this.bookName + "\n" +
                "Book Author : " + this.bookAuthor + "\n" +
                "Issued Date : " + this.issueDate + "\n";
    }
}
