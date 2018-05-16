package co.com.recruitment.test.hays.domain;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import co.com.recruitment.test.hays.util.RandomUtil;
import co.com.recruitment.test.hays.util.Role;

/**
 * Entity that it encapsulates the logic about one Employee
 * @author Andres
 *
 */
public class Employee implements Callable<Integer>{


	private int usedSeconds;
	private Integer id;
	private int amountOfCalls;
	private String fullName;
	private Role rol;
	private String numberAnswered;
	private int priority;
	private Boolean isBusy = Boolean.FALSE;

	ReadWriteLock lock = new ReentrantReadWriteLock();

	@Override
	public Integer call() throws Exception {
		isBusy = Boolean.TRUE;
		usedSeconds = RandomUtil.getCallDuration();
		System.out.println(getRol().name() + " speaking: " + getFullName() + " - Attending: " + getNumberAnswered());

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

	public int getAmountOfCalls() {
		return amountOfCalls;
	}


	public void setAmountOfCalls(int amountOfCalls) {
		this.amountOfCalls = amountOfCalls;
	}

	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
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

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}


	public int getUsedSeconds() {
		return usedSeconds;
	}

	public void setUsedSeconds(int usedSeconds) {
		this.usedSeconds = usedSeconds;
	}

}
