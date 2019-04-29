package nonteacherClass;

public class NonteacherQueries {
	
	public static final String getNonteacherList = "SELECT nti_id, nti_fname, nti_mname, nti_gname, nti_sex, nti_email, nti_position, nti_id_no, nti_status FROM non_teacher_information WHERE nti_id <> 1";
	
	public static final String getActiveNonteacherList = "SELECT nti_id, nti_fname, nti_mname, nti_gname, nti_sex, nti_position, nti_id_no, nti_status FROM non_teacher_information WHERE nti_status = 'A' and nti_id <> 1 and nti_id not in (select nti_id from user_account where nti_id <> '')";
	
	// must be revisited - must be accessed by one user only
	public static final String getLastNonteacherIdNo = "SELECT max(nti_id) as nti_id FROM non_teacher_information";
	
	public static final String saveNonteacher = "INSERT INTO non_teacher_information(nti_fname, nti_mname, nti_gname, nti_sex, nti_email, nti_position, nti_id_no, nti_status) VALUES(?, ?, ?, ?, ?, ?, ?, 'A') ";
	
	public static final String updateNonteacher = "UPDATE non_teacher_information SET nti_fname = ?, nti_mname = ?, nti_gname = ?, nti_sex = ?, nti_email = ?, nti_position = ?, nti_status = ? WHERE nti_id = ?";

}
