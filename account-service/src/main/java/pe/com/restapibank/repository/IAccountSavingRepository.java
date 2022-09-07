package pe.com.restapibank.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.com.restapibank.entity.AccountSaving;

public interface IAccountSavingRepository extends ReactiveCrudRepository<AccountSaving, Integer>{

}
