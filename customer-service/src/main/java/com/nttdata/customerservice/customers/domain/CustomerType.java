package com.nttdata.customerservice.customers.domain;

public enum CustomerType {
  PERSONAL, BUSINESS;

  static public boolean isValid(String type) {
    for (CustomerType customerType : CustomerType.values()) {
      if (customerType.name().equals(type)) {
        return true;
      }
    }
    return false;
  }
}
