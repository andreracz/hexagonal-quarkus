package com.hexagonal.adapter.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.hexagonal.core.domain.Account;
import com.hexagonal.core.ports.AccountRepository;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {

    private HashMap<String, Account> accounts = new HashMap<>();

    @Override
    public List<Account> listAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public Account getOne(String accountId) {
        return accounts.get(accountId);
    }

    @Override
    public void save(Account account) {
        accounts.put(account.getAccountId(), account);        
    }
    
}
