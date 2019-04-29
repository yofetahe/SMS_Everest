package cRoomClass.exam_grading;

public class EGradingQueries {
	
	public static final String getExamGradingList = "SELECT a.escg_id, a.subcl_id, a.eg_id, b.eg_value, a.grade_from, a.grade_to, a.escg_status "
													+ "FROM exam_subcl_grade_rel a, exam_grade b "
													+ "WHERE subcl_id = ? and a.eg_id = b.eg_id";
	
	public static final String getGradingList = "SELECT eg_id, eg_value  FROM exam_grade WHERE eg_id not in (select eg_id from exam_subcl_grade_rel where subcl_id = ?)";
	
	public static final String saveExamGradingRel = "INSERT INTO exam_subcl_grade_rel(subcl_id, eg_id, grade_from, grade_to, escg_status) VALUES(?,?,?,?,'A')";

	public static final String updateExamGradingRel = "UPDATE exam_subcl_grade_rel SET grade_from = ?, grade_to = ?, escg_status = ? WHERE escg_id = ?";
}
