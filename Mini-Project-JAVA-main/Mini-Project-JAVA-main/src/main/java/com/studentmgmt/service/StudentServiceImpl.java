package com.studentmgmt.service;

import com.studentmgmt.dao.CourseDAO; // (Assuming you created this)
import com.studentmgmt.dao.StudentDAO;
import com.studentmgmt.model.Course;
import com.studentmgmt.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import Spring's Transactional

import java.util.List;

@Service // Marks this as a Spring bean for business logic
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO; // (Assuming you created this)

    @Override
    @Transactional // All DAO calls in this method will be part of one transaction
    public void addStudent(String name) {
        Student student = new Student(name);
        studentDAO.save(student);
    }

    @Override
    @Transactional
    public void updateStudentName(int studentId, String newName) {
        Student student = getStudentById(studentId);
        if (student != null) {
            student.setName(newName);
            studentDAO.update(student);
        }
    }

    @Override
    @Transactional(readOnly = true) // readOnly = true is an optimization for read operations
    public Student getStudentById(int studentId) {
        return studentDAO.findById(studentId);
    }

    @Override
    @Transactional
    public void deleteStudent(int studentId) {
        Student student = getStudentById(studentId);
        if (student != null) {
            studentDAO.delete(student);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    @Override
    @Transactional
    public void addCourse(String courseName, int duration) {
        Course course = new Course(courseName, duration);
        courseDAO.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public Course getCourseById(int courseId) {
        return courseDAO.findById(courseId);
    }

    @Override
    @Transactional
    public void assignCourseToStudent(int studentId, int courseId) {
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
        if (student != null && course != null) {
            student.setEnrolledCourse(course);
            studentDAO.update(student);
            System.out.println("Successfully enrolled " + student.getName() + " in " + course.getCourseName());
        } else {
            System.out.println("Error: Student or Course not found.");
        }
    }
}