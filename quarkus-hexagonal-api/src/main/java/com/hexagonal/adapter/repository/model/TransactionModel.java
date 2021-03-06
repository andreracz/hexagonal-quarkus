package com.hexagonal.adapter.repository.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.hexagonal.core.domain.TransactionType;

@Entity
public class TransactionModel {

    @Id
    private String transactionId;

    private BigDecimal value;
    private String description;
    private TransactionType transactionType;
    private Date transactionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private AccountModel account;


    public TransactionModel() {

    }

    public TransactionModel(String transactionId, BigDecimal value, String description, TransactionType transactionType, Date transactionDate, AccountModel account) {
        this.transactionId = transactionId;
        this.value = value;
        this.description = description;
        this.transactionType = transactionType;
        this.account = account;
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
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
}
