package com.studentmgmt.service;

public interface FeeService {
    void makePayment(int studentId, double amount);
    void issueRefund(int studentId, double refundAmount);
}