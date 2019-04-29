package examClass.exam_subject_rel;

public class ExamSubjectRelQueries {
	
	public static final String getExamTypeList = "SELECT b.exsub_id, b.et_id, b.subcl_id, b.total_mark, b.pass_mark, b.rel_status, c.et_name " +
													"FROM subject_class_rel a, exam_sub_rel b, exams_type c " +
													"WHERE a.subcl_id = b.subcl_id and b.et_id = c.et_id and a.cl_id = ? and a.sub_id = ? and b.rel_status = 'A'";
	
	public static final String checkActiveExamSubRel = "SELECT a.subcl_id " +
			                                           "FROM exam_sub_rel a, subject_class_rel b " +
			                                           "WHERE a.subcl_id = b.subcl_id and a.subcl_id = (select subcl_id from subject_class_rel where cl_id = ? and sub_id = ?) and a.et_id = (select et_id from exam_sub_rel where exsub_id = ?) and a.rel_status = 'A'";
	
	public static final String checkActiveExamSubRelOtherThanCurrent = "SELECT a.subcl_id " +
            															"FROM exam_sub_rel a, subject_class_rel b " +
            															"WHERE a.subcl_id = b.subcl_id and a.subcl_id = (select subcl_id from subject_class_rel where cl_id = ? and sub_id = ?) and a.et_id = (select et_id from exam_sub_rel where exsub_id = ?) and a.exsub_id <> ? and a.rel_status = 'A'";
	
	public static final String insertClSubExmRel = "INSERT INTO exam_sub_rel (et_id, subcl_id, total_mark, pass_mark, rel_status) VALUES (?, ?, ?, ?, 'A')";
	
	public static final String checkSubjectTotalMark = "SELECT sum(total_mark) as total_mark FROM exam_sub_rel WHERE subcl_id = ? and rel_status = 'A'";
	
	public static final String updateClSubExmRel = "UPDATE exam_sub_rel SET total_mark = ?, pass_mark = ?, rel_status = ? WHERE exsub_id = ?";

	public static final String ExamScheduleReportPerQuarterExamType = 	"SELECT b.sub_name, a.es_ethio_date, a.es_greg_date, a.time_from, a.time_to, c.class_name, d.at_name, e.et_name " +
																		"FROM exam_schedule a, subject_list b, class_list c, annual_terms d, exams_type e " +
																		"WHERE a.sub_id = b.sub_id and a.cl_id = c.cl_id and a.at_id = d.at_id and e.et_id = a.et_id and a.at_id = ? and a.et_id = ?";
	
}
