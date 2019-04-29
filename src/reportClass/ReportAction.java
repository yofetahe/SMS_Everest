package reportClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.AgeCalculator;
import util.DateConvertor;
import util.EmailForm;
import util.Excel_StudMarkPlanAndImpReport;
import util.Excel_StudentMarkListReport;
import util.PDF_MarkListGenerator;
import util.PDF_StudAttendanceFormat;
import util.OpenFile;
import util.PDF_FullYearAvgMarkList;
import util.PDF_StudMarkListReport;
import util.PDF_StudMarkPlanAndImp;
import util.ReturnCurrentEthiopianYear;
import util.ReturnMonthNameInAmharic;
import util.RoleValidator;
import util.RosterPDFGenerator;
import util.SysConstant;
import util.TodayDate_YYYYMMDD;
import adminClass.AdminBean;
import adminClass.AdminDAO;
import adminClass.exam_schedule.ExamScheduleBean;
import adminClass.exam_schedule.ExamScheduleDAO;
import cRoomClass.CRoomBean;
import cRoomClass.CRoomDAO;
import cRoomClass.class_detail.CRDetailBean;
import cRoomClass.class_detail.CRDetailDAO;
import examClass.exam_result.ExamResultBean;
import examClass.exam_result.ExamResultDAO;
import examClass.exam_type.ExamBean;
import examClass.exam_type.ExamDAO;
import studentClass.StudentBean;
import studentClass.StudentClassBean;
import studentClass.StudentDAO;
import studentClass.student_registration.StudentRegDAO;
import subjectClass.SubjectBean;
import subjectClass.SubjectDAO;
//import sun.util.calendar.BaseCalendar.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReportAction extends ActionSupport implements ModelDriven<ReportBean>, SessionAware {
	
	private static final long serialVersionUID = 1L;
	
	ReportBean rb = new ReportBean();
	
	private String menutype = "report";
	
	private List<AdminBean> usrRoleList;
	private Map<String, Object> sessionMap;
	private List<ExamBean> examTypeList;
	private List<ExamResultBean> sem_list;
	private List<StudentClassBean> grade_rslt;
	private List<ExamScheduleBean> exam_schedule_report = new ArrayList<ExamScheduleBean>();
	private List<ExamScheduleBean> organize_exam_schedule_report = new ArrayList<ExamScheduleBean>();
	
	private List<ReportBean> quarter_mark_list;
	private List<ReportBean> quarter_stud_roster;
	private List<ReportBean> subject_list;
	private List<ReportBean> top_five_stud_per_class;
	private List<ReportBean> top_twenty_stud;
	private List<ReportBean> stud_mark_plan_imp_list;
	private List<ReportBean> stud_mark_list_report;
	private List<ReportBean> payment_collection;
	private List<CRoomBean> exam_type_total_mark_list;
	
	private List<StudentBean> stud_rslt;
	private List<CRoomBean> grdList_rslt;
	private List<ReportBean> unpaid_stud_list;
	private List<ExamBean> examtype_list;
	private List<ExamResultBean> summerized_studmark_number;
	
	private List<ExamResultBean> gradenine_trans_rslt;
	private List<ExamResultBean> gradeten_trans_rslt;
	
	private int[] acyear_list;
	private String acyear;	
	
	private List<List<ExamResultBean>> fullYearStudRoster = new ArrayList<List<ExamResultBean>>();
	private List<ExamResultBean> studMarkList = new ArrayList<ExamResultBean>();
	private List<ExamResultBean> sortedStudMarkList = new ArrayList<ExamResultBean>();
	private List<StudentBean> studList;
	private List<ExamResultBean> subList;
	
	private List<SubjectBean> subjectList;
	
	public String getReportTemp(){
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			
			String page = "Report";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				return "ACCESSDENIED";
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String getExamSchedule(){
		if (sessionMap.containsKey("userName")) {
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			examTypeList = ExamDAO.getActiveExamTypeList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getExamProgramReport(){
		if (sessionMap.containsKey("userName")) {
			
			exam_schedule_report = ExamScheduleDAO.ReportExamSchedulePerQuarterExamType(rb.getAt_id(), rb.getEt_id());
						
			int size = exam_schedule_report.size();
			for(int i = 0; i < size; i++){								
				if(i == 0){
					organize_exam_schedule_report.add(exam_schedule_report.get(i));
				} else {
					
					int n_size = organize_exam_schedule_report.size();
					int counter = 0;
					String classList = "";
					for(int j = 0; j < n_size; j++){
						
						if(exam_schedule_report.get(i).getEs_ethio_date().equals(organize_exam_schedule_report.get(j).getEs_ethio_date()) 
								&& exam_schedule_report.get(i).getSub_name().equals(organize_exam_schedule_report.get(j).getSub_name())
								&& exam_schedule_report.get(i).getTime_from().equals(organize_exam_schedule_report.get(j).getTime_from())
								&& exam_schedule_report.get(i).getTime_to().equals(organize_exam_schedule_report.get(j).getTime_to())
								){
							
							classList = organize_exam_schedule_report.get(j).getCl_name() + ", " + exam_schedule_report.get(i).getCl_name();
							organize_exam_schedule_report.get(j).setCl_name(classList);
							//System.out.println(exam_schedule_report.get(i).getEs_ethio_date() + " - " + exam_schedule_report.get(i).getSub_name() + " - " + organize_exam_schedule_report.get(j).getCl_name());
														
							counter++;
						} 
					}
					if(counter == 0){						
						organize_exam_schedule_report.add(exam_schedule_report.get(i));
					}
				}
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String quarterMarkList(){
		if(sessionMap.containsKey("userName")){
			
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear),
					Integer.parseInt(acyear)-1, Integer.parseInt(acyear)-2};
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			grade_rslt = StudentDAO.getClassList();	
			
			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());			
			rb.setStud_bean(sb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getQuarterMarkList(){
		
		if(sessionMap.containsKey("userName")){
			
			ExamResultBean crm = new ExamResultBean();
			crm.setSubcl_id(rb.getSub_bean().getSub_id());
			
			examtype_list = ExamDAO.getActivelyRelatedExamTypeListWithClassSubjectForMarkList(crm);
			
			ExamBean ex1 = new ExamBean();										
			ex1.setEt_name("Total");					
			examtype_list.add(examtype_list.size()-1, ex1);
			
			ExamBean ex3 = new ExamBean();
			ex3.setEt_name("Total");					
			examtype_list.add(ex3);	
			
			quarter_mark_list = ReportDAO.getClassQuarterMarkList_New(rb);
			Collections.sort(quarter_mark_list, new Comparator<ReportBean>() {
				public int compare(ReportBean a, ReportBean b){
					String aFullName = a.getStud_bean().getFname() + a.getStud_bean().getMname() + a.getStud_bean().getGname();
					String bFullName = b.getStud_bean().getFname() + b.getStud_bean().getMname() + b.getStud_bean().getGname();
					return aFullName.compareTo(bFullName);
				}
			});
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getPDFQuarterMarkList(){
		
		if(sessionMap.containsKey("userName")){
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			ExamResultBean exrb = new ExamResultBean();
			exrb.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			exrb.setAt_name(ExamResultDAO.getQuarterName(rb.getAt_id()));
			exrb.setCl_name(CRoomDAO.getClassName(rb.getClass_bean().getCl_id()));
			exrb.setCd_name(CRDetailDAO.getClassDetailName(rb.getCdetail_bean().getCd_id()));
			exrb.setSub_name(SubjectDAO.getSubjectName(rb.getSub_bean().getSub_id(), rb.getClass_bean().getCl_id()));
			exrb.setSubject_teacher(SubjectDAO.getAssignedTeacherForSubject(rb.getClass_bean().getCl_id(), rb.getCdetail_bean().getCd_id(), rb.getSub_bean().getSub_id()));
			rb.setEx_rslt_bean(exrb);
			
			String markListFile = SysConstant.CERT_PDF_PATH + " " + exrb.getAt_name() + "_" + exrb.getCl_name() + "_" + exrb.getCd_name()+ "_" + exrb.getSub_name() + "_Student_Mark_List_" + exrb.getCur_date() + "_" + timeStamp + ".pdf";

			ExamResultBean crm = new ExamResultBean();
			crm.setSubcl_id(rb.getSub_bean().getSub_id());
			examtype_list = ExamDAO.getActivelyRelatedExamTypeListWithClassSubject(crm);
			
			ExamBean ex1 = new ExamBean();										
			ex1.setEt_name("Total");					
			examtype_list.add(examtype_list.size()-1, ex1);
			
			ExamBean ex3 = new ExamBean();
			ex3.setEt_name("Total");					
			examtype_list.add(ex3);	
			
			quarter_mark_list = ReportDAO.getClassQuarterMarkList_New(rb);
			Collections.sort(quarter_mark_list, new Comparator<ReportBean>() {
				public int compare(ReportBean a, ReportBean b){
					String aFullName = a.getStud_bean().getFname() + a.getStud_bean().getMname() + a.getStud_bean().getGname();
					String bFullName = b.getStud_bean().getFname() + b.getStud_bean().getMname() + b.getStud_bean().getGname();
					return aFullName.compareTo(bFullName);
				}
			});
			
			exam_type_total_mark_list = CRoomDAO.getExamTypeTotalMarkList(rb.getSub_bean().getSub_id());
			
			int subject_total_per = 0, all_total_per = 0;
			
			for(int i = 0; i < exam_type_total_mark_list.size(); i++){
				
				if(i < exam_type_total_mark_list.size()-1){
				
					subject_total_per = subject_total_per + Integer.parseInt(exam_type_total_mark_list.get(i).getTotal_mark());
				}
				
				all_total_per = all_total_per + Integer.parseInt(exam_type_total_mark_list.get(i).getTotal_mark());
			}
			
			CRoomBean cr1 = new CRoomBean();
			cr1.setTotal_mark(String.valueOf(subject_total_per));
			exam_type_total_mark_list.add(exam_type_total_mark_list.size()-1, cr1);
			
			CRoomBean cr2 = new CRoomBean();
			cr2.setTotal_mark(String.valueOf(all_total_per));
			exam_type_total_mark_list.add(cr2);
			
			PDF_MarkListGenerator.generateMarkListPDF(rb, quarter_mark_list, markListFile, exam_type_total_mark_list, examtype_list);
			
			OpenFile.openExistingPDFFile(markListFile);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentRoster(){
		if(sessionMap.containsKey("userName")){
			
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear),
					Integer.parseInt(acyear)-1, Integer.parseInt(acyear)-2};
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			grade_rslt = StudentDAO.getClassList();			

			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());			
			rb.setStud_bean(sb);			
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentRosterList(){
		
		if(sessionMap.containsKey("userName")){
			
			subject_list = ReportDAO.getSubjectListPerClassExamSubRel(rb);
			
			quarter_stud_roster = ReportDAO.getQuarterStudentRoster(rb, subject_list);
			
			Collections.sort(quarter_stud_roster, new Comparator<ReportBean>() {
				public int compare(ReportBean a, ReportBean b){
					
					if(Double.parseDouble(b.getEx_rslt_bean().getTotal_mark()) == Double.parseDouble(a.getEx_rslt_bean().getTotal_mark())){
						return 0;
					} else if(Double.parseDouble(b.getEx_rslt_bean().getTotal_mark()) > Double.parseDouble(a.getEx_rslt_bean().getTotal_mark())){
						return 1;
					} else {
						return -1;
					}
				}
			});
			
			///>>> *** adding student rank based on the total mark *** <<<///
			double pervious_total = 0.0;
			int pervious_rank = 0, same_rank_counter = 0;
			
			for(int i = 0; i < quarter_stud_roster.size(); i++){
				
				if(pervious_total == 0.0){
					
					pervious_total = Double.parseDouble(quarter_stud_roster.get(i).getEx_rslt_bean().getTotal_mark());
					
					pervious_rank = 1;
					
					quarter_stud_roster.get(i).getEx_rslt_bean().setStud_rank(String.valueOf(pervious_rank));
					
				} else if(pervious_total != 0.0 && Double.parseDouble(quarter_stud_roster.get(i).getEx_rslt_bean().getTotal_mark()) == pervious_total){
					
					pervious_total = Double.parseDouble(quarter_stud_roster.get(i).getEx_rslt_bean().getTotal_mark());
					
					quarter_stud_roster.get(i).getEx_rslt_bean().setStud_rank(String.valueOf(pervious_rank));
					
					same_rank_counter++;
					
				} else {
					
					pervious_total = Double.parseDouble(quarter_stud_roster.get(i).getEx_rslt_bean().getTotal_mark());
					
					pervious_rank = pervious_rank + same_rank_counter + 1;
					
					quarter_stud_roster.get(i).getEx_rslt_bean().setStud_rank(String.valueOf(pervious_rank));
					
					same_rank_counter = 0;
					
				}
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentPDFRosterList(){
		
		if(sessionMap.containsKey("userName")){
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			ExamResultBean exrb = new ExamResultBean();
			exrb.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			exrb.setAt_name(ExamResultDAO.getQuarterName(rb.getAt_id()));
			exrb.setCl_name(CRoomDAO.getClassName(rb.getClass_bean().getCl_id()));
			exrb.setCd_name(CRDetailDAO.getClassDetailName(rb.getCdetail_bean().getCd_id()));
			rb.setEx_rslt_bean(exrb);
			
			StudentBean stb = new StudentBean();
			stb.setFullName(AdminDAO.getAssignedHomeReoomTeacherName(rb.getClass_bean().getCl_id(), rb.getCdetail_bean().getCd_id()));
			stb.setAc_year(rb.getStud_bean().getAc_year());
			rb.setStud_bean(stb);
			
			String rosterFile = SysConstant.CERT_PDF_PATH + " " + exrb.getAt_name() + "_" + exrb.getCl_name() + "_" + exrb.getCd_name()+ "_Student_Roster_" + exrb.getCur_date() + "_" + timeStamp + ".pdf";
						
			//subject_list = ReportDAO.getSubjectListPerClass(rb);
			subject_list = ReportDAO.getSubjectListPerClassExamSubRel(rb);
			
			quarter_stud_roster = ReportDAO.getQuarterStudentRoster(rb, subject_list);
			
			Collections.sort(quarter_stud_roster, new Comparator<ReportBean>() {
				public int compare(ReportBean a, ReportBean b){
					
					if(Double.parseDouble(b.getEx_rslt_bean().getTotal_mark()) == Double.parseDouble(a.getEx_rslt_bean().getTotal_mark())){
						return 0;
					} else if(Double.parseDouble(b.getEx_rslt_bean().getTotal_mark()) > Double.parseDouble(a.getEx_rslt_bean().getTotal_mark())){
						return 1;
					} else {
						return -1;
					}
				}
			});
			
			RosterPDFGenerator.generatRosterPDF(exrb, rb, rosterFile, subject_list, quarter_stud_roster);
			
			OpenFile.openExistingPDFFile(rosterFile);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentRosterFullYear(){
		if(sessionMap.containsKey("userName")){
			
			grdList_rslt = CRoomDAO.getActiveClass();
			
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear), Integer.parseInt(acyear)-1, Integer.parseInt(acyear)-2 };

			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());			
			rb.setStud_bean(sb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getFullYearStudentRosterList(){
		
		if(sessionMap.containsKey("userName")){
			
			ExamResultBean exrslt = new ExamResultBean();
			exrslt.setCl_id(rb.getClass_bean().getCl_id());
			exrslt.setCd_id(rb.getCdetail_bean().getCd_id());
			exrslt.setAcademic_year(rb.getStud_bean().getAc_year());			
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
						
			
			///*** getting subject list per exam_sub_rel ***///
			//subList = ExamResultDAO.getSubjectList(exrslt);	
			ReportBean rb = new ReportBean();
			
			CRoomBean cr = new CRoomBean();
			cr.setCl_id(exrslt.getCl_id());
			rb.setClass_bean(cr);
			
			CRDetailBean crd = new CRDetailBean();
			crd.setCd_id(exrslt.getCd_id());
			rb.setCdetail_bean(crd);
			
			rb.setAt_id("1");
			
			StudentBean stud_bean = new StudentBean();
			stud_bean.setAc_year(exrslt.getAcademic_year());
			rb.setStud_bean(stud_bean);
							
			subjectList = ReportDAO.getSubjectListPerClassExamSubRelWithSubClId(rb);
			
			
			studList = StudentDAO.getListPerGradeDetail(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			
			
			
			///*** quarter rank of student ***///
			
			List<ExamResultBean> studentTotalMarkPerQuarter = ExamResultDAO.getStudentTotalMarkPerQuarterForRanking(exrslt);
			
			///*** ----------------------- ***///
			
			
			
			////**** use to get annual average rank of student ****////
			
			List<ExamResultBean> getOrderedStudentAnnualTotalMark = ExamResultDAO.getOrderedDescStudentAnnualTotalResult(exrslt);
			
			List<ExamResultBean> getStudentActiveSemisterList = ExamResultDAO.getStudentActiveSemisterList(exrslt);
			
			for(int i = 0; i < getOrderedStudentAnnualTotalMark.size(); i++){
				
				int active_sem = 0;
				
				for(int j = 0; j < getStudentActiveSemisterList.size(); j++){
					
					if(getOrderedStudentAnnualTotalMark.get(i).getSi_id().equals(getStudentActiveSemisterList.get(j).getSi_id())){
						
						active_sem++;
					}
				}
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSi_id(getOrderedStudentAnnualTotalMark.get(i).getSi_id());
				ex.setTotal_mark(String.valueOf(Double.parseDouble(getOrderedStudentAnnualTotalMark.get(i).getTotal_mark())/active_sem));
				
				getOrderedStudentAnnualTotalMark.set(i, ex);				
			}
			
			if(getOrderedStudentAnnualTotalMark.size() > 0){
				///// sorting student based on annual year avg
				Collections.sort(getOrderedStudentAnnualTotalMark, new Comparator<ExamResultBean>() {
					public int compare(ExamResultBean a, ExamResultBean b){
						int rslt = 0;
						
						if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
							rslt = 1;
						}
						if(Double.parseDouble(b.getTotal_mark()) < Double.parseDouble(a.getTotal_mark())){
							rslt = -1;
						}
						if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
							rslt = 0;
						}
						
						return rslt;
					}
				});
			}
			
			///--- adding student rank based on the total mark ---///
			double pervious_total = 0.0;
			int pervious_rank = 0, same_rank_counter = 0;
			
			for(int i = 0; i < getOrderedStudentAnnualTotalMark.size(); i++){
				
				if(pervious_total == 0.0){
					
					pervious_total = Double.parseDouble(getOrderedStudentAnnualTotalMark.get(i).getTotal_mark());
					
					pervious_rank = 1;
					
					getOrderedStudentAnnualTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank));
					
				} else if(pervious_total != 0.0 && Double.parseDouble(getOrderedStudentAnnualTotalMark.get(i).getTotal_mark()) == pervious_total){
					
					pervious_total = Double.parseDouble(getOrderedStudentAnnualTotalMark.get(i).getTotal_mark());
					
					getOrderedStudentAnnualTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank));
					
					same_rank_counter++;
					
				} else {
					
					pervious_total = Double.parseDouble(getOrderedStudentAnnualTotalMark.get(i).getTotal_mark());
					
					pervious_rank = pervious_rank + same_rank_counter + 1;
					
					getOrderedStudentAnnualTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank));
					
					same_rank_counter = 0;
				}
			}
			////**** use to get annual average rank of student ****////
			
			int rowNum = 0;
						
			studMarkList = ExamResultDAO.getSubjectTotalMarkListWithAllStudName(exrslt, subjectList);
						
			fullYearStudRoster.add(studMarkList);
						
			ExamResultBean exrb = new ExamResultBean();
			exrb.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			exrb.setCl_name(CRoomDAO.getClassName(rb.getClass_bean().getCl_id()));
			exrb.setCd_name(CRDetailDAO.getClassDetailName(rb.getCdetail_bean().getCd_id()));
			exrb.setHomeroom_teacher_name(ExamResultDAO.getHomeroomTeacherName(exrslt));
			exrb.setAcademic_year(exrslt.getAcademic_year());
			rb.setEx_rslt_bean(exrb);
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			String fullYearAvgFile = SysConstant.CERT_PDF_PATH + " " + exrb.getCl_name() + "_" + exrb.getCd_name()+ "_Student_Average_Mark_List_" + exrb.getCur_date() + "_" + timeStamp + ".pdf";
						
			PDF_FullYearAvgMarkList.generateAvgMarkListPDF(exrb, studMarkList, subjectList, fullYearAvgFile, rowNum, getOrderedStudentAnnualTotalMark, studentTotalMarkPerQuarter);
			
			OpenFile.openExistingPDFFile(fullYearAvgFile);

			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String topNsStudent(){
		if(sessionMap.containsKey("userName")){
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			

			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());			
			rb.setStud_bean(sb);
			
			grdList_rslt = CRoomDAO.getActiveClass();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	} 
	
	public String getTopFiveStudentPerClass(){
		
		if(sessionMap.containsKey("userName")){
			
			grade_rslt = StudentDAO.getClassList();	
			//top_five_stud_per_class = ReportDAO.getTopFiveStudentPerClass(rb, (String)sessionMap.get("userName"));
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getTopNsStudent(){
		
		if(sessionMap.containsKey("userName")){
			
			grade_rslt = StudentDAO.getClassList();	

			if(rb.getOverAllTopRanking().equals("Yes")){
				
				top_five_stud_per_class = ReportDAO.getOverAllTopRankedStudent(rb);
				return "OVERALL";
				
			} else {
				
				top_five_stud_per_class = ReportDAO.getTopNsStudent(rb, (String)sessionMap.get("userName"));
				return SUCCESS;
			}
			
		} else {
			return INPUT;
		}
	}
	
	public String twentyRankStudentTemp(){
		if(sessionMap.containsKey("userName")){
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			
			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());			
			rb.setStud_bean(sb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getTwentyRankStudent(){
		if(sessionMap.containsKey("userName")){
			
			top_twenty_stud = ReportDAO.getTopTwentyRankedStudent(rb);
			Collections.sort(top_twenty_stud, new Comparator<ReportBean>() {
				public int compare(ReportBean a, ReportBean b){
					
					if(Double.parseDouble(b.getEx_rslt_bean().getAverage_mark()) == Double.parseDouble(a.getEx_rslt_bean().getAverage_mark())){
						return 0;
					} else if(Double.parseDouble(b.getEx_rslt_bean().getAverage_mark()) > Double.parseDouble(a.getEx_rslt_bean().getAverage_mark())){
						return 1;
					} else {
						return -1;
					}
				}
			});
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studMarkPlanAndImp(){
		if(sessionMap.containsKey("userName")){
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			//grade_rslt = StudentDAO.getClassList();
			

			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());			
			rb.setStud_bean(sb);
			
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String ClassCategoryList(){
		if(sessionMap.containsKey("userName")){
			
			ReportDAO.addSelectedSubject(rb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String selectSubject(){
		if(sessionMap.containsKey("userName")){
			
			ReportDAO.addSelectedSubject(rb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String disselectSubject(){
		if(sessionMap.containsKey("userName")){
			
			ReportDAO.removeSelectedSubject(rb);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudMarkPlanAndImpList(){
		if(sessionMap.containsKey("userName")){
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			ExamResultBean exrb = new ExamResultBean();
			exrb.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			exrb.setAt_name(ExamResultDAO.getQuarterName(rb.getAt_id()));
			exrb.setCl_name(CRoomDAO.getClassName(rb.getClass_bean().getCl_id()));
			rb.setEx_rslt_bean(exrb);
			
			if(rb.getClass_bean().getCl_id().equals("1")){
				rb.setCl_category("1-4(AA)");
			} else if(rb.getClass_bean().getCl_id().equals("2")){
				rb.setCl_category("5-6");
			} else if(rb.getClass_bean().getCl_id().equals("3")){
				rb.setCl_category("7-8");
			} else if(rb.getClass_bean().getCl_id().equals("4")){
				rb.setCl_category("9-10");
			}
			
			String fileNamePath = SysConstant.CERT_PDF_PATH + "StudentMarkPlanAndImp_" + rb.getCl_category() + "_" + exrb.getAt_name() + "_" + exrb.getCur_date() + "_" + timeStamp + ".pdf";
			
			stud_mark_plan_imp_list = ReportDAO.getStudNumberPerMarkRang(rb);
			
			PDF_StudMarkPlanAndImp.getStudMarkPlanAndImpPDF(stud_mark_plan_imp_list, fileNamePath, rb);
			
			OpenFile.openExistingPDFFile(fileNamePath);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getExcelStudMarkPlanAndImpList(){
		if(sessionMap.containsKey("userName")){
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			ExamResultBean exrb = new ExamResultBean();
			exrb.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			exrb.setAt_name(ExamResultDAO.getQuarterName(rb.getAt_id()));
			exrb.setCl_name(CRoomDAO.getClassName(rb.getClass_bean().getCl_id()));
			rb.setEx_rslt_bean(exrb);
			
			if(rb.getClass_bean().getCl_id().equals("1")){
				rb.setCl_category("1-4(AA)");
			} else if(rb.getClass_bean().getCl_id().equals("2")){
				rb.setCl_category("5-6");
			} else if(rb.getClass_bean().getCl_id().equals("3")){
				rb.setCl_category("7-8");
			} else if(rb.getClass_bean().getCl_id().equals("4")){
				rb.setCl_category("9-10");
			}
			
			String fileNamePath = "StudentMarkPlanAndImp_" + rb.getCl_category() + "_" + exrb.getAt_name() + "_" + exrb.getCur_date() + "_" + timeStamp;
			
			stud_mark_plan_imp_list = ReportDAO.getStudNumberPerMarkRang(rb);
			
			Excel_StudMarkPlanAndImpReport.generateExcelFile(fileNamePath, stud_mark_plan_imp_list);
			
			OpenFile.openExistingExcelFile(SysConstant.CERT_PDF_PATH, fileNamePath);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
		
	public String studMarkListTemp(){
		if(sessionMap.containsKey("userName")){
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			

			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());			
			rb.setStud_bean(sb);
			
						
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentMarkListPDFReport(){
		if(sessionMap.containsKey("userName")){
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			ExamResultBean exrb = new ExamResultBean();
			exrb.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			exrb.setAt_name(ExamResultDAO.getQuarterName(rb.getAt_id()));
			exrb.setCl_name(CRoomDAO.getClassName(rb.getClass_bean().getCl_id()));
			rb.setEx_rslt_bean(exrb);
			
			if(rb.getClass_bean().getCl_id().equals("1")){
				rb.setCl_category("1-4(AA)");
			} else if(rb.getClass_bean().getCl_id().equals("2")){
				rb.setCl_category("5-6");
			} else if(rb.getClass_bean().getCl_id().equals("3")){
				rb.setCl_category("7-8");
			} else if(rb.getClass_bean().getCl_id().equals("4")){
				rb.setCl_category("9-10");
			}
			
			String fileNamePath = SysConstant.CERT_PDF_PATH + "StudentMarkListReport_" + rb.getCl_category() + "_" + exrb.getAt_name() + "_" + exrb.getCur_date() + "_" + timeStamp + ".pdf";
			
			stud_mark_list_report = ReportDAO.getStudMarkListNumberPerMarkRang(rb);
			
			PDF_StudMarkListReport.getStudMarkListReportPDF(stud_mark_list_report, fileNamePath, rb);
			
			OpenFile.openExistingPDFFile(fileNamePath);
						
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String generateStudentMarkListExcelReport(){
		if(sessionMap.containsKey("userName")){
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			ExamResultBean exrb = new ExamResultBean();
			exrb.setCur_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			exrb.setAt_name(ExamResultDAO.getQuarterName(rb.getAt_id()));
			exrb.setCl_name(CRoomDAO.getClassName(rb.getClass_bean().getCl_id()));
			rb.setEx_rslt_bean(exrb);
			
			if(rb.getClass_bean().getCl_id().equals("1")){
				rb.setCl_category("1-4(AA)");
			} else if(rb.getClass_bean().getCl_id().equals("2")){
				rb.setCl_category("5-6");
			} else if(rb.getClass_bean().getCl_id().equals("3")){
				rb.setCl_category("7-8");
			} else if(rb.getClass_bean().getCl_id().equals("4")){
				rb.setCl_category("9-10");
			}
			
			String excelFileName = "StudentMarkListReport_" + rb.getCl_category() + "_" + exrb.getAt_name() + "_" + exrb.getCur_date() + "_" + timeStamp;
			
			stud_mark_list_report = ReportDAO.getStudMarkListNumberPerMarkRang(rb);
			
			Excel_StudentMarkListReport.generateExcelFile(excelFileName, stud_mark_list_report);
			
			OpenFile.openExistingExcelFile(SysConstant.CERT_PDF_PATH, excelFileName);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	public String studMarkListFormatTemp(){
		if(sessionMap.containsKey("userName")){
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			

			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());			
			rb.setStud_bean(sb);
			
						
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	public String studMarkListFormatInfo(){
		if(sessionMap.containsKey("userName")){
			
			ExamResultBean crm = new ExamResultBean();
			crm.setSubcl_id(rb.getSub_bean().getSubcl_id());
			
			examtype_list = ExamDAO.getActivelyRelatedExamTypeListWithClassSubject(crm);
			
			stud_rslt = StudentDAO.getListPerGradeDetail(rb.getClass_bean().getCl_id(),rb.getCdetail_bean().getCd_id(), rb.getStud_bean().getAc_year());
						
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	public String unpaidStudentListTemp(){
		if(sessionMap.containsKey("userName")){
			
			grade_rslt = StudentDAO.getClassList();
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear), Integer.parseInt(acyear) - 1};
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String unpaidStudentList(){
		if(sessionMap.containsKey("userName")){
			
			unpaid_stud_list = ReportDAO.getUnpaidStudentList(rb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	
	public String paymentCollectionTemp(){
		if(sessionMap.containsKey("userName")){
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear), Integer.parseInt(acyear) - 1};
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	} 
	
	public String todaysPaymentCollection(){
		if(sessionMap.containsKey("userName")){
			
			rb.setCol_date_from(TodayDate_YYYYMMDD.getToday());
			rb.setCol_date_to(TodayDate_YYYYMMDD.getToday());
			
			payment_collection = ReportDAO.dailyPaymentCollection(rb);
			Double grandTotalPayment = 0.0;
			for(int i = 0; i < payment_collection.size(); i++){
				grandTotalPayment = grandTotalPayment + Double.parseDouble(payment_collection.get(i).getPayment_bean().getPay_amount());
			}
			rb.setGrand_total(String.valueOf(grandTotalPayment));
			rb.setCol_date_from(TodayDate_YYYYMMDD.getToday());
			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String dailyPaymentCollection(){
		if(sessionMap.containsKey("userName")){
			
			payment_collection = ReportDAO.dailyPaymentCollection(rb);
			Double grandTotalPayment = 0.0;
			for(int i = 0; i < payment_collection.size(); i++){
				grandTotalPayment = grandTotalPayment + Double.parseDouble(payment_collection.get(i).getPayment_bean().getPay_amount());
			}
			rb.setGrand_total(String.valueOf(grandTotalPayment));
			rb.setReport_type("Daily");
			rb.setCol_date_from(TodayDate_YYYYMMDD.getDayMonthYearFormat(rb.getCol_date_from()));
			rb.setCol_date_to(TodayDate_YYYYMMDD.getDayMonthYearFormat(rb.getCol_date_to()));
			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String emailPaymentCollection(){
		if(sessionMap.containsKey("userName")){
			
			String emailto = ReportDAO.getEmailToList();
			String emailSubject = "";
			String emailBody = "";
			
			if(rb.getCol_date_from().equalsIgnoreCase(rb.getCol_date_to())){
				
				String today_eth = DateConvertor.gregToEthioDateConvertor(rb.getCol_date_from());
				
				emailSubject = SysConstant.SCHOOL_FULL_NAME + " - " + today_eth + " / " + rb.getCol_date_from() + " Collection";
				
				emailBody = 
						"<table width=\"100%\" cellpadding=\"0\" rules=\"none\" cellspacing=\"0\" style=\"border: thin; border-color: gray; border-style: solid; border-width: thin; padding: 10px;\">" +
							"<tr>" +
								"<td align=\"center\" style=\"font-weight: bold; padding: 10px;\">" +
									SysConstant.SCHOOL_FULL_NAME +
								"</td>" +
							"</tr>" +
							"<tr>" +
								"<td align=\"center\">" +
									today_eth + " (" + rb.getCol_date_from() + ")"  + " Total Collection is " + rb.getGrand_total() + " Birr " +
								"</td>" +
							"</tr>" +
						"</table>";
			} else {
				
				String eth_date_from = DateConvertor.gregToEthioDateConvertor(rb.getCol_date_from());
				String eth_date_to = DateConvertor.gregToEthioDateConvertor(rb.getCol_date_to());
				
				emailSubject = SysConstant.SCHOOL_FULL_NAME + " - " + eth_date_from + " - " + eth_date_to + " / " + rb.getCol_date_from() + " - " + rb.getCol_date_to() + " Collection";
				
				emailBody = 
						"<table width=\"100%\" cellpadding=\"0\" rules=\"none\" cellspacing=\"0\" style=\"border: thin; border-color: gray; border-style: solid; border-width: thin; padding: 10px;\">" +
							"<tr>" +
								"<td align=\"center\" style=\"font-weight: bold; padding: 10px;\">" +
									SysConstant.SCHOOL_FULL_NAME +
								"</td>" +
							"</tr>" +
							"<tr>" +
								"<td align=\"center\">" +
									eth_date_from + " - " + eth_date_to + " (" + rb.getCol_date_from() + " - " + rb.getCol_date_to() + ")" + " Total Collection is " + rb.getGrand_total() + " Birr " +
								"</td>" +
							"</tr>" +
						"</table>";
			}
			
			return EmailForm.getEmailForm(emailto, emailSubject, emailBody);			

		} else {
			
			return INPUT;
		}
	}
	
	public String monthlyPaymentCollection(){
		if(sessionMap.containsKey("userName")){
			payment_collection = ReportDAO.monthlyPaymentCollection(rb);
			
			Double grandTotalPayment = 0.0;
			for(int i = 0; i < payment_collection.size(); i++){
				grandTotalPayment = grandTotalPayment + Double.parseDouble(payment_collection.get(i).getPayment_bean().getPay_amount());
			}
			rb.setGrand_total(String.valueOf(grandTotalPayment));
			
			/*PaymentBean pb = new PaymentBean();
			pb.setMonth_from(ReturnMonthNameInAmharic.getEthiopianMonthName(Integer.parseInt(rb.getCol_month_from())));*/
			//rb.setPayment_bean(pb);
			rb.setReport_type("Monthly");
			rb.setCol_month_from(ReturnMonthNameInAmharic.getEthiopianMonthName(Integer.parseInt(rb.getCol_month_from())));
			rb.setCol_month_to(ReturnMonthNameInAmharic.getEthiopianMonthName(Integer.parseInt(rb.getCol_month_to())));
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}	
	
	
	public String attendanceFormatTemplate(){
		if(sessionMap.containsKey("userName")){
			
			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());
			rb.setStud_bean(sb);
			
			grade_rslt = StudentDAO.getClassList();	
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentAttendanceForm(){
		if(sessionMap.containsKey("userName")){
			
			rb.setEduoff_filter_status(rb.getEduoff_filter_status());
			
			stud_rslt = StudentDAO.getListPerGradeDetail(rb.getClass_bean().getCl_id(),rb.getCdetail_bean().getCd_id(), rb.getStud_bean().getAc_year());
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getPDFStudentAttendanceForm(){
		if(sessionMap.containsKey("userName")){
			
			///>>> filter student <<<///
			stud_rslt = StudentDAO.getListPerGradeDetailForAttendance(rb.getClass_bean().getCl_id(),rb.getCdetail_bean().getCd_id(), rb.getStud_bean().getAc_year(), rb.getEduoff_filter_status());
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
						
			rb.getClass_bean().setClass_name(CRoomDAO.getClassName(rb.getClass_bean().getCl_id()));
			rb.getCdetail_bean().setCd_name(CRDetailDAO.getClassDetailName(rb.getCdetail_bean().getCd_id()));
			
			String fileNamePath = SysConstant.CERT_PDF_PATH + "StudentAttendanceFormat_" + rb.getClass_bean().getClass_name() + "" + rb.getCdetail_bean().getCd_name() + "_" + timeStamp + ".pdf";
						
			PDF_StudAttendanceFormat.getStudAttendanceFormatPDF(stud_rslt, fileNamePath, rb);
			
			OpenFile.openExistingPDFFile(fileNamePath);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String checkInsertedExamResult(){
		if(sessionMap.containsKey("userName")){
			
			StudentBean sb = new StudentBean();
			sb.setAc_year(ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());			
			rb.setStud_bean(sb);
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			examTypeList = ExamDAO.getActiveExamTypeList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getAllExamTypeList(){
		if(sessionMap.containsKey("userName")){
			examTypeList = ExamDAO.getActiveExamTypeList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getCheckInsertedMarkRsltList(){
		
		if(sessionMap.containsKey("userName")){

			summerized_studmark_number = ExamResultDAO.getSummerizedStudCountPerMarkInserted(rb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentTranscript(){
		
		if(sessionMap.containsKey("userName")){
			
			grdList_rslt = CRoomDAO.getActiveClassForTranscript();
			
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear),
					Integer.parseInt(acyear)-1, Integer.parseInt(acyear)-2};
			
			return SUCCESS;
		} else {
			
			return INPUT;
		}
	}
	
	public String getStudentLisPerClassDetail(){
		
		if(sessionMap.containsKey("userName")){
			
			grdList_rslt = CRoomDAO.getActiveClass();
			
			stud_rslt = StudentDAO.getListPerGradeDetail(rb.getStud_bean().getClass_id(), rb.getStud_bean().getCd_id(), rb.getStud_bean().getAc_year());
			Collections.sort(stud_rslt, new Comparator<StudentBean>() {
				public int compare(StudentBean a, StudentBean b){
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
	
	public String getStudentTranscript(){
		
		if(sessionMap.containsKey("userName")){
			
			StudentBean stud = new StudentBean();
			stud.setSi_id(rb.getStud_bean().getSi_id());
			stud.setAc_year(rb.getStud_bean().getAc_year());
			
			studList = StudentDAO.getStudentProfileByAcademicYear(stud);
			
			///*** Getting subject list ***///
			ExamResultBean exrslt = new ExamResultBean();
			exrslt.setCl_id(rb.getStud_bean().getClass_id());
			subList = ExamResultDAO.getSubjectList(exrslt);
			
			///*** Getting Class Number by Class Id ***///
			CRoomBean crb = new CRoomBean();
			crb.setClass_number(CRoomDAO.getClassNumberByClId(rb.getStud_bean().getClass_id()));
			rb.setClass_bean(crb);
			
			///*** Transcript Category - Grade Names ***/// 
			if(rb.getClass_bean().getClass_number() == 9 || rb.getClass_bean().getClass_number() == 10) {
				rb.setCategory_1_grade(String.valueOf(9));
				rb.setCategory_2_grade(String.valueOf(10));
			}
			if(rb.getClass_bean().getClass_number() == 11 || rb.getClass_bean().getClass_number() == 12) {
				rb.setCategory_1_grade(String.valueOf(11));
				rb.setCategory_2_grade(String.valueOf(12));
			}
			
			///*** Transcript Category - Academic Year ***///
			if(rb.getClass_bean().getClass_number() == 9 || rb.getClass_bean().getClass_number() == 11) {
				rb.setValue_from(rb.getStud_bean().getAc_year());
				rb.setValue_to(String.valueOf(Integer.parseInt(rb.getStud_bean().getAc_year())+1));
			}
			if(rb.getClass_bean().getClass_number() == 10 || rb.getClass_bean().getClass_number() == 12) {
				rb.setValue_from(String.valueOf(Integer.parseInt(rb.getStud_bean().getAc_year())-1));
				rb.setValue_to(rb.getStud_bean().getAc_year());
			}
			
			///*** Setting Transcript Category - 9 and 10 / 11 and 12
			boolean firstCategory = false, secondCategory = false;
			
			if(rb.getClass_bean().getClass_number() == 9 || rb.getClass_bean().getClass_number() == 11) {
				firstCategory = true;
			}
			if(rb.getClass_bean().getClass_number() == 10 || rb.getClass_bean().getClass_number() == 12) {
				secondCategory = true;
			}
			
			///*** Get Student's result for Grade 9 ***///			
			//if(rb.getStud_bean().getClass_id().equals("9")){
			if(firstCategory) {
				
				gradenine_trans_rslt = ExamResultDAO.getStudentResultForTranscript(rb.getStud_bean().getSi_id(), rb.getStud_bean().getClass_id(), rb.getCdetail_bean().getCd_id(), rb.getStud_bean().getAc_year());
				
				subList = gradenine_trans_rslt;
			}
			///*** END - Get Student's result for Grade 9 ***///
			
			
			///*** Get Student's result for Grade 10 ***///	
			//if(rb.getStud_bean().getClass_id().equals("10")){
			if(secondCategory) {
				
				String lastYearClassId = String.valueOf(StudentRegDAO.getStudentClassIDByAcademicYearSiId(rb.getStud_bean().getSi_id(), String.valueOf(Integer.parseInt(rb.getStud_bean().getAc_year()) - 1)));
				
				///>>> Check if the student was in grade 9 <<<///
				int getCd_id = StudentDAO.getStudentPreviousClassDetailId(
						rb.getStud_bean().getSi_id(), 
						lastYearClassId, 
						String.valueOf(Integer.parseInt(rb.getStud_bean().getAc_year()) - 1)
					);
				
				if(getCd_id > -1){
				
					gradenine_trans_rslt = ExamResultDAO.getStudentResultForTranscript(
							rb.getStud_bean().getSi_id(), 
							lastYearClassId, 
							String.valueOf(getCd_id), 
							String.valueOf(Integer.parseInt(rb.getStud_bean().getAc_year()) - 1));
				
				} 
				
				gradeten_trans_rslt = ExamResultDAO.getStudentResultForTranscript(rb.getStud_bean().getSi_id(), rb.getStud_bean().getClass_id(), rb.getCdetail_bean().getCd_id(), rb.getStud_bean().getAc_year());
				
				subList = getCd_id > -1?gradenine_trans_rslt:gradeten_trans_rslt;				
			}			
			///*** END - Get Student's result for Grade 10 ***///
			
			
			///*** Getting student result status ***///
			if(studList.get(0).getRslt_status().equals("Passed")){
				
				if(rb.getClass_bean().getClass_number() == 12) {
					stud.setRslt_status("Grade 12 completed.");
				} else {
					stud.setRslt_status("Promotted to Grade " + (rb.getClass_bean().getClass_number() + 1));
				}
			}
			if(studList.get(0).getRslt_status().equals("Failed")){
				stud.setRslt_status("Detained in Grade " + rb.getClass_bean().getClass_number() + ", he must attend the vacation enrichment program");
			}
			rb.setStud_bean(stud);
			///*** END - Getting student result status ***///
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	public void grade1112StudentTranscriptContent(){
		
		///*** Get Student's result for Grade 9 ***///			
		if(rb.getStud_bean().getClass_id().equals("11")){
			
			rb.setValue_from(rb.getStud_bean().getAc_year());
			rb.setValue_to(rb.getStud_bean().getAc_year());
			
			gradenine_trans_rslt = ExamResultDAO.getStudentResultForTranscript(rb.getStud_bean().getSi_id(), rb.getStud_bean().getClass_id(), rb.getCdetail_bean().getCd_id(), rb.getStud_bean().getAc_year());
			
			subList = gradenine_trans_rslt;
		}
		///*** END - Get Student's result for Grade 9 ***///
		
		
		///*** Get Student's result for Grade 10 ***///	
		if(rb.getStud_bean().getClass_id().equals("12")){
			
			///>>> Check if the student was in grade 9 <<<///
			int getCd_id = StudentDAO.getStudentPreviousClassDetailId(rb.getStud_bean().getSi_id(), String.valueOf(Integer.parseInt(rb.getStud_bean().getClass_id()) - 1), String.valueOf(Integer.parseInt(rb.getStud_bean().getAc_year()) - 1));
			
			if(getCd_id > -1){
			
				rb.setValue_from(String.valueOf(Integer.parseInt(rb.getStud_bean().getAc_year())-1));
				gradenine_trans_rslt = ExamResultDAO.getStudentResultForTranscript(rb.getStud_bean().getSi_id(), String.valueOf(Integer.parseInt(rb.getStud_bean().getClass_id()) - 1), String.valueOf(getCd_id), String.valueOf(Integer.parseInt(rb.getStud_bean().getAc_year()) - 1));
			} else {
				
				rb.setValue_from(rb.getStud_bean().getAc_year());
			}				
			
			rb.setValue_to(rb.getStud_bean().getAc_year());
			
			gradeten_trans_rslt = ExamResultDAO.getStudentResultForTranscript(rb.getStud_bean().getSi_id(), rb.getStud_bean().getClass_id(), rb.getCdetail_bean().getCd_id(), rb.getStud_bean().getAc_year());
			
			subList = getCd_id > -1?gradenine_trans_rslt:gradeten_trans_rslt;				
		}			
		///*** END - Get Student's result for Grade 10 ***///
	}
	
	public String belowAverageStudents(){
		
		if(sessionMap.containsKey("userName")){
			
			acyear = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			acyear_list = new int[] { Integer.parseInt(acyear),
					Integer.parseInt(acyear)-1, Integer.parseInt(acyear)-2};
			
			String yr = "";
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
	
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}
	}
	
	
	
	
	
	@Override
	public ReportBean getModel() {
		return rb;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
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

	public List<ExamBean> getExamTypeList() {
		return examTypeList;
	}

	public void setExamTypeList(List<ExamBean> examTypeList) {
		this.examTypeList = examTypeList;
	}

	public List<ExamResultBean> getSem_list() {
		return sem_list;
	}

	public void setSem_list(List<ExamResultBean> sem_list) {
		this.sem_list = sem_list;
	}

	public List<ExamScheduleBean> getExam_schedule_report() {
		return exam_schedule_report;
	}

	public void setExam_schedule_report(List<ExamScheduleBean> exam_schedule_report) {
		this.exam_schedule_report = exam_schedule_report;
	}

	public List<ExamScheduleBean> getOrganize_exam_schedule_report() {
		return organize_exam_schedule_report;
	}

	public void setOrganize_exam_schedule_report(List<ExamScheduleBean> organize_exam_schedule_report) {
		this.organize_exam_schedule_report = organize_exam_schedule_report;
	}

	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}

	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}

	public List<ReportBean> getQuarter_mark_list() {
		return quarter_mark_list;
	}

	public void setQuarter_mark_list(List<ReportBean> quarter_mark_list) {
		this.quarter_mark_list = quarter_mark_list;
	}

	public List<ReportBean> getQuarter_stud_roster() {
		return quarter_stud_roster;
	}

	public void setQuarter_stud_roster(List<ReportBean> quarter_stud_roster) {
		this.quarter_stud_roster = quarter_stud_roster;
	}

	public List<ReportBean> getSubject_list() {
		return subject_list;
	}

	public void setSubject_list(List<ReportBean> subject_list) {
		this.subject_list = subject_list;
	}

	public List<ReportBean> getTop_five_stud_per_class() {
		return top_five_stud_per_class;
	}

	public void setTop_five_stud_per_class(List<ReportBean> top_five_stud_per_class) {
		this.top_five_stud_per_class = top_five_stud_per_class;
	}

	public List<ReportBean> getTop_twenty_stud() {
		return top_twenty_stud;
	}

	public void setTop_twenty_stud(List<ReportBean> top_twenty_stud) {
		this.top_twenty_stud = top_twenty_stud;
	}

	public List<ReportBean> getStud_mark_plan_imp_list() {
		return stud_mark_plan_imp_list;
	}

	public void setStud_mark_plan_imp_list(List<ReportBean> stud_mark_plan_imp_list) {
		this.stud_mark_plan_imp_list = stud_mark_plan_imp_list;
	}

	public List<ReportBean> getStud_mark_list_report() {
		return stud_mark_list_report;
	}

	public void setStud_mark_list_report(List<ReportBean> stud_mark_list_report) {
		this.stud_mark_list_report = stud_mark_list_report;
	}

	public List<StudentBean> getStud_rslt() {
		return stud_rslt;
	}

	public void setStud_rslt(List<StudentBean> stud_rslt) {
		this.stud_rslt = stud_rslt;
	}

	public List<ReportBean> getUnpaid_stud_list() {
		return unpaid_stud_list;
	}

	public void setUnpaid_stud_list(List<ReportBean> unpaid_stud_list) {
		this.unpaid_stud_list = unpaid_stud_list;
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
	
	public List<ReportBean> getPayment_collection() {
		return payment_collection;
	}

	public void setPayment_collection(List<ReportBean> payment_collection) {
		this.payment_collection = payment_collection;
	}

	public List<CRoomBean> getExam_type_total_mark_list() {
		return exam_type_total_mark_list;
	}

	public void setExam_type_total_mark_list(List<CRoomBean> exam_type_total_mark_list) {
		this.exam_type_total_mark_list = exam_type_total_mark_list;
	}

	public List<CRoomBean> getGrdList_rslt() {
		return grdList_rslt;
	}

	public void setGrdList_rslt(List<CRoomBean> grdList_rslt) {
		this.grdList_rslt = grdList_rslt;
	}

	public List<ExamResultBean> getSubList() {
		return subList;
	}

	public void setSubList(List<ExamResultBean> subList) {
		this.subList = subList;
	}

//	public List<ExamResultBean> getStudMarkList() {
//		return studMarkList;
//	}
//
//	public void setStudMarkList(List<ExamResultBean> studMarkList) {
//		this.studMarkList = studMarkList;
//	}

	public List<StudentBean> getStudList() {
		return studList;
	}

	public void setStudList(List<StudentBean> studList) {
		this.studList = studList;
	}

	public List<List<ExamResultBean>> getFullYearStudRoster() {
		return fullYearStudRoster;
	}

	public void setFullYearStudRoster(List<List<ExamResultBean>> fullYearStudRoster) {
		this.fullYearStudRoster = fullYearStudRoster;
	}

	public List<ExamBean> getExamtype_list() {
		return examtype_list;
	}

	public void setExamtype_list(List<ExamBean> examtype_list) {
		this.examtype_list = examtype_list;
	}

	public List<ExamResultBean> getSummerized_studmark_number() {
		return summerized_studmark_number;
	}

	public void setSummerized_studmark_number(List<ExamResultBean> summerized_studmark_number) {
		this.summerized_studmark_number = summerized_studmark_number;
	}

	public List<ExamResultBean> getGradenine_trans_rslt() {
		return gradenine_trans_rslt;
	}

	public void setGradenine_trans_rslt(List<ExamResultBean> gradenine_trans_rslt) {
		this.gradenine_trans_rslt = gradenine_trans_rslt;
	}

	public List<ExamResultBean> getGradeten_trans_rslt() {
		return gradeten_trans_rslt;
	}

	public void setGradeten_trans_rslt(List<ExamResultBean> gradeten_trans_rslt) {
		this.gradeten_trans_rslt = gradeten_trans_rslt;
	}

	public List<SubjectBean> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectBean> subjectList) {
		this.subjectList = subjectList;
	}

}
