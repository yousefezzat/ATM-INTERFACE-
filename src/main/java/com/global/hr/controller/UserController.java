package com.global.hr.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.model.Transaction;
import com.global.hr.model.User;
import com.global.hr.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestParam String fullName, @RequestParam String userPin,
			@RequestParam BigDecimal initialBalance) {
		User newUser = userService.signUp(fullName, userPin, initialBalance);

		if (newUser != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam Long userId, @RequestParam String userPin) {
		User user = userService.login(userId, userPin);
		if (user != null) {
			return ResponseEntity.ok("Login successful");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
		}
	}

	@GetMapping("/details/{userId}")
	public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
		User user = userService.getUserDetails(userId);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
		User user = userService.updateUser(userId, updatedUser.getFullName(), updatedUser.getUserPin());
		if (user != null) {
			return ResponseEntity.ok("User updated successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok("User deleted successfully");
	}

	@GetMapping("/history/{userId}")
	public ResponseEntity<List<Transaction>> getUserTransactionHistory(@PathVariable Long userId) {
		List<Transaction> history = userService.getUserTransactionHistory(userId);
		if (history != null) {
			return ResponseEntity.ok(history);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout() {
		// Implement logout logic here (later)
		return ResponseEntity.ok("Logout successful");
	}

}