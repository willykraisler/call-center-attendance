package co.com.recruitment.test.hays.concurrency.interfaces;

import java.util.Queue;
import java.util.concurrent.ExecutorService;

import co.com.recruitment.test.hays.domain.Call;
import co.com.recruitment.test.hays.util.CallCenter;
/**
 * Contract to build any dispatcher
 * @author Andres
 *
 */
public interface IDispatcherService {
	public void dispatchCall(Call call);
	public void init();
	public Queue<Call> getQueue();
	public CallCenter getCallCenter();
	public ExecutorService getExecutor();
}
