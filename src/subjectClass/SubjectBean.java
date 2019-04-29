package subjectClass;

import java.io.UnsupportedEncodingException;

public class SubjectBean {
	
	//subject_list table object
	private String sub_id;
	private String sub_name;	
	private String sub_status;
	
	private String ti_id;	
		
	//subject_class_rel table object
	private String subcl_id;
	private String cl_id;
	
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
		
		try {
			this.sub_name = new String(sub_name.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};
	}
	public String getSub_status() {
		return sub_status;
	}
	public void setSub_status(String sub_status) {
		this.sub_status = sub_status;
	}
	public String getTi_id() {
		return ti_id;
	}
	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public String getSubcl_id() {
		return subcl_id;
	}
	public void setSubcl_id(String subcl_id) {
		this.subcl_id = subcl_id;
	}
	
}
