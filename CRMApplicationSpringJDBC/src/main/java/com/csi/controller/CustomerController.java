package com.csi.controller;

import com.csi.model.Customer;
import com.csi.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Customer customer) {
        customerServiceImpl.signUp(customer);
        log.info(" #### Trying to save Data for : " + customer.getCustName());
        return ResponseEntity.ok("Sign Up Done SuccessFully");
    }
    @GetMapping("/signin/{custEmailId}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmailId,@PathVariable String custPassword){
        return ResponseEntity.ok(customerServiceImpl.signIn(custEmailId, custPassword));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerServiceImpl.findAll());
    }

    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Customer> findById(@PathVariable int custId) {
        return ResponseEntity.ok(customerServiceImpl.findById(custId));

    }

    @PutMapping("/updatebyid/{custId}")
    public ResponseEntity<String> updateById(@PathVariable int custId, @RequestBody Customer customer) {
        log.info(" ### Trying to Update By Id : " + customer.getCustId());
        customerServiceImpl.update(custId, customer);
        return ResponseEntity.ok("Id Updated SuccessFully ...");
    }

    @PatchMapping("/onlyupdatecustaddress/{custId}/{custAddress}")
    public ResponseEntity<String> onlyUpdateCustAddress(@PathVariable int custId, @PathVariable String custAddress) {
        customerServiceImpl.patchUpdateAddress(custId, custAddress);
        return ResponseEntity.ok("Customer Address Updated SuccessFully");
    }

    @PatchMapping("/onlyupdatecustaccountbalance/{custId}/{custAccountBalance}")
    public ResponseEntity<String> onlyUpdateCustAccountBalance(@PathVariable int custId, @PathVariable double custAccountBalance) {
        customerServiceImpl.pathchUpdateAccountBalance(custId, custAccountBalance);
        return ResponseEntity.ok("Customer Account Balance Updated SuccessFully");
    }

    @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deleteById(@PathVariable int custId) {
        customerServiceImpl.deleteById(custId);
        return ResponseEntity.ok("Id Deleted SuccessFully ");
    }
    @GetMapping("/firstmaxsalary")
    public ResponseEntity<Customer> firstMaxSalary(){
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(Customer::getCustAccountBalance).reversed()).toList().get(0));
    }

    @GetMapping("/sortaccountbalance")
    public ResponseEntity<List<Customer>> sortAccountBalance(){
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(Customer::getCustAccountBalance).reversed()).toList());
    }


    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        customerServiceImpl.deleteAll();
        return ResponseEntity.ok("All Data Deleted SuccessFully ...");
    }


}
