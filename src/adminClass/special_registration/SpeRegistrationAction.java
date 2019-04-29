package adminClass.special_registration;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import studentClass.StudentBean;
import studentClass.StudentClassBean;
import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import util.ReturnCurrentEthiopianYear;

public class SpeRegistrationAction extends ActionSupport implements ModelDriven<SpeRegistrationBean>, SessionAware {

	private static final long serialVersionUID = -1863217142010852590L;
	SpeRegistrationBean srb = new SpeRegistrationBean();
	private Map<String, Object> sessionMap;
	
	private List<StudentClassBean> grade_rslt;
	private List<StudentClassDetailBean> class_detail;
	private List<StudentBean> stud_rslt;
	private List<StudentBean> registered_stud_rslt;
	
	public String specialRegistrationTemp(){
		if(sessionMap.containsKey("userName")){
			grade_rslt = StudentDAO.getClassList();
			return SUCCESS;
		} else{
			return INPUT;
		}
	}
	
	public String studentPerGrade() {
		if (sessionMap.containsKey("userName")) {			
						
			class_detail = StudentDAO.getClassDetail(srb.getClass_bean().getCl_id(), (String) sessionMap.get("userName"));
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentPerGradeDetail() {
		if (sessionMap.containsKey("userName")) {
			
			stud_rslt = SpeRegistrationDAO.getListPerGradeDetail(srb.getClass_bean().getCl_id(), srb.getCdetail_bean().getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			registered_stud_rslt = SpeRegistrationDAO.getRegisteredListPerGradeDetail(srb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String regAddSelectedStudent(){
		if (sessionMap.containsKey("userName")) {
			
			SpeRegistrationDAO.speAddSelectedStudent(srb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String regRemoveSelectedStudent(){
		if (sessionMap.containsKey("userName")) {
			
			SpeRegistrationDAO.speRemoveSelectedStudent(srb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String speRegisterSelectedStudent(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = SpeRegistrationDAO.speRegisterSelectedStudent();
			if(rslt){
				addActionMessage("Students are successfully registered.");
			} else {
				addActionError("There are some students left unregistered. Please check you list again.\nOR\nPlease select students to register.");
			}
			
			stud_rslt = SpeRegistrationDAO.getListPerGradeDetail(srb.getClass_bean().getCl_id(), srb.getCdetail_bean().getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			registered_stud_rslt = SpeRegistrationDAO.getRegisteredListPerGradeDetail(srb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	public String regAddRegisteredStudent(){
		if (sessionMap.containsKey("userName")) {
			
			SpeRegistrationDAO.speAddRegisteredStudent(srb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String regRemoveSelectedRegisteredStudent(){
		if (sessionMap.containsKey("userName")) {
			
			SpeRegistrationDAO.speRemoveSelectedRegisteredStudent(srb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String speRemoveStudentSpecialRegistration(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = SpeRegistrationDAO.speRemoveStudentSpecialRegistration();
			if(rslt){
				addActionMessage("Students are successfully removed.");
			} else {
				addActionError("There are some students left unremoved. Please check you list again.\nOR\nPlease select students to register.");
			}
			
			stud_rslt = SpeRegistrationDAO.getListPerGradeDetail(srb.getClass_bean().getCl_id(), srb.getCdetail_bean().getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			registered_stud_rslt = SpeRegistrationDAO.getRegisteredListPerGradeDetail(srb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	@Override
	public SpeRegistrationBean getModel() {
		return srb;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}


	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}


	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}

	public List<StudentClassDetailBean> getClass_detail() {
		return class_detail;
	}

	public void setClass_detail(List<StudentClassDetailBean> class_detail) {
		this.class_detail = class_detail;
	}

	public List<StudentBean> getStud_rslt() {
		return stud_rslt;
	}

	public void setStud_rslt(List<StudentBean> stud_rslt) {
		this.stud_rslt = stud_rslt;
	}

	public List<StudentBean> getRegistered_stud_rslt() {
		return registered_stud_rslt;
	}

	public void setRegistered_stud_rslt(List<StudentBean> registered_stud_rslt) {
		this.registered_stud_rslt = registered_stud_rslt;
	}

}
