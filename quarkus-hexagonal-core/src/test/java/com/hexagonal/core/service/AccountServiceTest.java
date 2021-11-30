package com.hexagonal.core.service;

import com.hexagonal.core.domain.Account;
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
import java.util.Collections;
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
    }

    @Test
    public void testDeposit() {
        Account account = new Account("1", "1", Collections.emptyList());
        when(repositoryMock.getOne("1")).thenReturn(account);

        service.deposit("1", BigDecimal.ONE, "");
        verify(repositoryMock, times(1)).getOne("1");
        verify(repositoryMock, times(1)).save(account);
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
        return new Account("1", "1", Collections.emptyList());
    }

    @Override
    public void save(Account account) {
    }
}