package pe.com.restapibank.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.com.restapibank.entity.AccountFixed;

public interface IAccountFixedRepository extends ReactiveCrudRepository<AccountFixed, Integer>{

}
