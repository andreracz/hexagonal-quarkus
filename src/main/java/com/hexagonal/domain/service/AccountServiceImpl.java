package com.hexagonal.domain.service;

import java.math.BigDecimal;
import java.util.List;

import com.hexagonal.domain.model.Account;
import com.hexagonal.domain.ports.AccountService;

public class AccountServiceImpl implements AccountService {

    @Override
    public void deposit(String accountId, BigDecimal value, String description) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void withdraw(String accountId, BigDecimal value, String description) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void transfer(String originAccountId, String destinationAccountId, BigDecimal value, String description) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Account> listAllAccounts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account getOneAccount(String accountId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account createAccount(String clientId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
