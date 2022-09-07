package pe.com.restapibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.log4j.Log4j2;
import pe.com.restapibank.entity.AccountFixed;
import pe.com.restapibank.service.IAccountFixedService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account_fixed")
@Log4j2
public class AccountFixedController {

	@Autowired
	private IAccountFixedService fixedService;
	
	@GetMapping
	public ResponseEntity<Flux<AccountFixed>> getAll(){
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Cuenta de plazo fijo*****");
		log.info("*************************************************************");
		Flux<AccountFixed> getAll = fixedService.findAll();
		return new ResponseEntity<Flux<AccountFixed>>(getAll,HttpStatus.OK);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Flux<AccountFixed>> findById(@PathVariable Integer id){
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Cuenta de plazo fijo por Id Cliente*****");
		log.info("*************************************************************");
    	Flux<AccountFixed> p = fixedService.findById(id);
        return new ResponseEntity<Flux<AccountFixed>>(p, HttpStatus.OK);
    }
    
    @GetMapping("/client/{id}")
    public ResponseEntity<Flux<AccountFixed>> findByIdClient(@PathVariable Integer id){
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Cuenta de plazo fijo por Id Cliente*****");
		log.info("*************************************************************");
    	Flux<AccountFixed> p = fixedService.findByIdClient(id);
        return new ResponseEntity<Flux<AccountFixed>>(p, HttpStatus.OK);
    }
    
	@PostMapping
	public ResponseEntity<Mono<AccountFixed>> create (@RequestBody AccountFixed fixed){
		log.info("*****Inicio: Crear cuenta de plazo fijo*****");
		Mono<AccountFixed> a = fixedService.create(fixed);
		return new ResponseEntity<Mono<AccountFixed>>(a, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Mono<AccountFixed>> update (@RequestBody AccountFixed fixed){
		log.info("*************************************************************");
		log.info("*****Inicio: Actualizar la cuenta a plazo fijo*****");
		log.info("*************************************************************");
		Mono<AccountFixed> a = fixedService.depositFixed(fixed);
		return new ResponseEntity<Mono<AccountFixed>>(a, HttpStatus.CREATED);
	}
	
	//
	@PostMapping("/fixedpersonal")
	public Mono<AccountFixed> saveAccCreditByClient(@RequestBody AccountFixed accountFixed){
		log.info("*****Inicio: saveAccCreditByClient*****");
		return fixedService.saveAccountFixedByClient(accountFixed);
	}
	
	
}
