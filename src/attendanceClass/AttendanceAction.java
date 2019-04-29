package attendanceClass;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import studentClass.StudentBean;
import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import util.ReturnCurrentEthiopianYear;
import util.RoleValidator;

import adminClass.AdminBean;
import adminClass.AdminDAO;
import cRoomClass.CRoomBean;
import cRoomClass.CRoomDAO;
import examClass.exam_result.ExamResultBean;
import examClass.exam_result.ExamResultDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AttendanceAction extends ActionSupport implements ModelDriven<AttendanceBean>, SessionAware {
	
	private static final long serialVersionUID = 1174853287151115268L;
	private String menutype = "attendance";
	private List<AdminBean> usrRoleList;
	private Map<String, Object> sessionMap;
	private List<CRoomBean> class_rslt;
	private List<StudentClassDetailBean> class_detail;
	private List<StudentBean> stud_list;
	private String classSize;
	private String rowNum;
	private Integer listSize;
	private List<AttendanceBean> abstStu_list;
	private List<AttendanceBean> aggStudAbsent_list;
	private List<AttendanceBean> attendance_type_list;
	private List<ExamResultBean> sem_list;
	
	AttendanceBean atb = new AttendanceBean();

	public String attendanceTemp(){
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Attendance";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
			
			if(sessionMap.get("loggedInUserType").equals("THCR")){
				
				String ti_id = (String) sessionMap.get("ti_id");
				
				class_rslt = CRoomDAO.getActiveRelatedClass(ti_id);
			} else {
				
				class_rslt = CRoomDAO.getActiveClass();
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String attendancePerGrade(){
		if (sessionMap.containsKey("userName")) {
			
			atb.setCl_name(atb.getCl_name());
			
			if(sessionMap.get("loggedInUserType").equals("THCR")){
				
				String ti_id = (String) sessionMap.get("ti_id");
				
				class_detail = StudentDAO.getClassDetailTeacherRelated(atb.getCl_id(), ti_id, (String) sessionMap.get("userName"));
			} else {
				
				class_detail = StudentDAO.getClassDetail(atb.getCl_id(), (String) sessionMap.get("userName"));
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String studentListPerGrade(){
		if (sessionMap.containsKey("userName")) {
			
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			classSize = AttendanceDAO.getClassSize(atb);
			Double rwDbl = Double.parseDouble(classSize)/6;
			Integer rwInt = Integer.parseInt(classSize)/6;
			
			if(rwDbl > rwInt){
				rowNum = String.valueOf(rwInt + 1);
			} else {
				rowNum = String.valueOf(rwInt);			
			}
			
			stud_list = StudentDAO.getListPerGradeDetail(atb.getCl_id(), atb.getCd_id(), yr);
			listSize = stud_list.size();
//			if(listSize > 0){
//				Collections.sort(stud_list, new Comparator<StudentBean>() {
//					public int compare(StudentBean a, StudentBean b){
//						//return b.getAverage_mark().compareTo(a.getAverage_mark());
//						return a.getFname().compareTo(b.getFname());
//					}
//				});
//			}
			
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			attendance_type_list = AttendanceDAO.getAttendanceType();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String saveAbsent(){
		if (sessionMap.containsKey("userName")) {
			
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			boolean absDataValidate = AttendanceDAO.getCheckAbsentDataExistance(atb, yr, sessionMap.get("userName"));
			
			if(!absDataValidate){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String studentAbsentListPerGrade(){
		if (sessionMap.containsKey("userName")) {
			abstStu_list = AttendanceDAO.getAbsentStudentList(atb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String aggregateStudentAbsentListPerGrade(){
		if (sessionMap.containsKey("userName")) {
			
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			stud_list = StudentDAO.getListPerGradeDetail(atb.getCl_id(), atb.getCd_id(), yr);
			if(stud_list.size() > 0){
				aggStudAbsent_list = AttendanceDAO.getAggregateStudentAbsetList(stud_list, yr);
			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveAbsentReason(){
		boolean rslt = AttendanceDAO.saveAbsentReason(atb);
		if(rslt){
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	
	
	
	
	
	
	
	
	@Override
	public AttendanceBean getModel() {
		return atb;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}

	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<CRoomBean> getClass_rslt() {
		return class_rslt;
	}

	public void setClass_rslt(List<CRoomBean> class_rslt) {
		this.class_rslt = class_rslt;
	}

	public List<StudentBean> getStud_list() {
		return stud_list;
	}

	public void setStud_list(List<StudentBean> stud_list) {
		this.stud_list = stud_list;
	}

	public String getClassSize() {
		return classSize;
	}

	public void setClassSize(String classSize) {
		this.classSize = classSize;
	}

	public List<StudentClassDetailBean> getClass_detail() {
		return class_detail;
	}

	public void setClass_detail(List<StudentClassDetailBean> class_detail) {
		this.class_detail = class_detail;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	public List<AttendanceBean> getAbstStu_list() {
		return abstStu_list;
	}

	public void setAbstStu_list(List<AttendanceBean> abstStu_list) {
		this.abstStu_list = abstStu_list;
	}

	public List<AttendanceBean> getAggStudAbsent_list() {
		return aggStudAbsent_list;
	}

	public void setAggStudAbsent_list(List<AttendanceBean> aggStudAbsent_list) {
		this.aggStudAbsent_list = aggStudAbsent_list;
	}

	public List<AttendanceBean> getAttendance_type_list() {
		return attendance_type_list;
	}

	public void setAttendance_type_list(List<AttendanceBean> attendance_type_list) {
		this.attendance_type_list = attendance_type_list;
	}

	public List<ExamResultBean> getSem_list() {
		return sem_list;
	}

	public void setSem_list(List<ExamResultBean> sem_list) {
		this.sem_list = sem_list;
	}

}
