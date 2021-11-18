package com.hexagonal.core.domain;

import java.math.BigDecimal;

public class Transaction {
    private String transactionId;
    private BigDecimal value;
    private String description;
    private TransactionType transactionType;

    
    public Transaction(String transactionId, BigDecimal value, String description, TransactionType transactionType) {
        this.transactionId = transactionId;
        this.value = value;
        this.description = description;
        this.transactionType = transactionType;
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
