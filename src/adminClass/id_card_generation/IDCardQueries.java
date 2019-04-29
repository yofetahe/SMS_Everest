package adminClass.id_card_generation;

public class IDCardQueries {
	
	public static final String getStudListWithID = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, c.stud_status, a.photo_path, a.photo_name " +
			"FROM stud_information a, clist_cdetail_rel b, stud_registration c " +
			"WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and c.academic_year = ? and c.stud_status = 'Active' and a.si_id in (SELECT si_id FROM stud_id_card WHERE sid_status = 'Active') "
			+ "ORDER BY a.fname, a.mname, a.gname";
	
//	public static final String getStudListWithoutID = "SELECT distinct a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, c.stud_status, a.photo_path, a.photo_name, d.class_name, f.stud_photo, g.cd_name "
//			+"FROM clist_cdetail_rel b, stud_registration c, class_list d, student_photo_information f, stud_information a, stud_emergency_contact e, class_detail g "
//			+"WHERE a.si_id = c.si_id and b.cl_id = d.cl_id and c.clcd_id = b.clcd_id and b.cd_id = g.cd_id and a.si_id = e.si_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and c.academic_year = ? and c.stud_status = 'Active' and a.si_id not in (SELECT si_id FROM stud_id_card WHERE sid_status = 'Active') and a.si_id = f.si_id "
//			+"ORDER BY a.fname, a.mname, a.gname";
	
	public static final String getStudListWithoutID = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, a.photo_path, a.photo_name, c.stud_status, d.class_name, f.stud_photo, max(f.spi_id) spi_id, g.cd_name "
			+ "FROM stud_information a, clist_cdetail_rel b, stud_registration c, class_list d, student_photo_information f, class_detail g "
			+ "WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.cl_id = d.cl_id and b.cd_id = g.cd_id and a.si_id = f.si_id and b.clcd_id = (SELECT clcd_id FROM clist_cdetail_rel WHERE cl_id = ? and cd_id = ?) and c.academic_year = ? and c.stud_status = 'Active' and a.si_id not in (SELECT si_id FROM stud_id_card WHERE sid_status = 'Active') and a.SI_ID in (select SI_ID from stud_emergency_contact) "
			+ "group by si_id "
			+ "ORDER BY a.fname, a.mname, a.gname";
				
	public static final String saveStudWithID = "INSERT INTO stud_id_card(si_id, sid_status, print_date) VALUES(?, ?, ?)";

	public static final String getRemainingStudListWithoutID = "SELECT a.si_id, a.fname, a.mname, a.gname, a.mother_name, a.sex, a.dob, a.pob, a.nationality, a.id_no, a.si_status, a.photo_path, a.photo_name, c.stud_status, d.class_name, f.stud_photo, max(f.spi_id) spi_id, g.cd_name "
			+ "FROM stud_information a, clist_cdetail_rel b, stud_registration c, class_list d, student_photo_information f, class_detail g "
			+ "WHERE a.si_id = c.si_id and c.clcd_id = b.clcd_id and b.cl_id = d.cl_id and b.cd_id = g.cd_id and a.si_id = f.si_id and c.academic_year = ? and c.stud_status = 'Active' and a.si_id not in (SELECT si_id FROM stud_id_card WHERE sid_status = 'Active') and a.SI_ID in (select SI_ID from stud_emergency_contact) "
			+ "GROUP BY si_id "
			+ "ORDER BY a.fname, a.mname, a.gname";

}
