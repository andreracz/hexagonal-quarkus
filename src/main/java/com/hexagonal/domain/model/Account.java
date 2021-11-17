package com.hexagonal.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    
    private BigDecimal balance;
    private String clientId;
    private String accountId;
    private List<Transaction> transactionList;

    

    public Account(String clientId, String accountId, List<Transaction> transactionList) {
        this.clientId = clientId;
        this.accountId = accountId;
        this.transactionList = new ArrayList<Transaction>(transactionList);
        BigDecimal currentBalance = new BigDecimal(0);
        for (Transaction transaction : transactionList) {
            if(transaction.getTransactionType() == TransactionType.Credit) {
                currentBalance = currentBalance.add(transaction.getValue());
            } else if(transaction.getTransactionType() == TransactionType.Debit) {
                currentBalance = currentBalance.subtract(transaction.getValue());
            } else {
                throw new IllegalArgumentException("Transaction type invalid");
            }
        }
        if (currentBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Balance should be greater than zero");
        }
        this.balance = currentBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionList() {
        return Collections.unmodifiableList(transactionList);
    }


    public String getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public void deposit(BigDecimal value, String description) {
        Transaction trans = new Transaction(new String("aaa"), value, description, TransactionType.Credit);
        this.transactionList.add(trans);
        this.balance = this.balance.add(value);
    }

}
