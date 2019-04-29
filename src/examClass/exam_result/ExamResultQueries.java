package examClass.exam_result;

public class ExamResultQueries {
	
	public static final String selectMaxRslt = "";
	
	public static final String getQuarterName = "SELECT at_id, at_name FROM annual_terms WHERE at_id = ?";
	
	public static final String insertStudExamRslt = "INSERT INTO exam_result (si_id, examsub_id, result, fical_year, at_id, crt_by, crt_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public static final String updateStudExamRslt = "UPDATE exam_result SET result = ?, udt_by = ?, udt_date = ? WHERE  si_id = ? and examsub_id = ? and fical_year = ? and at_id = ?";
	
	public static final String selectStudTotalExamRslt = "SELECT sum(a.result) as total_mark, b.stud_status FROM exam_result a, stud_registration b, exam_sub_rel c, subject_class_rel d WHERE a.si_id = b.si_id and a.si_id = ? and a.fical_year = ? and a.at_id = ? and b.stud_status = 'Active' and a.examsub_id = c.exsub_id and c.subcl_id = d.subcl_id and d.add_status = 'Yes'";
	
	public static final String numOfSubPerGrade = "SELECT count(sub_id) as total FROM subject_class_rel WHERE cl_id = ? and rel_status = 'A' and add_status = 'Yes'";
	
	public static final String getAnnualTermList = "SELECT at_id, at_name FROM annual_terms WHERE at_status = 'A' and at_name <> 'Full Year'";
	
	public static final String getAnnualTermListIncludingFullYear = "SELECT at_id, at_name FROM annual_terms WHERE at_status = 'A'";
	
	public static final String getStudFullYearResult = 	"SELECT (SELECT sum(a.result) FROM exam_result a, stud_registration b WHERE a.si_id = b.si_id and a.si_id = c.si_id and a.fical_year = ? and a.at_id = 1 and b.stud_status = 'Active') as first_term, " +
																"(SELECT sum(a.result) FROM exam_result a, stud_registration b WHERE a.si_id = b.si_id and a.si_id = c.si_id and a.fical_year = ? and a.at_id = 2) as second_term " +
														"FROM stud_information c " +
														"WHERE c.si_id = ?";
	
	public static final String getCertificateRsltView = "SELECT (SELECT sum(c.result) FROM subject_class_rel a, exam_sub_rel b, exam_result c WHERE a.subcl_id = b.subcl_id and b.exsub_id = c.examsub_id and c.at_id = 1 and c.fical_year = ? and c.si_id = d.si_id and a.sub_id = ? and a.cl_id = ?) as fsem_subtotal, " +
															"(SELECT sum(g.result) FROM subject_class_rel e, exam_sub_rel f, exam_result g WHERE e.subcl_id = f.subcl_id and f.exsub_id = g.examsub_id and g.at_id = 2 and g.fical_year = ? and g.si_id = d.si_id and e.sub_id = ? and e.cl_id = ?) as ssem_subtotal " +
														"FROM stud_information d " +
														"WHERE d.si_id = ?";
	
	public static final String getCertRsltView = "SELECT fsem.fsem_subtotal, ssem.ssem_subtotal"
												+ " FROM (SELECT sum(c.result) fsem_subtotal FROM subject_class_rel a, exam_sub_rel b, exam_result c WHERE a.subcl_id = b.subcl_id and b.exsub_id = c.examsub_id and c.at_id = 1 and c.fical_year = ? and a.sub_id = ? and a.cl_id = ? and c.si_id = ?) as fsem,"
												+ " (SELECT sum(g.result) ssem_subtotal FROM subject_class_rel e, exam_sub_rel f, exam_result g WHERE e.subcl_id = f.subcl_id and f.exsub_id = g.examsub_id and g.at_id = 2 and g.fical_year = ? and e.sub_id = ? and e.cl_id = ? and g.si_id = ?) as ssem";
	
	public static final String getSubListPerClass = "SELECT a.sub_id, a.sub_name, a.sub_status, b.subcl_id, b.add_status, b.convert_to_grade FROM subject_list a, subject_class_rel b WHERE a.sub_id = b.sub_id and b.cl_id = ? and sub_status = 'A' and b.rel_status = 'A' ORDER BY b.convert_to_grade, a.sub_name ";
	
	public static final String getSubjectTotal = "SELECT sum(a.result) as sub_total, (select sum(d.total_mark) from exam_sub_rel d where d.subcl_id = b.subcl_id and d.rel_status = 'A') as total, " +
													"(select sum(d.pass_mark) from exam_sub_rel d where d.subcl_id = b.subcl_id and d.rel_status = 'A') as pass_total  " +
													"FROM exam_result a, exam_sub_rel b, subject_class_rel c " +
													"WHERE c.sub_id = ? and c.cl_id = ? and a.si_id = ? and c.subcl_id = b.subcl_id and b.exsub_id = a.examsub_id and a.fical_year = ? and a.at_id = ?";
	
	public static final String getSubjectTotalWithStudName = "SELECT sum(a.result) as sub_total, d.fname, d.mname, d.gname, d.sex, d.dob  " +
			"FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_information d " +
			"WHERE c.sub_id = ? and c.cl_id = ? and a.si_id = ? and c.subcl_id = b.subcl_id and a.si_id = d.si_id and b.exsub_id = a.examsub_id and a.fical_year = ? and a.at_id = ?";

//	public static final String getAllSubjectTotalWithAllStudName = "SELECT sum(a.result) as sub_total, d.fname, d.mname, d.gname, d.sex, d.dob , c.SUB_ID, a.si_id, f.SUB_NAME, c.CONVERT_TO_GRADE, c.ADD_STATUS, a.at_id "
//+ " FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_information d, stud_registration e, subject_list f"
//+ " WHERE e.CLCD_ID = (select CLCD_ID from clist_cdetail_rel where cl_id = ? and cd_id = ?) and a.fical_year = ? and f.SUB_ID = c.SUB_ID and d.si_id = e.si_id and e.ACADEMIC_YEAR = ? and c.subcl_id = b.subcl_id and a.si_id = d.si_id and b.exsub_id = a.examsub_id"
//+ " group by a.si_id, c.SUB_ID, a.at_id"
//+ " order by a.si_id, a.at_id, c.CONVERT_TO_GRADE, f.SUB_NAME";
	
	public static final String getAllSubjectTotalWithAllStudName = "SELECT sublist.sub_id, rsltlist.sub_total, sublist.fname, sublist.mname, sublist.gname, sublist.sex, sublist.dob, sublist.si_id, sublist.SUB_NAME, sublist.CONVERT_TO_GRADE, sublist.ADD_STATUS, sublist.at_id, sublist.SUBCL_ID "
																	+ "FROM "
																	+ "(SELECT a.sub_id, a.sub_name, a.sub_status, b.subcl_id, b.add_status, b.convert_to_grade, d.si_id, f.at_id, g.fname, g.mname, g.gname, g.sex, g.dob "
																		+ "FROM subject_list a, subject_class_rel b, stud_registration d, clist_cdetail_rel e, annual_terms f, stud_information g "
																		+ "WHERE a.sub_id = b.sub_id and e.cl_id = ? and e.CD_ID = ? and b.SUBCL_ID in (select subcl_id from exam_sub_rel where SUBCL_ID in (select subcl_id from subject_class_rel where CL_ID = ?)) and b.CL_ID = e.CL_ID and sub_status = 'A' and d.si_id = g.si_id and d.CLCD_ID = e.CLCD_ID and d.ACADEMIC_YEAR = ?) as sublist "
																	+ "LEFT JOIN "
																		+ "(SELECT sum(a.result) as sub_total, c.SUB_ID, a.si_id, a.at_id "
																		+ "FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_information d, stud_registration e, subject_list f "
																		+ "WHERE e.CLCD_ID = (select CLCD_ID from clist_cdetail_rel where cl_id = ? and cd_id = ?) and a.fical_year = ? and f.SUB_ID = c.SUB_ID and d.si_id = e.si_id and e.ACADEMIC_YEAR = ? and c.subcl_id = b.subcl_id and a.si_id = d.si_id and b.exsub_id = a.examsub_id "
																		+ "group by a.si_id, c.SUB_ID, a.at_id) as rsltlist "
																	+ "USING (si_id, sub_id, at_id) "
																	+ "ORDER BY sublist.fname, sublist.mname, sublist.gname, sublist.at_id, sublist.CONVERT_TO_GRADE, sublist.SUB_NAME";
	
	///*** ---------- Revised Certificate Processing Query ---------- ***///
	public static final String getAllStudentsSubjectTotalPerEachQuarterForCertificate = "select * from "
																						+ "(SELECT distinct g.SI_ID, f.at_id, c.SUBCL_ID, f.at_name, "
																						+ "e.fname, e.mname, e.gname, e.MOTHER_NAME, e.SEX, e.DOB, e.POB, e.NATIONALITY, e.ID_NO, e.PHOTO_PATH, e.PHOTO_NAME, e.SI_STATUS, g.STUD_STATUS, "
																						+ "d.SUB_ID, d.SUB_NAME, c.ADD_STATUS, c.CONVERT_TO_GRADE, c.cl_id"
																						+ " FROM subject_class_rel c, subject_list d, annual_terms f, stud_registration g, exam_result b, exam_sub_rel a, stud_information e"
																						+ " WHERE a.exsub_id = b.examsub_id and b.si_id = g.si_id and c.SUB_ID = d.SUB_ID and g.SI_ID = e.SI_ID and a.SUBCL_ID = c.SUBCL_ID and"
																							+ " c.CL_ID = ? and g.CLCD_ID = (select l.CLCD_ID from clist_cdetail_rel l where l.CL_ID = ? and l.CD_ID = ?) and g.ACADEMIC_YEAR = ? ) AA left join "
																						+ "(SELECT i.si_id, i.at_id, h.subcl_id, sum(i.result) subject_total, i.examsub_id, i.fical_year"
																						+ " FROM exam_result i, exam_sub_rel h, subject_class_rel j, stud_registration m"
																						+ " WHERE h.exsub_id = i.examsub_id and h.SUBCL_ID = j.SUBCL_ID and i.si_id = m.SI_ID"
																							+ " and m.CLCD_ID = (select l.CLCD_ID from clist_cdetail_rel l where l.CL_ID = ? and l.CD_ID = ?) and i.fical_year = ? and m.ACADEMIC_YEAR = ?"
																						+ " GROUP BY j.subcl_id, i.si_id, i.at_id) BB using (si_id, at_id, subcl_id)"
																						+ " ORDER BY fname, mname, gname, at_id, CONVERT_TO_GRADE, SUB_NAME";
	
	public static final String getAllStudentsSubjectTotalPerEachQuarterForCertificate2 = "select * from "
			+ " (SELECT a.si_id, a.fname, a.mname, a.gname, a.MOTHER_NAME, a.SEX, a.DOB, a.POB, a.NATIONALITY, a.ID_NO, a.PHOTO_PATH, a.PHOTO_NAME, a.SI_STATUS, b.STUD_STATUS, "
			+ " c.at_id, c.at_name, e.SUBCL_ID, e.ADD_STATUS, e.CONVERT_TO_GRADE, f.cl_id, d.SUB_ID, d.SUB_NAME "
            + " FROM stud_information a, stud_registration b, annual_terms c, subject_list d, subject_class_rel e, clist_cdetail_rel f "
            + " WHERE a.si_id = b.si_id and b.CLCD_ID = (select l.CLCD_ID from clist_cdetail_rel l where l.CL_ID = ? and l.CD_ID = ?) and " 
            + " b.ACADEMIC_YEAR = ? and a.SI_STATUS <> 'I' and b.STUD_STATUS <> 'DropOut' and d.SUB_ID = e.SUB_ID and e.CL_ID = ? and f.CLCD_ID = b.CLCD_ID "
			+ " ) AA left join ( "
			 + " SELECT i.si_id, i.at_id, h.subcl_id, sum(i.result) subject_total, i.examsub_id, i.fical_year "
			 + " FROM exam_result i, exam_sub_rel h, subject_class_rel j, stud_registration m "
			 + " WHERE h.exsub_id = i.examsub_id and h.SUBCL_ID = j.SUBCL_ID and i.si_id = m.SI_ID "
			 + " and m.CLCD_ID = (select l.CLCD_ID from clist_cdetail_rel l where l.CL_ID = ? and l.CD_ID = ?) and " 
             + " i.fical_year = ? and m.ACADEMIC_YEAR = ? and m.STUD_STATUS <> 'DropOut' "
			 + " GROUP BY j.subcl_id, i.si_id, i.at_id "
			+ " ) BB using (si_id, at_id, subcl_id) "
			+ " ORDER BY fname, mname, gname, at_id, CONVERT_TO_GRADE, SUB_NAME";
	
	public static final String getStudentTotalRsltPerEachQuarter = "SELECT rsltlist.qrt_total, sublist.si_id, sublist.fname, sublist.mname, sublist.gname, sublist.at_id, rsltlist.stud_status " 
																	+ "FROM "
																	+ "(SELECT distinct d.si_id, f.at_id, g.fname, g.mname, g.gname, g.sex, g.dob "
																	+ "FROM subject_list a, subject_class_rel b, stud_registration d, clist_cdetail_rel e, annual_terms f, stud_information g "
																	+ "WHERE a.sub_id = b.sub_id and e.cl_id = ? and e.CD_ID = ? and b.SUBCL_ID in (select subcl_id from exam_sub_rel where SUBCL_ID in (select subcl_id from subject_class_rel where CL_ID = ?)) and b.CL_ID = e.CL_ID and sub_status = 'A' and d.si_id = g.si_id and d.CLCD_ID = e.CLCD_ID and d.ACADEMIC_YEAR = ? and "
																		+ "(d.stud_status = 'Active' || d.stud_status = 'Passed' || d.stud_status = 'Failed' || d.stud_status = 'Back' || d.stud_status = 'Repeat')) as sublist " 
																	+ "LEFT JOIN "
																	+ "(SELECT sum(a.result) as qrt_total, a.si_id, a.at_id, d.FNAME, d.MNAME, d.GNAME, e.STUD_STATUS "
																	+ "FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_information d, stud_registration e, subject_list f "
																	+ "WHERE e.CLCD_ID = (select CLCD_ID from clist_cdetail_rel where cl_id = ? and cd_id = ?) and c.add_status = 'Yes' and a.fical_year = ? and f.SUB_ID = c.SUB_ID and d.si_id = e.si_id and e.ACADEMIC_YEAR = ? and c.subcl_id = b.subcl_id and a.si_id = d.si_id and b.exsub_id = a.examsub_id " 
																	+ "GROUP BY a.si_id, a.at_id) as rsltlist "
																	+ "USING (si_id, at_id) "
																	+ "ORDER BY sublist.si_id, sublist.at_id";
	
	
	
	public static final String getExamTypeList = "SELECT c.et_id, c.et_name, a.exsub_id FROM exam_sub_rel a, subject_class_rel b, exams_type c WHERE b.sub_id = ? and b.cl_id = ? and b.subcl_id = a.subcl_id and a.et_id = c.et_id and a.rel_status = 'A'";
	
	public static final String getStudentMarkPerExamType = "SELECT a.er_id, a.result, (select b.pass_mark from exam_sub_rel b where a.examsub_id = b.exsub_id and b.rel_status = 'A') as sub_passmark, (select b.total_mark from exam_sub_rel b where a.examsub_id = b.exsub_id and b.rel_status = 'A') as sub_totalmark FROM exam_result a  WHERE a.si_id = ? and a.examsub_id = ? and a.fical_year = ? and a.at_id = ?";
	
	public static final String getMarkPerExamType = "SELECT a.er_id, a.si_id, b.fname, b.mname, b.gname, a.result, d.pass_mark, d.total_mark " +
													"FROM exam_result a, stud_information b, stud_registration c, exam_sub_rel d " +
													"WHERE a.si_id = b.si_id and a.at_id = ? and a.fical_year = ? and c.academic_year = ? and a.si_id = c.si_id and c.clcd_id = (select clcd_id from clist_cdetail_rel where cl_id = ? and cd_id = ?) and a.examsub_id = ? and a.examsub_id = d.exsub_id and c.stud_status = 'Active'";
	
	public static final String updateStudResult = "UPDATE exam_result SET result = ?, udt_by = ?, udt_date = ? WHERE er_id = ?";
	
	public static final String getStudListPerGrade = "SELECT a.si_id, a.fname, a.mname, a.gname FROM stud_information a, stud_registration b, clist_cdetail_rel c WHERE c.cl_id = ? and c.clcd_id = b.clcd_id and b.si_id = a.si_id and (b.stud_status = 'Active' || b.stud_status = 'Passed' || b.stud_status = 'Failed' || b.stud_status = 'Back' || b.stud_status = 'Repeat') and b.academic_year = ?";
	
	public static final String getStudListWithRslt = "SELECT result FROM exam_result WHERE examsub_id = ? and si_id = ?";
	
	public static final String getGradeDetail = "SELECT a.cd_id, a.cd_name FROM class_detail a, clist_cdetail_rel b WHERE b.cl_id = ? and a.cd_id = b.cd_id and b.rel_status = 'Active'";
	
	public static final String getGradeDetailForExamReg = "SELECT a.cd_id, a.cd_name "
			+ "FROM class_detail a, clist_cdetail_rel b "
			+ "WHERE b.cl_id = ? and a.cd_id = b.cd_id and b.rel_status = 'Active' and a.cd_id in (select distinct c.cd_id from class_schedule c, tech_assignment d where c.ta_id = d.ta_id and d.ti_id = ? and ta_status = 'A' and cl_id = ? and c.academic_year = ?)";
	
	public static final String studentListPerClassDetail = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.id_no, a.si_status, " +
			 												"(SELECT total_mark FROM exam_sub_rel WHERE exsub_id = ?)  as total_mark " +
			 												"FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
															"WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?)";
	
	public static final String StudentListForExamRslt = "SELECT a.si_id, a.fname, a.mname, a.gname,"
			  											+ " (SELECT result FROM exam_result d WHERE d.si_id = a.si_id and examsub_id = ?) as rslt,"
			  											+ " (SELECT total_mark FROM exam_sub_rel WHERE exsub_id = ?)  as total_mark,"
			  											+ " (SELECT pass_mark FROM exam_sub_rel WHERE exsub_id = ?) as pass_mark"
			  											+ " FROM stud_information a, clist_cdetail_rel b, stud_registration c"
			  											+ " WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.cl_id = ? and a.si_status = 'A' and (SELECT result FROM exam_result d WHERE d.si_id = a.si_id and examsub_id = ?) is null and (SELECT stud_status FROM stud_registration e WHERE e.clcd_id = b.clcd_id and e.si_id = a.si_id and reg_status = 'A') = 'Active' and b.cd_id = ? and c.academic_year = ?";
	
	public static final String saveStudentRsltFinalDecision = "UPDATE stud_registration SET stud_status = ?, update_by = ?, update_date = ? WHERE si_id = ? and academic_year = ?";
	//public static final String saveStudentRsltFinalDecision = "UPDATE stud_registration SET stud_status = ?, status_updateby = ?, status_updatedate = ? WHERE si_id = ?";
	
	public static final String getSemisterList = "SELECT at_id, at_name FROM annual_terms WHERE at_status = 'A' and at_type = 'S'";
	
	public static final String getSemisterListIncludingFullYear = "SELECT at_id, at_name FROM annual_terms WHERE at_status = 'A'";
	
	public static final String getSubjectExamDate = "SELECT es_greg_date " +
			                                        "FROM exam_schedule c, (SELECT a.et_id, b.sub_id, b.cl_id  FROM exam_sub_rel a, subject_class_rel b WHERE exsub_id = ? and a.subcl_id = b.subcl_id and a.rel_status = 'A') d " +
			                                        "WHERE c.et_id = d.et_id and c.sub_id = d.sub_id and c.cl_id = d.cl_id and c.es_status = 'A' and c.es_fiscalyear = ?";

	/*
	 * this is converted to view table - v_quarter_cum_final_result 
	 */
	
//	public static final String getStudentQuarterResult = "SELECT d.sub_id, d.sub_name, c.add_status, c.convert_to_grade, "
//			 											+ "(SELECT sum(f.result) FROM exam_sub_rel e, exam_result f WHERE e.subcl_id = a.subcl_id and f.si_id = b.si_id and f.at_id = b.at_id and e.et_id <> 5 and e.exsub_id = f.examsub_id) as 'cummulative', "
//			 											+ "(b.result) as 'finalExam' "
//			 											+ "FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d "
//			 											+ "WHERE b.si_id = ? and b.at_id = ? and a.et_id = 5 and a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id ";
	
//	public static final String getStudentQuarterResult = "SELECT a.sub_id, b.sub_name, a.add_status, a.convert_to_grade, a.cummulative, a.finalExam, c.at_name "
//														+ "FROM v_quarter_cum_final_result a, subject_list b, annual_terms c "
//														+ "WHERE a.si_id = ? and a.at_id = ? and a.ac_year = ? and a.sub_id = b.sub_id and c.at_id = a.at_id";
	
	public static final String getStudentQuarterResult = "SELECT distinct b.si_id, e.fname, e.mname, e.gname, b.at_id, f.at_name, d.sub_id, d.sub_name, c.add_status, c.convert_to_grade, (SELECT sum(f.result) FROM exam_sub_rel e, exam_result f WHERE e.subcl_id = a.subcl_id and f.si_id = b.si_id and f.at_id = b.at_id and e.et_id <> (select et_id from exams_type where et_type = 'F' and et_status = 'A') and e.exsub_id = f.examsub_id) cummulative, (b.result) finalExam, b.fical_year, c.cl_id"
														+ " FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, annual_terms f, stud_registration g"
														+ " WHERE e.si_id = g.si_id and a.et_id = (select et_id from exams_type where et_type = 'F' and et_status = 'A') and a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and f.at_id = b.at_id and b.si_id = ? and b.at_id = ? and b.fical_year = ? and c.cl_id = ?"
														+ " ORDER BY c.convert_to_grade, d.sub_name";
	
	public static final String getStudQuarterResult = "SELECT distinct b.si_id, e.fname, e.mname, e.gname, b.at_id, b.examsub_id, f.at_name, d.sub_id, d.sub_name, c.add_status, c.convert_to_grade, (b.result) finalExam, b.fical_year, c.cl_id, c.SUBCL_ID, AA.cummulative"
													+ " FROM exam_result b, subject_class_rel c, subject_list d, stud_information e, annual_terms f, stud_registration g, exam_sub_rel a left join ("
													+ " 	SELECT sum(i.result) cummulative, h.subcl_id "
													+ " 	FROM exam_sub_rel h, exam_result i "
													+ " 	WHERE h.exsub_id = i.examsub_id and i.si_id = ? and i.at_id = ? and i.fical_year = ? and "
													+ "			h.et_id <> (select et_id from exams_type where et_type = 'F' and et_status = 'A') "
													+ " 	Group by h.subcl_id"
													+ " ) AA using (subcl_id)"
													+ " WHERE e.si_id = g.si_id and a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and f.at_id = b.at_id and b.si_id = ? and b.at_id = ? and b.fical_year = ? and c.cl_id = ? "
													+ "		and a.et_id = (select et_id from exams_type where et_type = 'F' and et_status = 'A') "
													+ " ORDER BY c.convert_to_grade, d.sub_name";
	
	public static final String getStudentQrtResult_QryUpdate = "SELECT AA.sub_id, AA.sub_name, BB.total, AA.convert_to_grade, AA.add_status, BB.exsub_id FROM " 
				+ "(SELECT a.SUB_ID, a.SUB_NAME, b.CONVERT_TO_GRADE, b.ADD_STATUS "
				+ "FROM subject_list a, subject_class_rel b WHERE a.SUB_ID = b.SUB_ID and b.CL_ID = ?) AA left join "
				+ "(SELECT sum(i.result) total, h.subcl_id, i.si_id, l.SUB_ID, h.EXSUB_ID "
				+ "FROM exam_sub_rel h, exam_result i, exams_type j, subject_class_rel l "
				+ "WHERE h.exsub_id = i.examsub_id and h.SUBCL_ID = l.SUBCL_ID and h.ET_ID = j.ET_ID and "
				+ "l.CL_ID = ? and i.si_id = ? and i.at_id = ? and i.fical_year = ? GROUP BY h.subcl_id) BB using (sub_id) "
				+ "order by convert_to_grade";
	
	public static final String getOrderedDescStudentAnnualTotalResult = "SELECT a.si_id, sum(result) as total "
			+ "FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_registration d, clist_cdetail_rel e "
			+ "WHERE b.exsub_id = a.examsub_id and b.subcl_id = c.subcl_id and c.add_status = 'Yes' and a.si_id = d.si_id and d.clcd_id = e.clcd_id and a.fical_year = ? and d.ACADEMIC_YEAR = ? and e.cl_id = ? and e.cd_id = ? "
			+ "and (d.stud_status = 'Active' || d.stud_status = 'Passed' || d.stud_status = 'Failed' || d.stud_status = 'Back' || d.stud_status = 'Repeat') "
			+ "GROUP BY a.si_id "
			+ "ORDER BY total desc";
	
	public static final String getStudentActiveSemisterList = "SELECT distinct a.si_id, a.at_id "
			+ "FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_registration d, clist_cdetail_rel e "
			+ "WHERE b.exsub_id = a.examsub_id and b.subcl_id = c.subcl_id and c.add_status = 'Yes' and a.si_id = d.si_id and d.clcd_id = e.clcd_id and a.fical_year = ? and e.cl_id = ? and e.cd_id = ? "
			+ "ORDER BY si_id desc";
	
	/*
	 * student quarter rank
	 */
//	public static final String getStudentQuarterRank = 	"SELECT rank, si_id, total, fname " +
//														"FROM (SELECT @rank := @rank + 1 as 'Rank', total, si_id, fname FROM (SELECT a.si_id, b.fname, sum(cummulative + finalExam) total FROM v_quarter_cum_final_result a, stud_information b JOIN (SELECT @rank := 0 FROM DUAL) as sub WHERE a.si_id = b.si_id and a.cl_id = ? and a.cd_id = ? and a.at_id = ? and a.ac_year = ? and a.add_status = 'Yes' GROUP BY a.si_id) as qrt_total  ORDER BY total desc, fname ) as rank_temp_table " +
//														"WHERE rank_temp_table.si_id = ?";
	
	public static final String getStudentQuarterRank =  "SELECT rank, si_id, total, fname " +
														"FROM (SELECT @rank := @rank + 1 as 'Rank', total, si_id, fname " +
														       "FROM ( " +
														                   	"SELECT b.si_id, e.fname, sum(b.result) as total " +
																		   	"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h JOIN (SELECT @rank := 0 FROM DUAL) as sub " +
																			"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.add_status = 'Yes' and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id = ? and b.fical_year = ? " +
																			"GROUP BY b.si_id " +
														               ") as qrt_total " +
														       "ORDER BY total desc, fname ) as rank_temp_table " +
														"WHERE rank_temp_table.si_id = ?";
															
	public static final String getStudentQuarterTotalResult = "CALL get_student_quarter_total_result(?,?)";
	
//	public static final String getStudentQuarterTotalResult = "SELECT distinct si_id, (SELECT sum(cummulative + finalExam) as quarter_total FROM v_quarter_cum_final_result b WHERE at_id = 1 and b.ac_year = a.ac_year and add_status = 'Yes' and a.si_id = b.si_id) as qrt_one, "
//																				+ "(SELECT sum(cummulative + finalExam) as quarter_total FROM v_quarter_cum_final_result b WHERE at_id = 2 and b.ac_year = a.ac_year and add_status = 'Yes' and a.si_id = b.si_id) as qrt_two, "
//																				+ "(SELECT sum(cummulative + finalExam) as quarter_total FROM v_quarter_cum_final_result b WHERE at_id = 3 and b.ac_year = a.ac_year and add_status = 'Yes' and a.si_id = b.si_id) as qrt_three, "
//																				+ "(SELECT sum(cummulative + finalExam) as quarter_total FROM v_quarter_cum_final_result b WHERE at_id = 4 and b.ac_year = a.ac_year and add_status = 'Yes' and a.si_id = b.si_id) as qrt_four " +
//																"FROM v_quarter_cum_final_result a " +
//																"WHERE si_id = ? and ac_year = ?";
	
//	public static final String getEachQuarterStudTotalRslt = "SELECT b.si_id, b.fname, b.mname, b.gname, a.qrt_one, a.qrt_two, a.qrt_three, a.qrt_four, c.stud_status " +
//																"FROM v_quarter_total_eachstud_perclass a, stud_information b, stud_registration c " +
//																"WHERE a.si_id = b.si_id and a.cl_id = ? and a.cd_id = ? and a.ac_year = ? and b.si_id = c.si_id and c.academic_year = a.ac_year";
	
	public static final String getEachQuarterStudTotalRslt = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, b.fical_year, c.cl_id, g.cd_id, h.stud_status"
			+ " FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h"
			+ " WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.add_status = 'Yes' and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id = ? and b.fical_year = ? and (h.stud_status = 'Active' || h.stud_status = 'Passed' || h.stud_status = 'Failed' || h.stud_status = 'Back' || h.stud_status = 'Repeat')"
			+ " GROUP BY b.si_id";
	
	public static final String getEachQuarterStudAllTotalRslt = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, b.fical_year, c.cl_id, g.cd_id, h.stud_status"
			+ " FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h"
			+ " WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id = ? and b.fical_year = ? and h.academic_year = ? and c.add_status = 'Yes' and c.convert_to_grade= 'NO'"
			+ " GROUP BY b.si_id"; 
			
	public static final String getEachQuarterStudAllTotalRsltCurrentYear = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, b.fical_year, c.cl_id, g.cd_id, h.stud_status"
							+ " FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h"
							+ " WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id = ? and b.fical_year = ? and h.academic_year = ? and c.add_status = 'Yes' and c.convert_to_grade= 'NO' and h.stud_status = 'Active'"
							+ " GROUP BY b.si_id";
	
	public static final String getEachQuarterTotalRsltPerStudent = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, b.fical_year, c.cl_id, g.cd_id, h.stud_status"
			+ " FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h"
			+ " WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.add_status = 'Yes' and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id = ? and b.fical_year = ? and h.academic_year = ? and b.si_id = ? and (h.stud_status = 'Active' || h.stud_status = 'Passed' || h.stud_status = 'Failed' || h.stud_status = 'Back' || h.stud_status = 'Repeat')"
			+ " GROUP BY b.si_id";
	
	public static final String getAllStudentsQuarterTotalResult = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, b.fical_year, c.cl_id, g.cd_id, h.stud_status"
			+ " FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h"
			+ " WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and b.si_id = e.si_id and c.add_status = 'Yes' and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = ? and g.cd_id = ? and b.at_id = ? and b.fical_year = ? and (h.stud_status = 'Active' || h.stud_status = 'Passed' || h.stud_status = 'Failed' || h.stud_status = 'Back' || h.stud_status = 'Repeat')"
			+ " GROUP BY b.si_id"
			+ " ORDER BY total desc";
	
	public static final String saveTeachersComment = "INSERT INTO exam_result_comment(si_id, cl_id, erc_content, erc_given_by, erc_givenby_type, erc_date, at_id, ac_year, erc_status) VALUES(?,?,?,?,?,?,?,?,'A') ";
	
	public static final String checkCommentExistance = "SELECT erc_id FROM exam_result_comment WHERE si_id = ? and cl_id = ? and at_id = ? and ac_year = ?";
	
	public static final String getTeacherComment = "SELECT erc_id, erc_content FROM exam_result_comment WHERE si_id = ? and cl_id = ? and at_id = ? and ac_year = ?";
	
	public static final String updateTeachersComment = "UPDATE exam_result_comment SET erc_content = ? WHERE erc_id = ?";
	
	public static final String getTeachersQuarterComment = "SELECT erc_content FROM exam_result_comment WHERE si_id = ? and at_id = ? and cl_id = ? and ac_year = ?";
	
	public static final String getTeachersQuarterCommentForAllStudentsPerClass = "SELECT si_id, erc_content, at_id FROM exam_result_comment WHERE cl_id = ? and ac_year = ?";
	
	public static final String getHomeroomTeachersName = "SELECT b.fname, b.mname, b.gname FROM tech_hroom_assignment a, teacher_information b WHERE a.ti_id = b.ti_id and a.cl_id = ? and a.cd_id = ? and a.academic_year = ? and thra_status = 'A'";
	
	public static final String getSubjectGradingResult = "SELECT b.eg_value "
														+ "FROM exam_subcl_grade_rel a, exam_grade b " 
														+ "WHERE a.eg_id = b.eg_id and subcl_id = (select subcl_id from exam_sub_rel where exsub_id = ?) and a.grade_from <= ? and a.grade_to >= ?";
	
	public static final String getParentsEmailAddress = "SELECT concat(email, ', ', email_2) as email FROM stud_current_address WHERE si_id = ? and sca_status = 'A'";
	
	public static final String checkDuplicatedStudExamRslt = "SELECT si_id FROM exam_result WHERE si_id = ? and examsub_id = ? and at_id = ? and fical_year = ?";
		
	public static final String getSummerizedStudCountPerMarkInserted = "SELECT a.examsub_id, f.SUB_NAME, COUNT(a.si_id) AS stud_count "
			+ " FROM sms_everest.exam_result a, exam_sub_rel d, subject_class_rel e, subject_list f "
			+ " WHERE a.fical_year = ? AND a.at_id = ? AND a.examsub_id IN (SELECT b.exsub_id FROM exam_sub_rel b WHERE "
			+ " b.ET_ID = ? AND b.SUBCL_ID IN (SELECT c.SUBCL_ID FROM subject_class_rel c WHERE c.CL_ID = ?)) "
			+ " AND a.si_id IN (SELECT g.si_id FROM stud_registration g WHERE g.ACADEMIC_YEAR = ? AND g.STUD_STATUS = 'Active' "
			+ " AND g.CLCD_ID IN (SELECT h.CLCD_ID FROM clist_cdetail_rel h WHERE h.cl_id = ? AND h.cd_id = ?))"
			+ " AND a.examsub_id = d.EXSUB_ID AND d.SUBCL_ID = e.SUBCL_ID AND f.SUB_ID = e.SUB_ID GROUP BY examsub_id;";
	
	public static final String getStudentTotalMarkPerQuarterForRanking = "SELECT a.si_id, sum(result) as total, a.at_id "
			+ "FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_registration d, clist_cdetail_rel e "
			+ "WHERE b.exsub_id = a.examsub_id and b.subcl_id = c.subcl_id and c.add_status = 'Yes' and a.si_id = d.si_id and d.clcd_id = e.clcd_id and a.fical_year = ? and d.ACADEMIC_YEAR = ? and e.cl_id = ? and e.cd_id = ? "
			+ "GROUP BY a.si_id, a.at_id "
			+ "ORDER BY at_id, total desc";
	
	///***	Report Queries
	
	public static final String examtype_per_subject = "SELECT a.et_id FROM exam_sub_rel a, subject_class_rel b WHERE a.subcl_id = b.subcl_id and b.cl_id = 1 and b.sub_id = 1";
	
	public static final String student_list_per_class = "select d.si_id from stud_registration d, clist_cdetail_rel e where d.clcd_id = e.clcd_id and e.cl_id = 1 and e.cd_id = 1 and d.stud_status = 'Active'";
	
	public static final String quarter_mark_list_per_class_subject = "SELECT a.si_id, concat(f.fname, ' ', f.mname, ' ', f.gname) fullName, f.sex, a.at_id, a.examsub_id, a.result " +
																		"FROM exam_result a, exam_sub_rel b, subject_class_rel c, stud_information f " +
																		"WHERE a.examsub_id = b.exsub_id and c.subcl_id = b.subcl_id and c.cl_id = 1 and c.sub_id = 1 and a.at_id = 2 and a.si_id in (select d.si_id from stud_registration d, clist_cdetail_rel e where d.clcd_id = e.clcd_id and e.cl_id = c.cl_id and e.cd_id = 1 and d.stud_status = 'Active') and f.si_id = a.si_id " +
																		"ORDER BY a.si_id";
}
