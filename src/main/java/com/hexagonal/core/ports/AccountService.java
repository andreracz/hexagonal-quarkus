package com.hexagonal.core.ports;

import java.math.BigDecimal;
import java.util.List;

import com.hexagonal.core.domain.Account;

public interface AccountService {
    
    void deposit(String accountId, BigDecimal value, String description);
    void withdraw(String accountId, BigDecimal value, String description);
    void transfer(String originAccountId, String destinationAccountId, BigDecimal value, String description);
    List<Account> listAllAccounts();
    Account getOneAccount(String accountId);
    Account createAccount(String clientId);
    
}
