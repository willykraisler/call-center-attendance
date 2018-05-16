package co.com.recruitment.test.hays.dispatcher.injection;

import com.google.inject.AbstractModule;

import co.com.recruitment.test.hays.concurrency.Dispatcher;
import co.com.recruitment.test.hays.concurrency.ExecutorServiceProvider;
import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.concurrency.interfaces.IExecutorServiceProvider;
import co.com.recruitment.test.hays.util.Calculator;
import co.com.recruitment.test.hays.util.CallCenter;
/**
 * The injector to provide dispatcher functionality 
 * I thought that Google Guice was simple and it adapts to this case
 * @author Andres
 *
 */
public class DispatcherModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CallCenter.class);
		bind(Calculator.class);
		bind(IDispatcherService.class).to(Dispatcher.class);
		bind(IExecutorServiceProvider.class).to(ExecutorServiceProvider.class);
	}
	

}
