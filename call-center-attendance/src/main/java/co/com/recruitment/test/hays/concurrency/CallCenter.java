package co.com.recruitment.test.hays.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.com.recruitment.test.hays.domain.Employee;
import co.com.recruitment.test.hays.util.Rol;

public class CallCenter {	

	private Map<Integer,Employee> employeesCache;
	private List<Employee> employees;

	public void hireStaff() {

		employeesCache = new HashMap<>();
		employees = new ArrayList<>();

		Employee employee = new Employee();		
		employee.setId(1);	
		employee.setRol(Rol.DIRECTOR);
		employee.setAmountOfCalls(0);		
		employee.setFullName("Manuel Arango");
		employee.setPriority(3);
		
		
		employeesCache.put(employee.getId(),employee);		

		employee = new Employee();
		employee.setId(2);
		employee.setRol(Rol.DIRECTOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Hugo Prieto");
		employee.setPriority(3);

		employeesCache.put(employee.getId(),employee);		

		employee = new Employee();
		employee.setId(3);
		employee.setRol(Rol.SUPERVISOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Natalia Grisales");
		employee.setPriority(2);

		employeesCache.put(employee.getId(),employee);

		employee = new Employee();
		employee.setId(4);
		employee.setRol(Rol.SUPERVISOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Jenny Rabelo");
		employee.setPriority(2);

		employeesCache.put(employee.getId(),employee);

		employee = new Employee();
		employee.setId(5);
		employee.setRol(Rol.SUPERVISOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Maria Diaz");
		employee.setPriority(2);

		employeesCache.put(employee.getId(),employee);

		employee = new Employee();
		employee.setId(6);
		employee.setRol(Rol.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Juan Manrique");
		employee.setPriority(1);

		employeesCache.put(employee.getId(),employee);

		employee = new Employee();
		employee.setId(7);
		employee.setRol(Rol.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Daniel Castellanos");
		employee.setPriority(1);

		employeesCache.put(employee.getId(),employee);

		employee = new Employee();
		employee.setId(8);
		employee.setRol(Rol.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Samuel de los Rios");
		employee.setPriority(1);

		employeesCache.put(employee.getId(),employee);

		employee = new Employee();
		employee.setId(9);
		employee.setRol(Rol.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("Eduardo Carrillo");
		employee.setPriority(1);

		employeesCache.put(employee.getId(),employee);

		employee = new Employee();
		employee.setId(10);
		employee.setRol(Rol.OPERATOR);
		employee.setAmountOfCalls(0);
		employee.setFullName("William Pinilla");
		employee.setPriority(1);

		employeesCache.put(employee.getId(),employee);
		
		employees.addAll(employeesCache.values());

		System.out.println(":::Staff is working:: Nice!");	

	}	
	
	public Map<Integer, Employee> getEmployeesCache() {
		return employeesCache;
	}

	public void setEmployeesCache(Map<Integer, Employee> employeesCache) {
		this.employeesCache = employeesCache;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	

}
