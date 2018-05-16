package co.com.recruitment.test.hays.domain;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import co.com.recruitment.test.hays.util.RandomUtil;
import co.com.recruitment.test.hays.util.Rol;

public class Employee implements Callable<Integer>{


	private long usedSeconds;
	private Integer id;
	private long amountOfCalls;
	private String fullName;
	private Rol rol;
	private String numberAnswered;
	private long priority;
	private Boolean isBusy = Boolean.FALSE;


	@Override
	public Integer call() throws Exception {

		isBusy = Boolean.TRUE;
		System.out.println(getRol().name() + " speaking: " + getFullName() + " - Attending: " + getNumberAnswered());		
		setUsedSeconds(RandomUtil.getCallDuration());
		
		try {			
			TimeUnit.SECONDS.sleep(getUsedSeconds());			
		}catch (InterruptedException e) {			
			isBusy = Boolean.FALSE;
			return 0;
		}
		
		amountOfCalls++;		
		System.out.println("\n" + getRol().name() +": " + getFullName() + " - Answered To: " + numberAnswered + " - Time: " + usedSeconds + " - Total Calls : " + amountOfCalls);
		isBusy = Boolean.FALSE;
		
		return 1;

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
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	public long getUsedSeconds() {
		return usedSeconds;
	}

	public void setUsedSeconds(long usedSeconds) {
		this.usedSeconds = usedSeconds;
	}

}
