package pe.com.restapibank.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.restapibank.entity.AccountCredit;
import pe.com.restapibank.entity.Client;
import pe.com.restapibank.repository.IAccountCreditRepository;
import pe.com.restapibank.repository.IClientRepository;
import pe.com.restapibank.service.IAccountCreditService;
import pe.com.restapibank.service.IAccountSavingClientService;
import pe.com.restapibank.utils.Constant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountCreditServiceImpl implements IAccountCreditService{

	@Autowired
	IAccountCreditRepository creditRepo;

	@Autowired
	IAccountCreditRepository perso;
	
	
	@Autowired
	IClientRepository clientRepo;
	
	@Autowired
	IAccountSavingClientService accountCredClient;

	@Override
	public Flux<AccountCredit> findAll() {
		return creditRepo.findAll();
	}

	@Override
	public Flux<AccountCredit> findById(Integer id) {
		return creditRepo.findAll().filter(x -> x.getIdCredit().equals(id));
	}
	
	@Override
	public Flux<AccountCredit> findByIdClient(Integer idClient) {
		return creditRepo.findAll().filter(x -> x.getIdClient().equals(idClient));
	}
	
	//Un cliente puede tener un producto de crédito sin la obligación 
	//de tener una cuenta bancaria en la institución.
	@Override
	public Mono<AccountCredit> create(AccountCredit Credit) {
		return creditRepo.save(Credit);
	}

	//Actualiza el deposito
	//Actualiza el saldo y la cantidad de meses de la linea de credito
	@Override
	public Mono<AccountCredit> depositCredit(AccountCredit Credit) {
		return creditRepo.save(Credit);
	}
	
	@Override
	public Mono<AccountCredit> retreatCredit(AccountCredit Credit) {
		return creditRepo.save(Credit);
	}

	@Override
	public Mono<Void> delete(AccountCredit credit) {
		return creditRepo.delete(credit);
	}


//	Personal: solo se permite un solo crédito por persona.
	@Override
	public Mono<AccountCredit> saveAccountCreditByClient(AccountCredit accountCredit) {
		log.info("*****INICIO: Crear cuenta de credito por persona*****");
		Flux<AccountCredit> getAccountCreditByClient = accountCredClient.getAccountCreditByClient(accountCredit.getIdClient());
		Mono<Client> getClientById = clientRepo.findById(accountCredit.getIdClient());
		if(getClientById.block().getTypeClient().equals(Constant.Personnel) || 
				getClientById.block().getTypeClient().equals(Constant.Personnel_vip) || getClientById.block().getTypeClient().equals(Constant.Bussiness_pyme)) {
			if(!(getAccountCreditByClient.count().block().longValue()>0)) {
				log.info("**EXITO: Se creó correctamente la cuenta de crédito Personal**");
				return creditRepo.save(accountCredit);
			}else {
				log.info("**ERROR: Ya existe una cuenta de crédito personal para el cliente**");
				return null;
			}
		}else {
			log.info("**ERROR: El cliente no es de tipo Personal**");
			return null;
		}

	}
}
