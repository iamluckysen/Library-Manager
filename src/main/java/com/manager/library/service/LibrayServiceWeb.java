package com.manager.library.service;

import com.manager.library.dao.BookDao;
import com.manager.library.dao.IssuedBookDao;
import com.manager.library.dao.MybooksDao;
import com.manager.library.dao.StudentDao;
import com.manager.library.entities.Book;
import com.manager.library.entities.IssuedBook;
import com.manager.library.entities.MyBooks;
import com.manager.library.entities.Student;

import java.util.List;

public class LibrayServiceWeb {
    private BookDao bookDao = new BookDao();
    private StudentDao studentDao = new StudentDao();
    private IssuedBookDao issuedBookDao = new IssuedBookDao();
    private MybooksDao mybooksDao = new MybooksDao();


    //GET BOOKS
    public List<Book> getbooklist(){
        return  bookDao.getBookList();
    }
    public Book getBookById(int bookId){
       Book book =  bookDao.getBookById(bookId);
      return book;
    }

    //GET STUDENTS
    public List<Student> getStudentList(){
        List<Student> studentsList = studentDao.getAllStudent();
        return studentsList;
    }
    public Student getStudentById(int studentId){
        Student student  = studentDao.getStudentById(studentId);
        return  student;
    }

    //GET ISSUED  BOOK LIST
    public List<IssuedBook> getIssuedBookList(){
        List<IssuedBook> issuedBookList = issuedBookDao.getIssuedList();
      return issuedBookList;
    }

    //GET MY BOOK LIST
    public List<MyBooks> getMyBookList(int student_id) {
        List<MyBooks> myBooksList = mybooksDao.seeMyBooks(student_id);
        return myBooksList;
    }



    //CREATE BOOK
    public void addNewBook(Book book){
        if(book!=null){
            bookDao.saveBook(book);
            return;
        }
        System.out.println("book details cannot be null !");

    }

    //CREATE STUDENT
    public void addNewStudent(Student student){
        if(student==null){
            System.out.println("student details cannot be null !");
        return;
        }
        studentDao.saveStudent(student);
    }

    //DELETE BOOK
    public void deleteBook(int bookId){
        Book book = bookDao.getBookById(bookId);
        if(book!=null){
            bookDao.deleteBookById(book);
            return;
        }
        System.out.println("book not exists!");

    }

    //BORROW A BOOK
    public boolean borrowBook(Book book, Student student){
        IssuedBook issuedBook = issuedBookDao.getIssuedBookById(book.getId(),student.getId());
        if(issuedBook!=null){
            return false;}
        if(book.getTotalQuantity()==0){
            return false;
        }
        //update book
        book.setTotalQuantity(book.getTotalQuantity()-1);
        book.setSoldQuantity(book.getSoldQuantity()+1);

        //update student
        student.setObtainedBook(student.getObtainedBook()+1);

            //borrow book
        issuedBookDao.borrowBook(book,student);
        return true;
        }

    //RETURN BOOK
    public boolean returnBook(Book book, Student student){
        IssuedBook issuedBook = issuedBookDao.getIssuedBookById(book.getId(),student.getId());
        if(issuedBook==null){
            return false;}

        //update book
        issuedBook.getBook().setTotalQuantity(book.getTotalQuantity()+1);
        issuedBook.getBook().setSoldQuantity(book.getSoldQuantity()-1);

        //update student
        issuedBook.getStudent().setObtainedBook(student.getObtainedBook()-1);

        //borrow book
        issuedBookDao.returnBook(issuedBook);
        return true;

    }

    public boolean emailExists(String email){
        return  studentDao.duplicateEmail(email);
    }



}
