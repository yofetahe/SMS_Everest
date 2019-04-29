package teacherClass.edu_background;

public class EduBackgroundQueries {
	
	public static final String getEduBackgroundList = "SELECT teb_id, field_of_study, name_of_institiute, graduation_date, award, teb_status FROM tech_edu_background WHERE ti_id = ?";
	
	public static final String saveEduBackground = "INSERT INTO tech_edu_background(field_of_study, name_of_institiute, graduation_date, award, ti_id, teb_status) VALUES(?,?,?,?,?,'A')";
	
	public static final String updateEduBackground = "UPDATE tech_edu_background SET field_of_study = ?, name_of_institiute = ?, graduation_date = ?, award = ?, teb_status = ?  WHERE teb_id = ?";

}
