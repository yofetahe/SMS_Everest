package behEvaluationClass;

public class BevalQueries {
	
	public static final String getQuarterList = "SELECT qr_id, qr_name FROM annual_quarters WHERE qr_status = 'A'";
	
	public static final String getStudentBevalRslt = "SELECT a.bsr_id, a.bt_id, b.bt_title, a.bg_id, d.bg_name, a.academic_year, a.qr_id, c.at_name, a.bsr_status "
			+ "FROM beval_stud_result a, beval_traits b, beval_grade d, annual_terms c "
			+ "WHERE a.bt_id = b.bt_id and a.qr_id = c.at_id and a.bg_id = d.bg_id and a.cl_id = ? and a.si_id = ? and a.academic_year = ? and a.qr_id = ? and a.bsr_status != 'I' ORDER BY a.bt_id";
	
	public static final String getUnmarkedBevalTraitList = "SELECT a.bt_id, b.bt_title " +
															"FROM btraits_class_rel a, beval_traits b " +
															"WHERE a.bt_id = b.bt_id and a.cl_id = ? and a.bt_id not in (SELECT bt_id FROM beval_stud_result WHERE cl_id = ? and si_id = ? and academic_year = ? and qr_id = ? )";
	
	public static final String getMarkedBevalTraitList = "SELECT a.bt_id, b.bt_title " +
														"FROM btraits_class_rel a, beval_traits b " +
														"WHERE a.bt_id = b.bt_id and a.cl_id = ? and a.bt_id in (SELECT bt_id FROM beval_stud_result WHERE cl_id = ? and si_id = ? and academic_year = ? and qr_id = ? )";
	
	public static final String getBevalGradeList = "SELECT a.bg_id, b.bg_name FROM btraits_bgrades_rel a, beval_grade b WHERE a.bg_id = b.bg_id and a.bt_id = ? ORDER BY b.bg_name";
	
	public static final String getBevalGradeGivenList = "SELECT a.bg_id, b.bg_name FROM btraits_bgrades_rel a, beval_grade b WHERE a.bg_id = b.bg_id and a.bt_id = ?";
	
	public static final String saveBevalGrade = "INSERT INTO beval_stud_result(si_id, cl_id, bt_id, bg_id, qr_id, academic_year, bsr_status) VALUES(?, ?, ?, ?, ?, ?, 'A')";
	
	public static final String updateBevalGrade = "UPDATE beval_stud_result SET bt_id = ?, bg_id  = ?, bsr_status = ? WHERE bsr_id = ?";
}
