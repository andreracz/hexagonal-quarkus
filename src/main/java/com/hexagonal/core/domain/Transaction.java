package com.hexagonal.core.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private String transactionId;
    private BigDecimal value;
    private String description;
    private TransactionType transactionType;
    private Date transactionDate;

    
    public Transaction(String transactionId, BigDecimal value, String description, TransactionType transactionType, Date transactionDate) {
        if (transactionId == null) {
            throw new IllegalArgumentException("TransactionId cannot be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        if (value.equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Value cannot be zero");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (transactionType == null) {
            throw new IllegalArgumentException("TransactionType cannot be null");
        }
        if (transactionDate == null) {
            throw new IllegalArgumentException("TransactionDate cannot be null");
        }
        this.transactionId = transactionId;
        this.value = value;
        this.description = description;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getValue() {
        return value;
    }
}
