package com.cherednik.Dao;

import com.cherednik.Models.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GroupDao {

    private SessionFactory sessionFactory;

    public GroupDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void clear() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", Group.class.getSimpleName());
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        }
    }

    public void addGroup(Group group) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
        }
    }

    public Group getGroup(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Group group = session.createQuery("From Group WHERE id = :id ", Group.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
            return group;
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
