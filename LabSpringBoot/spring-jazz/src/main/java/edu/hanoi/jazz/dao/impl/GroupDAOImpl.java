package edu.hanoi.jazz.dao.impl;

import edu.hanoi.jazz.ContextStartEventHandler;
import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.model.Group;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);

    @Override
    public void insert(Group group) {
        Session session = sessionFactory.getObject().openSession();
//        System.out.println(session.getSession());
        try {
            Transaction transaction = session.beginTransaction();
            session.save(group);
            session.flush();
            transaction.commit();
            LOGGER.info("Save group "+group.getName()+" done!");
        } finally {
            session.close();
        }
//        System.out.println(sessionFactory+" insert group "+group);
    }

    @Override
    public List<Group> list(String pattern) {
        Session session = sessionFactory.getObject().openSession();
        if (pattern == null || pattern.length() < 1){
            Query query = session.createQuery("from Group");
            try {
                return (List<Group>)query.list();
            } finally {
                session.close();
            }
        }
        Criteria criteria = session.createCriteria(Group.class);
        criteria.add(Restrictions.like("name", "%"+pattern+"%", MatchMode.ANYWHERE));
        return new ArrayList<Group>(criteria.list());
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = session.beginTransaction();
        Group group = session.get(Group.class, id);
        if(group == null) return;
        session.delete(group);
        session.flush();
        transaction.commit();
        LOGGER.info("Delete group "+group.getName()+" successful!");
        session.close();
    }

    @Override
    public void update(Group group) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = session.beginTransaction();
        group = (Group) session.merge(group);
        session.save(group);
        session.flush();
        transaction.commit();
        LOGGER.info("Update group "+group.getName()+" successful!");
        session.close();
    }

    @Override
    public Group get(int id) {
        Session session = sessionFactory.getObject().openSession();
        Group group = session.get(Group.class, id);
        return group;
    }

}
