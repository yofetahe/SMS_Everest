package classSchedule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import teacherClass.TeacherBean;
import teacherClass.TeacherDAO;
import util.OpenFile;
import util.PDF_GeneralClassSchedule;
import util.ReturnCurrentEthiopianYear;
import util.RoleValidator;
import util.SysConstant;
import util.TodayDate_YYYYMMDD;
import adminClass.AdminBean;
import adminClass.AdminDAO;
import adminClass.classPeriodAssignment.ClassPeriodAssignBean;
import adminClass.classPeriodAssignment.ClassPeriodAssignDAO;
import cRoomClass.CRoomBean;
import cRoomClass.CRoomDAO;
import cRoomClass.cd_registration.CDetailBean;
import cRoomClass.cd_registration.CDetailDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ClassScheduleAction extends ActionSupport implements ModelDriven<ClassScheduleBean>, SessionAware {
	
	private static final long serialVersionUID = 1L;
	private String menutype = "classSchedule";
	
	private List<AdminBean> usrRoleList;
	private Map<String, Object> sessionMap;
	private List<CRoomBean> class_rslt;
	private List<CDetailBean> cd_rslt;
	private List<StudentClassDetailBean> class_detail;
	private List<ClassScheduleBean> cs_info;
	private List<ClassScheduleBean> cs_detail;
	private List<ClassScheduleBean> clSubRelList;
	private List<ClassScheduleBean> teach_list;
	private List<ClassScheduleBean> teachSubSelected;
	
	private List<List<String>> sorted_classSchedule;
	private List<List<ClassScheduleBean>> sorted_classScheduleDetail;
	private List<ClassScheduleBean> classSchedulePerTeacher;	
	private List<List<ClassScheduleBean>> sortedClassSchedulePerTeacher;
	
	private List<List<ClassScheduleBean>> sortedClassSchedule;
	private List<List<List<ClassScheduleBean>>> generalSortedClassSchedule = new ArrayList<List<List<ClassScheduleBean>>>();
	
	private List<List<ClassPeriodAssignBean>> suggested_classSchedule;
	
	private List<TeacherBean> tchr_rslt;
	
	private List<Integer> rslt;
	
	List<ClassPeriodAssignBean> classPeriodAssign;	

	private int[] year;
	
	ClassScheduleBean csb = new ClassScheduleBean();
	
	public String classScheduleTemp(){
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Class Schedule";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
		
			cd_rslt = CDetailDAO.getCDetailList();
			class_rslt = CRoomDAO.getClassList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String ClDetailStudentList(){
		if (sessionMap.containsKey("userName")) {
			class_detail = StudentDAO.getClassDetail(csb.getCl_id(), (String)sessionMap.get("userName"));
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String classschedule_listpergrdetail(){
		if (sessionMap.containsKey("userName")) {
			String yr = util.DateConvertor.dateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat());		
			csb.setAcademic_year(yr);
			year = new int[]{Integer.parseInt(yr), Integer.parseInt(yr) - 1, Integer.parseInt(yr) - 2, Integer.parseInt(yr) - 3, Integer.parseInt(yr) - 4};
			cs_info = ClassScheduleDAO.getClassScheduleInfo();
			cs_detail = ClassScheduleDAO.getClassScheduleDetail(csb);
			
			sorted_classScheduleDetail = ClassScheduleDAO.getSortedClassScheduleDetail(cs_info, cs_detail);
						
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String general_classschedule(){
		if (sessionMap.containsKey("userName")) {
			
			cs_info = ClassScheduleDAO.getClassScheduleInfo();
			
			class_rslt = CRoomDAO.getClassList();
			
			for(int i = 0; i < class_rslt.size(); i++){
				
				class_detail = StudentDAO.getClassDetail(class_rslt.get(i).getCl_id(), (String)sessionMap.get("userName"));
				
				for(int j = 0; j < class_detail.size(); j++){
					
					List<List<ClassScheduleBean>> sortedClassSchedule1 = new ArrayList<List<ClassScheduleBean>>();
					
					csb.setCl_id(class_rslt.get(i).getCl_id());
					csb.setCd_id(class_detail.get(j).getCd_id());
					csb.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
					
					cs_detail = ClassScheduleDAO.getClassScheduleDetail(csb);
					
					sortedClassSchedule1 = ClassScheduleDAO.getGeneralSortedClassSchedule(cs_detail, cs_info);
					
					generalSortedClassSchedule.add(sortedClassSchedule1);
				}
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String general_classschedule_pdf(){
		
		if (sessionMap.containsKey("userName")) {
			
			cs_info = ClassScheduleDAO.getClassScheduleInfo();
			
			class_rslt = CRoomDAO.getClassList();
			
			for(int i = 0; i < class_rslt.size(); i++){
				
				class_detail = StudentDAO.getClassDetail(class_rslt.get(i).getCl_id(), (String)sessionMap.get("userName"));
				
				for(int j = 0; j < class_detail.size(); j++){
					
					List<List<ClassScheduleBean>> sortedClassSchedule1 = new ArrayList<List<ClassScheduleBean>>();
					
					csb.setCl_id(class_rslt.get(i).getCl_id());
					csb.setCd_id(class_detail.get(j).getCd_id());
					csb.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
					
					cs_detail = ClassScheduleDAO.getClassScheduleDetail(csb);
					
					sortedClassSchedule1 = ClassScheduleDAO.getGeneralSortedClassSchedule(cs_detail, cs_info);
					
					generalSortedClassSchedule.add(sortedClassSchedule1);
				}
			}
			
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			String fileName = SysConstant.CERT_PDF_PATH + "General_Class_Schedule_" + timeStamp + ".pdf";
			
			PDF_GeneralClassSchedule.generateGeneralClassSchedulePDF(generalSortedClassSchedule, fileName);
			
			OpenFile.openExistingPDFFile(fileName);
			
			File scheduleFile = new File(fileName);
			
			if(!scheduleFile.exists()){
				
				return ERROR;
			}
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	public String classschedule_listpergrdetail_forprint(){
		if (sessionMap.containsKey("userName")) {
			String yr = util.DateConvertor.dateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat());		
			csb.setAcademic_year(yr);
			year = new int[]{Integer.parseInt(yr), Integer.parseInt(yr) - 1, Integer.parseInt(yr) - 2, Integer.parseInt(yr) - 3, Integer.parseInt(yr) - 4};
			cs_info = ClassScheduleDAO.getClassScheduleInfo();
			cs_detail = ClassScheduleDAO.getClassScheduleDetail(csb);
			
			sorted_classSchedule = ClassScheduleDAO.getSortedClassScheduleSubjectOnly(cs_info, cs_detail);
									
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String classschedule_listpergrdetail_peryear(){
		if (sessionMap.containsKey("userName")) {
			String yr = csb.getCur_date();		
			csb.setAcademic_year(csb.getCur_date());
			year = new int[]{Integer.parseInt(yr), Integer.parseInt(yr) - 1, Integer.parseInt(yr) - 2, Integer.parseInt(yr) - 3, Integer.parseInt(yr) - 4};
			cs_info = ClassScheduleDAO.getClassScheduleInfo();
			cs_detail = ClassScheduleDAO.getClassScheduleDetail(csb);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String classschedule_listpergrdetail_crtform(){
		if (sessionMap.containsKey("userName")) {
			String yr = util.DateConvertor.dateConvertor(csb.getCur_date());
			csb.setAcademic_year(yr);
			year = new int[]{Integer.parseInt(yr), Integer.parseInt(yr) + 1, Integer.parseInt(yr) + 2};
			cs_info = ClassScheduleDAO.getClassScheduleInfo();
			clSubRelList = ClassScheduleDAO.getSelectedSubList(csb);
			teach_list = ClassScheduleDAO.getSubList(csb);
			return SUCCESS;
		} else {
			return INPUT;
		}
		
	}
	
	public String classschedule_teacherforsubject(){
		if (sessionMap.containsKey("userName")) {
			teach_list = ClassScheduleDAO.getSubList(csb);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String classschedule_perperiodandwday(){
		
		if (sessionMap.containsKey("userName")) {
			
			csb.setTa_id(csb.getTa_id());
			csb.setCd_id(csb.getCd_id());
			csb.setAcademic_year(csb.getAcademic_year());
			csb.setWeek_day(csb.getWeek_day());
			csb.setPeriod(csb.getPeriod());
			csb.setIndx(csb.getIndx());
			
			teachSubSelected = ClassScheduleDAO.collectClassScheduleData(csb);		
			
			return SUCCESS;
		} else {
			return INPUT;
		}
		
	}
	
	public String classschedule_saveinfo(){	
		if (sessionMap.containsKey("userName")) {
			rslt = ClassScheduleDAO.saveClassScheduleInfo(csb);
			return SUCCESS;
		} else {
			return INPUT;
		}				
	}
	
	public String classschedule_removeinfo(){
		if (sessionMap.containsKey("userName")) {
			
			clSubRelList = ClassScheduleDAO.getSelectedSubList(csb);
			teach_list = ClassScheduleDAO.getSubList(csb);
			
			boolean rmv = ClassScheduleDAO.removeAddedInfo(csb);
			
			if(rmv){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
		
	}
	
	public String classschedule_editinfo(){
		if (sessionMap.containsKey("userName")) {
			clSubRelList = ClassScheduleDAO.getSelectedSubList(csb);
			teach_list = ClassScheduleDAO.getSubList(csb);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String classschedule_updateinfo(){
		if (sessionMap.containsKey("userName")) {
			
			////*** if the edit form doesn't have value before, 
			////    then the edit form changes to save form
			if(csb.getCs_id().length() == 0){
				
				csb.setIndx(csb.getPeriod());
				csb.setSaveBtnIndex(csb.getWeek_day());
				
				String add = classschedule_perperiodandwday();
				String save = "";
				if(add.equalsIgnoreCase("success")){
					save = classschedule_saveinfo();
				}
				if(save.equals("success")){
					csb.setTa_id(csb.getTa_id());
					teachSubSelected = ClassScheduleDAO.collectClassScheduleData(csb);
					return SUCCESS;
				}
			}
			
			////*** if the edit form have value already added, 
			////    then the edit form perform the update process
			String rslt = ClassScheduleDAO.updateClassScheduleInfo(csb);
			
			if(rslt.length() > 0){
				csb.setTa_id(rslt);
				teachSubSelected = ClassScheduleDAO.collectClassScheduleData(csb);
				return SUCCESS;
			} else {
				clSubRelList = ClassScheduleDAO.getSelectedSubList(csb);
				teach_list = ClassScheduleDAO.getSubList(csb);
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String classschedule_teachers_schedule(){
		if (sessionMap.containsKey("userName")) {
			
			tchr_rslt = TeacherDAO.getActiveTeacherLilst();
			
			return SUCCESS;
		} else {
			return INPUT;
		}	
	}
	
	public String classschedule_teachers_schedule_detail(){		
		if (sessionMap.containsKey("userName")) {			
			
			classSchedulePerTeacher = ClassScheduleDAO.getClassSchedulePerTeacher(csb.getTi_id());
			cs_info = ClassScheduleDAO.getClassScheduleInfo();
			
			sortedClassSchedulePerTeacher = ClassScheduleDAO.getSortedClassSchedulePerTeacher(classSchedulePerTeacher, cs_info);
						
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String subjectAssignmentSuggestion(){
		if (sessionMap.containsKey("userName")) {
			
			classPeriodAssign = ClassPeriodAssignDAO.getClassPeriodAssigned(csb.getCl_id(), ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());
			
			suggested_classSchedule = ClassScheduleDAO.getSuggestedClassSchedule(csb, classPeriodAssign);
			
//			for(int i = 0; i < suggested_classSchedule.size(); i++){
//				
//				for(int j = 0; j < suggested_classSchedule.get(i).size(); j++){
//					
//					System.out.print(suggested_classSchedule.get(i).get(j).getSubjectBean().getSub_name() + " - ");
//				}
//				System.out.println();
//			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;		
	}
	@Override
	public ClassScheduleBean getModel() {
		return csb;
	}
	public String getMenutype() {
		return menutype;
	}
	public void setMenutype(String menutype) {
		this.menutype = menutype;
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
	public List<CDetailBean> getCd_rslt() {
		return cd_rslt;
	}
	public void setCd_rslt(List<CDetailBean> cd_rslt) {
		this.cd_rslt = cd_rslt;
	}
	public List<StudentClassDetailBean> getClass_detail() {
		return class_detail;
	}
	public void setClass_detail(List<StudentClassDetailBean> class_detail) {
		this.class_detail = class_detail;
	}

	public List<ClassScheduleBean> getCs_info() {
		return cs_info;
	}

	public void setCs_info(List<ClassScheduleBean> cs_info) {
		this.cs_info = cs_info;
	}

	public List<ClassScheduleBean> getCs_detail() {
		return cs_detail;
	}

	public void setCs_detail(List<ClassScheduleBean> cs_detail) {
		this.cs_detail = cs_detail;
	}

	public int[] getYear() {
		return year;
	}

	public void setYear(int[] year) {
		this.year = year;
	}

	public List<ClassScheduleBean> getClSubRelList() {
		return clSubRelList;
	}

	public void setClSubRelList(List<ClassScheduleBean> clSubRelList) {
		this.clSubRelList = clSubRelList;
	}

	public List<ClassScheduleBean> getTeach_list() {
		return teach_list;
	}

	public void setTeach_list(List<ClassScheduleBean> teach_list) {
		this.teach_list = teach_list;
	}

	public List<ClassScheduleBean> getTeachSubSelected() {
		return teachSubSelected;
	}

	public void setTeachSubSelected(List<ClassScheduleBean> teachSubSelected) {
		this.teachSubSelected = teachSubSelected;
	}

	public List<Integer> getRslt() {
		return rslt;
	}

	public void setRslt(List<Integer> rslt) {
		this.rslt = rslt;
	}

	public List<List<String>> getSorted_classSchedule() {
		return sorted_classSchedule;
	}

	public void setSorted_classSchedule(List<List<String>> sorted_classSchedule) {
		this.sorted_classSchedule = sorted_classSchedule;
	}

	public List<List<ClassScheduleBean>> getSorted_classScheduleDetail() {
		return sorted_classScheduleDetail;
	}

	public void setSorted_classScheduleDetail(List<List<ClassScheduleBean>> sorted_classScheduleDetail) {
		this.sorted_classScheduleDetail = sorted_classScheduleDetail;
	}

	public List<ClassScheduleBean> getClassSchedulePerTeacher() {
		return classSchedulePerTeacher;
	}

	public void setClassSchedulePerTeacher(List<ClassScheduleBean> classSchedulePerTeacher) {
		this.classSchedulePerTeacher = classSchedulePerTeacher;
	}

	public List<TeacherBean> getTchr_rslt() {
		return tchr_rslt;
	}

	public void setTchr_rslt(List<TeacherBean> tchr_rslt) {
		this.tchr_rslt = tchr_rslt;
	}

	public List<List<ClassScheduleBean>> getSortedClassSchedulePerTeacher() {
		return sortedClassSchedulePerTeacher;
	}

	public void setSortedClassSchedulePerTeacher(List<List<ClassScheduleBean>> sortedClassSchedulePerTeacher) {
		this.sortedClassSchedulePerTeacher = sortedClassSchedulePerTeacher;
	}

	public List<List<ClassScheduleBean>> getSortedClassSchedule() {
		return sortedClassSchedule;
	}

	public void setSortedClassSchedule(List<List<ClassScheduleBean>> sortedClassSchedule) {
		this.sortedClassSchedule = sortedClassSchedule;
	}

	public List<List<List<ClassScheduleBean>>> getGeneralSortedClassSchedule() {
		return generalSortedClassSchedule;
	}

	public void setGeneralSortedClassSchedule(List<List<List<ClassScheduleBean>>> generalSortedClassSchedule) {
		this.generalSortedClassSchedule = generalSortedClassSchedule;
	}

	public List<List<ClassPeriodAssignBean>> getSuggested_classSchedule() {
		return suggested_classSchedule;
	}

	public void setSuggested_classSchedule(List<List<ClassPeriodAssignBean>> suggested_classSchedule) {
		this.suggested_classSchedule = suggested_classSchedule;
	}
	
	public List<ClassPeriodAssignBean> getClassPeriodAssign() {
		return classPeriodAssign;
	}

	public void setClassPeriodAssign(List<ClassPeriodAssignBean> classPeriodAssign) {
		this.classPeriodAssign = classPeriodAssign;
	}
}
