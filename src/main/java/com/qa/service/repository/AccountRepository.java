package com.qa.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qa.persistence.domain.Account;

public class AccountRepository {

	//injection points
	@PersistenceContext(unitName = "accountsPU")
    private EntityManager entityManager;
	
	//business methods
	public Account createAccount(Account newAccount) {
        entityManager.persist(newAccount);
        return newAccount;
    }
}