package specialNeedRequired;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import adminClass.AdminAction;

public class SpecialNeedRequiredAction extends ActionSupport implements ModelDriven<SpecialNeedRequiredBean>, SessionAware {

	private static final long serialVersionUID = 6687927653192384206L;
	SpecialNeedRequiredBean snrb = new SpecialNeedRequiredBean();
	private Map<String, Object> sessionMap;
	
	////***** Logged in user id *****////
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
	
	public String saveStudentSpecialNeedRequirement(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = SpecialNeedRequiredDAO.saveStudentSpecialNeedRequirement(snrb, getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String updateStudentSpecialNeedRequirement(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = SpecialNeedRequiredDAO.updateStudentSpecialNeedRequirement(snrb, getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	@Override
	public SpecialNeedRequiredBean getModel() {
		return snrb;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	

}
