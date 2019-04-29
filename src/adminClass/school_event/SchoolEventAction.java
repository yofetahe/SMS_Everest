package adminClass.school_event;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SchoolEventAction extends ActionSupport implements ModelDriven<SchoolEventBean>, SessionAware {

	private static final long serialVersionUID = -5088745643220837464L;
	private List<SchoolEventBean> seblist;
	private Map<String, Object> sessionMap;
	
	SchoolEventBean seb = new SchoolEventBean();
	
	public String adminSchoolEvent(){
		if (sessionMap.containsKey("userName")) {
			seblist = SchoolEventDAO.seventList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String adminSchoolEventCrtForm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String adminSchoolEventEditForm(){
		if (sessionMap.containsKey("userName")) {
			seblist = SchoolEventDAO.seventList();
			Integer indx = Integer.parseInt(seb.getIndx());
			
			seb.setSe_id(seblist.get(indx).getSe_id());
			seb.setSe_name(seblist.get(indx).getSe_name());
			seb.setSe_description(seblist.get(indx).getSe_description());
			seb.setSe_greg_date(seblist.get(indx).getSe_greg_date());
			seb.setSe_status(seblist.get(indx).getSe_status());
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String adminSchoolEventSave(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = SchoolEventDAO.saveSchoolEvent(seb);
			if(rslt){
				seblist = SchoolEventDAO.seventList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String adminSchoolEventUpdate(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = SchoolEventDAO.updateSchoolEvent(seb);
			if(rslt){
				seblist = SchoolEventDAO.seventList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public SchoolEventBean getModel() {
		return seb;
	}

	public List<SchoolEventBean> getSeblist() {
		return seblist;
	}

	public void setSeblist(List<SchoolEventBean> seblist) {
		this.seblist = seblist;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}
