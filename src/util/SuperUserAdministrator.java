package util;

public class SuperUserAdministrator {
	
	public static boolean validateSuperUser(String username, String password){
		
		boolean rslt = false;
		
		if(username.equals("ADMINISTRATOR") && password.equals("sms@betty*2067650002")){
			rslt = true;
		}
		
		return rslt;
	}

}
