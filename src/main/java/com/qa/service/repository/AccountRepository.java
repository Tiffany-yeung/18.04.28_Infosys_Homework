package com.qa.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.util.TextUtil;

@Transactional(SUPPORTS)
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

	public Account getAnAccount(@NotNull Long id) {
		return em.find(Account.class, id);
	}

	public List<Account> getAllAccounts() {
		TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a ORDER BY a.id", Account.class);
		return query.getResultList();
	}
	
	@Transactional(REQUIRED)
	public void deleteAnAccount(@NotNull Long id) {
		em.remove(em.getReference(Account.class, id));
	}
	
}