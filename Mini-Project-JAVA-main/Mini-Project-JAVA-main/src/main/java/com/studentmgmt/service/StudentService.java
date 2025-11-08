package com.studentmgmt.service;

import com.studentmgmt.model.Course;
import com.studentmgmt.model.Student;
import java.util.List;

public interface StudentService {
    // Student CRUD
    void addStudent(String name);
    void updateStudentName(int studentId, String newName);
    Student getStudentById(int studentId);
    void deleteStudent(int studentId);
    List<Student> getAllStudents();

    // Course CRUD
    void addCourse(String courseName, int duration);
    Course getCourseById(int courseId);
    
    // Business Logic
    void assignCourseToStudent(int studentId, int courseId);
}