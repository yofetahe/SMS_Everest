package adminClass.school_event;

public class SchoolEventQueries {
	
	public static final String getSchEventList = "SELECT se_id, se_name, se_description, se_ethio_date, se_greg_date, se_fiscalyear, se_status FROM school_event WHERE se_fiscalyear = ? Order by se_fiscalyear";
	
	public static final String dbSchEventList = "SELECT se_id, se_name, se_description, se_ethio_date, se_greg_date, se_fiscalyear, se_status FROM school_event WHERE se_status = 'A' and se_fiscalyear = ? Order by se_fiscalyear";
	
	public static final String saveSchoolEvent = "INSERT INTO school_event(se_name, se_description, se_ethio_date, se_greg_date, se_fiscalyear, se_status) VALUES(?, ?, ?, ?, ?, 'A')";
	
	public static final String updateSchoolEvent = "UPDATE school_event SET se_name = ?, se_description = ?, se_ethio_date = ?, se_greg_date = ?, se_fiscalyear = ?, se_status = ? WHERE se_id = ?";

}
