package adminClass.exam_schedule;

public class ExamScheduleBean {

	// >>> exam_schedule table object
	private String es_id;
	private String et_id;
	private String es_ethio_date;
	private String es_greg_date;
	private String time_from;
	private String time_to;
	private String academic_year;
	private String at_id;
	private String cl_id;
	private String sub_id;
	private String es_status;

	// class_list table object
	private String cl_name;

	// subject_list table object
	private String sub_name;

	// annual_term table object
	private String at_name;

	// exams_type table object
	private String et_name;

	// additional objects
	private String day_of_week;
	private String indx;

	public String getEs_id() {
		return es_id;
	}

	public void setEs_id(String es_id) {
		this.es_id = es_id;
	}

	public String getEt_id() {
		return et_id;
	}

	public void setEt_id(String et_id) {
		this.et_id = et_id;
	}

	public String getEs_ethio_date() {
		return es_ethio_date;
	}

	public void setEs_ethio_date(String es_ethio_date) {
		this.es_ethio_date = es_ethio_date;
	}

	public String getEs_greg_date() {
		return es_greg_date;
	}

	public void setEs_greg_date(String es_greg_date) {
		this.es_greg_date = es_greg_date;
	}

	public String getCl_id() {
		return cl_id;
	}

	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}

	public String getEs_status() {
		return es_status;
	}

	public void setEs_status(String es_status) {
		this.es_status = es_status;
	}

	public String getIndx() {
		return indx;
	}

	public void setIndx(String indx) {
		this.indx = indx;
	}

	public String getEt_name() {
		return et_name;
	}

	public void setEt_name(String et_name) {
		this.et_name = et_name;
	}

	public String getCl_name() {
		return cl_name;
	}

	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public String getSub_id() {
		return sub_id;
	}

	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}

	public String getAcademic_year() {
		return academic_year;
	}

	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
	}

	public String getAt_id() {
		return at_id;
	}

	public void setAt_id(String at_id) {
		this.at_id = at_id;
	}

	public String getAt_name() {
		return at_name;
	}

	public void setAt_name(String at_name) {
		this.at_name = at_name;
	}

	public String getTime_from() {
		return time_from;
	}

	public void setTime_from(String time_from) {
		this.time_from = time_from;
	}

	public String getTime_to() {
		return time_to;
	}

	public void setTime_to(String time_to) {
		this.time_to = time_to;
	}

	public String getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(String day_of_week) {
		this.day_of_week = day_of_week;
	}

}
