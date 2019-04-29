package preExamDesc;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PreExamDescAction extends ActionSupport implements ModelDriven<PreExamDescBean>, SessionAware{

	private static final long serialVersionUID = 1L;
	
	PreExamDescBean ped = new PreExamDescBean();
	private List<PreExamDescBean> qdList;
	private List<PreExamDescBean> qst;
	private Map<String, Object> sessionMap;
	
	private File fileUpload;
	private String fileUploadFileName;
	private String fileUploadContentType;
	
	public String qstDescList(){
		if (sessionMap.containsKey("userName")) {
			qst = PreExamDescDAO.getQst(ped);
			qdList = PreExamDescDAO.getQstDesc(ped);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String qstDescCrtFrm(){
		if (sessionMap.containsKey("userName")) {
			qst = PreExamDescDAO.getQst(ped);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String qstDescSave(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PreExamDescDAO.saveQstDesc(ped);
			if(rslt){
				qst = PreExamDescDAO.getQst(ped);
				qdList = PreExamDescDAO.getQstDesc(ped);
				return SUCCESS;
			} else {
				qst = PreExamDescDAO.getQst(ped);
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String qstDescEdtFrm(){
		if (sessionMap.containsKey("userName")) {
			qst = PreExamDescDAO.getQst(ped);
			qdList = PreExamDescDAO.getQstDesc(ped);
			Integer x = Integer.parseInt(ped.getIndx());		
			
			ped.setExplanation_dtl(qdList.get(x).getExplanation_dtl());
			ped.setSuggested_material(qdList.get(x).getSuggested_material());
			ped.setPee_status(qdList.get(x).getPee_status());
					
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}	
	
	public String qstDescUpdate(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PreExamDescDAO.updateQstDesc(ped);
			if(rslt){
				qst = PreExamDescDAO.getQst(ped);
				qdList = PreExamDescDAO.getQstDesc(ped);
				return SUCCESS;
			} else {
				qst = PreExamDescDAO.getQst(ped);
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String qstDescDocUploadFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String qstDescDocUpload(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PreExamDescDAO.uploadQstDescPic(ped, fileUploadFileName);
	        if(rslt){
	        	File saveFilePath = new File("E:/eclipse_workspace/SMS/WebContent/ExamDocument/DescriptionImages/" +	fileUploadFileName);		
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
	public PreExamDescBean getModel() {
		return ped;
	}
	public List<PreExamDescBean> getQdList() {
		return qdList;
	}
	public void setQdList(List<PreExamDescBean> qdList) {
		this.qdList = qdList;
	}
	public List<PreExamDescBean> getQst() {
		return qst;
	}
	public void setQst(List<PreExamDescBean> qst) {
		this.qst = qst;
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

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}
