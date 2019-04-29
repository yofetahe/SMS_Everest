package studentClass.current_address;

public class CurrentAddressQueries {
	
	public static final String getCurAddressPerStud = "SELECT a.sca_id, a.si_id, a.sub_city, a.kebele, a.house_no, a.home_phone_no, a.email, a.email_2, a.sca_status FROM stud_current_address a WHERE a.si_id = ?";
	
	public static final String saveCurrentAddress = "INSERT INTO stud_current_address(sub_city, kebele, house_no, home_phone_no, email, email_2, sca_status, si_id) VALUES (?, ?, ?, ?, ?, ?, 'A', ?)";
	
	public static final String updateCurAdd = "UPDATE stud_current_address SET sub_city = ?, kebele = ?, house_no = ?, home_phone_no = ?, email = ?, email_2 = ? where sca_id = ?";
	
	public static final String checkCurrentAddress = "select si_id from stud_current_address where si_id = ?";

	public static final String getParentsEmailAddress = "SELECT a.fname, a.mname, a.gname, CONCAT(b.email, ', ', b.email_2) email FROM stud_information a, stud_current_address b, stud_registration c WHERE a.si_id = b.si_id and a.si_id = c.si_id and c.clcd_id = (select clcd_id from clist_cdetail_rel where cl_id = ? and cd_id = ?) and c.academic_year = ? and c.stud_status = 'Active' ";
	
}
