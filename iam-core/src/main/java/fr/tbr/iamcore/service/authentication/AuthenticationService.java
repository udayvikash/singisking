package fr.tbr.iamcore.service.authentication;

public class AuthenticationService {

	private String username = "uday";
	private String password = "vikash";
	public boolean authenticate(String username, String password) {
		
		
		if(this.username.equalsIgnoreCase(username) && this.password.equalsIgnoreCase(password) )
			return true;
		
		return false;
	
	}

}