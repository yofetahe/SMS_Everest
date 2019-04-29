package nonteacherClass;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import adminClass.AdminBean;
import adminClass.AdminDAO;
import teacherClass.TeacherDAO;
import util.RoleValidator;

public class NonteacherAction extends ActionSupport implements ModelDriven<NonteacherBean>, SessionAware {
	
	private static final long serialVersionUID = -3929068151646787974L;
	private Map<String, Object> sessionMap;
	
	private List<AdminBean> usrRoleList;
	private List<NonteacherBean> nontchr_list;
	String loggedUserName = null;
	
	NonteacherBean ntb = new NonteacherBean();
	
	public String nonteacherList(){
		// role validation if the userName is already stored in the session
		loggedUserName = null;
		if (sessionMap.containsKey("userName")) {
		    loggedUserName = (String) sessionMap.get("userName");        
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Non_Teacher";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
			
			ntb.setNonteach_tab(ntb.getNonteach_tab());
			nontchr_list = NonteacherDAO.getNonteacherList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveNonteacher(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = NonteacherDAO.saveNonteacher(ntb);
			if(rslt){
				nontchr_list = NonteacherDAO.getNonteacherList();
				return SUCCESS;
			} else {
				return INPUT;
			}
		} else {
			return INPUT;
		}
	}
	
	public String updateFormNonteacher(){
		if (sessionMap.containsKey("userName")) {		
			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String updateNonteacher(){
		if (sessionMap.containsKey("userName")) {		
			
			boolean rslt = NonteacherDAO.updateNonteacher(ntb);
			if(rslt){
				nontchr_list = NonteacherDAO.getNonteacherList();
				return SUCCESS;
			} else {
				return INPUT;
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
	public NonteacherBean getModel() {
		return ntb;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}





	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}





	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}





	public List<NonteacherBean> getNontchr_list() {
		return nontchr_list;
	}





	public void setNontchr_list(List<NonteacherBean> nontchr_list) {
		this.nontchr_list = nontchr_list;
	}

}
