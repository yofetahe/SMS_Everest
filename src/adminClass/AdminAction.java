package adminClass;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import studentClass.StudentBean;
import studentClass.StudentClassBean;
import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import teacherClass.TeacherBean;
import teacherClass.TeacherDAO;
import util.DeleteSecurityFile;
import util.ReturnCurrentEthiopianYear;
import util.RoleValidator;
import util.SystemValidator;
import util.SystemValidatorTextAndMacAddressCreator;
import adminClass.exam_schedule.ExamScheduleBean;
import adminClass.exam_schedule.ExamScheduleDAO;
import adminClass.holiday.holidayBean;
import adminClass.holiday.holidayDAO;
import adminClass.school_event.SchoolEventBean;
import adminClass.school_event.SchoolEventDAO;
import cRoomClass.CRoomBean;
import cRoomClass.CRoomDAO;
import classSchedule.ClassScheduleBean;
import classSchedule.ClassScheduleDAO;
import nonteacherClass.NonteacherBean;
import nonteacherClass.NonteacherDAO;
import paymentFineClass.PaymentBean;
import paymentFineClass.PaymentDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class AdminAction extends ActionSupport implements ModelDriven<AdminBean>, SessionAware {
	
	private static final long serialVersionUID = 1L;
	AdminBean ab = new AdminBean();
	private String menutype = "admin";
	
	private List<AdminBean> noneAcUserList;
	private List<AdminBean> teachUserList;
	private List<AdminBean> studUserList;
	private List<AdminBean> userTypeList;
	private List<AdminBean> teacherList;
	private List<AdminBean> tchrList;
	private List<AdminBean> usrRoleList;
	private List<AdminBean> allUserRoleList;
	private List<StudentClassBean> grade_rslt;
	private List<StudentBean> grade_db_rslt;
	private List<StudentBean> db_stud_persex;
	private List<AdminBean> loginUserInfo;
	private List<AdminBean> annualTermList;
	private List<AdminBean> userTypeListTeacher;
	private List<AdminBean> modulePerRoleList;
	private List<AdminBean> remainingModuleList;
	private List<ClassScheduleBean> classSchedulePerTeacher;
	private List<ClassScheduleBean> cs_info;
	
	private List<StudentBean> studentNumPerYear;
	private List<TeacherBean> teacherNumPerSex;
	
	private List<SchoolEventBean> seblist;
	private List<holidayBean> holidayList;
	private List<ExamScheduleBean> exSchList;
	
	private List<CRoomBean> classList;
	private List<StudentClassDetailBean> class_detail;
	private List<AdminBean> techAssignmentInfo;
		
	private String teacherName;
	private String teacherId;
	
	private String oldUserName;
	
	private List<AdminBean> assignTeacherList;
	
	private List<NonteacherBean> non_acadamic_list;
	
	List<List<ClassScheduleBean>> sortedClassSchedulePerTeacher;
	
	private String loggedUserName = null;
		
	private Map<String, Object> sessionMap;
	
	public String InstallSysAndSaveSchoolInformation(){		

		////****//// for first installation only ////****////        
		if(PaymentDAO.getSchoolInformation().size() != 0){
			return "FIRSTINSTALLATIONDONE";
		}
        ////****//// for first installation only ////****////
		
		boolean rslt = SystemValidatorTextAndMacAddressCreator.generateValidationText(ab.getSchool_name());
		
		if(rslt){
			
			PaymentBean pb = new PaymentBean();
			
			pb.setSchool_name(ab.getSchool_name());
			pb.setSchool_slogan(ab.getSchool_slogan());
			pb.setTin_num(ab.getTin_num());
			pb.setTelephone(ab.getTelephone());
			pb.setFax(ab.getFax());
			pb.setEmail(ab.getEmail());
			pb.setWeb(ab.getWeb());
			pb.setPobox(ab.getPobox());
			pb.setFiscal_machine_no(ab.getFiscal_machine_no());
			
			PaymentDAO.insertSchoolInformation(pb);
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}		
	}
	
	////***** Database Backup *****////
	public String takeDatabaseBackup(){
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = SystemValidator.fromUrlDatabaseBackup();
			
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	////***** Logged in user id *****////
	public String getLoggedInUser(String loggedInUserType){
		String loggedInUser = "";
		if(loggedInUserType.equals("NTHCR")){
			loggedInUser = (String) sessionMap.get("nti_id");
		}
		if(loggedInUserType.equals("THCR")){
			loggedInUser = (String) sessionMap.get("ti_id");
		}
		return loggedInUser;
	}
	////****** Logged in user id *****////
	
	public String getAdminTemp(){
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Admin";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
		            
    		return SUCCESS;
        } else {
        	return INPUT;
        }
	}
	
	public String validateLogin(){
		
		boolean sysValidation = SystemValidator.systemValidation();
		
//		if(!ab.getUsername().equals("esuperadmin") && SystemValidator.checkSystemFileExistance()){
//			
//			return "SYSINSERROR";	
//		}
		
		if(!sysValidation){
			
			boolean changePassword = AdminDAO.InvalidSysUserChangePassword(ab);
			
			DeleteSecurityFile.deleteSystemFileForSecurityPurpose();
						
			return "SYSERROR";	
			
		} else {
			
			// check if the userName is already stored in the session
			if (sessionMap.containsKey("userName")) {
				
	            loggedUserName = (String) sessionMap.get("userName");
	        }
			
			///*** USED ONLY FOR SYSTEM OWNERS ***///
			if(ab.getUsername().equals("smssuperadmin") && ab.getPassword().equals("P@55w0rd@sms")){
				
				sessionMap.put("userName", ab.getUsername());
				sessionMap.put("ti_id", "");
				sessionMap.put("loggedInUserType", "");
				
				db_stud_persex = StudentDAO.stud_db_persex();
				
				ab.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				
				usrRoleList = AdminDAO.getAllUserRoleListForSuperAdmin();
				
				return SUCCESS;
			}
			///*** END - USED ONLY FOR SYSTEM OWNERS ***///
						
	        if (loggedUserName != null && loggedUserName.equals(ab.getUsername())) {
	        	
	        	usrRoleList = AdminDAO.getLoginUserRoleList(ab.getUsername());
	        	
	            return SUCCESS;
	        } 
	        
	        // if no userName stored in the session,
	        // check the entered userName and password
	        List<AdminBean> rslt = AdminDAO.validateUsers(ab);
	        
	        if(rslt.size() == 0 || !ab.getPassword().equals(rslt.get(0).getPassword())){
	        	// in other cases, return login page
	        	addActionError("Wrong username or password.");
	        	addFieldError("errMsg", "Wrong username or password.");
		        return INPUT;
	        }
	        
	        if(ab.getPassword().equals(rslt.get(0).getPassword()) && rslt.get(0).getPassword().equals("pass*word")){
	        	
	        	// add userName to the session
		        //sessionMap.put("userName", ab.getUsername());
	        	setOldUserName(ab.getUsername());
	        	loginUserInfo = AdminDAO.getLogingUserFullName(ab.getUsername());
	        	return "CHANGEPASSWORD";
	        	
	        } else {
	        	
	        	// add userName, ti_id or nti_id and loggedUserType to the session
		        sessionMap.put("userName", ab.getUsername());
		        sessionMap.put("loggedInUserType", rslt.get(0).getLoggedin_user_type());
		        
		        if(rslt.get(0).getLoggedin_user_type().equals("THCR")){
		        	sessionMap.put("ti_id", rslt.get(0).getTi_id());
		        } else if(rslt.get(0).getLoggedin_user_type().equals("NTHCR")){
		        	sessionMap.put("nti_id", rslt.get(0).getNti_id());
		        }
		        
		        usrRoleList = AdminDAO.getLoginUserRoleList(ab.getUsername());
		        tchrList = AdminDAO.getLoginUserTeacherId(ab.getUsername());
				
				//checking whether the user is a teacher or not
				String ti_id = tchrList.get(0).getTi_id();
				if(ti_id.equals("N")){
					ti_id = "";
				}
				ab.setTi_id(tchrList.get(0).getTi_id());
				sessionMap.put("ti_id", ti_id);
				
				db_stud_persex = StudentDAO.stud_db_persex();
				ab.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				
				if(ti_id.length() != 0){
					
					ab.setDashboardStatus("teacher");
					classSchedulePerTeacher = ClassScheduleDAO.getClassSchedulePerTeacher(ti_id);
					cs_info = ClassScheduleDAO.getClassScheduleInfo();
					
				} else {
					
					//dashboard information
					ab.setDashboardStatus("management");				
			        grade_rslt = StudentDAO.getClassList();		
					grade_db_rslt = StudentDAO.stud_db_rslt(grade_rslt);
				}
				
		        return SUCCESS; // return welcome page
	        }        
		}
	}
	
	public String dashboard(){
		
		if (sessionMap.containsKey("userName")) {
			
            loggedUserName = (String) sessionMap.get("userName");        
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			db_stud_persex = StudentDAO.stud_db_persex();
			ab.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			String ti_id = (String) sessionMap.get("ti_id");
			
			if(ti_id.length() != 0){
				ab.setDashboardStatus("teacher");
				ab.setTi_id(ti_id);
				classSchedulePerTeacher = ClassScheduleDAO.getClassSchedulePerTeacher(ti_id);
				cs_info = ClassScheduleDAO.getClassScheduleInfo();
			} else {
				//dashboard information
				ab.setDashboardStatus("management");				
		        grade_rslt = StudentDAO.getClassList();		
				grade_db_rslt = StudentDAO.stud_db_rslt(grade_rslt);
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String changeProfile(){
		if (sessionMap.containsKey("userName")) {
            loggedUserName = (String) sessionMap.get("userName");        
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			//change profile content
			loginUserInfo = AdminDAO.getLogingUserFullName(loggedUserName);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	} 
	
	public String changeProfileFirstTimeLogin(){
		
		setOldUserName(getOldUserName());
		boolean rslt = AdminDAO.validateExistingUserName(ab);
		
		if(rslt){
			return ERROR;
		} else {
			return SUCCESS;
		}
		
	}
	
	public String changeProfileDuplicatedUsername(){
		
		//change profile content
		loginUserInfo = AdminDAO.getLogingUserFullName(getOldUserName());
		
		return SUCCESS;
	}
	
	public String changeProfileValidateUsername(){
		if (sessionMap.containsKey("userName")) {
			
			if(!AdminDAO.validateOldPassword(ab)){
				addActionMessage("Please insert a correct password.");
				return "PASSWORDERROR";
			}
			
			boolean rslt = AdminDAO.validateExistingUserName(ab);
			
			if(rslt){
				return ERROR;
			} else {
				return SUCCESS;
			}
		} else {
			return INPUT;
		}
	}
	
	public String changeProfileUpdate(){
		if (sessionMap.containsKey("userName")) {
            loggedUserName = (String) sessionMap.get("userName");        
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			//change profile content
			boolean rslt = AdminDAO.updateUserProfile(ab);
			if(rslt){
				addActionMessage("Please login with the new username and password.");
				sessionMap.remove("userName");
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String InvalidChangeProfileUpdate(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}	
	}
	
	public String changeProfileUpdateFirstTimeLogin(){
		
		boolean rslt = AdminDAO.updateUserProfile(ab);
		if(rslt){
			addActionMessage("Please login with the new username and password.");
			sessionMap.remove("userName");
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	public String logout() {
        // remove userName from the session		
        if (sessionMap.containsKey("userName")) {
            sessionMap.remove("userName");
            sessionMap.remove("ti_id");
            sessionMap.remove("nti_id");
            sessionMap.remove("loggedInUserType");
        }
        return SUCCESS;
    }
	
	public String noofstudpergrd(){
		if (sessionMap.containsKey("userName")) {
			grade_rslt = StudentDAO.getClassList();		
			grade_db_rslt = StudentDAO.stud_db_rslt(grade_rslt);
			
			db_stud_persex = StudentDAO.stud_db_persex();
			ab.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String noofstud(){
		if (sessionMap.containsKey("userName")) {
			
			studentNumPerYear = StudentDAO.getStudentNumPerYear();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String noofteacher(){
		if (sessionMap.containsKey("userName")) {
			
			teacherNumPerSex = TeacherDAO.getTeacherNumPerSex();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String teacherSchedule(){
		if (sessionMap.containsKey("userName")) {
			classSchedulePerTeacher = ClassScheduleDAO.getClassSchedulePerTeacher(ab.getTi_id());
			cs_info = ClassScheduleDAO.getClassScheduleInfo();
			
			sortedClassSchedulePerTeacher = ClassScheduleDAO.getSortedClassSchedulePerTeacher(classSchedulePerTeacher, cs_info);
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String schoolevent(){
		if (sessionMap.containsKey("userName")) {
			seblist = SchoolEventDAO.dbSEventList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String holidayList(){
		if (sessionMap.containsKey("userName")) {
			holidayList = holidayDAO.dbGetHolidayList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String examSchedule(){
		if (sessionMap.containsKey("userName")) {
			exSchList = ExamScheduleDAO.dbGetExSchList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String getSysUserList(){
		if (sessionMap.containsKey("userName")) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getNoneAcademicUserList(){
		if (sessionMap.containsKey("userName")) {
			noneAcUserList = AdminDAO.getNoneAcademicUserList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String getTeacherUserList(){
		if (sessionMap.containsKey("userName")) {
			teachUserList = AdminDAO.getTeachUserList();
			userTypeListTeacher = AdminDAO.getUserTypeTeacherList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String getStudentUserList(){
		if (sessionMap.containsKey("userName")) {
			studUserList = AdminDAO.getStudUserList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String getUserCreate(){
		if (sessionMap.containsKey("userName")) {
			
			non_acadamic_list = NonteacherDAO.getActiveNonteacherList();
			userTypeList = AdminDAO.getUserTypeList();
			teacherList = AdminDAO.getTeacherList();
			userTypeListTeacher = AdminDAO.getUserTypeTeacherList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	
	
	public String saveNoneAcUser(){
		if (sessionMap.containsKey("userName")) {
			
			boolean checkExistingUserName = AdminDAO.validateExistingUserName(ab);
			
			if(!checkExistingUserName){
				boolean rslt = AdminDAO.saveNoneAcUser(ab);
				if(rslt){
					noneAcUserList = AdminDAO.getNoneAcademicUserList();
					return SUCCESS;
				} else {
					non_acadamic_list = NonteacherDAO.getActiveNonteacherList();
					userTypeList = AdminDAO.getUserTypeList();
					return ERROR;
				}
			} else {
				addActionError("Username selected is used by someother user.");
				non_acadamic_list = NonteacherDAO.getActiveNonteacherList();
				userTypeList = AdminDAO.getUserTypeList();
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getNoneAcUserUpdate(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = AdminDAO.updateNoneAcUser(ab);			
			if(rslt){
				noneAcUserList = AdminDAO.getNoneAcademicUserList();
				return SUCCESS;
			} else {
				ab.setFormType("noneacademic");
				userTypeList = AdminDAO.getUserTypeList();
				return ERROR;
			}

		} else {
			return INPUT;
		}		
	}
	
	public String saveTeacherUser(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = AdminDAO.saveTeacherUser(ab);
			if(rslt){
				teachUserList = AdminDAO.getTeachUserList();
				return SUCCESS;
			} else {
				addActionError("Username selected is used by someother user.");
				teacherList = AdminDAO.getTeacherList();
				userTypeListTeacher = AdminDAO.getUserTypeTeacherList();
				return ERROR;
			}
		} else {
			return INPUT;
		}
		
	}
	
	public String teacherUserUpdate(){
		
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = AdminDAO.updateTeacherUser(ab);
			
			if(rslt){
				teachUserList = AdminDAO.getTeachUserList();
				return SUCCESS;
			} else {
				ab.setFormType("teacher");
				addActionError("Username is already taken by other user.");
				teacherList = AdminDAO.getTeacherList();
				userTypeListTeacher = AdminDAO.getUserTypeTeacherList();				
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getUserEdit(){	
		if(sessionMap.containsKey("userName")){
			
			String frmType = ab.getFormType();
			
			if(frmType.equalsIgnoreCase("noneacademic")){
				
				userTypeList = AdminDAO.getUserTypeList();
				noneAcUserList = AdminDAO.getNoneAcademicUserList();
				
				Integer x = Integer.parseInt(ab.getIndx());
				ab.setName(noneAcUserList.get(x).getName());
				ab.setUserType(noneAcUserList.get(x).getUtId());
				ab.setUa_id(noneAcUserList.get(x).getUa_id());
				ab.setUa_status(noneAcUserList.get(x).getUa_status());
							
			} 
			if(frmType.equalsIgnoreCase("teacher")) {
				
				userTypeListTeacher = AdminDAO.getUserTypeTeacherList();
				teacherList = AdminDAO.getTeacherList();
				classList = CRoomDAO.getClassList();
				
				for(int i = 0; i < teacherList.size(); i++){
					if(teacherList.get(i).getTi_id().equalsIgnoreCase(ab.getTi_id())){
						teacherName = teacherList.get(i).getTname();
						teacherId = teacherList.get(i).getTi_id();
					}
				}
				
				ab.setName(ab.getName());
				ab.setTi_id(ab.getTi_id());
				
			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getUserRoleList(){
		if(sessionMap.containsKey("userName")){
			allUserRoleList = AdminDAO.getAllUserRoleList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String UserRoleListCrtForm(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String UserRoleListEdtForm(){
		if(sessionMap.containsKey("userName")){
			allUserRoleList = AdminDAO.getAllUserRoleList();
			Integer x = Integer.parseInt(ab.getIndx());
			
			ab.setUt_name(allUserRoleList.get(x).getUt_name());
			ab.setUt_status(allUserRoleList.get(x).getUt_status());
			ab.setRelated_with(allUserRoleList.get(x).getRelated_with());
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String userRoleUpdate(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = AdminDAO.updateUserRoleList(ab);
			if(rslt){
				allUserRoleList = AdminDAO.getAllUserRoleList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String userRoleSave(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = AdminDAO.saveUserRoleList(ab);
			if(rslt){
				allUserRoleList = AdminDAO.getAllUserRoleList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String AnnualTermList(){
		if(sessionMap.containsKey("userName")){
			annualTermList = AdminDAO.getAnnualTermList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String AnnualTermCreateForm(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String AnnualTermSave(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = AdminDAO.saveAnnualTermList(ab);
			if(rslt){
				annualTermList = AdminDAO.getAnnualTermList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String AnnualTermEditForm(){
		if(sessionMap.containsKey("userName")){
			annualTermList = AdminDAO.getAnnualTermList();
			Integer in = Integer.parseInt(ab.getIndx());
			
			ab.setAt_id(annualTermList.get(in).getAt_id());
			ab.setAt_name(annualTermList.get(in).getAt_name());
			ab.setAt_status(annualTermList.get(in).getAt_status());
			
			boolean rslt = AdminDAO.updateAnnualTermList(ab);
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String AnnualTermUpdate(){	
		if(sessionMap.containsKey("userName")){
			boolean rslt = AdminDAO.updateAnnualTermList(ab);
			if(rslt){
				annualTermList = AdminDAO.getAnnualTermList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
		
	}
	
	public String ModulePerRoleList(){
		if(sessionMap.containsKey("userName")){
			modulePerRoleList = AdminDAO.getModuleListPerRole(ab);
			remainingModuleList = AdminDAO.getRemainingModuleList(ab);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String SaveModulePerRoleList(){	
		if(sessionMap.containsKey("userName")){
			boolean rslt = AdminDAO.saveModuleRoleRel(ab);
			
			if(rslt){
				modulePerRoleList = AdminDAO.getModuleListPerRole(ab);
				remainingModuleList = AdminDAO.getRemainingModuleList(ab);
				return SUCCESS;
			} else {
				modulePerRoleList = AdminDAO.getModuleListPerRole(ab);
				remainingModuleList = AdminDAO.getRemainingModuleList(ab);
				addFieldError("errorMsg", "Not saved, Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String updateModulePerRoleList(){
		if(sessionMap.containsKey("userName")){
			modulePerRoleList = AdminDAO.getModuleListPerRole(ab);
			remainingModuleList = AdminDAO.getRemainingModuleList(ab);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String UpdateModulePerRoleList(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = AdminDAO.updateModuleRoleRel(ab);
			
			if(rslt){
				modulePerRoleList = AdminDAO.getModuleListPerRole(ab);
				remainingModuleList = AdminDAO.getRemainingModuleList(ab);
				return SUCCESS;
			} else {
				modulePerRoleList = AdminDAO.getModuleListPerRole(ab);
				remainingModuleList = AdminDAO.getRemainingModuleList(ab);
				addFieldError("errorMsg", "Not saved, Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getHomeRoomTeacherAssign(){
		if(sessionMap.containsKey("userName")){
			teacherList = AdminDAO.getUnassignedTeacherList();
			classList = CRoomDAO.getClassList();
			assignTeacherList = AdminDAO.getAssignedHomeReoomTeacherList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getClassDetail(){
		if(sessionMap.containsKey("userName")){
			class_detail = StudentDAO.getClassDetail(ab.getClass_id(), (String) sessionMap.get("userName"));
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveTcherAssign(){		
		boolean rslt = AdminDAO.saveAssignedTeacherList(ab);
		if(rslt){
			teacherList = AdminDAO.getUnassignedTeacherList();
			classList = CRoomDAO.getClassList();
			assignTeacherList = AdminDAO.getAssignedHomeReoomTeacherList();
			return SUCCESS;
		} else {
			teacherList = AdminDAO.getUnassignedTeacherList();
			classList = CRoomDAO.getClassList();
			assignTeacherList = AdminDAO.getAssignedHomeReoomTeacherList();
			addFieldError("errMsg", "This grade is already taken by other teacher.");
			return ERROR;
		}
	}
	
	public String updateTcherAssignForm(){
		if(sessionMap.containsKey("userName")){
			teacherList = AdminDAO.getUnassignedTeacherList();
			classList = CRoomDAO.getClassList();
			techAssignmentInfo = AdminDAO.getTeacherAssignmentInfo(ab);
			ab.setThra_status(techAssignmentInfo.get(0).getThra_status());
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateTcherAssignment(){
		if(sessionMap.containsKey("userName")){
		boolean rslt = AdminDAO.updateTeacherAssignment(ab);
		if(rslt){
			teacherList = AdminDAO.getUnassignedTeacherList();
			classList = CRoomDAO.getClassList();
			assignTeacherList = AdminDAO.getAssignedHomeReoomTeacherList();
			return SUCCESS;
		} else {
			teacherList = AdminDAO.getUnassignedTeacherList();
			classList = CRoomDAO.getClassList();
			assignTeacherList = AdminDAO.getAssignedHomeReoomTeacherList();
			addFieldError("errMsg", "Update is not successful. Please try again later.");
			return ERROR;
		}
		} else {
			return INPUT;
		}
	}
	
	public String getUserPasswordResetForm(){
		if(sessionMap.containsKey("userName")){
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String resetUserPassword(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = AdminDAO.resetUserPassword(ab);
			
			if(rslt){
			
				teachUserList = AdminDAO.getTeachUserList();
				addActionMessage("Password reset successfully. Inform the user to log in with the default password and change it to his/her own.");
							
				return SUCCESS;
				
			} else {
				
				addActionMessage("Password reset is not successful, try again later.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String resetUserPasswordNoneAcademic(){
		
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = AdminDAO.resetUserPassword(ab);
			
			if(rslt){
			
				noneAcUserList = AdminDAO.getNoneAcademicUserList();
				addActionMessage("Password reset successfully. Inform the user to log in with the default password and change it to his/her own.");
							
				return SUCCESS;
			} else {
				
				addActionMessage("Password reset is not successful, try again later.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public AdminBean getModel() {
		return ab;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public List<AdminBean> getNoneAcUserList() {
		return noneAcUserList;
	}

	public void setNoneAcUserList(List<AdminBean> noneAcUserList) {
		this.noneAcUserList = noneAcUserList;
	}

	public List<AdminBean> getTeachUserList() {
		return teachUserList;
	}

	public void setTeachUserList(List<AdminBean> teachUserList) {
		this.teachUserList = teachUserList;
	}

	public List<AdminBean> getStudUserList() {
		return studUserList;
	}

	public void setStudUserList(List<AdminBean> studUserList) {
		this.studUserList = studUserList;
	}

	public List<AdminBean> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<AdminBean> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public List<AdminBean> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<AdminBean> teacherList) {
		this.teacherList = teacherList;
	}

	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}

	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}

	public List<AdminBean> getAllUserRoleList() {
		return allUserRoleList;
	}

	public void setAllUserRoleList(List<AdminBean> allUserRoleList) {
		this.allUserRoleList = allUserRoleList;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;		
	}

	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}

	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}

	public List<StudentBean> getGrade_db_rslt() {
		return grade_db_rslt;
	}

	public void setGrade_db_rslt(List<StudentBean> grade_db_rslt) {
		this.grade_db_rslt = grade_db_rslt;
	}

	public List<StudentBean> getDb_stud_persex() {
		return db_stud_persex;
	}

	public void setDb_stud_persex(List<StudentBean> db_stud_persex) {
		this.db_stud_persex = db_stud_persex;
	}

	public String getLoggedUserName() {
		return loggedUserName;
	}

	public void setLoggedUserName(String loggedUserName) {
		this.loggedUserName = loggedUserName;
	}

	public List<AdminBean> getLoginUserInfo() {
		return loginUserInfo;
	}

	public void setLoginUserInfo(List<AdminBean> loginUserInfo) {
		this.loginUserInfo = loginUserInfo;
	}

	public List<AdminBean> getAnnualTermList() {
		return annualTermList;
	}

	public void setAnnualTermList(List<AdminBean> annualTermList) {
		this.annualTermList = annualTermList;
	}

	public List<AdminBean> getUserTypeListTeacher() {
		return userTypeListTeacher;
	}

	public void setUserTypeListTeacher(List<AdminBean> userTypeListTeacher) {
		this.userTypeListTeacher = userTypeListTeacher;
	}

	public List<AdminBean> getModulePerRoleList() {
		return modulePerRoleList;
	}

	public void setModulePerRoleList(List<AdminBean> modulePerRoleList) {
		this.modulePerRoleList = modulePerRoleList;
	}

	public List<AdminBean> getRemainingModuleList() {
		return remainingModuleList;
	}

	public void setRemainingModuleList(List<AdminBean> remainingModuleList) {
		this.remainingModuleList = remainingModuleList;
	}

	public List<ClassScheduleBean> getClassSchedulePerTeacher() {
		return classSchedulePerTeacher;
	}

	public void setClassSchedulePerTeacher(List<ClassScheduleBean> classSchedulePerTeacher) {
		this.classSchedulePerTeacher = classSchedulePerTeacher;
	}

	public List<ClassScheduleBean> getCs_info() {
		return cs_info;
	}

	public void setCs_info(List<ClassScheduleBean> cs_info) {
		this.cs_info = cs_info;
	}

	public List<SchoolEventBean> getSeblist() {
		return seblist;
	}

	public void setSeblist(List<SchoolEventBean> seblist) {
		this.seblist = seblist;
	}

	public List<ExamScheduleBean> getExSchList() {
		return exSchList;
	}

	public void setExSchList(List<ExamScheduleBean> exSchList) {
		this.exSchList = exSchList;
	}

	public List<holidayBean> getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(List<holidayBean> holidayList) {
		this.holidayList = holidayList;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public List<CRoomBean> getClassList() {
		return classList;
	}

	public void setClassList(List<CRoomBean> classList) {
		this.classList = classList;
	}

	public List<StudentClassDetailBean> getClass_detail() {
		return class_detail;
	}

	public void setClass_detail(List<StudentClassDetailBean> class_detail) {
		this.class_detail = class_detail;
	}

	public List<AdminBean> getAssignTeacherList() {
		return assignTeacherList;
	}

	public void setAssignTeacherList(List<AdminBean> assignTeacherList) {
		this.assignTeacherList = assignTeacherList;
	}

	public List<AdminBean> getTchrList() {
		return tchrList;
	}

	public void setTchrList(List<AdminBean> tchrList) {
		this.tchrList = tchrList;
	}

	public List<AdminBean> getTechAssignmentInfo() {
		return techAssignmentInfo;
	}

	public void setTechAssignmentInfo(List<AdminBean> techAssignmentInfo) {
		this.techAssignmentInfo = techAssignmentInfo;
	}

	public List<NonteacherBean> getNon_acadamic_list() {
		return non_acadamic_list;
	}

	public void setNon_acadamic_list(List<NonteacherBean> non_acadamic_list) {
		this.non_acadamic_list = non_acadamic_list;
	}

	public List<StudentBean> getStudentNumPerYear() {
		return studentNumPerYear;
	}

	public void setStudentNumPerYear(List<StudentBean> studentNumPerYear) {
		this.studentNumPerYear = studentNumPerYear;
	}

	public List<TeacherBean> getTeacherNumPerSex() {
		return teacherNumPerSex;
	}

	public void setTeacherNumPerSex(List<TeacherBean> teacherNumPerSex) {
		this.teacherNumPerSex = teacherNumPerSex;
	}

	public String getOldUserName() {
		return oldUserName;
	}

	public void setOldUserName(String oldUserName) {
		this.oldUserName = oldUserName;
	}

	public List<List<ClassScheduleBean>> getSortedClassSchedulePerTeacher() {
		return sortedClassSchedulePerTeacher;
	}

	public void setSortedClassSchedulePerTeacher(List<List<ClassScheduleBean>> sortedClassSchedulePerTeacher) {
		this.sortedClassSchedulePerTeacher = sortedClassSchedulePerTeacher;
	}

}
