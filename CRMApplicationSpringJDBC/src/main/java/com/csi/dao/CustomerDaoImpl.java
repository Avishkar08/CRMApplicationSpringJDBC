package com.csi.dao;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String SIGN_UP_SQL = "insert into customer (custid,custname,custaddress,custaccountbalance,custdob,custcontactnumber,custuid,custpancard,custemailid,custpassword)values(?,?,?,?,?,?,?,?,?,?)";

    String FIND_ALL_SQL = "select * from customer";

    String FIND_BY_ID_SQL = "select * from customer where custid=?";

    String UPDATE_BY_ID_SQL = "update customer set custname=?,custaddress=?,custaccountbalance=?,custdob=?,custcontactnumber=?,custuid=?,custpancard=?,custemailid=?,custpassword=? where custid=?";

    String PATCH_UPDATE_ADDRESS_SQL = "update customer set custaddress=? where custid=?";

    String PATCH_UPDATE_ACCOUNT_BALANCE_SQL = "update customer set custaccountbalance=? where custid=?";

    String DELETE_BY_ID_SQL = "delete from customer where custid=? ";

    String DELETE_ALL_SQL = "truncate table customer";

    private Customer customer(ResultSet resultSet, int numRow) throws SQLException {
        return Customer.builder().custId(resultSet.getInt(1)).custName(resultSet.getString(2)).custAddress(resultSet.getString(3)).
                custAccountBalance(resultSet.getDouble(4)).custDOB(resultSet.getDate(5)).custContactNumber(resultSet.getLong(6)).
                custUID(resultSet.getLong(7)).custPancard(resultSet.getString(8)).custEmailId(resultSet.getString(9)).custPassword(resultSet.getNString(10)).build();
    }


    @Override
    public void signUp(Customer customer) {
        jdbcTemplate.update(SIGN_UP_SQL, customer.getCustId(), customer.getCustName(), customer.getCustAddress(), customer.getCustAccountBalance(), customer.getCustDOB(), customer.getCustContactNumber(), customer.getCustUID(), customer.getCustPancard(), customer.getCustEmailId(), customer.getCustPassword());
    }

    @Override
    public boolean signIn(String custEmailId, String custPassword) {
        boolean flag = false;
        for (Customer customer : findAll()) {
            if (customer.getCustEmailId().equals(custEmailId) && customer.getCustPassword().equals(custPassword)) {
                flag = true;
                break;

            }

        }
        return flag;
    }

    @Override
    public Customer findById(int custId) {

        try {
            return jdbcTemplate.query(FIND_BY_ID_SQL, this::customer, custId).get(0);
        } catch (Exception e) {
            throw new RecordNotFoundException("Customer Id Does Not Exist");
        }

    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, this::customer);
    }

    @Override
    public void update(int custId, Customer customer) {
        jdbcTemplate.update(UPDATE_BY_ID_SQL, customer.getCustName(), customer.getCustAddress(), customer.getCustAccountBalance(), customer.getCustDOB(), customer.getCustContactNumber(), customer.getCustUID(), customer.getCustPancard(), customer.getCustEmailId(), customer.getCustPassword(), custId);


    }

    @Override
    public void patchUpdateAddress(int custId, String custAddress) {
        jdbcTemplate.update(PATCH_UPDATE_ADDRESS_SQL, custAddress, custId);

    }

    @Override
    public void pathchUpdateAccountBalance(int custId, double custAccountBalance) {
        jdbcTemplate.update(PATCH_UPDATE_ACCOUNT_BALANCE_SQL, custAccountBalance, custId);
    }

    @Override
    public void deleteById(int custId) {
        jdbcTemplate.update(DELETE_BY_ID_SQL, custId);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL_SQL);
    }
}
