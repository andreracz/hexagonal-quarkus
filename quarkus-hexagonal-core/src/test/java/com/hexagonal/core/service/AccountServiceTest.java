package com.hexagonal.core.service;

import com.hexagonal.core.domain.Account;
import com.hexagonal.core.domain.Transaction;
import com.hexagonal.core.domain.TransactionType;
import com.hexagonal.core.ports.AccountRepository;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@QuarkusTest
public class AccountServiceTest {

    @InjectMocks @Inject
    AccountServiceImpl service;

    AccountRepository repositoryMock;

    @BeforeEach
    void setup() {
        repositoryMock = Mockito.mock(AccountRepositoryMock.class);
        QuarkusMock.installMockForType(repositoryMock, AccountRepository.class);

        Transaction transaction = new Transaction("1", BigDecimal.TEN, "", TransactionType.Credit, new Date());
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Account account1 = new Account("1", "1", transactions);
        Account account2 = new Account("2", "1", transactions);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        when(repositoryMock.getOne("1")).thenReturn(account1);
        when(repositoryMock.getOne("2")).thenReturn(account2);
        when(repositoryMock.listAccounts()).thenReturn(accounts);
    }

    @Test
    public void testDeposit() {
        service.deposit("1", BigDecimal.ONE, "");

        verify(repositoryMock, times(1)).getOne(any());
        verify(repositoryMock, times(1)).save(any());
    }

    @Test
    public void testWithdraw() {
        service.withdraw("1", BigDecimal.ONE, "");
        verify(repositoryMock, times(1)).save(any());
    }

    @Test
    public void testListAllAccounts() {
        service.listAllAccounts();
        verify(repositoryMock, times(1)).listAccounts();
    }

    @Test
    public void testCreateAccount() {
        service.createAccount("1");
        verify(repositoryMock, times(1)).save(any());
    }

    @Test
    public void testTransfer() {
        service.transfer("1", "2", BigDecimal.ONE, "");
        verify(repositoryMock, times(2)).save(any());
    }

    @Test
    public void testGetOneAccount() {
        service.getOneAccount("1");
        verify(repositoryMock, times(1)).getOne(any());
    }
}

@Mock
@ApplicationScoped
class AccountRepositoryMock implements AccountRepository {

    @Override
    public List<Account> listAccounts() {
        return Collections.emptyList();
    }

    @Override
    public Account getOne(String accountId) {
        return null;
    }

    @Override
    public void save(Account account) {
    }
}