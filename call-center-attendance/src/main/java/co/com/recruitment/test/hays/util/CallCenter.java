package co.com.recruitment.test.hays.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import co.com.recruitment.test.hays.domain.Employee;
/**
 * Factory of instance about the employees enable to attend calls
 * and the functionalities to know who is enable 
 * @author Andres
 *
 */
public class CallCenter {	


	private List<Employee> employees;

	public void hireStaff() {

		
		employees = new ArrayList<>();

		Employee employee = new Employee();		
		employee.setId(1);	
		employee.setRol(Role.DIRECTOR);
		employee.setAmountOfCalls(0);		
		employee.setFullName("Manuel Arango");
		employee.setPriority(3);		
		
		employees.add(employee);		

		employee = new Employee();
		employee.setId(2);
		employee.setRol(Role.DIRECTOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Hugo Prieto");
		employee.setPriority(3);

		employees.add(employee);		

		employee = new Employee();
		employee.setId(3);
		employee.setRol(Role.SUPERVISOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Natalia Grisales");
		employee.setPriority(2);

		employees.add(employee);

		employee = new Employee();
		employee.setId(4);
		employee.setRol(Role.SUPERVISOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Jenny Rabelo");
		employee.setPriority(2);

		employees.add(employee);

		employee = new Employee();
		employee.setId(5);
		employee.setRol(Role.SUPERVISOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Maria Diaz");
		employee.setPriority(2);

		employees.add(employee);

		employee = new Employee();
		employee.setId(6);
		employee.setRol(Role.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Juan Manrique");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Employee();
		employee.setId(7);
		employee.setRol(Role.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Daniel Castellanos");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Employee();
		employee.setId(8);
		employee.setRol(Role.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Samuel de los Rios");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Employee();
		employee.setId(9);
		employee.setRol(Role.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Eduardo Carrillo");
		employee.setPriority(1);

		employees.add(employee);

		employee = new Employee();
		employee.setId(10);
		employee.setRol(Role.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("William Pinilla");
		employee.setPriority(1);

		employees.add(employee);		

		System.out.println(":::Staff is working:: Nice!");	

	}
	
	/**
	 * Get any enable employee to answer any call 
	 * @return the enable person to attend the incoming call
	 * but first all, order by priority
	 */
	public Optional<Employee> findSomeOne() {
		return getFreeEmployee()
				.sorted(Comparator.comparing(Employee::getPriority))
				.findFirst();
	}

	/**
	 * Traverse the stream filtering by it's not busy
	 * @return the enable candidates to answer the incoming call 
	 */
	public Stream<Employee> getFreeEmployee() {
		return  employees.stream().sequential()
				.filter(Objects::nonNull)
				.filter(this::isNotBusy);
	}

	/**
	 * Check the condition about Is it busy the employee
	 * @param employee any person on the parallel stream
	 * @return True if the employee is not busy otherwise False
	 */
	public Boolean isNotBusy(Employee employee) {		
		return !employee.getIsBusy();
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	

}
