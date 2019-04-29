package teacherClass.disciplinary_action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TeacherDisactAction extends ActionSupport implements ModelDriven<TeacherDisactBean>, SessionAware {

	private static final long serialVersionUID = -4154646683490118370L;
	private String tda_id;
	private String tda_type;
	private String tda_reason;
	private String tda_date;
	private String tda_status;
	private String ti_id;
	
	private List<TeacherDisactBean> disact_rslt;
	private Map<String, Object> sessionMap;
	
	TeacherDisactBean disact = new TeacherDisactBean();
	
	public String disactList(){
		if (sessionMap.containsKey("userName")) {
			disact_rslt = TeacherDisactDAO.getDisactList(disact);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String disactSaveFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String disactSave(){
		
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			if(disact.getTda_type().trim().length() == 0){
				x++;
				addFieldError("disactError", "Dis act type is blank.");
			}
			
			if(x > 0){
				return "CREATEFORM";
			} else {
				boolean rslt = TeacherDisactDAO.insertDisAct(disact);
				if(rslt){
					disact_rslt = TeacherDisactDAO.getDisactList(disact);
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
	}
	
	public String disactEditFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String disactUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			if(disact.getTda_type().trim().length() == 0){
				x++;
				addFieldError("disactError", "Dis act type is blank.");
			}
			
			if(x > 0){
				return "UPDATEFORM";
			} else {
				boolean rslt = TeacherDisactDAO.updateDisAct(disact);
				if(rslt){
					disact_rslt = TeacherDisactDAO.getDisactList(disact);
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}		
		
	}
	
	
	
	
	
	
	public String getTda_id() {
		return tda_id;
	}
	public void setTda_id(String tda_id) {
		this.tda_id = tda_id;
	}
	public String getTda_type() {
		return tda_type;
	}
	public void setTda_type(String tda_type) {
		this.tda_type = tda_type;
	}
	public String getTda_reason() {
		return tda_reason;
	}
	public void setTda_reason(String tda_reason) {
		this.tda_reason = tda_reason;
	}
	public String getTda_date() {
		return tda_date;
	}
	public void setTda_date(String tda_date) {
		this.tda_date = tda_date;
	}
	public String getTda_status() {
		return tda_status;
	}
	public void setTda_status(String tda_status) {
		this.tda_status = tda_status;
	}
	public String getTi_id() {
		return ti_id;
	}
	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}
	@Override
	public TeacherDisactBean getModel() {
		return disact;
	}
	public List<TeacherDisactBean> getDisact_rslt() {
		return disact_rslt;
	}
	public void setDisact_rslt(List<TeacherDisactBean> disact_rslt) {
		this.disact_rslt = disact_rslt;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
}
