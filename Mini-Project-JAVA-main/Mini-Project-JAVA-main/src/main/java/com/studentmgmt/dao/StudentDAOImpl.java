package com.studentmgmt.dao;

import com.studentmgmt.model.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marks this as a Spring bean for persistence
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Student student) {
        sessionFactory.getCurrentSession().save(student);
    }

    @Override
    public void update(Student student) {
        sessionFactory.getCurrentSession().update(student);
    }

    @Override
    public void delete(Student student) {
        sessionFactory.getCurrentSession().delete(student);
    }

    @Override
    public Student findById(int id) {
        return sessionFactory.getCurrentSession().get(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Student", Student.class).list();
    }
}