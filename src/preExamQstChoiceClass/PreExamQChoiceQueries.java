package preExamQstChoiceClass;

public class PreExamQChoiceQueries {
	
	public static final String getQstChoice = "select pec_id, pec_content, pec_image, pec_correct, pec_status, peq_id from pre_exam_choice where peq_id = ?";
	
	public static final String saveQstChoice = "insert into pre_exam_choice (pec_content, pec_correct, pec_status, peq_id) values (?, ?, 'A', ?)";
	
	public static final String updateQstChoice = "update pre_exam_choice set pec_content = ?, pec_correct = ?, pec_status = ? where pec_id = ?";
	
	public static final String updateQstChoiceDocInfo = "UPDATE pre_exam_choice SET pec_image = ?, pec_image_path = ? WHERE pec_id = ?";

}
