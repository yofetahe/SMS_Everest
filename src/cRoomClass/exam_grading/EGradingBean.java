package cRoomClass.exam_grading;

public class EGradingBean {
	
	//>>> exam_subcl_grade_rel table object
	private String escg_id;
	private String subcl_id;
	private String grade_from;
	private String grade_to;
	private String escg_status;
	
	//>>> exam_grade table object
	private String eg_id;
	private String eg_value;
	private String eg_desc;
	
	//subject_list table object
	private String sub_name;
	
	//class_list table object
	private String cl_id;
	private String cl_name;
	
	public String getEscg_id() {
		return escg_id;
	}
	public void setEscg_id(String escg_id) {
		this.escg_id = escg_id;
	}
	public String getSubcl_id() {
		return subcl_id;
	}
	public void setSubcl_id(String subcl_id) {
		this.subcl_id = subcl_id;
	}
	public String getEg_id() {
		return eg_id;
	}
	public void setEg_id(String eg_id) {
		this.eg_id = eg_id;
	}
	public String getGrade_from() {
		return grade_from;
	}
	public void setGrade_from(String grade_from) {
		this.grade_from = grade_from;
	}
	public String getGrade_to() {
		return grade_to;
	}
	public void setGrade_to(String grade_to) {
		this.grade_to = grade_to;
	}
	public String getEscg_status() {
		return escg_status;
	}
	public void setEscg_status(String escg_status) {
		this.escg_status = escg_status;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public String getEg_value() {
		return eg_value;
	}
	public void setEg_value(String eg_value) {
		this.eg_value = eg_value;
	}
	public String getEg_desc() {
		return eg_desc;
	}
	public void setEg_desc(String eg_desc) {
		this.eg_desc = eg_desc;
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
	
}
