package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.example.demo.domain.CustomerSort.DUE_TIME_ASC;
import static java.time.Duration.ofSeconds;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static reactor.core.publisher.Flux.fromIterable;

class CustomerServiceTest {

    CustomerService service = new CustomerService();

    @Test
    void sortCustomersByDueTime() {
        Instant now = Instant.now();

        Customer first = withDueTime(now.atZone(ZoneId.of("+00:00")));
        Customer second = withDueTime(now.atZone(ZoneId.of("+01:00")));
        Customer third = withDueTime(now.plusMillis(1).atZone(ZoneId.of("+01:00")));

        List<Customer> unsorted = asList(second, first, third);

        List<Customer> sorted = service.sortCustomers(fromIterable(unsorted), DUE_TIME_ASC).collectList().block(ofSeconds(1));
        assertThat(sorted).containsExactly(first, second, third);
    }

    private Customer withDueTime(ZonedDateTime dueTime) {
        return new Customer(0L, "name", dueTime, ZonedDateTime.now());
    }
}