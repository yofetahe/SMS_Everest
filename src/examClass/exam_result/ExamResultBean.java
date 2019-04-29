package examClass.exam_result;

import java.util.List;

import studentClass.StudentBean;

public class ExamResultBean {
	
	//>>> exam_result table object
	private String er_id;
	private String si_id;
	private String exsub_id;
	private String result;
	private String at_id;
	private String academic_year;
	
	//stud_information table object
	private String fname;
	private String mname;
	private String gname;
	
	//class_list table object
	private String cl_id;
	private String cl_name;
	
	//subject_class_rel table object
	private String subcl_id;
	
	//class_detail table object
	private String cd_id;
	private String cd_name;
	
	//subject_list table object
	private String sub_id;
	private String sub_name;
	
	//exam_type table object
	private String et_id;
	private String et_name;
	
	//annual_term table object
	private String at_name;

	//exam_result_comment table object
	private String erc_id;
	private String erc_content;
	
	//additional object
	private String sub_passmark;
	private String sub_totalmark;
	private String submark_status;
	private String subject_total;
		
	private String grandTotal_mark;
	private String subjectTotal_mark;
	private String average_mark;
	private String total_mark;	
	private String pass_mark;
	private String mark_status;
	private String semister_total;
	private String stud_rank;
	private String studRslt_status;
	private String examDate_status;
	
	private String firstSemSubjectTotal;
	private String secondSemSubjectTotal;
	private String subjectFullYearAvg;
	private String firstSemisterTotal;
	private String firstSemisterAvg;
	private String secondSemisterTotal;
	private String secondSemisterAvg;
	private String fullYearAvg;
	private String averageMarkTotal;
	
	private String rwindex;
	private String stud_count;
	private String cur_date;
	
	private String qr1_at_id = "0";
	private String qr2_at_id = "0";
	private String qr3_at_id = "0";
	
	private String cummulative_mark;
	private String final_exam_mark;
	private String quarter_total;
	private String quarter_grand_total;
	private String quarter_average;
	private String teacher_comment;
	private String homeroom_teacher_name;
	private String grand_quarter_total;
	private String average_quarter_mark;
	private String sub_included_in_total_mark;
	private String convert_to_grade;
	private String grading_result;
	private String quarter_rank;
	private String year_avg_rank;
	
	private String qrt_one_total;
	private String qrt_two_total;
	private String qrt_three_total;
	private String qrt_four_total;
		
	private String success_counter;
	private String failur_counter;
	private String doesntexist_counter;
	
	private String noOfAbsentDay;
	private String absentDayList;
	
	private String pageFlag;	
	private String file_path;
	private String column_num;
	private String row_num;
	private String sheet_num;
	
	private String subject_teacher;	
	
	private double rank_from;
	private double rank_to;
	private double fail_rank_from;
	private double fail_rank_to;
	
	private List<Object> studMarkList;
	private List<List<ExamResultBean>> studEachQrtTotalRslt;
	

	private List<String> allExamResult;
	
	
	
	private StudentBean stud;
	
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
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public String getSubcl_id() {
		return subcl_id;
	}
	public void setSubcl_id(String subcl_id) {
		this.subcl_id = subcl_id;
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
	public String getCl_name() {
		return cl_name;
	}
	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}
	public String getCd_id() {
		return cd_id;
	}
	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}
	public String getGrandTotal_mark() {
		return grandTotal_mark;
	}
	public void setGrandTotal_mark(String grandTotal_mark) {
		this.grandTotal_mark = grandTotal_mark;
	}
	public String getSubjectTotal_mark() {
		return subjectTotal_mark;
	}
	public void setSubjectTotal_mark(String subjectTotal_mark) {
		this.subjectTotal_mark = subjectTotal_mark;
	}
	public String getAverage_mark() {
		return average_mark;
	}
	public void setAverage_mark(String average_mark) {
		this.average_mark = average_mark;
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public String getCd_name() {
		return cd_name;
	}
	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}
	public String getMark_status() {
		return mark_status;
	}
	public void setMark_status(String mark_status) {
		this.mark_status = mark_status;
	}
	public String getEt_id() {
		return et_id;
	}
	public void setEt_id(String et_id) {
		this.et_id = et_id;
	}
	public String getEt_name() {
		return et_name;
	}
	public void setEt_name(String et_name) {
		this.et_name = et_name;
	}
	public String getSub_passmark() {
		return sub_passmark;
	}
	public void setSub_passmark(String sub_passmark) {
		this.sub_passmark = sub_passmark;
	}
	public String getSub_totalmark() {
		return sub_totalmark;
	}
	public void setSub_totalmark(String sub_totalmark) {
		this.sub_totalmark = sub_totalmark;
	}
	public String getSubmark_status() {
		return submark_status;
	}
	public void setSubmark_status(String submark_status) {
		this.submark_status = submark_status;
	}
	public String getSubject_total() {
		return subject_total;
	}
	public void setSubject_total(String subject_total) {
		this.subject_total = subject_total;
	}
	public String getSemister_total() {
		return semister_total;
	}
	public void setSemister_total(String semister_total) {
		this.semister_total = semister_total;
	}
	public String getAcademic_year() {
		return academic_year;
	}
	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
	}
	public String getCur_date() {
		return cur_date;
	}
	public void setCur_date(String cur_date) {
		this.cur_date = cur_date;
	}
	public String getStud_rank() {
		return stud_rank;
	}
	public void setStud_rank(String stud_rank) {
		this.stud_rank = stud_rank;
	}
	public String getStudRslt_status() {
		return studRslt_status;
	}
	public void setStudRslt_status(String studRslt_status) {
		this.studRslt_status = studRslt_status;
	}
	public String getAt_id() {
		return at_id;
	}
	public void setAt_id(String at_id) {
		this.at_id = at_id;
	}
	public String getAt_name() {
		return at_name;
	}
	public void setAt_name(String at_name) {
		this.at_name = at_name;
	}
	public String getFirstSemisterTotal() {
		return firstSemisterTotal;
	}
	public void setFirstSemisterTotal(String firstSemisterTotal) {
		this.firstSemisterTotal = firstSemisterTotal;
	}
	public String getFirstSemisterAvg() {
		return firstSemisterAvg;
	}
	public void setFirstSemisterAvg(String firstSemisterAvg) {
		this.firstSemisterAvg = firstSemisterAvg;
	}
	public String getSecondSemisterTotal() {
		return secondSemisterTotal;
	}
	public void setSecondSemisterTotal(String secondSemisterTotal) {
		this.secondSemisterTotal = secondSemisterTotal;
	}
	public String getSecondSemisterAvg() {
		return secondSemisterAvg;
	}
	public void setSecondSemisterAvg(String secondSemisterAvg) {
		this.secondSemisterAvg = secondSemisterAvg;
	}
	public String getFullYearAvg() {
		return fullYearAvg;
	}
	public void setFullYearAvg(String fullYearAvg) {
		this.fullYearAvg = fullYearAvg;
	}
	public String getSubjectFullYearAvg() {
		return subjectFullYearAvg;
	}
	public void setSubjectFullYearAvg(String subjectFullYearAvg) {
		this.subjectFullYearAvg = subjectFullYearAvg;
	}
	public String getAverageMarkTotal() {
		return averageMarkTotal;
	}
	public void setAverageMarkTotal(String averageMarkTotal) {
		this.averageMarkTotal = averageMarkTotal;
	}
	public String getFirstSemSubjectTotal() {
		return firstSemSubjectTotal;
	}
	public void setFirstSemSubjectTotal(String firstSemSubjectTotal) {
		this.firstSemSubjectTotal = firstSemSubjectTotal;
	}
	public String getSecondSemSubjectTotal() {
		return secondSemSubjectTotal;
	}
	public void setSecondSemSubjectTotal(String secondSemSubjectTotal) {
		this.secondSemSubjectTotal = secondSemSubjectTotal;
	}
	public String getExamDate_status() {
		return examDate_status;
	}
	public void setExamDate_status(String examDate_status) {
		this.examDate_status = examDate_status;
	}
	public String getCummulative_mark() {
		return cummulative_mark;
	}
	public void setCummulative_mark(String cummulative_mark) {
		this.cummulative_mark = cummulative_mark;
	}
	public String getFinal_exam_mark() {
		return final_exam_mark;
	}
	public void setFinal_exam_mark(String final_exam_mark) {
		this.final_exam_mark = final_exam_mark;
	}
	public String getQuarter_total() {
		return quarter_total;
	}
	public void setQuarter_total(String quarter_total) {
		this.quarter_total = quarter_total;
	}
	public String getQuarter_grand_total() {
		return quarter_grand_total;
	}
	public void setQuarter_grand_total(String quarter_grand_total) {
		this.quarter_grand_total = quarter_grand_total;
	}
	public String getQuarter_average() {
		return quarter_average;
	}
	public void setQuarter_average(String quarter_average) {
		this.quarter_average = quarter_average;
	}
	public String getTeacher_comment() {
		return teacher_comment;
	}
	public void setTeacher_comment(String teacher_comment) {
		this.teacher_comment = teacher_comment;
	}
	public String getErc_id() {
		return erc_id;
	}
	public void setErc_id(String erc_id) {
		this.erc_id = erc_id;
	}
	public String getErc_content() {
		return erc_content;
	}
	public void setErc_content(String erc_content) {
		this.erc_content = erc_content;
	}
	public String getHomeroom_teacher_name() {
		return homeroom_teacher_name;
	}
	public void setHomeroom_teacher_name(String homeroom_teacher_name) {
		this.homeroom_teacher_name = homeroom_teacher_name;
	}
	public String getGrand_quarter_total() {
		return grand_quarter_total;
	}
	public void setGrand_quarter_total(String grand_quarter_total) {
		this.grand_quarter_total = grand_quarter_total;
	}
	public String getAverage_quarter_mark() {
		return average_quarter_mark;
	}
	public void setAverage_quarter_mark(String average_quarter_mark) {
		this.average_quarter_mark = average_quarter_mark;
	}
	public String getSub_included_in_total_mark() {
		return sub_included_in_total_mark;
	}
	public void setSub_included_in_total_mark(String sub_included_in_total_mark) {
		this.sub_included_in_total_mark = sub_included_in_total_mark;
	}
	public String getConvert_to_grade() {
		return convert_to_grade;
	}
	public void setConvert_to_grade(String convert_to_grade) {
		this.convert_to_grade = convert_to_grade;
	}
	public String getQrt_one_total() {
		return qrt_one_total;
	}
	public void setQrt_one_total(String qrt_one_total) {
		this.qrt_one_total = qrt_one_total;
	}
	public String getQrt_two_total() {
		return qrt_two_total;
	}
	public void setQrt_two_total(String qrt_two_total) {
		this.qrt_two_total = qrt_two_total;
	}
	public String getQrt_three_total() {
		return qrt_three_total;
	}
	public void setQrt_three_total(String qrt_three_total) {
		this.qrt_three_total = qrt_three_total;
	}
	public String getQrt_four_total() {
		return qrt_four_total;
	}
	public void setQrt_four_total(String qrt_four_total) {
		this.qrt_four_total = qrt_four_total;
	}
	public String getGrading_result() {
		return grading_result;
	}
	public void setGrading_result(String grading_result) {
		this.grading_result = grading_result;
	}
	public String getQuarter_rank() {
		return quarter_rank;
	}
	public void setQuarter_rank(String quarter_rank) {
		this.quarter_rank = quarter_rank;
	}
	public String getYear_avg_rank() {
		return year_avg_rank;
	}
	public void setYear_avg_rank(String year_avg_rank) {
		this.year_avg_rank = year_avg_rank;
	}
	public String getSuccess_counter() {
		return success_counter;
	}
	public void setSuccess_counter(String success_counter) {
		this.success_counter = success_counter;
	}
	public String getFailur_counter() {
		return failur_counter;
	}
	public void setFailur_counter(String failur_counter) {
		this.failur_counter = failur_counter;
	}
	public String getDoesntexist_counter() {
		return doesntexist_counter;
	}
	public void setDoesntexist_counter(String doesntexist_counter) {
		this.doesntexist_counter = doesntexist_counter;
	}
	public String getNoOfAbsentDay() {
		return noOfAbsentDay;
	}
	public void setNoOfAbsentDay(String noOfAbsentDay) {
		this.noOfAbsentDay = noOfAbsentDay;
	}
	public String getAbsentDayList() {
		return absentDayList;
	}
	public void setAbsentDayList(String absentDayList) {
		this.absentDayList = absentDayList;
	}
	public String getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	public StudentBean getStud() {
		return stud;
	}
	public void setStud(StudentBean stud) {
		this.stud = stud;
	}
	public String getQr1_at_id() {
		return qr1_at_id;
	}
	public void setQr1_at_id(String qr1_at_id) {
		this.qr1_at_id = qr1_at_id;
	}
	public String getQr2_at_id() {
		return qr2_at_id;
	}
	public void setQr2_at_id(String qr2_at_id) {
		this.qr2_at_id = qr2_at_id;
	}
	public String getQr3_at_id() {
		return qr3_at_id;
	}
	public void setQr3_at_id(String qr3_at_id) {
		this.qr3_at_id = qr3_at_id;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getColumn_num() {
		return column_num;
	}
	public void setColumn_num(String column_num) {
		this.column_num = column_num;
	}
	public String getRow_num() {
		return row_num;
	}
	public void setRow_num(String row_num) {
		this.row_num = row_num;
	}
	public String getSheet_num() {
		return sheet_num;
	}
	public void setSheet_num(String sheet_num) {
		this.sheet_num = sheet_num;
	}
	public String getSubject_teacher() {
		return subject_teacher;
	}
	public void setSubject_teacher(String subject_teacher) {
		this.subject_teacher = subject_teacher;
	}
	public double getRank_from() {
		return rank_from;
	}
	public void setRank_from(double rank_from) {
		this.rank_from = rank_from;
	}
	public double getRank_to() {
		return rank_to;
	}
	public void setRank_to(double rank_to) {
		this.rank_to = rank_to;
	}
	public List<Object> getStudMarkList() {
		return studMarkList;
	}
	public void setStudMarkList(List<Object> studMarkList) {
		this.studMarkList = studMarkList;
	}
	public String getStud_count() {
		return stud_count;
	}
	public void setStud_count(String stud_count) {
		this.stud_count = stud_count;
	}
	public List<List<ExamResultBean>> getStudEachQrtTotalRslt() {
		return studEachQrtTotalRslt;
	}
	public void setStudEachQrtTotalRslt(List<List<ExamResultBean>> studEachQrtTotalRslt) {
		this.studEachQrtTotalRslt = studEachQrtTotalRslt;
	}
	public double getFail_rank_from() {
		return fail_rank_from;
	}
	public void setFail_rank_from(double fail_rank_from) {
		this.fail_rank_from = fail_rank_from;
	}
	public double getFail_rank_to() {
		return fail_rank_to;
	}
	public void setFail_rank_to(double fail_rank_to) {
		this.fail_rank_to = fail_rank_to;
	}
	public List<String> getAllExamResult() {
		return allExamResult;
	}
	public void setAllExamResult(List<String> allExamResult) {
		this.allExamResult = allExamResult;
	}
}
