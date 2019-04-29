package studentClass.student_registration;

public class StudentRegQueries {

	public static final String getStudList = "SELECT si_id, fname, mname, gname, ('New') as 'status'  " +
			"FROM stud_information " +
			"WHERE si_id not in (select si_id from stud_registration) and si_status = 'A' " +
			"UNION " +
			"SELECT a.si_id, a.fname, a.mname, a.gname, ('Passed') as 'status' " +
			"FROM stud_information a, stud_registration b, clist_cdetail_rel c " +
			"WHERE a.si_id = b.si_id and b.clcd_id = c.clcd_id and (b.stud_status = 'Passed' and c.cl_id = ? and b.academic_year = ? and a.si_id not in (select si_id from stud_registration where stud_status = 'Active' and academic_year = ?)) and a.si_status = 'A' " +
			"UNION " +
			"SELECT a.si_id, a.fname, a.mname, a.gname, ('Failed/Repeat') as 'status' " +
			"FROM stud_information a, stud_registration b, clist_cdetail_rel c " +
			"WHERE a.si_id = b.si_id and b.clcd_id = c.clcd_id and((b.stud_status = 'Failed' or b.stud_status = 'Repeat') and c.cl_id = ?) and a.si_status = 'A' and (b.academic_year = ? or b.academic_year = ?) and a.si_id not in (select si_id from stud_registration where (stud_status = 'Active' or stud_status = 'Passed') and academic_year = ?) " +
			"UNION " +
			"SELECT si_id, fname, mname, gname, ('Return') as 'status' " +
			"FROM stud_information " +
			"WHERE si_id in (select si_id from stud_registration where stud_status = 'DropOut') " +
			"and si_id not in (select si_id from stud_registration where stud_status = 'Active' and academic_year = ?) and si_status = 'A'";
	
	public static final String getNewStudList = "SELECT si_id, fname, mname, gname, mother_name, sex, dob, pob, id_no, si_status, 'New' as status "
											+ "FROM stud_information "
											+ "WHERE si_id not in (select si_id from stud_registration) and si_status = 'A'"
											+ "UNION " 
											+ "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.id_no, a.si_status, ('Failed/Repeat') as 'status' "
											+ "FROM stud_information a, stud_registration b, clist_cdetail_rel c "
											+ "WHERE a.si_id = b.si_id and b.clcd_id = c.clcd_id and((b.stud_status = 'Failed' or b.stud_status = 'Repeat' or b.stud_status = 'DropOut') and c.cl_id = ?) and a.si_status = 'A' and b.academic_year = ? "
											+ "UNION "
											+ "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.id_no, a.si_status, ('Back') as 'status' "
											+ "FROM stud_information a, stud_registration b, clist_cdetail_rel c "
											+ "WHERE a.si_id = b.si_id and b.stud_status = 'Back' and c.clcd_id = b.clcd_id and c.cl_id > ? and a.si_id not in (select si_id from stud_registration where stud_status = 'Active' and academic_year = ?)";
		
	public static final String registerStudList = "insert into stud_registration(clcd_id, reg_status, reg_date, si_id, stud_status, academic_year) values(?, 'A', ?, ?, 'Active', ?)";
	
	public static final String getSecList = "select a.cd_id, a.cd_name from class_detail a, clist_cdetail_rel b where a.cd_id = b.cd_id and b.cl_id = ?";
	
	public static final String getActiveSecList = "select a.cd_id, a.cd_name "
			+ "from class_detail a, clist_cdetail_rel b "
			+ "where a.cd_id = b.cd_id and b.cl_id = ? and rel_status = 'Active'";
	
	public static final String getClSecRelId = "select clcd_id, cl_capacity from clist_cdetail_rel where cd_id = ? and cl_id = ?";
	
	public static final String updateStudentClassRoom = "UPDATE stud_registration SET clcd_id = ? WHERE si_id = ? and academic_year = ? ";
	
	public static final String updateStudentFullYearResultStatus = "UPDATE stud_registration SET stud_status = ? WHERE si_id = ? and academic_year = ? ";
	
	public static final String getStudentClassIDByAcademicYearSiId = "SELECT b.CL_ID "
			+ "FROM stud_registration a, clist_cdetail_rel b "
			+ "WHERE ACADEMIC_YEAR = ? and a.CLCD_ID = b.CLCD_ID and a.SI_ID = ?";
}
