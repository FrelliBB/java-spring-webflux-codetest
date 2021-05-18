package com.example.demo.web;

import com.example.demo.domain.Customer;
import com.example.demo.domain.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customers/sort")
    public Flux<Customer> sortCustomers(@RequestBody Flux<Customer> request,
                                        @RequestParam(value = "order", defaultValue = CustomerSort.DEFAULT_VALUE) CustomerSort order) {
        return customerService.sortCustomers(request, order);
    }

}
