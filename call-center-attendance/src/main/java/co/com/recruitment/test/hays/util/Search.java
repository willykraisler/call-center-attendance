package co.com.recruitment.test.hays.util;

import java.util.List;
import java.util.function.BiFunction;

import co.com.recruitment.test.hays.domain.Employee;

public class Search {
	
	private BiFunction<List<Employee>, Rol , Integer> 
	amountByRol = (empl, rol) -> empl.stream().parallel()
								.filter(emp -> emp.getRol().equals(rol))
								.mapToInt(emp -> emp.getAmountOfCalls()).sum();

	public BiFunction<List<Employee>, Rol, Integer> getAmountByRol() {
		return amountByRol;
	}

	public void setAmountByRol(BiFunction<List<Employee>, Rol, Integer> amountByRol) {
		this.amountByRol = amountByRol;
	}
}
