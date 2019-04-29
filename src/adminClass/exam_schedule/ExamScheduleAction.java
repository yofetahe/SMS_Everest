package adminClass.exam_schedule;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import subjectClass.SubjectBean;
import util.ReturnCurrentEthiopianYear;
import cRoomClass.CRoomBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import adminClass.AdminBean;
import adminClass.AdminDAO;
import examClass.exam_subject_rel.ExamSubjectRelBean;

public class ExamScheduleAction extends ActionSupport implements ModelDriven<ExamScheduleBean>, SessionAware {

	private static final long serialVersionUID = 7098319006559618650L;
	private List<ExamScheduleBean> exSchList;
	private List<CRoomBean> classList;
	private List<SubjectBean> subjectList;
	private List<ExamSubjectRelBean> examList;
	private List<AdminBean> annualTermList;
	private Map<String, Object> sessionMap;
	private int[] acyear_list;
	
	public static String curYear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
	
	ExamScheduleBean esb = new ExamScheduleBean();
	
	public String examScheduleList(){
		if (sessionMap.containsKey("userName")) {	
			setAcyear_list(new int[] { Integer.parseInt(curYear), Integer.parseInt(curYear) - 1,
					Integer.parseInt(curYear) - 2, Integer.parseInt(curYear) - 3,
					Integer.parseInt(curYear) - 4 });
			annualTermList = AdminDAO.getAnnualTermList();
			classList = ExamScheduleDAO.getClassList();	
			ExamScheduleDAO.clearSelectedExamSchedule();
			//exSchList = ExamScheduleDAO.getExSchList(curYear);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String examScheduleFilteredList(){
		if (sessionMap.containsKey("userName")) {
			exSchList = ExamScheduleDAO.filterExamScheduleList(esb);		
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String examScheduleCreateForm(){
		if (sessionMap.containsKey("userName")) {
			classList = ExamScheduleDAO.getClassList();
			annualTermList = AdminDAO.getAnnualTermList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String examScheduleEditForm(){
		if (sessionMap.containsKey("userName")) {
			esb.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			exSchList = ExamScheduleDAO.filterExamScheduleList(esb);
			
			Integer in = Integer.parseInt(esb.getIndx());
			esb.setEs_id(exSchList.get(in).getEs_id());
			esb.setCl_id(exSchList.get(in).getCl_id());
			esb.setSub_id(exSchList.get(in).getSub_id());
			esb.setEt_id(exSchList.get(in).getEt_id());
			esb.setEs_greg_date(exSchList.get(in).getEs_greg_date());
			esb.setEs_status(exSchList.get(in).getEs_status());
			esb.setTime_from(exSchList.get(in).getTime_from());
			esb.setTime_to(exSchList.get(in).getTime_to());
					
			classList = ExamScheduleDAO.getClassList();
			subjectList = ExamScheduleDAO.getSubjectListPerClass(exSchList.get(in).getCl_id());
			examList = ExamScheduleDAO.getExamSubjectRel(exSchList.get(in).getCl_id(), exSchList.get(in).getSub_id());
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String subjectListPerClass(){
		if (sessionMap.containsKey("userName")) {
			subjectList = ExamScheduleDAO.getSubjectListPerClass(esb.getCl_id());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String examTypeListPerSubjectPerClass(){
		if (sessionMap.containsKey("userName")) {
			examList = ExamScheduleDAO.getExamSubjectRel(esb.getCl_id(), esb.getSub_id());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String addSelectedExamSchedule(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = ExamScheduleDAO.addSelectedExamSchedule(esb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String examScheduleSave(){
		if (sessionMap.containsKey("userName")) {
			
//			boolean check = ExamScheduleDAO.checkExamScheduleExistance(esb);
//			if(!check){
//				addActionMessage("There is the same record.");
//				return ERROR;
//			}
			
			classList = ExamScheduleDAO.getClassList();
			annualTermList = AdminDAO.getAnnualTermList();
			setAcyear_list(new int[] { Integer.parseInt(curYear), Integer.parseInt(curYear) - 1,
					Integer.parseInt(curYear) - 2, Integer.parseInt(curYear) - 3 });			
			
			//boolean rslt = ExamScheduleDAO.saveExamSchedule(esb);
			boolean rslt = ExamScheduleDAO.saveExamSchedule();
			
			if(rslt){				
				//exSchList = ExamScheduleDAO.getExSchList(curYear);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String examScheduleUpdate(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = ExamScheduleDAO.updateExamSchedule(esb);
			if(rslt){
				setAcyear_list(new int[] { Integer.parseInt(curYear), Integer.parseInt(curYear) - 1,
						Integer.parseInt(curYear) - 2, Integer.parseInt(curYear) - 3,
						Integer.parseInt(curYear) - 4 });
				classList = ExamScheduleDAO.getClassList();	
				exSchList = ExamScheduleDAO.getExSchList(curYear);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public ExamScheduleBean getModel() {
		return esb;
	}

	public List<ExamScheduleBean> getExSchList() {
		return exSchList;
	}

	public void setExSchList(List<ExamScheduleBean> exSchList) {
		this.exSchList = exSchList;
	}

	public List<CRoomBean> getClassList() {
		return classList;
	}

	public void setClassList(List<CRoomBean> classList) {
		this.classList = classList;
	}

	public List<SubjectBean> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectBean> subjectList) {
		this.subjectList = subjectList;
	}

	public List<ExamSubjectRelBean> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamSubjectRelBean> examList) {
		this.examList = examList;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int[] getAcyear_list() {
		return acyear_list;
	}

	public void setAcyear_list(int[] acyear_list) {
		this.acyear_list = acyear_list;
	}

	public List<AdminBean> getAnnualTermList() {
		return annualTermList;
	}

	public void setAnnualTermList(List<AdminBean> annualTermList) {
		this.annualTermList = annualTermList;
	}

}
