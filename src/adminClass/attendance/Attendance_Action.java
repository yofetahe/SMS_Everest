package adminClass.attendance;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Attendance_Action extends ActionSupport implements ModelDriven<Attendance_Bean>, SessionAware {

	private static final long serialVersionUID = -5912754750148929223L;
	
	Attendance_Bean ab = new Attendance_Bean();
	private Map<String, Object> sessionMap;
	private List<Attendance_Bean> attypeList;
	
	public String getAttendanceTypeTemplate(){
		if(sessionMap.containsKey("userName")){
			attypeList = Attendance_DAO.getAttendanceTypeList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveAttendanceType(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = Attendance_DAO.saveAttendanceType(ab);
			if(rslt){
				attypeList = Attendance_DAO.getAttendanceTypeList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String updateFormAttendanceType(){
		if(sessionMap.containsKey("userName")){
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateAttendanceType(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = Attendance_DAO.updateAttendanceType(ab);
			if(rslt){
				attypeList = Attendance_DAO.getAttendanceTypeList();
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
	public Attendance_Bean getModel() {
		return ab;
	}

	public List<Attendance_Bean> getAttypeList() {
		return attypeList;
	}

	public void setAttypeList(List<Attendance_Bean> attypeList) {
		this.attypeList = attypeList;
	}

}
