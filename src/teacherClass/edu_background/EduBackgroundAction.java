package teacherClass.edu_background;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EduBackgroundAction extends ActionSupport implements ModelDriven<EduBackgroundBean>, SessionAware {

	private static final long serialVersionUID = -5759755565322783621L;

	EduBackgroundBean eb = new EduBackgroundBean();
	
	private List<EduBackgroundBean> edubackground_list;
	
	private Map<String, Object> sessionMap;
	
	public String getEduBackgroundList(){
		if(sessionMap.containsKey("userName")){
			
			edubackground_list = EduBackgroundDAO.getEduBackgroundList(eb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String eduBackgroundAddForm(){
		if(sessionMap.containsKey("userName")){			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
		
	public String saveEduBackground(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = EduBackgroundDAO.saveEduBackground(eb);
			if(rslt){
				edubackground_list = EduBackgroundDAO.getEduBackgroundList(eb);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String eduBackgroundUpdateForm(){
		if(sessionMap.containsKey("userName")){
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	} 
	
	public String updateEduBackground(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = EduBackgroundDAO.updateEduBackground(eb);
			if(rslt){
				edubackground_list = EduBackgroundDAO.getEduBackgroundList(eb);
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
	public EduBackgroundBean getModel() {
		return eb;
	}

	public List<EduBackgroundBean> getEdubackground_list() {
		return edubackground_list;
	}

	public void setEdubackground_list(List<EduBackgroundBean> edubackground_list) {
		this.edubackground_list = edubackground_list;
	}
	
	

}
