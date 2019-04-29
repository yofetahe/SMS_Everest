package studentClass;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import util.Excel_ImportStudEmergencyContactFromExcel;
import util.Excel_ImportStudPersonalInfoFromExcel;
import util.ReturnCurrentEthiopianYear;
import util.RoleValidator;
import util.SysConstant;
import adminClass.AdminAction;
import adminClass.AdminBean;
import adminClass.AdminDAO;
import adminClass.specialNeedCategory.SpecialNeedsCategoryBean;
import adminClass.specialNeedCategory.SpecialNeedsCategoryDAO;
import cRoomClass.CRoomDAO;
import cRoomClass.cd_registration.CDetailDAO;
import specialNeedRequired.SpecialNeedRequiredBean;
import specialNeedRequired.SpecialNeedRequiredDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StudentAction extends ActionSupport implements
		ModelDriven<StudentBean>, SessionAware {

	private static final long serialVersionUID = 3831092991955485352L;

	private String menutype = "student";

	StudentBean stud = new StudentBean();
	StudentClassBean studCl = new StudentClassBean();
	StudentClassDetailBean studClDtl = new StudentClassDetailBean();

	private List<StudentBean> stud_rslt;
	private List<StudentClassBean> grade_rslt;
	private List<StudentClassDetailBean> class_detail;
	private List<AdminBean> usrRoleList;
	private List<StudentBean> studnum_perclass;
	private Map<String, Object> sessionMap;
	private List<StudentBean> stud_personal_info;
	
	private List<SpecialNeedsCategoryBean> specialNeedsCategoryList;
	private List<SpecialNeedRequiredBean> studentSpecialNeedStatus;
	
	private boolean dropout_status_rslt;

	String loggedUserName = null;

	private File fileUpload;
	private String fileUploadFileName;
	private String fileUploadContentType;

	private int[] acyear_list;
	private String acyear;
	private String regBtnStatus;
	
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

	public String doUpload() {
		
		if (sessionMap.containsKey("userName")) {
			
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			stud.setStud_tab(stud.getStud_tab());
			
			stud_rslt = StudentDAO.getList(stud.getAc_year());
			grade_rslt = StudentDAO.getClassList();

			//StudentDAO.uploadPic(stud, fileUploadFileName);
			
			File image = new File(fileUpload.toString());
			
			boolean rslt = false;
			
			try {
				
				FileInputStream fis = new FileInputStream(image);
				
				boolean check = StudentDAO.checkStudentPhotoExistance(stud);
				
				if(check){
					
					rslt = StudentDAO.updateStudentPhoto(stud, fis, (int)image.length());
				} else {
						
					rslt = StudentDAO.saveStudentPhoto(stud, fis, (int)image.length());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear) + 1,
					Integer.parseInt(acyear), Integer.parseInt(acyear) - 1};
			
			if(rslt){
				stud.setAc_year(acyear);
				studList();
			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public boolean studentProfileInformationValidation(){
		
		boolean rslt = true;
		
		int counter = 0;
		
		if(stud.getFname() == null || stud.getFname().length() == 0){ counter++; } 
		if(stud.getMname() == null || stud.getMname().length() == 0){ counter++; }
		if(stud.getGname() == null || stud.getGname().length() == 0){ counter++; }
		if(stud.getMother_name() == null || stud.getMother_name().length() == 0){ counter++; }
		if(stud.getSex() == null || stud.getSex().equals("0")){ counter++; }
		if(stud.getDob() == null || stud.getDob().length() == 0){ counter++; }
		if(stud.getPob() == null || stud.getPob().length() == 0){ counter++; }
		if(stud.getNationality() == null || stud.getNationality().length() == 0){ counter++; }
		
		if(counter == 0 && stud.getEmergencyContactBean().getContact_name() != null && stud.getEmergencyContactBean().getContact_name_2().length() != 0){
			
			if(stud.getEmergencyContactBean().getRelationship() == null || stud.getEmergencyContactBean().getRelationship().equals("0")){ counter++; }
			if(stud.getEmergencyContactBean().getMob_no() == null || stud.getEmergencyContactBean().getMob_no().length() < 10){ counter++; }
		}
		if(counter == 0 && stud.getEmergencyContactBean().getContact_name_2() != null && stud.getEmergencyContactBean().getContact_name_2().length() != 0){
			
			if(stud.getEmergencyContactBean().getRelationship_2() == null || stud.getEmergencyContactBean().getRelationship_2().equals("0")){ counter++; }
			if(stud.getEmergencyContactBean().getMob_no_2() == null || stud.getEmergencyContactBean().getMob_no_2().length() < 10){ counter++; }
		}
		
		if(counter == 0){
			rslt = false;
		}
		
		return rslt;
	}
	
	public String saveStudentProfileInformation() {
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = false;
			boolean check = studentProfileInformationValidation();
			
			if(check || (fileUpload == null)){
				
				addActionError("The given student profile information is not correct. Please correct it.");
				
			} else {
				
				File image = new File(fileUpload.toString());
				
				rslt = StudentDAO.saveStudentProfileInformation(stud, image, studentIdNumberGenerator(stud.getFname(), stud.getMname(), stud.getGname()), getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			}

			studList();
			
			if(!check && !rslt){
				
				addActionMessage("It is not saved. Check if the student already exist.");
			}
						
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String studList() {
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Student";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);

			if (!rslt) {
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
					
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear) + 1, Integer.parseInt(acyear), 
									Integer.parseInt(acyear) - 1, Integer.parseInt(acyear) - 2};

			stud.setStud_tab(stud.getStud_tab());
			
			studnum_perclass = StudentDAO.getStudentNumPerClass(stud.getAc_year());
			grade_rslt = StudentDAO.getClassList();
			stud.setNum_of_class(String.valueOf(grade_rslt.size()));
			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}

	public String studentEdit() {
		
		if (sessionMap.containsKey("userName")) {
			
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();			
			setDropout_status_rslt(StudentDAO.getStudentDropOutStatus(stud.getSi_id(), acyear));
			class_detail = StudentDAO.getClassDetail(stud.getClass_id(), (String) sessionMap.get("userName"));
			
			studentSpecialNeedStatus = SpecialNeedRequiredDAO.checkStudSpecialNeedRequirment(stud.getSi_id());
			
			if(studentSpecialNeedStatus.size() > 0){
				stud.setStud_special_need_status("1");
				stud.setSnc_id(studentSpecialNeedStatus.get(0).getSnc_id());
				stud.setSsnr_id(studentSpecialNeedStatus.get(0).getSsnr_id());
			} else {
				stud.setStud_special_need_status("2");
			}
			
			specialNeedsCategoryList = SpecialNeedsCategoryDAO.getSpecialNeedsCategoryList();			

			//byte[] readImg = StudentDAO.getStudentPhoto(stud);
			//System.out.println(readImg.length + " image length");
			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	/*
	 * >>>>>> get student photo <<<<<<<
	 */
	public void getStudPhoto() throws FileNotFoundException{
		
		byte[] readImg = StudentDAO.getStudentPhoto(stud);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		if(readImg == null){
			
			File image = new File("C:\\sms_system_file\\everest\\Photos\\stud_photo\\default.jpeg");
			byte[] fileContent;
			try {
				fileContent = Files.readAllBytes(image.toPath());
				readImg = fileContent;
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		
		if(readImg != null){
						
			try {
				response.reset();
				response.setContentType("image/jpeg");
				response.getOutputStream().write(readImg, 0, readImg.length);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return;
	}
	
	
	public String editFormCandidateStudentList(){
		if (sessionMap.containsKey("userName")) {
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentDropOut(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String cancleStudentDropOut(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String confirmStudentDropOut(){
		if(sessionMap.containsKey("userName")){
			
			if(StudentDAO.confirmStudentDropOut(stud.getSi_id()))			
				return SUCCESS;
			else
				return ERROR;
			
		} else {
			
			return INPUT;
		}
	}
	
	public String uploadPage() {
		if (sessionMap.containsKey("userName")) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public boolean studentIdNumberValidator(String stud_id) {

		return StudentDAO.validateStudIdNumber(stud_id);
	}

	public String studentIdNumberGenerator(String fname, String mname, String gname) {
		
		String newStudId = fname.substring(0, 1).toUpperCase() + mname.substring(0, 1).toUpperCase() + gname.substring(0, 1).toUpperCase() + StudentDAO.getLastStudentNumber();
		
		return newStudId;
	}

	public String studentSave() {
		if (sessionMap.containsKey("userName")) {

//			if (!studentIdNumberValidator(stud.getId_no())) {
//				return ERROR;
//			}
			
			boolean rslt = StudentDAO.saveStudentInfo(stud, studentIdNumberGenerator(stud.getFname(), stud.getMname(), stud.getGname()), getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			
			if (rslt) {
				addActionMessage("Successfully saved.");
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}

	public String studentActiveMenu() {
		if(sessionMap.containsKey("userName")) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateStudIdNo(String fname, String mname, String gname, StudentBean std){
		
		String lstSiId = "";
		
		if(Integer.parseInt(std.getSi_id()) < 100){
			lstSiId = "000" + (Integer.parseInt(std.getSi_id()));
		} else if(Integer.parseInt(std.getSi_id()) >= 100 && Integer.parseInt(std.getSi_id()) < 1000){
			lstSiId = "00" + (Integer.parseInt(std.getSi_id()));
		} else {
			lstSiId = "0" + (Integer.parseInt(std.getSi_id()));
		}
		
		return fname.substring(0, 1).toUpperCase() + mname.substring(0, 1).toUpperCase() + gname.substring(0, 1).toUpperCase() + lstSiId;
	}

	public String studentUpdate() {
		if(sessionMap.containsKey("userName")) {
			
			//if(stud.getStud_edit_status().equals("candidate")){
			stud.setId_no(updateStudIdNo(stud.getFname(), stud.getMname(), stud.getGname(), stud));
			//}
			
			Boolean rslt = StudentDAO.updatePersonalInfo(stud, getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			if (rslt) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}

	public String studentCreate() {
		if(sessionMap.containsKey("userName")) {
			
			//grade_rslt = StudentDAO.getClassList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String studentPerGrade() {
		if (sessionMap.containsKey("userName")) {
			
			if (stud.getAc_year() == null) {
				regBtnStatus = "on";
			} else {
				if (Integer.parseInt(stud.getAc_year()) == Integer.parseInt(ReturnCurrentEthiopianYear.getCurrentEthiopianYear())) {
					regBtnStatus = "on";
				} else {
					regBtnStatus = "off";
				}
			}
			
			stud_rslt = StudentDAO.getStudentListPerGradeDetail(stud);
			class_detail = StudentDAO.getClassDetail(stud.getClass_id(), (String) sessionMap.get("userName"));
			
			stud.setNum_of_class_detail(String.valueOf(class_detail.size()));
			
			return SUCCESS;
		} else {
			return INPUT;
		}

	}
	
	public String getAllStudentList(){
		if (sessionMap.containsKey("userName")) {			
			stud_rslt = StudentDAO.getList(stud.getAc_year());
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getAllInactiveStudentList(){
		if (sessionMap.containsKey("userName")) {			
			stud_rslt = StudentDAO.getInactiveList(stud.getAc_year());
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getCandidateStudentList(){
		if (sessionMap.containsKey("userName")) {			
			stud_rslt = StudentDAO.getCandidateStudentList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String deleteCandidateStudent(){
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = StudentDAO.deleteCandidateStudent(stud.getSi_id());
			
			stud_rslt = StudentDAO.getCandidateStudentList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getAllStudentContactInfoPerGrade(){
		if (sessionMap.containsKey("userName")) {
			
			stud_rslt = StudentDAO.getStudContactInfoListPerGradeDetail(stud.getClass_id(), stud.getCd_id(), stud.getAc_year());
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentInfoWithEmergencyContactPerGrade(){
		if (sessionMap.containsKey("userName")) {
			
			stud_rslt = StudentDAO.getStudInfoWithEmergencyContactListPerGradeDetail(stud.getClass_id(), stud.getCd_id(), stud.getAc_year());
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String studentPerGradeDetail() {
		if (sessionMap.containsKey("userName")) {
			
			stud_rslt = StudentDAO.getListPerGradeDetail(stud.getClass_id(),stud.getCd_id(), stud.getAc_year());
			class_detail = StudentDAO.getClassDetail(stud.getClass_id(), (String) sessionMap.get("userName"));
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getAllStudentPerGrade(){
		if (sessionMap.containsKey("userName")) {
			stud_rslt = StudentDAO.getListPerGrade(stud.getClass_id(), stud.getAc_year());
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String studentAddInfoCreate() {
		if (sessionMap.containsKey("userName")) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String studentAddInfoEdit() {
		
		if (sessionMap.containsKey("userName")) {
			
			specialNeedsCategoryList = SpecialNeedsCategoryDAO.getSpecialNeedsCategoryList();
			
			if(!stud.getClass_id().equals("")){
				class_detail = StudentDAO.getClassDetail(stud.getClass_id(), (String) sessionMap.get("userName"));
			}
			
			stud_personal_info = StudentDAO.getStudentProfile(stud);
			
			stud.setFname(stud_personal_info.get(0).getFname());
			stud.setMname(stud_personal_info.get(0).getMname());
			stud.setGname(stud_personal_info.get(0).getGname());
			stud.setMother_name(stud_personal_info.get(0).getMother_name());
			stud.setSex(stud_personal_info.get(0).getSex());
			stud.setDob(stud_personal_info.get(0).getDob());
			stud.setPob(stud_personal_info.get(0).getPob());
			stud.setNationality(stud_personal_info.get(0).getNationality());
			stud.setSi_status(stud_personal_info.get(0).getSi_status());
			stud.setId_no(stud_personal_info.get(0).getId_no());
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studPersonalInfoFromExcelForm(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String importStudPersonalInfoFromExcel(){
		
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = false;
			
			try {
				rslt = Excel_ImportStudPersonalInfoFromExcel.importStudentPersonalInfoFromExcel(stud.getExamResultBean().getColumn_num(), stud.getExamResultBean().getFile_path(), stud.getExamResultBean().getRow_num(), stud.getExamResultBean().getSheet_num(), getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
			
		} else {
			
			return INPUT;
		}
	}
	
	public String studContactInfoFromExcelForm(){
		if(sessionMap.containsKey("userName")){
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	public String importStudContactInfoFromExcel(){
		if(sessionMap.containsKey("userName")){
			
			stud_rslt = StudentDAO.getListPerGradeDetail(stud.getClass_id(),stud.getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			boolean rslt = false;
			
			try {
				rslt = Excel_ImportStudEmergencyContactFromExcel.importStudentEmergencyContactInfoFromExcel(stud.getExamResultBean().getColumn_num(), stud.getExamResultBean().getFile_path(), stud.getExamResultBean().getRow_num(), stud.getExamResultBean().getSheet_num(), getLoggedInUser((String)sessionMap.get("loggedInUserType")), stud_rslt, stud.getEmergencyContactBean().getRelationship());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			
			return INPUT;
		}
	}
	
	public String getDropoutStudentList(){
		
		if(sessionMap.containsKey("userName")){
			
			stud_rslt = StudentDAO.getDropoutStudentList();
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	public String activateStudentsStatus(){
		
		if(sessionMap.containsKey("userName")){
			
			StudentDAO.activateDropoutStudent(stud.getSi_id(), stud.getAc_year());
			
			stud_rslt = StudentDAO.getInactiveList(stud.getAc_year());
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	@Override
	public StudentBean getModel() {
		return stud;
	}

	public List<StudentBean> getStud_rslt() {
		return stud_rslt;
	}

	public void setStud_rslt(List<StudentBean> stud_rslt) {
		this.stud_rslt = stud_rslt;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}

	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}

	public List<StudentClassDetailBean> getClass_detail() {
		return class_detail;
	}

	public void setClass_detail(List<StudentClassDetailBean> class_detail) {
		this.class_detail = class_detail;
	}

	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}

	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
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

	public int[] getAcyear_list() {
		return acyear_list;
	}

	public void setAcyear_list(int[] acyear_list) {
		this.acyear_list = acyear_list;
	}

	public String getAcyear() {
		return acyear;
	}

	public void setAcyear(String acyear) {
		this.acyear = acyear;
	}

	public String getRegBtnStatus() {
		return regBtnStatus;
	}

	public void setRegBtnStatus(String regBtnStatus) {
		this.regBtnStatus = regBtnStatus;
	}

	public boolean getDropout_status_rslt() {
		return dropout_status_rslt;
	}

	public void setDropout_status_rslt(boolean dropout_status_rslt) {		
		this.dropout_status_rslt = dropout_status_rslt;
	}

	public List<StudentBean> getStudnum_perclass() {
		return studnum_perclass;
	}

	public void setStudnum_perclass(List<StudentBean> studnum_perclass) {
		this.studnum_perclass = studnum_perclass;
	}

	public List<StudentBean> getStud_personal_info() {
		return stud_personal_info;
	}

	public void setStud_personal_info(List<StudentBean> stud_personal_info) {
		this.stud_personal_info = stud_personal_info;
	}

	public List<SpecialNeedsCategoryBean> getSpecialNeedsCategoryList() {
		return specialNeedsCategoryList;
	}

	public void setSpecialNeedsCategoryList(List<SpecialNeedsCategoryBean> specialNeedsCategoryList) {
		this.specialNeedsCategoryList = specialNeedsCategoryList;
	}

	public List<SpecialNeedRequiredBean> getStudentSpecialNeedStatus() {
		return studentSpecialNeedStatus;
	}

	public void setStudentSpecialNeedStatus(List<SpecialNeedRequiredBean> studentSpecialNeedStatus) {
		this.studentSpecialNeedStatus = studentSpecialNeedStatus;
	}
}
