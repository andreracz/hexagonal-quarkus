package com.hexagonal.adapter.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.hexagonal.adapter.repository.model.AccountModel;
import com.hexagonal.adapter.repository.model.TransactionModel;
import com.hexagonal.core.domain.Account;
import com.hexagonal.core.domain.Transaction;
import com.hexagonal.core.ports.AccountRepository;

@ApplicationScoped
public class AccountRepositoryJpaImpl implements AccountRepository {

    @Inject
    EntityManager repository; 

    @Override
    @Transactional
    public List<Account> listAccounts() {
        return repository.createQuery("from AccountModel", AccountModel.class).getResultList().stream().map(AccountRepositoryJpaImpl::toAccount).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Account getOne(String accountId) {
        return toAccount(repository.find(AccountModel.class, accountId));
    }

    @Override
    @Transactional
    public void save(Account account) {
        AccountModel model = repository.find(AccountModel.class, account.getAccountId());
        if (model == null) {
            model = new AccountModel();
            model.setAccountId(account.getAccountId());
        }
        model.setBalance(account.getBalance());
        model.setClientId(account.getClientId());
        List<TransactionModel> transactions = mapTransactionsToModel(account.getTransactionList(), model);
        model.setTransactionList(transactions);
        repository.persist(model);
        model.getTransactionList().forEach(transaction -> repository.merge(transaction));
    }

    private static List<TransactionModel> mapTransactionsToModel(List<Transaction> transactions, final AccountModel account) {
        return transactions.stream().map(transaction -> 
            new TransactionModel(transaction.getTransactionId(), 
                             transaction.getValue(),
                             transaction.getDescription(),
                             transaction.getTransactionType(),
                             transaction.getTransactionDate(),
                             account)).collect(Collectors.toList());
    }

    public static Account toAccount(AccountModel model) {
        return new Account(model.getClientId(), 
                            model.getAccountId(), 
                            model.getTransactionList()
                                    .stream()
                                    .map(AccountRepositoryJpaImpl::toTransaction)
                                    .collect(Collectors.toList()));
    }

    public static Transaction toTransaction(TransactionModel model) {
        return new Transaction(model.getTransactionId(), model.getValue(), model.getDescription(), model.getTransactionType(), model.getTransactionDate());
    }

   
    
}
