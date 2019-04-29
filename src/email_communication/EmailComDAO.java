package email_communication;

import java.util.List;

import nonteacherClass.NonteacherBean;
import studentClass.current_address.CurrentAddressBean;
import teacherClass.current_address.TeacherCuraddBean;

public class EmailComDAO {
	
	public static String emailList = "";
	public static int emailCounter = 0;
	public static String[] emails = new String[500];
	
	public static String collectEmailsToSend(EmailComBean em){
		
		emails[emailCounter] = em.getTo();
		emailCounter++;
		
		return emailList;
	}

	public static String removeEmailFromList(EmailComBean em){
		
		int counter = emailCounter;
		for(int i = 0; i <= counter; i++){
			if(emails[i].equals(em.getTo())){
				emails[i] = "";
				emailCounter--;
			}
		}		
		return emailList;
	}
	
	public static String collectSelectedEmailsList(){
		
		for(int i = 0; i < emailCounter; i++){
			if(emailList.length() > 0){
				emailList = emailList + "; ";
			}
			emailList = emailList + emails[i];
		}
		
		return emailList;
	}
	
	public static void clearSelectedEmailList(){
		emailList = "";
		emailCounter = 0;
	}
	
	public static String getAllTeacherEmailList(List<TeacherCuraddBean> teachersEmailList){
		emailList = "";
		
		for(int i = 0; i < teachersEmailList.size(); i++){
			if(emailList.length() > 0){
				emailList = emailList + "; " + teachersEmailList.get(i).getEmail();
			} else {
				emailList = emailList + teachersEmailList.get(i).getEmail();
			}
		}
		
		return emailList;
	}
	
	public static String getAllNonTeacherEmailList(List<NonteacherBean> nonacademicEmailList){
		emailList = "";
		
		for(int i = 0; i < nonacademicEmailList.size(); i++){
			if(emailList.length() > 0){
				emailList = emailList + "; " + nonacademicEmailList.get(i).getNti_email();
			} else {
				emailList = emailList + nonacademicEmailList.get(i).getNti_email();
			}
		}
		
		return emailList;
	}
	
	public static String getAllParentEmailList(List<CurrentAddressBean> studentParentList){
		emailList = "";
		
		for(int i = 0; i < studentParentList.size(); i++){
			if(emailList.length() > 0){
				emailList = emailList + "; " + studentParentList.get(i).getEmail();
			} else {
				emailList = emailList + studentParentList.get(i).getEmail();
			}
		}
		
		return emailList;
	}
}
