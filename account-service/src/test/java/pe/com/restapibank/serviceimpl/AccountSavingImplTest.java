package pe.com.restapibank.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import pe.com.restapibank.entity.AccountSaving;
import pe.com.restapibank.repository.IAccountSavingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class AccountSavingImplTest {

	@Mock
	private IAccountSavingRepository iIAccountSavingRepository;
	
	@InjectMocks
	private AccountSavingImpl accountSavingImpl;
	
	@Autowired
	private Mono<AccountSaving> accountSaving;
	
	@Autowired
	private Flux<AccountSaving> accountSavingg;
	
	@BeforeEach
	void ini() {
		accountSaving = Mono.just(new AccountSaving(1,"Ahorro Soles","S/.","Cuenta ahorro","2020202020202020202","2022-09-27T12:26:30.107",100.2,12.2,1,2,3,1,"631a5f93f7444c28833a7df2"));
	
		accountSavingg = accountSavingImpl.findAll();
	}
//
//	@Test
//	void testFindAll() {
//		Mockito.when(iIAccountSavingRepository.findAll()).thenReturn(accountSavingg);
//		assertEquals("Exito: El Id es = ", accountSavingg.blockFirst().getIdSaving());
//	}
	
	@Test
	void testFindById() {
        Mockito.when(iIAccountSavingRepository.findById(1)).thenReturn(accountSaving);
        Mono<AccountSaving> obj = accountSavingImpl.getById(1);
        assertEquals(accountSaving, obj);
	}

//	@Test
//	void testSave() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSaveAccSavingByClient() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSaveAccSavingClientVIP() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDepositAccSaving() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRetreatAccSaving() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSaveAccCurrentBussinessPyme() {
//		fail("Not yet implemented");
//	}

}
