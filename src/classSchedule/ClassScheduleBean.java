package classSchedule;

public class ClassScheduleBean {

	// >>> class_schedule table object
	private String cs_id;
	private String ta_id;
	private String cd_id;
	private String week_day;
	private String period;
	private String academic_year;

	// >>> class_sched_subject_period_rel table object
	// >>> period_per_week needed
	private String cl_id;
	private String sub_id;

	// >>> class_schedule_info table object
	private String noof_period;
	private String noof_days;

	// teacher_info table object
	private String ti_id;
	private String tfname;
	private String tmname;
	private String tgname;
	private String fullName;

	// subject_list table object
	private String sub_name;

	// class_list table object
	private String class_name;

	// class_detail table object
	private String cd_name;

	// additional objects
	private String period_name;
	private String day_name;
	private String cur_date;
	private String indx;
	private String saveBtnIndex;
	private String checkExistance;
	private int noofcopies;

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

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	public String getCd_name() {
		return cd_name;
	}

	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}

	public String getNoof_period() {
		return noof_period;
	}

	public void setNoof_period(String noof_period) {
		this.noof_period = noof_period;
	}

	public String getNoof_days() {
		return noof_days;
	}

	public void setNoof_days(String noof_days) {
		this.noof_days = noof_days;
	}

	public String getCur_date() {
		return cur_date;
	}

	public void setCur_date(String cur_date) {
		this.cur_date = cur_date;
	}

	public String getAcademic_year() {
		return academic_year;
	}

	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
	}

	public String getTi_id() {
		return ti_id;
	}

	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}

	public String getTfname() {
		return tfname;
	}

	public void setTfname(String tfname) {
		this.tfname = tfname;
	}

	public String getTmname() {
		return tmname;
	}

	public void setTmname(String tmname) {
		this.tmname = tmname;
	}

	public String getTgname() {
		return tgname;
	}

	public void setTgname(String tgname) {
		this.tgname = tgname;
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

	public String getWeek_day() {
		return week_day;
	}

	public void setWeek_day(String week_day) {
		this.week_day = week_day;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTa_id() {
		return ta_id;
	}

	public void setTa_id(String ta_id) {
		this.ta_id = ta_id;
	}

	public String getIndx() {
		return indx;
	}

	public void setIndx(String indx) {
		this.indx = indx;
	}

	public String getSaveBtnIndex() {
		return saveBtnIndex;
	}

	public void setSaveBtnIndex(String saveBtnIndex) {
		this.saveBtnIndex = saveBtnIndex;
	}

	public String getCs_id() {
		return cs_id;
	}

	public void setCs_id(String cs_id) {
		this.cs_id = cs_id;
	}

	public String getCheckExistance() {
		return checkExistance;
	}

	public void setCheckExistance(String checkExistance) {
		this.checkExistance = checkExistance;
	}

	public String getPeriod_name() {
		return period_name;
	}

	public void setPeriod_name(String period_name) {
		this.period_name = period_name;
	}

	public String getDay_name() {
		return day_name;
	}

	public void setDay_name(String day_name) {
		this.day_name = day_name;
	}

	public int getNoofcopies() {
		return noofcopies;
	}

	public void setNoofcopies(int noofcopies) {
		this.noofcopies = noofcopies;
	}

}
