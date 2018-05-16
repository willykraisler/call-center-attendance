package co.com.recruitment.test.hays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.dispatcher.injection.DispatcherModule;
import co.com.recruitment.test.hays.domain.Call;
import co.com.recruitment.test.hays.domain.Employee;
import co.com.recruitment.test.hays.util.CallsFactory;
import co.com.recruitment.test.hays.util.Rol;
import co.com.recruitment.test.hays.util.Search;
import net.lamberto.junit.GuiceJUnitRunner;
import net.lamberto.junit.GuiceJUnitRunner.GuiceModules;


@RunWith(GuiceJUnitRunner.class)
@GuiceModules(DispatcherModule.class)
public class TestCallCenterClient {


	@Inject
	private IDispatcherService dispatcher; 
	
	@Inject
	private Search search;
	 

	CallsFactory callsFactory;
	List<Employee> callCenter;
	BiFunction<List<Employee>, Rol, Integer> amountByRol;

	private static final int TEN_CALLS_SIMULTANEOUSLY = 10;
	private static final int TWENTY_CALLS_SIMULTANEOUSLY = 20;
	private static final int EXCLUSIVE = 1;

	@Before
	public void initTest() {
		//Get whole calls 
		callsFactory = new CallsFactory();		
		callCenter = dispatcher.getCallCenter().getEmployees();
		amountByRol = search.getAmountByRol();
		
	}
	
	private Consumer<Call> callUntilDontHaveQueue() {
		return call ->  {			
			do {
				dispatcher.dispatchCall(call);				
			} while (!dispatcher.getQueue().isEmpty());
		};
	}

	@Test
	public void amount_with_ten_calls() throws InterruptedException {
		//Incoming ten calls
		callsFactory.startCalls(TEN_CALLS_SIMULTANEOUSLY + EXCLUSIVE).stream()
		.filter(Objects::nonNull)
		.forEach(callUntilDontHaveQueue());
		
		TimeUnit.SECONDS.sleep(TEN_CALLS_SIMULTANEOUSLY);
		
		int callsByDirector = amountByRol.apply(callCenter, Rol.DIRECTOR);
		int callsBySupervisor = amountByRol.apply(callCenter, Rol.SUPERVISOR);
		int callsByOperator = amountByRol.apply(callCenter, Rol.OPERATOR);
		
		assertTrue( callsByDirector < callsBySupervisor);
		assertTrue( callsBySupervisor < callsByOperator);
		
		assertEquals(TEN_CALLS_SIMULTANEOUSLY, callCenter.stream().parallel()
					 .mapToLong(e -> e.getAmountOfCalls()).sum());

	}
	
	
	@Test
	public void greater_than_ten_calls() throws InterruptedException {
		//Greater than ten calls
		callsFactory.startCalls(TWENTY_CALLS_SIMULTANEOUSLY + EXCLUSIVE).stream()
		.filter(Objects::nonNull)
		.forEach(callUntilDontHaveQueue());


		TimeUnit.SECONDS.sleep(TWENTY_CALLS_SIMULTANEOUSLY);
		
		int callsByDirector = amountByRol.apply(callCenter, Rol.DIRECTOR);
		int callsBySupervisor = amountByRol.apply(callCenter, Rol.SUPERVISOR);
		int callsByOperator = amountByRol.apply(callCenter, Rol.OPERATOR);
		
		assertTrue( callsByDirector < callsBySupervisor);
		assertTrue( callsBySupervisor < callsByOperator);
		
		assertEquals(TWENTY_CALLS_SIMULTANEOUSLY, dispatcher.getCallCenter().getEmployees().stream().parallel()
					 .mapToLong(e -> e.getAmountOfCalls()).sum());

	}

	

}
