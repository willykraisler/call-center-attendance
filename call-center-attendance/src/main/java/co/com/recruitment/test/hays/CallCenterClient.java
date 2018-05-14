package co.com.recruitment.test.hays;

import com.google.inject.Guice;
import com.google.inject.Injector;

import co.com.recruitment.test.hays.concurrency.CallCenter;
import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.dispatcher.injection.DispatcherModule;

public class CallCenterClient {


	public static void main(String[] args) {
		
		
		Injector injector = Guice.createInjector(new DispatcherModule());
		
		//Initialize the dispatcher instance		
		IDispatcherService dispatcher = injector.getInstance(IDispatcherService.class);

		//Get whole workers enables to receive calls
		CallCenter callCenter = injector.getInstance(CallCenter.class);		
		callCenter.hireStaff();	
		
		//Incoming calls
		callCenter.startCalls().forEach(call -> dispatcher.dispatchCall(call));
	}



}
