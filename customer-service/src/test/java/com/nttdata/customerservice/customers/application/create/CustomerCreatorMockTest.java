package com.nttdata.customerservice.customers.application.create;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.nttdata.customerservice.customers.domain.Customer;
import com.nttdata.customerservice.customers.domain.CustomerRepository;

import reactor.core.publisher.Mono;

@SpringBootTest
public class CustomerCreatorMockTest {
  @Mock
  private CustomerRepository customerRepository;

  private CustomerCreator creator;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    creator = new CustomerCreator(customerRepository);
    Customer customer = Customer.create("name", "BUSINESS");
    Customer customerReturn = Customer.builder().id("id").name("name").build();
    Mockito.when(customerRepository.save(customer)).thenReturn(Mono.just((customerReturn)));
  }

  @Test
  public void whenCreateCustomer_thenCustomerIsCreated() {
    creator.create("name", "PERSONAL").subscribe(customerResponse -> {
      assertEquals(customerResponse.getId(), "id");
      assertEquals(customerResponse.getName(), "name");
    });
  }

}
