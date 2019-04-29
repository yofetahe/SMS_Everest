package teacherClass.assignment;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import subjectClass.SubjectBean;

import cRoomClass.CRoomBean;
import cRoomClass.CRoomDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TeacherAssignAction extends ActionSupport implements ModelDriven<TeacherAssignBean>, SessionAware {

	private static final long serialVersionUID = -2893972063212343249L;
	private String ta_id;
	private String sub_id;
	private String sub_name;
	private String cl_id;
	private String cl_name;
	private String year;
	private String ta_status;
	private String ti_id;
	
	private List<TeacherAssignBean> assign_rslt;
	private List<SubjectBean> subject_rslt;
	private List<CRoomBean> clroom_rslt;
	private List<TeacherAssignBean> subjectList;
	private Map<String, Object> sessionMap;
		
	TeacherAssignBean assign = new TeacherAssignBean();
	
	public String tchrAssignmentList(){
		if (sessionMap.containsKey("userName")) {
			assign_rslt = TeacherAssignDAO.getListAssignmentList(assign);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String tchrAssignmentSaveFrm(){
		if (sessionMap.containsKey("userName")) {
			subjectList = TeacherAssignDAO.getSubjectListPerGrade(assign);
			clroom_rslt = CRoomDAO.getActiveClass();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String subjectListPerGrade(){
		if (sessionMap.containsKey("userName")) {
			subjectList = TeacherAssignDAO.getSubjectListPerGrade(assign);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String tchrAssignmentSave(){
		
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(assign.getSub_id().equalsIgnoreCase("0")){
				x++;
				addFieldError("assignError", "Please select subject.");
			}
			if(assign.getCl_id().equalsIgnoreCase("0")){
				x++;
				addFieldError("assignError", "Please select class from");
			}
			if(TeacherAssignDAO.checkTeacherSubjectRel(assign)){
				x++;
				addFieldError("assignError", "The relation already created.");
			}
			
			if(x > 0){
				subjectList = TeacherAssignDAO.getSubjectListPerGrade(assign);
				clroom_rslt = CRoomDAO.getActiveClass();
				return ERROR;
			} else {
				boolean rslt = TeacherAssignDAO.insertTeacherAssign(assign);
				if(rslt){
					assign_rslt = TeacherAssignDAO.getListAssignmentList(assign);
					return SUCCESS;
				} else {
					subjectList = TeacherAssignDAO.getSubjectListPerGrade(assign);
					clroom_rslt = CRoomDAO.getActiveClass();
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
		
	}
	
	public String tchrAssignmentUpdateFrm(){
		if (sessionMap.containsKey("userName")) {
			subjectList = TeacherAssignDAO.getSubjectListPerGrade(assign);
			clroom_rslt = CRoomDAO.getActiveClass();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String tchrAssignmentUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(assign.getSub_id().equalsIgnoreCase("0")){
				x++;
				addFieldError("assignError", "Please select subject.");
			}
			if(assign.getCl_id().equalsIgnoreCase("0")){
				x++;
				addFieldError("assignError", "Please select class from");
			}
			
			if(x > 0){
				subjectList = TeacherAssignDAO.getSubjectListPerGrade(assign);
				clroom_rslt = CRoomDAO.getActiveClass();
				return ERROR;
			} else {
				boolean rslt = TeacherAssignDAO.updateTeacherAssign(assign);
				if(rslt){
					assign_rslt = TeacherAssignDAO.getListAssignmentList(assign);
					return SUCCESS;
				} else {
					addFieldError("assignError", "It is not successfully updated. Please try again later.");
					subjectList = TeacherAssignDAO.getSubjectListPerGrade(assign);
					clroom_rslt = CRoomDAO.getActiveClass();
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}		
		
	}
	
	
	
	
	
	
	
	
	public String getTa_id() {
		return ta_id;
	}
	public void setTa_id(String ta_id) {
		this.ta_id = ta_id;
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public TeacherAssignBean getModel() {
		return assign;
	}
	public List<TeacherAssignBean> getAssign_rslt() {
		return assign_rslt;
	}
	public void setAssign_rslt(List<TeacherAssignBean> assign_rslt) {
		this.assign_rslt = assign_rslt;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public String getTi_id() {
		return ti_id;
	}
	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}
	public List<SubjectBean> getSubject_rslt() {
		return subject_rslt;
	}
	public void setSubject_rslt(List<SubjectBean> subject_rslt) {
		this.subject_rslt = subject_rslt;
	}
	public List<CRoomBean> getClroom_rslt() {
		return clroom_rslt;
	}
	public void setClroom_rslt(List<CRoomBean> clroom_rslt) {
		this.clroom_rslt = clroom_rslt;
	}
	public String getTa_status() {
		return ta_status;
	}
	public void setTa_status(String ta_status) {
		this.ta_status = ta_status;
	}

	public String getCl_id() {
		return cl_id;
	}

	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}

	public String getCl_name() {
		return cl_name;
	}

	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}

	public List<TeacherAssignBean> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<TeacherAssignBean> subjectList) {
		this.subjectList = subjectList;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
}
