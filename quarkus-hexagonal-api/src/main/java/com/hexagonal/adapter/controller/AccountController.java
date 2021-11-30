package com.hexagonal.adapter.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexagonal.core.domain.Account;
import com.hexagonal.core.ports.AccountService;

@Path("/account")
public class AccountController {
   
    private final AccountService accountService;
    
    public AccountController(AccountService service) {
        this.accountService = service;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccounts() {
        return accountService.listAllAccounts();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("id") String accountId) {
        return accountService.getOneAccount(accountId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account saveAccount(AccountDto account) {
        return accountService.createAccount(account.getClientId());
    }

    @POST
    @Path("{id}/deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deposit(@PathParam("id") String accountId, FinancialTransactionDTO transactionDTO) {
        accountService.deposit(accountId, transactionDTO.getValue(), transactionDTO.getDescription());
    }

    @POST
    @Path("{id}/withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void withdraw(@PathParam("id") String accountId, FinancialTransactionDTO transactionDTO) {
        accountService.withdraw(accountId, transactionDTO.getValue(), transactionDTO.getDescription());
    }

    @POST
    @Path("{origin}/transferTo/{destination}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void transferTo(@PathParam("origin") String originId, @PathParam("destination") String destinationId, FinancialTransactionDTO transactionDTO) {
        accountService.transfer(originId, destinationId, transactionDTO.getValue(), transactionDTO.getDescription());
    }
    


}
