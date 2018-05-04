package com.Revature.Bank;

import java.util.List;

public interface LoginDao {
	
	int getUsernameById();
	
	String getUsername();
	
	boolean isLoggedIn();
	
	boolean superUser();
	
}
