package behEvaluationClass;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import studentClass.StudentBean;
import studentClass.StudentClassBean;
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

public class BevalAction extends ActionSupport implements ModelDriven<BevalBean>, SessionAware{
	
	private static final long serialVersionUID = -5211910540843712099L;
	
	private List<AdminBean> usrRoleList;
	private List<CRoomBean> class_rslt;
	private Map<String, Object> sessionMap;
	private List<StudentClassBean> grade_rslt;
	private List<StudentClassDetailBean> class_detail;
	private List<StudentBean> stud_rslt;
	private List<BevalBean> stud_bevalrslt_list;
	private List<BevalBean> quarter_list;
	private List<BevalBean> btrait_list;
	private List<BevalBean> bgrade_list;
	private List<ExamResultBean> sem_list;
	
	BevalBean bvb = new BevalBean();

	public String getBehEvalTemp(){
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Behavioural Evaluation";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
					
			if(sessionMap.get("loggedInUserType").equals("THCR")){
				
				String ti_id = (String) sessionMap.get("ti_id");
				
				grade_rslt = StudentDAO.getRelatedClassList(ti_id);
				
			} else {
				
				grade_rslt = StudentDAO.getClassList();	
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getClassDetailList(){
		if (sessionMap.containsKey("userName")) {
			
			if(sessionMap.get("loggedInUserType").equals("THCR")){
				
				String ti_id = (String) sessionMap.get("ti_id");
				
				class_detail = StudentDAO.getClassDetailTeacherRelated(bvb.getCl_id(), ti_id, (String)sessionMap.get("userName"));
				
			} else {
				
				class_detail = StudentDAO.getClassDetail(bvb.getCl_id(), (String)sessionMap.get("userName"));
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentListPerGrade(){
		if (sessionMap.containsKey("userName")) {
										
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			//quarter_list = BevalDAO.getQuarterList();
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			stud_rslt = StudentDAO.getListPerGradeDetail(bvb.getCl_id(), bvb.getCd_id(), yr);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentBevalList(){
		if (sessionMap.containsKey("userName")) {
			
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			bvb.setAc_year(yr);
			//quarter_list = BevalDAO.getQuarterList();
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			for(int i = 0; i < sem_list.size(); i++){
				if(bvb.getAt_id().equals(sem_list.get(i).getAt_id())){
					bvb.setAt_name(sem_list.get(i).getAt_name());
				}
			}
			
			btrait_list = BevalDAO.getBevelTraitList(bvb);
			
			stud_bevalrslt_list = BevalDAO.getStudentBevalRsltList(bvb);
			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String getBevalGradeList(){
		if (sessionMap.containsKey("userName")) {
			
			bgrade_list = BevalDAO.getBevelGradeList(bvb);
			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String saveBevalGradeFromJS(){
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = BevalDAO.saveBevalGradeFromJS(bvb);
			
			btrait_list = BevalDAO.getBevelTraitList(bvb);
			
			stud_bevalrslt_list = BevalDAO.getStudentBevalRsltList(bvb);
			
			if(rslt){				
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String saveBevalGrade(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = BevalDAO.saveBevalGrade(bvb);
			
			btrait_list = BevalDAO.getBevelTraitList(bvb);
			
			stud_bevalrslt_list = BevalDAO.getStudentBevalRsltList(bvb);
			
			if(rslt){				
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String bevalRsltEditForm(){
		if (sessionMap.containsKey("userName")) {			
			
			btrait_list = BevalDAO.getBevelTraitEditList(bvb);
			bgrade_list = BevalDAO.getBevelGradeEditList(bvb);
										
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String updateDevalRslt(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = BevalDAO.updateBevalGrade(bvb);
			
			btrait_list = BevalDAO.getBevelTraitList(bvb);
			stud_bevalrslt_list = BevalDAO.getStudentBevalRsltList(bvb);
			
			if(rslt){				
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	@Override
	public BevalBean getModel() {
		return bvb;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}

	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}

	public List<CRoomBean> getClass_rslt() {
		return class_rslt;
	}

	public void setClass_rslt(List<CRoomBean> class_rslt) {
		this.class_rslt = class_rslt;
	}

	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}

	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}

	public List<StudentClassDetailBean> getClass_detail() {
		return class_detail;
	}

	public void setClass_detail(List<StudentClassDetailBean> class_detail) {
		this.class_detail = class_detail;
	}

	public List<StudentBean> getStud_rslt() {
		return stud_rslt;
	}

	public void setStud_rslt(List<StudentBean> stud_rslt) {
		this.stud_rslt = stud_rslt;
	}

	public List<BevalBean> getStud_bevalrslt_list() {
		return stud_bevalrslt_list;
	}

	public void setStud_bevalrslt_list(List<BevalBean> stud_bevalrslt_list) {
		this.stud_bevalrslt_list = stud_bevalrslt_list;
	}

	public List<BevalBean> getQuarter_list() {
		return quarter_list;
	}

	public void setQuarter_list(List<BevalBean> quarter_list) {
		this.quarter_list = quarter_list;
	}

	public List<BevalBean> getBtrait_list() {
		return btrait_list;
	}

	public void setBtrait_list(List<BevalBean> btrait_list) {
		this.btrait_list = btrait_list;
	}

	public List<BevalBean> getBgrade_list() {
		return bgrade_list;
	}

	public void setBgrade_list(List<BevalBean> bgrade_list) {
		this.bgrade_list = bgrade_list;
	}

	public List<ExamResultBean> getSem_list() {
		return sem_list;
	}

	public void setSem_list(List<ExamResultBean> sem_list) {
		this.sem_list = sem_list;
	}

}
