package preExamQstClass;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

import preExamQstChoiceClass.PreExamQChoiceBean;
import preExamQstChoiceClass.PreExamQChoiceDAO;

import subjectClass.SubjectBean;
import subjectClass.SubjectDAO;
import util.RoleValidator;

import adminClass.AdminBean;
import adminClass.AdminDAO;
import cRoomClass.CRoomBean;
import cRoomClass.CRoomDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PreExamAction extends ActionSupport implements ModelDriven<PreExamBean>, SessionAware{
	
	private static final long serialVersionUID = 1L;
	PreExamBean peb = new PreExamBean();
	
	private String menutype = "examqst";
	private List<CRoomBean> grdList;
	private List<SubjectBean> subList;
	private List<PreExamBean> ExamCat;
	private List<PreExamBean> examCatList;
	private List<PreExamBean> qstList;
	private List<PreExamBean> catInfo;
	private List<PreExamBean> qst;
	private List<PreExamQChoiceBean> qstChoiceList;
	
	private List<AdminBean> usrRoleList;
	private Map<String, Object> sessionMap;
	
	private File fileUpload;
	private String fileUploadFileName;
	private String fileUploadContentType;

	public String getPreExamList(){
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Exam Question";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				return "ACCESSDENIED";
			}
		}
				
		if (sessionMap.containsKey("userName")) {
            loggedUserName = (String) sessionMap.get("userName");        
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			grdList = CRoomDAO.getClassList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String questionFrom(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String subjectList(){
		if (sessionMap.containsKey("userName")) {
			subList = SubjectDAO.getSubjectListPerClass(peb);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String subjectExamCatList(){
		if (sessionMap.containsKey("userName")) {
			ExamCat = PreExamDAO.getExamCategory(peb);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String subjectQstList(){
		if (sessionMap.containsKey("userName")) {
			catInfo = PreExamDAO.getCatInfo(peb);
			qstList = PreExamDAO.getExamQstList(peb);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String saveQst(){
		if (sessionMap.containsKey("userName")) {
			boolean validateNoOfQst = PreExamDAO.validateMaxQstNo(peb);
			if(validateNoOfQst){
				boolean rslt = PreExamDAO.savePreExamQst(peb);
				if(rslt){
					qstList = PreExamDAO.getExamQstList(peb);
					return SUCCESS;
				} else {
					return ERROR;
				}
			} else {
				qstList = PreExamDAO.getExamQstList(peb);
				addFieldError("errorMsg", "This category reach the max allowed number of questions!!!");
				return SUCCESS;
			}
		} else {
			return INPUT;
		}		
	}	
	
	public String questionUpdateForm(){
		if (sessionMap.containsKey("userName")) {
			qstList = PreExamDAO.getExamQstList(peb);
			int in = Integer.parseInt(peb.getIndx());
			
			peb.setPeq_id(qstList.get(in).getPeq_id());
			peb.setPeq_content(qstList.get(in).getPeq_content());
			peb.setPeq_image(qstList.get(in).getPeq_image());
			peb.setPeq_status(qstList.get(in).getPeq_status());
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String updateQst(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PreExamDAO.updatePreExamQst(peb);
			if(rslt){
				qstList = PreExamDAO.getExamQstList(peb);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String examCatCreateForm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String examCatList(){
		examCatList = PreExamDAO.getExamCatList(peb);
		return SUCCESS;
	}
	
	public String saveQstCat(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PreExamDAO.saveExamCategory(peb);
			if(rslt){
				ExamCat = PreExamDAO.getExamCategory(peb);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String examCatUpdateForm(){
		if (sessionMap.containsKey("userName")) {
			examCatList = PreExamDAO.getExamCatList(peb);
			int in = Integer.parseInt(peb.getIndx());
			
			peb.setExam_level(examCatList.get(in).getExam_level());
			peb.setNumber_of_qst(examCatList.get(in).getNumber_of_qst());
			peb.setTotal_time_allowed(examCatList.get(in).getTotal_time_allowed());
			peb.setPet_status(examCatList.get(in).getPet_status());
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String updateQstCat(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PreExamDAO.updateExamCategory(peb);
			if(rslt){
				examCatList = PreExamDAO.getExamCatList(peb);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String qstChoiceList(){
		if (sessionMap.containsKey("userName")) {
			qst = PreExamDAO.getQuestion(peb);
			qstChoiceList = PreExamQChoiceDAO.getPreExamQChoiceList(peb.getPeq_id());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String qstChoiceCreateForm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String qstDocUpload(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PreExamDAO.uploadQstPic(peb, fileUploadFileName);
	        if(rslt){
	        	File saveFilePath = new File("E:/eclipse_workspace/SMS/WebContent/ExamDocument/QuestionImages/" +	fileUploadFileName);		
				try {
					FileUtils.copyFile(fileUpload, saveFilePath);
				} catch (IOException ex) {
					System.out.println("Couldn't save file: " +	ex.getMessage());
				}
				return SUCCESS;
	        } else {
	        	return ERROR;
	        }
		} else {
			return INPUT;
		}		
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public PreExamBean getModel() {
		return peb;
	}


	public String getMenutype() {
		return menutype;
	}


	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}


	public List<CRoomBean> getGrdList() {
		return grdList;
	}


	public void setGrdList(List<CRoomBean> grdList) {
		this.grdList = grdList;
	}

	public List<SubjectBean> getSubList() {
		return subList;
	}

	public void setSubList(List<SubjectBean> subList) {
		this.subList = subList;
	}

	public List<PreExamBean> getExamCat() {
		return ExamCat;
	}

	public void setExamCat(List<PreExamBean> examCat) {
		ExamCat = examCat;
	}

	public List<PreExamBean> getQstList() {
		return qstList;
	}

	public void setQstList(List<PreExamBean> qstList) {
		this.qstList = qstList;
	}

	public List<PreExamBean> getCatInfo() {
		return catInfo;
	}

	public void setCatInfo(List<PreExamBean> catInfo) {
		this.catInfo = catInfo;
	}

	public List<PreExamBean> getExamCatList() {
		return examCatList;
	}

	public void setExamCatList(List<PreExamBean> examCatList) {
		this.examCatList = examCatList;
	}

	public List<PreExamBean> getQst() {
		return qst;
	}

	public void setQst(List<PreExamBean> qst) {
		this.qst = qst;
	}

	public List<PreExamQChoiceBean> getQstChoiceList() {
		return qstChoiceList;
	}

	public void setQstChoiceList(List<PreExamQChoiceBean> qstChoiceList) {
		this.qstChoiceList = qstChoiceList;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap(sessionMap);
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

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	
}
