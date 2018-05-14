package co.com.recruitment.test.hays.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.com.recruitment.test.hays.concurrency.interfaces.IExecutorServiceProvider;

public class ExecutorServiceProvider implements IExecutorServiceProvider{

	public static final int POOL_SIZE = 10;
	
	@Override
	public ExecutorService provide() {
		return Executors.newFixedThreadPool(POOL_SIZE);
	}
}
