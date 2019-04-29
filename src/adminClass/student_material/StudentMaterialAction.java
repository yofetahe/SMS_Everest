package adminClass.student_material;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cRoomClass.CRoomBean;
import cRoomClass.CRoomDAO;

public class StudentMaterialAction extends ActionSupport implements ModelDriven<StudentMaterialBean>, SessionAware {

	private static final long serialVersionUID = 4427966671185473783L;
	
	private Map<String, Object> sessionMap;
	StudentMaterialBean smb = new StudentMaterialBean();
	
	private List<StudentMaterialBean> materialList;
	private List<StudentMaterialBean> materialClassRelList;
	private List<CRoomBean> classList;
	private List<StudentMaterialBean> activeMatList;
	
	public String studentMaterialTemplate(){
		if(sessionMap.containsKey("userName")){
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentMaterialList(){
		if(sessionMap.containsKey("userName")){
			materialList = StudentMaterialDAO.getMaterialList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	} 
	
	public String saveStudentMaterial(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = StudentMaterialDAO.saveStudentMaterial(smb);
			if(rslt){
				materialList = StudentMaterialDAO.getMaterialList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String updateStudentMaterialForm(){
		if(sessionMap.containsKey("userName")){
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateStudentMaterial(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = StudentMaterialDAO.updateStudentMaterial(smb);
			if(rslt){
				materialList = StudentMaterialDAO.getMaterialList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String studentMaterialRelList(){
		if(sessionMap.containsKey("userName")){
			materialClassRelList = StudentMaterialDAO.getMaterialClassRelList();
			classList = CRoomDAO.getClassList();
			activeMatList = StudentMaterialDAO.getActiveMaterialList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveMaterialClassRelList(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = StudentMaterialDAO.saveMaterialClassRel(smb);
			
			materialClassRelList = StudentMaterialDAO.getMaterialClassRelList();
			classList = CRoomDAO.getClassList();
			activeMatList = StudentMaterialDAO.getActiveMaterialList();
			
			if(rslt){				
				return SUCCESS;
			} else {
				addFieldError("eMsg", "The data is already exist.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String updateMaterialClassRelForm(){
		if(sessionMap.containsKey("userName")){
			classList = CRoomDAO.getClassList();
			activeMatList = StudentMaterialDAO.getActiveMaterialList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateMaterialClassRel(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = StudentMaterialDAO.updateMaterialClassRel(smb);
			
			materialClassRelList = StudentMaterialDAO.getMaterialClassRelList();
			classList = CRoomDAO.getClassList();
			activeMatList = StudentMaterialDAO.getActiveMaterialList();
			
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
	public StudentMaterialBean getModel() {		
		return smb;
	}

	public List<StudentMaterialBean> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<StudentMaterialBean> materialList) {
		this.materialList = materialList;
	}

	public List<StudentMaterialBean> getMaterialClassRelList() {
		return materialClassRelList;
	}

	public void setMaterialClassRelList(List<StudentMaterialBean> materialClassRelList) {
		this.materialClassRelList = materialClassRelList;
	}

	public List<CRoomBean> getClassList() {
		return classList;
	}

	public void setClassList(List<CRoomBean> classList) {
		this.classList = classList;
	}

	public List<StudentMaterialBean> getActiveMatList() {
		return activeMatList;
	}

	public void setActiveMatList(List<StudentMaterialBean> activeMatList) {
		this.activeMatList = activeMatList;
	}

}
