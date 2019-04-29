package examClass.exam_type;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ExamAction extends ActionSupport implements ModelDriven<ExamBean>, SessionAware{
	
	private static final long serialVersionUID = 7873127086183246668L;
	private String et_id;
	private String et_name;
	private String et_status;
	private String exsub_id;
	
	private List<ExamBean> examtype_rslt;
	private Map<String, Object> sessionMap;
	
	ExamBean ex = new ExamBean();
	
	public String examtypeCreateFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String examtypeSave(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(ex.getEt_name().trim().length() == 0){
				x++;
				addFieldError("etypeError", "Exam type name is blank.");
			}
			
			if(x > 0){
				return "CREATEFORM";
			} else {
				boolean rslt = ExamDAO.insertExamType(ex);
				if(rslt){
					examtype_rslt = ExamDAO.getExamTypeList();
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
				
	}
	
	public String examtypeEditFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String examtypeUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(ex.getEt_name().trim().length() == 0){
				x++;
				addFieldError("etypeError", "Exam type name is blank.");
			}
			if(ex.getEt_status().equalsIgnoreCase("0")){
				x++;
				addFieldError("etypeError", "Select the status");
			}
			
			if(x > 0){
				return "UPDATEFORM";
			} else {
				boolean rslt = ExamDAO.updateExamType(ex);			
				if(rslt){
					examtype_rslt = ExamDAO.getExamTypeList();
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
		
	}
	
	
	
	
	
	
	
	public String getEt_id() {
		return et_id;
	}
	public void setEt_id(String et_id) {
		this.et_id = et_id;
	}
	public String getEt_name() {
		return et_name;
	}
	public void setEt_name(String et_name) {
		this.et_name = et_name;
	}
	public String getEt_status() {
		return et_status;
	}
	public void setEt_status(String et_status) {
		this.et_status = et_status;
	}
	@Override
	public ExamBean getModel() {
		return ex;
	}

	public List<ExamBean> getExamtype_rslt() {
		return examtype_rslt;
	}

	public void setExamtype_rslt(List<ExamBean> examtype_rslt) {
		this.examtype_rslt = examtype_rslt;
	}

	public String getExsub_id() {
		return exsub_id;
	}

	public void setExsub_id(String exsub_id) {
		this.exsub_id = exsub_id;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}
