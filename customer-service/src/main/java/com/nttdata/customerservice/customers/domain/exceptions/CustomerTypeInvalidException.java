package com.nttdata.customerservice.customers.domain.exceptions;

public class CustomerTypeInvalidException extends RuntimeException {
  public CustomerTypeInvalidException(String message) {
    super(message);
  }
}
