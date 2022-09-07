package pe.com.restapibank.service;

import pe.com.restapibank.entity.AccountFixed;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountFixedService {
	
	Flux<AccountFixed> findAll();
	Flux<AccountFixed> findById(Integer id);
	Flux<AccountFixed> findByIdClient(Integer idClient);
	Mono<AccountFixed> create(AccountFixed fixed);
	Mono<AccountFixed> depositFixed(AccountFixed fixed);
	Mono<AccountFixed> retreatFixed(AccountFixed fixed);
	
	//
	Mono<AccountFixed> saveAccountFixedByClient(AccountFixed accountFixed);
}
