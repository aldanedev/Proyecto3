package com.nttdata.customerservice.customers.application.search_all;

import org.springframework.stereotype.Service;

import com.nttdata.customerservice.customers.application.CustomerResponse;
import com.nttdata.customerservice.customers.domain.CustomerRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class AllCustomersSearcher {
  private final CustomerRepository customerRepository;

  public Flux<CustomerResponse> searchAll() {
    return customerRepository.findAll()
        .map(CustomerResponse::fromAggregate);
  }
}
