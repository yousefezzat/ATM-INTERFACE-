package com.global.hr.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.model.Transaction;
import com.global.hr.model.TransactionType;
import com.global.hr.model.User;
import com.global.hr.repository.TransactionRepo;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepo transactionRepo;
	

	public Transaction viewTransaction(Long id) {
		return transactionRepo.findById(id).orElse(null);
	}

	public Transaction withdraw(User user, BigDecimal amount) {
		// Ensure the user has sufficient balance
		if (user.getAccountBalance().compareTo(amount) >= 0) {
			Transaction transaction = new Transaction(user, amount, TransactionType.WITHDRAWAL, LocalDateTime.now());
			user.setAccountBalance(user.getAccountBalance().subtract(amount));
			return transactionRepo.save(transaction);
		}
		return null; // Handle insufficient balance.. later
	}

	public Transaction deposit(User user, BigDecimal amount) {
		Transaction transaction = new Transaction(user, amount, TransactionType.DEPOSIT, LocalDateTime.now());
		user.setAccountBalance(user.getAccountBalance().add(amount));
		return transactionRepo.save(transaction);
	}

	public Transaction transfer(User sender, User receiver, BigDecimal amount) {
		// Ensure the sender has sufficient balance
		if (sender.getAccountBalance().compareTo(amount) >= 0) {
			Transaction transaction = new Transaction(sender, amount, TransactionType.TRANSFER, LocalDateTime.now());
			sender.setAccountBalance(sender.getAccountBalance().subtract(amount));
			receiver.setAccountBalance(receiver.getAccountBalance().add(amount));
			return transactionRepo.save(transaction);
		}
		return null; // Handle insufficient balance
	}

	public List<Transaction> getTransactionsByUserId(Long userId) {

		return transactionRepo.getTransactionsByUserId(userId);

	}

}