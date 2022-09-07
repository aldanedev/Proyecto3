package com.nttdata.customerservice.customers.application.find;

import org.springframework.stereotype.Service;

import com.nttdata.customerservice.customers.application.CustomerResponse;
import com.nttdata.customerservice.customers.domain.CustomerRepository;
import com.nttdata.customerservice.customers.domain.exceptions.CustomerNotFoundException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerFinder {
  private final CustomerRepository repository;

  public Mono<CustomerResponse> find(String id) {
    return repository.findById(id)
        .map(CustomerResponse::fromAggregate)
        .switchIfEmpty(Mono.error(new CustomerNotFoundException("Customer not found")));
  }
}
