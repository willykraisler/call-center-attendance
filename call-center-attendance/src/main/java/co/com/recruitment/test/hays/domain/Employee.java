package co.com.recruitment.test.hays.domain;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public abstract class Employee implements Callable<Boolean>{


	private long consumeMin;
	private long amountOfCalls;
	private String fullName;
	private long priority;
	private Boolean isBusy;

	@Override
	public Boolean call() throws Exception {
		
		isBusy = Boolean.TRUE;

		TimeUnit.MINUTES.sleep(consumeMin);
		amountOfCalls ++;	

		isBusy = Boolean.FALSE;
		
		return Boolean.FALSE;
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
	
	public Boolean getIsBusy() {
		return isBusy;
	}

	public void setIsBusy(Boolean isBusy) {
		this.isBusy = isBusy;
	}

}
