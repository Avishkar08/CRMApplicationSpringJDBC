package com.csi.service;

import com.csi.dao.CustomerDao;
import com.csi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDaoImpl;

    @Override
    public void signUp(Customer customer) {
        customerDaoImpl.signUp(customer);

    }

    @Override
    public boolean signIn(String custEmailId, String custPassword) {
        return customerDaoImpl.signIn(custEmailId, custPassword);
    }

    @Override
    public Customer findById(int custId) {
        return customerDaoImpl.findById(custId);
    }

    @Override
    public List<Customer> findAll() {
        return customerDaoImpl.findAll();
    }

    @Override
    public void update(int custId, Customer customer) {
        customerDaoImpl.update(custId, customer);
    }

    @Override
    public void patchUpdateAddress(int custId, String custAddress) {
        customerDaoImpl.patchUpdateAddress(custId, custAddress);
    }

    @Override
    public void pathchUpdateAccountBalance(int custId, double custAccountBalance) {
        customerDaoImpl.pathchUpdateAccountBalance(custId, custAccountBalance);
    }

    @Override
    public void deleteById(int custId) {
        customerDaoImpl.deleteById(custId);
    }

    @Override
    public void deleteAll() {
        customerDaoImpl.deleteAll();
    }
}
