package com.Revature.Bank;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		String yesOrNo = null;
		
		String username = null;
		String password = null;
		
		Login login = null;
		RegisterUser register;
		
		SimpleUser simpleUser = null;
		SuperUser superUser = null;
		
		
		
		int option = 0;
		
		System.out.println("Welcome to the Bank!\n");
		Scanner sc = new Scanner(System.in);
		
		do {	
			System.out.println("Do you have an account?");
			yesOrNo = sc.nextLine();

			if(yesOrNo.toLowerCase().equals("yes") || yesOrNo.toLowerCase().equals("y")) {
				
				do {		
					System.out.println("Enter your username: ");
					username = sc.nextLine();
		
					System.out.println("Enter your password: ");
					password = sc.nextLine();
					
					login = new Login(username, password);
					
					if (!login.loggedIn()) System.out.println("The username or password entered is incorrect. Please try again.\n");
					
				} while (!login.loggedIn());
					
			} else if (yesOrNo.equals("no") || yesOrNo.equals("n")) {
					
				System.out.println("------register--------\n");
	
				do {
					System.out.println("Enter username");
					username = sc.nextLine();
	
					do {
	
						System.out.println("Enter password");
						password = sc.nextLine();
	
						System.out.println("Re-Enter password");
	
						if(!password.equals(sc.nextLine())) System.out.println("Passwords must be the same\n");			
	
					} while(!password.equals(sc.nextLine()));
	
	
					register = new RegisterUser(username, password);
	
					// TODO add to method register.getMessage();
					if (register.userExist())  System.out.println("The username you entered already exist.");
	
				} while (register.userExist());
					
					register.registerResult();
					// TODO add this print statement to above method?
					System.out.println("Account Successfully Created!\n");
					
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
			
		System.out.println("Welcome " + username + "! You have successfully logged in.\n");
		

		if (simpleUser != null) {
			
			do {
				
				// TODO 
				simpleUser.viewAllAccounts();
				System.out.println("Type:");
				System.out.println("\t(1) to create a new account.\n "
						+ "\t(2) to access an existing account.\n"
						+ "\t(3) to delete an account that has a balance of 0.00.\n"
						+ "\t(4) to deposit money in an existing account."
						+ "\t(5) to withrdraw money from an existing account.\n"
						+ "\t(6) to view the transaction history of an account.\n"
						+ "\t(7) to logout.");
				option = sc.nextInt();
				
				int amount = 0;
				String account;
				//			TODO
				// try to clear console at this point
				switch (option){

					case 1:
						System.out.println("Please enter the name of the account: \n");
						account = sc.nextLine();
						//TODO have method print a success or unsuccess
						simpleUser.createAccount(account);
						break;
						
					case 2:
						System.out.println("Please enter the name of the account you would like to access: \n");
						account = sc.nextLine();
						simpleUser.accessAccount(account);
						break;
						
					case 3:
						System.out.println("Please enter the name of the account you would like to delete: \n");
						account = sc.nextLine();
						simpleUser.deleteAccount(account);
						break;
						
					case 4:
						System.out.println("Please enter the name of the account you would deposit money into: \n");
						account = sc.nextLine();
						simpleUser.accessAccount(account);
						System.out.println("Enter the amount you would like to deposit?\n");
						amount = sc.nextInt();
						simpleUser.deposit(account, amount);
						break;
						
					case 5:
						System.out.println("Please enter the name of the account you would withdraw money from: \n");
						account = sc.nextLine();
						simpleUser.accessAccount(account);
						System.out.println("Enter the amount you would like to withdraw?\n");
						amount = sc.nextInt();
						simpleUser.withdraw(account, amount);
						break;
						
					case 6:
						// view transaction history
						// simpleUser.viewTransactionHist();
						break;
					case 7:
						break;
					default:
						System.out.println("Enter a valid option (1-6)");
				}
				
				System.out.println("Type: \n" +
									"\t(1) to continue to the main screen or \n" +
									"\t(2) to logout\n");
				option = sc.nextInt();
				System.out.println("---------------------------------------------------------------------\n");
				
				if (option == 2) option = 7;
				
			} while (option != 7);
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
		