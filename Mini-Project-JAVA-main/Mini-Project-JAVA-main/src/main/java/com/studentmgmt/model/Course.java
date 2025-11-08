package com.studentmgmt.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int id;

    @Column(name = "course_name")
    private String courseName;

    private int duration; // in months

    // Constructors, Getters, Setters
    public Course() {}

    public Course(String courseName, int duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    // ... (Generate getters and setters for all fields) ...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    @Override
    public String toString() {
        return "Course [id=" + id + ", courseName=" + courseName + ", duration=" + duration + " months]";
    }
}