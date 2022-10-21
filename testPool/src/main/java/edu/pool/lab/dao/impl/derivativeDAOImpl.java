package edu.pool.lab.dao.impl;

import edu.pool.lab.dao.derivativeDAO;
import edu.pool.lab.model.derivative;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("derivativeDAO")
public class derivativeDAOImpl implements derivativeDAO {
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<derivative> list(String code) {
        Session session = sessionFactory.getObject().openSession();
        try {
            List query = session.createQuery("from derivative where code like: code")
                    .setParameter("code", "%"+code+"%").list();
            return (List<derivative>) query;
        } finally {
            session.close();
        }
    }

    @Override
    public List<derivative> listAll() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from derivative");
            return (List<derivative>) query.list();
        } finally {
            session.close();
        }
    }

}
