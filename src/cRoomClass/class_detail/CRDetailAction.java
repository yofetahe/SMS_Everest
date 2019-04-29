package cRoomClass.class_detail;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CRDetailAction extends ActionSupport implements ModelDriven<CRDetailBean>, SessionAware {
	
	private static final long serialVersionUID = 3791418635693613543L;	
	private String cd_id;
	private String cd_name;
	private String cd_status;
	private String clcd_id;
	private String rel_status;
	private String cl_capacity;
	private String cl_id;
	private String class_name;
	private List<CRDetailBean> cld_rslt;
	private List<CRDetailBean> cdetaillist;
	private Map<String, Object> sessionMap;
	
	CRDetailBean crd = new CRDetailBean();
	
	public String classDtlList(){
		if (sessionMap.containsKey("userName")) {
			cld_rslt = CRDetailDAO.getClassDetailList(crd);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String classDtlAddFrm(){		
		if (sessionMap.containsKey("userName")) {
			cdetaillist = CRDetailDAO.getAllClassDetail(crd);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String classDtlSave(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(crd.getCd_id().equalsIgnoreCase("0")){
				x++;
				addFieldError("classDtlError", "Please select class detail");
			}
			if(crd.getCl_capacity().trim().length() == 0){
				x++;
				addFieldError("classDtlError", "Capacity is blank.");
			}
			
			if(x > 0){
				cdetaillist = CRDetailDAO.getAllClassDetail(crd);
				return "CREATEFORM";			
			} else {
				boolean checkRslt = CRDetailDAO.checkClassDetail(crd);
				if(checkRslt){
					addFieldError("classDtlError", "It is previously recoded.");
					cdetaillist = CRDetailDAO.getAllClassDetail(crd);
					return INPUT;
				} else {			
					boolean rslt = CRDetailDAO.insertClassDetail(crd);
					if(rslt){
						cld_rslt = CRDetailDAO.getClassDetailList(crd);
						return SUCCESS;
					} else {
						addFieldError("classDtlError", "It is not saved. Please try again later.");
						cdetaillist = CRDetailDAO.getAllClassDetail(crd);
						return ERROR;
					}
				}
			}
		} else {
			return INPUT;
		}
				
	}
	
	public String classDtlUpdateFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String classDtlUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(crd.getRel_status().equalsIgnoreCase("0")){
				x++;
				addFieldError("classDtlError", "Please select the status");
			}
			if(crd.getCl_capacity().trim().length() == 0){
				x++;
				addFieldError("classDtlError", "Capacity is blank.");
			}
			
			if(x > 0){
				cdetaillist = CRDetailDAO.getAllClassDetail(crd);
				return "UPDATEFORM";			
			} else {
							
				boolean rslt = CRDetailDAO.updateClassDetail(crd);
				if(rslt){
					cld_rslt = CRDetailDAO.getClassDetailList(crd);
					return SUCCESS;
				} else {
					addFieldError("classDtlError", "It is not saved. Please try again later.");
					cdetaillist = CRDetailDAO.getAllClassDetail(crd);
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
	public CRDetailBean getModel() {
		return crd;
	}
	public List<CRDetailBean> getCld_rslt() {
		return cld_rslt;
	}
	public void setCld_rslt(List<CRDetailBean> cld_rslt) {
		this.cld_rslt = cld_rslt;
	}





	public String getCl_id() {
		return cl_id;
	}





	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}





	public String getClass_name() {
		return class_name;
	}





	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}





	public String getClcd_id() {
		return clcd_id;
	}





	public void setClcd_id(String clcd_id) {
		this.clcd_id = clcd_id;
	}





	public String getRel_status() {
		return rel_status;
	}





	public void setRel_status(String rel_status) {
		this.rel_status = rel_status;
	}





	public String getCl_capacity() {
		return cl_capacity;
	}





	public void setCl_capacity(String cl_capacity) {
		this.cl_capacity = cl_capacity;
	}

	public List<CRDetailBean> getCdetaillist() {
		return cdetaillist;
	}

	public void setCdetaillist(List<CRDetailBean> cdetaillist) {
		this.cdetaillist = cdetaillist;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	

}
