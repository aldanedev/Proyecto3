package com.nttdata.customerservice.customers.application;

import com.nttdata.customerservice.customers.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
  private String id;
  private String name;
  private String type;

  public static CustomerResponse fromAggregate(Customer customer) {
    return new CustomerResponse(customer.getId(), customer.getName(), customer.getType().name());
  }
}
