package adminClass.curriculum_activity;

public class CurriculumAct_Bean {

	// >>> cca_department table object
	private String dep_id;
	private String dep_name;
	private String dep_desc;
	private String dep_status;

	// >>> cca_club table object
	private String clb_id;
	private String clb_name;
	private String clb_desc;
	private String clb_status;

	// >>> cca_teacher_responsibility table object
	private String tr_id;
	private String ti_id;
	private String role_id;
	private String responsibility_id;
	private String academic_year;
	private String tr_status;
	//???
	private String tr_name;

	// >>> cca_role table object
	private String role_name;

	// additional object
	private String dep_or_club;

	public String getTr_id() {
		return tr_id;
	}

	public void setTr_id(String tr_id) {
		this.tr_id = tr_id;
	}

	public String getTi_id() {
		return ti_id;
	}

	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getResponsibility_id() {
		return responsibility_id;
	}

	public void setResponsibility_id(String responsibility_id) {
		this.responsibility_id = responsibility_id;
	}

	public String getAcademic_year() {
		return academic_year;
	}

	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
	}

	public String getTr_status() {
		return tr_status;
	}

	public void setTr_status(String tr_status) {
		this.tr_status = tr_status;
	}

	public String getClb_id() {
		return clb_id;
	}

	public void setClb_id(String clb_id) {
		this.clb_id = clb_id;
	}

	public String getClb_name() {
		return clb_name;
	}

	public void setClb_name(String clb_name) {
		this.clb_name = clb_name;
	}

	public String getClb_desc() {
		return clb_desc;
	}

	public void setClb_desc(String clb_desc) {
		this.clb_desc = clb_desc;
	}

	public String getClb_status() {
		return clb_status;
	}

	public void setClb_status(String clb_status) {
		this.clb_status = clb_status;
	}

	public String getDep_id() {
		return dep_id;
	}

	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getDep_desc() {
		return dep_desc;
	}

	public void setDep_desc(String dep_desc) {
		this.dep_desc = dep_desc;
	}

	public String getDep_status() {
		return dep_status;
	}

	public void setDep_status(String dep_status) {
		this.dep_status = dep_status;
	}

	public String getTr_name() {
		return tr_name;
	}

	public void setTr_name(String tr_name) {
		this.tr_name = tr_name;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getDep_or_club() {
		return dep_or_club;
	}

	public void setDep_or_club(String dep_or_club) {
		this.dep_or_club = dep_or_club;
	}

}
