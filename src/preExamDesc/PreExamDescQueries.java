package preExamDesc;

public class PreExamDescQueries {
	
	public static final String getQstDesc = "select pee_id, explanation_dtl, suggested_material, ti_id, pee_status, pee_image, pee_image_path from pre_exam_explanation where peq_id = ?";

	public static final String getQst = "SELECT peq_content FROM pre_exam_questions WHERE peq_id = ?";
	
	public static final String saveQstDesc = "INSERT INTO pre_exam_explanation(explanation_dtl, suggested_material, pee_status, pee_image, pee_image_path, peq_id) VALUES(?, ?, 'A', 'no', 'no', ?)";
	
	public static final String updateQstDesc = "UPDATE pre_exam_explanation SET explanation_dtl = ?, suggested_material = ?, pee_status = ? WHERE pee_id = ?";
	
	public static final String updateQstDescDocInfo = "UPDATE pre_exam_explanation SET pee_image = ?, pee_image_path = ? WHERE pee_id = ?";
}
