package com.studentmgmt.dao;

import com.studentmgmt.model.Payment;
import java.util.List;

public interface PaymentDAO {
    void save(Payment payment);
    Payment findById(int id);
    List<Payment> findAll();
    // We typically don't update or delete payment records,
    // so we'll just implement 'save' for this example.
}