package pe.com.restapibank.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.restapibank.entity.AccountCredit;

public interface IAccountCreditRepository extends ReactiveCrudRepository<AccountCredit, Integer>{

}
