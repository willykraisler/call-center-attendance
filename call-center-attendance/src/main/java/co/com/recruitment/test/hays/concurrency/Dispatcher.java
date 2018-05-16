package co.com.recruitment.test.hays.concurrency;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.google.inject.Inject;

import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.domain.Call;
import co.com.recruitment.test.hays.domain.Employee;

public class Dispatcher implements IDispatcherService{

	private Queue<Call> callsOnHold;
	private ExecutorService executorService;
	private CallCenter callcenter;	
	private static final int JUST_ONE_SECOND = 1;

	@Inject
	public Dispatcher(ExecutorServiceProvider executorServiceProvider, CallCenter callcenter){
		executorService  = executorServiceProvider.provide();
		this.callcenter = callcenter;
		callcenter.hireStaff();
		callsOnHold = new LinkedList<>();	
	}

	@Override
	public void dispatchCall(Call call) {
		
		mustWaitToAssignCall();
		Optional<Employee> candidate = findSomeOne();
		
		if(candidate.isPresent()){			
			if(callsOnHold.contains(call)) {
				callsOnHold.poll();
			}				
			candidate.get().setNumberAnswered(call.getNumber());
			executorService.submit(candidate.get());			
		}else {
			callsOnHold.add(call);			
		}		
	}

	private void mustWaitToAssignCall() {
		try {
			TimeUnit.SECONDS.sleep(JUST_ONE_SECOND);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

	public Optional<Employee> findSomeOne() {
		return getFreeEmployee()
				.sorted(Comparator.comparing(Employee::getPriority))
				.findFirst();
	}

	public Stream<Employee> getFreeEmployee() {
		return  callcenter.getEmployees().stream()
				.filter(this::isNotBusy);
	}

	public Boolean isNotBusy(Employee employee) {		
		return !employee.getIsBusy();
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

	@Override
	public Queue<Call> getQueue() {
		return getCallsOnHold();
	}

	@Override
	public CallCenter getCallCenter() {
		return getCallcenter();
	}
	
	public CallCenter getCallcenter() {
		return callcenter;
	}

	@Override
	public ExecutorService getExecutor() {	
		return getExecutor();
	}
	
}
