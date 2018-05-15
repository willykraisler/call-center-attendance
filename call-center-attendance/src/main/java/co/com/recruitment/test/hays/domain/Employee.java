package co.com.recruitment.test.hays.domain;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public abstract class Employee implements Callable<Integer>{


	private long consumeMin;
	private Integer id;
	private long amountOfCalls;
	private String fullName;
	private String numberAnswered;
	private long priority;
	private Boolean isBusy = Boolean.FALSE;


	@Override
	public Integer call() throws Exception {

		synchronized (this) {
			isBusy = Boolean.TRUE;
		}		

		try {
			System.out.println(this.getClass().getSimpleName() + " speaking: " + getFullName() + " - Attending: " + numberAnswered );
			TimeUnit.SECONDS.sleep(consumeMin);
			synchronized (this) {
				amountOfCalls ++;		
				isBusy = Boolean.FALSE;
				System.out.println("\n" + this.getClass().getSimpleName()+": " + getFullName() + " - Answered To: " + numberAnswered + " - Time: " + consumeMin + " - Total Calls : " + amountOfCalls);
			}
			return id;
		}catch (InterruptedException e) {			
			isBusy = Boolean.FALSE;
			return 0;

		}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumberAnswered() {
		return numberAnswered;
	}

	public void setNumberAnswered(String numberAnswered) {
		this.numberAnswered = numberAnswered;
	}

}
