package adminClass.special_registration;

import cRoomClass.CRoomBean;
import cRoomClass.class_detail.CRDetailBean;
import studentClass.StudentBean;

public class SpeRegistrationBean {
	
	private CRoomBean class_bean;
	private CRDetailBean cdetail_bean;
	private StudentBean stud_bean;
	
	
	public CRDetailBean getCdetail_bean() {
		return cdetail_bean;
	}
	public void setCdetail_bean(CRDetailBean cdetail_bean) {
		this.cdetail_bean = cdetail_bean;
	}
	public CRoomBean getClass_bean() {
		return class_bean;
	}
	public void setClass_bean(CRoomBean class_bean) {
		this.class_bean = class_bean;
	}
	public StudentBean getStud_bean() {
		return stud_bean;
	}
	public void setStud_bean(StudentBean stud_bean) {
		this.stud_bean = stud_bean;
	}

}
