package com.example.demo.domain;

import com.example.demo.web.CustomerSort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;

@Service
public class CustomerService {

    public List<Customer> sortCustomers(List<Customer> customers, CustomerSort order) {
        requireNonNull(order);

        if (customers == null || customers.isEmpty()) {
            return emptyList();
        }

        List<Customer> sorted = new ArrayList<>(customers);
        sorted.sort(order.getComparator());
        return sorted;
    }
}
