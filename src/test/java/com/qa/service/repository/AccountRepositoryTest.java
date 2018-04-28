package com.qa.service.repository;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.qa.persistence.domain.Account;

@RunWith(Arquillian.class)
public class AccountRepositoryTest {

	// attribute
	private static Long accountId;

	public static Long getAccountId() {
		return accountId;
	}

	public static void setAccountId(Long accountId) {
		AccountRepositoryTest.accountId = accountId;
	}

	// injection
	@Inject
	private AccountRepository accountRepository;

	// deployment
	@Deployment
	public static Archive<?> createDeploymentPackage() {

		return ShrinkWrap.create(JavaArchive.class).addClass(Account.class).addClass(AccountRepository.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
	}

	// Tests
	@Test
	@InSequence(1)
	public void shouldBeDeployed() {
		assertNotNull(accountRepository);
	}

	@Test
	@InSequence(2)
	public void shouldCreateAnAccount() {
		// Creates a book
		Account account = new Account("Tiffany", "Yeung", 23, "Tiffany.yeung@qa.com", "07412895568");
		account = accountRepository.createAnAccount(account);
		// Checks the created book
		assertNotNull(account);
		assertNotNull(account.getId());
		setAccountId(account.getId());
	}

}
