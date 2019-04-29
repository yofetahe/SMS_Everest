package subjectClass;

public class SubjectQueries {
	
	public static final String getSubjectName = "SELECT a.sub_id, a.sub_name, a.sub_status, b.subcl_id FROM subject_list a, subject_class_rel b WHERE a.sub_id = b.sub_id and b.subcl_id = ? and b.cl_id = ? and sub_status = 'A'";
	
	public static final String getActiveSubList = "SELECT sub_id, sub_name, sub_status FROM subject_list WHERE sub_status = 'A'";
	//public static final String getActiveSubList = "CALL get_Active_Sub_List";
	
	public static final String getSubList = "SELECT sub_id, sub_name, sub_status FROM subject_list";
	//public static final String getSubList = "CALL select_subject()";
	
	public static final String getSubListPerClass = "SELECT distinct a.sub_id, a.sub_name, a.sub_status, b.subcl_id, c.period_per_week FROM  subject_list a, subject_class_rel b LEFT JOIN class_sched_subject_period_rel c ON b.sub_id = c.sub_id and b.cl_id = c.cl_id WHERE a.sub_id = b.sub_id and b.cl_id = ? and (a.sub_status = 'A' and b.rel_status = 'A')";
	//public static final String getSubListPerClass = "CALL get_Sub_List_Per_Class(?)";

	public static final String validateSubjectBeforeSave = "SELECT sub_id FROM subject_list WHERE sub_name = ? ";
	
	public static final String validateSubjectBeforeUpdate = "SELECT sub_id FROM subject_list WHERE sub_name = ? and sub_id <> ?";
	
	
	public static final String insertSubject = "INSERT INTO subject_list(sub_name, sub_status) VALUES (? , 'A')";
	//public static final String insertSubject = "CALL insert_subject(?)";
	
	public static final String updateSubject = "UPDATE subject_list SET sub_name = ?, sub_status = ? WHERE sub_id = ?";
	//public static final String updateSubject = "CALL update_subject(?, ?, ?)";
	
	public static final String getUnselectedSubject = "SELECT sub_id, sub_name FROM subject_list WHERE sub_id not in (SELECT b.sub_id FROM subject_list b, subject_class_rel a WHERE a.sub_id = b.sub_id and a.cl_id = ? and a.rel_status = 'A') and sub_status = 'A'";
	//public static final String getUnselectedSubject = "CALL get_Unselected_Subject(?)";
	
	public static final String getSelectedSubject = "SELECT b.sub_id, b.sub_name, a.subcl_id FROM subject_list b, subject_class_rel a WHERE a.sub_id = b.sub_id and a.cl_id = ? and rel_status = 'A'";
	//public static final String getSelectedSubject = "CALL get_Selected_Subject(?)";
	
	public static final String getSelectedRelatedSubject = "SELECT b.sub_id, b.sub_name, a.subcl_id FROM subject_list b, subject_class_rel a WHERE a.sub_id = b.sub_id and a.cl_id = ? and rel_status = 'A' and b.sub_id in (SELECT sub_id FROM tech_assignment WHERE ti_id = ? and ta_status = 'A')";
	
	public static final String getAssignedTeacherForSubject = "SELECT b.fname, b.mname, b.gname FROM tech_assignment a, teacher_information b, subject_class_rel c WHERE a.cl_id = c.cl_id and a.sub_id = c.sub_id and a.ti_id = b.ti_id and c.subcl_id = ? and ta_status = 'A'";

}
