package co.com.recruitment.test.hays.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import co.com.recruitment.test.hays.domain.Call;
import co.com.recruitment.test.hays.domain.Director;
import co.com.recruitment.test.hays.domain.Employee;
import co.com.recruitment.test.hays.domain.Operator;
import co.com.recruitment.test.hays.domain.Supervisor;
import co.com.recruitment.test.hays.util.RandomUtil;

public class CallCenter {	

	public static final int START_INCLUSIVE = 1;
	public static final int END_EXCLUSIVE = 11;
	public static final String STANDARD_NUMBER = "123456789";
	

	private List<Employee> employees;
	

	public void hireStaff() {

		employees = new ArrayList<>();

		Employee employee = new Director();		
		employee.setAmountOfCalls(0);
		employee.setFullName("Manuel Arango");
		employee.setPriority(3);

		employees.add(employee);		

		employee = new Supervisor();		
		employee.setAmountOfCalls(0);
		employee.setFullName("Hugo Prieto");
		employee.setPriority(2);

		employees.add(employee);		

		employee = new Supervisor();		
		employee.setAmountOfCalls(0);
		employee.setFullName("Natalia Grisales");
		employee.setPriority(2);

		employees.add(employee);

		employee = new Operator();		
		employee.setAmountOfCalls(0);
		employee.setFullName("Jenny Rabelo");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Operator();		
		employee.setAmountOfCalls(0);
		employee.setFullName("Maria Diaz");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Operator();		
		employee.setAmountOfCalls(0);
		employee.setFullName("Juan Manrique");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Operator();		
		employee.setAmountOfCalls(0);
		employee.setFullName("Daniel Castellanos");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Operator();		
		employee.setAmountOfCalls(0);
		employee.setFullName("Samuel de los Rios");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Operator();		
		employee.setAmountOfCalls(0);
		employee.setFullName("Eduardo Carrillo");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Operator();		
		employee.setAmountOfCalls(0);
		employee.setFullName("William Pinilla");
		employee.setPriority(1);

		employees.add(employee);

		System.out.println(":::Staff hired:: Nice!");	

	}	

	public List<Call> startCalls() {
		 return IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
				.mapToObj(this::createCall)
				.collect(Collectors.toList());				        
	}
	
	private Call createCall(int c) {		
		Call call = new Call();		
		call.setNumber(STANDARD_NUMBER.concat(String.valueOf(c)));
		call.setTimeToSolveIssue(RandomUtil.getCallDuration());		
		return call;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	

}
