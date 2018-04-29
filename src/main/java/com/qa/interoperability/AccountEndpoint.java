package com.qa.interoperability;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.qa.persistence.domain.Account;
import com.qa.service.repository.AccountRepository;

@Path("/accounts")
public class AccountEndpoint {

	//injection points
	@Inject
	private AccountRepository accountRepository;
	
	//business methods
	@POST
	 @Consumes(APPLICATION_JSON)
	public Response createAnAccount(Account newAccount, @Context UriInfo uriInfo) {
		newAccount = accountRepository.createAnAccount(newAccount);
		URI createdURI = uriInfo.getBaseUriBuilder().path(newAccount.getId().toString()).build();
		return Response.created(createdURI).build();
	}
}
