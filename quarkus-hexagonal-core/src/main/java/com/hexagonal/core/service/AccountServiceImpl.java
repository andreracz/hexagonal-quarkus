package com.hexagonal.core.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.hexagonal.core.domain.Account;
import com.hexagonal.core.ports.AccountRepository;
import com.hexagonal.core.ports.AccountService;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository repository) {
        this.accountRepository = repository;
    }

    @Override
    public void deposit(String accountId, BigDecimal value, String description) {
        Account account = this.accountRepository.getOne(accountId);
        account.deposit(value, description);
        this.accountRepository.save(account);
    }

    @Override
    public void withdraw(String accountId, BigDecimal value, String description) {
        Account account = this.accountRepository.getOne(accountId);
        account.withdraw(value, description);
        this.accountRepository.save(account);
    }

    @Override
    public void transfer(String originAccountId, String destinationAccountId, BigDecimal value, String description) {
        Account originAccount = this.accountRepository.getOne(originAccountId);
        Account destinationAccount = this.accountRepository.getOne(destinationAccountId);
        originAccount.transferTo(destinationAccount, value, description);
        this.accountRepository.save(originAccount);
        this.accountRepository.save(destinationAccount);
    }

    @Override
    public List<Account> listAllAccounts() {
        return this.accountRepository.listAccounts();
    }

    @Override
    public Account getOneAccount(String accountId) {
        return this.accountRepository.getOne(accountId);
    }

    @Override
    public Account createAccount(String clientId) {
        Account newAccount = new Account(clientId, UUID.randomUUID().toString(), Collections.emptyList());
        this.accountRepository.save(newAccount);
        return newAccount;
    }
    
}
