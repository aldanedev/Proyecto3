package com.nttdata.customerservice.customers.domain.exceptions;

public class CustomerNotFoundException extends RuntimeException {

  public CustomerNotFoundException(String message) {
    super(message);
  }
}
