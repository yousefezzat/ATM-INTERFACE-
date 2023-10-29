package com.global.hr.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.model.Transaction;
import com.global.hr.model.User;
import com.global.hr.service.TransactionService;
import com.global.hr.service.UserService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private UserService userService;

	@GetMapping("/details/{transactionId}")
	public ResponseEntity<Transaction> getTransactionDetails(@PathVariable Long transactionId) {
		Transaction transaction = transactionService.viewTransaction(transactionId);
		if (transaction != null) {
			return ResponseEntity.ok(transaction);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/withdraw")
	public ResponseEntity<String> withdraw(@RequestParam Long userId, @RequestParam BigDecimal amount) {
		User user = userService.getUserDetails(userId); // Retrieve the user
		if (user != null) {
			Transaction transaction = transactionService.withdraw(user, amount);
			if (transaction != null) {
				return ResponseEntity.ok("Withdrawal successful");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Withdrawal failed");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	@PostMapping("/deposit")
	public ResponseEntity<String> deposit(@RequestParam Long userId, @RequestParam BigDecimal amount) {
		User user = userService.getUserDetails(userId); // Retrieve the user
		if (user != null) {
			Transaction transaction = transactionService.deposit(user, amount);
			if (transaction != null) {
				return ResponseEntity.ok("Deposit successful");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deposit failed");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestParam Long senderId, @RequestParam Long receiverId,
			@RequestParam BigDecimal amount) {
		if (senderId != receiverId) {
			User sender = userService.getUserDetails(senderId);
			User receiver = userService.getUserDetails(receiverId);

			if (sender != null && receiver != null) {
				Transaction transaction = transactionService.transfer(sender, receiver, amount);
				if (transaction != null) {
					return ResponseEntity.ok("Transfer successful");
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transfer failed");
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}
		} else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There's no transfer between the same account");
	}

	@GetMapping("/quit")
	public ResponseEntity<String> logout() {
		// Implement logout logic here, such as invalidating the session.
		return ResponseEntity.ok("Quit successful");
	}
}