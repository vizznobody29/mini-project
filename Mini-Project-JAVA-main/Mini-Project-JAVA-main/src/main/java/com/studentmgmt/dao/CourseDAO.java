package com.studentmgmt.dao;

import com.studentmgmt.model.Course;
import java.util.List;

public interface CourseDAO {
    void save(Course course);
    void update(Course course);
    void delete(Course course);
    Course findById(int id);
    List<Course> findAll();
}