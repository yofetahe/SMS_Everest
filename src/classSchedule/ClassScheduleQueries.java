package classSchedule;

public class ClassScheduleQueries {
	
	public static final String getClassSchInfo= "SELECT noof_period, noof_days FROM class_schedule_info WHERE csi_status = 'A'";
	
	public static final String getClassSchDetail = "SELECT a.ti_id, c.fname, c.mname, c.gname, a.sub_id, d.sub_name, b.week_day, b.period, b.cs_id, e.class_name, f.cd_name " + 
													"FROM tech_assignment a, class_schedule b, teacher_information c, subject_list d, class_list e, class_detail f " +
													"WHERE a.ta_id = b.ta_id and a.ti_id = c.ti_id and a.cl_id = e.cl_id and b.cd_id = f.cd_id and a.sub_id = d.sub_id and a.ta_status = 'A' and a.cl_id = ? and b.cd_id = ? and b.academic_year = ?";
	
	public static final String getTeacherList = "SELECT b.ti_id, b.fname, b.mname, b.gname, a.ta_id " +
												"FROM tech_assignment a, teacher_information b " +
												"WHERE a.ti_id = b.ti_id and a.cl_id = ? and a.sub_id = ? and a.ti_id not in (select d.ti_id from class_schedule c, tech_assignment d where c.ta_id = d.ta_id and c.period = ? and c.academic_year = ? and c.week_day = ?) and a.ta_status = 'A' and b.ti_status = 'A'";
	
	public static final String getSelectedTeacherAndSubject = "SELECT b.fname, b.mname, b.gname, c.sub_name FROM tech_assignment a, teacher_information b, subject_list c WHERE a.ti_id = b.ti_id and a.sub_id = c.sub_id and a.ta_id = ?";
	
	public static final String checkClassSCheduleInfo = "SELECT a.cs_id FROM class_schedule a, tech_assignment b WHERE a.ta_id = b.ta_id and a.week_day = ? and a.period = ? and a.cd_id = ? and a.academic_year = ? and b.cl_id = ?";
	
	public static final String saveClassScheduleInfo = "INSERT INTO class_schedule(week_day, period, ta_id, cd_id, academic_year) VALUES(?, ?, ?, ?, ?)";
	
	public static final String getTeacherAssignmentId = "SELECT ta_id FROM tech_assignment WHERE cl_id = ? and sub_id = ? and ti_id = ?";
	
	public static final String updateClassScheduleInfo = "UPDATE class_schedule SET ta_id = ? WHERE cs_id = ?";
	
	public static final String teachersDashBoardInfo = 	"SELECT a.period, a.week_day, c.sub_name, d.class_name, e.cd_name " +
														"FROM class_schedule a, tech_assignment b, subject_list c, class_list d, class_detail e " +
														"WHERE a.ta_id = b.ta_id and c.sub_id = b.sub_id and b.cl_id = d.cl_id and e.cd_id = a.cd_id and b.ti_id = ? and a.academic_year = ?";
}
