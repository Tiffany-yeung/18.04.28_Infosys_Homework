package com.qa.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import static javax.transaction.Transactional.TxType.REQUIRED;

import com.qa.persistence.domain.Account;

public class AccountRepository {

	// injection points
	@PersistenceContext(unitName = "accountsPU")
	private EntityManager em;

	// business methods
	
	@Transactional(REQUIRED)
	public Account createAnAccount(@NotNull Account newAccount) {
		em.persist(newAccount);
		return newAccount;
	}
	
}