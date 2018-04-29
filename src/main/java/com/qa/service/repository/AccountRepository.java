package com.qa.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.util.TextUtil;

public class AccountRepository {

	// injection points
	@PersistenceContext(unitName = "accountsPU")
	private EntityManager em;

	@Inject
	private TextUtil textUtil;
	
	// business methods
	
	@Transactional(REQUIRED)
	public Account createAnAccount(@NotNull Account newAccount) {
		newAccount.setEmail(textUtil.sanitize(newAccount.getEmail()));
		newAccount.setPhoneNumber(textUtil.sanitize(newAccount.getPhoneNumber()));
		em.persist(newAccount);
		return newAccount;
	}
	
}