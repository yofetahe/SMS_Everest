package adminClass.id_card_generation;

import cRoomClass.CRoomBean;
import cRoomClass.class_detail.CRDetailBean;
import studentClass.StudentBean;

public class IDCardBean {
	
	private int sid_id;
	private int si_id;
	private int sid_status;
	private String print_date;
	private String return_date;
	
	private CRoomBean class_bean;
	private CRDetailBean cdetail_bean;
	private StudentBean stud_bean;
	
	private String pdf_full_page_status;

	public int getSid_id() {
		return sid_id;
	}

	public void setSid_id(int sid_id) {
		this.sid_id = sid_id;
	}

	public int getSi_id() {
		return si_id;
	}

	public void setSi_id(int si_id) {
		this.si_id = si_id;
	}

	public int getSid_status() {
		return sid_status;
	}

	public void setSid_status(int sid_status) {
		this.sid_status = sid_status;
	}

	public String getPrint_date() {
		return print_date;
	}

	public void setPrint_date(String print_date) {
		this.print_date = print_date;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public CRoomBean getClass_bean() {
		return class_bean;
	}

	public void setClass_bean(CRoomBean class_bean) {
		this.class_bean = class_bean;
	}

	public CRDetailBean getCdetail_bean() {
		return cdetail_bean;
	}

	public void setCdetail_bean(CRDetailBean cdetail_bean) {
		this.cdetail_bean = cdetail_bean;
	}

	public StudentBean getStud_bean() {
		return stud_bean;
	}

	public void setStud_bean(StudentBean stud_bean) {
		this.stud_bean = stud_bean;
	}

	public String getPdf_full_page_status() {
		return pdf_full_page_status;
	}

	public void setPdf_full_page_status(String pdf_full_page_status) {
		this.pdf_full_page_status = pdf_full_page_status;
	}
	
	
}
