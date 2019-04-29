package preExamQstChoiceClass;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

import preExamQstClass.PreExamBean;
import preExamQstClass.PreExamDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PreExamQChoiceAction extends ActionSupport implements ModelDriven<PreExamQChoiceBean>, SessionAware{

	private static final long serialVersionUID = 1L;
	PreExamQChoiceBean pqb = new PreExamQChoiceBean();
	PreExamBean peb = new PreExamBean();
	private List<PreExamBean> qst;
	
	private File fileUpload;
	private String fileUploadFileName;
	private String fileUploadContentType;
	
	private List<PreExamQChoiceBean> qstChoiceList;
	private Map<String, Object> sessionMap;

	public String qstChoiceSave(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PreExamQChoiceDAO.saveQuestionChoice(pqb);
			if(rslt){
				qstChoiceList = PreExamQChoiceDAO.getPreExamQChoiceList(pqb.getPeq_id());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}				
	}
	
	public String qstChoiceUpdateForm(){
		if (sessionMap.containsKey("userName")) {
			int in = Integer.parseInt(pqb.getIndx());
			qstChoiceList = PreExamQChoiceDAO.getPreExamQChoiceList(pqb.getPeq_id());
			
			pqb.setPec_content(qstChoiceList.get(in).getPec_content());
			pqb.setPec_correct(qstChoiceList.get(in).getPec_correct());
			pqb.setPec_image(qstChoiceList.get(in).getPec_image());
			pqb.setPec_status(qstChoiceList.get(in).getPec_status());
			pqb.setPec_id(qstChoiceList.get(in).getPec_id());
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String qstChoiceUpdate(){
		if (sessionMap.containsKey("userName")) {
			peb.setPeq_id(pqb.getPeq_id());
			qst = PreExamDAO.getQuestion(peb);
			
			boolean rslt = PreExamQChoiceDAO.updateQstChoice(pqb);
			if(rslt){
				qstChoiceList = PreExamQChoiceDAO.getPreExamQChoiceList(pqb.getPeq_id());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}				
	}
	
	public String qstChoiceDocUpload(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PreExamQChoiceDAO.uploadQstPic(pqb, fileUploadFileName);
			System.out.println(rslt);
	        if(rslt){
	        	File saveFilePath = new File("E:/eclipse_workspace/SMS/WebContent/ExamDocument/ChoiceImages/" +	fileUploadFileName);		
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
	public PreExamQChoiceBean getModel() {
		return pqb;
	}
	public List<PreExamQChoiceBean> getQstChoiceList() {
		return qstChoiceList;
	}
	public void setQstChoiceList(List<PreExamQChoiceBean> qstChoiceList) {
		this.qstChoiceList = qstChoiceList;
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

	public List<PreExamBean> getQst() {
		return qst;
	}

	public void setQst(List<PreExamBean> qst) {
		this.qst = qst;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	
}
