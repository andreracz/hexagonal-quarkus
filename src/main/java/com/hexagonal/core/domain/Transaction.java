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
        this.transactionId = transactionId;
        this.value = value;
        this.description = description;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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
