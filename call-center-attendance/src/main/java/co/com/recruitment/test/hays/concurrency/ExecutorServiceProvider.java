package co.com.recruitment.test.hays.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.com.recruitment.test.hays.concurrency.interfaces.IExecutorServiceProvider;

/**
 * provide the executor concrete to the dispatcher class I separated the executor 
 * services because It could be change from here to avoid to change on the dispatcher class.
 * @author Andres
 *
 */
public class ExecutorServiceProvider implements IExecutorServiceProvider{

	public static final int POOL_SIZE = 10;
	
	private ExecutorService executor;
	

	public ExecutorService getExecutor() {
		return executor;
	}


	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	@Override
	public void provide() {
		setExecutor(Executors.newFixedThreadPool(POOL_SIZE));
	}
}
