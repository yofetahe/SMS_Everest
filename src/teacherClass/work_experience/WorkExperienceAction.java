package teacherClass.work_experience;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import teacherClass.edu_background.EduBackgroundDAO;

public class WorkExperienceAction extends ActionSupport implements ModelDriven<WorkExperienceBean>, SessionAware {

	private static final long serialVersionUID = 7422499617360056892L;
	
	WorkExperienceBean we = new WorkExperienceBean();
	
	private Map<String, Object> sessionMap;
	
	private List<WorkExperienceBean> workexp_list;
	
	public String getWorkExpList(){
		if(sessionMap.containsKey("userName")){
			
			workexp_list = WorkExperienceDAO.getWorkExpList(we);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String workExpAddForm(){
		if(sessionMap.containsKey("userName")){			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
		
	public String saveWorkExp(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = WorkExperienceDAO.saveWorkExp(we);
			if(rslt){
				workexp_list = WorkExperienceDAO.getWorkExpList(we);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String workExpUpdateForm(){
		if(sessionMap.containsKey("userName")){
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	} 
	
	public String updateWorkExp(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = WorkExperienceDAO.updateWorkExp(we);
			if(rslt){
				workexp_list = WorkExperienceDAO.getWorkExpList(we);
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
	public WorkExperienceBean getModel() {
		return we;
	}

	public List<WorkExperienceBean> getWorkexp_list() {
		return workexp_list;
	}

	public void setWorkexp_list(List<WorkExperienceBean> workexp_list) {
		this.workexp_list = workexp_list;
	}

}
