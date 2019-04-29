package email_communication;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import adminClass.AdminBean;
import adminClass.AdminDAO;
import nonteacherClass.NonteacherBean;
import nonteacherClass.NonteacherDAO;
import studentClass.StudentClassBean;
import studentClass.StudentDAO;
import studentClass.current_address.CurrentAddressBean;
import studentClass.current_address.CurrentAddressDOA;
import teacherClass.current_address.TeacherCuraddBean;
import teacherClass.current_address.TeacherCuraddDAO;
import util.RoleValidator;
import util.SysConstant;

public class EmailComAction extends ActionSupport implements ModelDriven<EmailComBean>, SessionAware  {

	private static final long serialVersionUID = -515838468415518215L;
	
	EmailComBean ecb = new EmailComBean();
	
	private List<StudentClassBean> grade_rslt;
	private List<AdminBean> usrRoleList;
	private List<TeacherCuraddBean> teachersEmailList;
	private List<NonteacherBean> nonacademicEmailList;
	private List<CurrentAddressBean> studentParentList;
	
	private Map<String, Object> sessionMap;	
	
	static Properties properties = new Properties();
	static{
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
	}
	
	////****** Logged in user id *****////
	public String getLoggedInUser(String loggedInUserType){
		String loggedInUser = "";
		if(loggedInUserType.equals("NTHCR")){
			loggedInUser = (String) sessionMap.get("nti_id");
		}
		if(loggedInUserType.equals("THCR")){
			loggedInUser = (String) sessionMap.get("ti_id");
		}
		return loggedInUser;
	}
	////****** Logged in user id *****////
	
	public String getEmailPage(){
		if(sessionMap.containsKey("userName")){
			
			String loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			//grade_rslt = StudentDAO.getClassList();
			
			String page = "Payment and Fin";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
			EmailComDAO.clearSelectedEmailList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getEmailListTemp(){
		if(sessionMap.containsKey("userName")){
			EmailComDAO.clearSelectedEmailList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getClassList(){
		if(sessionMap.containsKey("userName")){
			grade_rslt = StudentDAO.getClassList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	} 
	
	public String getTeacherEmailList(){
		if(sessionMap.containsKey("userName")){
			teachersEmailList = TeacherCuraddDAO.getTeachEmailAddress();
			return SUCCESS;
		} else {
			return INPUT;
		}
	} 
	
	public String addToEmailList(){
		if(sessionMap.containsKey("userName")){
			EmailComDAO.collectEmailsToSend(ecb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String removeFromEmailList(){
		if(sessionMap.containsKey("userName")){
			EmailComDAO.removeEmailFromList(ecb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String collectEmailList(){
		if(sessionMap.containsKey("userName")){			
			ecb.setTo(EmailComDAO.collectSelectedEmailsList());
			return SUCCESS;			
		} else {
			return INPUT;
		}
	}
	
	public String getAllTeacherEmailList(){
		if(sessionMap.containsKey("userName")){
			
			teachersEmailList = TeacherCuraddDAO.getTeachEmailAddress();
			ecb.setTo(EmailComDAO.getAllTeacherEmailList(teachersEmailList));
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String sendAnEmail(){
		if(sessionMap.containsKey("userName")){
			String ret = SUCCESS;
						
			try{
				
				Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
					protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
						return new javax.mail.PasswordAuthentication(SysConstant.SCHOOL_GMAIL_ACCOUNT, SysConstant.SCHOOL_GMAIL_ACCOUNT_PASSWORD);
					}
				});
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(ecb.getFrom()));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ecb.getTo()));
				message.setSubject(ecb.getSubject());
				message.setText(ecb.getBody());
				Transport.send(message);
				
			} catch(Exception ex){
				addActionMessage("There is no internet connection.");
				ret = ERROR;
				ex.printStackTrace();
			}
			EmailComDAO.clearSelectedEmailList();
			return ret;
		} else {
			return INPUT;
		}
	}
	
	public String sendAnEmail(EmailComBean ecb){
		
		String ret = SUCCESS;
		
		try{
			
			Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
				protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
					return new javax.mail.PasswordAuthentication(SysConstant.SCHOOL_GMAIL_ACCOUNT, SysConstant.SCHOOL_GMAIL_ACCOUNT_PASSWORD);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ecb.getFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ecb.getTo()));
			message.setSubject(ecb.getSubject());
			message.setContent(ecb.getHtmlbody(), "text/html; charset=utf-8");
			Transport.send(message);
			
		} catch(MessagingException ex){
			
			ret = ERROR;
		} catch(Exception ex){
			
			addActionMessage("There is no internet connection.");
			ret = ERROR;
			ex.printStackTrace();
		}
		EmailComDAO.clearSelectedEmailList();
		return ret;
		
	}
	
	public String getNonAcademicStuffEmailList(){
		if(sessionMap.containsKey("userName")){
			nonacademicEmailList = NonteacherDAO.getNonteacherList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getAllNonTeacherEmailList(){
		if(sessionMap.containsKey("userName")){
			
			nonacademicEmailList = NonteacherDAO.getNonteacherList();
			ecb.setTo(EmailComDAO.getAllNonTeacherEmailList(nonacademicEmailList));
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String ClDetailParentList(){
		if(sessionMap.containsKey("userName")){
			studentParentList = CurrentAddressDOA.getParentsEmailAccount(ecb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getAllParentsEmailList(){
		if(sessionMap.containsKey("userName")){
			
			studentParentList = CurrentAddressDOA.getParentsEmailAccount(ecb);
			ecb.setTo(EmailComDAO.getAllParentEmailList(studentParentList));
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	@Override
	public EmailComBean getModel() {
		return ecb;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}

	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}

	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}

	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}

	public List<TeacherCuraddBean> getTeachersEmailList() {
		return teachersEmailList;
	}

	public void setTeachersEmailList(List<TeacherCuraddBean> teachersEmailList) {
		this.teachersEmailList = teachersEmailList;
	}

	public List<NonteacherBean> getNonacademicEmailList() {
		return nonacademicEmailList;
	}

	public void setNonacademicEmailList(List<NonteacherBean> nonacademicEmailList) {
		this.nonacademicEmailList = nonacademicEmailList;
	}

	public List<CurrentAddressBean> getStudentParentList() {
		return studentParentList;
	}

	public void setStudentParentList(List<CurrentAddressBean> studentParentList) {
		this.studentParentList = studentParentList;
	}
	

}
