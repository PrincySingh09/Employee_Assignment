package com.Employee.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.Employee.Employee.Controller.EmployeeController;
import com.Employee.Employee.Entity.Employee;
import com.Employee.Employee.ServiceImplementation.EmployeeServiceImplementation;
import com.Employee.Employee.ServiceImplementation.SalaryComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeControllerTest {

  @Autowired
  private EmployeeController employeeController;

  @MockBean
  private EmployeeServiceImplementation employeeService;

  @Test
  void testAddEmployee() {
    Employee employee = new Employee(1, "Princy Singh", 50000, "It");
    when(employeeService.addEmployee(employee)).thenReturn(employee);

    Employee responseEmployee = (Employee) employeeController
      .addEmployee(employee)
      .getBody();

    assertEquals(employee, responseEmployee);
  }

  @Test
  void testRetrieveAllEmployees() {
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee(1, "Princy Singh", 50000, "It"));
    employees.add(new Employee(2, "Khushi Amb", 60000, "It"));
    when(employeeService.getAllEmployees()).thenReturn(employees);

    List<Employee> responseEmployee = (List<Employee>) employeeController
      .retrieveAllEmployees()
      .getBody();

    assertEquals(employees, responseEmployee);
  }

  @Test
  void testRetrieveEmployeeById() {
    Employee employee = new Employee(1, "Princy Singh", 50000, "It");
    when(employeeService.getEmployeeById(1)).thenReturn(Optional.of(employee));

    Employee responseEmployee = (Employee) employeeController
      .retrieveEmployeeById(1)
      .getBody();

    assertEquals(employee, responseEmployee);
  }

  @Test
  void testRetrieveEmployeeById_NotFound() {
    when(employeeService.getEmployeeById(1)).thenReturn(Optional.empty());

    ResponseEntity<?> responseEntity = employeeController.retrieveEmployeeById(
      1
    );

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
  }

  @Test
  void testUpdateEmployee() {
    Employee employee = new Employee(1, "Princy Singh", 50000, "It");
    when(employeeService.updateEmployee(1, employee)).thenReturn(employee);

    Employee responseEmployee = (Employee) employeeController
      .updateEmployee(1, employee)
      .getBody();

    assertEquals(employee, responseEmployee);
  }

  @Test
  void testSortedEmployees() {
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee(1, "Princy Singh", 60000, "It"));
    employees.add(new Employee(2, "Khushi Amb", 50000, "It"));

    //sort the employee
    Collections.sort(employees, new SalaryComparator());

    when(employeeService.getAllEmployeesSortedBySalary()).thenReturn(employees);

    List<Employee> responseEmployee = (List<Employee>) employeeController
      .getSortedEmployees()
      .getBody();

    assertEquals(employees, responseEmployee);
  }
  
  
  
}
