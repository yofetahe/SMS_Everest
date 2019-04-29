package studentClass;

import java.util.List;

import examClass.exam_result.ExamResultBean;
import studentClass.current_address.CurrentAddressBean;
import studentClass.emergency_contact.EmergencyContactBean;

public class StudentBean {
	
	//stud_information table object
	private String si_id;
	private String fname;
	private String mname;
	private String gname;
	private String mother_name;
	private String sex;
	private String dob;
	private String pob;
	private String nationality;
	private String id_no;
	private String photo_path;
	private String photo_name;
	private int bro_sis_number;
	private String special_attention_info;
	private String si_status;
	
	//additional objects
	private String stud_tab;
	private String fullName;	
	private String stud_count;
	private String stud_count_prc;
	private String total_number;
	private String no_male;
	private String no_female;
	private String num_of_class;
	private String num_of_class_detail;	
	private String rslt_status;
	private String dropout_status;
	private String stud_edit_status;	
	private String age;
	private String ac_year;
	private String stud_special_need_status;
	
	//user_type table object
	private String ut_id;
	
	//u_module table object
	private String m_id;
	private String m_name;
	
	//class_list table object
	private String class_id;
	private String class_name;
	
	//class_detail table object
	private String cd_id;
	private String cd_name;
		
	//special_need_category table object
	private String snc_id;
	
	//stud_special_need_required table object
	private String ssnr_id;
		
	private byte[] stud_photo;
		
	private String contact_information;
	
	private CurrentAddressBean currentAddressBean;
	
	private EmergencyContactBean emergencyContactBean;
	
	private ExamResultBean examResultBean;
	
	private List<EmergencyContactBean> emergencyContactBeanList;
	
	
	public String getSi_id() {
		return si_id;
	}
	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPob() {
		return pob;
	}
	public void setPob(String pob) {
		this.pob = pob;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getSi_status() {
		return si_status;
	}
	public void setSi_status(String si_status) {
		this.si_status = si_status;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getCd_id() {
		return cd_id;
	}
	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getStud_tab() {
		return stud_tab;
	}
	public void setStud_tab(String stud_tab) {
		this.stud_tab = stud_tab;
	}	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getUt_id() {
		return ut_id;
	}
	public void setUt_id(String ut_id) {
		this.ut_id = ut_id;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public String getPhoto_name() {
		return photo_name;
	}
	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}
	public String getStud_count() {
		return stud_count;
	}
	public void setStud_count(String stud_count) {
		this.stud_count = stud_count;
	}
	public String getStud_count_prc() {
		return stud_count_prc;
	}
	public void setStud_count_prc(String stud_count_prc) {
		this.stud_count_prc = stud_count_prc;
	}
	public String getTotal_number() {
		return total_number;
	}
	public void setTotal_number(String total_number) {
		this.total_number = total_number;
	}
	public String getAc_year() {
		return ac_year;
	}
	public void setAc_year(String ac_year) {
		this.ac_year = ac_year;
	}
	public String getNo_male() {
		return no_male;
	}
	public void setNo_male(String no_male) {
		this.no_male = no_male;
	}
	public String getNo_female() {
		return no_female;
	}
	public void setNo_female(String no_female) {
		this.no_female = no_female;
	}
	public String getRslt_status() {
		return rslt_status;
	}
	public void setRslt_status(String rslt_status) {
		this.rslt_status = rslt_status;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDropout_status() {
		return dropout_status;
	}
	public void setDropout_status(String dropout_status) {
		this.dropout_status = dropout_status;
	}
	public String getNum_of_class() {
		return num_of_class;
	}
	public void setNum_of_class(String num_of_class) {
		this.num_of_class = num_of_class;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCd_name() {
		return cd_name;
	}
	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}
	public String getNum_of_class_detail() {
		return num_of_class_detail;
	}
	public void setNum_of_class_detail(String num_of_class_detail) {
		this.num_of_class_detail = num_of_class_detail;
	}
	public String getStud_edit_status() {
		return stud_edit_status;
	}
	public void setStud_edit_status(String stud_edit_status) {
		this.stud_edit_status = stud_edit_status;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getStud_special_need_status() {
		return stud_special_need_status;
	}
	public void setStud_special_need_status(String stud_special_need_status) {
		this.stud_special_need_status = stud_special_need_status;
	}
	public String getSnc_id() {
		return snc_id;
	}
	public void setSnc_id(String snc_id) {
		this.snc_id = snc_id;
	}
	public String getSsnr_id() {
		return ssnr_id;
	}
	public void setSsnr_id(String ssnr_id) {
		this.ssnr_id = ssnr_id;
	}
	public CurrentAddressBean getCurrentAddressBean() {
		return currentAddressBean;
	}
	public void setCurrentAddressBean(CurrentAddressBean currentAddressBean) {
		this.currentAddressBean = currentAddressBean;
	}
	public byte[] getStud_photo() {
		return stud_photo;
	}
	public void setStud_photo(byte[] stud_photo) {
		this.stud_photo = stud_photo;
	}
	public EmergencyContactBean getEmergencyContactBean() {
		return emergencyContactBean;
	}
	public void setEmergencyContactBean(EmergencyContactBean emergencyContactBean) {
		this.emergencyContactBean = emergencyContactBean;
	}
	public ExamResultBean getExamResultBean() {
		return examResultBean;
	}
	public void setExamResultBean(ExamResultBean examResultBean) {
		this.examResultBean = examResultBean;
	}
	public List<EmergencyContactBean> getEmergencyContactBeanList() {
		return emergencyContactBeanList;
	}
	public void setEmergencyContactBeanList(List<EmergencyContactBean> emergencyContactBeanList) {
		this.emergencyContactBeanList = emergencyContactBeanList;
	}
	public String getContact_information() {
		return contact_information;
	}
	public void setContact_information(String contact_information) {
		this.contact_information = contact_information;
	}
	public int getBro_sis_number() {
		return bro_sis_number;
	}
	public void setBro_sis_number(int bro_sis_number) {
		this.bro_sis_number = bro_sis_number;
	}
	public String getSpecial_attention_info() {
		return special_attention_info;
	}
	public void setSpecial_attention_info(String special_attention_info) {
		this.special_attention_info = special_attention_info;
	}	
}
