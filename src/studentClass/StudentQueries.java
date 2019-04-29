package studentClass;

public class StudentQueries {
	
	public static final String saveStudPhotoInfo = "INSERT INTO student_photo_information(si_id, stud_photo, spi_status) VALUES(?, ?, 'Active')";
	
	public static final String updateStudentPhotoInfo = "UPDATE student_photo_information SET stud_photo = ? WHERE si_id = ?";
	
	public static final String getStudentPhoto = "SELECT stud_photo FROM student_photo_information WHERE si_id = ?";
	
	public static final String validateStudIdNumber = "SELECT id_no FROM stud_information WHERE id_no = ?";
	
	public static final String getLoggedinUserUserType = "SELECT a.ut_id FROM `uaccount_utype_rel` a, user_account b WHERE a.ua_id = b.ua_id and a.uaut_status = 'A' and b.user_name = ?";
	
	// must be revisited - must be accessed by one user only
	public static final String getLastSiId = "SELECT max(si_id) as si_id FROM stud_information";
	
	public static final String getUserRoleList = "SELECT c.m_id, c.m_name FROM user_type a, utype_module_rel b, u_module c WHERE a.ut_id = b.ut_id and b.m_id = c.m_id and a.ut_id = ? and b.rel_status = 'A'";
	
	public static final String allStudentList = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, a.photo_path, a.photo_name FROM stud_information a, stud_registration b WHERE a.si_id = b.si_id and b.academic_year = ? and (b.stud_status = 'Active' || b.stud_status = 'Passed' || b.stud_status = 'Failed' || b.stud_status = 'Back' || b.stud_status = 'Repeat')";
	
	public static final String getStudentPerfile = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, a.photo_path, a.photo_name "
			+ "FROM stud_information a WHERE a.si_id = ?";
	
	public static final String getStudentPerfileByAcademicYear = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, a.photo_path, a.photo_name, b.stud_status "
			+ "FROM stud_information a, stud_registration b "
			+ "WHERE a.si_id = ? and a.si_id = b.si_id and b.academic_year = ?";
	
	public static final String allInactiveStudentList = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, a.photo_path, a.photo_name "
			+ "FROM stud_information a, stud_registration b "
			+ "WHERE a.si_id = b.si_id and b.academic_year = ? and a.si_status = 'I' and (b.stud_status <> 'Active' && b.stud_status <> 'Passed' && b.stud_status <> 'Failed' && b.stud_status <> 'Back' && b.stud_status <> 'Repeat')";
	
	public static final String allCandidateStudentList = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, a.photo_path, a.photo_name "
			+ "FROM stud_information a "
			+ "WHERE a.si_id not in (select si_id from stud_registration)";
	
	public static final String deleteCandidateStudent = "DELETE FROM stud_information WHERE si_id = ?";
	
	public static final String allDropoutStudentList = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, a.photo_path, a.photo_name FROM stud_information a, stud_registration b WHERE a.si_id = b.si_id and b.stud_status = 'DropOut'";
	
	
	/*------------ Activate Drop out students----------------*/
	public static final String activateDropoutStudentInfo = "UPDATE stud_information SET si_status = 'A' WHERE si_id = ?";
	
	public static final String activateCurrentYearDropoutStudentReg = "UPDATE stud_registration SET stud_status = 'Active' WHERE si_id = ? and academic_year = ?";
	
	public static final String activatePastYearDropoutStudentReg = "UPDATE stud_registration SET stud_status = 'Active' WHERE si_id = ? and academic_year = ?";	
	/*-------------------------------------------------------*/
	
	
	public static final String allClassList = "select cl_id, class_name, class_status from class_list where class_status = 'Active'";
	
	public static final String StudentListPerGrade = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, a.photo_path, a.photo_name, b.cd_id " +
													 "FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
													 "WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.cl_id = ? and c.academic_year = ? and (c.stud_status = 'Active' || c.stud_status = 'Passed' || c.stud_status = 'Failed' || c.stud_status = 'Back' || c.stud_status = 'Repeat')";
	
	public static final String classDetailList = "SELECT a.cd_id, a.cd_name, b.class_name " +
												"FROM class_detail a, class_list b, clist_cdetail_rel c " +
												"WHERE a.cd_id = c.cd_id and b.cl_id = c.cl_id and b.cl_id = ? and c.rel_status = 'Active' " + 
												"and c.cd_id not in (SELECT cd_id FROM tech_hroom_assignment WHERE cl_id = ? and academic_year = ? " +
												"and ti_id = (SELECT ti_id FROM user_account WHERE user_name = ? and ua_status = 'A' and ti_id <> 'N'))";
	
	public static final String classDetailTeacherRelatedList = "SELECT a.cd_id, a.cd_name, b.class_name "
			+ "FROM class_detail a, class_list b, clist_cdetail_rel c "
			+ "WHERE a.cd_id = c.cd_id and b.cl_id = c.cl_id and b.cl_id = ? and c.rel_status = 'Active'  and c.cd_id in (SELECT cd_id FROM tech_hroom_assignment WHERE cl_id = ? and ti_id = ? and thra_status = 'A' and academic_year = ?)";
	
	public static final String studentListPerClassDetail = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, c.stud_status, a.photo_path, a.photo_name " +
			 												"FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
			 												"WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and c.academic_year = ? and (c.stud_status = 'Active' || c.stud_status = 'Passed' || c.stud_status = 'Failed' || c.stud_status = 'Back' || c.stud_status = 'Repeat') and a.si_status = 'A' "
			 												+ "ORDER BY a.fname, a.mname, a.gname";
	
	public static final String studentListPerClassDetailForAttendance = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, c.stud_status, a.photo_path, a.photo_name " +
				"FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
				"WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and c.academic_year = ? and (c.stud_status = 'Active' || c.stud_status = 'Passed' || c.stud_status = 'Failed' || c.stud_status = 'Back' || c.stud_status = 'Repeat') and a.si_status = 'A' "
				+ "ORDER BY a.fname, a.mname, a.gname";
	
	public static final String studentListPerClassDetailForAttendanceFiltered = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, c.stud_status, a.photo_path, a.photo_name " +
			"FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
			"WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and c.academic_year = ? and (c.stud_status = 'Active' || c.stud_status = 'Passed' || c.stud_status = 'Failed' || c.stud_status = 'Back' || c.stud_status = 'Repeat') and a.si_status = 'A' and c.eduoff_reg = 'A'"
			+ "ORDER BY a.fname, a.mname, a.gname";
	
	public static final String countStudentListPerClassDetail = "SELECT COUNT(a.si_id) count " +
			"FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
			"WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.clcd_id = ? and c.academic_year = ? and (c.stud_status = 'Active' || c.stud_status = 'Passed' || c.stud_status = 'Failed' || c.stud_status = 'Back' || c.stud_status = 'Repeat' || c.stud_status = 'DropOut') ";

	
	public static final String studentListPerClassDetailForCertificate = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, c.stud_status, a.photo_path, a.photo_name " +
															"FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
															"WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and c.academic_year = ? and (c.stud_status = 'Active' || c.stud_status = 'Passed' || c.stud_status = 'Failed' || c.stud_status = 'Back' || c.stud_status = 'Repeat') "
															+ "ORDER BY a.fname, a.mname, a.gname";

	public static final String studentContactInfoListPerClassDetail = "SELECT a.si_id, a.fname, a.mname, a.gname, a.sex, b.cont_name, b.relationship, b.mob_no "
				+"FROM stud_registration c, stud_information a LEFT JOIN stud_emergency_contact b ON a.si_id = b.si_id "
				+"WHERE b.sec_status = 'A' and a.si_status = 'A' and a.si_id = c.si_id and c.clcd_id = (select clcd_id from clist_cdetail_rel where cl_id = ? and cd_id = ?) and c.academic_year = ?"
				+"ORDER BY a.fname, a.mname, a.gname";
	
	public static final String studentInfoWithContactInfoPerClassDetail = "SELECT a.si_id, a.fname, a.MNAME, a.GNAME, a.MOTHER_NAME, a.SEX, a.DOB, a.POB, a.NATIONALITY, group_concat(c.CONT_NAME) cont_name, group_concat(c.RELATIONSHIP) relationship, group_concat(c.MOB_NO) mob_no "
				+ "FROM sms_everest.stud_information a, stud_registration b, stud_emergency_contact c "
				+ "WHERE a.SI_ID = b.SI_ID and a.SI_ID = c.SI_ID and b.CLCD_ID = (select clcd_id from clist_cdetail_rel where CL_ID = ? and cd_id = ?) and b.ACADEMIC_YEAR = ? and b.STUD_STATUS <> 'DropOut' "
				+ "GROUP BY fname, mname, gname";
	
	public static final String studentWithoutPhotoContactInfoListPerClassDetail = "SELECT a.si_id, a.fname, a.mname, a.gname, a.sex, b.cont_name, b.relationship, b.mob_no "
			+"FROM stud_registration c, stud_information a LEFT JOIN stud_emergency_contact b ON a.si_id = b.si_id "
			+"WHERE a.si_id = c.si_id and c.clcd_id = (select clcd_id from clist_cdetail_rel where cl_id = ? and cd_id = ?) and c.academic_year = ? and a.si_id not in (select si_id from student_photo_information)";
	
	public static final String studentWithoutContactInfoListPerClassDetail = "SELECT a.si_id, a.fname, a.mname, a.gname, a.sex, '' as cont_name, '' as relationship, '' as mob_no "
			+"FROM stud_registration c, stud_information a "
			+"WHERE a.si_id = c.si_id and c.clcd_id = (select clcd_id from clist_cdetail_rel where cl_id = ? and cd_id = ?) and c.academic_year = ? and a.si_id not in (select si_id from stud_emergency_contact)";
		
	public static final String getStudentDropOutStatus = "SELECT a.si_status, b.stud_status " +
			                                             "FROM stud_information a, stud_registration b " +
			                                             "WHERE a.si_id = b.si_id and a.si_id = ? and b.academic_year = ?";
	
	public static final String updateStudentRegistrationInfo = "UPDATE stud_registration SET stud_status = ? WHERE si_id = ? and academic_year = ? ";
	
	public static final String updateStudentPersonalInfo = "UPDATE stud_information SET si_status = 'I' WHERE si_id = ?";
	
	public static final String updateStudInfo = "UPDATE stud_information SET fname = ?, mname = ?, gname = ?, mother_name = ?, sex = ?, dob = ?, pob = ?, nationality = ?, si_status = ?, id_no = ?, update_by = ?, update_date = ? where si_id = ?";
	
	public static final String insertStudInfo = "INSERT INTO stud_information(fname, mname, gname, mother_name, sex, dob, pob, nationality, id_no, photo_name, photo_path, bro_sis_number, special_attention_info, si_status, create_by, create_date) "
												+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'A', ?, ?)";
	
	public static final String checkStudInfo = "SELECT fname, mname, gname, mother_name, sex, dob, pob, nationality, id_no, photo_name, photo_path, si_status, create_by, create_date, si_id "
			+ "FROM stud_information "
			+ "WHERE fname = ? and mname = ? and gname = ? and mother_name = ? and sex = ? ";
	
	public static final String updateStudPhotoInfo = "UPDATE stud_information SET photo_name = ?, photo_path = ? WHERE si_id = ?";
	
//	public static final String StudentListForExamRslt = "SELECT a.si_id, a.fname, a.mname, a.gname,"
//													  +	" (SELECT result FROM exam_result d WHERE d.si_id = a.si_id and a.examsub_id = ?) as rslt,"
//													  + " (SELECT total_mark FROM exam_sub_rel WHERE exsub_id = ?)  as total_mark,"
//													  + " (SELECT pass_mark FROM exam_sub_rel WHERE exsub_id = ?) as pass_mark"
//													  + " FROM stud_information a, clist_cdetail_rel b, stud_registration c"
//													  + " WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.cl_id = ? and a.si_status = 'A' and (SELECT result FROM exam_result d WHERE d.si_id = a.si_id and examsub_id = ?) is null and (SELECT stud_status FROM stud_registration e WHERE e.clcd_id = b.clcd_id and e.si_id = a.si_id and reg_status = 'A') = 'Active' and c.academic_year = ?";
	
	public static final String StudentListForExamRslt = "SELECT b.si_id, b.fname, b.mname, b.gname, f.total_mark, f.pass_mark " +
														"FROM stud_information b, stud_registration c, clist_cdetail_rel d, subject_class_rel e, exam_sub_rel f " +
														"WHERE b.si_id = c.si_id and c.clcd_id = (select clcd_id from clist_cdetail_rel where cl_id = ? and cd_id = ?) and academic_year = ? and (c.stud_status = 'Active' || c.stud_status = 'Passed' || c.stud_status = 'Failed' || c.stud_status = 'Back' || c.stud_status = 'Repeat') and c.clcd_id = d.clcd_id and d.cl_id = e.cl_id and e.subcl_id = f.subcl_id and f.exsub_id = ? and " +
														"b.si_id not in ( SELECT a.si_id FROM exam_result a, stud_information b, stud_registration c WHERE a.si_id = b.si_id and a.at_id = ? and a.fical_year = ? and a.si_id = c.si_id and c.clcd_id = (select clcd_id from clist_cdetail_rel where cl_id = ? and cd_id = ?) and a.examsub_id = ?)";
	
	public static final String getTotalNumberOfStudent = "SELECT count(c.sr_id) as total_no FROM stud_registration c WHERE c.academic_year = ? and (c.stud_status = 'Active' || c.stud_status = 'Passed' || c.stud_status = 'Failed' || c.stud_status = 'Back' || c.stud_status = 'Repeat') ";
	
	public static final String getNoOfStudPerGrd = "SELECT count(si_id) as total "+ 
													"FROM stud_registration c,  clist_cdetail_rel b " +
													"WHERE c.clcd_id = b.clcd_id and b.cl_id = ? and (c.academic_year = ? and (c.stud_status = 'Active' || c.stud_status = 'Passed' || c.stud_status = 'Failed' || c.stud_status = 'Back' || c.stud_status = 'Repeat') )";
	
	public static final String getStudentNoPerSex = "SELECT count(a.si_id) as total " + 
													"FROM stud_registration a, stud_information c " +
													"WHERE a.academic_year = ? and (a.stud_status = 'Active' || a.stud_status = 'Passed' || a.stud_status = 'Failed' || a.stud_status = 'Back' || a.stud_status = 'Repeat') and a.si_id = c.si_id and c.sex = ?";
	
	public static final String getStudentPreviousClassDetailId = "SELECT b.CD_ID FROM stud_registration a, clist_cdetail_rel b "
			+ "WHERE a.CLCD_ID = b.CLCD_ID and a.SI_ID = ? and b.CL_ID = ? and a.ACADEMIC_YEAR = ?";
	
	///*** report sql generated from the view
	public static final String getCurrentYearStudentNoPerSex = "SELECT class_name, male, female FROM v_current_year_students_num_per_grade";
	
	public static final String getStudentNoPerSexAndGrade = "SELECT count(a.si_id) as total " + 
													"FROM stud_registration a,  clist_cdetail_rel b, stud_information c " +
													"WHERE a.clcd_id = b.clcd_id and b.cl_id = ? and (a.academic_year = ? and (a.stud_status = 'Active' || a.stud_status = 'Passed' || a.stud_status = 'Failed' || a.stud_status = 'Back' || a.stud_status = 'Repeat')) and a.si_id = c.si_id and c.sex = ?";

	//*** report sql generated from the view
	public static final String getStudentNumPerYear = "SELECT academic_year, male, female FROM v_student_num_per_year_sex ORDER BY academic_year desc";
	
	public static final String getStudentNumPerClassSex = "SELECT class_name, cl_id, male, female FROM v_student_num_per_class_sex_and_year WHERE academic_year = ? ORDER BY cl_id";
	
	public static final String getStudentNumberPerCDetailAndSex = "SELECT class_name, cd_name, male, female "
			+ "FROM v_student_num_per_class_cdetail_sex_and_year "
			+ "WHERE cl_id = ? and academic_year = ? ";
	
	
	public static final String getNumOfStudPerClassDetail = "SELECT count(si_id) as total "+ 
													"FROM stud_registration a,  clist_cdetail_rel b " +
													"WHERE a.clcd_id = b.clcd_id and b.cl_id = ? and b.cd_id = ? and (a.academic_year = ? and (a.stud_status = 'Active' || a.stud_status = 'Passed' || a.stud_status = 'Failed' || a.stud_status = 'Back' || a.stud_status = 'Repeat'))";
	
	
	
}
