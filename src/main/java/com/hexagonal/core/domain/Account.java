package com.hexagonal.core.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        Transaction trans = new Transaction(UUID.randomUUID().toString(), value, description, TransactionType.Credit, new Date());
        this.transactionList.add(trans);
        this.balance = this.balance.add(value);
    }

    public void withdraw(BigDecimal value, String description) {
        if (value.compareTo(this.balance) >0) {
            throw new IllegalArgumentException("Withdraw is greater than account balance");
        }
        Transaction trans = new Transaction(UUID.randomUUID().toString(), value, description, TransactionType.Debit, new Date());
        this.transactionList.add(trans);
        this.balance = this.balance.subtract(value);
    }

    
    public void transferTo(Account toDeposit, BigDecimal value, String description) {
        this.withdraw(value, description);
        toDeposit.deposit(value, description);
    }

}
