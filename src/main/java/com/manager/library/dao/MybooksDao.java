package com.manager.library.dao;

import com.manager.library.entities.MyBooks;
import com.manager.library.entities.Student;
import com.manager.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MybooksDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//GET LIST OF MY BOOKS
    public List<MyBooks> seeMyBooks(int studentId){
        try(Session session = sessionFactory.openSession();){
            String hql = "from my_books where studentId =:student_id";
            Query<MyBooks> myBooksQuery = session.createQuery(hql,MyBooks.class);
            myBooksQuery.setParameter("student_id",studentId);
            return myBooksQuery.getResultList();
        }

    }

}
