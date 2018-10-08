package com.microideation.samples.hazelcastcaching.service.impl;

import com.microideation.samples.hazelcastcaching.domain.Customer;
import com.microideation.samples.hazelcastcaching.repository.CustomerRepository;
import com.microideation.samples.hazelcastcaching.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);

    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "customer", key = "#oldEmail"),
            @CacheEvict(value = "customer", key = "#result.mobile")
    })
    public Customer updateCustomerEmail(Long id, String oldEmail, String newEmail) {

        Optional<Customer> customerOpt = customerRepository.findById(id);
        Customer customer = customerOpt.get();
        customer.setEmail(newEmail);
        return customerRepository.save(customer);

    }

    @Override
    @Cacheable(value = "customer", unless = "#result == null")
    public Customer findByMobile(String mobile) {

        log.info("findByMobile -> Inside service class : " + mobile);
        return customerRepository.findByMobile(mobile);

    }

    @Override
    @Cacheable(value = "customer", unless = "#result == null")
    public Customer findByEmail(String email) {

        log.info("findByEmail -> Inside service class : " + email);
        return customerRepository.findByEmail(email);

    }
}

