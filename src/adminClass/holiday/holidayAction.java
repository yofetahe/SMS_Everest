package adminClass.holiday;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class holidayAction extends ActionSupport implements ModelDriven<holidayBean>, SessionAware {

	private static final long serialVersionUID = 8515638378662848222L;
	private List<holidayBean> holidayList;
	private Map<String, Object> sessionMap;
	holidayBean hb = new holidayBean();

	public String adminHolidayList(){
		if (sessionMap.containsKey("userName")) {
			holidayList = holidayDAO.getHolidayList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String adminHolidayCreateForm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String adminHolidaySave(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = holidayDAO.saveholiday(hb);
			if(rslt){
				holidayList = holidayDAO.getHolidayList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String adminHolidayEditForm(){
		if (sessionMap.containsKey("userName")) {
			Integer in = Integer.parseInt(hb.getIndx());
			holidayList = holidayDAO.getHolidayList();
			
			hb.setH_id(holidayList.get(in).getH_id());
			hb.setH_name(holidayList.get(in).getH_name());
			hb.setH_greg_date(holidayList.get(in).getH_greg_date());
			hb.setWork_status(holidayList.get(in).getWork_status());
			hb.setH_status(holidayList.get(in).getH_status());
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String adminHolidayUpdate(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = holidayDAO.updateHoliday(hb);
			if(rslt){
				holidayList = holidayDAO.getHolidayList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
		
	}
	
	
	
	
	
	
	
	
	

	@Override
	public holidayBean getModel() {
		return hb;
	}

	public List<holidayBean> getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(List<holidayBean> holidayList) {
		this.holidayList = holidayList;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
}
