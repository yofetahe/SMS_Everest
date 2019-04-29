package teacherClass.disciplinary_action;

public class TeacherDisactQueries {
	
	public static final String getDisActList = "SELECT tda_id, tda_type, tda_reason, tda_date, tda_status FROM tech_displinary_action WHERE ti_id = ?";
	
	public static final String insertDisAct = "INSERT INTO tech_displinary_action (tda_type, tda_reason, tda_date, tda_status, ti_id) VALUES (?, ?, ?, 'A', ?)";
	
	public static final String updateDisAct = "UPDATE tech_displinary_action SET tda_type = ?, tda_reason = ?, tda_date = ?, tda_status = ? WHERE tda_id = ?";

}
