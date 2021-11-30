package com.hexagonal.adapter.repository.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class AccountModel {
    
    private BigDecimal balance;
    private String clientId;
    
    @Id
    private String accountId;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    @OrderBy("transactionDate")
    private List<TransactionModel> transactionList;

    public BigDecimal getBalance() {
        return balance;
    }
    public List<TransactionModel> getTransactionList() {
        return transactionList;
    }
    public void setTransactionList(List<TransactionModel> transactionList) {
        this.transactionList = transactionList;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
