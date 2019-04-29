package studentClass.emergency_contact;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EmergencyContactAction extends ActionSupport implements ModelDriven<EmergencyContactBean>, SessionAware{
	
	private static final long serialVersionUID = -1642906816793545002L;
	private String sec_id;
	private String contact_name;
	private String relationship;
	private String mob_no;
	private String office_phone_no;
	private String home_phone_no;
	private String sec_status;
	private String si_id;
	private List<EmergencyContactBean> emergcont_rslt;
	private Map<String, Object> sessionMap;
	
	EmergencyContactBean emcont = new EmergencyContactBean();
	
	public String execute(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentEmergencyContactCreate(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentEmergencyContactList(){
		if (sessionMap.containsKey("userName")) {
			emergcont_rslt = EmergencyContactDAO.getEmergContList(emcont.getSi_id());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String studentEmergencyContactEdit(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentEmergencyContactSave(){
		if (sessionMap.containsKey("userName")) {
			Boolean rslt = EmergencyContactDAO.saveEmergContact(emcont.getContact_name(), emcont.getRelationship(), emcont.getMob_no(), emcont.getHome_phone_no(), emcont.getOffice_phone_no(), emcont.getSi_id());
			if(rslt){
				emcont.setSuccessful_save("true");
				emergcont_rslt = EmergencyContactDAO.getEmergContList(emcont.getSi_id());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String studentEmergencyContactUpdate(){
		if (sessionMap.containsKey("userName")) {
			Boolean rslt = EmergencyContactDAO.updateEmergContact(emcont.getContact_name(), emcont.getRelationship(), emcont.getMob_no(), emcont.getHome_phone_no(), emcont.getOffice_phone_no(), emcont.getSec_status(), emcont.getSec_id());
			if(rslt){
				emcont.setSuccessful_update("true");
				emergcont_rslt = EmergencyContactDAO.getEmergContList(emcont.getSi_id());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
		
	}
	
	
	
	
	
	
	
	
	
	
	public String getSec_id() {
		return sec_id;
	}
	public void setSec_id(String sec_id) {
		this.sec_id = sec_id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getMob_no() {
		return mob_no;
	}
	public void setMob_no(String mob_no) {
		this.mob_no = mob_no;
	}
	public String getOffice_phone_no() {
		return office_phone_no;
	}
	public void setOffice_phone_no(String office_phone_no) {
		this.office_phone_no = office_phone_no;
	}
	public String getHome_phone_no() {
		return home_phone_no;
	}
	public void setHome_phone_no(String home_phone_no) {
		this.home_phone_no = home_phone_no;
	}
	public String getSec_status() {
		return sec_status;
	}
	public void setSec_status(String sec_status) {
		this.sec_status = sec_status;
	}	

	public List<EmergencyContactBean> getEmergcont_rslt() {
		return emergcont_rslt;
	}

	public void setEmergcont_rslt(List<EmergencyContactBean> emergcont_rslt) {
		this.emergcont_rslt = emergcont_rslt;
	}

	@Override
	public EmergencyContactBean getModel() {
		return emcont;
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
