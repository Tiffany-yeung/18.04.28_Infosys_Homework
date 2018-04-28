package com.qa.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;

import com.qa.persistence.domain.Account;

public class AccountRepository {

	// injection points
	@PersistenceContext(unitName = "accountsPU")
	private EntityManager entityManager;

	// business methods
	@Transactional(REQUIRED)
	public Account createAccount(Account newAccount) {
		entityManager.persist(newAccount);
		return newAccount;
	}
}