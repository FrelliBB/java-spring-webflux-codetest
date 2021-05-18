package com.example.demo.web;

import com.example.demo.domain.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

import static java.util.Comparator.comparing;

@Getter
@RequiredArgsConstructor
public enum CustomerSort {

    DUE_TIME_ASC(comparing(Customer::getDueTime)),
    DUE_TIME_DESC(DUE_TIME_ASC.getComparator().reversed());

    public static final String DEFAULT = "DUE_TIME_ASC";

    private final Comparator<Customer> comparator;

}
