package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.Objects.requireNonNull;

public class CustomerService {

    public List<Customer> sortCustomersByDueTime(List<Customer> customers) {
        return sortCustomers(customers, comparing(Customer::getDueTime));
    }

    private List<Customer> sortCustomers(List<Customer> customers, Comparator<Customer> comparator) {
        requireNonNull(comparator);

        if (customers == null || customers.isEmpty()) {
            return emptyList();
        }

        List<Customer> sorted = new ArrayList<>(customers);
        sorted.sort(comparator);
        return sorted;
    }
}
