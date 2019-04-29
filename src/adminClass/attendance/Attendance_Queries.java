package adminClass.attendance;

public class Attendance_Queries {
	
	public static final String getAttendanceList = "SELECT attype_id, attype_description, attype_code, attype_status FROM da_attendance_type";
	
	public static final String saveAttendanceType = "INSERT INTO da_attendance_type (attype_description, attype_code, attype_status) VALUES (?, ?, 'A') ";
	
	public static final String updateAttendanceType = "UPDATE da_attendance_type SET attype_description = ?, attype_code = ?, attype_status = ? WHERE attype_id = ?";

}
