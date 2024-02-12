package com.Employee.Employee.ServiceImplementation;

import java.util.Comparator;

import com.Employee.Employee.Entity.Employee;

public class SalaryComparator implements Comparator<Employee>{
	
	

	@Override
	public int compare(Employee e1, Employee e2) {
		// TODO Auto-generated method stub
		int diff = e1.getSalary() - e2.getSalary();
		return diff;
	
	}
	

}
