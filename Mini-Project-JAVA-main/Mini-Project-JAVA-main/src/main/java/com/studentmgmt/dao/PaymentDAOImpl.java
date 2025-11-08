package com.studentmgmt.dao;

import com.studentmgmt.model.Payment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Mark this as a Spring bean
public class PaymentDAOImpl implements PaymentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Payment payment) {
        sessionFactory.getCurrentSession().save(payment);
    }

    @Override
    public Payment findById(int id) {
        return sessionFactory.getCurrentSession().get(Payment.class, id);
    }

    @Override
    public List<Payment> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Payment", Payment.class).list();
    }
}