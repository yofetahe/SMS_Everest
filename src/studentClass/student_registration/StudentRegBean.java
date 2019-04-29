package studentClass.student_registration;

import java.util.List;

import cRoomClass.class_detail.CRDetailBean;

public class StudentRegBean {
	
	//stud_registration table object
	//private int sr_id;
	private String clcd_id;
	private String si_id;
	private String academic_year;
	private String regStudStatus;

	//class_list table object
	private String cl_id;
	private String cl_name;

	//class_detail table object
	private String cd_id;
	
	private String sec_id;
	private String sec_name;
	
	//additional object needed
	private String stud_name;
	private String selstud_id;
	private String selstud_name;
	private String selcl_id;
	private String inde;
	
	private CRDetailBean crdetailBean;
	
	private List<Object> studentListForRegistration;
	
	private List<Object> removeStudentFromRegistrationList;
	
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
	public String getAcademic_year() {
		return academic_year;
	}
	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
	}
	public String getRegStudStatus() {
		return regStudStatus;
	}
	public void setRegStudStatus(String regStudStatus) {
		this.regStudStatus = regStudStatus;
	}
	public String getCd_id() {
		return cd_id;
	}
	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}
	public CRDetailBean getCrdetailBean() {
		return crdetailBean;
	}
	public void setCrdetailBean(CRDetailBean crdetailBean) {
		this.crdetailBean = crdetailBean;
	}
	public List<Object> getStudentListForRegistration() {
		return studentListForRegistration;
	}
	public void setStudentListForRegistration(List<Object> studentListForRegistration) {
		this.studentListForRegistration = studentListForRegistration;
	}
	public List<Object> getRemoveStudentFromRegistrationList() {
		return removeStudentFromRegistrationList;
	}
	public void setRemoveStudentFromRegistrationList(List<Object> removeStudentFromRegistrationList) {
		this.removeStudentFromRegistrationList = removeStudentFromRegistrationList;
	}	

}
