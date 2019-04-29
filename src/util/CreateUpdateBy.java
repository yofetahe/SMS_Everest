package util;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class CreateUpdateBy implements SessionAware {
	
	private static final long serialVersionUID = -2211652603271878170L;
	
	private static Map<String, Object> sessionMap;	
	
	private static String loggedInUser;
	
	public static String getLoggedInUser(String loggedInUserType){
		
		System.out.println(" type " + sessionMap.get("nti_id"));
		if(loggedInUserType.equals("NTHCR")){
			loggedInUser = (String) sessionMap.get("nti_id");
		}
		if(loggedInUserType.equals("THCR")){
			loggedInUser = (String) sessionMap.get("ti_id");
		}
		System.out.println(loggedInUser + " logged in user ");
		return loggedInUser;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;		
	}

	public String getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}	
	
	
}
