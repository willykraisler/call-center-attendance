package co.com.recruitment.test.hays;

import java.util.Objects;

import com.google.inject.Guice;
import com.google.inject.Injector;

import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.dispatcher.injection.DispatcherModule;
import co.com.recruitment.test.hays.util.CallsFactory;

public class CallCenterClient {


	public static void main(String[] args) {

		//Initialize guice injector
		Injector injector = Guice.createInjector(new DispatcherModule());

		//Initialize the dispatcher instance		
		IDispatcherService dispatcher = injector.getInstance(IDispatcherService.class);

		//Get whole calls 
		CallsFactory callsFactory = new CallsFactory();			

		//Incoming ten calls
		callsFactory.startCalls(10).stream()
		.filter(Objects::nonNull)
		.forEach(call ->  {			
			do {
				dispatcher.dispatchCall(call);				
			} while (!dispatcher.getQueue().isEmpty());
		});

	}



}
