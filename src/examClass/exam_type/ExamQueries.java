package examClass.exam_type;

public class ExamQueries {
	
	public static final String getExamTypeList = "SELECT et_id, et_name, et_type, et_status FROM exams_type ";
	
	public static final String getActiveExamTypeList = "SELECT et_id, et_name, et_status FROM exams_type WHERE et_status = 'A'";
	
	public static final String insertExamType = "INSERT INTO exams_type(et_name, et_type, et_status) VALUES (?, ?, 'A')";
	
	public static final String updateExamType = "UPDATE exams_type SET et_name = ?, et_type = ?, et_status = ? WHERE et_id = ?";
	
	public static final String getUnselectedExamTypeList = "SELECT et_id, et_name, et_status FROM exams_type WHERE et_id not in (SELECT b.et_id FROM subject_class_rel a, exam_sub_rel b, exams_type c WHERE a.subcl_id = b.subcl_id and b.et_id = c.et_id and a.cl_id = ? and a.sub_id = ? and b.rel_status = 'A')";
	
//	public static final String getSelectedExamTypeList = "SELECT a.exsub_id, a.et_id, b.et_name, b.et_status, a.total_mark, a.pass_mark "
//			+ "FROM exam_sub_rel a, exams_type b "
//			+ "WHERE a.et_id = b.et_id and a.subcl_id = ? and a.rel_status = 'A' and "
//			+ "a.et_id in (select et_id from exam_schedule where es_fiscalyear = ? and cl_id = (select cl_id from subject_class_rel where subcl_id = ?) and sub_id = (select sub_id from subject_class_rel where subcl_id = ?))";
	
	public static final String getSelectedExamTypeList = "SELECT c.es_id, a.et_id, a.et_name, a.et_status, b.total_mark, b.pass_mark, b.exsub_id " +
														 "FROM exams_type a, exam_sub_rel b, exam_schedule c " +
														 "WHERE b.subcl_id = ? and a.et_id = b.et_id and a.et_id = c.et_id and c.at_id = ? and c.es_fiscalyear = ? and c.cl_id = ? and c.sub_id = (select sub_id from subject_class_rel where subcl_id = ?) and b.rel_status = 'A' and c.es_status = 'A' and et_status = 'A'";

	public static final String getActivelyRelatedExamWithClassSubject = "SELECT a.et_id, a.et_name, a.et_status, b.total_mark, b.pass_mark, b.exsub_id " +
																		"FROM exams_type a, exam_sub_rel b " +
																		"WHERE b.subcl_id = ? and a.et_id = b.et_id and b.rel_status = 'A' and a.et_status = 'A'" +
																		"ORDER BY a.et_type desc";


}
