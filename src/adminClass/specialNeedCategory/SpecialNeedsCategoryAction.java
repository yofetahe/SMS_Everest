package adminClass.specialNeedCategory;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SpecialNeedsCategoryAction extends ActionSupport implements ModelDriven<SpecialNeedsCategoryBean>, SessionAware {

	private static final long serialVersionUID = 7541302743405543186L;
	SpecialNeedsCategoryBean sncb = new SpecialNeedsCategoryBean();
	private Map<String, Object> sessionMap;
	
	private List<SpecialNeedsCategoryBean> spNeedsCatList;
	
	public String specialNeedTemp(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String specialNeedCategoryList(){
		if(sessionMap.containsKey("userName")){
			spNeedsCatList = SpecialNeedsCategoryDAO.getAllSpecialNeedsCategoryList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveSpecialNeedCategory(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = SpecialNeedsCategoryDAO.saveSpecialNeedsCategory(sncb);
			spNeedsCatList = SpecialNeedsCategoryDAO.getAllSpecialNeedsCategoryList();
			if(rslt){
				addActionMessage("Successfully Saved.");
				return SUCCESS;
			} else {
				addActionError("The category is not saved. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String specialNeedCategoryUpdateForm(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateSpecialNeedCategory(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = SpecialNeedsCategoryDAO.updateSpecialNeedsCategory(sncb);
			spNeedsCatList = SpecialNeedsCategoryDAO.getAllSpecialNeedsCategoryList();
			if(rslt){
				addActionMessage("Successfully updated.");
				return SUCCESS;
			} else {
				addActionError("The category is not updated. Please try again.");
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
	public SpecialNeedsCategoryBean getModel() {
		return sncb;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<SpecialNeedsCategoryBean> getSpNeedsCatList() {
		return spNeedsCatList;
	}

	public void setSpNeedsCatList(List<SpecialNeedsCategoryBean> spNeedsCatList) {
		this.spNeedsCatList = spNeedsCatList;
	}

}
