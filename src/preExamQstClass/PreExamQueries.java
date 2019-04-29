package preExamQstClass;

public class PreExamQueries {
	
	public static final String getExamCategoryList = "select pet_id, total_time_allowed, number_of_qst, exam_level, subcl_id, pet_status from pre_exam_type where subcl_id = ? and pet_status = 'A'";
	
	public static final String getExamQstList = "select peq_id, peq_content, peq_image, peq_status, ti_id, pet_id, peq_image from pre_exam_questions where pet_id = ?";
	
	public static final String getExamCatInfo = "select pet_id, total_time_allowed, number_of_qst, exam_level, subcl_id, pet_status from pre_exam_type where pet_id = ?";
	
	public static final String getExamCatList = "select pet_id, total_time_allowed, number_of_qst, exam_level, subcl_id, pet_status from pre_exam_type where subcl_id = ?";
	
	public static final String maxAllowedQst = "select number_of_qst from pre_exam_type where pet_id = ?";
	
	public static final String totalSavedQst = "select count(peq_id) as totalQst from pre_exam_questions where pet_id = ?";
	
	public static final String saveExamQst = "insert into pre_exam_questions (peq_content, peq_status, ti_id, pet_id, peq_image, peq_image_path) values (?, 'A', 1, ?, 'no', 'no')";
	
	public static final String updateExamQst = "update pre_exam_questions set peq_content = ?, peq_status = ?, pet_id = ? where peq_id = ?";
	
	public static final String saveExamCat = "insert into pre_exam_type (total_time_allowed, number_of_qst, exam_level, subcl_id, pet_status) values (?, ?, ?, ?, 'A')";
	
	public static final String updateExamCat = "update pre_exam_type set total_time_allowed = ?, number_of_qst = ?, exam_level = ?, pet_status = ? where pet_id = ?";
	
	public static final String getQst = "select peq_content from pre_exam_questions where peq_id = ?";
	
	public static final String updateQstDocInfo = "UPDATE pre_exam_questions SET peq_image = ?, peq_image_path = ? WHERE peq_id = ?";

}
