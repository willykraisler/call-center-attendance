package co.com.recruitment.test.hays.dispatcher.injection;

import com.google.inject.AbstractModule;

import co.com.recruitment.test.hays.concurrency.CallCenter;
import co.com.recruitment.test.hays.concurrency.Dispatcher;
import co.com.recruitment.test.hays.concurrency.ExecutorServiceProvider;
import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.concurrency.interfaces.IExecutorServiceProvider;
import co.com.recruitment.test.hays.util.Search;

public class DispatcherModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CallCenter.class);
		bind(Search.class);
		bind(IDispatcherService.class).to(Dispatcher.class);
		bind(IExecutorServiceProvider.class).to(ExecutorServiceProvider.class);
	}
	

}
