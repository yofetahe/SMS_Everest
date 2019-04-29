package teacherClass.current_address;

public class TeacherCuraddQueries {
	
	public static final String getTchCuradd = "SELECT tca_id, sub_city, kebele, house_no, house_phone_no, mobile_no, email, tca_status FROM tech_current_address WHERE ti_id = ?";
	
	public static final String insertTchCuradd = "INSERT INTO tech_current_address (sub_city, kebele, house_no, house_phone_no, mobile_no, email, ti_id, tca_status) VALUES (?, ?, ?, ?, ?, ?, ?, 'A')";
	
	public static final String updateTchCuradd = "UPDATE tech_current_address set sub_city = ?, kebele = ?, house_no = ?, house_phone_no = ?, mobile_no = ?, email = ?, tca_status = ? WHERE tca_id = ?";

	public static final String getTeachersEmailAddress = "SELECT b.fname, b.mname, b.gname, a.email FROM tech_current_address a, teacher_information b WHERE a.ti_id = b.ti_id ";
	
}
