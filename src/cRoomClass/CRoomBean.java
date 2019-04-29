package cRoomClass;

public class CRoomBean {
	
	//>>> class_list table object
	private String cl_id;
	private String class_name;
	private int class_number;
	private String class_status;
	
	//teacher_info table object
	private String ti_id;
		
	//subject_list table object	
	private String sub_name;
		
	//exam_sub_rel table object
	private String exsub_id;
	private String total_mark;
	private String pass_mark;
	private String rel_status;

	//subject_class_rel table object
	private String subcl_id;
	private String sub_id;
	private String add_to_total;
	private String con_to_grade;
	
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
	public String getAdd_to_total() {
		return add_to_total;
	}
	public void setAdd_to_total(String add_to_total) {
		this.add_to_total = add_to_total;
	}
	public String getCon_to_grade() {
		return con_to_grade;
	}
	public void setCon_to_grade(String con_to_grade) {
		this.con_to_grade = con_to_grade;
	}
	public int getClass_number() {
		return class_number;
	}
	public void setClass_number(int class_number) {
		this.class_number = class_number;
	}
	

}
