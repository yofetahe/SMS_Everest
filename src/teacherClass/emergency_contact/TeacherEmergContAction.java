package teacherClass.emergency_contact;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TeacherEmergContAction extends ActionSupport implements ModelDriven<TeacherEmergContBean>, SessionAware{
	
	private static final long serialVersionUID = 3637764626670589394L;
	private String tec_id;
	private String contact_name;
	private String relationship;
	private String mobile_no;
	private String home_phone;
	private String office_phone;
	private String tec_status;
	private String ti_id;
	
	private String frmtype;
	
	private List<TeacherEmergContBean> tchremergcont_rslt;
	private Map<String, Object> sessionMap;
	
	TeacherEmergContBean emrgcont = new TeacherEmergContBean();
	
	public String emergContSaveFrm(){
		if (sessionMap.containsKey("userName")) {
			tchremergcont_rslt = TeacherEmergContDAO.getListEmergCont(emrgcont);
			
			if(tchremergcont_rslt.size() > 0){
				
				emrgcont.setContact_name(tchremergcont_rslt.get(0).getContact_name());
				emrgcont.setRelationship(tchremergcont_rslt.get(0).getRelationship());
				emrgcont.setMobile_no(tchremergcont_rslt.get(0).getMobile_no());
				emrgcont.setHome_phone(tchremergcont_rslt.get(0).getHome_phone());
				emrgcont.setOffice_phone(tchremergcont_rslt.get(0).getOffice_phone());
				emrgcont.setTec_status(tchremergcont_rslt.get(0).getTec_status());
				emrgcont.setTec_id(tchremergcont_rslt.get(0).getTec_id());
				
				emrgcont.setFrmtype("update");
			} else {
				emrgcont.setFrmtype("save");
			}
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String emergContSave(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(emrgcont.getContact_name().trim().length() == 0){
				x++;
				addFieldError("tchremergcontError", "Contact name is blank.");
			}
			if(emrgcont.getRelationship().trim().length() == 0){
				x++;
				addFieldError("tchremergcontError", "Relationship is blank.");
			}
			if(emrgcont.getMobile_no().trim().length() == 0 && emrgcont.getHome_phone().trim().length() == 0 && emrgcont.getOffice_phone().trim().length() == 0){
				x++;
				addFieldError("tchremergcontError", "At lease one phone number must be given.");
			}
			
			if(x > 0){
				return "CREATEFORM";
			} else {
				boolean rslt = TeacherEmergContDAO.insertEmergCont(emrgcont);
				
				if(rslt){
					emrgcont.setSuccessful_save("true");
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
	}
	
	public String emergContUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(emrgcont.getContact_name().trim().length() == 0){
				x++;
				addFieldError("tchremergcontError", "Contact name is blank.");
			}
			if(emrgcont.getRelationship().trim().length() == 0){
				x++;
				addFieldError("tchremergcontError", "Relationship is blank.");
			}
			if(emrgcont.getMobile_no().trim().length() == 0 && emrgcont.getHome_phone().trim().length() == 0 && emrgcont.getOffice_phone().trim().length() == 0){
				x++;
				addFieldError("tchremergcontError", "At lease one phone number must be given.");
			}
			
			if(x > 0){
				return "UPDATEFORM";
			} else {
				boolean rslt = TeacherEmergContDAO.updateEmergCont(emrgcont);
				
				if(rslt){
					emrgcont.setSuccessful_update("true");
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}		
	}
	
	
	
	
	
	
	public String getTec_id() {
		return tec_id;
	}
	public void setTec_id(String tec_id) {
		this.tec_id = tec_id;
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
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	public String getTec_status() {
		return tec_status;
	}
	public void setTec_status(String tec_status) {
		this.tec_status = tec_status;
	}
	public String getTi_id() {
		return ti_id;
	}
	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}
	@Override
	public TeacherEmergContBean getModel() {
		return emrgcont;
	}
	public String getFrmtype() {
		return frmtype;
	}
	public void setFrmtype(String frmtype) {
		this.frmtype = frmtype;
	}
	public List<TeacherEmergContBean> getTchremergcont_rslt() {
		return tchremergcont_rslt;
	}
	public void setTchremergcont_rslt(List<TeacherEmergContBean> tchremergcont_rslt) {
		this.tchremergcont_rslt = tchremergcont_rslt;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}
