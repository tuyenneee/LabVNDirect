package edu.hanoi.spring.dao.impl;

import edu.hanoi.spring.dao.GroupDAO;
import edu.hanoi.spring.model.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {

    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    @Qualifier(value = "sessionFactory")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Group> list() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from Group");
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public String insert(Group group) {
        Session session = sessionFactory.getObject().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Serializable value = session.save(group);
            session.flush();
            transaction.commit();
            System.out.println("Save user "+group.getName()+" done!");
            return value.toString();
        } finally {
            session.close();
        }
    }

    @Override
    public Group get(String Id) {
        int id = Integer.parseInt(Id);
        Session session = sessionFactory.getObject().openSession();
        try {
            return session.get(Group.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getObject().openSession();
        //int id = Integer.parseInt(Id);
        try {
            Transaction transaction = session.beginTransaction();
            Group group = session.get(Group.class, id);
            if (group != null) session.delete(group);
            session.flush();
            transaction.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Group group) {
        Session session = sessionFactory.getObject().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            group = (Group) session.merge(group);
            session.save(group);
            session.flush();
            transaction.commit();
        } finally {
            session.close();
        }
    }
}
