package com.hexagonal.core.ports;

import java.util.List;

import com.hexagonal.core.domain.Account;

public interface AccountRepository {
    public List<Account> listAccounts();
    public Account getOne(String accountId);
    public void save(Account account);
}
