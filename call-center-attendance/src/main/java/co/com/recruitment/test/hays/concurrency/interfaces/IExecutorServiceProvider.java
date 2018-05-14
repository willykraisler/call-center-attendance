package co.com.recruitment.test.hays.concurrency.interfaces;

import java.util.concurrent.ExecutorService;

public interface IExecutorServiceProvider {
	public ExecutorService provide();
}
