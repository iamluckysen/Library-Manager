package com.manager.library.dao;

import com.manager.library.entities.Book;
import com.manager.library.entities.IssuedBook;
import com.manager.library.entities.Student;
import com.manager.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class IssuedBookDao {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    //GET OR SEE ISSUED BOOKS
    public List<IssuedBook> getIssuedList() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from issued_books ";
            Query<IssuedBook> query = session.createQuery(hql, IssuedBook.class);
            return query.list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    //GET ISSUED BOOK BY ID
    public IssuedBook getIssuedBookById(int book_id,int student_id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from issued_books where book.id = :bookId and student.id = :studentId";
            Query<IssuedBook> query = session.createQuery(hql, IssuedBook.class);
            query.setParameter("bookId", book_id);
            query.setParameter("studentId", student_id);
            return query.uniqueResult();
        }
        }

    //ISSUE NEW BOOK
    public void borrowBook(Book book,Student student) {
        try (Session session = sessionFactory.openSession()) {
            IssuedBook issuedBook = new IssuedBook();
            issuedBook.setBook(book);
            issuedBook.setStudent(student);
            session.beginTransaction();
            session.update(book);
            session.update(student);
            session.save(issuedBook);
            session.getTransaction().commit();
            System.out.println("Book has been borrowed successfully");
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    //RETURN A BOOK
    public void returnBook(IssuedBook issuedBook) {
        try (Session session = sessionFactory.openSession()) {
           session.beginTransaction();
           session.update(issuedBook.getBook());
           session.update(issuedBook.getStudent());
           session.delete(issuedBook);
           session.getTransaction().commit();
            System.out.println("Book has been returned successfully");
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
