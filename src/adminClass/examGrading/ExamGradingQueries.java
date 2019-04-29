package adminClass.examGrading;

public class ExamGradingQueries {
	
	public static final String getExamGradingList = "SELECT eg_id, eg_value, eg_desc, eg_status FROM exam_grade";
	
	public static final String saveExamGrading = "INSERT INTO exam_grade(eg_value, eg_desc, eg_status) VALUES(?, ?, 'A')";
	
	public static final String updateExamGrading = "UPDATE exam_grade SET eg_value = ?, eg_desc = ?, eg_status = ? WHERE eg_id = ?";

}
