package com.nttdata.customerservice.customers.application.create;

import org.springframework.stereotype.Service;

import com.nttdata.customerservice.customers.application.CustomerResponse;
import com.nttdata.customerservice.customers.domain.Customer;
import com.nttdata.customerservice.customers.domain.CustomerRepository;
import com.nttdata.customerservice.customers.domain.exceptions.CustomerNotFoundException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerCreator {

  private final CustomerRepository repository;

  public Mono<CustomerResponse> create(String name, String type) {

    return repository.save(Customer.create(name, type))
        .map(CustomerResponse::fromAggregate);
  }

  public Mono<CustomerResponse> update(String id, String name, String type) {
    return repository.findById(id)
        .map(customer -> Customer.update(id, name, type))
        .switchIfEmpty(Mono.error(new CustomerNotFoundException("Customer not found")))
        .flatMap(repository::save)
        .map(CustomerResponse::fromAggregate);
  }
}
