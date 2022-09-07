package com.nttdata.customerservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nttdata.customerservice.controllers.request.CustomerRequest;
import com.nttdata.customerservice.controllers.request.ErrorMessage;
import com.nttdata.customerservice.customers.application.CustomerResponse;
import com.nttdata.customerservice.customers.application.create.CustomerCreator;
import com.nttdata.customerservice.customers.application.find.CustomerFinder;
import com.nttdata.customerservice.customers.application.search_all.AllCustomersSearcher;
import com.nttdata.customerservice.customers.domain.exceptions.CustomerNameEmptyException;
import com.nttdata.customerservice.customers.domain.exceptions.CustomerNotFoundException;
import com.nttdata.customerservice.customers.domain.exceptions.CustomerTypeInvalidException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@RequestMapping("/api/v1/customers")
public class CustomerController {

  @Autowired
  private CustomerCreator customerCreator;

  @Autowired
  private AllCustomersSearcher allCustomersSearcher;

  @Autowired
  private CustomerFinder customerFinder;

  @GetMapping
  public ResponseEntity<Flux<CustomerResponse>> list() {
    return ResponseEntity.ok(allCustomersSearcher.searchAll());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<CustomerResponse>> get(@PathVariable String id) {
    return customerFinder.find(id)
        .map(customerResponse -> ResponseEntity.ok(customerResponse));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<CustomerResponse>> update(@PathVariable String id,
      @RequestBody CustomerRequest customerRequest) {
    return customerCreator.update(id, customerRequest.getName(), customerRequest.getType())
        .map(customerResponse -> ResponseEntity.ok(customerResponse));
  }

  @PostMapping
  public Mono<ResponseEntity<CustomerResponse>> create(@RequestBody CustomerRequest customerRequest) {
    return customerCreator.create(customerRequest.getName(), customerRequest.getType())
        .flatMap(customerResponse -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(customerResponse)));

  }

  @ExceptionHandler(value = {
      CustomerNameEmptyException.class,
      CustomerTypeInvalidException.class
  })
  Mono<ResponseEntity<ErrorMessage>> badRequest(Exception e) {
    return Mono.just(ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage())));
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  Mono<ResponseEntity<ErrorMessage>> notFound(Exception e) {
    return Mono.just(ResponseEntity.notFound().build());
  }

}
