package com.example.demo.web;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class CustomerSortTest {

    @Test
    public void defaultSortIsValidValue() {
        assertThatNoException().isThrownBy(() -> CustomerSort.valueOf(CustomerSort.DEFAULT));
    }
}