package com.Employee.Employee.ServiceImplementation;

import com.Employee.Employee.Entity.Employee;
import com.Employee.Employee.Exception.*;
import com.Employee.Employee.Repository.*;
import com.Employee.Employee.Service.EmployeeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public Employee addEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public Optional<Employee> getEmployeeById(Integer id) {
    Optional<Employee> optionalEmployee = employeeRepository.findById(id);
    return optionalEmployee;
  }

  @Override
  public Employee updateEmployee(Integer id, Employee newEmployeeData) {
    return employeeRepository
      .findById(id)
      .map(employee -> {
        employee.setName(newEmployeeData.getName());
        employee.setSalary(newEmployeeData.getSalary());
        return employeeRepository.save(employee);
      })
      .orElseThrow(() ->
        new EmployeeNotFoundException("Employee not found with id: " + id)
      );
  }

  @Override
  public void deleteEmployee(Integer id) {
    if (!employeeRepository.existsById(id)) {
      throw new EmployeeNotFoundException("Employee not found with id: " + id);
    }
    employeeRepository.deleteById(id);
  }

  @Override
  public List<Employee> getAllEmployeesSortedBySalary() {
    List<Employee> employees = employeeRepository.findAll();
    employees.sort(new SalaryComparator());
    return employees;
  }
}
