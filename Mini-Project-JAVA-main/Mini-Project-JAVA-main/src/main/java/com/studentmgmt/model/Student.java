package com.studentmgmt.model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;

    private String name;
    private double balance = 0.0; // Initial balance

    // This demonstrates the dependency injection (DI) concept
    // A Student "depends on" a Course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course enrolledCourse;

    // Constructors, Getters, Setters
    public Student() {}

    public Student(String name) {
        this.name = name;
    }
    
    // ... (Generate getters and setters for all fields) ...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public Course getEnrolledCourse() { return enrolledCourse; }
    public void setEnrolledCourse(Course enrolledCourse) { this.enrolledCourse = enrolledCourse; }

    @Override
    public String toString() {
        String courseName = (enrolledCourse != null) ? enrolledCourse.getCourseName() : "Not Enrolled";
        return "Student [id=" + id + ", name=" + name + ", balance=₹" + balance + ", course=" + courseName + "]";
    }
}