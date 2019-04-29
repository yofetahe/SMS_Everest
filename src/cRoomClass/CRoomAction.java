package cRoomClass;


import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import subjectClass.SubjectBean;
import subjectClass.SubjectDAO;
import util.RoleValidator;

import adminClass.AdminBean;
import adminClass.AdminDAO;
import cRoomClass.cd_registration.CDetailBean;
import cRoomClass.cd_registration.CDetailDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import examClass.exam_subject_rel.ExamSubjectRelBean;
import examClass.exam_subject_rel.ExamSubjectRelDAO;
import examClass.exam_type.ExamBean;
import examClass.exam_type.ExamDAO;

public class CRoomAction extends ActionSupport implements ModelDriven<CRoomBean>, SessionAware {
	
	private static final long serialVersionUID = -2067115910166606505L;
	private String cl_id;
	private String class_name;
	private String class_status;
	private String ti_id;
	
	private String subcl_id;
	private String sub_id;
	private String sub_name;
	private String rel_status;
	private String total_mark;
	private String pass_mark;
	private String exsub_id;
	
	private List<CRoomBean> class_rslt;
	private String menutype = "class";
	private List<CDetailBean> cd_rslt;
	private List<CRoomBean> classsub_rslt;
	private List<SubjectBean> subject_rslt;
	private List<ExamSubjectRelBean> examtype_subrel_rslt;
	private List<ExamBean> examtypelist_rslt;
	private List<AdminBean> usrRoleList;
	private Map<String, Object> sessionMap;
	
	CRoomBean crm = new CRoomBean();
	
	public String classList(){
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Class";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
		
			cd_rslt = CDetailDAO.getCDetailList();
			class_rslt = CRoomDAO.getClassList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String classSaveFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String classSave(){
		if (sessionMap.containsKey("userName")) {
			
			int x = 0;
			
			if(crm.getClass_name().trim().length() == 0){
				x++;
				addFieldError("classError", "Class name is blank.");
			}
			if(CRoomDAO.checkClassExistance(crm)){
				addFieldError("classError", "The class already exist.");
				return ERROR;
			}
			
			if(x > 0){
				return "CREATEFORM";
			} else {
				boolean rslt = CRoomDAO.insertClass(crm);
				if(rslt){
					class_rslt = CRoomDAO.getClassList();
					return SUCCESS;
				} else {
					addFieldError("classError", "It is not saved. Please try again later.");
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}		
		
	}
	
	public String classUpdateFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String classUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(crm.getClass_name().trim().length() == 0){
				x++;
				addFieldError("classError", "Class name is blank.");
			}
			if(crm.getClass_status().equalsIgnoreCase("0")){
				x++;
				addFieldError("classError", "Select the status");
			}
			
			if(x > 0){
				return "UPDATEFORM";
			} else {
				boolean rslt = CRoomDAO.updateClass(crm);
				if(rslt){
					class_rslt = CRoomDAO.getClassList();
					return SUCCESS;
				} else {
					addFieldError("classError", "It is not updated. Please try again later.");
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
		
		
	}
	
	public String classSubjectRel(){
		if (sessionMap.containsKey("userName")) {
			classsub_rslt = CRoomDAO.getClassSubList(crm);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String classSubjectRelCrtFrm(){
		if (sessionMap.containsKey("userName")) {
			subject_rslt = SubjectDAO.getUnselectedSubList(crm);
			return SUCCESS;
		} else {
			return INPUT;
		}
		
	}
	
	public String classSubjectRelCrt(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(crm.getSub_id().equalsIgnoreCase("0")){
				x++;
				addFieldError("clSubRelError", "Please select the subject.");
			}
			if(CRoomDAO.checkRelationExistance(crm)){
				x++;
				addFieldError("clSubRelError", "The class-subject relation already created.");
			}
			
			if(x > 0){
				subject_rslt = SubjectDAO.getUnselectedSubList(crm);
				return "CREATEFORM";
			} else {
				boolean rslt = CRoomDAO.insertClSubRel(crm);
				if(rslt){
					classsub_rslt = CRoomDAO.getClassSubList(crm);
					return SUCCESS;
				} else {
					subject_rslt = SubjectDAO.getUnselectedSubList(crm);
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}		
	}
	
	public String classSubjectRelEditFrm(){		
		if (sessionMap.containsKey("userName")) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String classSubjectRelEdit(){
		
		if (sessionMap.containsKey("userName")) {
			
//			int x = 0;
//			
//			if(crm.getRel_status().equalsIgnoreCase("0")){
//				x++;
//				addFieldError("clSubRelError", "Please select the status.");
//			}
//			
//			if(x > 0){			
//				return "UPDATEFORM";
//			} else {
				boolean rslt = CRoomDAO.updateClSubRel(crm);
				if(rslt){
					classsub_rslt = CRoomDAO.getClassSubList(crm);
					return SUCCESS;
				} else {
					subject_rslt = SubjectDAO.getUnselectedSubList(crm);
					return ERROR;
				}
			//}
		} else {
			return INPUT;
		}		
	}
	
	public String clSubExmRel(){
		if (sessionMap.containsKey("userName")) {
			examtype_subrel_rslt = ExamSubjectRelDAO.getExamSubjectRel(crm);
			return SUCCESS;
		} else {
			return INPUT;
		}
		
	}
	
	public String clSubExmRelCrtFrm(){
		if (sessionMap.containsKey("userName")) {
			examtypelist_rslt = ExamDAO.getUnselectedExamTypeList(crm);
			return SUCCESS;
		} else {
			return INPUT;
		}
		
	}
	
	public String clSubExmRelEditFrm(){
		if (sessionMap.containsKey("userName")) {
			return SUCCESS;
		} else {
			return INPUT;
		}
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
	public String getClass_status() {
		return class_status;
	}
	public void setClass_status(String class_status) {
		this.class_status = class_status;
	}
	public String getTi_id() {
		return ti_id;
	}
	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}
	public String getMenutype() {
		return menutype;
	}
	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}
	@Override
	public CRoomBean getModel() {
		return crm;
	}
	public List<CRoomBean> getClass_rslt() {
		return class_rslt;
	}
	public void setClass_rslt(List<CRoomBean> class_rslt) {
		this.class_rslt = class_rslt;
	}

	public List<CDetailBean> getCd_rslt() {
		return cd_rslt;
	}

	public void setCd_rslt(List<CDetailBean> cd_rslt) {
		this.cd_rslt = cd_rslt;
	}

	public List<CRoomBean> getClasssub_rslt() {
		return classsub_rslt;
	}

	public void setClasssub_rslt(List<CRoomBean> classsub_rslt) {
		this.classsub_rslt = classsub_rslt;
	}

	public String getSubcl_id() {
		return subcl_id;
	}

	public void setSubcl_id(String subcl_id) {
		this.subcl_id = subcl_id;
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

	public String getRel_status() {
		return rel_status;
	}

	public void setRel_status(String rel_status) {
		this.rel_status = rel_status;
	}

	public List<SubjectBean> getSubject_rslt() {
		return subject_rslt;
	}

	public void setSubject_rslt(List<SubjectBean> subject_rslt) {
		this.subject_rslt = subject_rslt;
	}

	public List<ExamSubjectRelBean> getExamtype_subrel_rslt() {
		return examtype_subrel_rslt;
	}

	public void setExamtype_subrel_rslt(List<ExamSubjectRelBean> examtype_subrel_rslt) {
		this.examtype_subrel_rslt = examtype_subrel_rslt;
	}

	public List<ExamBean> getExamtypelist_rslt() {
		return examtypelist_rslt;
	}

	public void setExamtypelist_rslt(List<ExamBean> examtypelist_rslt) {
		this.examtypelist_rslt = examtypelist_rslt;
	}

	public String getPass_mark() {
		return pass_mark;
	}

	public void setPass_mark(String pass_mark) {
		this.pass_mark = pass_mark;
	}

	public String getExsub_id() {
		return exsub_id;
	}

	public void setExsub_id(String exsub_id) {
		this.exsub_id = exsub_id;
	}

	public String getTotal_mark() {
		return total_mark;
	}

	public void setTotal_mark(String total_mark) {
		this.total_mark = total_mark;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap(sessionMap);
	}

	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}

	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
}
