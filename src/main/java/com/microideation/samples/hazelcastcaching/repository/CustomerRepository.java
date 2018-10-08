package com.microideation.samples.hazelcastcaching.repository;

import com.microideation.samples.hazelcastcaching.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByMobile(String mobile);
    Customer findByEmail(String email);

}
