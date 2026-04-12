package com.manager.library.dao;

import com.manager.library.entities.Book;
import com.manager.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class BookDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//GET OR SEE BOOKS
   public List<Book> getBookList() {
       try (Session session = sessionFactory.openSession()) {
           String hql = "from book";
           Query<Book> query = session.createQuery(hql, Book.class);
           return query.list();
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
   }
   public Book getBookById( int bookId ) {
       try (Session session = sessionFactory.openSession()) {
           String hql = "from book where id = :bookId";
           Query<Book> query = session.createQuery(hql,Book.class);
           query.setParameter("bookId", bookId);
           Book book = query.uniqueResult();
           return book;
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
   }

   //ADD NEW BOOK IN LIBRARY
   public void saveBook( Book book ) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String bookName = book.getName();
            String author = book.getAuthor();
            String hql = "from book where name = :bookName and author = :author";
            Query<Book> query = session.createQuery(hql,Book.class);
            query.setParameter("bookName", bookName);
            query.setParameter("author", author);
            Book b = query.uniqueResult();
            if(b != null){
                session.getTransaction().rollback();
                System.out.println("duplicate book not allowed");
            }
            else{ session.save(book);
                session.getTransaction().commit();
                System.out.println("Book Saved Successfully");}


        } catch (RuntimeException e) {
            System.out.println("Error saving book" + e.getMessage());
        }
   }

   //DELETE BOOK IN LIBRARY
   public void deleteBookById( Book book) {
       try(Session session = sessionFactory.openSession()) {
           session.beginTransaction();
           session.delete(book);
           session.getTransaction().commit();
           System.out.println("Book Deleted Successfully");
       }catch (RuntimeException e) {
           System.out.println("Error deleting book" + e.getMessage());
       }
   }


   //UPDATE BOOK IN LIBRARY
   public void updateBook( Book book ) {
       try(Session session = sessionFactory.openSession()) {
           session.beginTransaction();
           session.update(book);
           session.getTransaction().commit();
       }catch (RuntimeException e) {
           System.out.println(e.getMessage());
       }
   }

}
