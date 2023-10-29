package com.global.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.hr.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByAccountNumber(Integer accountNumber);

}