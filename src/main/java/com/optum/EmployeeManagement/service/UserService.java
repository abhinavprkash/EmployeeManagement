package com.optum.EmployeeManagement.service;


import com.optum.EmployeeManagement.model.Department;
import com.optum.EmployeeManagement.model.Employee;

import java.util.List;

public interface UserService {
    List<Employee> getAllEmployees();
    List<Department> getAllDepartment();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(Long id);


}