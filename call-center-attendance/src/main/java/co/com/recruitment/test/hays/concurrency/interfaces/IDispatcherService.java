package co.com.recruitment.test.hays.concurrency.interfaces;

import java.util.Queue;
import java.util.concurrent.ExecutorService;

import co.com.recruitment.test.hays.concurrency.CallCenter;
import co.com.recruitment.test.hays.domain.Call;

public interface IDispatcherService {
	public void dispatchCall(Call call);
	public Queue<Call> getQueue();
	public CallCenter getCallCenter();
	public ExecutorService getExecutor();
}
