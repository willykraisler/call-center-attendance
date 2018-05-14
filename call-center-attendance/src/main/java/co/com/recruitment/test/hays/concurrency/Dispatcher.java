package co.com.recruitment.test.hays.concurrency;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;

import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.domain.Call;
import co.com.recruitment.test.hays.domain.Employee;

public class Dispatcher implements IDispatcherService{

	private Queue<Call> callsOnHold = new LinkedList<>();	
	
	private ExecutorService executorService;
	private CallCenter callcenter;

	@Inject
	public Dispatcher(ExecutorServiceProvider executorServiceProvider, CallCenter callcenter){
		this.callcenter = callcenter;
		executorService  = executorServiceProvider.provide();
	}

	@Override
	public void dispatchCall(Call call) {
		
		Optional<Employee> candidate = someoneIsfree();
		
		if(candidate.isPresent()) {			
			executorService.submit(candidate.get());
		}else {
			callsOnHold.add(call);			
		}		
		
	}
	
	public Optional<Employee> someoneIsfree() {
		return  callcenter.getEmployees().stream()
				.filter(this::isNotBusy)
				.findFirst();
	}
	
	public Boolean isNotBusy(Employee employee) {
		
		
		return Boolean.TRUE;
	}
	
	
	

}
