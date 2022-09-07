package pe.com.restapibank.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.restapibank.entity.Comission;

public interface IComissionRepository extends ReactiveCrudRepository<Comission, Integer>{

}
