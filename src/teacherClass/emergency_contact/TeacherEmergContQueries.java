package teacherClass.emergency_contact;

public class TeacherEmergContQueries {
	
	public static final String getEmergCont = "SELECT tec_id, cont_name, relationship, mobile_no, home_phone_no, office_phone_no, tec_status FROM tech_emergency_contact WHERE ti_id = ?";
	
	public static final String insertEmergCont = "INSERT INTO tech_emergency_contact (cont_name, relationship, mobile_no, home_phone_no, office_phone_no, tec_status, ti_id) values (?, ?, ?, ?, ?, 'A', ?)";
	
	public static final String updateEmergCont = "UPDATE tech_emergency_contact SET cont_name = ?, relationship = ?, mobile_no = ?, home_phone_no = ?, office_phone_no = ?, tec_status = ? WHERE tec_id = ?";

}
