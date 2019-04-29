package teacherClass.current_address;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import teacherClass.TeacherBean;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TeacherCuraddAction extends ActionSupport implements ModelDriven<TeacherCuraddBean>, SessionAware{

	private static final long serialVersionUID = -8814212544626206148L;
	private String tca_id;
	private String sub_city;
	private String kebele;
	private String house_no;
	private String house_phone_no;
	private String mobile_no;
	private String ica_status;
	private String ti_id;
	
	private String frmtype;
	
	private List<TeacherCuraddBean> tchcuradd_rslt;
	private Map<String, Object> sessionMap;
	
	TeacherCuraddBean tchcadd = new TeacherCuraddBean();
	TeacherBean tchr = new TeacherBean();
	
	public String teacherCurraddEditFrm(){
		
		if (sessionMap.containsKey("userName")) {
			tchcuradd_rslt = TeacherCuraddDAO.getTeachCuradd(tchcadd);
			
			if(tchcuradd_rslt.size() > 0){
				tchcadd.setSub_city(tchcuradd_rslt.get(0).getSub_city());
				tchcadd.setKebele(tchcuradd_rslt.get(0).getKebele());
				tchcadd.setHouse_no(tchcuradd_rslt.get(0).getHouse_no());
				tchcadd.setHouse_phone_no(tchcuradd_rslt.get(0).getHouse_phone_no());
				tchcadd.setMobile_no(tchcuradd_rslt.get(0).getMobile_no());
				tchcadd.setEmail(tchcuradd_rslt.get(0).getEmail());
				tchcadd.setIca_status(tchcuradd_rslt.get(0).getIca_status());
				tchcadd.setTca_id(tchcuradd_rslt.get(0).getTca_id());
				
				tchcadd.setFrmtype("update");
			} else {
				tchcadd.setFrmtype("save");
			}	
			
			return SUCCESS;
		} else {
			return INPUT;
		}
		
	}
	
	public String teacherCurraddSave(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(tchcadd.getSub_city().trim().length() == 0){
				x++;
				addFieldError("tchrcuraddError", "Sub city is blank.");
			}
			if(tchcadd.getKebele().trim().length() == 0){
				x++;
				addFieldError("tchrcuraddError", "Kebele is blank");
			}
			if(tchcadd.getHouse_phone_no().trim().length() == 0 && tchcadd.getMobile_no().trim().length() == 0){
				x++;
				addFieldError("tchrcuraddError", "Mobile or home phone number is mandatory.");
			}
			
			if(x > 0){
				return "CREATEFORM";
			} else {
				boolean rslt = TeacherCuraddDAO.insertTeacherCuradd(tchcadd);
				
				if(rslt){
					tchcadd.setSuccessful_save("true");
					tchcuradd_rslt = TeacherCuraddDAO.getTeachCuradd(tchcadd);
					
					if(tchcuradd_rslt.size() > 0){
						tchcadd.setFrmtype("update");
					} else {
						tchcadd.setFrmtype("save");
					}
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
		
	}
	
	public String teacherCurraddUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(tchcadd.getSub_city().trim().length() == 0){
				x++;
				addFieldError("tchrcuraddError", "Sub city is blank.");
			}
			if(tchcadd.getKebele().trim().length() == 0){
				x++;
				addFieldError("tchrcuraddError", "Kebele is blank");
			}
			if(tchcadd.getHouse_phone_no().trim().length() == 0 && tchcadd.getMobile_no().trim().length() == 0){
				x++;
				addFieldError("tchrcuraddError", "Mobile or home phone number is mandatory.");
			}
			
			if(x > 0){
				return "UPDATEFORM";
			} else {
				boolean rslt = TeacherCuraddDAO.updateTeacherCuradd(tchcadd);
				
				if(rslt){
					tchcadd.setSuccessful_update("true");
					tchcuradd_rslt = TeacherCuraddDAO.getTeachCuradd(tchcadd);
					
					if(tchcuradd_rslt.size() > 0){
						tchcadd.setFrmtype("update");
					} else {
						tchcadd.setFrmtype("save");
					}
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
		
	}
	
	
	
	
	
	
	
	
	
	public String getTca_id() {
		return tca_id;
	}
	public void setTca_id(String tca_id) {
		this.tca_id = tca_id;
	}
	public String getSub_city() {
		return sub_city;
	}
	public void setSub_city(String sub_city) {
		this.sub_city = sub_city;
	}
	public String getKebele() {
		return kebele;
	}
	public void setKebele(String kebele) {
		this.kebele = kebele;
	}
	public String getHouse_no() {
		return house_no;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	public String getHouse_phone_no() {
		return house_phone_no;
	}
	public void setHouse_phone_no(String house_phone_no) {
		this.house_phone_no = house_phone_no;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getIca_status() {
		return ica_status;
	}
	public void setIca_status(String ica_status) {
		this.ica_status = ica_status;
	}
	public String getTi_id() {
		return ti_id;
	}
	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}
	public List<TeacherCuraddBean> getTchcuradd_rslt() {
		return tchcuradd_rslt;
	}
	public void setTchcuradd_rslt(List<TeacherCuraddBean> tchcuradd_rslt) {
		this.tchcuradd_rslt = tchcuradd_rslt;
	}

	@Override
	public TeacherCuraddBean getModel() {
		return tchcadd;
	}

	public String getFrmtype() {
		return frmtype;
	}
	public void setFrmtype(String frmtype) {
		this.frmtype = frmtype;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
}
