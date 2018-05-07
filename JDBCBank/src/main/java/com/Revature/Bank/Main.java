package com.Revature.Bank;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

import com.Revature.Exceptions.InvalidLoginException;
import com.Revature.Exceptions.UserDoesNotExistException;

public class Main {
	
	public static void main(String[] args) {
		
		String yesOrNo = null;
		
		String username = null;
		String password = null;
		String reEnterPass = null;
		
		Login login = null;
		RegisterUser register;
		
		SimpleUser simpleUser = null;
		SuperUser superUser = null;	
		
		String option = "";
		
		System.out.println("Welcome to the Bank!\n");
		Scanner sc = new Scanner(System.in);
		
		do {	
			System.out.println("Do you have an account? (yes/y or no/n)");
			yesOrNo = sc.nextLine();

			if(yesOrNo.toLowerCase().equals("yes") || yesOrNo.toLowerCase().equals("y")) {
				
				do {		
					System.out.println("Enter your username: ");
					username = sc.nextLine();
		
					System.out.println("Enter your password: ");
					password = sc.nextLine();
					
					
					login = new Login(username, password);
					
				} while (!login.isLoggedIn());
					
			} else if (yesOrNo.equals("no") || yesOrNo.equals("n")) {
					
				System.out.println("------register--------\n");
	
				do {
					System.out.println("Enter username");
					username = sc.nextLine();
	
					do {
	
						System.out.println("Enter password");
						password = sc.nextLine();
	
						System.out.println("Re-Enter password");
						reEnterPass = sc.nextLine();
						if(!password.equals(reEnterPass)) System.out.println("Passwords must be the same\n");			
	
					} while(!password.equals(reEnterPass));
	
	
					register = new RegisterUser(username, password);
	
					// TODO add to method register.getMessage();
//					if (register.userExist())  
//						System.out.println("The username you entered already exist.\n");
				} while (register.userExist());
					register.registerResult();
					System.out.println("Account Successfully Created\n");
					login = new Login(username, password);
					
			} else {
				System.out.println("Please enter a valid commmand.\n");
			}
		} while (!yesOrNo.toLowerCase().matches("y|yes|n|no"));
			
		if (login.superUser()) {
			superUser = new SuperUser(login);
			System.out.println("You are logged in as the Super User!\n");
		}
		else {
			simpleUser = new SimpleUser(login);
		}


		System.out.println("\nWelcome " + username + "! You have successfully logged in.\n");
		

		if (simpleUser != null) {
			
			do {
				
				// TODO 
				simpleUser.viewAllAccounts();
				System.out.println("Type:");
				System.out.println("\t(1) to create a new account.\n "
						+ "\t(2) to delete an account that has a balance of 0.00.\n"
						+ "\t(3) to deposit money in an existing account.\n"
						+ "\t(4) to withrdraw money from an existing account.\n"
						+ "\t(5) to view the transaction history of an account.\n"
						+ "\t(6) to logout.");

				option = sc.nextLine();
				

				
				String amount = null;
				String account;
				//			TODO
				// try to clear console at this point
				switch (Integer.parseInt(option)){

					case 1:
						do {
							System.out.println("Please enter the name of the account: ");
							account = sc.nextLine();
						} while (!simpleUser.createAccount(account));
						break;
						
					case 2:
						do {
							System.out.println("Please enter the name of the account you would like to delete: ");
							account = sc.nextLine();
						} while(simpleUser.deleteAccount(account));
						break;
						
					case 3:
						do {
							System.out.println("Please enter the name of the account you would deposit money into: ");
							account = sc.nextLine();
						} while(!simpleUser.accessAccount(account));
						
						System.out.println("Enter the amount you would like to deposit");
						amount = sc.nextLine();
						System.out.println("\n");
						simpleUser.deposit(account, Double.parseDouble(amount));
						break;
						
					case 4:
						do {
							System.out.println("Please enter the name of the account you would withdraw money from: ");
							account = sc.nextLine();
						} while(simpleUser.accessAccount(account));
						
						System.out.println("Enter the amount you would like to withdraw? ");
						amount = sc.nextLine();
						simpleUser.withdraw(account, Double.parseDouble(amount));
						break;
						
					case 5:
						simpleUser.viewTransactionHistory();
						break;
					case 6:
						System.out.println("\nAre you sure you would like to log out?\n");
						break;
					default:
						System.out.println("Enter a valid option (1-6)");
				}
				
				System.out.println("Type: \n" +
									"\t(1) to continue to the main screen or \n" +
									"\t(2) to logout\n");
				option = sc.nextLine();
				System.out.println("---------------------------------------------------------------------\n");
				
				if (Integer.parseInt(option) == 2) option = "6";
				
			} while (Integer.parseInt(option) != 6);
		} else if (superUser != null) {
			
//			TODO
//			if (superUser != null) {
			
//				System.out.println("You are a super user\n");
//				System.out.println("Commands: view, create, update, or delete all users ");
			
//			}
		}
		
	System.out.println("You have successfully logged out!");
	}
	
}			
		