package com.cherednik.Dao;

import com.cherednik.Models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class StudentDao {

    private SessionFactory sessionFactory;

    public StudentDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void clear() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", Student.class.getSimpleName());
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        }
    }

    public void addStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }
    }

    public List getStudentByGroup(String groupName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT students from Group where lower(groupName)= lower (:groupName)")
                    .setParameter("groupName", groupName)
                    .list();
        }
    }

    public List getGroupByStudent(String studentName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT groups from Student where lower (studentName) = lower (:studentName)")
                    .setParameter("studentName", studentName)
                    .list();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
