package com.optum.EmployeeManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.optum.EmployeeManagement.model.EmployeeDTO;
import com.optum.EmployeeManagement.exception.ResourceNotFoundException;
import com.optum.EmployeeManagement.model.Department;
import com.optum.EmployeeManagement.model.Employee;
import com.optum.EmployeeManagement.repository.DepartmentRepository;
import com.optum.EmployeeManagement.repository.EmployeeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // get all employees
    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        EmployeeDTO employeeDTO = convertToDTO(employee);
        return ResponseEntity.ok(employeeDTO);
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setDepartment(employeeDetails.getDepartment());

        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeDTO updatedEmployeeDTO = convertToDTO(updatedEmployee);
        return ResponseEntity.ok(updatedEmployeeDTO);
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Fetch departments
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
    // Helper method to convert Employee entity to EmployeeDTO
   private EmployeeDTO convertToDTO(Employee employee) {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    employeeDTO.setId(employee.getId());
    employeeDTO.setFirstName(employee.getFirstName());
    employeeDTO.setLastName(employee.getLastName());
    employeeDTO.setEmailId(employee.getEmailId());
    
    Department department = employee.getDepartment();
    if (department != null) {
        employeeDTO.setDepartment(department.getName());
    }
    
    return employeeDTO;
}


}
