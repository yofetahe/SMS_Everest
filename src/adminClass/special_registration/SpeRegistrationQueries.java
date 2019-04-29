package adminClass.special_registration;

public class SpeRegistrationQueries {
	
	public static final String unregStudentListPerClassDetail = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, c.stud_status, a.photo_path, a.photo_name " +
				"FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
				"WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and c.academic_year = ? and c.stud_status = 'Active' and c.eduoff_reg <> 'A' "
				+ "ORDER BY a.fname, a.mname, a.gname";
	
	public static final String regStudentListPerClassDetail = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, c.stud_status, a.photo_path, a.photo_name " +
			"FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
			"WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and c.academic_year = ? and c.stud_status = 'Active' and c.eduoff_reg = 'A' "
			+ "ORDER BY a.fname, a.mname, a.gname";
	
	public static final String StudentSpecialRegistration = "UPDATE stud_registration SET eduoff_reg = 'A' WHERE si_id = ?";

	public static final String RemoveStudentSpecialRegistration = "UPDATE stud_registration SET eduoff_reg = 'I' WHERE si_id = ?";


}
