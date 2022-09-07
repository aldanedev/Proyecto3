package com.nttdata.customerservice.customers.infrastructure.mapping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "customers")
@Data
@Builder
public class CustomerDocument {
  @Id
  private String id;

  private String name;

  private String type;
}
