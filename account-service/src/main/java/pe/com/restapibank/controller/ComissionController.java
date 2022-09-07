package pe.com.restapibank.controller;

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
import pe.com.restapibank.entity.Comission;
import pe.com.restapibank.service.IComissionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comission")
@Slf4j
public class ComissionController {
	
	@Autowired
	private IComissionService comissionService;
	
	@GetMapping
	public ResponseEntity<Flux<Comission>> getAll(){
		Flux<Comission> getAll = comissionService.getAll();
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Comission*****");
		log.info("*************************************************************");
		return new ResponseEntity<Flux<Comission>>(getAll,HttpStatus.OK);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Flux<Comission>> findById(@PathVariable Integer id){
    	Flux<Comission> p = comissionService.findById(id);
        return new ResponseEntity<Flux<Comission>>(p, HttpStatus.OK);
    }
    
    
	@PostMapping
	public ResponseEntity<Mono<Comission>> create (@RequestBody Comission Comission){
		log.info("*****Inicio: Crear Comissiona*****");
		log.info("*************************************************************");
		Mono<Comission> p = comissionService.create(Comission);
		return new ResponseEntity<Mono<Comission>>(p, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Mono<Comission>> update (@RequestBody Comission Comission){
		Mono<Comission> p = comissionService.update(Comission);
		return new ResponseEntity<Mono<Comission>>(p, HttpStatus.CREATED);
	}
	
    @DeleteMapping("/delete")
    public ResponseEntity<Mono<Void>> delete(@RequestBody Comission Comission ){
    	Mono<Void> p = comissionService.delete(Comission);
        return new ResponseEntity<Mono<Void>>(p, HttpStatus.NO_CONTENT);
    }
    	
}
