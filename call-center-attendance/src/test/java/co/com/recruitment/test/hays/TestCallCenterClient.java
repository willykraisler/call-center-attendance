package co.com.recruitment.test.hays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.dispatcher.injection.DispatcherModule;
import co.com.recruitment.test.hays.domain.Call;
import co.com.recruitment.test.hays.domain.Employee;
import co.com.recruitment.test.hays.util.Calculator;
import co.com.recruitment.test.hays.util.CallsFactory;
import co.com.recruitment.test.hays.util.Role;
import net.lamberto.junit.GuiceJUnitRunner;
import net.lamberto.junit.GuiceJUnitRunner.GuiceModules;


@RunWith(GuiceJUnitRunner.class)
@GuiceModules(DispatcherModule.class)
public class TestCallCenterClient {

	private static final int TEN_CALLS_SIMULTANEOUSLY = 10;
	private static final int TWENTY_CALLS_SIMULTANEOUSLY = 20;
	private static final int DELAY_PROCESS = 10;
	
	private static final int EXCLUSIVE = 1;
	
	private CallsFactory callsFactory;
	private List<Employee> callCenter;
	private int callsByDirector;
	private int callsBySupervisor;
	private int callsByOperator;
	private int totalCalls;
	
	@Inject
	private IDispatcherService dispatcher; 
	
	@Inject
	private Calculator calculator;
	
	@Before
	public void initTest() {
		//Get whole calls 
		callsFactory = new CallsFactory();		
		
		//Initialize the staff and provider
		dispatcher.init();
		
		//Get the employees	
		callCenter = dispatcher.getCallCenter().getEmployees();
		
	}
	
	/**
	 * It calls until the queue will be empty
	 * @return
	 */
	private Consumer<Call> callUntilDontHaveQueue() {
		return call ->  {			
			do {
				dispatcher.dispatchCall(call);	
				
			} while (!dispatcher.getQueue().isEmpty());
		};
	}
	
	/**
	 * Calculate the amount necessary to the tests
	 */
	private void calculateTotalCalls() {
		callsByDirector   = calculator.getAmountByRole().apply(callCenter, Role.DIRECTOR);
		callsBySupervisor = calculator.getAmountByRole().apply(callCenter, Role.SUPERVISOR);
		callsByOperator   = calculator.getAmountByRole().apply(callCenter, Role.OPERATOR);
		totalCalls 		  = calculator.getTotal().apply(callCenter);
		
	}

	@Test
	public void amount_with_ten_calls() throws InterruptedException {
		//Incoming ten calls
		callsFactory.startCalls(TEN_CALLS_SIMULTANEOUSLY + EXCLUSIVE).stream().sequential()
		.filter(Objects::nonNull)
		.forEach(callUntilDontHaveQueue());
		
		//This a minimal delay to wait that it finish to process any call
		TimeUnit.SECONDS.sleep(DELAY_PROCESS);
		
		//Calculate the total of calls and split by role
		calculateTotalCalls();
		
		//this assert guarantee that the most efficient call center agent is the operator with major 
		//amount of calls processed 
		assertTrue( callsByDirector < callsBySupervisor);
		assertTrue( callsBySupervisor < callsByOperator);
		assertTrue( callsByDirector < callsByOperator);
		assertEquals(TEN_CALLS_SIMULTANEOUSLY, totalCalls);

	}

	
	
	
	@Test
	public void greater_than_ten_calls() throws InterruptedException {
		//Greater than ten calls
		callsFactory.startCalls(TWENTY_CALLS_SIMULTANEOUSLY + EXCLUSIVE).stream().sequential()
		.filter(Objects::nonNull)
		.forEach(callUntilDontHaveQueue());

		//This a minimal delay to wait that it finish to process any call
		TimeUnit.SECONDS.sleep(DELAY_PROCESS);
		
		calculateTotalCalls();
		//this assert guarantee that the most efficient call center agent is the operator with major 
		//amount of calls processed 
		assertTrue( callsByDirector < callsBySupervisor);
		assertTrue( callsBySupervisor < callsByOperator);
		assertTrue( callsByDirector < callsByOperator);
		assertEquals(TWENTY_CALLS_SIMULTANEOUSLY, totalCalls);

	}

	

}
