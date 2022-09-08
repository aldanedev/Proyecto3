package pe.com.restapibank.service;

import pe.com.restapibank.entity.AccountSaving;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountSavingService {
	
	Flux<AccountSaving> findAll();
	Mono<AccountSaving> getById(Integer id);
	Flux<AccountSaving> findById(Integer id);
	Mono<AccountSaving> save(AccountSaving account_saving);
	Mono<AccountSaving> saveAccSavingByClient(AccountSaving accountSaving);
	Mono<AccountSaving> depositAccSaving(AccountSaving accountSaving);
	Mono<AccountSaving> retreatAccSaving(AccountSaving accountSaving);	
	Mono<AccountSaving> saveAccSavingClientVIP(AccountSaving accountSaving);
	Mono<AccountSaving> saveAccCurrentBussinessPyme(AccountSaving accountSaving);
	Mono<Void> delete(AccountSaving account_saving);
}
