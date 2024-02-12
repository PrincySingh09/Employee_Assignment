package com.Employee.Employee.Service;

import com.Employee.Employee.Entity.Employee;
import java.util.List;
import java.util.Optional; // Fix: Replace org.apache.el.stream.Optional with java.util.Optional

public interface EmployeeService {
  Employee addEmployee(Employee employee);
  List<Employee> getAllEmployees();
  Optional<Employee> getEmployeeById(Integer id);
  Employee updateEmployee(Integer id, Employee newEmployeeData);
  void deleteEmployee(Integer id);
  List<Employee> getAllEmployeesSortedBySalary();
}
