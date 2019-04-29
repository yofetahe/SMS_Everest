package teacherClass;

public class TeacherQueries {
	
	public static final String getTeacherList = "select ti_id, fname, mname, gname, sex, dob, pob, nationality, id_no, ti_status, photo_name, photo_path from teacher_information";
	
	public static final String getActiveTeacherList = "select ti_id, fname, mname, gname, sex, dob, pob, nationality, id_no, ti_status, photo_name, photo_path from teacher_information where ti_status = 'A' order by fname";
	
	public static final String insertTeacherInfo = "insert into teacher_information(fname, mname, gname, sex, dob, pob, nationality, id_no, photo_name, photo_path, ti_status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'A')";
	
	// must be revisited - must be accessed by one user only
	public static final String getLastTeacherId = "SELECT max(ti_id) as ti_id FROM teacher_information";
	
	public static final String updateTeacherInfo = "update teacher_information set fname = ?, mname = ?, gname = ?, sex = ?, dob = ?, pob = ?, nationality = ?, id_no = ?, ti_status = ? where ti_id = ?";
	
	public static final String getTeachPerInfo = "select ti_id, fname, mname, gname, sex, dob, pob, nationality, id_no, ti_status from teacher_information where ti_id = ?";
	
	public static final String updateTeachPhotoInfo = "UPDATE teacher_information SET photo_name = ?, photo_path = ? WHERE ti_id = ?";
	
	public static final String getStudentNumPerYear = "SELECT male, female FROM v_male_female_teacher_num";

}
