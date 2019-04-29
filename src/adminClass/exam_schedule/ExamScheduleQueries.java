package adminClass.exam_schedule;

public class ExamScheduleQueries {
	
	public static final String getExSchList = "SELECT a.es_id, a.et_id, a.cl_id, a.es_ethio_date, a.es_greg_date, e.at_name, a.es_status, b.class_name, c.et_name, d.sub_id, d.sub_name " +
			"FROM exam_schedule a, class_list b, exams_type c, subject_list d, annual_terms e " +
			"where a.cl_id = b.cl_id and a.et_id = c.et_id and a.sub_id = d.sub_id and a.at_id = e.at_id and a.es_fiscalyear = ?";
	
	public static final String getFilteredExamScheduleList = "SELECT a.es_id, a.et_id, a.cl_id, a.es_ethio_date, a.es_greg_date, e.at_name, a.es_status, b.class_name, c.et_name, d.sub_id, d.sub_name, a.time_from, a.time_to " +
			"FROM exam_schedule a, class_list b, exams_type c, subject_list d, annual_terms e " +
			"where a.cl_id = b.cl_id and a.et_id = c.et_id and a.sub_id = d.sub_id and a.cl_id = ? and a.at_id = e.at_id and a.es_fiscalyear = ? and a.at_id = ?";
	
	public static final String dbExSchList = "SELECT a.es_id, a.et_id, a.cl_id, a.es_ethio_date, a.es_greg_date, a.es_status, b.class_name, c.et_name, d.sub_id, d.sub_name " +
			"FROM exam_schedule a, class_list b, exams_type c, subject_list d " +
			"WHERE a.cl_id = b.cl_id and a.et_id = c.et_id and a.sub_id = d.sub_id and a.es_status = 'A' and a.es_fiscalyear = ?";
	
	public static final String saveExamSchedule = "INSERT INTO exam_schedule(cl_id, sub_id, et_id, es_ethio_date, es_greg_date, time_from, time_to, es_fiscalyear, at_id, es_status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, 'A')";
	
	public static final String updateExamSchedule = "UPDATE exam_schedule SET cl_id = ?, sub_id = ?, et_id = ?, es_ethio_date = ?, es_greg_date = ?, es_fiscalyear = ?, es_status = ?, time_from = ?, time_to = ?  WHERE es_id = ?";

	public static final String checkExamSchedule = "SELECT es_id FROM exam_schedule WHERE et_id = ? and es_fiscalyear = ? and at_id = ? and cl_id = ? and sub_id = ? ";
	
}
