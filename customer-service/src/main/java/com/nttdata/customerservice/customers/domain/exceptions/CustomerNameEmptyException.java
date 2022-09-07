package com.nttdata.customerservice.customers.domain.exceptions;

public class CustomerNameEmptyException extends RuntimeException {
  public CustomerNameEmptyException(String message) {
    super(message);
  }
}
