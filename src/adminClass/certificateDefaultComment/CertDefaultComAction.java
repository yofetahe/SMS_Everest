package adminClass.certificateDefaultComment;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CertDefaultComAction extends ActionSupport implements ModelDriven<CertDefaultComBean>, SessionAware {

	
	private static final long serialVersionUID = 4433384725598310228L;
	
	CertDefaultComBean cdc = new CertDefaultComBean();
	private Map<String, Object> sessionMap;
	
	private List<CertDefaultComBean> defCommentList;
	
	
	public String getCommentList(){
		if(sessionMap.containsKey("userName")){
			defCommentList = CertDefaultComDAO.getDefaultCommentList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveDefaultComment(){
		if(sessionMap.containsKey("userName")){
			
			boolean validate = CertDefaultComDAO.validateRankRang(cdc);
			if(validate){
				addActionMessage("The rank rang selected is not appropraite.");
				defCommentList = CertDefaultComDAO.getDefaultCommentList();
				return SUCCESS;
			}
			
			boolean rslt = CertDefaultComDAO.saveDefaultComment(cdc);
			if(!rslt){			
				addActionMessage("The default comment is not saved. Please try again.");
			}
			defCommentList = CertDefaultComDAO.getDefaultCommentList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateFormDefaultComment(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateDefaultComment(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = CertDefaultComDAO.updateDefaultComment(cdc);
			if(!rslt){
				addActionMessage("The comment is not updated. Please try again.");
			}
			defCommentList = CertDefaultComDAO.getDefaultCommentList();
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
	public CertDefaultComBean getModel() {
		return cdc;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}




	public List<CertDefaultComBean> getDefCommentList() {
		return defCommentList;
	}




	public void setDefCommentList(List<CertDefaultComBean> defCommentList) {
		this.defCommentList = defCommentList;
	}

}
