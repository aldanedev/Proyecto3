package com.nttdata.customerservice.customers.infrastructure.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.customerservice.customers.infrastructure.mapping.CustomerDocument;

public interface CustomerStore extends ReactiveMongoRepository<CustomerDocument, String> {

}
