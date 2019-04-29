package cRoomClass.cd_registration;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CDetailAction extends ActionSupport implements ModelDriven<CDetailBean>, SessionAware {
	
	private static final long serialVersionUID = 6555151566157808041L;
	private String cd_id;
	private String cd_name;
	private String cd_status;
	
	private List<CDetailBean> cd_rslt;
	private Map<String, Object> sessionMap;
	CDetailBean cd = new CDetailBean();
	
	public String cDeatilUpdateFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String cDeatilEdit(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(cd.getCd_name().trim().length() == 0){
				x++;
				addFieldError("cDtlError", "Class detail name is blank.");
			}
			if(cd.getCd_status().equalsIgnoreCase("0")){
				x++;
				addFieldError("cDtlError", "Please select the status");
			}
			
			if(x > 0){
				return "CREATEFORM";
			} else {
				
				boolean rslt = CDetailDAO.updateCDetail(cd);
				if(rslt){
					cd_rslt = CDetailDAO.getCDetailList();
					return SUCCESS;
				} else {
					addFieldError("cDtlError", "It is not updated. Please try again.");
					return ERROR;
				}
				
			}
		} else {
			return INPUT;
		}
		
	}
	
	public String cDeatilSaveFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String cDeatilSave(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(cd.getCd_name().trim().length() == 0){
				x++;
				addFieldError("cDtlError", "Class detail name is blank.");
			}
			
			if(x > 0){
				return "UPDATEFORM";
			} else {
				
				boolean rslt = CDetailDAO.saveCDetail(cd);
				if(rslt){
					cd_rslt = CDetailDAO.getCDetailList();
					return SUCCESS;
				} else {
					addFieldError("cDtlError", "It is not updated. Please try again.");
					return ERROR;
				}
				
			}
		} else {
			return INPUT;
		}
		
	}
	
	
	
	
	
	
	
	
	
	public String getCd_id() {
		return cd_id;
	}
	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}
	public String getCd_name() {
		return cd_name;
	}
	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}
	public String getCd_status() {
		return cd_status;
	}
	public void setCd_status(String cd_status) {
		this.cd_status = cd_status;
	}
	@Override
	public CDetailBean getModel() {
		return cd;
	}
	public List<CDetailBean> getCd_rslt() {
		return cd_rslt;
	}
	public void setCd_rslt(List<CDetailBean> cd_rslt) {
		this.cd_rslt = cd_rslt;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}
