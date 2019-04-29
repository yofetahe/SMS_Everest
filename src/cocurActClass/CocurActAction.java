/*
 * Co-curriculum activity module can only be active for teachers assigned to specific club or department.
 * the activity list will be displayed for any authorized users working under one of the active clubs 
 * but if the authorized users don't have relation to any of the active clubs, he wouldn't allow to 
 * insert new activity, update the activities, register member, give comment on the activities.
 * 
 * Assumption: one teacher only involve in one club. Therefore, the system will pick the related club id with
 * the logged in user to create relationship between the new inserted activity and the clubs. 
 * And also the teacher only register members to the club he is controlling.
 * 
 * Teachers or other stuff also can participate in the club. but their registration is done under the admin page
 * of co_curriculum page teacher_Role_assignment tab. and their list included under the member list but is not
 * editable. Evaluation result filled using EVALUATE button on the member list table.
 */

package cocurActClass;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import studentClass.StudentBean;
import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import util.RoleValidator;

import adminClass.AdminBean;
import adminClass.AdminDAO;
import adminClass.curriculum_activity.CurriculumAct_DAO;

import cRoomClass.CRoomBean;
import cRoomClass.CRoomDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CocurActAction extends ActionSupport implements ModelDriven<CocurActBean>, SessionAware {

	private static final long serialVersionUID = 3868248728459561294L;
	
	private String menutype = "cocuract";
	
	private List<AdminBean> usrRoleList;
	private List<Integer> acYearList;
	private List<CocurActBean> activityList;
	private List<CocurActBean> memberList;
	private List<CRoomBean> class_rslt;
	private List<StudentClassDetailBean> class_detail;
	private List<StudentBean> stud_list;
	
	private String clbid;
	private String clbname;
	
	private Map<String, Object> sessionMap;
	
	CocurActBean cab = new CocurActBean();

	public String getCocuractTemp(){
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Co-curriculum Activity";
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
	
	public String getClubId(){
		String clbid = "";
		
		String uname = (String) sessionMap.get("userName");
		boolean unameIsRoot = CocurActDAO.checkUserType(uname);
		
		if(!unameIsRoot){
			clbid = CocurActDAO.getClidByLoginUser(uname);
		}
		
		return clbid;
	}
	
	public String getCocuractActivityTemp(){
		if (sessionMap.containsKey("userName")) {			
			cab.setClb_id(getClubId());			
			activityList = CocurActDAO.getActivityList(cab);
			if(activityList.size() == 0 && cab.getClb_id().length() != 0){
				cab.setEditStatus(true);
			}			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractActivityCreateForm(){
		if (sessionMap.containsKey("userName")) {
			acYearList = CurriculumAct_DAO.getYearList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractActivitySave(){
		if (sessionMap.containsKey("userName")) {
			cab.setClb_id(getClubId());
			boolean rslt = CocurActDAO.saveClubActivity(cab);
			
			if(rslt){
				activityList = CocurActDAO.getActivityList(cab);
				acYearList = CurriculumAct_DAO.getYearList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractActivityUpdateForm(){
		if (sessionMap.containsKey("userName")) {
			acYearList = CurriculumAct_DAO.getYearList();
			
			activityList = CocurActDAO.getActivityList(cab);
			
			for(int i = 0; i < activityList.size(); i++){
				if(activityList.get(i).getCa_id().equalsIgnoreCase(cab.getCa_id())){
					cab.setCa_id(cab.getCa_id());
					cab.setCa_activity(activityList.get(i).getCa_activity());
					cab.setCa_activity_desc(activityList.get(i).getCa_activity_desc());
					cab.setCa_datefrom(activityList.get(i).getCa_datefrom());
					cab.setCa_dateto(activityList.get(i).getCa_dateto());
					cab.setAcademic_year(activityList.get(i).getAcademic_year());
					cab.setCa_status(activityList.get(i).getCa_status());
				}
			}
			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractActivityUpdate(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = CocurActDAO.updateClubActivity(cab);
			if(rslt){				
				activityList = CocurActDAO.getActivityList(cab);
				return SUCCESS;
			} else {
				acYearList = CurriculumAct_DAO.getYearList();
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	
	public String getCocuractMemberTemp(){
		if (sessionMap.containsKey("userName")) {
			String username = (String) sessionMap.get("userName");
			clbid = CocurActDAO.getClidByLoginUser(username);			
			cab.setClb_id(clbid);
			if(clbid.length() != 0){
				cab.setEditStatus(true);
			}
			memberList = CocurActDAO.getMemberList(clbid);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractMemberCreateForm(){
		if (sessionMap.containsKey("userName")) {
			class_rslt = CRoomDAO.getClassList();
			cab.setClbid(cab.getClbid());
			cab.setClbname(cab.getClbname());
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getClassDetailList(){
		class_detail = StudentDAO.getClassDetail(cab.getCl_id(), (String)sessionMap.get("userName"));
		return SUCCESS;
	}
	
	public String getStudentList(){
		
		if (sessionMap.containsKey("userName")) {
			//getting academic year --- starting from May to August the academic year consider the next year
			//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
						
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int year = cal.get(Calendar.YEAR);
			String yr = util.DateConvertor.dateConvertor(day+"-"+month+"-"+year);
			
			stud_list = StudentDAO.getListPerGradeDetail(cab.getCl_id(), cab.getCd_id(), yr);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractMemberSave(){
		if (sessionMap.containsKey("userName")) {
			//getting academic year --- starting from May to August the academic year consider the next year
			//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
						
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int year = cal.get(Calendar.YEAR);
			String yr = util.DateConvertor.dateConvertor(day+"-"+month+"-"+year);
			
			cab.setCm_acyear(yr);
			
			boolean rslt = CocurActDAO.saveClubMembers(cab);
			if(rslt){
				memberList = CocurActDAO.getMemberList(cab.getClbid());
				return SUCCESS;
			} else {
				addFieldError("errorMsg", "Member is not saved. the system crushed. pls try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractMemberEditForm(){
		if (sessionMap.containsKey("userName")) {						
			memberList = CocurActDAO.getMemberList(cab.getClbid());
			
			for(int i = 0; i < memberList.size(); i++){
				if(memberList.get(i).getCm_id().equalsIgnoreCase(cab.getCm_id())){
					cab.setM_id(memberList.get(i).getM_id());
					cab.setCm_reasontojoin(memberList.get(i).getCm_reasontojoin());
					cab.setCm_evaluation(memberList.get(i).getCm_evaluation());
					cab.setCm_status(memberList.get(i).getCm_status());
					cab.setMem_name(memberList.get(i).getMem_name());
				}
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractMemberEvalForm(){
		if (sessionMap.containsKey("userName")) {
			
			memberList = CocurActDAO.getMemberList(cab.getClbid());
			
			for(int i = 0; i < memberList.size(); i++){
				if(memberList.get(i).getCm_id().equalsIgnoreCase(cab.getCm_id())){
					cab.setM_id(memberList.get(i).getM_id());
					cab.setCm_reasontojoin(memberList.get(i).getCm_reasontojoin());
					cab.setCm_evaluation(memberList.get(i).getCm_evaluation());
					cab.setCm_status(memberList.get(i).getCm_status());
					cab.setMem_name(memberList.get(i).getMem_name());
				}
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractMemberEvalResult(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = CocurActDAO.addClubMemberEvaluation(cab);
			if(rslt){
				memberList = CocurActDAO.getMemberList(cab.getClbid());
				cab.setClb_id(cab.getClbid());
				return SUCCESS;
			} else {
				addFieldError("errorMsg", "Member is not updated. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractMemberUpdate(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = CocurActDAO.updateClubMember(cab);
			if(rslt){
				memberList = CocurActDAO.getMemberList(cab.getClbid());
				cab.setClb_id(cab.getClbid());
				return SUCCESS;
			} else {
				addFieldError("errorMsg", "Member is not updated. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getCocuractActivityControlTemp(){
		if (sessionMap.containsKey("userName")) {
			cab.setClb_id(getClubId());
			activityList = CocurActDAO.getActivityList(cab);			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveClubHeadComment(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = CocurActDAO.saveClubHeadComment(cab);
			
			if(rslt){
				cab.setClb_id(getClubId());
				activityList = CocurActDAO.getActivityList(cab);			
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String updateClubHeadComment(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = CocurActDAO.saveClubHeadComment(cab);
			
			if(rslt){
				cab.setClb_id(getClubId());
				activityList = CocurActDAO.getActivityList(cab);			
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}
	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	@Override
	public CocurActBean getModel() {
		return cab;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public String getMenutype() {
		return menutype;
	}
	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public List<Integer> getAcYearList() {
		return acYearList;
	}

	public void setAcYearList(List<Integer> acYearList) {
		this.acYearList = acYearList;
	}

	public List<CocurActBean> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<CocurActBean> activityList) {
		this.activityList = activityList;
	}

	public List<CocurActBean> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<CocurActBean> memberList) {
		this.memberList = memberList;
	}

	public String getClbid() {
		return clbid;
	}

	public void setClbid(String clbid) {
		this.clbid = clbid;
	}

	public String getClbname() {
		return clbname;
	}

	public void setClbname(String clbname) {
		this.clbname = clbname;
	}

	public List<CRoomBean> getClass_rslt() {
		return class_rslt;
	}

	public void setClass_rslt(List<CRoomBean> class_rslt) {
		this.class_rslt = class_rslt;
	}

	public List<StudentClassDetailBean> getClass_detail() {
		return class_detail;
	}

	public void setClass_detail(List<StudentClassDetailBean> class_detail) {
		this.class_detail = class_detail;
	}

	public List<StudentBean> getStud_list() {
		return stud_list;
	}

	public void setStud_list(List<StudentBean> stud_list) {
		this.stud_list = stud_list;
	}
	
}
