package adminClass.classPeriodAssignment;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import studentClass.StudentClassBean;
import studentClass.StudentDAO;
import subjectClass.SubjectBean;
import subjectClass.SubjectDAO;
import util.ReturnCurrentEthiopianYear;

public class ClassPeriodAssignAction extends ActionSupport implements ModelDriven<ClassPeriodAssignBean>, SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> sessionMap;
	ClassPeriodAssignBean cpab = new ClassPeriodAssignBean();
	
	private List<ClassPeriodAssignBean> assignPeriodList;
	private List<StudentClassBean> grade_rslt;
	private List<SubjectBean> subject_list;
	
	private int[] acyear_list;
	
	public String getClassAllotmentTemp(){
		
		if (sessionMap.containsKey("userName")) {
			
			String acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear), Integer.parseInt(acyear) - 1};
			
			assignPeriodList = ClassPeriodAssignDAO.getGeneralPeriondAssignment(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			grade_rslt = StudentDAO.getClassList();
			
			cpab.setAcademic_year(Integer.parseInt(ReturnCurrentEthiopianYear.getCurrentEthiopianYear()));
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	
	public String classSubjectPeriodAllotmentByYear(){
		
		if (sessionMap.containsKey("userName")) {
			
			assignPeriodList = ClassPeriodAssignDAO.getGeneralPeriondAssignment(String.valueOf(cpab.getAcademic_year()));
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	public String getSubjectAllotmentForm(){
		
		if (sessionMap.containsKey("userName")) {
			
			assignPeriodList = ClassPeriodAssignDAO.getSubjectListPerClassByClassId(cpab.getCroomBean().getCl_id());
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	public String saveClassPeriodAllotment(){
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = ClassPeriodAssignDAO.saveClassPeriodAllotment(cpab);
			
			if(rslt){
				addActionMessage("Successfully saved.");
			}
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	public String getClassPeriodAllotmentUpdateForm(){
		
		if (sessionMap.containsKey("userName")) {
			
			cpab.setAcademic_year(Integer.parseInt(ReturnCurrentEthiopianYear.getCurrentEthiopianYear()));
			
			assignPeriodList = ClassPeriodAssignDAO.getClassPeriodAllotmentByCsp_id(cpab.getCsp_id());
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	public String updateClassPeriodAllotment(){
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = ClassPeriodAssignDAO.updateClassPeriodAllotment(cpab);
			
			if(rslt){
				addActionMessage("Successfully updated");
			}
			
			assignPeriodList = ClassPeriodAssignDAO.getGeneralPeriondAssignment(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			grade_rslt = StudentDAO.getClassList();
			
			cpab.setAcademic_year(Integer.parseInt(ReturnCurrentEthiopianYear.getCurrentEthiopianYear()));
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	
	
	

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	@Override
	public ClassPeriodAssignBean getModel() {		
		return cpab;
	}
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<ClassPeriodAssignBean> getAssignPeriodList() {
		return assignPeriodList;
	}

	public void setAssignPeriodList(List<ClassPeriodAssignBean> assignPeriodList) {
		this.assignPeriodList = assignPeriodList;
	}

	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}

	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}

	public List<SubjectBean> getSubject_list() {
		return subject_list;
	}

	public void setSubject_list(List<SubjectBean> subject_list) {
		this.subject_list = subject_list;
	}

	public int[] getAcyear_list() {
		return acyear_list;
	}

	public void setAcyear_list(int[] acyear_list) {
		this.acyear_list = acyear_list;
	}
}
