package com.studentmgmt.dao;

import com.studentmgmt.model.Student;
import java.util.List;

public interface StudentDAO {
    void save(Student student);
    void update(Student student);
    void delete(Student student);
    Student findById(int id);
    List<Student> findAll();
}