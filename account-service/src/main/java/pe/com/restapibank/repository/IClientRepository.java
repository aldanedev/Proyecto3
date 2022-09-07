package pe.com.restapibank.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.com.restapibank.entity.Client;

public interface IClientRepository extends ReactiveCrudRepository<Client, Integer>{

}
