package pe.com.restapibank.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import pe.com.restapibank.client.CustomerClient;
import pe.com.restapibank.entity.AccountCredit;
import pe.com.restapibank.entity.AccountSaving;

import pe.com.restapibank.models.CustomerResponse;
import pe.com.restapibank.models.CustomerType;
import pe.com.restapibank.repository.IAccountSavingRepository;

import pe.com.restapibank.service.IAccountSavingClientService;
import pe.com.restapibank.service.IAccountSavingService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class AccountSavingImpl implements IAccountSavingService {

	@Autowired
	private IAccountSavingRepository iIAccountSavingRepository;

	@Autowired
	private IAccountSavingClientService accountSavingService;

	@Autowired
	private CustomerClient customerClient;

	@Override
	public Flux<AccountSaving> findAll() {
		return iIAccountSavingRepository.findAll();
	}

	@Override
	public Flux<AccountSaving> findById(Integer id) {
		return iIAccountSavingRepository.findAll().filter(x -> x.getIdSaving().equals(id));
	}
	
	@Override
	public Mono<AccountSaving> getById(Integer id) {
		return iIAccountSavingRepository.findById(id);
	}

	@Override
	public Mono<AccountSaving> save(AccountSaving account_saving) {
		return iIAccountSavingRepository.save(account_saving);
	}

	@Override
	public Mono<Void> delete(AccountSaving account_saving) {
		return iIAccountSavingRepository.delete(account_saving);
	}

	// Un cliente personal solo puede tener un máximo de una cuenta de ahorro,
	// una cuenta corriente o cuentas a plazo fijo.
	@Override
	public Mono<AccountSaving> saveAccSavingByClient(AccountSaving accountSaving) {

		CustomerResponse customer = customerClient.get(accountSaving.getCustomerId()).getBody();

		log.info("*****Guardando: saveAccSavingByClientPersonnel*****");
		Flux<AccountSaving> savingss = accountSavingService.getAccountSavingByClient(accountSaving.getIdClient());

		if (accountSaving.getBalance() >= 0) {
			if (customer.getType().equals(CustomerType.PERSONAL)) {
				if (!(savingss.count().block().longValue() > 0)) {
					log.info("**EXITO: Se creó correctamente la Cuenta de Ahorro/Corriente para el cliente personal**");
					return iIAccountSavingRepository.save(accountSaving);
				} else {
					log.info("**CREACIÓN FALLIDA: La cuenta de ahorro para el cliente ya existe**");
					return null;
				}
			} else {
				log.info("**CREACIÓN FALLIDA: El cliente no es tipo personal**");
				return null;
			}
		} else {
			log.info("**CREACIÓN FALLIDA: El monto de la cuenta no puede ser menor a 0**");
			return null;
		}

	}

	// VIP • Cuenta de ahorro que requiere un monto mínimo de promedio diario cada
	// mes. Adicionalmente,
	// para solicitar este producto el cliente debe tener una tarjeta de crédito con
	// el banco
	// al momento de la creación de la cuenta
	@Override
	public Mono<AccountSaving> saveAccSavingClientVIP(AccountSaving accountSaving) {
		CustomerResponse customer = customerClient.get(accountSaving.getCustomerId()).getBody();

		Flux<AccountCredit> getAccountCreditByClient = accountSavingService
				.getAccountCreditByClient(accountSaving.getIdClient());

		// Valida si el cliente pertenece a Personal_VIP
		if (customer.getType().equals(CustomerType.PERSONAL_VIP)) {
			// Valida que tenga una tarjeta de crédito
			if (getAccountCreditByClient.count().block().longValue() > 0) {
				log.info("**EXITO: Se creó correctamente la Cuenta de Ahorro para el cliente personal_VIP**");
				return iIAccountSavingRepository.save(accountSaving);
			} else {
				log.info("**CREACIÓN FALLIDA: El cliente no cuenta con una tarjeta de crédito**");
				return null;
			}
		} else {
			log.info("**CREACIÓN FALLIDA: El cliente no es tipo personal_VIP**");
			return null;
		}

	}

	@Override
	public Mono<AccountSaving> depositAccSaving(AccountSaving accountSaving) {
		return iIAccountSavingRepository.save(accountSaving);
	}

	@Override
	public Mono<AccountSaving> retreatAccSaving(AccountSaving accountSaving) {
		return iIAccountSavingRepository.save(accountSaving);
	}

	// ▪ PYME • Cuenta corriente sin comisión de mantenimiento. Como requisito, el
	// cliente debe tener
	// una tarjeta de crédito con el banco al momento de la creación de la cuenta.
	@Override
	public Mono<AccountSaving> saveAccCurrentBussinessPyme(AccountSaving accountSaving) {
		CustomerResponse customer = customerClient.get(accountSaving.getCustomerId()).getBody();

		Flux<AccountCredit> getAccountCreditByClient = accountSavingService
				.getAccountCreditByClient(accountSaving.getIdClient());

		// Valida si el cliente pertenece a Personal_VIP
		if (customer.getType().equals(CustomerType.BUSINESS_PYME)) {
			// Valida que tenga una tarjeta de crédito
			if (getAccountCreditByClient.count().block().longValue() > 0) {
				log.info("**EXITO: Se creó correctamente la Cuenta Corriente para el cliente Empresarial_PYME**");
				return iIAccountSavingRepository.save(accountSaving);
			} else {
				log.info("**CREACIÓN FALLIDA: El cliente no cuenta con una tarjeta de crédito**");
				return null;
			}
		} else {
			log.info("**CREACIÓN FALLIDA: El cliente no es tipo Empresarial_Pyme**");
			return null;
		}
	}

}
