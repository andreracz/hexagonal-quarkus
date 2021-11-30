package com.hexagonal.core.ports;

import com.hexagonal.core.domain.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> listAccounts();
    Account getOne(String accountId);
    void save(Account account);
}
