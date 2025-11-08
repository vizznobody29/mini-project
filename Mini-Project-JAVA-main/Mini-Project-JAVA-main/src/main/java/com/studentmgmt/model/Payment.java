package com.studentmgmt.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;

    private double amount;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    // Constructors, Getters, Setters
    public Payment() {}

    public Payment(double amount, LocalDateTime date, Student student) {
        this.amount = amount;
        this.date = date;
        this.student = student;
    }
    
    // ... (Generate getters and setters) ...
}