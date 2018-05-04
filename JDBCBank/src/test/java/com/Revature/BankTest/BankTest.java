package com.Revature.BankTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.Revature.Bank.Login;

public class BankTest {

	@Test
	public void testThatValidUserIsValidatedWhenTheyTryToLogin() {
		
		Login login = new Login("ADMIN", "pass");
		assertEquals(true, login.isLoggedIn());
	}
	
	@Test
	public void testWhetherUserIsSuperUserOrNot() {
		
		Login login = new Login("ADMIN", "pass");
		login.isLoggedIn();
		assertEquals(true, login.superUser());
	}
	

}
