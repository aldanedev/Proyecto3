package pe.com.restapibank.service;

import pe.com.restapibank.entity.AccountCredit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountCreditService {
	
	Flux<AccountCredit> findAll();
	Flux<AccountCredit> findById(Integer id);
	Flux<AccountCredit> findByIdClient(Integer idClient);
	Mono<AccountCredit> create(AccountCredit fixed);
	Mono<AccountCredit> depositCredit(AccountCredit fixed);
	Mono<AccountCredit> retreatCredit(AccountCredit fixed);
	Mono<Void> delete(AccountCredit fixed);
	//
	Mono<AccountCredit> saveAccountCreditByClient(AccountCredit accountCredit);
}
