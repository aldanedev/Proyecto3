package com.nttdata.customerservice.customers.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {
  public Mono<Customer> save(Customer customer);

  public Mono<Customer> findById(String id);

  public Flux<Customer> findAll();
}
