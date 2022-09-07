package pe.com.restapibank.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.restapibank.entity.AccountCredit;
import pe.com.restapibank.entity.AccountFixed;
import pe.com.restapibank.entity.AccountSaving;
import pe.com.restapibank.entity.Client;
import pe.com.restapibank.repository.IAccountCreditRepository;
import pe.com.restapibank.repository.IAccountFixedRepository;
import pe.com.restapibank.repository.IAccountSavingRepository;
import pe.com.restapibank.repository.IClientRepository;
import pe.com.restapibank.service.IAccountSavingClientService;
import reactor.core.publisher.Flux;

@Service
public class AccountSavingClientImpl implements IAccountSavingClientService{

//	private final CopyOnWriteArrayList<Client> mgclient = new CopyOnWriteArrayList<>();
//	private final CopyOnWriteArrayList<AccountSaving> mgaccountsaving = new CopyOnWriteArrayList<>();	
	
	@Autowired
	private IClientRepository iClientRepository;

	@Autowired
	private IAccountSavingRepository iIAccountSavingRepository;
	
	@Autowired 
	private IAccountCreditRepository iAccountCreditRepo;
	
	@Autowired 
	private IAccountFixedRepository iAccountFixedRepo;

	@Override
	public Flux<Client> getByIdClient(Integer idClient) {
//	    return Flux.fromIterable(mgclient)
//		        .filter(xmg_client -> xmg_client.getIdClient().equals(idClient))
//		        .next();
		return iClientRepository.findAll()
				.filter(xmg_client-> xmg_client.getIdClient().equals(idClient));		
	}

	//Obtiene la cuenta de ahorro por cliente
	@Override
	public Flux<AccountSaving> getAccountSavingByClient(Integer idClient) {
		return iIAccountSavingRepository.findAll()
				.filter(xmg_account_saving-> xmg_account_saving.getIdClient().equals(idClient));
	}
	
	//Obtiene la cuenta de credito por cliente
	@Override
	public Flux<AccountCredit> getAccountCreditByClient(Integer idClient) {
		return iAccountCreditRepo.findAll().filter(x -> x.getIdClient().equals(idClient));
	}
	
	//Obtiene la cuenta de plazo fijo por cliente
	@Override
	public Flux<AccountFixed> getAccountFixedByClient(Integer idClient) {
		return iAccountFixedRepo.findAll().filter(x -> x.getIdClient().equals(idClient));
	}

	
}
