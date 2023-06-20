package com.optum.EmployeeManagement.repository;

import com.optum.EmployeeManagement.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials, Long> {

    UserCredentials findByUsername(String username);
}
