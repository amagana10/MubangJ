package com.Revature.Bank;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		String username;
		String password;
		
		Login login;
		RegisterUser register;
		
		SimpleUser simpleUser;
		SuperUser superUser;

		String account;
		
		int option = 0;
		
		System.out.println("Welcome to the Bank!\n");
		
		System.out.println("Do you have an account?");
		Scanner sc = new Scanner(System.in);
		String yesOrNo = sc.nextLine();
		
		while (true) {
			
			if(yesOrNo.toLowerCase().equals("yes") || yesOrNo.toLowerCase().equals("y")) {
				
				System.out.println("Enter your username: ");
				username = sc.nextLine();
				
				System.out.println("Enter your password: ");
				password = sc.nextLine();
				
			} else if (yesOrNo.equals("no") || yesOrNo.equals("n")) {
				
				System.out.println("------register--------\n");
				
				System.out.println("Enter username");
				username = sc.nextLine();
				
				System.out.println("Enter password");
				password = sc.nextLine();
				
				System.out.println("Re-Enter password");
				
				while (!password.equals(sc.nextLine())) {
					System.out.println("Passwords must be the same\n");
				}
				
				
				register = new RegisterUser(username, password);
				
				System.out.println("Account Successfully Created!\n");
				break;
				
			}
			login = new Login(username, password);
			if (login.loggedIn()) {
				
				if (login.superUser()) {
					superUser = new SuperUser(username, password);
				}
				
				simpleUser = new SimpleUser(username, password);
			} else {
				System.out.println("The username or password entered is incorrect.");
			}
		}
			
		System.out.println("You have successfully logged in " + "\n");
		
//		TODO
//		if (superUser != null) {
		
//			System.out.println("You are a super user\n");
//			System.out.println("Commands: view, create, update, or delete all users ");
		
//		}
		
		// TODO user.viewAllAccounts()
		
		while (option != 1 || option != 2) {
			
			System.out.println("Type:");
			System.out.println("\t(1) to create a new account\n "
					+ "\t(2) to access an existing account\n");
			option = sc.nextInt();
			
			if (option == 1) {
				System.out.println("What would you like to name the account (May only use alphabetic characters (a-z, A-Z)): ");
				simpleUser.createAccount(sc.nextLine());
			}
		}
//				+ "\t(3) to deposit money, withdraw money, or delete the existing account.\n");
		
		
		System.out.println("Which account would you like to access: ");
		account = sc.nextLine();
		
		System.out.println("commands: " + "withdraw, ");
		
		// write query to test if user and password exist in database
		
		//if they do then log them in
		
//		if (user != null) {
//			System.out.println("You have successfully been logged in!");
//		}
		
		
		
		

	}
}
