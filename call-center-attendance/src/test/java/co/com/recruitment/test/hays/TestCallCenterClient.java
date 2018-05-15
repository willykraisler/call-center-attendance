package co.com.recruitment.test.hays;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.dispatcher.injection.DispatcherModule;
import co.com.recruitment.test.hays.util.CallsFactory;
import net.lamberto.junit.GuiceJUnitRunner;
import net.lamberto.junit.GuiceJUnitRunner.GuiceModules;


@RunWith(GuiceJUnitRunner.class)
@GuiceModules(DispatcherModule.class)
public class TestCallCenterClient {


	@Inject
	private IDispatcherService dispatcher; 

	CallsFactory callsFactory;

	private static final long RESULT_TEN = 10;

	@Before
	public void initTest() {
		//Get whole calls 
		callsFactory = new CallsFactory();
	}

	@Test
	public void amountWithTenCalls() throws InterruptedException {
		//Incoming calls
		callsFactory.startCalls(11).stream()
		.filter(Objects::nonNull)
		.forEach(call ->  {			
			do {
				dispatcher.dispatchCall(call);				
			} while (!dispatcher.getQueue().isEmpty());
		});


		TimeUnit.SECONDS.sleep(10);
		assertEquals(dispatcher.getCallCenter().getEmployees().stream().mapToLong(e -> e.getAmountOfCalls()).sum(), RESULT_TEN);


	}

}
