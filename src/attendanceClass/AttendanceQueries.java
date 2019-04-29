package attendanceClass;

public class AttendanceQueries {
	
	public static final String getClassSize = "SELECT cl_capacity FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ? and rel_status = 'Active'";
	
	public static final String getClCdId = "SELECT si_id FROM da_attendance WHERE clcd_id in (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and att_date = ? and si_id = ?";
	
	public static final String getAttendanceTypeList = "SELECT attype_id, attype_description, attype_code FROM da_attendance_type WHERE attype_status = 'A'";
	
	public static final String saveAttendanceData = "INSERT INTO da_attendance(si_id, att_date, att_reason, clcd_id, academic_year, attype_id, at_id, ti_id, att_status) values(?, ?, ?, (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?), ?, ?, ?, (SELECT ua_id FROM user_account WHERE user_name like ?), 'A')";
	
	public static final String getAbsentStudentList = "SELECT a.att_id, a.si_id, b.fname, b.mname, b.gname, a.att_date, a.att_reason FROM da_attendance a, stud_information b WHERE a.clcd_id in (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and a.att_date = ? and a.si_id = b.si_id";
	
	public static final String getStudAbsentDateList = "SELECT att_date FROM da_attendance WHERE si_id = ? and academic_year = ? and at_id = ? and att_status = 'A' and att_reason = 'N'";
	
	public static final String getAllStudAbsentDateList = "SELECT att_date FROM da_attendance WHERE si_id = ? and academic_year = ? and att_status = 'A' and att_reason = 'N'";
	
	public static final String saveAttendaceReason = "UPDATE da_attendance SET att_reason = ? WHERE att_id = ?";

}
