package co.com.recruitment.test.hays.util;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.Objects;
import co.com.recruitment.test.hays.domain.Employee;
/**
 * Functions to calculate amounts about the calls
 * @author Andres
 *
 */
public class Calculator {

	
	private BiFunction<List<Employee>, Role , Integer> amountByRol = (empl, rol) -> 
		empl.stream().parallel()
		.filter(Objects::nonNull)
		.filter(emp -> emp.getRol() != null && emp.getRol().equals(rol))		
		.mapToInt(emp -> emp.getAmountOfCalls()).sum();


	private Function<List<Employee>, Integer> calculateTotal = empl -> 
		empl.stream()
		.filter(Objects::nonNull)
		.mapToInt(Employee::getAmountOfCalls).sum();


	/**
	 * Get the function to calculate the total of calls 
	 * @return the callback
	 */
	public Function<List<Employee>, Integer> getTotal() {
		return calculateTotal;
	}

	public void setTotal(Function<List<Employee>, Integer> total) {
		this.calculateTotal = total;
	}

	/**
	 * Get the binary function to calculate the amount of calls per role
	 * @return
	 */
	public BiFunction<List<Employee>, Role, Integer> getAmountByRole() {
		return amountByRol;
	}

	public void setAmountByRole(BiFunction<List<Employee>, Role, Integer> amountByRol) {
		this.amountByRol = amountByRol;
	}
}
