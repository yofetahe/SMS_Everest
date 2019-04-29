package teacherClass.assignment;

public class TeacherAssignQueries {
	
	public static final String getAssignList = "SELECT a.ta_id, a.sub_id, (select sub_name from subject_list where sub_id = a.sub_id) as sub_name, a.cl_id, (select class_name from class_list where cl_id = a.cl_id) as cl_name, a.year, a.ta_status FROM tech_assignment a WHERE ti_id = ?";
	
	public static final String insertAssignment = "INSERT INTO tech_assignment (sub_id, cl_id, ta_status, ti_id) VALUES (?, ?, 'A', ?)";
	
	public static final String checkTeacherAssignment = "SELECT sub_id FROM tech_assignment WHERE sub_id = ? and cl_id = ? and ti_id = ?";
	
	public static final String updateAssignment = "UPDATE tech_assignment SET sub_id = ?, cl_id = ?, ta_status = ? WHERE ta_id = ?";
	
	public static final String getSubjectListPerGrade = "SELECT b.sub_id, b.sub_name FROM subject_class_rel a, subject_list b WHERE a.cl_id = ? and a.sub_id = b.sub_id";

}
