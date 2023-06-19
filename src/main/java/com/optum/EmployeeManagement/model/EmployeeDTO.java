package com.optum.EmployeeManagement.model;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String department;

    // Constructors, getters, and setters
    // ...
    
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

