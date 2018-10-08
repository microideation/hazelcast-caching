package com.microideation.samples.hazelcastcaching.controller;

import com.microideation.samples.hazelcastcaching.domain.Customer;
import com.microideation.samples.hazelcastcaching.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/api/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer saveCustomer(@RequestBody Customer customer) {

        log.info("saveCustomer -> Request received: " + customer);
        Customer saved = customerService.saveCustomer(customer);
        log.info("savedCustomer -> " + saved);
        return saved;

    }

    @PostMapping(value = "/api/customers/email")
    public Customer updateEmail(@RequestParam(value = "id") Long id,
                                @RequestParam(value = "oldEmail") String oldEmail,
                                @RequestParam(value = "newEmail") String newEmail) {

        log.info("updateEmail -> Request received: Id - " + id + " : Old Email -" + oldEmail + " New Email - " + newEmail);
        Customer saved = customerService.updateCustomerEmail(id, oldEmail,newEmail);
        log.info("savedCustomer -> " + saved);
        return saved;

    }

    @GetMapping("/api/customers/search/email")
    public Customer getCustomerByEmail(@RequestParam(value = "email") String email) {

        log.info("getCustomerByEmail -> Email : " + email);
        Customer customer = customerService.findByEmail(email);
        log.info("getCustomerByEmail -> Found customer : " + customer);
        return customer;

    }

    @GetMapping("/api/customers/search/mobile")
    public Customer getCustomerByMobile(@RequestParam(value = "mobile") String mobile) {

        log.info("getCustomerByMobile -> Mobile : " + mobile);
        Customer customer = customerService.findByMobile(mobile);
        log.info("getCustomerByMobile -> Found customer : " + customer);
        return customer;

    }

}
