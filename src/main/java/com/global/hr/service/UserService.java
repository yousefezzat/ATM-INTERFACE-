package com.global.hr.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global.hr.model.Transaction;
import com.global.hr.model.User;
import com.global.hr.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TransactionService transactionService;
	private final AtomicInteger accountNumberGenerator = new AtomicInteger(1000); // Start account number

	public User signUp( String fullName,String userPin, BigDecimal initialBalance) {
		Integer accNum = generateUniqueAccountNumber();

		User newUser = new User(fullName, userPin, accNum, initialBalance, new ArrayList<Transaction>());
		return userRepo.save(newUser);

	}

	private Integer generateUniqueAccountNumber() {
		while (true) {
			int accountNumber = accountNumberGenerator.getAndIncrement();
			if (isAccountNumberUnique(accountNumber)) {
				return accountNumber;
			}
		}
	}

	private boolean isAccountNumberUnique(int accountNumber) {
		return userRepo.findByAccountNumber(accountNumber) == null;
	}

	public User getUserDetails(Long id) {
		Optional<User> user = userRepo.findById(id);

		return user.orElse(null);
	}

	public User updateUser(Long id, String fullName, String userPin) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			User verifiedUser = user.get();
			verifiedUser.setFullName(fullName);
			verifiedUser.setUserPin(userPin);
			return userRepo.save(verifiedUser);
		}
		return null;
	}

	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

	public User login(Long id, String userPin) {

		Optional<User> user = userRepo.findById(id);
		if (user.get().getUserPin().equals(userPin)) {
			return user.get();
		} else
			return null;
	}

	public BigDecimal checkAccountBalance(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			return user.get().getAccountBalance();
		} else
			return null;

	}

	public User changeUserPin(Long id, String newUserPin) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			User verifiedUser = user.get();
			verifiedUser.setUserPin(newUserPin);
			return userRepo.save(verifiedUser);
		}
		return null;
	}

	public List<Transaction> getUserTransactionHistory(Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return transactionService.getTransactionsByUserId(id);
		}
		return new ArrayList<>();
	}
}