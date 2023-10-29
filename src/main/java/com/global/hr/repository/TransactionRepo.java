package com.global.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.global.hr.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	@Query("SELECT t FROM Transaction t WHERE t.user.userId = :userId")
	public List<Transaction> getTransactionsByUserId(@Param("userId") Long userId);

}