package studentClass.reward;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class RewardAction extends ActionSupport implements ModelDriven<RewardBean>, SessionAware{
	
	private static final long serialVersionUID = 7465333282947693904L;
	private String sr_id;
	private String sr_type;
	private String sr_reason;
	private String sr_date;
	private String sr_status;
	private String si_id;
	private List<RewardBean> rwd_rslt;
	private Map<String, Object> sessionMap;
	
	RewardBean rwd = new RewardBean();
	
	public String execut(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentRewardCreate(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentRewardEdit(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentRewardList(){
		if (sessionMap.containsKey("userName")) {
			rwd_rslt = RewardDAO.getRewardList(rwd.getSi_id());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String studentRewardSave(){
		if (sessionMap.containsKey("userName")) {
			Boolean rslt = RewardDAO.saveStudReward(rwd.getSr_type(), rwd.getSr_reason(), rwd.getSr_date(), rwd.getSi_id());
			if(rslt){
				rwd.setSuccessful_save("true");
				rwd_rslt = RewardDAO.getRewardList(rwd.getSi_id());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String studentRewardUpdate(){
		if (sessionMap.containsKey("userName")) {
			Boolean rslt = RewardDAO.updateStudReward(rwd.getSr_type(), rwd.getSr_reason(), rwd.getSr_date(), rwd.getSr_status(), rwd.getSr_id());
			if(rslt){
				rwd.setSuccessful_update("true");
				rwd_rslt = RewardDAO.getRewardList(rwd.getSi_id());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	
	
	
	
	
	
	public String getSr_id() {
		return sr_id;
	}
	public void setSr_id(String sr_id) {
		this.sr_id = sr_id;
	}
	public String getSr_type() {
		return sr_type;
	}
	public void setSr_type(String sr_type) {
		this.sr_type = sr_type;
	}
	public String getSr_reason() {
		return sr_reason;
	}
	public void setSr_reason(String sr_reason) {
		this.sr_reason = sr_reason;
	}
	public String getSr_date() {
		return sr_date;
	}
	public void setSr_date(String sr_date) {
		this.sr_date = sr_date;
	}
	public String getSr_status() {
		return sr_status;
	}
	public void setSr_status(String sr_status) {
		this.sr_status = sr_status;
	}
	public List<RewardBean> getRwd_rslt() {
		return rwd_rslt;
	}
	public void setRwd_rslt(List<RewardBean> rwd_rslt) {
		this.rwd_rslt = rwd_rslt;
	}
	@Override
	public RewardBean getModel() {
		return rwd;
	}

	public String getSi_id() {
		return si_id;
	}

	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}
