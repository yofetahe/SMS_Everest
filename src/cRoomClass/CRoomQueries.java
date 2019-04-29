package cRoomClass;

public class CRoomQueries {
	
	public static final String getActiveCroom = "SELECT cl_id, class_name, class_status FROM class_list WHERE class_status = 'Active'";
	
	public static final String getActiveCroomForTranscript = "SELECT cl_id, class_name, class_status FROM class_list WHERE class_status = 'Active' and class_number in (9, 10, 11, 12)";
	
	public static final String getActiveRelatedCroom = "SELECT cl_id, class_name, class_status FROM class_list WHERE class_status = 'Active' and cl_id in (SELECT cl_id FROM tech_hroom_assignment WHERE ti_id = ? and thra_status = 'A' and academic_year = ?)";
	
	public static final String getActiveCroomForExamRsltReg = "SELECT cl_id, class_name, class_status FROM class_list WHERE class_status = 'Active' and cl_id in (SELECT cl_id FROM tech_assignment WHERE ti_id = ? and ta_status = 'A')";
	
	public static final String getCroomList = "SELECT cl_id, class_name, class_status, class_number FROM class_list";
	
	public static final String getClassName = "SELECT class_name, class_status FROM class_list WHERE cl_id = ?";
	
	public static final String checkCroomExistance = "SELECT cl_id FROM class_list WHERE class_name = ? or class_name = ? or class_name = ?";
	
	public static final String insertCroom = "INSERT INTO class_list (class_name, class_number, class_status) VALUES (?, ?, 'Active')";
	
	public static final String updateCroom = "UPDATE class_list SET class_name = ?, class_number = ?, class_status = ? WHERE cl_id = ?";
	
	public static final String getClassSubRel = "SELECT a.subcl_id, a.sub_id, a.cl_id, a.rel_status, b.sub_name, a.add_status, a.convert_to_grade FROM subject_class_rel a, subject_list b WHERE a.sub_id = b.sub_id and cl_id = ?";
	
	public static final String insertClSubRel = "INSERT INTO subject_class_rel (sub_id, cl_id, add_status, convert_to_grade, rel_status) values (?, ?, ?, ?, 'A')";
	
	public static final String updateClSubRel = "UPDATE subject_class_rel SET add_status = ?, convert_to_grade = ?, rel_status = ? WHERE subcl_id = ?";

	public static final String checkClSubRelExistance = "SELECT count(sub_id) sub_id FROM subject_class_rel WHERE sub_id = ? and cl_id = ?";
	
	public static final String getExamTypeTotalMarkList = "SELECT a.et_id, c.et_name, a.total_mark "
														+ "FROM exam_sub_rel a, subject_class_rel b, exams_type c "
														+ "WHERE a.subcl_id = b.subcl_id and a.et_id = c.et_id and b.subcl_id = ? and a.REL_STATUS = 'A' "
														+ "ORDER BY a.et_id asc";
	
	public static final String getClassNumberByClId = "SELECT class_number FROM class_list WHERE cl_id = ?";
}
