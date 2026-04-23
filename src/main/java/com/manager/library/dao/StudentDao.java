package com.manager.library.dao;

import com.manager.library.entities.Book;
import com.manager.library.entities.Student;
import com.manager.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    //GET STUDENT
    public List<Student> getAllStudent(){
        try(Session session = sessionFactory.openSession()){
            String hql = "FROM students";
            Query<Student> query = session.createQuery(hql, Student.class);
            return query.getResultList();

        }
    }
    public Student getStudentById(int studentId ) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from students where id = :studentId";
            Query<Student> query = session.createQuery(hql,Student.class);
            query.setParameter("studentId", studentId);
            return query.uniqueResult();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //CREATE STUDENT OR ADD NEW STUDENT
    public void saveStudent(Student student){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            System.out.println("save student successful");
        } catch (Exception e) {
            System.out.println("saveStudent error !" + e.getMessage());
        }
    }

    //UPDATE BOOK DETAILS
    public void updateStudent(Student student){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    //DELETE STUDENT
    public void deleteStudent(int studentId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Student student = getStudentById(studentId);
            if(student != null){
                System.out.println("no student found for the id " + studentId);
                session.getTransaction().rollback();
                return;
            }
            session.delete(student);
            session.getTransaction().commit();
            System.out.println("delete student successful");
        }
    }


    public boolean duplicateEmail(String email) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
        String hql = "from students where email = :email";
        Query<Student> query = session.createQuery(hql,Student.class);
        query.setParameter("email", email);
        Student student = query.uniqueResult();
        return student != null;
        }
    }
}
