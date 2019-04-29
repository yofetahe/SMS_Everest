package studentClass.disciplinary_action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DisciplinaryAction extends ActionSupport implements ModelDriven<DisciplinaryBean>, SessionAware{
	
	private static final long serialVersionUID = -6389691320711817267L;
	private String sda_id;
	private String sda_type;
	private String sda_reason;
	private String sda_date;
	private String sda_status;
	private String si_id;
	private List<DisciplinaryBean> disact_rslt;
	private Map<String, Object> sessionMap;
	
	DisciplinaryBean disact = new DisciplinaryBean();
	
	public String execute(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentDisActionCreate(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentDisActionEdit(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentDisActionList(){
		if (sessionMap.containsKey("userName")) {
			disact_rslt = DisciplinaryDAO.getDisActList(disact.getSi_id());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String studentDisActionSave(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = DisciplinaryDAO.saveDisAct(disact.getSda_type(), disact.getSda_reason(), disact.getSda_date(), disact.getSi_id());
			if(rslt){
				disact.setSuccessful_save("true");
				disact_rslt = DisciplinaryDAO.getDisActList(disact.getSi_id());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String studentDisActionUpdate(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = DisciplinaryDAO.updateDisAct(disact.getSda_type(), disact.getSda_reason(), disact.getSda_date(), disact.getSi_id(), disact.getSda_id(), disact.getSda_status());
			if(rslt){
				disact.setSuccessful_update("true");
				disact_rslt = DisciplinaryDAO.getDisActList(disact.getSi_id());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	
	
	
	
	
	public String getSda_id() {
		return sda_id;
	}
	public void setSda_id(String sda_id) {
		this.sda_id = sda_id;
	}
	public String getSda_type() {
		return sda_type;
	}
	public void setSda_type(String sda_type) {
		this.sda_type = sda_type;
	}
	public String getSda_reason() {
		return sda_reason;
	}
	public void setSda_reason(String sda_reason) {
		this.sda_reason = sda_reason;
	}
	public String getSda_date() {
		return sda_date;
	}
	public void setSda_date(String sda_date) {
		this.sda_date = sda_date;
	}
	public String getSda_status() {
		return sda_status;
	}
	public void setSda_status(String sda_status) {
		this.sda_status = sda_status;
	}
	@Override
	public DisciplinaryBean getModel() {
		return disact;
	}
	public List<DisciplinaryBean> getDisact_rslt() {
		return disact_rslt;
	}
	public void setDisact_rslt(List<DisciplinaryBean> disact_rslt) {
		this.disact_rslt = disact_rslt;
	}

	public String getSi_id() {
		return si_id;
	}

	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
		
	}
	

}
