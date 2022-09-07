package com.nttdata.customerservice.customers.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nttdata.customerservice.customers.domain.Customer;
import com.nttdata.customerservice.customers.domain.CustomerRepository;
import com.nttdata.customerservice.customers.domain.CustomerType;
import com.nttdata.customerservice.customers.infrastructure.mapping.CustomerDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

  @Autowired
  public CustomerStore customerStore;

  public Mono<Customer> save(Customer customer) {
    return customerStore.save(CustomerDocument.builder()
        .id(customer.getId())
        .name(customer.getName())
        .type(customer.getType().name())
        .build())
        .map(customerDocument -> Customer.builder()
            .id(customerDocument.getId())
            .name(customerDocument.getName())
            .type(CustomerType.valueOf(customerDocument.getType()))
            .build());

  }

  public Mono<Customer> findById(String id) {
    return customerStore.findById(id)
        .map(customerDocument -> Customer.builder()
            .id(customerDocument.getId())
            .name(customerDocument.getName())
            .type(CustomerType.valueOf(customerDocument.getType()))
            .build());
  }

  @Override
  public Flux<Customer> findAll() {

    return customerStore.findAll()
        .map(customerDocument -> Customer.builder()
            .id(customerDocument.getId())
            .name(customerDocument.getName())
            .type(CustomerType.valueOf(customerDocument.getType()))
            .build());
  }
}
