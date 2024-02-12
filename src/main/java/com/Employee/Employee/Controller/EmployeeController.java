package com.Employee.Employee.Controller;

import com.Employee.Employee.Entity.Employee;
import com.Employee.Employee.Exception.EmployeeNotFoundException;
import com.Employee.Employee.ServiceImplementation.EmployeeServiceImplementation;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  EmployeeServiceImplementation esi;

  @PostMapping("/create")
  public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
    Employee savedEmployee = esi.addEmployee(employee);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Employee>> retrieveAllEmployees() {
    List<Employee> employees = esi.getAllEmployees();
    return ResponseEntity.ok(employees);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> retrieveEmployeeById(@PathVariable Integer id) {
    try {
      Optional<Employee> employee = esi.getEmployeeById(id);

      if (!employee.isPresent()) {
        throw new EmployeeNotFoundException(
          "Employee not found with id: " + id
        );
      }
      return ResponseEntity.ok(employee.get());
    } catch (EmployeeNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Employee> updateEmployee(
    @PathVariable Integer id,
    @RequestBody Employee employee
  ) {
    Employee updatedEmployee = esi.updateEmployee(id, employee);
    return ResponseEntity.ok(updatedEmployee);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
    esi.deleteEmployee(id);
    return ResponseEntity.noContent().build();
  }

  //get the sorted list of employees
  @GetMapping("/sorted")
  public ResponseEntity<List<Employee>> getSortedEmployees() {
    List<Employee> employees = esi.getAllEmployeesSortedBySalary();
    return ResponseEntity.ok(employees);
  }
}
