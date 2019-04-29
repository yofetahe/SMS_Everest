package adminClass.classPeriodAssignment;

public class ClassPeriodAssignQueries {
	
	public static final String getClassPeriodAssigned = "SELECT a.period_per_week, a.sub_id, b.sub_name FROM class_sched_subject_period_rel a, subject_list b WHERE a.cl_id = ? and a.academic_year = ? and a.sub_id = b.sub_id";

	public static final String getGeneralPeriondAssignment = "SELECT a.csp_id, b.class_name, c.sub_name, a.period_per_week, a.academic_year "
															+ "FROM class_sched_subject_period_rel a, class_list b, subject_list c "
															+ "WHERE a.cl_id = b.cl_id and a.sub_id = c.sub_id and a.academic_year = ? "
															+ "ORDER BY class_name";
	
	public static final String saveClassPeriodAllotment = "INSERT INTO class_sched_subject_period_rel(cl_id, sub_id, period_per_week, academic_year, csp_status) VALUES(?, ?, ?, ?, 'Active')";
	
	public static final String getSubListPerClass = "SELECT distinct a.sub_id, a.sub_name, a.sub_status, b.subcl_id, c.period_per_week "
			+ "FROM subject_list a, subject_class_rel b LEFT JOIN class_sched_subject_period_rel c ON b.sub_id = c.sub_id and b.cl_id = c.cl_id and c.academic_year = ? "
			+ "WHERE a.sub_id = b.sub_id and b.cl_id = ? and (a.sub_status = 'A' and b.rel_status = 'A')";
	
	public static final String getClassPeriodAllotmentByCsp_id = "SELECT b.CLASS_NAME, c.SUB_NAME, a.period_per_week, a.academic_year "
			+ "FROM class_sched_subject_period_rel a, class_list b, subject_list c "
			+ "where csp_id = ? and a.cl_id = b.CL_ID and a.sub_id = c.SUB_ID;";
	
	public static final String updateClassPeriodAllotment = "UPDATE class_sched_subject_period_rel SET period_per_week = ? WHERE csp_id = ?";
	
	
}
