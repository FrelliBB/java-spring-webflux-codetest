package com.example.demo.domain;

import com.example.demo.web.CustomerSort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

class CustomerServiceTest {

    CustomerService service = new CustomerService();

    @Test
    void sortCustomersByDueTime() {
        Instant now = Instant.now();

        Customer first = withDueTime(now.atZone(ZoneId.of("+00:00")));
        Customer second = withDueTime(now.atZone(ZoneId.of("+01:00")));
        Customer third = withDueTime(now.plusMillis(1).atZone(ZoneId.of("+01:00")));

        List<Customer> customers = Arrays.asList(second, first, third);

        List<Customer> sorted = service.sortCustomers(Flux.fromIterable(customers), CustomerSort.DUE_TIME_ASC).collectList().block();
        Assertions.assertThat(sorted).containsExactly(first, second, third);
    }

    private Customer withDueTime(ZonedDateTime dueTime) {
        return new Customer(0L, "name", dueTime, ZonedDateTime.now());
    }
}