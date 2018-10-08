package com.microideation.samples.hazelcastcaching.service;

import com.microideation.samples.hazelcastcaching.domain.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer updateCustomerEmail(Long id, String oldEmail, String newEmail);

    Customer findByMobile(String mobile);

    Customer findByEmail(String email);
}
