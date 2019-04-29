package teacherClass.reward;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TeacherRewardAction extends ActionSupport implements ModelDriven<TeacherRewardBean>, SessionAware {

	private static final long serialVersionUID = 5379927483609631907L;
	private String tr_id;
	private String tr_type;
	private String tr_reason;
	private String tr_date;
	private String tr_status;
	private String ti_id;
	
	private List<TeacherRewardBean> reward_rslt;
	private Map<String, Object> sessionMap;
	
	TeacherRewardBean rw = new TeacherRewardBean();
	
	public String rewardList(){
		if (sessionMap.containsKey("userName")) {
			reward_rslt = TeacherRewardDAO.getRewardList(rw);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String rewardSaveFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String rewardSave(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(rw.getTr_type().trim().length() == 0){
				x++;
				addFieldError("rwrdError", "Reward type is blank.");
			}
			if(rw.getTr_reason().trim().length() == 0){
				x++;
				addFieldError("rwrdError", "Reward reason is blank.");
			}
			if(rw.getTr_date().trim().length() == 0){
				x++;
				addFieldError("rwrdError", "Please select the date.");
			}
			
			if(x > 0){
				return "CREATEFORM";
			} else {
				boolean rslt = TeacherRewardDAO.insertReward(rw);
				if(rslt){
					reward_rslt = TeacherRewardDAO.getRewardList(rw);
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}		
	}
	
	public String rewardUpdateFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String rewardUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(rw.getTr_type().trim().length() == 0){
				x++;
				addFieldError("rwrdError", "Reward type is blank.");
			}
			if(rw.getTr_reason().trim().length() == 0){
				x++;
				addFieldError("rwrdError", "Reward reason is blank.");
			}
			if(rw.getTr_date().trim().length() == 0){
				x++;
				addFieldError("rwrdError", "Please select the date.");
			}
			
			if(x > 0){
				return "UPDATEFORM";
			} else {
				boolean rslt = TeacherRewardDAO.updateReward(rw);
				if(rslt){
					reward_rslt = TeacherRewardDAO.getRewardList(rw);
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getTr_id() {
		return tr_id;
	}
	public void setTr_id(String tr_id) {
		this.tr_id = tr_id;
	}
	public String getTr_type() {
		return tr_type;
	}
	public void setTr_type(String tr_type) {
		this.tr_type = tr_type;
	}
	public String getTr_reason() {
		return tr_reason;
	}
	public void setTr_reason(String tr_reason) {
		this.tr_reason = tr_reason;
	}
	public String getTr_date() {
		return tr_date;
	}
	public void setTr_date(String tr_date) {
		this.tr_date = tr_date;
	}
	public String getTr_status() {
		return tr_status;
	}
	public void setTr_status(String tr_status) {
		this.tr_status = tr_status;
	}
	public String getTi_id() {
		return ti_id;
	}
	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}
	public List<TeacherRewardBean> getReward_rslt() {
		return reward_rslt;
	}
	public void setReward_rslt(List<TeacherRewardBean> reward_rslt) {
		this.reward_rslt = reward_rslt;
	}
	@Override
	public TeacherRewardBean getModel() {
		return rw;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
}
