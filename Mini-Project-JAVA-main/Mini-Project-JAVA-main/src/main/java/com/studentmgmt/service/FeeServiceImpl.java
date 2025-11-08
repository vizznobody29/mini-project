package com.studentmgmt.service;

import com.studentmgmt.dao.PaymentDAO; // (Assuming you created this)
import com.studentmgmt.dao.StudentDAO;
import com.studentmgmt.model.Payment;
import com.studentmgmt.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private PaymentDAO paymentDAO; // (Assuming you created this)

    /**
     * This method demonstrates Transaction Management.
     * If saving the payment record fails, the update to the student's balance
     * will be automatically ROLLED BACK by Spring.
     */
    @Override
    @Transactional
    public void makePayment(int studentId, double amount) {
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found with id: " + studentId);
        }

        // 1. Update Student's balance
        student.setBalance(student.getBalance() + amount);
        studentDAO.update(student);

        // --- (Uncomment the line below to test rollback) ---
        // if(true) { throw new RuntimeException("Simulating payment failure!"); }

        // 2. Create a Payment record
        Payment payment = new Payment(amount, LocalDateTime.now(), student);
        paymentDAO.save(payment);

        System.out.println("Payment of ₹" + amount + " successful for " + student.getName());
    }

    @Override
    @Transactional
    public void issueRefund(int studentId, double refundAmount) {
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found with id: " + studentId);
        }
        if (student.getBalance() < refundAmount) {
            throw new RuntimeException("Refund amount (₹" + refundAmount + ") exceeds student balance (₹" + student.getBalance() + ")");
        }

        // 1. Deduct from student's balance
        student.setBalance(student.getBalance() - refundAmount);
        studentDAO.update(student);

        // 2. Create a (negative) Payment record for tracking
        Payment refundRecord = new Payment(-refundAmount, LocalDateTime.now(), student);
        paymentDAO.save(refundRecord);

        System.out.println("Refund of ₹" + refundAmount + " successful for " + student.getName());
    }
}