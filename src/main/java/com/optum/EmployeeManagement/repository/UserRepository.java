package com.optum.EmployeeManagement.repository;

import com.optum.EmployeeManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}