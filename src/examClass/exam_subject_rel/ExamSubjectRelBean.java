package examClass.exam_subject_rel;

public class ExamSubjectRelBean {
	
	//>>> exam_sub_rel table object
	private String exsub_id;
	private String et_id;
	private String subcl_id;
	private String total_mark;
	private String pass_mark;
	private String rel_status;
	
	//exams_type table object
	private String et_name;
	
	//class_list table object
	private String cl_id;
	
	//subject_list table object
	private String sub_id;
	private String sub_name;
	
	
	public String getExsub_id() {
		return exsub_id;
	}
	public void setExsub_id(String exsub_id) {
		this.exsub_id = exsub_id;
	}
	public String getEt_id() {
		return et_id;
	}
	public void setEt_id(String et_id) {
		this.et_id = et_id;
	}
	public String getSubcl_id() {
		return subcl_id;
	}
	public void setSubcl_id(String subcl_id) {
		this.subcl_id = subcl_id;
	}
	public String getPass_mark() {
		return pass_mark;
	}
	public void setPass_mark(String pass_mark) {
		this.pass_mark = pass_mark;
	}
	public String getRel_status() {
		return rel_status;
	}
	public void setRel_status(String rel_status) {
		this.rel_status = rel_status;
	}
	public String getEt_name() {
		return et_name;
	}
	public void setEt_name(String et_name) {
		this.et_name = et_name;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getTotal_mark() {
		return total_mark;
	}
	public void setTotal_mark(String total_mark) {
		this.total_mark = total_mark;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	
	

}
