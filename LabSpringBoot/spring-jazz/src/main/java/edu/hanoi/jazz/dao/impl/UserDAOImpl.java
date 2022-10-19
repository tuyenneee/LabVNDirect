package edu.hanoi.jazz.dao.impl;

import edu.hanoi.jazz.ContextStartEventHandler;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("userDAO")
public class UserDAOImpl implements UserDAO {
    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);
    private final static int SIZE_OF_PAGE = 10;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void insert(User user) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        session.flush();
        transaction.commit();
        LOGGER.info("Save user " + user.getUsername() + " done!");
        session.close();
    }

    @Override
    public List<User> list(Integer group) {
        Session session = sessionFactory.getObject().openSession();
        try {
            if (group == null || group < 0) {
                Query query = session.createQuery("from User");
                return (List<User>) query.list();
            }
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("groupId", group));
            return new ArrayList<User>(criteria.list());
        } finally {
            session.close();
        }
    }

    @Override
    public User getDetail(String username) {
        Session session = sessionFactory.getObject().openSession();
        User user = session.get(User.class, username);
        return user;
    }

    @Override
    public List<User> listAll() {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery("from User order by age desc");
        try {
//            Criteria criteria = session.createCriteria(User.class);
//            criteria.add(Restrictions.gt("age", 21));
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String name) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from User where username like :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        int result = query.executeUpdate();
        transaction.commit();
        LOGGER.info("Row affected: " + result + "\n\n");
    }

    @Override
    public int averageAge() {
        Session session = sessionFactory.getObject().openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.setProjection(Projections.avg("age"));
        List avgAge = criteria.list();
        return ((Double) avgAge.get(0)).intValue();
    }

    @Override
    public List<User> page(int page) {
        Session session = sessionFactory.getObject().openSession();
        Criteria criteria = session.createCriteria(User.class);
        int start = (page - 1)*SIZE_OF_PAGE;
        criteria.setFirstResult(start);
        criteria.setMaxResults(SIZE_OF_PAGE);
        return (List<User>) criteria.list();
    }

    @Override
    public List<User> listUserByNativeSQL() {
        Session session = sessionFactory.getObject().openSession();
        String sql = "select * from HN_USER";
        NativeQuery query = session.createSQLQuery(sql);
        //query.setParameter("value", 25);
        query.addEntity(User.class);
        return query.list();
    }

    @Override
    public void addBatch() {
        org.hibernate.Transaction tx = null;
        Session session = sessionFactory.getObject().openSession();
        try {
            tx = session.beginTransaction();
            for (int i = 0; i < 50; i++) {
                session.save(UserFactory.generate(i+1));
            }
            session.flush();
            tx.commit();

        } catch (HibernateException e){
            if(tx != null) tx.rollback();
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
    }
}
