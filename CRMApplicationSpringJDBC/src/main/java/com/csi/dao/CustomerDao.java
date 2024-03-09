package com.csi.dao;

import com.csi.model.Customer;

import java.util.List;

public interface CustomerDao {

    void signUp(Customer customer);

    boolean signIn(String custEmailId, String custPassword);

    Customer findById(int custId);

    List<Customer> findAll();

    void update(int custId, Customer customer);

    void patchUpdateAddress(int custId, String custAddress);

    void pathchUpdateAccountBalance(int custId, double custAccountBalance);

    void deleteById(int custId);

    void deleteAll();
}
