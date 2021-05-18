package com.example.demo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

import static java.util.Comparator.comparing;

@Getter
@RequiredArgsConstructor
public enum CustomerSort {

    DUE_TIME_ASC(comparing(Customer::getDueTime)),
    DUE_TIME_DESC(DUE_TIME_ASC.getComparator().reversed());

    public static final String DEFAULT_VALUE = "DUE_TIME_ASC";
    public static final CustomerSort DEFAULT = CustomerSort.valueOf(DEFAULT_VALUE);

    private final Comparator<Customer> comparator;

}
