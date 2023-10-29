package com.global.hr.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	private BigDecimal amount;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING) // Store enum values as strings
	private TransactionType transactionType;

	@Column(nullable = false)
	private LocalDateTime timestamp;

	public Transaction() {

	}

	public Transaction(Long transactionId, User user, BigDecimal amount, TransactionType transactionType,
			LocalDateTime timestamp) {
		super();
		this.transactionId = transactionId;
		this.user = user;
		this.amount = amount;
		this.transactionType = transactionType;
		this.timestamp = timestamp;
	}

	public Transaction(User user, BigDecimal amount, TransactionType transactionType, LocalDateTime timestamp) {
		super();
		this.user = user;
		this.amount = amount;
		this.transactionType = transactionType;
		this.timestamp = timestamp;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
