package com.studentmgmt.dao;

import com.studentmgmt.model.Course;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Mark this as a Spring bean
public class CourseDAOImpl implements CourseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Course course) {
        sessionFactory.getCurrentSession().save(course);
    }

    @Override
    public void update(Course course) {
        sessionFactory.getCurrentSession().update(course);
    }

    @Override
    public void delete(Course course) {
        sessionFactory.getCurrentSession().delete(course);
    }

    @Override
    public Course findById(int id) {
        return sessionFactory.getCurrentSession().get(Course.class, id);
    }

    @Override
    public List<Course> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Course", Course.class).list();
    }
}