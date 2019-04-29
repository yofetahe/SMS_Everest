package cRoomClass.exam_grading;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EGradingAction extends ActionSupport implements ModelDriven<EGradingBean>, SessionAware {

	private static final long serialVersionUID = 1888798547212358252L;
	private Map<String, Object> sessionMap;
	EGradingBean egb = new EGradingBean();
	
	private List<EGradingBean> related_grading_list;
	private List<EGradingBean> grading_list;
	
	public String examGradingList(){
		if(sessionMap.containsKey("userName")){
			related_grading_list = EGradingDAO.getExamGradingList(egb);			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String examGradingAddForm(){
		if(sessionMap.containsKey("userName")){
			grading_list = EGradingDAO.getGradingList(egb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveExamGradingRel(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = EGradingDAO.saveExamGradingRel(egb);
			if(rslt){
				related_grading_list = EGradingDAO.getExamGradingList(egb);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String examGradingUpdateForm(){
		if(sessionMap.containsKey("userName")){
			grading_list = EGradingDAO.getGradingList(egb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateExamGradingRel(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = EGradingDAO.updateExamGradingRel(egb);
			if(rslt){
				related_grading_list = EGradingDAO.getExamGradingList(egb);
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
	public EGradingBean getModel() {
		return egb;
	}
	public List<EGradingBean> getRelated_grading_list() {
		return related_grading_list;
	}
	public void setRelated_grading_list(List<EGradingBean> related_grading_list) {
		this.related_grading_list = related_grading_list;
	}

	public List<EGradingBean> getGrading_list() {
		return grading_list;
	}

	public void setGrading_list(List<EGradingBean> grading_list) {
		this.grading_list = grading_list;
	}
	
}
