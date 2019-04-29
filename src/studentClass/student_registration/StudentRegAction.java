package studentClass.student_registration;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import studentClass.StudentDAO;
import util.ReturnCurrentEthiopianYear;
import util.TodayDate_YYYYMMDD;

public class StudentRegAction extends ActionSupport implements ModelDriven<StudentRegBean>, SessionAware{	

	private static final long serialVersionUID = -1519155795439938988L;
	private String si_id;
	private String stud_name;
	private String clcd_id;
	private String cl_id;
	private String cl_name;
	private String selstud_id;
	private String selstud_name;
	private String selcl_id;
	private String inde;
	private String sec_id;
	private String sec_name;
	private List<StudentRegBean> studList;
	private List<StudentRegBean> selStud_rslt;
	private List<StudentRegBean> section_list;
	private List<StudentRegBean> clcdid_rslt;
	private Map<String, Object> sessionMap;
	
	StudentRegBean studreg = new StudentRegBean();
	
	public String execute(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentList(){
		
		if (sessionMap.containsKey("userName")) {
			
			StudentRegDAO.clearSelStudList();
			
			studList = StudentRegDAO.getStudList(studreg.getCl_id(), studreg.getAcademic_year());
			
			return SUCCESS;
		} else {
			return INPUT;
		}			
	}
	
	
	
	public String selectedStudentListForRegistration(){
	
		if (sessionMap.containsKey("userName")) {
			
			StudentRegDAO.clearSelStudList();
			
			section_list = StudentRegDAO.getSectionList(studreg.getSelcl_id());
							
			studreg.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			selStud_rslt = StudentRegDAO.getSelectedStudentListForRegistration(studreg.getStudentListForRegistration());
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	///*** This is the place where to limit the max month for student registration
	///    of the current year. ***///
	public String selStudentList(){
		
		if (sessionMap.containsKey("userName")) {
			
			StudentRegDAO.clearSelStudList();
			
			section_list = StudentRegDAO.getSectionList(studreg.getSelcl_id());
							
			studreg.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			selStud_rslt = StudentRegDAO.selStudList(studreg.getSi_id(), studreg.getStud_name(), studreg.getCl_id());
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	} 
	
	public String revmoveStudentList(){
		if (sessionMap.containsKey("userName")) {
			section_list = StudentRegDAO.getSectionList(studreg.getSelcl_id());
			studreg.setAcademic_year(section_list.get(0).getAcademic_year());
			selStud_rslt = StudentRegDAO.removeStudList(studreg.getInde());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String regStudentList(){
		
		if (sessionMap.containsKey("userName")) {
			
			clcdid_rslt = StudentRegDAO.getClCdId(studreg.getSec_id(), studreg.getSelcl_id());
			
			int stud_count = StudentDAO.countListPerGradeDetail(studreg.getClcd_id(), studreg.getAcademic_year());
			
			Boolean regStud_rslt = StudentRegDAO.registerStudList(clcdid_rslt.get(0).getClcd_id(), studreg, stud_count, Integer.parseInt(clcdid_rslt.get(0).getCrdetailBean().getCl_capacity()));
			
			if(regStud_rslt){		
				return SUCCESS;
			} else {			
				return ERROR;
			}
		} else {
			
			return INPUT;
		}
	}
	
	/*
	 * >>> this method must check students registered mark
	 * >>> then change the class room
	 */
	public String changeStudentClassroom(){
		if (sessionMap.containsKey("userName")) {
			studreg.setClcd_id(StudentRegDAO.getClCdId(studreg.getCd_id(), studreg.getCl_id()).get(0).getClcd_id());
			boolean rslt = StudentRegDAO.changeStudentCurrentClassroom(studreg);
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	
	
	public String getSi_id() {
		return si_id;
	}
	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}
	public String getClcd_id() {
		return clcd_id;
	}
	public void setClcd_id(String clcd_id) {
		this.clcd_id = clcd_id;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	@Override
	public StudentRegBean getModel() {
		return studreg;
	}
	public List<StudentRegBean> getStudList() {
		return studList;
	}
	public void setStudList(List<StudentRegBean> studList) {
		this.studList = studList;
	}

	public String getStud_name() {
		return stud_name;
	}

	public void setStud_name(String stud_name) {
		this.stud_name = stud_name;
	}

	public String getCl_name() {
		return cl_name;
	}

	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}

	public List<StudentRegBean> getSelStud_rslt() {
		return selStud_rslt;
	}

	public void setSelStud_rslt(List<StudentRegBean> selStud_rslt) {
		this.selStud_rslt = selStud_rslt;
	}

	public String getSelstud_id() {
		return selstud_id;
	}

	public void setSelstud_id(String selstud_id) {
		this.selstud_id = selstud_id;
	}

	public String getSelstud_name() {
		return selstud_name;
	}

	public void setSelstud_name(String selstud_name) {
		this.selstud_name = selstud_name;
	}

	public String getSelcl_id() {
		return selcl_id;
	}

	public void setSelcl_id(String selcl_id) {
		this.selcl_id = selcl_id;
	}

	public String getInde() {
		return inde;
	}

	public void setInde(String inde) {
		this.inde = inde;
	}

	public List<StudentRegBean> getSection_list() {
		return section_list;
	}

	public void setSection_list(List<StudentRegBean> section_list) {
		this.section_list = section_list;
	}

	public String getSec_id() {
		return sec_id;
	}

	public void setSec_id(String sec_id) {
		this.sec_id = sec_id;
	}

	public String getSec_name() {
		return sec_name;
	}

	public void setSec_name(String sec_name) {
		this.sec_name = sec_name;
	}

	public List<StudentRegBean> getClcdid_rslt() {
		return clcdid_rslt;
	}

	public void setClcdid_rslt(List<StudentRegBean> clcdid_rslt) {
		this.clcdid_rslt = clcdid_rslt;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}
