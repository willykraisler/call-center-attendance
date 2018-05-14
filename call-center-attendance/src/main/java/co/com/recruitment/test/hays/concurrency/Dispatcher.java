package co.com.recruitment.test.hays.concurrency;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

import com.google.inject.Inject;

import co.com.recruitment.test.hays.concurrency.interfaces.IDispatcherService;
import co.com.recruitment.test.hays.domain.Call;
import co.com.recruitment.test.hays.domain.Employee;

public class Dispatcher implements IDispatcherService{

	private Queue<Call> callsOnHold;	
	private ExecutorService executorService;
	private CallCenter callcenter;

	@Inject
	public Dispatcher(ExecutorServiceProvider executorServiceProvider, CallCenter callcenter){
		executorService  = executorServiceProvider.provide();
		this.callcenter = callcenter;
		callsOnHold = new LinkedList<>();
	}

	@Override
	public void dispatchCall(Call call) {
		
		Optional<Employee> candidate = findSomeOne();
		
		if(candidate.isPresent()) {			
			executorService.submit(candidate.get());
		}else {
			callsOnHold.add(call);			
		}	
		
	}
	
	public Optional<Employee> findSomeOne() {
		return allArefree()
				.sorted(Comparator.comparing(Employee::getPriority))
				.findFirst();
	}
	
	
	public Stream<Employee> allArefree() {
		return  callcenter.getEmployees().stream()
				.filter(this::isNotBusy);
	}
	
	public Boolean isNotBusy(Employee employee) {		
		return !employee.getIsBusy();
	}
	
	
	

}
