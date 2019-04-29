package teacherClass;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

import studentClass.StudentDAO;
import util.RoleValidator;
import util.SysConstant;
import adminClass.AdminBean;
import adminClass.AdminDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TeacherAction extends ActionSupport implements ModelDriven<TeacherBean>, SessionAware {

	private static final long serialVersionUID = 6272727732013186183L;
	
	private String ti_id;
	private String fname;
	private String mname;
	private String gname;
	private String sex;
	private String dob;
	private String pob;
	private String id_no;
	private String ti_status;
	private String menutype = "teacher";
	private List<TeacherBean> tchr_rslt;
	private List<TeacherBean> tchr_perinfo;
	private List<AdminBean> usrRoleList;
	private Map<String, Object> sessionMap;
	
	String loggedUserName = null;
	TeacherBean tchr = new TeacherBean();
	
	////****** Logged in user id *****////
	public String getLoggedInUser(String loggedInUserType){
		String loggedInUser = "";
		if(loggedInUserType.equals("NTHCR")){
			loggedInUser = (String) sessionMap.get("nti_id");
		}
		if(loggedInUserType.equals("THCR")){
			loggedInUser = (String) sessionMap.get("ti_id");
		}
		return loggedInUser;
	}
	////****** Logged in user id *****////
	
	public String execute(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String teacherList(){
		// role validation if the userName is already stored in the session
		loggedUserName = null;
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Teacher";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
		
			tchr.setTeach_tab(tchr.getTeach_tab());
			tchr_rslt = TeacherDAO.getTeacherLilst();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String uploadPage(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	private File fileUpload;
	private String fileUploadFileName;
	private String fileUploadContentType;
	
	public String doUpload() {
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");  
            usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
            tchr.setTeach_tab(tchr.getTeach_tab());
			tchr_rslt = TeacherDAO.getTeacherLilst();			
            
            boolean rslt = TeacherDAO.uploadPic(tchr, fileUploadFileName);
            if(rslt){
				
				File saveFilePath = new File(SysConstant.TEACHER_PHOTO_UPLOAD_PATH +	fileUploadFileName);		
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
	
	public String teacherCrtFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String teacherEditFrm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String teacherIdNumberGenrator(String fname, String mname, String gname){
		
		return fname.substring(0, 1).toUpperCase() + mname.substring(0, 1).toUpperCase() + gname.substring(0, 1).toUpperCase() + TeacherDAO.getLastTeacherNumber();
	}
	
	public String teacherSave(){
		if (sessionMap.containsKey("userName")) {
			
			tchr.setId_no(teacherIdNumberGenrator(tchr.getFname(), tchr.getMname(), tchr.getGname()));
			
			int x = 0;
			
			if(tchr.getFname().trim().length() == 0){
				x++;
				addFieldError("tcherSaveError", "First name is blank.");
			}
			if(tchr.getMname().trim().length() == 0){
				x++;
				addFieldError("tcherSaveError", "Middle name is blank.");
			}
			if(tchr.getGname().trim().length() == 0){
				x++;
				addFieldError("tcherSaveError", "Grand father name is blank");
			}
			if(tchr.getSex().equalsIgnoreCase("0")){
				x++;
				addFieldError("tcherSaveError", "Please select sex");
			}
			if(tchr.getDob().trim().length() == 0){
				x++;
				addFieldError("tcherSaveError", "Date of birth is blank.");
			}
			if(tchr.getPob().trim().length() == 0){
				x++;				
				addFieldError("tcherSaveError", "Place of birth is blank.");
			}
			
			if(x > 0){
				return "CREATEFORM";
			} else {
				boolean rslt = TeacherDAO.saveTeacher(tchr);
				if(rslt){
					tchr_rslt = TeacherDAO.getTeacherLilst();
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
		
		
	}
	
	public String teacherUpdate(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(tchr.getFname().trim().length() == 0){
				x++;
				addFieldError("tcherSaveError", "First name is blank.");
			}
			if(tchr.getMname().trim().length() == 0){
				x++;
				addFieldError("tcherSaveError", "Middle name is blank.");
			}
			if(tchr.getGname().trim().length() == 0){
				x++;
				addFieldError("tcherSaveError", "Grand father name is blank");
			}
			if(tchr.getSex().equalsIgnoreCase("0")){
				x++;
				addFieldError("tcherSaveError", "Please select sex");
			}
			if(tchr.getDob().trim().length() == 0){
				x++;
				addFieldError("tcherSaveError", "Date of birth is blank.");
			}
			if(tchr.getPob().trim().length() == 0){
				x++;
				addFieldError("tcherSaveError", "Place of birth is blank.");
			}
			
			if(x > 0){
				return "UPDATEFORM";
			} else {
				boolean rslt = TeacherDAO.updateTeacher(tchr);
				if(rslt){
					//tchr_rslt = TeacherDAO.getTeacherLilst();
					tchr.setSuccessful_update("true");
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
	}
	
	public String teacherPerinfoEditFrm(){
		
		if (sessionMap.containsKey("userName")) {
			tchr_perinfo = TeacherDAO.getTeacherPerInfo(tchr);
			
			tchr.setTi_id(tchr_perinfo.get(0).getTi_id());
			tchr.setFname(tchr_perinfo.get(0).getFname());
			tchr.setMname(tchr_perinfo.get(0).getMname());
			tchr.setGname(tchr_perinfo.get(0).getGname());
			tchr.setSex(tchr_perinfo.get(0).getSex());
			tchr.setDob(tchr_perinfo.get(0).getDob());
			tchr.setPob(tchr_perinfo.get(0).getPob());
			tchr.setId_no(tchr_perinfo.get(0).getId_no());
			tchr.setTi_status(tchr_perinfo.get(0).getTi_status());
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
		
	}
	
	
	
	
	
	
	
	
	public String getTi_id() {
		return ti_id;
	}
	public void setTi_id(String ti_id) {
		this.ti_id = ti_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPob() {
		return pob;
	}
	public void setPob(String pob) {
		this.pob = pob;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getTi_status() {
		return ti_status;
	}
	public void setTi_status(String ti_status) {
		this.ti_status = ti_status;
	}

	@Override
	public TeacherBean getModel() {		
		return tchr;
	}	

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public List<TeacherBean> getTchr_rslt() {
		return tchr_rslt;
	}

	public void setTchr_rslt(List<TeacherBean> tchr_rslt) {
		this.tchr_rslt = tchr_rslt;
	}

	public List<TeacherBean> getTchr_perinfo() {
		return tchr_perinfo;
	}

	public void setTchr_perinfo(List<TeacherBean> tchr_perinfo) {
		this.tchr_perinfo = tchr_perinfo;
	}

	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}

	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
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
}
