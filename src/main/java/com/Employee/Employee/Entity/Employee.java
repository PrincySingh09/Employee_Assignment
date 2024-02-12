package com.Employee.Employee.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {

  public Employee(int id, String name, int salary, String department) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.department = department;
	}

@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "salary")
  private int salary;

  @Column(name = "department")
  private String department;

  public Employee() {}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getSalary() {
	return salary;
}

public void setSalary(int salary) {
	this.salary = salary;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}
  
}
