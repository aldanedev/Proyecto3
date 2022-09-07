package pe.com.restapibank.controller;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pe.com.restapibank.entity.AccountSaving;
import pe.com.restapibank.entity.Movement;
import pe.com.restapibank.service.IAccountSavingClientService;
import pe.com.restapibank.service.IMovementService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movement")
@Slf4j
public class GenerateMovementController {
	
	@Autowired
	private IMovementService movementService;

	@Autowired
	private IAccountSavingClientService accountSavingClientService;
	
	@GetMapping
	public ResponseEntity<Flux<Movement>> getAll(){
		Flux<Movement> getAll = movementService.getAll();
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Movimientos*****");
		log.info("*************************************************************");
		return new ResponseEntity<Flux<Movement>>(getAll,HttpStatus.OK);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Flux<Movement>> findById(@PathVariable Integer id){
    	Flux<Movement> p = movementService.findById(id);
        return new ResponseEntity<Flux<Movement>>(p, HttpStatus.OK);
    }
    
	@PutMapping
	public ResponseEntity<Mono<Movement>> update (@RequestBody Movement Movement){
		Mono<Movement> p = movementService.update(Movement);
		return new ResponseEntity<Mono<Movement>>(p, HttpStatus.CREATED);
	}
	
    @DeleteMapping("/delete")
    public ResponseEntity<Mono<Void>> delete(@RequestBody Movement Movement ){
    	Mono<Void> p = movementService.delete(Movement);
        return new ResponseEntity<Mono<Void>>(p, HttpStatus.NO_CONTENT);
    }
    
    @SuppressWarnings("null")
	@GetMapping("movementclient/{idClient}")
    public ResponseEntity<Flux<Movement>> findByIdProductBankClient(@PathVariable Integer idClient){
    	// Ahorros
    	Flux<AccountSaving> objFluxaccountSavingClient = accountSavingClientService.getAccountSavingByClient(idClient);
    	List<AccountSaving> listAccountSavingClient = objFluxaccountSavingClient.collectList().block();
		Flux<Movement> objFluxAccountMovement = null;
    	List<Movement> listMovementClient = null;
    	List<Movement> listMovementAll = null;
		for (Iterator<AccountSaving> iterator = listAccountSavingClient.iterator(); iterator.hasNext();) {
			AccountSaving accountSaving = (AccountSaving) iterator.next();
			objFluxAccountMovement = movementService.findByIdSaving(accountSaving.getIdSaving());
			listMovementClient = objFluxAccountMovement.collectList().block();
			listMovementAll.addAll(listMovementClient);
		}
		Flux<Movement> fluxAll = Flux.fromIterable(listMovementAll);
		// Plazo Fijo
		// Creditro
        return new ResponseEntity<Flux<Movement>>(fluxAll, HttpStatus.OK);
    }    
    
    // Ahorro: libre de comisión por mantenimiento y con un límite máximo de movimientos mensuales.
	@PostMapping
	public ResponseEntity<Mono<Movement>> create(@RequestBody Movement movement){
		Mono<Movement> saving = movementService.create(movement);
		if (saving==null) {
			return new ResponseEntity<Mono<Movement>>(saving, HttpStatus.PRECONDITION_FAILED);
		}else{
			return new ResponseEntity<Mono<Movement>>(saving, HttpStatus.CREATED);
		}
	}
	
//	// Plazo fijo: libre de comisión por mantenimiento, solo permite un movimiento de retiro o depósito en un día específico del mes.
//	@PostMapping
//	public ResponseEntity<Mono<Movement>> createMovAccountFixed (@RequestBody Movement Movement){
//		log.info("*****Inicio: Crear Movimiento Plazo Fijo*****");
//		log.info("*************************************************************");
//		// Ahorro: libre de comisión por mantenimiento y con un límite máximo de movimientos mensuales.
//		Mono<Movement> p = movementService.create(Movement);
//		return new ResponseEntity<Mono<Movement>>(p, HttpStatus.CREATED);
//	}
	
}
