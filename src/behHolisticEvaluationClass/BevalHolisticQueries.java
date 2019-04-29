package behHolisticEvaluationClass;

public class BevalHolisticQueries {
	
	public static final String getHolisticCategoryList = "SELECT bhc_id, bhc_name, bhc_desc, bhc_status FROM beval_holistic_category ";

	public static final String saveHolisticCategory = "INSERT INTO beval_holistic_category(bhc_name, bhc_desc, bhc_status) VALUES(?, ?, 'A')";
	
	public static final String updateHolisticCategory = "UPDATE beval_holistic_category SET bhc_name = ?, bhc_desc = ?, bhc_status = ? WHERE bhc_id = ?";
	
	public static final String getHolisticPointList = "SELECT bhp_id, bhp_content, bhp_desc, bhp_status FROM beval_holistic_points ";
	
	public static final String saveHolisticPoint = "INSERT INTO beval_holistic_points(bhp_content, bhp_desc, bhp_status) VALUES(?, ?, 'A')";
	
	public static final String updateHolisticPoint = "UPDATE beval_holistic_points SET bhp_content = ?, bhp_desc = ?, bhp_status = ? WHERE bhp_id = ?";
	
	public static final String getRelatedPointList = "SELECT a.bhcp_id, a.bhc_id, a.bhp_id, a.bhcp_status, c.bhp_content FROM beval_holistic_catpoint_rel a, beval_holistic_category b, beval_holistic_points c WHERE a.bhc_id = b.bhc_id and a.bhp_id = c.bhp_id and a.bhc_id = ?";
	
	public static final String getActiveRelatedPointList = "SELECT a.bhcp_id, a.bhc_id, a.bhp_id, a.bhcp_status, c.bhp_content FROM beval_holistic_catpoint_rel a, beval_holistic_category b, beval_holistic_points c WHERE a.bhc_id = b.bhc_id and a.bhp_id = c.bhp_id and a.bhc_id = ? and a.bhcp_status = 'A'";
	
	public static final String getUncommentedRelatedPointList = "SELECT a.bhcp_id, a.bhc_id, a.bhp_id, a.bhcp_status, c.bhp_content, (select beval_comment from beval_holistic_stud_result where bhcp_id = a.bhcp_id and at_id = ?) as beval_comment, (select bhsr_id from  beval_holistic_stud_result where bhcp_id = a.bhcp_id and at_id = ?) as bhsr_id " +
																"FROM beval_holistic_catpoint_rel a, beval_holistic_category b, beval_holistic_points c " +
																"WHERE a.bhc_id = b.bhc_id and a.bhp_id = c.bhp_id and a.bhc_id = ? and a.bhcp_status = 'A'";
	
	public static final String getUnrelatedPointList = "SELECT bhp_id, bhp_content FROM beval_holistic_points WHERE bhp_id not in ( SELECT a.bhp_id FROM beval_holistic_catpoint_rel a, beval_holistic_category b, beval_holistic_points c WHERE a.bhc_id = b.bhc_id and a.bhp_id = c.bhp_id and a.bhc_id = ? )";

	public static final String saveCategoryPointRel = "INSERT INTO beval_holistic_catpoint_rel(bhc_id, bhp_id, bhcp_status) VALUES(?, ?, 'A')";
	
	public static final String updateCategoryPointRel = "UPDATE beval_holistic_catpoint_rel SET bhcp_status = ? WHERE bhcp_id = ?";
	
	public static final String getRelatedCategoryList = "SELECT a.bhccr_id, a.bhc_id, a.cl_id, a.bhccr_status, b.bhc_name FROM beval_holistic_catclass_rel a, beval_holistic_category b WHERE a.cl_id = ? and a.bhc_id = b.bhc_id";
	
	public static final String getUnrelatedCategoryList = "SELECT c.bhc_id, bhc_name FROM beval_holistic_category c WHERE c.bhc_id not in (SELECT a.bhc_id FROM beval_holistic_catclass_rel a, beval_holistic_category b WHERE a.cl_id = ? and a.bhc_id = b.bhc_id)";
	
	public static final String saveClassCategoryRel = "INSERT INTO beval_holistic_catclass_rel(bhc_id, cl_id, bhccr_status) VALUES(?,?,'A')";
	
	public static final String updateClassCategoryRel = "UPDATE beval_holistic_catclass_rel SET bhccr_status = ? WHERE bhccr_id = ?";
	
	public static final String saveStudPointCommentGiven = "INSERT INTO beval_holistic_stud_result(si_id, cl_id, bhcp_id, beval_comment, academic_year, at_id, bhsr_status) VALUES(?,?,?,?,?,?,'A')";
	
	public static final String updateStudPointCommentGiven = "UPDATE beval_holistic_stud_result SET beval_comment = ? WHERE bhsr_id = ?";
	
	public static final String getStudentsBehaviouralHolisticComment = "SELECT a.bhsr_id, c.bhc_id, c.bhc_name, d.bhp_content, a.beval_comment " +
																		"FROM beval_holistic_stud_result a, beval_holistic_catpoint_rel b, beval_holistic_category c, beval_holistic_points d " +
																		"WHERE a.si_id = ? and a.cl_id = ? and a.at_id = ? and a.academic_year = ? and a.bhcp_id = b.bhcp_id and b.bhc_id = c.bhc_id and b.bhp_id = d.bhp_id and b.bhcp_status = 'A'";
	
}
