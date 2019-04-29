package studentClass.grade_history;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class GradeHistoryAction extends ActionSupport implements ModelDriven<GradeHistoryBean>, SessionAware{
	
	private static final long serialVersionUID = -5352941916394587323L;

	private String class_name;
	private String si_id;
	private String clid;
	
	private String sub_id;
	private String sub_name;
	private String sub_status;
	
	private String selTab;
	
	private List<GradeHistoryBean> grdattend_rslt;
	private List<GradeHistorySubjectBean> classSubject_rslt;
	private Map<String, Object> sessionMap;
	
	GradeHistoryBean grdhst = new GradeHistoryBean();
	
	public String execute(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentGradeHistoryCreate(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentGradeHistoryList(){
		if (sessionMap.containsKey("userName")) {
			grdattend_rslt = GradeHistoryDAO.getGrdHistory(getSi_id());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String studentGradeHistoryEdit(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentGradeHistorySubject(){
		if (sessionMap.containsKey("userName")) {
			classSubject_rslt = GradeHistoryDAO.getGrdHstySub(getClid(), getSi_id());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	
	
	

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	@Override
	public GradeHistoryBean getModel() {
		return grdhst;
	}

	public List<GradeHistoryBean> getGrdattend_rslt() {
		return grdattend_rslt;
	}

	public void setGrdattend_rslt(List<GradeHistoryBean> grdattend_rslt) {
		this.grdattend_rslt = grdattend_rslt;
	}

	public String getSi_id() {
		return si_id;
	}

	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}	

	public String getClid() {
		return clid;
	}

	public void setClid(String clid) {
		this.clid = clid;
	}

	public List<GradeHistorySubjectBean> getClassSubject_rslt() {
		return classSubject_rslt;
	}

	public void setClassSubject_rslt(List<GradeHistorySubjectBean> classSubject_rslt) {
		this.classSubject_rslt = classSubject_rslt;
	}

	public String getSub_id() {
		return sub_id;
	}

	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public String getSub_status() {
		return sub_status;
	}

	public void setSub_status(String sub_status) {
		this.sub_status = sub_status;
	}

	public String getSelTab() {
		return selTab;
	}

	public void setSelTab(String selTab) {
		this.selTab = selTab;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	

}
