/*
 * *** Student Result List ***
 * Generate students result summary annual or semester level. And also possible to do deep specific to exam type under
 * the subject to see the detail mark of the student. And also it generate a certificate for each student. 
 * 
 * *** Student Result Add Form ***
 * Exam type categorized under Subject and Subject categorized under Class. this is the general format.
 * But each exam type under subject varies year to year and semester to semester. Therefore, the variation managed
 * by exam schedule. Exam schedule create additional relation between subject, exam type, and annual term(semester).
 * That means, we might have one exam type twice (on the 1st and 2nd semester) per annum. Therefore, the two repetitive
 * exam type related with the subject and class on the exam scheduling.
 * To add students exam result, the exam must be scheduled first and mandatorely the exam date must be after today.
 * 
 * It is possible to insert some part of students result on the add form. that means the unfilled student will be
 * available on the form till the result will be filled.
 * 
 * currently, filtering exam result is visible for all. but it is possible to filter on two level.
 * 1- Hide all the result information, it only visible to the authorized teacher. 
 * 2- the result will be visible for all but the add form save button will be active only for authorized teacher only.
 * 
 */

package examClass.exam_result;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import studentClass.StudentBean;
import studentClass.StudentClassBean;
import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import studentClass.student_registration.StudentRegDAO;
import subjectClass.SubjectBean;
import subjectClass.SubjectDAO;
import util.AgeCalculator;
import util.CheckFileExistance;
import util.DateConvertor;
import util.Excel_ImportStudMarkListResultFromExcel;
import util.OpenFile;
import util.PDFGenerator;
import util.PDFGenerator_AcademicCertificateCoverForAll;
import util.PDFGenerator_AcademicCertificateForAll;
import util.ReturnCurrentEthiopianYear;
import util.RoleValidator;
import util.SysConstant;
import util.TodayDate_YYYYMMDD;
import adminClass.AdminBean;
import adminClass.AdminDAO;
import adminClass.certificateDefaultComment.CertDefaultComBean;
import adminClass.certificateDefaultComment.CertDefaultComDAO;
import behEvaluationClass.BevalBean;
import behEvaluationClass.BevalDAO;
import behHolisticEvaluationClass.BevalHolisticBean;
import cRoomClass.CRoomBean;
import cRoomClass.CRoomDAO;
import cRoomClass.class_detail.CRDetailBean;
import email_communication.EmailComAction;
import email_communication.EmailComBean;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import examClass.exam_type.ExamBean;
import examClass.exam_type.ExamDAO;
import reportClass.ReportBean;
import reportClass.ReportDAO;

public class ExamResultAction extends ActionSupport implements ModelDriven<ExamResultBean>, SessionAware{

	private static final long serialVersionUID = 3865252279057597301L;
	
	private String er_id;
	private String si_id;
	private String fname;
	private String mname;
	private String gname;
	
	private String exsub_id;
	private String result;
	private String total_mark;
	private String pass_mark;
	private String cl_id;
	private String subcl_id;
	private String rwindex;
	
	private String menutype = "examrslt";
	
	private List<AdminBean> usrRoleList;
	private Map<String, Object> sessionMap;
	
	private List<StudentBean> stud_rslt;
	private List<ExamResultBean> clstudlist_rslt;
	private List<StudentClassBean> grade_rslt;
	private List<StudentClassDetailBean> class_detail;
	private List<ExamResultBean> sem_list;
	
	ExamResultBean exrslt = new ExamResultBean();
	
	private List<CRoomBean> grdList_rslt;
	private List<SubjectBean> clSubRelList;
	private List<ExamBean> clSubExamRelList;
	private List<ExamResultBean> clStudAllSubResList;
	
	private List<ExamResultBean> studTotalMark;
	private List<ExamResultBean> sortedStudTotalMark;
	private List<ExamResultBean> subList;
	private List<ExamResultBean> studentMarkList;
	private List<ExamResultBean> examTypeList;
	private List<ExamResultBean> markPerExamType;
	private List<ExamResultBean> studFullYearMark;
	private List<ExamResultBean> certificateRsltView;
	
	private List<ExamResultBean> quarterRsltView;
	private List<BevalBean> quarterEvaluation;
	private List<ExamResultBean> quarterTeacherComment;
	
	private List<BevalBean> evalList;
	
	private List<BevalBean> firstQuarterEvaluation;
	private List<BevalBean> secondQuarterEvaluation;
	private List<BevalBean> thirdQuarterEvaluation;
	private List<BevalBean> fourthQuarterEvaluation;
	
	private List<ExamResultBean> firstQuarterTeacherComment;
	private List<ExamResultBean> secondQuarterTeacherComment;
	private List<ExamResultBean> thirdQuarterTeacherComment;
	private List<ExamResultBean> fourthQuarterTeacherComment;
	
	private List<CertDefaultComBean> firstQuarterDefaultComment;
	private List<CertDefaultComBean> secondQuarterDefaultComment;
	private List<CertDefaultComBean> thirdQuarterDefaultComment;
	private List<CertDefaultComBean> fourthQuarterDefaultComment;
	
	private List<ExamResultBean> rsltList;
	
	private List<ExamResultBean> firstQuarterRslt;
	private List<ExamResultBean> secondQuarterRslt;
	private List<ExamResultBean> thirdQuarterRslt;
	private List<ExamResultBean> fourthQuarterRslt;
	private List<ExamResultBean> fullYearAvgRslt;
	private List<ExamResultBean> fullYearStudRank;
	
	private List<BevalHolisticBean> firstQuarterHolisticEvaluationResult;
	private List<BevalHolisticBean> secondQuarterHolisticEvaluationResult;
	private List<BevalHolisticBean> thirdQuarterHolisticEvaluationResult;
	
	private List<ExamResultBean> studList;
	private List<ExamResultBean> studRsltList;	
	private List<ExamResultBean> getTeacherComment;	
	private List<ExamResultBean> gradeDetail;
	private List<ExamResultBean> getAbsentDayList;
	
	private List<CertDefaultComBean> getDefaultComment;
	
	private List<StudentBean> unmatchStudNameList;
	private List<StudentBean> stud_personal_info;
	
	private int[] acyear_list;
	private int[] average_list;
	private String decColStatus;

	private static List<ExamResultBean> studFullYearMark_temp = new ArrayList<ExamResultBean>();
	
	private String loggedUserName = null;
	
	
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
	
	
	public String examRsltTemp(){
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Exam Result";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
				
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}
					
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			acyear_list = new int[]{Integer.parseInt(yr), Integer.parseInt(yr) - 1, Integer.parseInt(yr) - 2, Integer.parseInt(yr) - 3, Integer.parseInt(yr) - 4};
			
			grdList_rslt = CRoomDAO.getActiveClass();
			grade_rslt = StudentDAO.getClassList();
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studExamRsltListTemp(){
		if (sessionMap.containsKey("userName")) {
			
			if(sessionMap.get("loggedInUserType").equals("THCR")){
				return "ACCESSDENIED";
			}
			
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			acyear_list = new int[]{Integer.parseInt(yr), Integer.parseInt(yr) - 1, Integer.parseInt(yr) - 2, Integer.parseInt(yr) - 3, Integer.parseInt(yr) - 4};
			
			grdList_rslt = CRoomDAO.getActiveClass();
			grade_rslt = StudentDAO.getClassList();
			
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	} 
	
	public String getAnnualTermPerYear(){
		if (sessionMap.containsKey("userName")) {
			sem_list = ExamResultDAO.getSemisterList(exrslt.getAcademic_year());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String studExamRsltAddFrmTemp(){
		if (sessionMap.containsKey("userName")) {
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			exrslt.setAcademic_year(yr);
			sem_list = ExamResultDAO.getSemisterList(exrslt.getAcademic_year());
			grdList_rslt = CRoomDAO.getActiveClass();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String ClassList(){
		if (sessionMap.containsKey("userName")) {
			
			exrslt.setAcademic_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());
			
			if("THCR".equals(sessionMap.get("loggedInUserType"))){
				
				String ti_id = (String) sessionMap.get("ti_id");
				
				grdList_rslt = CRoomDAO.getActiveRelatedClassForExamReg(ti_id);
				
			} else {
				System.out.println("in");
				grdList_rslt = CRoomDAO.getActiveClass();
			}	
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getAllActiveClassList(){
		
		if (sessionMap.containsKey("userName")) {
				
				grdList_rslt = CRoomDAO.getActiveClass();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String gradeDetailList(){
		if (sessionMap.containsKey("userName")) {
			
			if(sessionMap.get("loggedInUserType").equals("THCR")){
				
				String ti_id = (String) sessionMap.get("ti_id");
				
				gradeDetail = ExamResultDAO.getGradeDetailForExamReg(exrslt, ti_id);
			} else {
				
				gradeDetail = ExamResultDAO.getGradeDetail(exrslt);
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String averageList(){
		
		if (sessionMap.containsKey("userName")) {
			
			average_list = new int[]{40, 45, 50, 55, 60, 65};
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getAllRelatedGradeDetailList(){
		if (sessionMap.containsKey("userName")) {

			gradeDetail = ExamResultDAO.getGradeDetail(exrslt);
						
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String ClSubjectList(){
		if (sessionMap.containsKey("userName")) {
			
			if(sessionMap.get("loggedInUserType").equals("THCR")){
				
				String ti_id = (String) sessionMap.get("ti_id");
				
				clSubRelList = SubjectDAO.getSelectedRelatedSubList(exrslt, ti_id);
			} else {
				
				clSubRelList = SubjectDAO.getSelectedSubList(exrslt);
			}
			
			gradeDetail = ExamResultDAO.getGradeDetail(exrslt);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String ClSubjectListPerExamSubRel(){
		
		if (sessionMap.containsKey("userName")) {
			
			if(sessionMap.get("loggedInUserType").equals("THCR")){
				
				String ti_id = (String) sessionMap.get("ti_id");
				
				clSubRelList = SubjectDAO.getSelectedRelatedSubList(exrslt, ti_id);
				
			} else {
				
				ReportBean rb = new ReportBean();
				
				CRoomBean cr = new CRoomBean();
				cr.setCl_id(exrslt.getCl_id());
				rb.setClass_bean(cr);
				
				CRDetailBean crd = new CRDetailBean();
				crd.setCd_id(exrslt.getCd_id());
				rb.setCdetail_bean(crd);
				
				rb.setAt_id(exrslt.getAt_id());
				
				StudentBean stud_bean = new StudentBean();
				stud_bean.setAc_year(exrslt.getAcademic_year());
				rb.setStud_bean(stud_bean);
								
				clSubRelList = ReportDAO.getSubjectListPerClassExamSubRelWithSubClId(rb);
			}
			
			gradeDetail = ExamResultDAO.getGradeDetail(exrslt);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String FilterClSubjectList(){
		if (sessionMap.containsKey("userName")) {
			
			ReportDAO.clearSelectedSubject();
			
			if(exrslt.getCl_id().equals("1")){
				exrslt.setCl_id("1");
			} else if(exrslt.getCl_id().equals("2")){
				exrslt.setCl_id("5");
			} else if(exrslt.getCl_id().equals("3")){
				exrslt.setCl_id("7");
			} else if(exrslt.getCl_id().equals("4")){
				exrslt.setCl_id("9");
			}
			
			clSubRelList = SubjectDAO.getSelectedSubList(exrslt);			
			return SUCCESS;
			
		} else {
			return INPUT;
		}		
	}
	
	public String ClSubListStudRslt(){
		if (sessionMap.containsKey("userName")) {
			
			clSubRelList = SubjectDAO.getSelectedSubList(exrslt);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String ClSubjectExamList(){
		if (sessionMap.containsKey("userName")) {			
			clSubExamRelList = ExamDAO.getSelectedExamTypeList(exrslt);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String ClStudentList(){
		if (sessionMap.containsKey("userName")) {
			
			ExamResultDAO.clearExamrslt();
			
			//String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			//exrslt.setAcademic_year(yr);
			
			//student list mark not filled
			//clstudlist_rslt = StudentDAO.getStudentListPerGrade(exrslt);
			
			clstudlist_rslt = ExamResultDAO.getStudListPerGradeDetail(exrslt);
			
			Collections.sort(clstudlist_rslt, new Comparator<ExamResultBean>() {
				
				public int compare(ExamResultBean a, ExamResultBean b){
					//return b.getAverage_mark().compareTo(a.getAverage_mark());						
					//return b.getTotal_mark().compareTo(a.getTotal_mark());
					String aFullName = a.getFname() + a.getMname() + a.getGname();
					String bFullName = b.getFname() + b.getMname() + b.getGname();
					return aFullName.compareTo(bFullName);
				}
			});
			
			studList = ExamResultDAO.getStudListPerGrade(exrslt);
			
			//students list with filled mark
			studRsltList = ExamResultDAO.getStudRsltList(studList, exrslt);
			
			//gradeDetail = ExamResultDAO.getGradeDetail(exrslt);
			//sem_list = ExamResultDAO.getSemisterList(yr);
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String refreshClStudentList(){
		if (sessionMap.containsKey("userName")) {
			//String yr = util.DateConvertor.dateConvertor(exrslt.getCur_date());
			clstudlist_rslt = StudentDAO.getStudentListPerGrade(exrslt);
			Collections.sort(clstudlist_rslt, new Comparator<ExamResultBean>() {
				public int compare(ExamResultBean a, ExamResultBean b){
					//return b.getAverage_mark().compareTo(a.getAverage_mark());						
					//return b.getTotal_mark().compareTo(a.getTotal_mark());
					String aFullName = a.getFname() + a.getMname() + a.getGname();
					String bFullName = b.getFname() + b.getMname() + b.getGname();
					return aFullName.compareTo(bFullName);
				}
			});
			studList = ExamResultDAO.getStudListPerGrade(exrslt);		
			studRsltList = ExamResultDAO.getStudRsltList(studList, exrslt);
			gradeDetail = ExamResultDAO.getGradeDetail(exrslt);
			sem_list = ExamResultDAO.getSemisterList(exrslt.getAcademic_year());
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String studListPerGradeDetail(){	
		if (sessionMap.containsKey("userName")) {
			clstudlist_rslt = ExamResultDAO.getStudListPerGradeDetail(exrslt);
			Collections.sort(clstudlist_rslt, new Comparator<ExamResultBean>() {
				public int compare(ExamResultBean a, ExamResultBean b){
					//return b.getAverage_mark().compareTo(a.getAverage_mark());						
					//return b.getTotal_mark().compareTo(a.getTotal_mark());
					String aFullName = a.getFname() + a.getMname() + a.getGname();
					String bFullName = b.getFname() + b.getMname() + b.getGname();
					return aFullName.compareTo(bFullName);
				}
			});
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String AddStudentRslt(){	
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = ExamResultDAO.addStudExamRslt(exrslt);
			
			if(rslt){
				sem_list = ExamResultDAO.getSemisterList(exrslt.getAcademic_year());
				return SUCCESS;
			} else {
				return ERROR;
			}						
			
		} else {
			return INPUT;
		}		
	}

	public String RemoveStudentRslt(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = ExamResultDAO.removeStudExamRslt(exrslt);
			
			if(rslt){
				sem_list = ExamResultDAO.getSemisterList(exrslt.getAcademic_year());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String saveStudentRslt(){
		if (sessionMap.containsKey("userName")) {
			
			//clstudlist_rslt = StudentDAO.getStudentListPerGrade(exrslt);		
			//int listsize = clstudlist_rslt.size();
			//loggedUserName = (String) sessionMap.get("userName");			
			
			///>>> save stud exam result from array list created in ExamResultDAO <<<///
			//boolean rslt = ExamResultDAO.saveStudExamRslt(getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			
			boolean rslt = ExamResultDAO.saveStudExamRsltFromJavaScriptArray_Batch(exrslt, getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
			
		} else {
			return INPUT;
		}		
	}
	
	public String ClDetailStudentList(){
		if (sessionMap.containsKey("userName")) {
			grade_rslt = StudentDAO.getClassList();
			
			for(int i = 0; i < grade_rslt.size(); i++){
				if(exrslt.getCl_id().equals(grade_rslt.get(i).getClass_id())){
					exrslt.setCl_name(grade_rslt.get(i).getClass_name());
				}
			}
			
			class_detail = StudentDAO.getClassDetail(exrslt.getCl_id(), (String)sessionMap.get("userName"));
			return SUCCESS;
		} else {
			return INPUT;
		}		
	} 
	
	public String StudentListExRsltPerGrade(){
		
		if (sessionMap.containsKey("userName")) {
			
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			String selecetedYear = exrslt.getAcademic_year();
			if(Integer.parseInt(selecetedYear) < Integer.parseInt(yr)){
				decColStatus = "on";
			} else {
				decColStatus = "off";
			}
						
			studTotalMark = getStudentRankWithTotalMark(exrslt);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
		
	}
	
	/*
	 * the method return the student rank on the current quarter
	 */
	public List<ExamResultBean> getStudentRankWithTotalMark(ExamResultBean exr){
		
		studTotalMark = ExamResultDAO.getQuarterTotalSumStudRslt(exr);
		
		if(studTotalMark.size() > 0){
			//sort the student list per their average mark
			Collections.sort(studTotalMark, new Comparator<ExamResultBean>() {
				public int compare(ExamResultBean a, ExamResultBean b){
					//return b.getAverage_mark().compareTo(a.getAverage_mark());						
					//return b.getTotal_mark().compareTo(a.getTotal_mark());
					
					if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
						return 0;
					} else if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
						return 1;
					} else {
						return -1;
					}
				}
			});
		}
		
		///>>> *** adding student rank based on the total mark *** <<<///
		double pervious_total = 0.0;
		int pervious_rank = 0, same_rank_counter = 0;
		
		for(int i = 0; i < studTotalMark.size(); i++){
			
			if(pervious_total == 0.0){
				
				pervious_total = Double.parseDouble(studTotalMark.get(i).getTotal_mark());
				
				pervious_rank = 1;
				
				studTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank));
				
			} else if(pervious_total != 0.0 && Double.parseDouble(studTotalMark.get(i).getTotal_mark()) == pervious_total){
				
				pervious_total = Double.parseDouble(studTotalMark.get(i).getTotal_mark());
				
				studTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank));
				
				same_rank_counter++;
				
			} else {
				
				pervious_total = Double.parseDouble(studTotalMark.get(i).getTotal_mark());
				
				pervious_rank = pervious_rank + same_rank_counter + 1;
				
				studTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank));
				
				same_rank_counter = 0;
			}
		}
		
		return studTotalMark;
	}
	
	public String StudentListFullYearExRsltPerGrade(){
		
		if (sessionMap.containsKey("userName")) {
			
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			String selecetedYear = exrslt.getAcademic_year();
			
			if(Integer.parseInt(selecetedYear) < Integer.parseInt(yr)){
				decColStatus = "on";
			} else {
				decColStatus = "off";
			}
			
			//stud_rslt = StudentDAO.getListPerGradeDetail(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			//studFullYearMark = ExamResultDAO.getStudEachQuarterTotalRslt(stud_rslt, exrslt.getCl_id(), exrslt.getAcademic_year());
			//studFullYearMark = ExamResultDAO.getEachQuarterStudTotalRslt(exrslt);
			studFullYearMark = ExamResultDAO.getStudentTotalRsltPerEachQuarter(exrslt);
			
			if(studFullYearMark.size() > 0){
				///// sorting student based on annual year avg
				Collections.sort(studFullYearMark, new Comparator<ExamResultBean>() {
					public int compare(ExamResultBean a, ExamResultBean b){
						int rslt = 0;
						
						if(Double.parseDouble(b.getAverage_quarter_mark()) > Double.parseDouble(a.getAverage_quarter_mark())){
							rslt = 1;
						}
						if(Double.parseDouble(b.getAverage_quarter_mark()) < Double.parseDouble(a.getAverage_quarter_mark())){
							rslt = -1;
						}
						if(Double.parseDouble(b.getAverage_quarter_mark()) == Double.parseDouble(a.getAverage_quarter_mark())){
							rslt = 0;
						}
						
						return rslt;
						//b.getAverage_quarter_mark().compareTo(a.getAverage_quarter_mark());
					}
				});
			}
			
			studFullYearMark_temp = studFullYearMark;
			
			///>>> *** adding student rank based on the total mark *** <<<///
			double pervious_total = 0.0;
			int pervious_rank = 0, same_rank_counter = 0;
			
			for(int i = 0; i < studFullYearMark.size(); i++){
				
				if(pervious_total == 0.0){
					
					pervious_total = Double.parseDouble(studFullYearMark.get(i).getTotal_mark());
					
					pervious_rank = 1;
					
					studFullYearMark.get(i).setStud_rank(String.valueOf(pervious_rank));
					
				} else if(pervious_total != 0.0 && Double.parseDouble(studFullYearMark.get(i).getTotal_mark()) == pervious_total){
					
					pervious_total = Double.parseDouble(studFullYearMark.get(i).getTotal_mark());
					
					studFullYearMark.get(i).setStud_rank(String.valueOf(pervious_rank));
					
					same_rank_counter++;
					
				} else {
					
					pervious_total = Double.parseDouble(studFullYearMark.get(i).getTotal_mark());
					
					pervious_rank = pervious_rank + same_rank_counter + 1;
					
					studFullYearMark.get(i).setStud_rank(String.valueOf(pervious_rank));
					
					same_rank_counter = 0;
				}
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
		
	}
	
	public String getCertificateView(){
		
		if (sessionMap.containsKey("userName")) {
			
//			clSubRelList = SubjectDAO.getSelectedSubList(exrslt);
//			certificateRsltView = ExamResultDAO.getCertificateRsltView(exrslt, clSubRelList);
			
			stud_rslt = StudentDAO.getListPerGradeDetail(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			
			StudentBean stud = new StudentBean();
			stud.setSi_id(exrslt.getSi_id());
			stud.setAc_year(exrslt.getAcademic_year());
			
			stud_personal_info = StudentDAO.getStudentProfileByAcademicYear(stud);
			
			if(stud_personal_info.get(0).getRslt_status().equals("Passed")){
				stud.setRslt_status("Promotted to " + CRoomDAO.getClassName(String.valueOf(Integer.parseInt(exrslt.getCl_id()) + 1)));
			}
			if(stud_personal_info.get(0).getRslt_status().equals("Failed")){
				stud.setRslt_status("Detained in " + exrslt.getCl_name() + ", he/she must attend the vacation enrichment program");
			}
			
			exrslt.setStud(stud);
			exrslt.setRow_num(String.valueOf(stud_rslt.size()));
			
			exrslt.setAt_id("1");
			firstQuarterRslt = ExamResultDAO.getFullYearCertificateRsltView_Update(exrslt);
			int fsize = firstQuarterRslt.size();
			firstQuarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
			firstQuarterDefaultComment = CertDefaultComDAO.getCommentPerRank(firstQuarterRslt.get(fsize-1).getAverage_quarter_mark());
			
			exrslt.setAt_id("2");
			secondQuarterRslt = ExamResultDAO.getFullYearCertificateRsltView_Update(exrslt);
			int ssize = secondQuarterRslt.size();
			secondQuarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
			secondQuarterDefaultComment = CertDefaultComDAO.getCommentPerRank(secondQuarterRslt.get(ssize-1).getAverage_quarter_mark());
			
			exrslt.setAt_id("3");
			thirdQuarterRslt = ExamResultDAO.getFullYearCertificateRsltView_Update(exrslt);
			int tsize = thirdQuarterRslt.size();
			thirdQuarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
			thirdQuarterDefaultComment = CertDefaultComDAO.getCommentPerRank(thirdQuarterRslt.get(tsize-1).getAverage_quarter_mark());
			
			exrslt.setAt_id("4");
			fourthQuarterRslt = ExamResultDAO.getFullYearCertificateRsltView_Update(exrslt);
			int frsize = fourthQuarterRslt.size();
			fourthQuarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
			fourthQuarterDefaultComment = CertDefaultComDAO.getCommentPerRank(fourthQuarterRslt.get(frsize-1).getAverage_quarter_mark());
			
			if(firstQuarterRslt.size() > 1 && firstQuarterRslt.size() >= secondQuarterRslt.size() && firstQuarterRslt.size() >= thirdQuarterRslt.size() && firstQuarterRslt.size() >= fourthQuarterRslt.size()){
				rsltList = firstQuarterRslt;
			} else if(secondQuarterRslt.size() > 1 && secondQuarterRslt.size() >= firstQuarterRslt.size() && secondQuarterRslt.size() >= thirdQuarterRslt.size() && secondQuarterRslt.size() >= fourthQuarterRslt.size()){
				rsltList = secondQuarterRslt;
			} else if(thirdQuarterRslt.size() > 1 && thirdQuarterRslt.size() >= firstQuarterRslt.size() && thirdQuarterRslt.size() >= secondQuarterRslt.size() && thirdQuarterRslt.size() >= fourthQuarterRslt.size()){
				rsltList = thirdQuarterRslt;
			} else if(fourthQuarterRslt.size() > 1 && fourthQuarterRslt.size() >= firstQuarterRslt.size() && fourthQuarterRslt.size() >= secondQuarterRslt.size() && fourthQuarterRslt.size() >= thirdQuarterRslt.size()){
				rsltList = fourthQuarterRslt;
			}
			
			fullYearAvgRslt = ExamResultDAO.getCertificateAverage(firstQuarterRslt, secondQuarterRslt, thirdQuarterRslt, fourthQuarterRslt);
			fullYearStudRank = ExamResultDAO.getStudentRankPerQuareter(exrslt);
			
			exrslt.setHomeroom_teacher_name(ExamResultDAO.getHomeroomTeacherName(exrslt));
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	/*
	 * This method is out of use replaced with getCertificateViewForAllPDF_new() method
	 */
	public String getCertificateViewForAllPDF(){
		
		if (sessionMap.containsKey("userName")) {
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
						
			String FileName = SysConstant.CERT_PDF_PATH + " " + exrslt.getCl_name() + "_" + exrslt.getCd_name() + "_For_All_Stud_Certificate_" + exrslt.getCur_date() + "_" + timeStamp + ".pdf";
			
			stud_rslt = StudentDAO.getListPerGradeDetail(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			
			exrslt.setHomeroom_teacher_name(ExamResultDAO.getHomeroomTeacherName(exrslt));
			
			////**** sort the student list by full year average ****////
			
			studFullYearMark = studFullYearMark_temp;
			
			////**** ------------------------------------------ ****////			
			
			PDFGenerator_AcademicCertificateForAll pg = new PDFGenerator_AcademicCertificateForAll();
			
			pg.openCertDocument(FileName);
			
			for(int i = 0; i < stud_rslt.size(); i++){
				
				int rank = 0;
				
				for(int j = 0; j < studFullYearMark.size(); j++){
					if(stud_rslt.get(i).getSi_id().equals(studFullYearMark.get(j).getSi_id())){
						rank = j+1;						
						break;
					}
				}
				
				if(rank != 0){
					exrslt.setStud_rank(String.valueOf(rank));
				} else {
					exrslt.setStud_rank("-");
				}
				
				exrslt.setSi_id(stud_rslt.get(i).getSi_id());
				
				StudentBean stud = new StudentBean();
				stud.setTotal_number(String.valueOf(stud_rslt.size()));
				stud.setSi_id(exrslt.getSi_id());
				stud.setAc_year(exrslt.getAcademic_year());
				
				stud_personal_info = StudentDAO.getStudentProfileByAcademicYear(stud);
				
				if(stud_personal_info.get(0).getRslt_status().equals("Passed")){
					stud.setRslt_status("Promotted to " + CRoomDAO.getClassName(String.valueOf(Integer.parseInt(exrslt.getCl_id()) + 1)));
				} else if(stud_personal_info.get(0).getRslt_status().equals("Failed")){
					stud.setRslt_status("Detained in " + exrslt.getCl_name() + ", he/she must attend the vacation enrichment program");
				} else {
					stud.setRslt_status("");
				}
				
				exrslt.setStud(stud);
				
				exrslt.setAt_id("1");
				firstQuarterRslt = ExamResultDAO.getFullYearCertificateRsltView(exrslt);
				int fsize = firstQuarterRslt.size();
				firstQuarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
				firstQuarterDefaultComment = CertDefaultComDAO.getCommentPerRank(firstQuarterRslt.get(fsize-1).getAverage_quarter_mark());
				
				exrslt.setAt_id("2");
				secondQuarterRslt = ExamResultDAO.getFullYearCertificateRsltView(exrslt);
				int ssize = secondQuarterRslt.size();
				secondQuarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
				secondQuarterDefaultComment = CertDefaultComDAO.getCommentPerRank(secondQuarterRslt.get(ssize-1).getAverage_quarter_mark());
				
				exrslt.setAt_id("3");
				thirdQuarterRslt = ExamResultDAO.getFullYearCertificateRsltView(exrslt);
				int tsize = thirdQuarterRslt.size();
				thirdQuarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
				thirdQuarterDefaultComment = CertDefaultComDAO.getCommentPerRank(thirdQuarterRslt.get(tsize-1).getAverage_quarter_mark());
				
				exrslt.setAt_id("4");
				fourthQuarterRslt = ExamResultDAO.getFullYearCertificateRsltView(exrslt);
				int frsize = fourthQuarterRslt.size();
				fourthQuarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
				fourthQuarterDefaultComment = CertDefaultComDAO.getCommentPerRank(fourthQuarterRslt.get(frsize-1).getAverage_quarter_mark());
				
				fullYearAvgRslt = ExamResultDAO.getCertificateAverage(firstQuarterRslt, secondQuarterRslt, thirdQuarterRslt, fourthQuarterRslt);
				fullYearStudRank = ExamResultDAO.getStudentRankPerQuareter(exrslt);
				
				pg.generateCertficatePdf(exrslt, firstQuarterRslt, secondQuarterRslt, thirdQuarterRslt, fourthQuarterRslt, firstQuarterTeacherComment, secondQuarterTeacherComment, thirdQuarterTeacherComment, fourthQuarterTeacherComment, firstQuarterDefaultComment, secondQuarterDefaultComment, thirdQuarterDefaultComment, fourthQuarterDefaultComment, fullYearAvgRslt, fullYearStudRank);
				
			}
			
			pg.closeCertDocument();
			
			OpenFile.openExistingPDFFile(FileName);
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public synchronized String getCertificateViewForAllPDF_New(){
		
		if (sessionMap.containsKey("userName")) {
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getDayMonthYearFormat()));
			//exrslt.setCur_date(TodayDate_YYYYMMDD.getDayMonthYearFormat());
						
			String FileName = SysConstant.CERT_PDF_PATH + " " + exrslt.getCl_name() + "_" + exrslt.getCd_name() + "_For_All_Stud_Certificate_" + exrslt.getCur_date() + "_" + timeStamp + ".pdf";
			
			stud_rslt = StudentDAO.getListPerGradeDetail(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			
			exrslt.setHomeroom_teacher_name(ExamResultDAO.getHomeroomTeacherName(exrslt)); ///>>> connection 1
			
			int subject_count = SubjectDAO.getSubjectNumberPerClass(exrslt.getCl_id());///>>> connection 2
						
			
			///*** getting all students full year result, comment ***///
			
			ExamResultDAO.getAllStudentsFullYearResultForCertificate(exrslt, subject_count); ///>>> connection 3
			
			ExamResultDAO.getQuarterTeacherCommentForAllStudentsPerClass(exrslt); ///>>> connection 4
			
			ExamResultDAO.getActiveDefaultCommentList(); ///>>> connection 5
			
			String next_grade = CRoomDAO.getClassName(String.valueOf(Integer.parseInt(exrslt.getCl_id()) + 1)); ///>>> connection 6
			
			ExamResultDAO.getStudentEachQuarterTotalResult(stud_rslt);
			
			///*** ----------------------------------- ***///
			
			
			////**** sort the student list by full year average ****////
			
			studFullYearMark = studFullYearMark_temp;
			
			////**** sort the student list by full year average ****////			
			
			PDFGenerator_AcademicCertificateForAll pg = new PDFGenerator_AcademicCertificateForAll();
			
			synchronized(this){
				
			pg.openCertDocument(FileName);
			
			for(int i = 0; i < stud_rslt.size(); i++){
				
//				int rank = 0;
//				for(int j = 0; j < studFullYearMark.size(); j++){
//					if(stud_rslt.get(i).getSi_id().equals(studFullYearMark.get(j).getSi_id())){
//						rank = j+1;
//						break;
//					}
//				}
//				if(rank != 0){
//					exrslt.setStud_rank(String.valueOf(rank));
//				} else {
//					exrslt.setStud_rank("-");
//				}
				
				exrslt.setSi_id(stud_rslt.get(i).getSi_id());
				
				StudentBean stud = new StudentBean();
				stud.setTotal_number(String.valueOf(stud_rslt.size()));
				stud.setSi_id(exrslt.getSi_id());
				stud.setAc_year(exrslt.getAcademic_year());
				
				stud_personal_info = ExamResultDAO.getStudentProfileByAcademicYearFromExecutedQuery(stud);
				
				if(stud_personal_info.get(0).getRslt_status().equals("Passed")){
					stud.setRslt_status("Promotted to " + next_grade);
				} else if(stud_personal_info.get(0).getRslt_status().equals("Failed")){
					
					//String gender = stud_personal_info.get(i).getSex().equals("M")?"he":"she";
					
					stud.setRslt_status("Detained in " + exrslt.getCl_name() + ", he/she must attend the vacation enrichment program");
					
				} else {
					
					stud.setRslt_status("");
				}
				
				exrslt.setStud(stud);
				
				exrslt.setAt_id("1");
				firstQuarterRslt = ExamResultDAO.getStudentFullYearCertificateRsltView(exrslt);
				int fsize = firstQuarterRslt.size();
				firstQuarterTeacherComment = ExamResultDAO.getQuarterTeacherCommentPerStudent(exrslt);
				firstQuarterDefaultComment = ExamResultDAO.getDefaultCommentPerStudent(firstQuarterRslt.get(fsize-1).getAverage_quarter_mark());
				
				exrslt.setAt_id("2");
				secondQuarterRslt = ExamResultDAO.getStudentFullYearCertificateRsltView(exrslt);
				int ssize = secondQuarterRslt.size();
				secondQuarterTeacherComment = ExamResultDAO.getQuarterTeacherCommentPerStudent(exrslt);
				secondQuarterDefaultComment = ExamResultDAO.getDefaultCommentPerStudent(secondQuarterRslt.get(ssize-1).getAverage_quarter_mark());
				
				exrslt.setAt_id("3");
				thirdQuarterRslt = ExamResultDAO.getStudentFullYearCertificateRsltView(exrslt);
				int tsize = thirdQuarterRslt.size();
				thirdQuarterTeacherComment = ExamResultDAO.getQuarterTeacherCommentPerStudent(exrslt);
				thirdQuarterDefaultComment = ExamResultDAO.getDefaultCommentPerStudent(thirdQuarterRslt.get(tsize-1).getAverage_quarter_mark());
				
				exrslt.setAt_id("4");
				fourthQuarterRslt = ExamResultDAO.getStudentFullYearCertificateRsltView(exrslt);
				int frsize = fourthQuarterRslt.size();
				fourthQuarterTeacherComment = ExamResultDAO.getQuarterTeacherCommentPerStudent(exrslt);
				fourthQuarterDefaultComment = ExamResultDAO.getDefaultCommentPerStudent(fourthQuarterRslt.get(frsize-1).getAverage_quarter_mark());
								
				fullYearAvgRslt = ExamResultDAO.getCertificateAverageForFullYearCertificate(firstQuarterRslt, secondQuarterRslt, thirdQuarterRslt, fourthQuarterRslt, subject_count);
				fullYearStudRank = ExamResultDAO.getStudentRankPerQuareterForCertificate(exrslt);
				
				pg.generateCertficatePdf(exrslt, firstQuarterRslt, secondQuarterRslt, thirdQuarterRslt, fourthQuarterRslt, firstQuarterTeacherComment, secondQuarterTeacherComment, thirdQuarterTeacherComment, fourthQuarterTeacherComment, firstQuarterDefaultComment, secondQuarterDefaultComment, thirdQuarterDefaultComment, fourthQuarterDefaultComment, fullYearAvgRslt, fullYearStudRank);
				
			}
			
			pg.closeCertDocument();
			
			OpenFile.openExistingPDFFile(FileName);
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public synchronized String getCertificateCoverForAllPDF(){
		
		if (sessionMap.containsKey("userName")) {
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getDayMonthYearFormat()));
						
			String FileName = SysConstant.CERT_PDF_PATH + " " + exrslt.getCl_name() + "_" + exrslt.getCd_name() + "_For_All_Stud_Certificate_Cover_" + exrslt.getCur_date() + "_" + timeStamp + ".pdf";
			
			stud_rslt = StudentDAO.getListPerGradeDetail(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			//stud_rslt = StudentDAO.getListPerGradeDetailForCertificate(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			
			PDFGenerator_AcademicCertificateCoverForAll pg = new PDFGenerator_AcademicCertificateCoverForAll();
			
			pg.openCertDocument(FileName);

			StudentBean stud = new StudentBean();
			
			for(int i = 0; i < stud_rslt.size(); i++){
				
				stud.setSi_id(stud_rslt.get(i).getSi_id());
				
				stud.setAc_year(exrslt.getAcademic_year());
				
				stud_personal_info = StudentDAO.getStudentProfileByAcademicYear(stud);
				
				if(stud_personal_info.get(0).getRslt_status().equals("Passed")){
					stud.setRslt_status("Promotted to " + CRoomDAO.getClassName(String.valueOf(Integer.parseInt(exrslt.getCl_id()) + 1)));
				} else if(stud_personal_info.get(0).getRslt_status().equals("Failed")){
					stud.setRslt_status("Detained in " + exrslt.getCl_name() + ", he must attend the vacation enrichment program");
				} else {
					stud.setRslt_status("");
				}
				
				stud.setAge(AgeCalculator.calculateAgeBasedOnGivenYear(stud_personal_info.get(0).getDob(), exrslt.getAcademic_year()));
				
				String x = stud_personal_info.get(0).getDob();
				
//				if(x.length() > 0){
//					///////////////////////extracting day, month and year from the string given
//					int[] separator = new int[2];
//					for (int z = 0, j = 1, y = 0; z < x.length(); z++, j++) {
//					    if (x.substring(z, j).equalsIgnoreCase("-")) {
//					        separator[y] = z;
//					        y++;
//					    }
//					}
//					String g_year = x.substring(0, separator[0]);
//					String g_month = x.substring(separator[0] + 1, separator[1]);
//					String g_day = x.substring(separator[1] + 1);
//					
//					stud.setDob(g_day + "-" + g_month + "-" + g_year);				
//				}
				
				exrslt.setStud(stud);
				
				//exrslt.setCur_date(TodayDate_YYYYMMDD.getDayMonthYearFormat());
				
				String yr = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear();
				
				if(Integer.parseInt(yr) == Integer.parseInt(exrslt.getAcademic_year())){
					
					exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getDayMonthYearFormat()));
				
				} else {
					
					exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getDefaultLastDateOfSelectedPassedYear(exrslt.getAcademic_year())));
				}
				
				BevalBean bvb = new BevalBean();
				bvb.setCl_id(exrslt.getCl_id());
				bvb.setSi_id(stud_rslt.get(i).getSi_id());
				bvb.setAc_year(exrslt.getAcademic_year());
				
				bvb.setQr_id("1");
				firstQuarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
				
				bvb.setQr_id("2");
				secondQuarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
				
				bvb.setQr_id("3");
				thirdQuarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
				
				bvb.setQr_id("4");
				fourthQuarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
				
				pg.generateCertficateCoverPdf(exrslt, firstQuarterEvaluation, secondQuarterEvaluation, thirdQuarterEvaluation, fourthQuarterEvaluation, stud_personal_info);
				
			}
			
			pg.closeCertDocument();
			
			OpenFile.openExistingPDFFile(FileName);
			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String getCertificateViewCoverPage(){
		
		if (sessionMap.containsKey("userName")) {
			
			StudentBean stud = new StudentBean();
			stud.setSi_id(exrslt.getSi_id());
			stud.setAc_year(exrslt.getAcademic_year());
			
			stud_personal_info = StudentDAO.getStudentProfileByAcademicYear(stud);
			
			if(stud_personal_info.get(0).getRslt_status().equals("Passed")){
				stud.setRslt_status("Promotted to " + CRoomDAO.getClassName(String.valueOf(Integer.parseInt(exrslt.getCl_id()) + 1)));
			}
			if(stud_personal_info.get(0).getRslt_status().equals("Failed")){
				stud.setRslt_status("Detained in " + exrslt.getCl_name());
			}
			
			stud.setAge(AgeCalculator.calculateAgeBasedOnGivenYear(stud_personal_info.get(0).getDob(), exrslt.getAcademic_year()));
			
			String x = stud_personal_info.get(0).getDob();
			if(x.length() > 0){
				///////////////////////extracting day, month and year from the string given
				int[] separator = new int[2];
				for (int i = 0, j = 1, y = 0; i < x.length(); i++, j++) {
				    if (x.substring(i, j).equalsIgnoreCase("-")) {
				        separator[y] = i;
				        y++;
				    }
				}
				String g_year = x.substring(0, separator[0]);
				String g_month = x.substring(separator[0] + 1, separator[1]);
				String g_day = x.substring(separator[1] + 1);
				
				stud.setDob(g_day + "-" + g_month + "-" + g_year);				
			}
			
			exrslt.setStud(stud);
			//exrslt.setCur_date(TodayDate_YYYYMMDD.getDayMonthYearFormat());
			String yr = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear();
			if(Integer.parseInt(yr) == Integer.parseInt(exrslt.getAcademic_year())){
				
				exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			} else {
				
				exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getDefaultLastDateOfSelectedPassedYear(exrslt.getAcademic_year())));
			}
			
			BevalBean bvb = new BevalBean();
			bvb.setCl_id(exrslt.getCl_id());
			bvb.setSi_id(exrslt.getSi_id());
			bvb.setAc_year(exrslt.getAcademic_year());
			
			bvb.setQr_id("1");
			firstQuarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
			
			bvb.setQr_id("2");
			secondQuarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
			
			bvb.setQr_id("3");
			thirdQuarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
			
			bvb.setQr_id("4");
			fourthQuarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
			
			if(firstQuarterEvaluation.size() > 0){
				
				setEvalList(firstQuarterEvaluation);
				
			} else if(secondQuarterEvaluation.size() > 0){
				
				setEvalList(secondQuarterEvaluation);
				
			} else if(thirdQuarterEvaluation.size() > 0){
				
				setEvalList(thirdQuarterEvaluation);
				
			} else if(fourthQuarterEvaluation.size() > 0){
				
				setEvalList(fourthQuarterEvaluation);
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
		
	public String examRsltDetail(){
		if (sessionMap.containsKey("userName")) {
			subList = ExamResultDAO.getSubjectList(exrslt);		
			studentMarkList = ExamResultDAO.getSubjectTotalMarkList(exrslt, subList);		
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String examRsltPerSubDetail(){
		if (sessionMap.containsKey("userName")) {
			examTypeList = ExamResultDAO.getExamTypeList(exrslt);			
			markPerExamType = ExamResultDAO.getMarkPerExamType(examTypeList, exrslt);		
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String examRsltPerSubDetail_editform(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String examRsltPerSubDetail_update(){
		
		if (sessionMap.containsKey("userName")) {
			
			int currentYear = Integer.parseInt(DateConvertor.returnConvertedEthiopianYear(TodayDate_YYYYMMDD.getToday()));
			
			///***THIS IS TO RESTRICT PREVIOUS YEARS RESULT UPDATE ***///
			if(currentYear > Integer.parseInt(exrslt.getAcademic_year())){
			
				return "UPDATENOTALLOWED";
			}
			
			boolean rslt = ExamResultDAO.updateStudResult(exrslt, getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			
			if(rslt && exrslt.getPageFlag().equals("2")){
				return "UPDATESUCCESS";
			}
			
			if(rslt){
				//students list with filled mark
				addActionMessage("Successfully Updated.");
				studRsltList = ExamResultDAO.getStudRsltList(studList, exrslt);
				
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String saveFinalStudentRsltDecision(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = ExamResultDAO.saveStudentRsltFinalDescion(exrslt, getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			
			if(rslt){
				
				studFullYearMark = ExamResultDAO.getEachQuarterStudTotalRslt(exrslt);			
				
				if(studFullYearMark.size() > 0){
					///// sorting student based on annual year avg
					Collections.sort(studFullYearMark, new Comparator<ExamResultBean>() {
						public int compare(ExamResultBean a, ExamResultBean b){
							int rslt = 0;
							
							if(Double.parseDouble(b.getAverage_quarter_mark()) > Double.parseDouble(a.getAverage_quarter_mark())){
								rslt = 1;
							}
							if(Double.parseDouble(b.getAverage_quarter_mark()) < Double.parseDouble(a.getAverage_quarter_mark())){
								rslt = -1;
							}
							if(Double.parseDouble(b.getAverage_quarter_mark()) == Double.parseDouble(a.getAverage_quarter_mark())){
								rslt = 0;
							}
							
							return rslt;
							//b.getAverage_quarter_mark().compareTo(a.getAverage_quarter_mark());
						}
					});
				}
				
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String getQuarterResultView(){
		
		if (sessionMap.containsKey("userName")) {
			
			exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getDayMonthYearFormat()));
			
			quarterRsltView = ExamResultDAO.getQuarterResult(exrslt);
			
//			if(quarterRsltView.size() > 0){
//				//sort the student list per their average mark
//				Collections.sort(quarterRsltView, new Comparator<ExamResultBean>() {
//					public int compare(ExamResultBean a, ExamResultBean b){
//						return a.getSub_name().compareTo(b.getSub_name());						
//					}
//				});
//			}
			
			Double grand_total = 0.0;
			int subNo = 0;
			
			for(int i = 0; i < quarterRsltView.size(); i++){
				if(quarterRsltView.get(i).getSub_included_in_total_mark().equals("Yes") && quarterRsltView.get(i).getConvert_to_grade().equals("NO")){
					subNo++;
					grand_total += Double.parseDouble(quarterRsltView.get(i).getQuarter_total());
				}				
			}
			
			String total_number = StudentDAO.getStudentNumPerClassDetail(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			
			StudentBean stb = new StudentBean();
			stb.setTotal_number(total_number);
			exrslt.setStud(stb);
			
			exrslt.setGrand_quarter_total(String.valueOf(grand_total));
			exrslt.setAverage_quarter_mark(String.valueOf(grand_total/subNo));
			
			BevalBean bvb = new BevalBean();
			bvb.setCl_id(exrslt.getCl_id());
			bvb.setSi_id(exrslt.getSi_id());
			bvb.setAc_year(exrslt.getAcademic_year());
			bvb.setQr_id(exrslt.getAt_id());
			
			quarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
			quarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
			getDefaultComment = CertDefaultComDAO.getCommentPerRank(exrslt.getAverage_quarter_mark());
			
			exrslt.setHomeroom_teacher_name(ExamResultDAO.getHomeroomTeacherName(exrslt));
			if(quarterEvaluation.size() > 0){
				exrslt.setAt_name(quarterEvaluation.get(0).getQuarter());
			}
			
			getAbsentDayList = ExamResultDAO.getStudentAbsentDayList(exrslt);
			
			/////**** added for compare 2nd quart with 1st quart result **** /////
			if(exrslt.getAt_id().equals("2")){
				
				//exrslt.setAt_id("1");
				exrslt.setQr1_at_id("1");
				firstQuarterRslt = ExamResultDAO.getPDFFullYearCertificateRsltView(exrslt);				
			}
			
			/////**** added for compare 3rd quarter with 2nd and 1st quart result **** /////
			if(exrslt.getAt_id().equals("3")){
				
				//exrslt.setAt_id("1");
				exrslt.setQr1_at_id("1");
				firstQuarterRslt = ExamResultDAO.getPDFFullYearCertificateRsltView(exrslt);
				
				//exrslt.setAt_id("2");
				exrslt.setQr2_at_id("2");
				secondQuarterRslt = ExamResultDAO.getPDFFullYearCertificateRsltView(exrslt);
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public synchronized String generateAllStudentQuarterResultCertificatePDF(){
		
		if (sessionMap.containsKey("userName")) {
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			//certificate rank display option -- pass using stud_rank
			String certRankPrintOption = exrslt.getStud_rank();
			
			exrslt.setAcademic_year(exrslt.getAcademic_year());
			
			exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getDayMonthYearFormat()));
			exrslt.setAt_name(ExamResultDAO.getQuarterName(exrslt.getAt_id()));
			
			String FileName = SysConstant.CERT_PDF_PATH + " " + exrslt.getAt_name() + "_" + exrslt.getCl_name() + "_" + exrslt.getCd_name() + "_Stud_Certificate_" + exrslt.getCur_date() + "_" + timeStamp + ".pdf";
			
			studTotalMark = getStudentRankWithTotalMark(exrslt); ///>>> Connection 7
			
			PDFGenerator pg = new PDFGenerator();
			
			synchronized (this) {
				
			pg.openCertDocument(FileName);
			
			String total_number = StudentDAO.getStudentNumPerClassDetail(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			
			///>>>>>>>>>> NEED TO BE UPDATED --- STUDENT TOTAL MARK MUST BE FETCH WITH ONE QUERY <<<<<<<<<<<<<<<///
			for(int i = 0; i < studTotalMark.size(); i++){
				
				exrslt.setSi_id(studTotalMark.get(i).getSi_id());
				//exrslt.setAcademic_year(exrslt.getAcademic_year());
				
				quarterRsltView = ExamResultDAO.getQuarterResult(exrslt); ///>>> Connection 1
				
				Double grand_total = 0.0;
				int subNo = 0;
				
				for(int j = 0; j < quarterRsltView.size(); j++){
					if(quarterRsltView.get(j).getSub_included_in_total_mark().equals("Yes") && quarterRsltView.get(j).getConvert_to_grade().equals("NO")){
						subNo++;
						grand_total += Double.parseDouble(quarterRsltView.get(j).getQuarter_total());
					}				
				}
				
				StudentBean stb = new StudentBean();
				stb.setTotal_number(total_number);
				exrslt.setStud(stb);
				
				exrslt.setGrand_quarter_total(String.valueOf(grand_total));
				exrslt.setAverage_quarter_mark(String.valueOf(grand_total/subNo));
				
				BevalBean bvb = new BevalBean();
				bvb.setCl_id(exrslt.getCl_id());
				bvb.setSi_id(studTotalMark.get(i).getSi_id());
				bvb.setAc_year(exrslt.getAcademic_year());
				bvb.setQr_id(exrslt.getAt_id());
				
				quarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb); ///>>> Connection 2
				quarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt); ///>>> Connection 3
				getDefaultComment = CertDefaultComDAO.getCommentPerRank(exrslt.getAverage_quarter_mark()); ///>>> Connection 4
				getAbsentDayList = ExamResultDAO.getStudentAbsentDayList(exrslt); ///>>> Connection 5
				
				exrslt.setFname(studTotalMark.get(i).getFname());
				exrslt.setMname(studTotalMark.get(i).getMname());
				exrslt.setGname(studTotalMark.get(i).getGname());
				exrslt.setHomeroom_teacher_name(ExamResultDAO.getHomeroomTeacherName(exrslt)); ///>>> Connection 6
				exrslt.setStud_rank(studTotalMark.get(i).getStud_rank());
				
				/////**** added for comparison 2nd quart with 1st quart result **** /////
				if(exrslt.getAt_id().equals("2")){
					
					//exrslt.setAt_id("1");
					exrslt.setQr1_at_id("1");
					firstQuarterRslt = ExamResultDAO.getPDFFullYearCertificateRsltView(exrslt);				
				}
				
				/////**** added for comparison 3rd quarter with 2nd and 1st quart result **** /////
				if(exrslt.getAt_id().equals("3")){
					
					//exrslt.setAt_id("1");
					exrslt.setQr1_at_id("1");
					firstQuarterRslt = ExamResultDAO.getPDFFullYearCertificateRsltView(exrslt);
										
					//exrslt.setAt_id("2");
					exrslt.setQr2_at_id("2");
					secondQuarterRslt = ExamResultDAO.getPDFFullYearCertificateRsltView(exrslt);
				}
				
				pg.generateCertficatePdf(exrslt, quarterEvaluation, quarterTeacherComment, getDefaultComment, quarterRsltView, getAbsentDayList, firstQuarterRslt, secondQuarterRslt, certRankPrintOption);
				
				exrslt.setQr1_at_id("0");
				exrslt.setQr2_at_id("0");
				exrslt.setQr3_at_id("0");				
			}
			
			pg.closeCertDocument();
			
			OpenFile.openExistingPDFFile(FileName);			
			}
			
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getClassListForQrtView(){
		grade_rslt = StudentDAO.getClassList();
		return SUCCESS;
	}
	
	public String getTeachersCommentAddForm(){
		if (sessionMap.containsKey("userName")) {
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveTeachersComment(){
		if (sessionMap.containsKey("userName")) {
			
			boolean validateCommentExistance = ExamResultDAO.checkCommentExistance(exrslt);
			
			if(validateCommentExistance){
				addActionError("You already give comment for this student. You can edit the existing comment");
				return ERROR;
			}
			
			boolean rslt = ExamResultDAO.saveTeachersComment(exrslt, (String)sessionMap.get("loggedInUserType"), getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			
			if(rslt){
			
				//String curyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();				
				//stud_rslt = StudentDAO.getListPerGradeDetail(exrslt.getCl_id(), exrslt.getCd_id(), curyear);
				//studTotalMark = ExamResultDAO.getTotalSumStudRslt(stud_rslt, exrslt.getCl_id(), curyear, exrslt.getAt_id());
				studTotalMark = ExamResultDAO.getQuarterTotalSumStudRslt(exrslt);
				
				if(studTotalMark.size() > 0){
					//sort the student list per their average mark
					Collections.sort(studTotalMark, new Comparator<ExamResultBean>() {
						public int compare(ExamResultBean a, ExamResultBean b){
							//return b.getAverage_mark().compareTo(a.getAverage_mark());
							if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
								return 0;
							} else if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
								return 1;
							} else {
								return -1;
							}
						}
					});
				}
				
	            return SUCCESS;
            
			} else {
				
				addActionError("Your comment is not saved. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getTeachersCommentEditForm(){
		if (sessionMap.containsKey("userName")) {
			
			getTeacherComment = ExamResultDAO.getTeacherComment(exrslt);
			
            return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateTeachersComment(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = ExamResultDAO.updateTeachersComment(exrslt, (String)sessionMap.get("loggedInUserType"), getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			
			if(rslt){
				//String curyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
				//stud_rslt = StudentDAO.getListPerGradeDetail(exrslt.getCl_id(), exrslt.getCd_id(), curyear);
				//studTotalMark = ExamResultDAO.getTotalSumStudRslt(stud_rslt, exrslt.getCl_id(), curyear, exrslt.getAt_id());
				studTotalMark = ExamResultDAO.getQuarterTotalSumStudRslt(exrslt);
				
				if(studTotalMark.size() > 0){
					//sort the student list per their average mark
					Collections.sort(studTotalMark, new Comparator<ExamResultBean>() {
						public int compare(ExamResultBean a, ExamResultBean b){
							//return b.getAverage_mark().compareTo(a.getAverage_mark());
							if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
								return 0;
							} else if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
								return 1;
							} else {
								return -1;
							}
						}
					});
				}
				
				return SUCCESS;
			} else {
				
				addActionError("Your comment is not saved. Please try again.");
				return ERROR;
			}
            
		} else {
			return INPUT;
		}
	}
	
	public String generalStudentResultDecision(){
		if (sessionMap.containsKey("userName")) {
			
			studFullYearMark = studFullYearMark_temp;
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveStudentFinalResultStatusPerAverageRange(){
		
		if (sessionMap.containsKey("userName")) {
			
			studFullYearMark = studFullYearMark_temp;			

			boolean rslt = StudentRegDAO.saveStudentFinalResultStatus(exrslt, studFullYearMark_temp);
			
			if(rslt){
				
				studFullYearMark = ExamResultDAO.getEachQuarterStudTotalRslt(exrslt);			
				
				if(studFullYearMark.size() > 0){
					///// sorting student based on annual year avg
					Collections.sort(studFullYearMark, new Comparator<ExamResultBean>() {
						public int compare(ExamResultBean a, ExamResultBean b){
							int rslt = 0;
							
							if(Double.parseDouble(b.getAverage_quarter_mark()) > Double.parseDouble(a.getAverage_quarter_mark())){
								rslt = 1;
							}
							if(Double.parseDouble(b.getAverage_quarter_mark()) < Double.parseDouble(a.getAverage_quarter_mark())){
								rslt = -1;
							}
							if(Double.parseDouble(b.getAverage_quarter_mark()) == Double.parseDouble(a.getAverage_quarter_mark())){
								rslt = 0;
							}
							
							return rslt;
							//b.getAverage_quarter_mark().compareTo(a.getAverage_quarter_mark());
						}
					});
				}
				
				return SUCCESS;
			} else {
				return ERROR;
			}
			
		} else {
			return INPUT;
		}
	}
	
	public String updateStudentFinalResultStatusPerAverageRange(){
		
		if (sessionMap.containsKey("userName")) {
			
			studFullYearMark = studFullYearMark_temp;			

			boolean rslt = StudentRegDAO.updateStudentFinalResultStatus(exrslt, studFullYearMark_temp);
			
			if(rslt){
				
				studFullYearMark = ExamResultDAO.getEachQuarterStudTotalRslt(exrslt);			
				
				if(studFullYearMark.size() > 0){
					///// sorting student based on annual year avg
					Collections.sort(studFullYearMark, new Comparator<ExamResultBean>() {
						public int compare(ExamResultBean a, ExamResultBean b){
							int rslt = 0;
							
							if(Double.parseDouble(b.getAverage_quarter_mark()) > Double.parseDouble(a.getAverage_quarter_mark())){
								rslt = 1;
							}
							if(Double.parseDouble(b.getAverage_quarter_mark()) < Double.parseDouble(a.getAverage_quarter_mark())){
								rslt = -1;
							}
							if(Double.parseDouble(b.getAverage_quarter_mark()) == Double.parseDouble(a.getAverage_quarter_mark())){
								rslt = 0;
							}
							
							return rslt;
							//b.getAverage_quarter_mark().compareTo(a.getAverage_quarter_mark());
						}
					});
				}
				
				return SUCCESS;
			} else {
				return ERROR;
			}
			
		} else {
			return INPUT;
		}
	}
	
	
	
		
	
	
	
	
	
	/*
	 * The method generate the HTML format of the quarter report card and forward to email method
	 */
	public String emailStudentQuarterResult(){
		if (sessionMap.containsKey("userName")) {
			
			String emailto = ExamResultDAO.getParentsEmailAddress(exrslt);
			
			if(emailto.length() == 0){
				
				quarterRsltView = ExamResultDAO.getQuarterResult(exrslt);
				
				exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
							
				Double grand_total = 0.0;
				int subNo = 0;
				
				for(int i = 0; i < quarterRsltView.size(); i++){
					if(quarterRsltView.get(i).getSub_included_in_total_mark().equals("Yes") && quarterRsltView.get(i).getConvert_to_grade().equals("NO")){
						subNo++;
						grand_total += Double.parseDouble(quarterRsltView.get(i).getQuarter_total());
					}				
				}
				
				exrslt.setGrand_quarter_total(String.valueOf(grand_total));
				exrslt.setAverage_quarter_mark(String.valueOf(grand_total/subNo));
				
				BevalBean bvb = new BevalBean();
				bvb.setCl_id(exrslt.getCl_id());
				bvb.setSi_id(exrslt.getSi_id());
				bvb.setAc_year(exrslt.getAcademic_year());
				bvb.setQr_id(exrslt.getAt_id());
				
				quarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
				quarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
								
				exrslt.setHomeroom_teacher_name(ExamResultDAO.getHomeroomTeacherName(exrslt));
				if(quarterEvaluation.size() > 0){
					exrslt.setAt_name(quarterEvaluation.get(0).getQuarter());
				}
	            
				addActionMessage("Parent email doesn't exist.");
				
	            return ERROR;
			}
			
			EmailComBean ecb = new EmailComBean();
			
			ecb.setFrom(SysConstant.SCHOOL_GMAIL_ACCOUNT);
			ecb.setPassword(SysConstant.SCHOOL_GMAIL_ACCOUNT_PASSWORD);
			ecb.setTo(emailto);
			ecb.setSubject("Student " + exrslt.getFname() + " " + exrslt.getMname() + " " + exrslt.getGname() + ", " + exrslt.getAt_name()  + " Progress Report");
			
			String header = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px;\" border=\"0\"><tr><td align=\"center\">"
					+ "<br/><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td align=\"center\" style=\"font-size: 24px; font-weight: bold; height: 30px;\"> " 
					+ "Everest International Academy </td></tr><tr><td align=\"center\" style=\"font-weight: bold; height: 30px;\"> P.O.Box 21628 Tel. +251-11-367 9030/11-367-9066 <br/> Alemgena, Ethiopia </td></tr></table><br/>"
					+ "</td></tr></table>";
		
			String title = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px;\" border=\"0\"><tr><td align=\"center\">"
					+ "<table cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"font-size: 28px; font-weight: bold; height: 50px; text-decoration: underline; text-align: center;\">Student's Progress Report</td></tr></table><br/></td></tr></table>";
		
			String stud_info = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px;\" border=\"0\"><tr>"
						+ "<td style=\"padding-top: 5px; padding-bottom: 5px; border-bottom: thin; border-top: thin; border-bottom-color: silver; border-top-color: silver; border-bottom-style: solid; border-top-style: solid; border-bottom-width: thin; border-top-width: thin;\">"
							+ "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"
							+ "<tr style=\"height: 30px;\">"
							+ "<td colspan=\"2\"><span style=\"font-weight: bold;\"> Student's Name: </span> <span style=\"text-decoration: underline;\">" + exrslt.getFname() + " " + exrslt.getMname() +" " + exrslt.getGname() + "</span></td>"
							+ "</tr><tr style=\"height: 30px;\">"
							+ "<td> <span style=\"font-weight: bold;\"> Class: </span><span style=\"text-decoration: underline;\">" + exrslt.getCl_name() + " " + exrslt.getCd_name() + "</span> </td>"
							+ "</tr><tr style=\"height: 30px;\">"
							+ "<td><span style=\"font-weight: bold;\"> Home Room Teacher: </span><span style=\"text-decoration: underline;\">" + exrslt.getHomeroom_teacher_name() + "</span> </td>"
							+ "</tr><tr style=\"height: 30px;\">"
							+ "<td> <span style=\"font-weight: bold;\"> Term: </span> <span style=\"text-decoration: underline;\">" + exrslt.getAt_name() + "</span> </td>"
							+ "</tr><tr style=\"height: 30px;\">"
							+ "<td> <span style=\"font-weight: bold;\"> Date: </span> <span style=\"text-decoration: underline;\">" + exrslt.getCur_date() + "</span> </td>"
							+ "</tr></table>"						
						+ "</td></tr></table><br/>";
		
			String cert_body_first_part = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px;\" border=\"0\">" +
				" <tr>" +
				"<td valign=\"top\">" +	
					"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"1\" border=\"0\" rules=\"rows\" style=\"border: thin; border-color: gray; border-style: solid; border-width: thin;\">"
								+ "<tr style=\"font-weight: bold; height: 40px;\">"
								+	"<td width=\"20%\">Subject</td>"
								+	"<td width=\"20%\" valign=\"top\" align=\"center\">Cumulative <br/>Assessment <br/>50%</td>"
								+	"<td width=\"15%\" valign=\"top\" align=\"center\">Exam <br/>Mark <br/> 50%</td>"
								+	"<td width=\"15%\" valign=\"top\" align=\"center\">Total Mark 100%</td>"								
								+ "</tr>";
					
			String cert_body_second_part = "<tr style=\"font-weight: bold;\">"
									+ "<td colspan=\"3\" height=\"25px;\"> Rank </td>"
									+ "<td align=\"center\">" + exrslt.getStud_rank() + "</td>"									
								+ "</tr>"
							+ "</table>"							
							+ "<br/>"							
							+ "<table width=\"100%\" style=\"border-color: silver; border-style: solid; border-width: thin;\">"
							+ "<tr>"
								+ "<td colspan=\"2\" align=\"left\" style=\"font-weight: bold;\">"
									+ "Grade System"
								+ "</td>"
								+ "</tr>"
								+ "<tr>"									
									+ "<td> 95% - 100%(Excellent) </td>"
									+ "<td> 85% - 94%(Very Good) </td>"
								+ "</tr>"
								+ "<tr>"
									+ "<td> 75% - 84%(Good)</td>"
									+ "<td> 70% - 74%(Satisfactory) </td>"
								+ "</tr>"
								+ "<tr>"
									+ "<td> 65% - 69%(Pass)</td>"
									+ "<td> Below 65%(Fail) </td>"
								+ "</tr>"
							+ "</table>";
			
			String behavioural_evaluation_first_part = "<br/>" +
					"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"padding-left: 5px; padding-right: 5px;\">" +
					"<tr>" +
					"<td valign=\"top\">" +
					"<table width=\"100%\" cellpadding=\"0\" rules=\"rows\" cellspacing=\"0\" style=\"border: thin; border-color: gray; border-style: solid; border-width: thin;\">" +
								"<tr height=\"30px;\" style=\"font-weight: bold;\">" +
									"<td align=\"center\">Attitude</td>" +
									"<td align=\"center\">Tutors Comments</td>" +
								"</tr>";
			
			String behavioural_evaluation_second_part = "</table>" +
							"<br/>" +
							"<table width=\"100%\" cellpadding=\"0\" rules=\"none\" cellspacing=\"0\" style=\"border: thin; border-color: gray; border-style: solid; border-width: thin;\">" +
								"<tr>" +
									"<td>" +
										"Key:<br/> A - Excellent, B+ - V.Good, B - Good, C - Fair, D - Needs Improvement" +
									"</td>" +
								"</tr>" +
							"</table>";
			
			getDefaultComment = CertDefaultComDAO.getCommentPerRank(exrslt.getStud_rank());
			String defaultComment = "";
			if(getDefaultComment.size() > 0){
				defaultComment = getDefaultComment.get(0).getEdc_content();
			}
			
			String footer = "<br/>" +
				"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"padding-left: 5px; padding-right: 5px; border-color: silver; border-style: solid; border-width: thin; padding-top: 10px; padding-bottom: 10px;\">" +
				"<tr>" +
				"<td valign=\"top\">" +
				"<span style=\"font-weight: bold;\">Class Teacher's/Director's comment:</span><br/><br/>" + defaultComment + " " + exrslt.getErc_content() +
				"</td>" +
				"</tr>" +			
				"</table>";
			
			quarterRsltView = ExamResultDAO.getQuarterResult(exrslt);
			
			String emailResultMiddlePartContent = "";
			String cum_mark = "";
			String final_mark = "";
			
			for(int i = 0; i < quarterRsltView.size(); i++){				
				
				final_mark = quarterRsltView.get(i).getFinal_exam_mark() == null?"-":quarterRsltView.get(i).getFinal_exam_mark();
				
				if(quarterRsltView.get(i).getConvert_to_grade().equals("NO")){
					cum_mark = quarterRsltView.get(i).getCummulative_mark() == null?"-":quarterRsltView.get(i).getCummulative_mark();
				} else {
					cum_mark = "-";
				}
				
				if(emailResultMiddlePartContent.length() == 0){
					emailResultMiddlePartContent = "<tr><td height=\"25px;\" style=\"padding-left: 10px;\">" + quarterRsltView.get(i).getSub_name() + "</td><td align=\"center\">" + cum_mark + "</td><td align=\"center\">" + final_mark + "</td><td align=\"center\">";
					if(quarterRsltView.get(i).getConvert_to_grade().equals("NO")){
						String grdQrtResult = quarterRsltView.get(i).getQuarter_total() == null?"-":quarterRsltView.get(i).getQuarter_total();
						emailResultMiddlePartContent = emailResultMiddlePartContent + grdQrtResult + "</td></tr>";
					} else {
						String grdResult = quarterRsltView.get(i).getGrading_result() == null?"-":quarterRsltView.get(i).getGrading_result();
						emailResultMiddlePartContent = emailResultMiddlePartContent + grdResult + "</td></tr>";
					}
				} else {					
					emailResultMiddlePartContent = emailResultMiddlePartContent + "<tr><td height=\"25px;\" style=\"padding-left: 10px;\">" + quarterRsltView.get(i).getSub_name() + "</td><td align=\"center\">" + cum_mark + "</td><td align=\"center\">" + final_mark + "</td><td align=\"center\">";
					if(quarterRsltView.get(i).getConvert_to_grade().equals("NO")){						
						String grdQrtResult = quarterRsltView.get(i).getQuarter_total() == null?"-":quarterRsltView.get(i).getQuarter_total();
						emailResultMiddlePartContent = emailResultMiddlePartContent + grdQrtResult + "</td></tr>";
					} else {
						String grdResult = quarterRsltView.get(i).getGrading_result() == null?"-":quarterRsltView.get(i).getGrading_result();
						emailResultMiddlePartContent = emailResultMiddlePartContent + grdResult + "</td></tr>";
					}
				}
			}
			DecimalFormat fmt = new DecimalFormat("###.##");
			String emailResultTotalMarkPartContent = "<tr style=\"font-weight: bold;\">" +
									"<td colspan=\"3\" height=\"25px;\"> Sum </td>" +
									"<td align=\"center\">" + exrslt.getGrand_quarter_total() + "</td>" +									
								"</tr>" +
								"<tr style=\"font-weight: bold;\">" +
									"<td colspan=\"3\" height=\"25px;\"> Average </td>" +
									"<td align=\"center\">" + 
									fmt.format(Double.parseDouble(exrslt.getAverage_quarter_mark())) +										 
									"</td>" +									
								"</tr>";
			
			
			BevalBean bvb = new BevalBean();
			bvb.setCl_id(exrslt.getCl_id());
			bvb.setSi_id(exrslt.getSi_id());
			bvb.setAc_year(exrslt.getAcademic_year());
			bvb.setQr_id(exrslt.getAt_id());
			
			quarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
			
			String behavioural_evaluation_middle_part = "";
			
			for(int i = 0; i < quarterEvaluation.size(); i++){
				if(behavioural_evaluation_middle_part.length() == 0){
					behavioural_evaluation_middle_part = "<tr>" +
										"<td>" + quarterEvaluation.get(i).getBt_title() + "</td>" +
										"<td align=\"center\">" + quarterEvaluation.get(i).getBg_name() + "</td>" +
									"</tr>";
				} else {
					behavioural_evaluation_middle_part = behavioural_evaluation_middle_part + "<tr>" +
							"<td>" + quarterEvaluation.get(i).getBt_title() + "</td>" +
							"<td align=\"center\">" + quarterEvaluation.get(i).getBg_name() + "</td>" +
						"</tr>";
				}
			}
			
			getAbsentDayList = ExamResultDAO.getStudentAbsentDayList(exrslt);
			
			String attendance_upper = "<br/> <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" rules=\"rows\" style=\"border-bottom-color: gray; border-bottom-style: solid; border-bottom-width: thin; border-top-color: gray; border-top-style: solid; border-top-width: thin;\">";
			
			String attendance_body = "";
			
			String attendance_footer = "</table> <br/>";
			
			for(int i = 0; i < getAbsentDayList.size(); i++){
				if(attendance_body.length() == 0){
				attendance_body = "<tr>" +
										"<td style=\"font-weight: bold; width: 30%; height: 30px;\">Number of Absent Days:</td>" +
										"<td>" + getAbsentDayList.get(i).getNoOfAbsentDay() +
										"</td>" +
									"</tr>" +
									"<tr>" +
										"<td style=\"font-weight: bold; width: 20%; height: 30px;\">Days List:</td>" +
										"<td>" + getAbsentDayList.get(i).getAbsentDayList() +
										"</td>" +
									"</tr>";
				} else {
					attendance_body = attendance_body + "<tr>" +
							"<td style=\"font-weight: bold; width: 30%; height: 30px;\">Number of Absent Days:</td>" +
							"<td>" + getAbsentDayList.get(i).getNoOfAbsentDay() +
							"</td>" +
						"</tr>" +
						"<tr>" +
							"<td style=\"font-weight: bold; width: 20%; height: 30px;\">Days List:</td>" +
							"<td>" + getAbsentDayList.get(i).getAbsentDayList() +
							"</td>" +
						"</tr>";
				}
			}
						
			ecb.setHtmlbody(header + title + stud_info + cert_body_first_part + emailResultMiddlePartContent + emailResultTotalMarkPartContent + cert_body_second_part + attendance_upper + attendance_body + attendance_footer + behavioural_evaluation_first_part + behavioural_evaluation_middle_part + behavioural_evaluation_second_part + footer);
			
			EmailComAction eca = new EmailComAction();
			String rslt = eca.sendAnEmail(ecb);
			
			if(rslt.equals(SUCCESS)){
			
				studTotalMark = ExamResultDAO.getQuarterTotalSumStudRslt(exrslt);
				
				if(studTotalMark.size() > 0){
					//sort the student list per their average mark
					Collections.sort(studTotalMark, new Comparator<ExamResultBean>() {
						public int compare(ExamResultBean a, ExamResultBean b){
							//return b.getAverage_mark().compareTo(a.getAverage_mark());
							if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
								return 0;
							} else if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
								return 1;
							} else {
								return -1;
							}
						}
					});
				}
				addActionMessage("An email succefully sent to " + exrslt.getFname() + " " + exrslt.getMname() + " " + exrslt.getGname() + " parent(s).");
	            return SUCCESS;
	            
			} else if(rslt.equals(ERROR)){
            
				exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
							
				Double grand_total = 0.0;
				int subNo = 0;
				
				for(int i = 0; i < quarterRsltView.size(); i++){
					if(quarterRsltView.get(i).getSub_included_in_total_mark().equals("Yes") && quarterRsltView.get(i).getConvert_to_grade().equals("NO")){
						subNo++;
						grand_total += Double.parseDouble(quarterRsltView.get(i).getQuarter_total());
					}				
				}
				
				exrslt.setGrand_quarter_total(String.valueOf(grand_total));
				exrslt.setAverage_quarter_mark(String.valueOf(grand_total/subNo));
				
				quarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
				exrslt.setHomeroom_teacher_name(ExamResultDAO.getHomeroomTeacherName(exrslt));
				if(quarterEvaluation.size() > 0){
					exrslt.setAt_name(quarterEvaluation.get(0).getQuarter());
				}
				addActionMessage("There is no internet connection.");
	            return ERROR;
			} else {				
				return INPUT;
			}			
		} else {			
			return INPUT;
		}
	}
	
	/* The method generate the html format of the quarter report card and forward to email method
	 * Required
	 * General for all >>> at_id, cl_id, cd_id, at_name, cl_name, cd_name, academic_year, homeroom_teacher_name, cur_date
	 * Info per each student >>> student_name(fname, mname, gname), student_id(email_to), erc_comment
	 */
	public String emailStudentQuarterResult(ExamResultBean exrslt){
			
			/// Check whether the family email exist or not			
			String emailto = ExamResultDAO.getParentsEmailAddress(exrslt);
			
			if(emailto.length() == 0){
				return "NO_PARENT_EMAIL";
			}
			
			/// get home_room_teacher and quarter_name
			BevalBean bvb = new BevalBean();
			bvb.setCl_id(exrslt.getCl_id());
			bvb.setSi_id(exrslt.getSi_id());
			bvb.setAc_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			bvb.setQr_id(exrslt.getAt_id());
			
			quarterEvaluation = BevalDAO.getStudentBevalRsltList(bvb);
			quarterTeacherComment = ExamResultDAO.getQuarterTeacherComment(exrslt);
			
			if(quarterTeacherComment.size() > 0){
				exrslt.setHomeroom_teacher_name(ExamResultDAO.getHomeroomTeacherName(exrslt));
			}
			if(quarterEvaluation.size() > 0){
				exrslt.setAt_name(quarterEvaluation.get(0).getQuarter());
			}
			
			getDefaultComment = CertDefaultComDAO.getCommentPerRank(exrslt.getStud_rank());
			
			/// getting Ethiopian the day of today
			exrslt.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			exrslt.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			/// forming an email content start
			EmailComBean ecb = new EmailComBean();
			
			ecb.setFrom(SysConstant.SCHOOL_GMAIL_ACCOUNT);
			ecb.setPassword(SysConstant.SCHOOL_GMAIL_ACCOUNT_PASSWORD);
			ecb.setTo(emailto);
			ecb.setSubject("Student " + exrslt.getFname() + " " + exrslt.getMname() + " " + exrslt.getGname() + ", " + exrslt.getAt_name()  + " Progress Report");
			
			String header = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px;\" border=\"0\"><tr><td align=\"center\">"
					+ "<br/><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td align=\"center\" style=\"font-size: 24px; font-weight: bold; height: 30px;\"> " 
					+ "Everest International Academy </td></tr><tr><td align=\"center\" style=\"font-weight: bold; height: 30px;\"> P.O.Box 21628 Tel. +251-11-367 9030/11-367-9066 <br/> Alemgena, Ethiopia </td></tr></table><br/>"
					+ "</td></tr></table>";
		
			String title = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px;\" border=\"0\"><tr><td align=\"center\">"
					+ "<table cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"font-size: 28px; font-weight: bold; height: 50px; text-decoration: underline; text-align: center;\">Student's Progress Report</td></tr></table><br/></td></tr></table>";
		
			String stud_info = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px;\" border=\"0\"><tr>"
						+ "<td style=\"padding-top: 5px; padding-bottom: 5px; border-bottom: thin; border-top: thin; border-bottom-color: silver; border-top-color: silver; border-bottom-style: solid; border-top-style: solid; border-bottom-width: thin; border-top-width: thin;\">"
							+ "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"
							+ "<tr style=\"height: 30px;\">"
							+ "<td colspan=\"2\"><span style=\"font-weight: bold;\"> Student's Name: </span> <span style=\"text-decoration: underline;\">" + exrslt.getFname() + " " + exrslt.getMname() +" " + exrslt.getGname() + "</span></td>"
							+ "</tr><tr style=\"height: 30px;\">"
							+ "<td> <span style=\"font-weight: bold;\"> Class: </span><span style=\"text-decoration: underline;\">" + exrslt.getCl_name() + " " + exrslt.getCd_name() + "</span> </td>"
							+ "</tr><tr style=\"height: 30px;\">"
							+ "<td><span style=\"font-weight: bold;\"> Home Room Teacher: </span><span style=\"text-decoration: underline;\">" + exrslt.getHomeroom_teacher_name() + "</span> </td>"
							+ "</tr><tr style=\"height: 30px;\">"
							+ "<td> <span style=\"font-weight: bold;\"> Term: </span> <span style=\"text-decoration: underline;\">" + exrslt.getAt_name() + "</span> </td>"
							+ "</tr><tr style=\"height: 30px;\">"
							+ "<td> <span style=\"font-weight: bold;\"> Date: </span> <span style=\"text-decoration: underline;\">" + exrslt.getCur_date() + "</span> </td>"
							+ "</tr></table>"						
						+ "</td></tr></table><br/>";
		
			String cert_body_first_part = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px;\" border=\"0\">" +
				" <tr>" +
				"<td valign=\"top\">" +	
					"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"1\" border=\"0\" rules=\"rows\" style=\"border: thin; border-color: gray; border-style: solid; border-width: thin;\">"
								+ "<tr style=\"font-weight: bold; height: 40px;\">"
								+	"<td width=\"20%\">Subject</td>"
								+	"<td width=\"20%\" valign=\"top\" align=\"center\">Cumulative <br/>Assessment <br/>50%</td>"
								+	"<td width=\"15%\" valign=\"top\" align=\"center\">Exam <br/>Mark <br/> 50%</td>"
								+	"<td width=\"15%\" valign=\"top\" align=\"center\">Total Mark 100%</td>"								
								+ "</tr>";
					
			String cert_body_second_part = "<tr style=\"font-weight: bold;\">"
									+ "<td colspan=\"3\" height=\"25px;\"> Rank </td>"
									+ "<td align=\"center\">" + exrslt.getStud_rank()  + "</td>"									
								+ "</tr>"
							+ "</table>"							
							+ "<br/>"							
							+ "<table width=\"100%\" style=\"border-color: silver; border-style: solid; border-width: thin;\">"
							+ "<tr>"
								+ "<td colspan=\"2\" align=\"left\" style=\"font-weight: bold;\">"
									+ "Grade System"
								+ "</td>"
								+ "</tr>"
								+ "<tr>"									
									+ "<td> 95% - 100%(Excellent) </td>"
									+ "<td> 85% - 94%(Very Good) </td>"
								+ "</tr>"
								+ "<tr>"
									+ "<td> 75% - 84%(Good)</td>"
									+ "<td> 70% - 74%(Satisfactory) </td>"
								+ "</tr>"
								+ "<tr>"
									+ "<td> 65% - 69%(Pass)</td>"
									+ "<td> Below 65%(Fail) </td>"
								+ "</tr>"
							+ "</table>";
			
			String behavioural_evaluation_first_part = "<br/>" +
					"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"padding-left: 5px; padding-right: 5px;\">" +
					"<tr>" +
					"<td valign=\"top\">" +
					"<table width=\"100%\" cellpadding=\"0\" rules=\"rows\" cellspacing=\"0\" style=\"border: thin; border-color: gray; border-style: solid; border-width: thin;\">" +
								"<tr height=\"30px;\" style=\"font-weight: bold;\">" +
									"<td align=\"center\">Attitude</td>" +
									"<td align=\"center\">Tutors Comments</td>" +
								"</tr>";
			
			String behavioural_evaluation_second_part = "</table>" +
							"<br/>" +
							"<table width=\"100%\" cellpadding=\"0\" rules=\"none\" cellspacing=\"0\" style=\"border: thin; border-color: gray; border-style: solid; border-width: thin;\">" +
								"<tr>" +
									"<td>" +
										"Key:<br/> A - Excellent, B+ - V.Good, B - Good, C - Fair, D - Needs Improvement" +
									"</td>" +
								"</tr>" +
							"</table>";
		
			String erc_conent = "";
			if(quarterTeacherComment.size() > 0){
				erc_conent = quarterTeacherComment.get(0).getErc_content();
			}
			if(getDefaultComment.size() > 0){
				erc_conent = getDefaultComment.get(0).getEdc_content() + erc_conent;
			}
			
			String footer = "<br/>" +
				"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"padding-left: 5px; padding-right: 5px; border-color: silver; border-style: solid; border-width: thin; padding-top: 10px; padding-bottom: 10px;\">" +
				"<tr>" +
				"<td valign=\"top\">" +
				"<span style=\"font-weight: bold;\">Class Teacher's/Director's comment:</span><br/><br/>" + erc_conent +
				"</td>" +
				"</tr>" +			
				"</table>";
			
			quarterRsltView = ExamResultDAO.getQuarterResult(exrslt);
			
			String emailResultMiddlePartContent = "";
			String cum_mark = "";
			String final_mark = "";
			
			Double grandTotal = 0.0;
			int subjectNo = 0;
			
			for(int i = 0; i < quarterRsltView.size(); i++){
				
				if(quarterRsltView.get(i).getSub_included_in_total_mark().equals("Yes") && quarterRsltView.get(i).getConvert_to_grade().equals("NO")){
					subjectNo++;
					grandTotal += Double.parseDouble(quarterRsltView.get(i).getQuarter_total());
				}
				
				final_mark = quarterRsltView.get(i).getFinal_exam_mark() == null?"-":quarterRsltView.get(i).getFinal_exam_mark();
				
				if(quarterRsltView.get(i).getConvert_to_grade().equals("NO")){
					cum_mark = quarterRsltView.get(i).getCummulative_mark() == null?"-":quarterRsltView.get(i).getCummulative_mark();
				} else {
					cum_mark = "-";
				}
				
				if(emailResultMiddlePartContent.length() == 0){
					emailResultMiddlePartContent = "<tr><td height=\"25px;\" style=\"padding-left: 10px;\">" + quarterRsltView.get(i).getSub_name() + "</td><td align=\"center\">" + cum_mark + "</td><td align=\"center\">" + final_mark + "</td><td align=\"center\">";
					if(quarterRsltView.get(i).getConvert_to_grade().equals("NO")){
						String grdQrtResult = quarterRsltView.get(i).getQuarter_total() == null?"-":quarterRsltView.get(i).getQuarter_total();
						emailResultMiddlePartContent = emailResultMiddlePartContent + grdQrtResult + "</td></tr>";
					} else {
						String grdResult = quarterRsltView.get(i).getGrading_result() == null?"-":quarterRsltView.get(i).getGrading_result();
						emailResultMiddlePartContent = emailResultMiddlePartContent + grdResult + "</td></tr>";
					}
				} else {					
					emailResultMiddlePartContent = emailResultMiddlePartContent + "<tr><td height=\"25px;\" style=\"padding-left: 10px;\">" + quarterRsltView.get(i).getSub_name() + "</td><td align=\"center\">" + cum_mark + "</td><td align=\"center\">" + final_mark + "</td><td align=\"center\">";
					if(quarterRsltView.get(i).getConvert_to_grade().equals("NO")){						
						String grdQrtResult = quarterRsltView.get(i).getQuarter_total() == null?"-":quarterRsltView.get(i).getQuarter_total();
						emailResultMiddlePartContent = emailResultMiddlePartContent + grdQrtResult + "</td></tr>";
					} else {
						String grdResult = quarterRsltView.get(i).getGrading_result() == null?"-":quarterRsltView.get(i).getGrading_result();
						emailResultMiddlePartContent = emailResultMiddlePartContent + grdResult + "</td></tr>";
					}
				}
			}
			
			String emailResultTotalMarkPartContent = "<tr style=\"font-weight: bold;\">" +
									"<td colspan=\"3\" height=\"25px;\"> Sum </td>" +
									"<td align=\"center\">" + String.valueOf(grandTotal) + "</td>" +									
								"</tr>" +
								"<tr style=\"font-weight: bold;\">" +
									"<td colspan=\"3\" height=\"25px;\"> Average </td>" +
									"<td align=\"center\">" + 
									String.valueOf(grandTotal/subjectNo) +										 
									"</td>" +									
								"</tr>";
			
			exrslt.setGrand_quarter_total(String.valueOf(grandTotal));
			exrslt.setAverage_quarter_mark(String.valueOf(grandTotal/subjectNo));
			
			String behavioural_evaluation_middle_part = "";
			
			for(int i = 0; i < quarterEvaluation.size(); i++){
				if(behavioural_evaluation_middle_part.length() == 0){
					behavioural_evaluation_middle_part = "<tr>" +
										"<td>" + quarterEvaluation.get(i).getBt_title() + "</td>" +
										"<td align=\"center\">" + quarterEvaluation.get(i).getBg_name() + "</td>" +
									"</tr>";
				} else {
					behavioural_evaluation_middle_part = behavioural_evaluation_middle_part + "<tr>" +
							"<td>" + quarterEvaluation.get(i).getBt_title() + "</td>" +
							"<td align=\"center\">" + quarterEvaluation.get(i).getBg_name() + "</td>" +
						"</tr>";
				}
			}
			
			getAbsentDayList = ExamResultDAO.getStudentAbsentDayList(exrslt);
			
			String attendance_upper = "<br/> <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" rules=\"rows\" style=\"border-bottom-color: gray; border-bottom-style: solid; border-bottom-width: thin; border-top-color: gray; border-top-style: solid; border-top-width: thin;\">";
			
			String attendance_body = "";
			
			String attendance_footer = "</table> <br/>";
			
			for(int i = 0; i < getAbsentDayList.size(); i++){
				if(attendance_body.length() == 0){
				attendance_body = "<tr>" +
										"<td style=\"font-weight: bold; width: 30%; height: 30px;\">Number of Absent Days:</td>" +
										"<td>" + getAbsentDayList.get(i).getNoOfAbsentDay() +
										"</td>" +
									"</tr>" +
									"<tr>" +
										"<td style=\"font-weight: bold; width: 20%; height: 30px;\">Days List:</td>" +
										"<td>" + getAbsentDayList.get(i).getAbsentDayList() +
										"</td>" +
									"</tr>";
				} else {
					attendance_body = attendance_body + "<tr>" +
							"<td style=\"font-weight: bold; width: 30%; height: 30px;\">Number of Absent Days:</td>" +
							"<td>" + getAbsentDayList.get(i).getNoOfAbsentDay() +
							"</td>" +
						"</tr>" +
						"<tr>" +
							"<td style=\"font-weight: bold; width: 20%; height: 30px;\">Days List:</td>" +
							"<td>" + getAbsentDayList.get(i).getAbsentDayList() +
							"</td>" +
						"</tr>";
				}
			}
						
			ecb.setHtmlbody(header + title + stud_info + cert_body_first_part + emailResultMiddlePartContent + emailResultTotalMarkPartContent + cert_body_second_part + attendance_upper + attendance_body + attendance_footer + behavioural_evaluation_first_part + behavioural_evaluation_middle_part + behavioural_evaluation_second_part + footer);
			
			EmailComAction eca = new EmailComAction();
			String rslt = eca.sendAnEmail(ecb);
			
			if(rslt.equals(SUCCESS)){
			
				return SUCCESS;
	            
			} else {            					
				
	            return ERROR;
			} 			
		
	}
	
	private List<String> successfulEmail = new ArrayList<String>();
	private List<String> unsuccessfulEmail = new ArrayList<String>();
	private List<String> emailDoesntExist = new ArrayList<String>();
	
	public String emailToAllParents(){
		if(sessionMap.containsKey("userName")){
			
			exrslt.setAcademic_year(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			//// Get all students list
			studTotalMark = ExamResultDAO.getQuarterTotalSumStudRslt(exrslt);
			if(studTotalMark.size() > 0){
				//sort the student list per their average mark
				Collections.sort(studTotalMark, new Comparator<ExamResultBean>() {
					public int compare(ExamResultBean a, ExamResultBean b){
						//return b.getAverage_mark().compareTo(a.getAverage_mark());
						if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
							return 0;
						} else if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
							return 1;
						} else {
							return -1;
						}
					}
				});
			}
			
			for(int i = 0; i < studTotalMark.size(); i++){
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(studTotalMark.get(i).getSi_id());
				exb.setFname(studTotalMark.get(i).getFname());
				exb.setMname(studTotalMark.get(i).getMname());
				exb.setGname(studTotalMark.get(i).getGname());
				exb.setStud_rank(String.valueOf(i+1));
				
				exb.setCl_id(exrslt.getCl_id());
				exb.setCd_id(exrslt.getCd_id());
				exb.setCl_name(exrslt.getCl_name());
				exb.setCd_name(exrslt.getCd_name());
				exb.setAt_id(exrslt.getAt_id());
				
				String rslt = emailStudentQuarterResult(exb);
				String studFullName = studTotalMark.get(i).getFname() + " " + studTotalMark.get(i).getMname() + " " + studTotalMark.get(i).getGname();;
				
				if(rslt.equals(SUCCESS)){
					successfulEmail.add(studFullName);
				} else if(rslt.equals(ERROR)){
					unsuccessfulEmail.add(studFullName);
				} else if(rslt.equals("NO_PARENT_EMAIL")) {
					emailDoesntExist.add(studFullName);
				}				
			}
			
			exrslt.setSuccess_counter(String.valueOf(successfulEmail.size()));
			exrslt.setFailur_counter(String.valueOf(unsuccessfulEmail.size()));
			exrslt.setDoesntexist_counter(String.valueOf(emailDoesntExist.size()));
			
			return SUCCESS;
		} else {			
			return INPUT;
		}
	}
	
	
	
	
	/*
	 * import data from excel file
	 */
	
	public String importStudrsltfromexceltemplate(){
		if(sessionMap.containsKey("userName")){
			
			if(sessionMap.get("loggedInUserType").equals("THCR")){
				return "ACCESSDENIED";
			}
			
			String acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			acyear_list = new int[] { Integer.parseInt(acyear), Integer.parseInt(acyear) - 1};
			
			sem_list = ExamResultDAO.getSemisterList(acyear);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String importAllExamTypeResultFromExcel(){
		
		if(sessionMap.containsKey("userName")){			
			
			String ac_year = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			acyear_list = new int[] { Integer.parseInt(ac_year), Integer.parseInt(ac_year) - 1};
			
			String path = exrslt.getFile_path();
			
			boolean checkFile = CheckFileExistance.fileExist(path);
			
			if(!checkFile){
				sem_list = ExamResultDAO.getSemisterList(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				addActionMessage("The file doesn't exist. Please check your path again.");
				return ERROR;
			}
			
			boolean rslt = false;
			try {
				rslt = Excel_ImportStudMarkListResultFromExcel.readFullStudMarkListExcelFile(exrslt.getAt_id(), ac_year, exrslt.getFile_path(), exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getSubcl_id(), exrslt.getRow_num(), exrslt.getSheet_num());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Boolean rslt3 = false;
			if(rslt){
				rslt3 = ExamResultDAO.saveStudExamRsltFromExcel(getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			}
			
			if(rslt3){
				return SUCCESS;
			} else {
				sem_list = ExamResultDAO.getSemisterList(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				addActionMessage("The import is not successful. Check your information given. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String importSpecificExamTypeResultFromExcel(){
		if(sessionMap.containsKey("userName")){
			
			String ac_year = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			acyear_list = new int[] { Integer.parseInt(ac_year), Integer.parseInt(ac_year) - 1};
			
			String path = exrslt.getFile_path();
			
			boolean checkFile = CheckFileExistance.fileExist(path);
			
			if(!checkFile){
				sem_list = ExamResultDAO.getSemisterList(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				addActionMessage("The file doesn't exist. Please check your path again.");
				return ERROR;
			}
			
			boolean rslt2 = false;
			try {
				rslt2 = Excel_ImportStudMarkListResultFromExcel.readSpecificColumnStudMarkListExcelFile(exrslt.getAt_id(), ac_year, exrslt.getExsub_id(), Integer.parseInt(exrslt.getColumn_num()), path, exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getSubcl_id(), exrslt.getRow_num(), exrslt.getSheet_num());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Boolean rslt3 = false;
			if(rslt2){
				rslt3 = ExamResultDAO.saveStudExamRsltFromExcel(getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			}
			
			if(rslt3){
				return SUCCESS;
			} else {
				sem_list = ExamResultDAO.getSemisterList(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				addActionMessage("The import is not successful. Check your information given. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String importMultipleColExamTypeResultFromExcel(){
		
		if(sessionMap.containsKey("userName")){
			
			String ac_year = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			acyear_list = new int[] { Integer.parseInt(ac_year), Integer.parseInt(ac_year) - 1};
			
			String path = exrslt.getFile_path();
			
			boolean checkFile = CheckFileExistance.fileExist(path);
			
			if(!checkFile){
				sem_list = ExamResultDAO.getSemisterList(ac_year);
				addActionMessage("The file doesn't exist. Please check your path again.");
				return ERROR;
			}
			
			boolean rslt2 = false;
			
			try {
				rslt2 = Excel_ImportStudMarkListResultFromExcel.readMultipleColumnStudMarkListExcelFile(exrslt.getAt_id(), ac_year, exrslt.getExsub_id(), exrslt.getColumn_num(), path, exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getSubcl_id(), exrslt.getRow_num(), exrslt.getSheet_num());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			Boolean rslt3 = false;
			
			if(rslt2){
				rslt3 = ExamResultDAO.saveStudExamRsltFromExcel(getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			}
			
			if(rslt3){
				return SUCCESS;
			} else {
				sem_list = ExamResultDAO.getSemisterList(ac_year);
				addActionMessage("The import is not successful. Check your information given. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String checkStudentNameFromExcelFile(){
		
		if(sessionMap.containsKey("userName")){
			
			String path = exrslt.getFile_path();
			
			boolean checkFile = CheckFileExistance.fileExist(path);
						
			if(!checkFile){
				sem_list = ExamResultDAO.getSemisterList(exrslt.getAcademic_year());
				addActionMessage("The file doesn't exist. Please check your path again.");
				return ERROR;
			}
			
			try {				
				unmatchStudNameList = Excel_ImportStudMarkListResultFromExcel.checkStudNameFromMarkListExcelFile(exrslt.getAt_id(), exrslt.getAcademic_year(), exrslt.getExsub_id(), path, exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getSubcl_id(), exrslt.getRow_num(), exrslt.getSheet_num());
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	/*
	 * update multiple from excel file
	 */	
	public String updateAllExamTypeResultFromExcel(){
		
		if(sessionMap.containsKey("userName")){
			
			String path = exrslt.getFile_path();
			
			boolean checkFile = CheckFileExistance.fileExist(path);
			
			String ac_year = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			if(!checkFile){
				sem_list = ExamResultDAO.getSemisterList(ac_year);
				addActionMessage("The file doesn't exist. Please check your path again.");
				return ERROR;
			}
			
			boolean rslt = false;
			try {
				rslt = Excel_ImportStudMarkListResultFromExcel.readFullStudMarkListExcelFile(exrslt.getAt_id(), ac_year, exrslt.getFile_path(), exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getSubcl_id(), exrslt.getRow_num(), exrslt.getSheet_num());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Boolean rslt3 = false;
			if(rslt){
				rslt3 = ExamResultDAO.updateStudExamRsltFromExcel(getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			}
			
			if(rslt3){
				return SUCCESS;
			} else {
				sem_list = ExamResultDAO.getSemisterList(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				addActionMessage("The update is not successful. Check your information given. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String updateSpecificExamTypeResultFromExcel(){
		if(sessionMap.containsKey("userName")){
			
			String path = exrslt.getFile_path();
			
			boolean checkFile = CheckFileExistance.fileExist(path);
			
			String ac_year = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			if(!checkFile){
				sem_list = ExamResultDAO.getSemisterList(ac_year);
				addActionMessage("The file doesn't exist. Please check your path again.");
				return ERROR;
			}
			
			boolean rslt2 = false;
			try {
				rslt2 = Excel_ImportStudMarkListResultFromExcel.readSpecificColumnStudMarkListExcelFile(exrslt.getAt_id(), ac_year, exrslt.getExsub_id(), Integer.parseInt(exrslt.getColumn_num()), path, exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getSubcl_id(), exrslt.getRow_num(), exrslt.getSheet_num());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Boolean rslt3 = false;
			if(rslt2){
				rslt3 = ExamResultDAO.updateStudExamRsltFromExcel(getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			}
			
			if(rslt3){
				return SUCCESS;
			} else {
				sem_list = ExamResultDAO.getSemisterList(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				addActionMessage("The update is not successful. Check your information given. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	public String updateMultipleColExamTypeResultFromExcel(){
		if(sessionMap.containsKey("userName")){
			
			String path = exrslt.getFile_path();
			
			boolean checkFile = CheckFileExistance.fileExist(path);
			
			String ac_year = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			
			if(!checkFile){
				sem_list = ExamResultDAO.getSemisterList(ac_year);
				addActionMessage("The file doesn't exist. Please check your path again.");
				return ERROR;
			}
			
			boolean rslt2 = false;
			try {
				rslt2 = Excel_ImportStudMarkListResultFromExcel.readMultipleColumnStudMarkListExcelFile(exrslt.getAt_id(), ac_year, exrslt.getExsub_id(), exrslt.getColumn_num(), path, exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getSubcl_id(), exrslt.getRow_num(), exrslt.getSheet_num());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Boolean rslt3 = false;
			if(rslt2){
				rslt3 = ExamResultDAO.updateStudExamRsltFromExcel(getLoggedInUser((String) sessionMap.get("loggedInUserType")));
			}
			
			if(rslt3){
				return SUCCESS;
			} else {
				sem_list = ExamResultDAO.getSemisterList(ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				addActionMessage("The update is not successful. Check your information given. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getBelowAverageStudentMarkList(){
		
		if(sessionMap.containsKey("userName")){
			
			studTotalMark = ExamResultDAO.getQuarterTotalSumBelowAverageStudRslt(exrslt);
			
			if(studTotalMark.size() > 0){
				//sort the student list per their average mark
				Collections.sort(studTotalMark, new Comparator<ExamResultBean>() {
					public int compare(ExamResultBean a, ExamResultBean b){
						//return b.getAverage_mark().compareTo(a.getAverage_mark());						
						//return b.getTotal_mark().compareTo(a.getTotal_mark());
						
						if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
							return 0;
						} else if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
							return 1;
						} else {
							return -1;
						}
					}
				});
			}
			
			return SUCCESS;
		
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getEr_id() {
		return er_id;
	}
	public void setEr_id(String er_id) {
		this.er_id = er_id;
	}
	public String getSi_id() {
		return si_id;
	}
	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}
	public String getExsub_id() {
		return exsub_id;
	}
	public void setExsub_id(String exsub_id) {
		this.exsub_id = exsub_id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public ExamResultBean getModel() {		
		return exrslt;
	}

	public List<CRoomBean> getGrdList_rslt() {
		return grdList_rslt;
	}
	
	public void setGrdList_rslt(List<CRoomBean> grdList_rslt) {
		this.grdList_rslt = grdList_rslt;
	}
	
	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public List<SubjectBean> getClSubRelList() {
		return clSubRelList;
	}

	public void setClSubRelList(List<SubjectBean> clSubRelList) {
		this.clSubRelList = clSubRelList;
	}

	public String getCl_id() {
		return cl_id;
	}

	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}

	public List<ExamBean> getClSubExamRelList() {
		return clSubExamRelList;
	}

	public void setClSubExamRelList(List<ExamBean> clSubExamRelList) {
		this.clSubExamRelList = clSubExamRelList;
	}

	public String getSubcl_id() {
		return subcl_id;
	}

	public void setSubcl_id(String subcl_id) {
		this.subcl_id = subcl_id;
	}

	public List<ExamResultBean> getClstudlist_rslt() {
		return clstudlist_rslt;
	}

	public void setClstudlist_rslt(List<ExamResultBean> clstudlist_rslt) {
		this.clstudlist_rslt = clstudlist_rslt;
	}

	public String getRwindex() {
		return rwindex;
	}

	public void setRwindex(String rwindex) {
		this.rwindex = rwindex;
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

	public String getTotal_mark() {
		return total_mark;
	}

	public void setTotal_mark(String total_mark) {
		this.total_mark = total_mark;
	}

	public String getPass_mark() {
		return pass_mark;
	}

	public void setPass_mark(String pass_mark) {
		this.pass_mark = pass_mark;
	}

	public List<ExamResultBean> getClStudAllSubResList() {
		return clStudAllSubResList;
	}

	public void setClStudAllSubResList(List<ExamResultBean> clStudAllSubResList) {
		this.clStudAllSubResList = clStudAllSubResList;
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

	public List<ExamResultBean> getStudTotalMark() {
		return studTotalMark;
	}

	public void setStudTotalMark(List<ExamResultBean> studTotalMark) {
		this.studTotalMark = studTotalMark;
	}

	public List<ExamResultBean> getSubList() {
		return subList;
	}

	public void setSubList(List<ExamResultBean> subList) {
		this.subList = subList;
	}

	public List<ExamResultBean> getStudentMarkList() {
		return studentMarkList;
	}

	public void setStudentMarkList(List<ExamResultBean> studentMarkList) {
		this.studentMarkList = studentMarkList;
	}

	public List<ExamResultBean> getExamTypeList() {
		return examTypeList;
	}

	public void setExamTypeList(List<ExamResultBean> examTypeList) {
		this.examTypeList = examTypeList;
	}

	public List<ExamResultBean> getMarkPerExamType() {
		return markPerExamType;
	}

	public void setMarkPerExamType(List<ExamResultBean> markPerExamType) {
		this.markPerExamType = markPerExamType;
	}

	public List<ExamResultBean> getStudList() {
		return studList;
	}

	public void setStudList(List<ExamResultBean> studList) {
		this.studList = studList;
	}

	public List<ExamResultBean> getStudRsltList() {
		return studRsltList;
	}

	public void setStudRsltList(List<ExamResultBean> studRsltList) {
		this.studRsltList = studRsltList;
	}

	public List<ExamResultBean> getGradeDetail() {
		return gradeDetail;
	}

	public void setGradeDetail(List<ExamResultBean> gradeDetail) {
		this.gradeDetail = gradeDetail;
	}

	public List<ExamResultBean> getSortedStudTotalMark() {
		return sortedStudTotalMark;
	}

	public void setSortedStudTotalMark(List<ExamResultBean> sortedStudTotalMark) {
		this.sortedStudTotalMark = sortedStudTotalMark;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap(sessionMap);
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

	public int[] getAcyear_list() {
		return acyear_list;
	}

	public void setAcyear_list(int[] acyear_list) {
		this.acyear_list = acyear_list;
	}

	public String getDecColStatus() {
		return decColStatus;
	}

	public void setDecColStatus(String decColStatus) {
		this.decColStatus = decColStatus;
	}

	public List<ExamResultBean> getSem_list() {
		return sem_list;
	}

	public void setSem_list(List<ExamResultBean> sem_list) {
		this.sem_list = sem_list;
	}

	public List<ExamResultBean> getStudFullYearMark() {
		return studFullYearMark;
	}

	public void setStudFullYearMark(List<ExamResultBean> studFullYearMark) {
		this.studFullYearMark = studFullYearMark;
	}

	public List<ExamResultBean> getCertificateRsltView() {
		return certificateRsltView;
	}

	public void setCertificateRsltView(List<ExamResultBean> certificateRsltView) {
		this.certificateRsltView = certificateRsltView;
	}

	public List<ExamResultBean> getQuarterRsltView() {
		return quarterRsltView;
	}

	public void setQuarterRsltView(List<ExamResultBean> quarterRsltView) {
		this.quarterRsltView = quarterRsltView;
	}

	public List<BevalBean> getQuarterEvaluation() {
		return quarterEvaluation;
	}

	public void setQuarterEvaluation(List<BevalBean> quarterEvaluation) {
		this.quarterEvaluation = quarterEvaluation;
	}

	public List<ExamResultBean> getGetTeacherComment() {
		return getTeacherComment;
	}

	public void setGetTeacherComment(List<ExamResultBean> getTeacherComment) {
		this.getTeacherComment = getTeacherComment;
	}

	public List<ExamResultBean> getQuarterTeacherComment() {
		return quarterTeacherComment;
	}

	public void setQuarterTeacherComment(List<ExamResultBean> quarterTeacherComment) {
		this.quarterTeacherComment = quarterTeacherComment;
	}

	public List<ExamResultBean> getFirstQuarterRslt() {
		return firstQuarterRslt;
	}

	public void setFirstQuarterRslt(List<ExamResultBean> firstQuarterRslt) {
		this.firstQuarterRslt = firstQuarterRslt;
	}

	public List<ExamResultBean> getSecondQuarterRslt() {
		return secondQuarterRslt;
	}

	public void setSecondQuarterRslt(List<ExamResultBean> secondQuarterRslt) {
		this.secondQuarterRslt = secondQuarterRslt;
	}

	public List<ExamResultBean> getThirdQuarterRslt() {
		return thirdQuarterRslt;
	}

	public void setThirdQuarterRslt(List<ExamResultBean> thirdQuarterRslt) {
		this.thirdQuarterRslt = thirdQuarterRslt;
	}

	public List<ExamResultBean> getFourthQuarterRslt() {
		return fourthQuarterRslt;
	}

	public void setFourthQuarterRslt(List<ExamResultBean> fourthQuarterRslt) {
		this.fourthQuarterRslt = fourthQuarterRslt;
	}

	public List<ExamResultBean> getFullYearAvgRslt() {
		return fullYearAvgRslt;
	}

	public void setFullYearAvgRslt(List<ExamResultBean> fullYearAvgRslt) {
		this.fullYearAvgRslt = fullYearAvgRslt;
	}

	public List<BevalHolisticBean> getFirstQuarterHolisticEvaluationResult() {
		return firstQuarterHolisticEvaluationResult;
	}

	public void setFirstQuarterHolisticEvaluationResult(List<BevalHolisticBean> firstQuarterHolisticEvaluationResult) {
		this.firstQuarterHolisticEvaluationResult = firstQuarterHolisticEvaluationResult;
	}

	public List<BevalHolisticBean> getSecondQuarterHolisticEvaluationResult() {
		return secondQuarterHolisticEvaluationResult;
	}

	public void setSecondQuarterHolisticEvaluationResult(List<BevalHolisticBean> secondQuarterHolisticEvaluationResult) {
		this.secondQuarterHolisticEvaluationResult = secondQuarterHolisticEvaluationResult;
	}
	public List<BevalHolisticBean> getThirdQuarterHolisticEvaluationResult() {
		return thirdQuarterHolisticEvaluationResult;
	}
	public void setThirdQuarterHolisticEvaluationResult(List<BevalHolisticBean> thirdQuarterHolisticEvaluationResult) {
		this.thirdQuarterHolisticEvaluationResult = thirdQuarterHolisticEvaluationResult;
	}
	public List<ExamResultBean> getFullYearStudRank() {
		return fullYearStudRank;
	}
	public void setFullYearStudRank(List<ExamResultBean> fullYearStudRank) {
		this.fullYearStudRank = fullYearStudRank;
	}
	public List<String> getSuccessfulEmail() {
		return successfulEmail;
	}
	public void setSuccessfulEmail(List<String> successfulEmail) {
		this.successfulEmail = successfulEmail;
	}
	public List<String> getUnsuccessfulEmail() {
		return unsuccessfulEmail;
	}
	public void setUnsuccessfulEmail(List<String> unsuccessfulEmail) {
		this.unsuccessfulEmail = unsuccessfulEmail;
	}
	public List<String> getEmailDoesntExist() {
		return emailDoesntExist;
	}
	public void setEmailDoesntExist(List<String> emailDoesntExist) {
		this.emailDoesntExist = emailDoesntExist;
	}
	public List<ExamResultBean> getGetAbsentDayList() {
		return getAbsentDayList;
	}
	public void setGetAbsentDayList(List<ExamResultBean> getAbsentDayList) {
		this.getAbsentDayList = getAbsentDayList;
	}
	public List<CertDefaultComBean> getGetDefaultComment() {
		return getDefaultComment;
	}
	public void setGetDefaultComment(List<CertDefaultComBean> getDefaultComment) {
		this.getDefaultComment = getDefaultComment;
	}
	public List<StudentBean> getUnmatchStudNameList() {
		return unmatchStudNameList;
	}
	public void setUnmatchStudNameList(List<StudentBean> unmatchStudNameList) {
		this.unmatchStudNameList = unmatchStudNameList;
	}
	public List<ExamResultBean> getFirstQuarterTeacherComment() {
		return firstQuarterTeacherComment;
	}
	public void setFirstQuarterTeacherComment(List<ExamResultBean> firstQuarterTeacherComment) {
		this.firstQuarterTeacherComment = firstQuarterTeacherComment;
	}
	public List<ExamResultBean> getSecondQuarterTeacherComment() {
		return secondQuarterTeacherComment;
	}
	public void setSecondQuarterTeacherComment(List<ExamResultBean> secondQuarterTeacherComment) {
		this.secondQuarterTeacherComment = secondQuarterTeacherComment;
	}
	public List<ExamResultBean> getThirdQuarterTeacherComment() {
		return thirdQuarterTeacherComment;
	}
	public void setThirdQuarterTeacherComment(List<ExamResultBean> thirdQuarterTeacherComment) {
		this.thirdQuarterTeacherComment = thirdQuarterTeacherComment;
	}
	public List<ExamResultBean> getFourthQuarterTeacherComment() {
		return fourthQuarterTeacherComment;
	}
	public void setFourthQuarterTeacherComment(List<ExamResultBean> fourthQuarterTeacherComment) {
		this.fourthQuarterTeacherComment = fourthQuarterTeacherComment;
	}
	public List<CertDefaultComBean> getFirstQuarterDefaultComment() {
		return firstQuarterDefaultComment;
	}
	public void setFirstQuarterDefaultComment(List<CertDefaultComBean> firstQuarterDefaultComment) {
		this.firstQuarterDefaultComment = firstQuarterDefaultComment;
	}
	public List<CertDefaultComBean> getSecondQuarterDefaultComment() {
		return secondQuarterDefaultComment;
	}
	public void setSecondQuarterDefaultComment(List<CertDefaultComBean> secondQuarterDefaultComment) {
		this.secondQuarterDefaultComment = secondQuarterDefaultComment;
	}
	public List<CertDefaultComBean> getThirdQuarterDefaultComment() {
		return thirdQuarterDefaultComment;
	}
	public void setThirdQuarterDefaultComment(List<CertDefaultComBean> thirdQuarterDefaultComment) {
		this.thirdQuarterDefaultComment = thirdQuarterDefaultComment;
	}
	public List<CertDefaultComBean> getFourthQuarterDefaultComment() {
		return fourthQuarterDefaultComment;
	}
	public void setFourthQuarterDefaultComment(List<CertDefaultComBean> fourthQuarterDefaultComment) {
		this.fourthQuarterDefaultComment = fourthQuarterDefaultComment;
	}
	public List<StudentBean> getStud_personal_info() {
		return stud_personal_info;
	}
	public void setStud_personal_info(List<StudentBean> stud_personal_info) {
		this.stud_personal_info = stud_personal_info;
	}
	public List<BevalBean> getFirstQuarterEvaluation() {
		return firstQuarterEvaluation;
	}
	public void setFirstQuarterEvaluation(List<BevalBean> firstQuarterEvaluation) {
		this.firstQuarterEvaluation = firstQuarterEvaluation;
	}
	public List<BevalBean> getSecondQuarterEvaluation() {
		return secondQuarterEvaluation;
	}
	public void setSecondQuarterEvaluation(List<BevalBean> secondQuarterEvaluation) {
		this.secondQuarterEvaluation = secondQuarterEvaluation;
	}
	public List<BevalBean> getThirdQuarterEvaluation() {
		return thirdQuarterEvaluation;
	}
	public void setThirdQuarterEvaluation(List<BevalBean> thirdQuarterEvaluation) {
		this.thirdQuarterEvaluation = thirdQuarterEvaluation;
	}
	public List<BevalBean> getFourthQuarterEvaluation() {
		return fourthQuarterEvaluation;
	}
	public void setFourthQuarterEvaluation(List<BevalBean> fourthQuarterEvaluation) {
		this.fourthQuarterEvaluation = fourthQuarterEvaluation;
	}


	public List<BevalBean> getEvalList() {
		return evalList;
	}


	public void setEvalList(List<BevalBean> evalList) {
		this.evalList = evalList;
	}


	public List<ExamResultBean> getRsltList() {
		return rsltList;
	}


	public void setRsltList(List<ExamResultBean> rsltList) {
		this.rsltList = rsltList;
	}


	public int[] getAverage_list() {
		return average_list;
	}


	public void setAverage_list(int[] average_list) {
		this.average_list = average_list;
	}

	
}
