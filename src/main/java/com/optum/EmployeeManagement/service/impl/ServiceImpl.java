package com.optum.EmployeeManagement.service.impl;

import com.optum.EmployeeManagement.model.Department;
import com.optum.EmployeeManagement.model.Employee;
import com.optum.EmployeeManagement.repository.EmployeeRepository;
import com.optum.EmployeeManagement.repository.DepartmentRepository;
import com.optum.EmployeeManagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceImpl implements UserService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    public ServiceImpl(EmployeeRepository employeeRepository,DepartmentRepository departmentRepository) {
        super();
        this.employeeRepository = employeeRepository;
        this.departmentRepository=departmentRepository;
    }

    @Override
    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }
    @Override
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }
    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    @Override
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).get();
    }
    @Override
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    @Override
    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }

}