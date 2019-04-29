package adminClass.curriculum_activity;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CurriculumAct_Action extends ActionSupport implements ModelDriven<CurriculumAct_Bean>, SessionAware{

	private static final long serialVersionUID = -7831976075262410893L;
	
	private Map<String, Object> sessionMap;
	private List<CurriculumAct_Bean> depList;
	private List<CurriculumAct_Bean> clList;
	private List<CurriculumAct_Bean> tchList;
	private List<CurriculumAct_Bean> tchRoleList;
	private List<CurriculumAct_Bean> respList;
	private List<CurriculumAct_Bean> assignTchRoleList;
	private List<Integer> acYearList;
	
	CurriculumAct_Bean cab = new CurriculumAct_Bean();

	public String getCocurActTemplate(){
		if (sessionMap.containsKey("userName")) {			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getDepartmentList(){
		if (sessionMap.containsKey("userName")) {
			depList = CurriculumAct_DAO.getDepartmentList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveDepartment(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = CurriculumAct_DAO.saveDepartment(cab);
			if(rslt){
				depList = CurriculumAct_DAO.getDepartmentList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String departmentEditForm(){
		if (sessionMap.containsKey("userName")) {
			depList = CurriculumAct_DAO.getDepartmentList();
			for(int i = 0; i < depList.size(); i++){
				if(depList.get(i).getDep_id().equalsIgnoreCase(cab.getDep_id())){
					cab.setDep_name(depList.get(i).getDep_name());
					cab.setDep_desc(depList.get(i).getDep_desc());
					cab.setDep_status(depList.get(i).getDep_status());
				}
			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateDepartment(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = CurriculumAct_DAO.updateDepartment(cab);
			if(rslt){
				depList = CurriculumAct_DAO.getDepartmentList();
				return SUCCESS;
			} else {
				depList = CurriculumAct_DAO.getDepartmentList();
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getClubList(){
		if (sessionMap.containsKey("userName")) {
			clList = CurriculumAct_DAO.getClubList();
			depList = CurriculumAct_DAO.getDepartmentList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveClub(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = CurriculumAct_DAO.saveClub(cab);
			if(rslt){
				clList = CurriculumAct_DAO.getClubList();
				depList = CurriculumAct_DAO.getDepartmentList();
				return SUCCESS;
			} else {
				depList = CurriculumAct_DAO.getDepartmentList();
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String clubEditForm(){
		if (sessionMap.containsKey("userName")) {
			clList = CurriculumAct_DAO.getClubList();
			depList = CurriculumAct_DAO.getDepartmentList();
			for(int i = 0; i < clList.size(); i++){
				if(clList.get(i).getClb_id().equalsIgnoreCase(cab.getClb_id())){
					cab.setClb_name(clList.get(i).getClb_name());
					cab.setClb_desc(clList.get(i).getClb_desc());
					cab.setClb_status(clList.get(i).getClb_status());
					cab.setDep_id(clList.get(i).getDep_id());
				}
			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateClub(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = CurriculumAct_DAO.updateClub(cab);
			if(rslt){
				clList = CurriculumAct_DAO.getClubList();
				depList = CurriculumAct_DAO.getDepartmentList();
				return SUCCESS;
			} else {
				clList = CurriculumAct_DAO.getClubList();
				depList = CurriculumAct_DAO.getDepartmentList();
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
		
	public String getTeacherRoleList(){
		if (sessionMap.containsKey("userName")) {			
			
			tchList = CurriculumAct_DAO.getTeacherList();
			tchRoleList = CurriculumAct_DAO.getTeacherRoleList();
			acYearList = CurriculumAct_DAO.getYearList();
			
			assignTchRoleList = CurriculumAct_DAO.getAssignTchRespList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String responsibilityList(){
		if (sessionMap.containsKey("userName")) {			
			
			cab.setRole_id(cab.getRole_id());
			respList = CurriculumAct_DAO.getResponsibilityList(cab);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveTeacherRole(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = CurriculumAct_DAO.saveTeacherResponsibility(cab);
			if(rslt){
				tchList = CurriculumAct_DAO.getTeacherList();
				tchRoleList = CurriculumAct_DAO.getTeacherRoleList();
				acYearList = CurriculumAct_DAO.getYearList();
				
				assignTchRoleList = CurriculumAct_DAO.getAssignTchRespList();				

				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String tchrroleEditForm(){
		if (sessionMap.containsKey("userName")) {
			
			tchList = CurriculumAct_DAO.getAllTeacherList();
			tchRoleList = CurriculumAct_DAO.getTeacherRoleList();
			acYearList = CurriculumAct_DAO.getYearList();
			
			assignTchRoleList = CurriculumAct_DAO.getAssignTchRespList();
			
			for(int i = 0; i < assignTchRoleList.size(); i++){
				if(cab.getTr_id().equalsIgnoreCase(assignTchRoleList.get(i).getTr_id())){
					cab.setTi_id(assignTchRoleList.get(i).getTi_id());
					cab.setRole_id(assignTchRoleList.get(i).getRole_id());
					if(assignTchRoleList.get(i).getRole_id().equalsIgnoreCase("1")){
						cab.setDep_id(assignTchRoleList.get(i).getDep_id());
					}
					if(assignTchRoleList.get(i).getRole_id().equalsIgnoreCase("2")){
						cab.setDep_id(assignTchRoleList.get(i).getClb_id());
					}
					cab.setAcademic_year(assignTchRoleList.get(i).getAcademic_year());
					cab.setTr_status(assignTchRoleList.get(i).getTr_status());
				}
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateTeacherRole(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = CurriculumAct_DAO.updateTeacherResponsibility(cab);
			if(rslt){
				tchList = CurriculumAct_DAO.getTeacherList();
				tchRoleList = CurriculumAct_DAO.getTeacherRoleList();
				acYearList = CurriculumAct_DAO.getYearList();
				
				assignTchRoleList = CurriculumAct_DAO.getAssignTchRespList();				

				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	@Override
	public CurriculumAct_Bean getModel() {		
		return cab;
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
	public List<CurriculumAct_Bean> getDepList() {
		return depList;
	}
	public void setDepList(List<CurriculumAct_Bean> depList) {
		this.depList = depList;
	}

	public List<CurriculumAct_Bean> getClList() {
		return clList;
	}

	public void setClList(List<CurriculumAct_Bean> clList) {
		this.clList = clList;
	}

	public List<CurriculumAct_Bean> getTchList() {
		return tchList;
	}

	public void setTchList(List<CurriculumAct_Bean> tchList) {
		this.tchList = tchList;
	}

	public List<CurriculumAct_Bean> getTchRoleList() {
		return tchRoleList;
	}

	public void setTchRoleList(List<CurriculumAct_Bean> tchRoleList) {
		this.tchRoleList = tchRoleList;
	}

	public List<Integer> getAcYearList() {
		return acYearList;
	}

	public void setAcYearList(List<Integer> acYearList) {
		this.acYearList = acYearList;
	}

	public List<CurriculumAct_Bean> getRespList() {
		return respList;
	}

	public void setRespList(List<CurriculumAct_Bean> respList) {
		this.respList = respList;
	}

	public List<CurriculumAct_Bean> getAssignTchRoleList() {
		return assignTchRoleList;
	}

	public void setAssignTchRoleList(List<CurriculumAct_Bean> assignTchRoleList) {
		this.assignTchRoleList = assignTchRoleList;
	}
}
