package reportClass;

import java.util.ArrayList;
import java.util.List;

import org.jfree.date.AnnualDateRule;

import cRoomClass.CRoomBean;
import cRoomClass.class_detail.CRDetailBean;
import examClass.exam_result.ExamResultBean;
import examClass.exam_type.ExamBean;
import otherMembers.OtherMembersBean;
import paymentFineClass.PaymentBean;
import studentClass.StudentBean;
import subjectClass.SubjectBean;

public class ReportBean {
	
	private String at_id;
	private String et_id;
	
	private String exam_one;
	private String exam_two;
	private String worksheet;
	private String activity;
	private String sub_total;
	private String finalExam;
	private String grand_total;
	
	private List<String> subject_total = new ArrayList<String>();
	
	private StudentBean stud_bean;
	private SubjectBean sub_bean;
	private ExamBean exam_bean;
	private CRoomBean class_bean;
	private CRDetailBean cdetail_bean;
	private ExamResultBean ex_rslt_bean;
	private PaymentBean payment_bean;
	private OtherMembersBean other_members_bean;
	
	private double male_stat_percent_cat1;
	private double female_stat_percent_cat1;
	private double total_stat_percent_cat1;	
	private int male_num_cat1;
	private int female_num_cat1;
	private int total_num_cat1;
	private double male_percent_cat1;
	private double female_percent_cat1;
	private double total_percent_cat1;
	
	private int grt_50_total_male_cat1;
	private int grt_50_total_female_cat1;
	private int grt_50_total_cat1;
	private double grt_50_total_percent_male_cat1;
	private double grt_50_total_percent_female_cat1;
	private double grt_50_total_percent_cat1;
	
	private double male_stat_percent_cat2;
	private double female_stat_percent_cat2;
	private double total_stat_percent_cat2;
	private int male_num_cat2;
	private int female_num_cat2;
	private int total_num_cat2;
	private double male_percent_cat2;
	private double female_percent_cat2;
	private double total_percent_cat2;
	
	private int grt_75_total_male_cat2;
	private int grt_75_total_female_cat2;
	private int grt_75_total_cat2;
	private double grt_75_total_percent_male_cat2;
	private double grt_75_total_percent_female_cat2;
	private double grt_75_total_percent_cat2;
	
	private double male_stat_percent_cat3;
	private double female_stat_percent_cat3;
	private double total_stat_percent_cat3;
	private int male_num_cat3;
	private int female_num_cat3;
	private int total_num_cat3;
	private double male_percent_cat3;
	private double female_percent_cat3;
	private double total_percent_cat3;
	
	private int grt_90_total_male_cat3;
	private int grt_90_total_female_cat3;
	private int grt_90_total_cat3;
	private double grt_90_total_percent_male_cat3;
	private double grt_90_total_percent_female_cat3;
	private double grt_90_total_percent_cat3;
	
	private int male_grt_50;
	private int female_grt_50;
	private int total_grt_50;
	private double male_percent_grt_50;
	private double female_percent_grt_50;
	private double total_percent_grt_50;
	
	private String cl_category;
	
	private String eduoff_filter_status;
	
	private String report_type;
	private String col_date_from;
	private String col_month_from;
	private String col_date_to;
	private String col_month_to;
	private String value_from;
	private String value_to;
	private String category_1_grade;
	private String category_2_grade;
	
	private String overAllTopRanking;
	private int topOf;	
	private List<String> selectedGradeList;
	private List<String> selectedQuarterList;
	
	public String getAt_id() {
		return at_id;
	}
	public void setAt_id(String at_id) {
		this.at_id = at_id;
	}
	public String getEt_id() {
		return et_id;
	}
	public void setEt_id(String et_id) {
		this.et_id = et_id;
	}
	public StudentBean getStud_bean() {
		return stud_bean;
	}
	public void setStud_bean(StudentBean stud_bean) {
		this.stud_bean = stud_bean;
	}
	public SubjectBean getSub_bean() {
		return sub_bean;
	}
	public void setSub_bean(SubjectBean sub_bean) {
		this.sub_bean = sub_bean;
	}
	public ExamBean getExam_bean() {
		return exam_bean;
	}
	public void setExam_bean(ExamBean exam_bean) {
		this.exam_bean = exam_bean;
	}
	public CRoomBean getClass_bean() {
		return class_bean;
	}
	public void setClass_bean(CRoomBean class_bean) {
		this.class_bean = class_bean;
	}
	public CRDetailBean getCdetail_bean() {
		return cdetail_bean;
	}
	public void setCdetail_bean(CRDetailBean cdetail_bean) {
		this.cdetail_bean = cdetail_bean;
	}
	public ExamResultBean getEx_rslt_bean() {
		return ex_rslt_bean;
	}
	public void setEx_rslt_bean(ExamResultBean ex_rslt_bean) {
		this.ex_rslt_bean = ex_rslt_bean;
	}
	public String getExam_one() {
		return exam_one;
	}
	public void setExam_one(String exam_one) {
		this.exam_one = exam_one;
	}
	public String getExam_two() {
		return exam_two;
	}
	public void setExam_two(String exam_two) {
		this.exam_two = exam_two;
	}
	public String getWorksheet() {
		return worksheet;
	}
	public void setWorksheet(String worksheet) {
		this.worksheet = worksheet;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getFinalExam() {
		return finalExam;
	}
	public void setFinalExam(String finalExam) {
		this.finalExam = finalExam;
	}
	public String getSub_total() {
		return sub_total;
	}
	public void setSub_total(String sub_total) {
		this.sub_total = sub_total;
	}
	public String getGrand_total() {
		return grand_total;
	}
	public void setGrand_total(String grand_total) {
		this.grand_total = grand_total;
	}
	public List<String> getSubject_total() {
		return subject_total;
	}
	public void setSubject_total(List<String> subject_total) {
		this.subject_total = subject_total;
	}
	public int getMale_num_cat1() {
		return male_num_cat1;
	}
	public void setMale_num_cat1(int male_num_cat1) {
		this.male_num_cat1 = male_num_cat1;
	}
	public int getFemale_num_cat1() {
		return female_num_cat1;
	}
	public void setFemale_num_cat1(int female_num_cat1) {
		this.female_num_cat1 = female_num_cat1;
	}
	public int getTotal_num_cat1() {
		return total_num_cat1;
	}
	public void setTotal_num_cat1(int total_num_cat1) {
		this.total_num_cat1 = total_num_cat1;
	}
	public double getMale_percent_cat1() {
		return male_percent_cat1;
	}
	public void setMale_percent_cat1(double male_percent_cat1) {
		this.male_percent_cat1 = male_percent_cat1;
	}
	public double getFemale_percent_cat1() {
		return female_percent_cat1;
	}
	public void setFemale_percent_cat1(double female_percent_cat1) {
		this.female_percent_cat1 = female_percent_cat1;
	}
	public double getTotal_percent_cat1() {
		return total_percent_cat1;
	}
	public void setTotal_percent_cat1(double total_percent_cat1) {
		this.total_percent_cat1 = total_percent_cat1;
	}
	public int getMale_num_cat2() {
		return male_num_cat2;
	}
	public void setMale_num_cat2(int male_num_cat2) {
		this.male_num_cat2 = male_num_cat2;
	}
	public int getFemale_num_cat2() {
		return female_num_cat2;
	}
	public void setFemale_num_cat2(int female_num_cat2) {
		this.female_num_cat2 = female_num_cat2;
	}
	public int getTotal_num_cat2() {
		return total_num_cat2;
	}
	public void setTotal_num_cat2(int total_num_cat2) {
		this.total_num_cat2 = total_num_cat2;
	}
	public double getMale_percent_cat2() {
		return male_percent_cat2;
	}
	public void setMale_percent_cat2(double male_percent_cat2) {
		this.male_percent_cat2 = male_percent_cat2;
	}
	public double getFemale_percent_cat2() {
		return female_percent_cat2;
	}
	public void setFemale_percent_cat2(double female_percent_cat2) {
		this.female_percent_cat2 = female_percent_cat2;
	}
	public double getTotal_percent_cat2() {
		return total_percent_cat2;
	}
	public void setTotal_percent_cat2(double total_percent_cat2) {
		this.total_percent_cat2 = total_percent_cat2;
	}
	public int getMale_num_cat3() {
		return male_num_cat3;
	}
	public void setMale_num_cat3(int male_num_cat3) {
		this.male_num_cat3 = male_num_cat3;
	}
	public int getFemale_num_cat3() {
		return female_num_cat3;
	}
	public void setFemale_num_cat3(int female_num_cat3) {
		this.female_num_cat3 = female_num_cat3;
	}
	public int getTotal_num_cat3() {
		return total_num_cat3;
	}
	public void setTotal_num_cat3(int total_num_cat3) {
		this.total_num_cat3 = total_num_cat3;
	}
	public double getMale_percent_cat3() {
		return male_percent_cat3;
	}
	public void setMale_percent_cat3(double male_percent_cat3) {
		this.male_percent_cat3 = male_percent_cat3;
	}
	public double getFemale_percent_cat3() {
		return female_percent_cat3;
	}
	public void setFemale_percent_cat3(double female_percent_cat3) {
		this.female_percent_cat3 = female_percent_cat3;
	}
	public double getTotal_percent_cat3() {
		return total_percent_cat3;
	}
	public void setTotal_percent_cat3(double total_percent_cat3) {
		this.total_percent_cat3 = total_percent_cat3;
	}
	public int getMale_grt_50() {
		return male_grt_50;
	}
	public void setMale_grt_50(int male_grt_50) {
		this.male_grt_50 = male_grt_50;
	}
	public int getFemale_grt_50() {
		return female_grt_50;
	}
	public void setFemale_grt_50(int female_grt_50) {
		this.female_grt_50 = female_grt_50;
	}
	public int getTotal_grt_50() {
		return total_grt_50;
	}
	public void setTotal_grt_50(int total_grt_50) {
		this.total_grt_50 = total_grt_50;
	}
	public double getMale_percent_grt_50() {
		return male_percent_grt_50;
	}
	public void setMale_percent_grt_50(double male_percent_grt_50) {
		this.male_percent_grt_50 = male_percent_grt_50;
	}
	public double getFemale_percent_grt_50() {
		return female_percent_grt_50;
	}
	public void setFemale_percent_grt_50(double female_percent_grt_50) {
		this.female_percent_grt_50 = female_percent_grt_50;
	}
	public double getTotal_percent_grt_50() {
		return total_percent_grt_50;
	}
	public void setTotal_percent_grt_50(double total_percent_grt_50) {
		this.total_percent_grt_50 = total_percent_grt_50;
	}
	public String getCl_category() {
		return cl_category;
	}
	public void setCl_category(String cl_category) {
		this.cl_category = cl_category;
	}
	public String getEduoff_filter_status() {
		return eduoff_filter_status;
	}
	public void setEduoff_filter_status(String eduoff_filter_status) {
		this.eduoff_filter_status = eduoff_filter_status;
	}
	public double getMale_stat_percent_cat1() {
		return male_stat_percent_cat1;
	}
	public void setMale_stat_percent_cat1(double male_stat_percent_cat1) {
		this.male_stat_percent_cat1 = male_stat_percent_cat1;
	}
	public double getFemale_stat_percent_cat1() {
		return female_stat_percent_cat1;
	}
	public void setFemale_stat_percent_cat1(double female_stat_percent_cat1) {
		this.female_stat_percent_cat1 = female_stat_percent_cat1;
	}
	public double getTotal_stat_percent_cat1() {
		return total_stat_percent_cat1;
	}
	public void setTotal_stat_percent_cat1(double total_stat_percent_cat1) {
		this.total_stat_percent_cat1 = total_stat_percent_cat1;
	}
	public double getMale_stat_percent_cat2() {
		return male_stat_percent_cat2;
	}
	public void setMale_stat_percent_cat2(double male_stat_percent_cat2) {
		this.male_stat_percent_cat2 = male_stat_percent_cat2;
	}
	public double getFemale_stat_percent_cat2() {
		return female_stat_percent_cat2;
	}
	public void setFemale_stat_percent_cat2(double female_stat_percent_cat2) {
		this.female_stat_percent_cat2 = female_stat_percent_cat2;
	}
	public double getTotal_stat_percent_cat2() {
		return total_stat_percent_cat2;
	}
	public void setTotal_stat_percent_cat2(double total_stat_percent_cat2) {
		this.total_stat_percent_cat2 = total_stat_percent_cat2;
	}
	public double getMale_stat_percent_cat3() {
		return male_stat_percent_cat3;
	}
	public void setMale_stat_percent_cat3(double male_stat_percent_cat3) {
		this.male_stat_percent_cat3 = male_stat_percent_cat3;
	}
	public double getFemale_stat_percent_cat3() {
		return female_stat_percent_cat3;
	}
	public void setFemale_stat_percent_cat3(double female_stat_percent_cat3) {
		this.female_stat_percent_cat3 = female_stat_percent_cat3;
	}
	public double getTotal_stat_percent_cat3() {
		return total_stat_percent_cat3;
	}
	public void setTotal_stat_percent_cat3(double total_stat_percent_cat3) {
		this.total_stat_percent_cat3 = total_stat_percent_cat3;
	}
	public int getGrt_50_total_male_cat1() {
		return grt_50_total_male_cat1;
	}
	public void setGrt_50_total_male_cat1(int grt_50_total_male_cat1) {
		this.grt_50_total_male_cat1 = grt_50_total_male_cat1;
	}
	public int getGrt_50_total_female_cat1() {
		return grt_50_total_female_cat1;
	}
	public void setGrt_50_total_female_cat1(int grt_50_total_female_cat1) {
		this.grt_50_total_female_cat1 = grt_50_total_female_cat1;
	}
	public int getGrt_50_total_cat1() {
		return grt_50_total_cat1;
	}
	public void setGrt_50_total_cat1(int grt_50_total_cat1) {
		this.grt_50_total_cat1 = grt_50_total_cat1;
	}
	public int getGrt_75_total_male_cat2() {
		return grt_75_total_male_cat2;
	}
	public void setGrt_75_total_male_cat2(int grt_75_total_male_cat2) {
		this.grt_75_total_male_cat2 = grt_75_total_male_cat2;
	}
	public int getGrt_75_total_female_cat2() {
		return grt_75_total_female_cat2;
	}
	public void setGrt_75_total_female_cat2(int grt_75_total_female_cat2) {
		this.grt_75_total_female_cat2 = grt_75_total_female_cat2;
	}
	public int getGrt_75_total_cat2() {
		return grt_75_total_cat2;
	}
	public void setGrt_75_total_cat2(int grt_75_total_cat2) {
		this.grt_75_total_cat2 = grt_75_total_cat2;
	}
	public int getGrt_90_total_male_cat3() {
		return grt_90_total_male_cat3;
	}
	public void setGrt_90_total_male_cat3(int grt_90_total_male_cat3) {
		this.grt_90_total_male_cat3 = grt_90_total_male_cat3;
	}
	public int getGrt_90_total_female_cat3() {
		return grt_90_total_female_cat3;
	}
	public void setGrt_90_total_female_cat3(int grt_90_total_female_cat3) {
		this.grt_90_total_female_cat3 = grt_90_total_female_cat3;
	}
	public int getGrt_90_total_cat3() {
		return grt_90_total_cat3;
	}
	public void setGrt_90_total_cat3(int grt_90_total_cat3) {
		this.grt_90_total_cat3 = grt_90_total_cat3;
	}
	public double getGrt_50_total_percent_male_cat1() {
		return grt_50_total_percent_male_cat1;
	}
	public void setGrt_50_total_percent_male_cat1(double grt_50_total_percent_male_cat1) {
		this.grt_50_total_percent_male_cat1 = grt_50_total_percent_male_cat1;
	}
	public double getGrt_50_total_percent_female_cat1() {
		return grt_50_total_percent_female_cat1;
	}
	public void setGrt_50_total_percent_female_cat1(double grt_50_total_percent_female_cat1) {
		this.grt_50_total_percent_female_cat1 = grt_50_total_percent_female_cat1;
	}
	public double getGrt_50_total_percent_cat1() {
		return grt_50_total_percent_cat1;
	}
	public void setGrt_50_total_percent_cat1(double grt_50_total_percent_cat1) {
		this.grt_50_total_percent_cat1 = grt_50_total_percent_cat1;
	}
	public double getGrt_75_total_percent_male_cat2() {
		return grt_75_total_percent_male_cat2;
	}
	public void setGrt_75_total_percent_male_cat2(double grt_75_total_percent_male_cat2) {
		this.grt_75_total_percent_male_cat2 = grt_75_total_percent_male_cat2;
	}
	public double getGrt_75_total_percent_female_cat2() {
		return grt_75_total_percent_female_cat2;
	}
	public void setGrt_75_total_percent_female_cat2(double grt_75_total_percent_female_cat2) {
		this.grt_75_total_percent_female_cat2 = grt_75_total_percent_female_cat2;
	}
	public double getGrt_75_total_percent_cat2() {
		return grt_75_total_percent_cat2;
	}
	public void setGrt_75_total_percent_cat2(double grt_75_total_percent_cat2) {
		this.grt_75_total_percent_cat2 = grt_75_total_percent_cat2;
	}
	public double getGrt_90_total_percent_male_cat3() {
		return grt_90_total_percent_male_cat3;
	}
	public void setGrt_90_total_percent_male_cat3(double grt_90_total_percent_male_cat3) {
		this.grt_90_total_percent_male_cat3 = grt_90_total_percent_male_cat3;
	}
	public double getGrt_90_total_percent_female_cat3() {
		return grt_90_total_percent_female_cat3;
	}
	public void setGrt_90_total_percent_female_cat3(double grt_90_total_percent_female_cat3) {
		this.grt_90_total_percent_female_cat3 = grt_90_total_percent_female_cat3;
	}
	public double getGrt_90_total_percent_cat3() {
		return grt_90_total_percent_cat3;
	}
	public void setGrt_90_total_percent_cat3(double grt_90_total_percent_cat3) {
		this.grt_90_total_percent_cat3 = grt_90_total_percent_cat3;
	}
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public String getCol_date_from() {
		return col_date_from;
	}
	public void setCol_date_from(String col_date_from) {
		this.col_date_from = col_date_from;
	}
	public String getCol_month_from() {
		return col_month_from;
	}
	public void setCol_month_from(String col_month_from) {
		this.col_month_from = col_month_from;
	}
	public String getCol_date_to() {
		return col_date_to;
	}
	public void setCol_date_to(String col_date_to) {
		this.col_date_to = col_date_to;
	}
	public String getCol_month_to() {
		return col_month_to;
	}
	public void setCol_month_to(String col_month_to) {
		this.col_month_to = col_month_to;
	}
	public PaymentBean getPayment_bean() {
		return payment_bean;
	}
	public void setPayment_bean(PaymentBean payment_bean) {
		this.payment_bean = payment_bean;
	}
	public OtherMembersBean getOther_members_bean() {
		return other_members_bean;
	}
	public void setOther_members_bean(OtherMembersBean other_members_bean) {
		this.other_members_bean = other_members_bean;
	}
	public String getValue_from() {
		return value_from;
	}
	public void setValue_from(String value_from) {
		this.value_from = value_from;
	}
	public String getValue_to() {
		return value_to;
	}
	public void setValue_to(String value_to) {
		this.value_to = value_to;
	}
	public int getTopOf() {
		return topOf;
	}
	public void setTopOf(int topOf) {
		this.topOf = topOf;
	}
	public List<String> getSelectedGradeList() {
		return selectedGradeList;
	}
	public void setSelectedGradeList(List<String> selectedGradeList) {
		this.selectedGradeList = selectedGradeList;
	}
	public List<String> getSelectedQuarterList() {
		return selectedQuarterList;
	}
	public void setSelectedQuarterList(List<String> selectedQuarterList) {
		this.selectedQuarterList = selectedQuarterList;
	}
	public String getOverAllTopRanking() {
		return overAllTopRanking;
	}
	public void setOverAllTopRanking(String overAllTopRanking) {
		this.overAllTopRanking = overAllTopRanking;
	}
	public String getCategory_1_grade() {
		return category_1_grade;
	}
	public void setCategory_1_grade(String category_1_grade) {
		this.category_1_grade = category_1_grade;
	}
	public String getCategory_2_grade() {
		return category_2_grade;
	}
	public void setCategory_2_grade(String category_2_grade) {
		this.category_2_grade = category_2_grade;
	}		
	
}
