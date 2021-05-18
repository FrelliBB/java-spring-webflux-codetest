package com.example.demo.domain;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static java.util.Objects.requireNonNull;

@Service
public class CustomerService {

    public Flux<Customer> sortCustomers(Flux<Customer> customers, CustomerSort order) {
        requireNonNull(customers);
        requireNonNull(order);

        return customers.sort(order.getComparator());
    }
}
