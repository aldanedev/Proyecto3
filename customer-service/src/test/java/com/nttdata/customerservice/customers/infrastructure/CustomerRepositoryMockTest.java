package com.nttdata.customerservice.customers.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.nttdata.customerservice.customers.infrastructure.mapping.CustomerDocument;
import com.nttdata.customerservice.customers.infrastructure.persistence.CustomerStore;

@DataMongoTest
public class CustomerRepositoryMockTest {
  @Autowired
  private CustomerStore customerRepository;

  @Test
  public void whenSaveCustomer_thenCustomerIsSaved() {
    CustomerDocument customer = CustomerDocument.builder()
        .name("John")
        .build();
    customerRepository.save(customer);
    Assertions.assertNotEquals(customer.getId(), "");
  }
}
