package adminClass.holiday;

public class holidayQueries {
	
	public static final String getHolidayList = "SELECT h_id, h_name, h_ethio_date, h_greg_date, h_fiscalyear, work_status, h_status FROM holidays WHERE h_fiscalyear = ?";
	
	public static final String dbHolidayList = "SELECT h_id, h_name, h_ethio_date, h_greg_date, h_fiscalyear, work_status, h_status FROM holidays WHERE h_status = 'A'";
	
	public static final String saveHoliday = "INSERT INTO holidays(h_name, h_ethio_date, h_greg_date, h_fiscalyear, work_status, h_status) VALUES(?, ?, ?, ?, ?, 'A')";
	
	public static final String updateHoliday = "UPDATE holidays SET h_name = ?, h_ethio_date = ?, h_greg_date = ?, h_fiscalyear = ?, work_status = ?, h_status = ? WHERE h_id = ?";

}
