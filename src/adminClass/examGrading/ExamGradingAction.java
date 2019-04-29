package adminClass.examGrading;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ExamGradingAction extends ActionSupport implements ModelDriven<ExamGradingBean>, SessionAware {

	
	private static final long serialVersionUID = 7415851268062375355L;
	ExamGradingBean egb = new ExamGradingBean();
	private Map<String, Object> sessionMap;
	
	private List<ExamGradingBean> gradingList;
	
	public String getExamGradingList(){
		if(sessionMap.containsKey("userName")){
			gradingList = ExamGradingDAO.getExamGradingList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveExamGrading(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = ExamGradingDAO.saveExamGrading(egb);			
			if(!rslt){
				addActionMessage("Grade value is not saved. Please try again.");
			}
			gradingList = ExamGradingDAO.getExamGradingList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateFormExamGrading(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateExamGrading(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = ExamGradingDAO.updateExamGrading(egb);
			if(!rslt){
				addActionMessage("Grade value is not updated. Please try again.");
			}
			gradingList = ExamGradingDAO.getExamGradingList();
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
	public ExamGradingBean getModel() {
		return egb;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public List<ExamGradingBean> getGradingList() {
		return gradingList;
	}
	public void setGradingList(List<ExamGradingBean> gradingList) {
		this.gradingList = gradingList;
	}

}
