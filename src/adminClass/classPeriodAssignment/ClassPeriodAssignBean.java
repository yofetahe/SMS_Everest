package adminClass.classPeriodAssignment;

import cRoomClass.CRoomBean;
import classSchedule.ClassScheduleBean;
import subjectClass.SubjectBean;

public class ClassPeriodAssignBean {
	
	private int csp_id;
	private int cl_id;
	private int sub_id;
	private int period_per_week;
	private int academic_year;
	private String csp_status;
	
	private SubjectBean subjectBean;
	private ClassScheduleBean classScheduleBean;
	private CRoomBean croomBean;
	
	private String[] subjectPeriodAllotment;
	
	public int getCsp_id() {
		return csp_id;
	}
	public void setCsp_id(int csp_id) {
		this.csp_id = csp_id;
	}
	public int getCl_id() {
		return cl_id;
	}
	public void setCl_id(int cl_id) {
		this.cl_id = cl_id;
	}
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public int getPeriod_per_week() {
		return period_per_week;
	}
	public void setPeriod_per_week(int period_per_week) {
		this.period_per_week = period_per_week;
	}
	public int getAcademic_year() {
		return academic_year;
	}
	public void setAcademic_year(int academic_year) {
		this.academic_year = academic_year;
	}
	public String getCsp_status() {
		return csp_status;
	}
	public void setCsp_status(String csp_status) {
		this.csp_status = csp_status;
	}
	public SubjectBean getSubjectBean() {
		return subjectBean;
	}
	public void setSubjectBean(SubjectBean subjectBean) {
		this.subjectBean = subjectBean;
	}
	public ClassScheduleBean getClassScheduleBean() {
		return classScheduleBean;
	}
	public void setClassScheduleBean(ClassScheduleBean classScheduleBean) {
		this.classScheduleBean = classScheduleBean;
	}
	public CRoomBean getCroomBean() {
		return croomBean;
	}
	public void setCroomBean(CRoomBean croomBean) {
		this.croomBean = croomBean;
	}
	public String[] getSubjectPeriodAllotment() {
		return subjectPeriodAllotment;
	}
	public void setSubjectPeriodAllotment(String[] subjectPeriodAllotment) {
		this.subjectPeriodAllotment = subjectPeriodAllotment;
	}
}
