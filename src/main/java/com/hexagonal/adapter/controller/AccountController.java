package com.hexagonal.adapter.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexagonal.domain.model.Account;

@Path("/account")
public class AccountController {
   
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccounts() {
        return null;
    }

}
