package reportClass;

public class ReportQueries {
	
	public static final String examtype_per_subject = "SELECT a.et_id FROM exam_sub_rel a, subject_class_rel b WHERE a.subcl_id = b.subcl_id and b.cl_id = ? and b.sub_id = ?";
	
	public static final String examtype_per_subjectClassRel = "SELECT a.et_id "
			+ "FROM exam_sub_rel a, subject_class_rel b, exams_type c "
			+ "WHERE a.subcl_id = b.subcl_id and b.cl_id = ? and b.subcl_id = ? and a.rel_status = 'A' and b.rel_status = 'A' and a.et_id = c.et_id "
			+ "ORDER BY et_type desc";
	
	public static final String student_list_per_class = "SELECT d.si_id, f.fname, f.mname, f.gname, f.sex, f.dob "
			+ "FROM stud_registration d, clist_cdetail_rel e, stud_information f "
			+ "WHERE d.clcd_id = e.clcd_id and d.si_id = f.si_id and e.cl_id = ? and e.cd_id = ? and (d.stud_status = 'Active' || d.stud_status = 'Passed' || d.stud_status = 'Failed' || d.stud_status = 'Back' || d.stud_status = 'Repeat') and d.ACADEMIC_YEAR = ?";
	
	public static final String filtered_student_list_per_class = "SELECT d.si_id, f.fname, f.mname, f.gname, f.sex, f.dob FROM stud_registration d, clist_cdetail_rel e, stud_information f WHERE d.clcd_id = e.clcd_id and d.si_id = f.si_id and e.cl_id = ? and e.cd_id = ? and (d.stud_status = 'Active' || d.stud_status = 'Passed' || d.stud_status = 'Failed' || d.stud_status = 'Back' || d.stud_status = 'Repeat') and d.eduoff_reg = 'A' and d.ACADEMIC_YEAR = ? ";
	
	public static final String quarter_mark_list_per_class_subject = "SELECT a.si_id, f.fname, f.mname, f.gname, f.sex, f.dob, a.at_id, a.examsub_id, a.result, b.et_id " +
																		"FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_information f " +
																		"WHERE a.examsub_id = b.exsub_id and c.subcl_id = b.subcl_id and c.cl_id = ? and c.sub_id = (select sub_id from subject_class_rel where subcl_id = ?) and a.at_id = ? and a.si_id in (select d.si_id from stud_registration d, clist_cdetail_rel e where d.clcd_id = e.clcd_id and e.cl_id = c.cl_id and e.cd_id = ? and (d.stud_status = 'Active' || d.stud_status = 'Passed' || d.stud_status = 'Failed' || d.stud_status = 'Back' || d.stud_status = 'Repeat')) and f.si_id = a.si_id and a.fical_year = ? " +
																		"ORDER BY a.si_id, examsub_id";
	
	public static final String filtered_quarter_mark_list_per_class_subject = "SELECT a.si_id, f.fname, f.mname, f.gname, f.sex, f.dob, a.at_id, a.examsub_id, a.result, b.et_id " +
			"FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_information f " +
			"WHERE a.examsub_id = b.exsub_id and c.subcl_id = b.subcl_id and c.cl_id = ? and c.sub_id = (select sub_id from subject_class_rel where subcl_id = ?) and a.at_id = ? and a.si_id in (select d.si_id from stud_registration d, clist_cdetail_rel e where d.clcd_id = e.clcd_id and e.cl_id = c.cl_id and e.cd_id = ? and (d.stud_status = 'Active' || d.stud_status = 'Passed' || d.stud_status = 'Failed' || d.stud_status = 'Back' || d.stud_status = 'Repeat') and d.eduoff_reg = 'A') and f.si_id = a.si_id and a.fical_year = ?" +
			"ORDER BY a.si_id";
	
	public static final String subject_list_per_class = "SELECT a.sub_id, b.sub_name FROM subject_class_rel a, subject_list b WHERE a.sub_id = b.sub_id and a.cl_id = ? and a.rel_status = 'A' ORDER BY a.sub_id";
	
//	public static final String subject_list_per_class_exam_sub_rel = "SELECT b.si_id, e.fname, e.mname, e.gname, e.sex, b.examsub_id, b.at_id, c.subcl_id, c.sub_id, d.sub_name, sum(b.result) as sub_total, c.add_status, c.convert_to_grade, b.fical_year, c.cl_id, g.cd_id, h.stud_status " +
//			"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h " +
//			"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id = ? and b.fical_year = ? and h.academic_year = ? " +
//			"GROUP BY b.si_id, c.sub_id "
//			+ "ORDER BY c.convert_to_grade, d.sub_name";
	
	public static final String subject_list_per_class_exam_sub_rel = "SELECT b.sub_id, b.SUB_NAME, a.subcl_id "
																	+ "FROM subject_class_rel a, subject_list b " +
																	"WHERE a.SUB_ID = b.SUB_ID and a.rel_status = 'A' and SUBCL_ID in (select subcl_id from exam_sub_rel where SUBCL_ID in (select subcl_id from subject_class_rel where CL_ID = ?))"
																	+ "ORDER BY a.subcl_id, a.convert_to_grade, b.sub_name";
	
	public static final String all_subject_student_mark_list = "SELECT b.si_id, e.fname, e.mname, e.gname, e.sex, b.examsub_id, b.at_id, c.sub_id, d.sub_name, format(sum(b.result), 1) as sub_total, c.add_status, c.convert_to_grade, b.fical_year, c.cl_id, g.cd_id, h.stud_status, c.subcl_id " +
																"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h " +
																"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id = ? and b.fical_year = ? and h.academic_year = ? and c.REL_STATUS = 'A' "
																+ "and (h.stud_status = 'Active' || h.stud_status = 'Passed' || h.stud_status = 'Failed' || h.stud_status = 'Back' || h.stud_status = 'Repeat') " +
																"GROUP BY b.si_id, c.sub_id "
																+ "ORDER BY c.subcl_id, c.convert_to_grade, d.sub_name";
	
	public static final String all_subject_filtered_student_mark_list = "SELECT b.si_id, e.fname, e.mname, e.gname, e.sex, b.examsub_id, b.at_id, c.sub_id, d.sub_name, sum(b.result) as sub_total, c.add_status, c.convert_to_grade, b.fical_year, c.cl_id, g.cd_id, h.stud_status " +
			"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h " +
			"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id = ? and b.fical_year = ? and h.academic_year = ? and h.eduoff_reg = 'A' and c.REL_STATUS = 'A' "
			+ "and (h.stud_status = 'Active' || h.stud_status = 'Passed' || h.stud_status = 'Failed' || h.stud_status = 'Back' || h.stud_status = 'Repeat') " +
			"GROUP BY b.si_id, c.sub_id "
			+ "ORDER BY c.convert_to_grade, d.sub_name";
	
	public static final String getTopNsStudentPerClass = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, b.fical_year, c.cl_id, g.cd_id, h.stud_status, g.cd_name, i.CLASS_NAME " +
			"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h, class_list i " +
			"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and i.CL_ID = c.CL_ID and b.si_id = e.si_id and c.add_status = 'Yes' and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id in (?) and b.fical_year = ? and h.STUD_STATUS in ('Active', 'Passed') " +
			"GROUP BY b.si_id ORDER BY total desc limit ?";
	
	public static final String getOverAllTopRankedStudent = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, sum(b.result)/(select count(subcl_id) from subject_class_rel where cl_id = c.cl_id and add_status = 'Yes' ) as average, b.fical_year, c.cl_id, g.cd_id, h.stud_status, g.cd_name, i.CLASS_NAME " +
			"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h, class_list i " +
			"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and i.CL_ID = c.CL_ID and b.si_id = e.si_id and c.add_status = 'Yes' and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id in (?) and b.at_id in (?) and b.fical_year = ? and h.STUD_STATUS in ('Active', 'Passed') " +
			"GROUP BY b.si_id ORDER BY total desc LIMIT ?";
			
	public static final String getTopTwentyRankedStudent = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, sum(b.result)/(select count(subcl_id) from subject_class_rel where cl_id = c.cl_id and add_status = 'Yes' ) as average, b.fical_year, c.cl_id, g.cd_id, h.stud_status, g.cd_name, i.class_name " +
			"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h, class_list i " +
			"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.add_status = 'Yes' and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and b.at_id = ? and b.fical_year = ? and f.cl_id = i.cl_id and h.STUD_STATUS = 'Active'" +
			"GROUP BY b.si_id ORDER BY average desc limit 20";
				
	public static final String getTotalSubjectMarkPerClass = "SELECT b.si_id, e.fname, e.mname, e.gname, e.sex, b.at_id, c.sub_id, sum(b.result) as sub_total, c.add_status, c.convert_to_grade, b.fical_year, c.cl_id, g.cd_id, h.stud_status " + 
			"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h " +
			"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and b.at_id = ? and b.fical_year = ? and h.academic_year = ? " +
			"GROUP BY b.si_id, c.sub_id";	
	
	public static final String getTotalSubjectMarkPerClassFilteredStud = "SELECT b.si_id, e.fname, e.mname, e.gname, e.sex, b.at_id, c.sub_id, sum(b.result) as sub_total, c.add_status, c.convert_to_grade, b.fical_year, c.cl_id, g.cd_id, h.stud_status " + 
			"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h " +
			"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and b.at_id = ? and b.fical_year = ? and h.academic_year = ? and h.eduoff_reg = 'A'" +
			"GROUP BY b.si_id, c.sub_id";
	
	public static final String getNumberOfStudentPerSex = "SELECT count(a.si_id) as total " + 
			"FROM stud_registration a,  clist_cdetail_rel b, stud_information c " +
			"WHERE a.clcd_id = b.clcd_id and b.cl_id = ? and (a.academic_year = ? and (a.stud_status = 'Active' || a.stud_status = 'Passed' || a.stud_status = 'Failed' || a.stud_status = 'Back' || a.stud_status = 'Repeat')) and a.si_id = c.si_id and c.sex = ?";

	public static final String getNumberOfStudentPerSexFiltered = "SELECT count(a.si_id) as total " + 
			"FROM stud_registration a,  clist_cdetail_rel b, stud_information c " +
			"WHERE a.clcd_id = b.clcd_id and b.cl_id = ? and (a.academic_year = ? and (a.stud_status = 'Active' || a.stud_status = 'Passed' || a.stud_status = 'Failed' || a.stud_status = 'Back' || a.stud_status = 'Repeat')) and a.si_id = c.si_id and c.sex = ? and a.eduoff_reg = 'A'";

	public static final String getDailyPaymentCollection = "SELECT a.si_id, b.fname, b.mname, b.gname, sum(total_payment) total_payment, fm_receipt_no, d.pt_name, g.class_name, h.cd_name, a.payment_date " +
			"FROM payment_stud_rel a, stud_information b, payment_class_rel c, payment_type d, stud_registration e, clist_cdetail_rel f, class_list g, class_detail h " +
			"WHERE payment_date between ? and ? and a.si_id = b.si_id and a.pc_id = c.pc_id and c.pt_id = d.pt_id and e.si_id = a.si_id and e.academic_year = ? and e.clcd_id = f.clcd_id and f.cl_id = g.cl_id and f.cd_id = h.cd_id " +
			"GROUP BY si_id";
	
	public static final String getMonthlyPaymentCollection = "SELECT a.si_id, b.fname, b.mname, b.gname, sum(total_payment) total_payment, fm_receipt_no, d.pt_name, g.class_name, h.cd_name, a.payment_date " +
			"FROM payment_stud_rel a, stud_information b, payment_class_rel c, payment_type d, stud_registration e, clist_cdetail_rel f, class_list g, class_detail h " +
			"WHERE month_id between ? and ? and a.si_id = b.si_id and a.pc_id = c.pc_id and c.pt_id = d.pt_id and e.si_id = a.si_id and e.academic_year = ? and e.clcd_id = f.clcd_id and f.cl_id = g.cl_id and f.cd_id = h.cd_id " +
			"GROUP BY si_id";
	
	public static final String getUnpaidStudentList = "SELECT d.fname, d.mname, d.gname, f.cl_id, f.cd_id, g.class_name, h.cd_name " +
			"FROM stud_information d, stud_registration e, clist_cdetail_rel f, class_list g, class_detail h " +
			"WHERE d.si_id not in (select a.si_id from payment_stud_rel a, stud_registration b, clist_cdetail_rel c where a.si_id = b.si_id and c.cl_id = ? and a.month_id between ? and ? and a.ac_year = ? and b.academic_year = ? and c.clcd_id = b.clcd_id) and d.si_id = e.si_id and e.clcd_id = f.clcd_id and f.cl_id = g.cl_id and f.cd_id = h.cd_id and f.cl_id = ? and e.academic_year = ? and (e.stud_status = 'Active' || e.stud_status = 'Passed' || e.stud_status = 'Failed' || e.stud_status = 'Back' || e.stud_status = 'Repeat') " +
			"ORDER BY f.cl_id, f.cd_id, d.fname, d.mname, d.gname";
	
	public static final String getEmailToList = "SELECT osm_email_address FROM other_school_member WHERE osm_position = 'owner'";

	
	
	public static final String getAnnualTotalMarkPerGrade = "SELECT sublist.sub_id, rsltlist.sub_total, sublist.fname, sublist.mname, sublist.gname, sublist.sex, sublist.dob, sublist.si_id, sublist.SUB_NAME, sublist.CONVERT_TO_GRADE, sublist.ADD_STATUS, sublist.at_id, sublist.SUBCL_ID"
															+ " FROM"
															+ " (SELECT a.sub_id, a.sub_name, a.sub_status, b.subcl_id, b.add_status, b.convert_to_grade, d.si_id, f.at_id, g.fname, g.mname, g.gname, g.sex, g.dob"
															+ " FROM subject_list a, subject_class_rel b, stud_registration d, clist_cdetail_rel e, annual_terms f, stud_information g"
															+ " WHERE a.sub_id = b.sub_id and b.CL_ID = e.CL_ID and sub_status = 'A' and d.si_id = g.si_id and b.rel_status = 'A' and d.CLCD_ID = e.CLCD_ID and g.si_id = ? and e.cl_id = ? and e.CD_ID = ? and d.ACADEMIC_YEAR = ?) as sublist"
															+ " LEFT JOIN"
															+ " (SELECT format(sum(a.result), 1) as sub_total, c.SUB_ID, a.si_id, a.at_id"
															+ " FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_information d, stud_registration e, subject_list f"
															+ " WHERE d.si_id = ? and e.CLCD_ID = (select CLCD_ID from clist_cdetail_rel where cl_id = ? and cd_id = ?) and a.fical_year = ? and e.ACADEMIC_YEAR = ? and f.SUB_ID = c.SUB_ID and d.si_id = e.si_id and c.subcl_id = b.subcl_id and a.si_id = d.si_id and b.exsub_id = a.examsub_id"
															+ " GROUP BY a.si_id, c.SUB_ID, a.at_id) as rsltlist"
															+ " USING (si_id, sub_id, at_id)"
															+ " ORDER BY sublist.CONVERT_TO_GRADE, sublist.SUB_NAME, sublist.at_id";
	
	
}
