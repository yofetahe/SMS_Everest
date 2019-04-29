package teacherClass.work_experience;

public class WorkExperienceQueries {
	
	public static final String getEduBackgroundList = "SELECT twe_id, name_of_institute, job_title, date_from, date_to, twe_status FROM tech_work_exp WHERE ti_id = ?";
	
	public static final String saveWorkExp = "INSERT INTO tech_work_exp(name_of_institute, job_title, date_from, date_to, twe_status, ti_id) VALUES(?,?,?,?,'A',?)";
	
	public static final String updateWorkExp = "UPDATE tech_work_exp SET name_of_institute = ?, job_title = ?, date_from = ?, date_to = ?, twe_status = ?  WHERE twe_id = ?";

}
