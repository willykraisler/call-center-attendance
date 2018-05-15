package co.com.recruitment.test.hays.concurrency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
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
	private static final int JUST_ONE_SECOND = 0;
//	private List<Future<Integer>> futures;

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
		
		if(candidate.isPresent()) {	
			//Set the time that client needs to solve the problem
			candidate.get().setConsumeMin(call.getTimeToSolveIssue());
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
}
