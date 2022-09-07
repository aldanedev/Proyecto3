package pe.com.restapibank.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.restapibank.entity.Movement;

public interface IMovementRepository extends ReactiveCrudRepository<Movement, Integer>{

}
