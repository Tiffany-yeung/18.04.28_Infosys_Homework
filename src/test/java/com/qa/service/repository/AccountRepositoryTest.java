package com.qa.service.repository;

import static org.junit.Assert.assertEquals;
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
import com.qa.util.TextUtil;

@RunWith(Arquillian.class)
public class AccountRepositoryTest {

	// attribute
	private static Long accountId;

	// injection
	@Inject
	private AccountRepository accountRepository;

	// deployment
	@Deployment
	public static Archive<?> createDeploymentPackage() {

		return ShrinkWrap.create(JavaArchive.class)
				.addClass(Account.class)
				.addClass(AccountRepository.class)
				.addClass(TextUtil.class)
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
	public void createAnAccountTest() {
		// Creates a book
		Account newAccount = new Account("Tiffany", "Yeung", 23, "Tiffany.yeung@qa.com", "07412875548");
		newAccount = accountRepository.createAnAccount(newAccount);
		// Checks the created book
		assertNotNull(newAccount);
		assertNotNull(newAccount.getId());
		accountId = newAccount.getId();
	}
	
	@Test(expected = Exception.class)
	@InSequence(3)
	public void createAccountWithNullValueTest() {
		Account newAccount2 = new Account("Tiffany", null, 23, "Tiffany.yeung@qa.com", "07412875548");
		accountRepository.createAnAccount(newAccount2);
	}
	
	@Test
	@InSequence(4)
	public void createAccountWithSpacesTest() {
		// Creates a book
		Account newAccount3 = new Account("Tiffany", "Yeung", 23, "Tif  fany.yeu ng@q   a.com", "0  74 128   75548");
		newAccount3 = accountRepository.createAnAccount(newAccount3);
		// Checks the created book
		assertEquals("Tiffany.yeung@qa.com", newAccount3.getEmail());
		assertEquals("07412875548", newAccount3.getPhoneNumber());
		assertNotNull(newAccount3);
		assertNotNull(newAccount3.getId());
		accountId = newAccount3.getId();
	}

}
