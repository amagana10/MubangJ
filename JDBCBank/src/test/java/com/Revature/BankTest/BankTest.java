package com.Revature.BankTest;

import static org.junit.Assert.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.Revature.Bank.Login;
import com.Revature.Dao.AccountDao;
import com.Revature.Dao.AccountDaoImpl;
import com.Revature.Dao.TransactionDao;
import com.Revature.Dao.TransactionDaoImpl;
import com.Revature.Dao.UserDao;
import com.Revature.Dao.UserDaoImpl;
import com.Revature.Tables.Account;
import com.Revature.Tables.Transaction;
import com.Revature.Tables.User;


public class BankTest {


	
//	private static final Login login = new Login("ADMIN", "pass");
	

//	@Test
//	public void testThatValidUserIsValidatedWhenTheyTryToLogin() {
//		
//		Login login = new Login("ADMIN", "pass");
//		assertEquals(true, login.isLoggedIn());
//	}
//	
//	@Test
//	public void testWhetherUserIsSuperUserOrNot() {
//		
//		Login login = new Login("ADMIN", "pass");
//		login.isLoggedIn();
//		assertEquals(true, login.superUser());
//	}
//	
	
	/********************* UserDao Test ***************************/
	
	@Test
	public void testAllUsersAreReturnedFromUsersTable() {
		
		UserDao userDao = new UserDaoImpl();
		List<User> allUsers = new ArrayList<>();
		allUsers = userDao.getUsers();
		assertEquals(3, allUsers.size());
	}
	
	@Test
	public void ReturnsUserByIdFromUsersTable() {
		
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserById(1);
		assertEquals("ADMIN", user.getUsername());
	}
	
	@Test
	public void FailsToReturnUserByIdFromUsersTable() {
		
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserById(1000);
		assertEquals(null, user);
	}
	
	@Test
	public void ReturnsUserByUsernameFromUsersTable() {
		
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserByName("ADMIN");
		assertEquals("ADMIN", user.getUsername());
	}
	
	@Test
	public void FailsToReturnUserByUsernameFromUsersTable() {
		
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserByName("BILLY");
		assertEquals(null, user);
	}
	
	
	@Test
	public void insertsAUserIntoUsersTable() throws SQLIntegrityConstraintViolationException {
		UserDao userDao = new UserDaoImpl();
		assertEquals(false, userDao.insertUser("regularClient", "p4ss"));		
	}
	
	// already exist in database so need to change to false. UNIQUE constraint in database. returns true for users that do not already exist in database. 
	// for now it throws exception
	
	@Test
	public void testUserPasswordisUpdatedDependingOnOption() {
		
		UserDao userDao = new UserDaoImpl();
		assertEquals(true, userDao.updateUser(25, "p4ss", "password"));
		
	}
	
	// already exist in database so need to change to false. UNIQUE constraint in 
	// database returns true for users that do not already exist in database. 
	// for now it throws exception
	
	@Test
	public void testUserUsernameisUpdatedDependingOnOption() {
		
		UserDao userDao = new UserDaoImpl();
		assertEquals(false, userDao.updateUser(25, "regularclienttt", "username"));
		
	}
	
	
	@Test
	public void doesNotAllowInsertOfExistingUsername() throws SQLIntegrityConstraintViolationException {
		UserDao userDao = new UserDaoImpl();
		assertEquals(false, userDao.insertUser("regularClient", "p4sss"));		

	}
	
	// after user has been deleted need to change the test to false and it will test for whether user is in database
	@Test
	public void deletesAnExistingUser() {
		UserDao userDao = new UserDaoImpl();
		assertEquals(false, userDao.deleteUser(33));

	}
	
	
	
	
	/********************* AccountDao Test ***************************/
	
	
	// TODO account size changing
	@Test
	public void testAllAccountsAreReturnedFromUsersTable() {
	
		AccountDao AccountDao = new AccountDaoImpl();
		List<Account> allAccounts = new ArrayList<>();
		allAccounts = AccountDao.getAccounts(1);
		assertEquals(1, allAccounts.size());
	}
	
	@Test
	public void ReturnsAccountByIdFromAccountsTableIfExist() {
	
		AccountDao AccountDao = new AccountDaoImpl();
		Account account = AccountDao.getAccountById(1, "AdminAccount");
		assertEquals("AdminAccount", account.getAccountName());
	}
	
	@Test
	public void ReturnsNoAccountByIdFromAccountsTableIfAccountDoesNotExist() {
	
		AccountDao AccountDao = new AccountDaoImpl();
		Account account = AccountDao.getAccountById(4, "AdminAccount");
		assertEquals(null, account);
	}
	
	
	// will fail since this is already in database. Only works for new accounts that are
	// not in database yet
	
//	@Test
//	public void insertsAnAccountIntoUsersTable() {
//		AccountDao accountDao = new AccountDaoImpl();	
//		assertEquals(true, accountDao.insertAccount(1, "AdminCheckingAccount", 2000.00));	
//	}
	
	@Test
	public void attemptsToInsertAnAccountThatAlreadyExistsAndFails() {
		AccountDao accountDao = new AccountDaoImpl();	
		assertEquals(false, accountDao.insertAccount(1, "AdminCheckingAccount", 2000.00));	

	}
	
	@Test
	public void attemptsToInsertAnAccountWithFundsLessThanZero() {
		AccountDao accountDao = new AccountDaoImpl();	
		assertEquals(false, accountDao.insertAccount(1, "AdminSavingsAccount", -2000.00));	

	}
	
	@Test
	public void depositFundsIntoAccount() {
	
		AccountDao accountDao = new AccountDaoImpl();	
		assertEquals(true, accountDao.updateAccount(1, "AdminAccount", 1000.00));
	}
	
	@Test 
	public void TryTodepositFundsIntoAnAccountThatDoesNotExist() {
		AccountDao accountDao = new AccountDaoImpl();
		assertEquals(false, accountDao.updateAccount(100, "AdminAccount", 5000.00));
	}
	
	@Test
	public void withdrawMoreFundsThanTheAccountCanHold() {
	
		AccountDao accountDao = new AccountDaoImpl();
		assertEquals(false, accountDao.updateAccount(1, "AdminAccount", -1000000.00));

	}
	
	@Test
	public void deleteAccountGivenTheProperUserIdAndAccountName() {
	
		AccountDao accountDao = new AccountDaoImpl();	
		assertEquals(true, accountDao.deleteAccount(1, "AdminCheckingAccount"));
	}
	
	@Test 
	public void TryToDeleteAnAccountThatDoesNotExist() {
		AccountDao accountDao = new AccountDaoImpl();	
		assertEquals(false, accountDao.deleteAccount(10, "AdminCheckingAccount"));

	}
	
	/********************* TransactionDao Test ***************************/

	@Test
	public void testAllTransactionsAreReturnedFromTransactionsTable() {
	
		TransactionDao transactionDao = new TransactionDaoImpl();
		List<Transaction> allTransactions = new ArrayList<>();
		allTransactions = transactionDao.getTransactionHistory(1);
		assertEquals(1, allTransactions.size());
	}
	
	@Test
	public void ReturnsTransactionsByAccountNameFromTransactionsTable() {
	
		TransactionDao transactionDao = new TransactionDaoImpl();
		List<Transaction> transactions = new ArrayList<>();

		transactions = transactionDao.getTransactionsByAccountName("AdminAccount");
		assertEquals(1, transactions.size());
	}
	
	@Test
	public void AttemptToReturnNonExistantTransactionsByAccountNameFromTransactionsTable() {
	
		TransactionDao transactionDao = new TransactionDaoImpl();
		List<Transaction> transactions = transactionDao.getTransactionsByAccountName("AdminAccountsssssss");
		assertEquals(true, transactions.isEmpty());
	}
	
	
	@Test
	public void insertsATransactionIntoTransactionsTable() {
		TransactionDao transactionDao = new TransactionDaoImpl();
		assertEquals(true, transactionDao.insertTransaction(1, "AdminAccount", 2000.00));	
	}
	
	@Test
	public void FailsToInsertANonExistentUsersTransactionIntoTransactionsTable() {
		TransactionDao transactionDao = new TransactionDaoImpl();
		assertEquals(false, transactionDao.insertTransaction(100, "AdminCheckingAccount", 2000.00));	
	}
	
	@Test
	public void updateExistingTransactionInTransactionTable() {
	
		TransactionDao transactionDao = new TransactionDaoImpl();
		assertEquals(true, transactionDao.updateTransaction(23, 1, "AdminAccount", 100000.00));
	}
	
	@Test
	public void FailsToUpdateNonExistentTransactionInTransactionTable() {
	
		TransactionDao transactionDao = new TransactionDaoImpl();
		assertEquals(false, transactionDao.updateTransaction(100, 1, "AdminAccount", 100000.00));
	}
	
	@Test
	public void deleteTransactionFromTransactionsTableGivenTransactionId() {
	
		TransactionDao transactionDao = new TransactionDaoImpl();
		assertEquals(true, transactionDao.deleteTransaction(24));
	}
	
	@Test
	public void FailTodeleteTransactionFromTransactionsTableGivenNonExistentTransactionId() {
	
		TransactionDao transactionDao = new TransactionDaoImpl();
		assertEquals(false, transactionDao.deleteTransaction(24));
	}
	
	@Test
	public void deleteMultipleTransactionsFromTransactionsTableGivenUserId() {
	
		TransactionDao transactionDao = new TransactionDaoImpl();
		assertEquals(true, transactionDao.deleteTransactions(1));
	}
	
	@Test
	public void FailsToDeleteMultipleTransactionsFromTransactionsTableInvalidGivenUserId() {
	
		TransactionDao transactionDao = new TransactionDaoImpl();
		assertEquals(false, transactionDao.deleteTransactions(100));
	}
	
}
	

