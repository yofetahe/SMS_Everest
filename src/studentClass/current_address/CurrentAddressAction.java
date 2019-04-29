package studentClass.current_address;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CurrentAddressAction extends ActionSupport implements ModelDriven<CurrentAddressBean>, SessionAware{
	
	private static final long serialVersionUID = -5352766406112870268L;
	
	private String sca_id;
	private String sub_city;
	private String kebele;
	private String house_no;
	private String home_phone;
	private String status;
	private String si_id;
	private List<CurrentAddressBean> curaddList;
	private Map<String, Object> sessionMap;
	CurrentAddressBean curadd = new CurrentAddressBean();
	
	public String execute(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentCurrentAddressCreate(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentCurAddressSave(){
		if (sessionMap.containsKey("userName")) {
			Boolean rslt = CurrentAddressDOA.saveCurAddress(curadd);
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String studentCurrentAddressEdit(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = CurrentAddressDOA.checkCurAddress(curadd);
			if(rslt){
				curaddList = CurrentAddressDOA.getCurAdd(curadd.getSi_id());
				
				if(curaddList.size() > 0){
					curadd.setSca_id(curaddList.get(0).getSca_id());
					curadd.setSub_city(curaddList.get(0).getSub_city());
					curadd.setKebele(curaddList.get(0).getKebele());
					curadd.setHouse_no(curaddList.get(0).getHouse_no());
					curadd.setHome_phone(curaddList.get(0).getHome_phone());
					curadd.setEmail(curaddList.get(0).getEmail());
					curadd.setEmail_2(curaddList.get(0).getEmail_2());
					curadd.setStatus(curaddList.get(0).getStatus());
				}
				return SUCCESS;
			} else {
				return "create";
			}
		} else {
			return INPUT;
		}		
	}
	
	public String studentCurrentAddressUpdate(){
		if (sessionMap.containsKey("userName")) {
			Boolean rslt = CurrentAddressDOA.updateCurAddress(curadd);
			
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	
	
	
	public String getSub_city() {
		return sub_city;
	}
	public void setSub_city(String sub_city) {
		this.sub_city = sub_city;
	}
	public String getKebele() {
		return kebele;
	}
	public void setKebele(String kebele) {
		this.kebele = kebele;
	}
	public String getHouse_no() {
		return house_no;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public CurrentAddressBean getModel() {
		return curadd;
	}

	public String getSi_id() {
		return si_id;
	}

	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}

	public List<CurrentAddressBean> getCuraddList() {
		return curaddList;
	}

	public void setCuraddList(List<CurrentAddressBean> curaddList) {
		this.curaddList = curaddList;
	}

	public String getSca_id() {
		return sca_id;
	}

	public void setSca_id(String sca_id) {
		this.sca_id = sca_id;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;		
	}

}
