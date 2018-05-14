package co.com.recruitment.test.hays.dispatcher.injection;

import com.google.inject.AbstractModule;

import co.com.recruitment.test.hays.concurrency.Dispatcher;
import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;

public class DispatcherModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDispatcherService.class).to(Dispatcher.class);		
	}
	

}
