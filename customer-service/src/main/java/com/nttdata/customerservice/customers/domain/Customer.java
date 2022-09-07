package com.nttdata.customerservice.customers.domain;

import com.nttdata.customerservice.customers.domain.exceptions.CustomerNameEmptyException;
import com.nttdata.customerservice.customers.domain.exceptions.CustomerTypeInvalidException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Customer {
  private String id;
  private String name;
  private CustomerType type;

  public Customer(String name, String type) {
    if (name.isEmpty()) {
      throw new CustomerNameEmptyException("Customer name is empty");
    }
    this.name = name;

    if (!CustomerType.isValid(type)) {
      throw new CustomerTypeInvalidException("Customer type is invalid");
    }

    this.type = CustomerType.valueOf(type);
  }

  public static Customer create(String name, String type) {
    return new Customer(name, type);
  }

  public static Customer update(String id, String name, String type) {
    Customer customer = new Customer(name, type);
    customer.setId(id);
    return customer;
  }
}
