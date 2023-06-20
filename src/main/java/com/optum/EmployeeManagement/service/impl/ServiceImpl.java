package com.optum.EmployeeManagement.service.impl;

import com.optum.EmployeeManagement.model.Department;
import com.optum.EmployeeManagement.model.Employee;
import com.optum.EmployeeManagement.repository.EmployeeRepository;
import com.optum.EmployeeManagement.repository.UserRepository;
import com.optum.EmployeeManagement.repository.DepartmentRepository;
import com.optum.EmployeeManagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.optum.EmployeeManagement.model.UserCredentials;
import com.optum.EmployeeManagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;
import java.util.List;

@Service
public class ServiceImpl implements UserService, UserDetailsService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private UserRepository userRepository;

    @Autowired
    public ServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
