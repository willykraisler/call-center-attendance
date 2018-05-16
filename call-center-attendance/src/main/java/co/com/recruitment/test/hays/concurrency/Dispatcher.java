package co.com.recruitment.test.hays.concurrency;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;

import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.domain.Call;
import co.com.recruitment.test.hays.domain.Employee;
import co.com.recruitment.test.hays.util.CallCenter;
/**
 * This class has the responsibility to assign N amount of call and after 
 * that it assign to any no busy employee
 * @author Andres
 *
 */
public class Dispatcher implements IDispatcherService{

	private static final int JUST_ONE_SECOND = 600;
	private Queue<Call> callsOnHold = new LinkedList<>();
	private ExecutorService executorService;
	private CallCenter callcenter;	
	private ExecutorServiceProvider exeServiceProvider;


	@Inject
	public Dispatcher(ExecutorServiceProvider exeServiceProvider, CallCenter callcenter){
		this.exeServiceProvider  = exeServiceProvider;
		this.callcenter = callcenter;	
	}
	
	@Override
	public void init() {
		//Initialize employee instances
		callcenter.hireStaff();
		//Initialize the pool of threads
		exeServiceProvider.provide();		
	}	

	@Override
	public void dispatchCall(Call call) {
		//Delay to avoid that one employee has two call simultaneously
		mustWaitToAssignCall();
		
		//Look for some employee that isn't busy
		Optional<Employee> candidate = callcenter.findSomeOne();		
		
		if(candidate.isPresent()){			
			//Check that it's a call on hold and it removes from the queue 
			if(callsOnHold.contains(call)) {
				callsOnHold.poll();
			}
			//it assigns the number to the employee and the candidate attend the call
			candidate.get().setNumberAnswered(call.getNumber());
			exeServiceProvider.getExecutor().submit(candidate.get());			
		}else {
			//Add to the queue because everyone is busy
			callsOnHold.add(call);			
		}		
	}

	private void mustWaitToAssignCall() {
		try {
			TimeUnit.MILLISECONDS.sleep(JUST_ONE_SECOND);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Queue<Call> getCallsOnHold() {
		return callsOnHold;
	}

	public void setCallsOnHold(Queue<Call> callsOnHold) {
		this.callsOnHold = callsOnHold;
	}
	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
	
	public CallCenter getCallcenter() {
		return callcenter;
	}
	
	@Override
	public Queue<Call> getQueue() {
		return getCallsOnHold();
	}

	@Override
	public CallCenter getCallCenter() {
		return getCallcenter();
	}
	
	@Override
	public ExecutorService getExecutor() {	
		return getExecutor();
	}	
}
