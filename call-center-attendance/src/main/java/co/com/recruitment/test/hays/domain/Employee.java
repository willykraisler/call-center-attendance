package co.com.recruitment.test.hays.domain;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public abstract class Employee implements Callable<Boolean>{


	private long consumeMin;
	private long amountOfCalls;
	private String fullName;
	private long priority;

	@Override
	public Boolean call() throws Exception {

		TimeUnit.MINUTES.sleep(consumeMin);
		amountOfCalls ++;	

		return Boolean.TRUE;
	}
	
	
	public Boolean isNotBusy(Employee employee) {
		
		
		return Boolean.TRUE;
	}

	public long getConsumeMin() {
		return consumeMin;
	}


	public void setConsumeMin(long consumeMin) {
		this.consumeMin = consumeMin;
	}


	public long getAmountOfCalls() {
		return amountOfCalls;
	}


	public void setAmountOfCalls(long amountOfCalls) {
		this.amountOfCalls = amountOfCalls;
	}

	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

}
