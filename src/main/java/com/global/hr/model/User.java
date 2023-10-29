package com.global.hr.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false, unique = true)
	private String userPin;

	@Column(name = "name" ,nullable = false)
	private String fullName;

	@Column(nullable = false, unique = true)
	private Integer accountNumber;

	@Column(nullable = false)
	private BigDecimal accountBalance;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	private List<Transaction> transactionsHistory;

	public User() {
		super();
	}
	



	public User(String fullName,String userPin, Integer accountNumber, BigDecimal accountBalance,
			List<Transaction> transactionsHistory) {
		super();
		this.userPin = userPin;
		this.fullName = fullName;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.transactionsHistory = transactionsHistory;
	}

	public User(String userPin, String fullName) {
		this.userPin = userPin;
		this.fullName = fullName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserPin() {
		return userPin;
	}

	public void setUserPin(String userPin) {
		this.userPin = userPin;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Transaction> getTransactions() {
		return transactionsHistory;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactionsHistory = transactions;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

}