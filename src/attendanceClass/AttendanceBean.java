package attendanceClass;

public class AttendanceBean {

	// >>> da_attendance table object
	private String att_id;
	private String si_id;
	private String attendance_date;
	private String attendacen_reason;
	private String clcd_id;
	private String acadmic_year;
	private String ti_id;
	private String attype_id;
	private String at_id;
	private String att_status;

	// >>> da_attendance_type table object
	private String attype_description;
	private String attype_code;
	private String attype_status;

	//class_list table object
	private String cl_id;
	private String cl_name;

	//class_detail table object
	private String cd_id;

	//additional objects
	private String firstName;
	private String fatherName;
	private String gfName;
	private String dateList;
	private String noofdays;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAttype_id() {
		return attype_id;
	}

	public void setAttype_id(String attype_id) {
		this.attype_id = attype_id;
	}

	public String getAttype_description() {
		return attype_description;
	}

	public void setAttype_description(String attype_description) {
		this.attype_description = attype_description;
	}

	public String getAttype_code() {
		return attype_code;
	}

	public void setAttype_code(String attype_code) {
		this.attype_code = attype_code;
	}

	public String getAttype_status() {
		return attype_status;
	}

	public void setAttype_status(String attype_status) {
		this.attype_status = attype_status;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGfName() {
		return gfName;
	}

	public void setGfName(String gfName) {
		this.gfName = gfName;
	}

	public String getSi_id() {
		return si_id;
	}

	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}

	public String getAttendance_date() {
		return attendance_date;
	}

	public void setAttendance_date(String attendance_date) {
		this.attendance_date = attendance_date;
	}

	public String getAttendacen_reason() {
		return attendacen_reason;
	}

	public void setAttendacen_reason(String attendacen_reason) {
		this.attendacen_reason = attendacen_reason;
	}

	public String getClcd_id() {
		return clcd_id;
	}

	public void setClcd_id(String clcd_id) {
		this.clcd_id = clcd_id;
	}

	public String getTi_id() {
		return ti_id;
	}

	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}

	public String getAtt_status() {
		return att_status;
	}

	public void setAtt_status(String att_status) {
		this.att_status = att_status;
	}

	public String getAtt_id() {
		return att_id;
	}

	public void setAtt_id(String att_id) {
		this.att_id = att_id;
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

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	public String getAcadmic_year() {
		return acadmic_year;
	}

	public void setAcadmic_year(String acadmic_year) {
		this.acadmic_year = acadmic_year;
	}

	public String getDateList() {
		return dateList;
	}

	public void setDateList(String dateList) {
		this.dateList = dateList;
	}

	public String getNoofdays() {
		return noofdays;
	}

	public void setNoofdays(String noofdays) {
		this.noofdays = noofdays;
	}

	public String getAt_id() {
		return at_id;
	}

	public void setAt_id(String at_id) {
		this.at_id = at_id;
	}

}
