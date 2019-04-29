package adminClass.id_card_generation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Result;

import adminClass.AdminDAO;
import studentClass.StudentBean;
import studentClass.StudentClassBean;
import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import studentClass.emergency_contact.EmergencyContactBean;
import studentClass.emergency_contact.EmergencyContactDAO;
import util.DateConvertor;
import util.OpenFile;
import util.PDF_GenerateStudIDCard;
import util.ReturnCurrentEthiopianYear;
import util.SysConstant;
import util.TodayDate_YYYYMMDD;

public class IDCardAction extends ActionSupport implements ModelDriven<IDCardBean>, SessionAware {
	
	private static final long serialVersionUID = -7052016725695131769L;

	IDCardBean idbean = new IDCardBean();
	
	private Map<String, Object> sessionMap;
	
	private List<StudentClassBean> grade_rslt;
	private List<StudentClassDetailBean> class_detail;
	private List<StudentBean> stud_rslt;
	private List<StudentBean> stud_with_id_rslt;
	private List<StudentBean> stud_with_out_id_rslt;
	private List<EmergencyContactBean> stud_contact_rslt;
	private List<StudentBean> stud_without_photo_contact_rslt;
	
	public String getIDCardGenerationTemplate(){
		
		if(sessionMap.containsKey("userName")){
			
			String username = (String)sessionMap.get("userName");
			
			if(!username.equals("esuperadmin")){
				
				return "ACCESSDENIED";
			}
			
			grade_rslt = StudentDAO.getClassList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentPerGrade() {
		if (sessionMap.containsKey("userName")) {			
						
			class_detail = StudentDAO.getClassDetail(idbean.getClass_bean().getCl_id(), (String) sessionMap.get("userName"));
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentListPerGradeDetail() {
		if (sessionMap.containsKey("userName")) {			
			
			stud_with_id_rslt = IDCardDAO.getStudListWithIDPerGradeDetail(idbean.getClass_bean().getCl_id(), idbean.getCdetail_bean().getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
						
			stud_with_out_id_rslt = IDCardDAO.getStudListWithoutIDPerGradeDetail(idbean.getClass_bean().getCl_id(), idbean.getCdetail_bean().getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
						
			return SUCCESS;
		} else {
			
			return INPUT;
		}
	}
	
	public String generateStudentIDCard(){
		if (sessionMap.containsKey("userName")) {
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			stud_with_out_id_rslt = IDCardDAO.getStudListWithoutIDPerGradeDetail(idbean.getClass_bean().getCl_id(), idbean.getCdetail_bean().getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());

			stud_contact_rslt = EmergencyContactDAO.getAllEmergContListPerClass(idbean.getClass_bean().getCl_id(), idbean.getCdetail_bean().getCd_id());
			
			int num_of_page = stud_with_out_id_rslt.size()/5;
			
			if(num_of_page > 0){
			
				StudentBean sb = new StudentBean();
				sb.setAc_year(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
				idbean.setStud_bean(sb);
				
				String fileName = SysConstant.CERT_PDF_PATH + " " + idbean.getClass_bean().getCl_id() + "_" + idbean.getCdetail_bean().getCd_id() + "_ID_Card_" + idbean.getStud_bean().getAc_year() + "_" + timeStamp + ".pdf";
				
				PDF_GenerateStudIDCard.generateIDCardPDF(fileName, stud_with_out_id_rslt, stud_contact_rslt, num_of_page, idbean.getPdf_full_page_status());
				
				IDCardDAO.saveStudentIDCardInfo(stud_with_out_id_rslt, num_of_page, idbean.getPdf_full_page_status());
				
				OpenFile.openExistingPDFFile(fileName);
				
				return SUCCESS;
				
			} else {
				
				addActionMessage("The number of student is not enough.");
				
				return ERROR;
			}
			
			
		} else {
			
			return INPUT;
		}
	}
	
	public String remainingStudentList(){
		
		if (sessionMap.containsKey("userName")) {
			
			stud_with_out_id_rslt = IDCardDAO.getRemainingStudListWithoutID();
			
			return SUCCESS;
		} else {
			
			return INPUT;
		}
	}
	
	public String generateStudentIDCardForRemaining(){
		if (sessionMap.containsKey("userName")) {
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			stud_with_out_id_rslt = IDCardDAO.getRemainingStudListWithoutID();
			
			stud_contact_rslt = EmergencyContactDAO.getAllEmergContListForRemaining(stud_with_out_id_rslt);
			
			StudentBean sb = new StudentBean();
			sb.setAc_year(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			idbean.setStud_bean(sb);
			
			String fileName = SysConstant.CERT_PDF_PATH + "_ID_Card_" + idbean.getStud_bean().getAc_year() + "_" + timeStamp + ".pdf";
			
			PDF_GenerateStudIDCard.generateIDCardPDF(fileName, stud_with_out_id_rslt, stud_contact_rslt, 1, idbean.getPdf_full_page_status());
			
			IDCardDAO.saveStudentIDCardInfo(stud_with_out_id_rslt, 1, idbean.getPdf_full_page_status());
			
			OpenFile.openExistingPDFFile(fileName);
			
			return SUCCESS;
						
		} else {
			
			return INPUT;
		}
	}
	
	public String getStudentDontHavePhotoContactInfo(){
		if(sessionMap.containsKey("userName")){
			
			stud_without_photo_contact_rslt = StudentDAO.getStudWithoutPhotoContactInfoListPerGradeDetail(idbean.getClass_bean().getCl_id(), idbean.getCdetail_bean().getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentDontHaveContactInfo(){
		if(sessionMap.containsKey("userName")){
			
			stud_without_photo_contact_rslt = StudentDAO.getStudWithoutContactInfoListPerGradeDetail(idbean.getClass_bean().getCl_id(), idbean.getCdetail_bean().getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
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
	public IDCardBean getModel() {
		
		return idbean;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
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
	public List<StudentBean> getStud_rslt() {
		return stud_rslt;
	}
	public void setStud_rslt(List<StudentBean> stud_rslt) {
		this.stud_rslt = stud_rslt;
	}

	public List<StudentBean> getStud_with_id_rslt() {
		return stud_with_id_rslt;
	}

	public void setStud_with_id_rslt(List<StudentBean> stud_with_id_rslt) {
		this.stud_with_id_rslt = stud_with_id_rslt;
	}

	public List<StudentBean> getStud_with_out_id_rslt() {
		return stud_with_out_id_rslt;
	}

	public void setStud_with_out_id_rslt(List<StudentBean> stud_with_out_id_rslt) {
		this.stud_with_out_id_rslt = stud_with_out_id_rslt;
	}

	public List<EmergencyContactBean> getStud_contact_rslt() {
		return stud_contact_rslt;
	}

	public void setStud_contact_rslt(List<EmergencyContactBean> stud_contact_rslt) {
		this.stud_contact_rslt = stud_contact_rslt;
	}

	public List<StudentBean> getStud_without_photo_contact_rslt() {
		return stud_without_photo_contact_rslt;
	}

	public void setStud_without_photo_contact_rslt(List<StudentBean> stud_without_photo_contact_rslt) {
		this.stud_without_photo_contact_rslt = stud_without_photo_contact_rslt;
	}
}
