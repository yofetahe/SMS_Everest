package subjectClass;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.RoleValidator;

import adminClass.AdminBean;
import adminClass.AdminDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import examClass.exam_type.ExamBean;
import examClass.exam_type.ExamDAO;

public class SubjectAction extends ActionSupport implements ModelDriven<SubjectBean>, SessionAware {

	private static final long serialVersionUID = 5690847066126875173L;
	private String sub_id;
	private String sub_name;
	private String sub_status;
	private String ti_id;
	private String menutype = "subject";
	private String cl_id;
	private String subcl_id;
	
	private List<SubjectBean> subject_rslt;
	private List<ExamBean> examtype_rslt;
	private List<AdminBean> usrRoleList;
	private Map<String, Object> sessionMap;
	
	SubjectBean sub = new SubjectBean();
	
	public String subjectList(){
		
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Subject and Exam";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
		
			subject_rslt = SubjectDAO.getSubjectList();
			examtype_rslt = ExamDAO.getExamTypeList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String subjectEditFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	} 
	
	public String subjectSaveFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String subjectSave(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(sub.getSub_name().trim().length() == 0){
				x++;
				addFieldError("subError", "Subject name is blank.");
			}
			
			if(SubjectDAO.checkSubjectExistanceBeforeSave(sub)){
				x++;
				addFieldError("subError", "Subject name is already registered.");
			}
			
			if(x > 0){
				return "CREATEFORM";
			} else {
				boolean rslt = SubjectDAO.insertSubject(sub);
				if(rslt){
					subject_rslt = SubjectDAO.getSubjectList();
					return SUCCESS;
				} else {
					addFieldError("subError", "It is not saved. Please try later.");
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}		
	}
	
	public String subjectUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(sub.getSub_name().trim().length() == 0){
				x++;
				addFieldError("subError", "Subject name is blank.");
			}
			if(sub.getSub_status().equalsIgnoreCase("0")){
				x++;
				addFieldError("subError", "Please select the status.");
			}
			if(SubjectDAO.checkSubjectExistanceBeforeUpdate(sub)){
				x++; System.out.println(x + " x");
				addFieldError("subError", "Subject name is already registered.");
			}
			
			if(x > 0){
				return "UPDATEFORM";
			} else {
				boolean rslt = SubjectDAO.updateSubject(sub);
				if(rslt){
					subject_rslt = SubjectDAO.getSubjectList();
					return SUCCESS;
				} else {
					addFieldError("subError", "It is not updated. Please try later.");
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
	}
	
	public String examTypeList(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	
	
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public String getSub_status() {
		return sub_status;
	}
	public void setSub_status(String sub_status) {
		this.sub_status = sub_status;
	}
	public String getTi_id() {
		return ti_id;
	}
	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}
	@Override
	public SubjectBean getModel() {
		return sub;
	}
	public List<SubjectBean> getSubject_rslt() {
		return subject_rslt;
	}
	public void setSubject_rslt(List<SubjectBean> subject_rslt) {
		this.subject_rslt = subject_rslt;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public List<ExamBean> getExamtype_rslt() {
		return examtype_rslt;
	}

	public void setExamtype_rslt(List<ExamBean> examtype_rslt) {
		this.examtype_rslt = examtype_rslt;
	}

	public String getCl_id() {
		return cl_id;
	}

	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}

	public String getSubcl_id() {
		return subcl_id;
	}

	public void setSubcl_id(String subcl_id) {
		this.subcl_id = subcl_id;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap(sessionMap);		
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}

	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}

}
