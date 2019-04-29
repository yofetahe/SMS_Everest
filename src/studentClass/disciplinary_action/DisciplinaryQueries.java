package studentClass.disciplinary_action;

public class DisciplinaryQueries {
	
	public static final String getDisActList = "SELECT a.sda_id, a.sda_type, a.sda_reason, a.sda_date, a.sda_status FROM stud_disciplinary_action a WHERE a.si_id = ?";
	
	public static final String insertDisAct = "INSERT INTO stud_disciplinary_action (SDA_TYPE, SDA_REASON, SDA_DATE, SDA_STATUS, SI_ID) VALUES (?, ?, ?, 'A', ?)";
	
	public static final String updateDisAct = "UPDATE stud_disciplinary_action SET sda_type = ?, sda_reason = ?, sda_date = ?, sda_status = ? WHERE sda_id = ?";

}
