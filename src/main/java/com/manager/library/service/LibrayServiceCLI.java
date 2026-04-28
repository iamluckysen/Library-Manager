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

public class LibrayServiceCLI {
    private BookDao bookDao = new BookDao();
    private StudentDao studentDao = new StudentDao();
    private IssuedBookDao issuedBookDao = new IssuedBookDao();
    private MybooksDao mybooksDao = new MybooksDao();


    //GET BOOKS
    public void getBooklist(){
        List<Book> bookList = bookDao.getBookList();
        System.out.println("###############BOOKS################");
        for(Book book : bookList){
            int available = book.getTotalQuantity()- book.getSoldQuantity();
            System.out.println("book_id : "+book.getId()+"\n"+"book_name : "+book.getName()+"\n"+"bookAuthor : "+book.getAuthor()+"\n"+"bookCategory : " + book.getCategory() + "\n"+ "AvailableQuantity : "+available + "\n" + "SoldQuantity : "+book.getSoldQuantity());
            System.out.println("-------------------------------------");
        }
        System.out.println("####################################");
    }
    public List<Book> getbooklist(){
        return  bookDao.getBookList();
    }
    public void getBookById(int bookId){
        Book book =  bookDao.getBookById(bookId);
        if(book!=null){
            int available = book.getTotalQuantity()- book.getSoldQuantity();
            System.out.println("book_id : "+book.getId()+"\n"+"book_name : "+book.getName()+"\n"+"bookAuthor : "+book.getAuthor()+"\n"+"bookCategory : " + book.getCategory() + "\n"+ "AvailableQuantity : "+available);
        }
        else System.out.println("book not found !");
    }

    //GET STUDENTS
    public void getStudentList(){
        List<Student> studentsList = studentDao.getAllStudent();
        System.out.println("###############STUDENTS################");
        for(Student student: studentsList){
            System.out.println("student_id : "+student.getId()+"\n"+"student_name : "+student.getName());
            System.out.println("-------------------------------------");
        }
        System.out.println("####################################");
    }
    public Student getStudentById(int studentId){
        Student student  = studentDao.getStudentById(studentId);
        if(student!=null){
//            System.out.println("student_id : "+student.getId()+"\n"+"student_name : "+student.getName());
            return student;
        }
//        else System.out.println("student not found !");
        return null;
    }

    //GET ISSUED  BOOK LIST
    public void getIssuedBookList(){
        List<IssuedBook> issuedBookList = issuedBookDao.getIssuedList();
        if(issuedBookList.isEmpty()){
            System.out.println("No issued books found !");
            return;
        }
        System.out.println("###############ISSUED BOOKS################");
        for(IssuedBook book : issuedBookList){
            System.out.println("issued id : "+book.getIssue_id());
            System.out.println("book id : "+book.getBook().getId());
            System.out.println("book name : "+book.getBook().getName());
            System.out.println("book author : "+book.getBook().getAuthor());
            System.out.println("issued date : "+book.getIssue_date());
            System.out.println("issued by : "+ book.getStudent().getName());
            System.out.println("-------------------------------------");
        }
        System.out.println("####################################");
    }
    public void getIssuedBookById(int bookId,int studentId){
        IssuedBook issuedBook = issuedBookDao.getIssuedBookById(bookId,studentId);
        if(issuedBook==null){
            System.out.println("issued book not found !");
            return;
        }
        System.out.println(issuedBook);
    }

    //GET MY BOOK LIST
    public void getMyBookList(int student_id) {
        List<MyBooks> myBooksList = mybooksDao.seeMyBooks(student_id);
        if (myBooksList.isEmpty()) {
            System.out.println("No books found !");
            return;
        }
        for (MyBooks mybooks : myBooksList) {
            System.out.println(mybooks);
        }
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
    public void borrowBook(int bookId, int studentId){
        IssuedBook issuedBook = issuedBookDao.getIssuedBookById(bookId,studentId);
        if(issuedBook!=null){
            System.out.println("duplicate books are not allowed to borrow");
            return;}
        Book book = bookDao.getBookById(bookId);
        Student student = studentDao.getStudentById(studentId);
        if(book==null){
            System.out.println("book not found !");
            return;
        }
        if(student==null){
            System.out.println("student not found !");
            return;
        }
        if(book.getTotalQuantity()<1){
            System.out.println("book sold out !");
            return;
        }
        //update book
        book.setTotalQuantity(book.getTotalQuantity()-1);
        book.setSoldQuantity(book.getSoldQuantity()+1);

        //update student
        student.setObtainedBook(student.getObtainedBook()+1);

        //borrow book
        issuedBookDao.borrowBook(book,student);

    }

    //RETURN BOOK
    public void returnBook(int bookId, int studentId){
        IssuedBook issuedBook = issuedBookDao.getIssuedBookById(bookId,studentId);
        if(issuedBook==null){
            System.out.println("you not hold this book!");
            return;}

        Book book = issuedBook.getBook();
        Student student = issuedBook.getStudent();

        //update book
        book.setTotalQuantity(book.getTotalQuantity()+1);
        book.setSoldQuantity(book.getSoldQuantity()-1);

        //update student
        student.setObtainedBook(student.getObtainedBook()-1);

        //borrow book
        issuedBookDao.returnBook(issuedBook);

    }

    public boolean emailExists(String email){
        return  studentDao.duplicateEmail(email);
    }



}
