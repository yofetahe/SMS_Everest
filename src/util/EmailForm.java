package util;

import email_communication.EmailComAction;
import email_communication.EmailComBean;

public class EmailForm {
	
	public static String getEmailForm(String emailto, String emailSubject, String emailBody){
		
		EmailComBean ecb = new EmailComBean();
		
		ecb.setFrom(SysConstant.SCHOOL_GMAIL_ACCOUNT);
		ecb.setPassword(SysConstant.SCHOOL_GMAIL_ACCOUNT_PASSWORD);
		ecb.setTo(emailto);
		ecb.setSubject(emailSubject);			
		ecb.setHtmlbody(emailBody);
		
		EmailComAction eca = new EmailComAction();
		String rslt = eca.sendAnEmail(ecb);
		
		if(rslt.equalsIgnoreCase("SUCCESS")){
		
			return "success";
            
		} else {            					
			
            return "error";
		}
	}

}
