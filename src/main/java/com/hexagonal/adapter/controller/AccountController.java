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
   
    private AccountService accountService;
    
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
        return accountService.createAccount(account.getAccountId());
    }

    


}
