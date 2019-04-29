package studentClass.emergency_contact;

public class EmergencyContactQueries {
	
	public static final String getContactList = "SELECT a.sec_id, a.si_id, a.cont_name, a.relationship, a.mob_no, a.office_phone_no, a.home_phone_no, a.sec_status FROM stud_emergency_contact a WHERE a.si_id = ?";
	
	public static final String updateEmergContact = "UPDATE stud_emergency_contact SET cont_name = ?, relationship = ?, mob_no = ?, office_phone_no = ?, home_phone_no = ?, sec_status = ? WHERE sec_id = ?";
	
	public static final String insertEmergContact = "INSERT INTO stud_emergency_contact(cont_name, relationship, mob_no, office_phone_no, home_phone_no, sec_status, si_id) VALUES(?, ?, ?, ?, ?, 'A', ?)";

	public static final String getAllContactListPerClass = "SELECT a.sec_id, a.si_id, a.cont_name, a.relationship, a.mob_no, a.office_phone_no, a.home_phone_no, a.sec_status "
			+ "FROM stud_emergency_contact a, stud_registration b "
			+ "WHERE a.si_id = b.si_id and b.clcd_id in (select clcd_id from clist_cdetail_rel where cl_id = ? and cd_id = ?) ";
	
	public static final String getAllContactListForRemaining = "SELECT a.sec_id, a.si_id, a.cont_name, a.relationship, a.mob_no, a.office_phone_no, a.home_phone_no, a.sec_status "
			+ "FROM stud_emergency_contact a "
			+ "WHERE a.si_id = ?";
	
	public static final String checkEmergencyContactBySiid = "SELECT COUNT(si_id) count FROM stud_emergency_contact WHERE si_id = ? and relationship = ?";
}
