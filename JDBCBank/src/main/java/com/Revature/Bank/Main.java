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
			
				if (superUser != null) {
				
					System.out.println("You are a super user\n");
					System.out.println("Commands: view, create, update, or delete all users ");
					
					do {
						
						superUser.viewAllUsers();
						System.out.println("Type:");
						System.out.println("\t(1) to View all existing users.\n "
								+ "\t(2) to Create a User.\n"
								+ "\t(3) to Update an existing user.\n"
								+ "\t(4) to Delete an existing user.\n"
								+ "\t(5) to logout.");
	
						option = sc.nextLine();
						
						String userIdStr = null;
						int userId = -1;
						String account;
						//			TODO
						// try to clear console at this point
						do {
							
							switch (Integer.parseInt(option)){
								
								case 1:
									superUser.viewAllUsers();
									break;	
								case 3:
									do {
										System.out.println("You are attempting to update an existing user. \n");
										
										System.out.println("Please enter the userId (Must be an integer): ");
										userIdStr = sc.nextLine();
										do {
											  try  
											  {  
											    userId = Integer.parseInt(userIdStr);  
											  }  
											  catch(NumberFormatException e)  
											  {  
											    System.out.println("Must enter a numeric value.");
											  }  
										} while (userId == -1);
										
										int userOrPassOption = 0;
										do {
											System.out.println("Would you like to update the username (1) or password (2) ? ");
											userOrPassOption = Integer.parseInt(sc.next());
											
											if (userOrPassOption != 1 || userOrPassOption != 2)
												System.out.println("Enter (1) for username or (2) for password");

										} while(userOrPassOption != 1 || userOrPassOption != 2);
										
										String userOrPass = null;
										if (userOrPassOption == 1) {
											userOrPass = "username";
										} else {
											userOrPass = "password";
										}
										
										String fieldToChange = null;

										do {

											System.out.println("Are you attempting to change your username (1) or password (2) ? ");
											fieldToChange = sc.nextLine();
											
											if (!fieldToChange.equals("1") || !fieldToChange.equals("2"))
												System.out.println("Enter (1) for username or (2) for password");

										} while(fieldToChange.equals("1") || fieldToChange.equals("2"));
										
										if (fieldToChange.equals("1")) {
											fieldToChange = "username";
											
										} else {
											fieldToChange = "password";
										}
										
										superUser.updateUser(userId, userOrPass, fieldToChange);
									} while(simpleUser.deleteAccount(account));
									break;
									
								case 2:
									System.out.println("You are now creating a new user.");
									System.out.println("Please enter the name of the user");
									String username1 = sc.nextLine();
									
									System.out.println("Please enter the name of the password");
									String password2 = sc.nextLine();
									
									if (username1.contains("\\s+") || password2.matches("\\s+")) {
										System.out.println("");
									}
									superUser.createUser(username1, password2);
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
									System.out.println("\nAre you sure you would like to log out?\n");
									break;
								default:
									System.out.println("Enter a valid option (1-5)");
							}
						} while(Integer.parseInt(option) >=1 && Integer.parseInt(option) <= 5);

						
						System.out.println("Type: \n" +
											"\t(1) to continue to the main screen or \n" +
											"\t(2) to logout\n");
						option = sc.nextLine();
						System.out.println("---------------------------------------------------------------------\n");
						
						
				
				} while (Integer.parseInt(option) != 5);
			}
			
		System.out.println("You have successfully logged out!");
	}
	}
	
}			
		