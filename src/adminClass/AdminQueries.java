package adminClass;

public class AdminQueries {
	
	public static final String validateUser = "SELECT ua_id, password, ti_id, nti_id  FROM user_account WHERE user_name = ? and ua_status = 'A' and si_id = 'N'";
	//public static final String validateUser = "CALL validate_User(?)";
	
	public static final String getUserCurrentPassword = "SELECT FROM WHERE";
	
	public static final String getUserAccountIdByUsername = "SELECT ua_id FROM user_account WHERE user_name = ? ";
	
	public static final String checkUserAccountExistance = "select ua_id from user_account where nti_id = ?";
	
	public static final String validateUserOldPassword = "SELECT ua_id FROM user_account WHERE password = ? and ua_id = ?";
	
	public static final String checkingExistedUserName = "select ua_id from user_account where user_name = ? and ua_id <> ?";
	
	public static final String getLoginUserRoleList = 	"SELECT a.m_id, b.m_name, c.ut_id " +
														"FROM utype_module_rel a, u_module b, user_type c " +
														"WHERE a.m_id = b.m_id and a.ut_id = c.ut_id and a.rel_status = 'A' and c.ut_id in ( SELECT d.ut_id " +
														"FROM uaccount_utype_rel d, user_account e " +
														"WHERE d.ua_id = e.ua_id and d.uaut_status = 'A' and e.ua_status = 'A' and e.user_name = ? )";
	
	public static final String getAllUserRoleListForSuperAdmin = "SELECT b.m_id, b.m_name, '1' as ut_id FROM u_module b WHERE b.M_STATUS = 'A'";
	
	public static final String getLoginUserTeacherId = "SELECT ti_id FROM user_account WHERE user_name = ?";
	
	public static final String getUserList = "SELECT a.ua_id, a.user_name, b.ut_name, b.ut_id, a.si_id, (select d.fname from stud_information d where d.si_id = a.si_id) as stud_name, a.ti_id, (select c.fname from teacher_information c where c.ti_id = a.ti_id) as tech_name, a.name" 
											+ " FROM user_account a, user_type b"
											+ " WHERE a.ut_id = b.ut_id and a.ua_id <> 1";
	
	public static final String getNoneAcademicUserList = "SELECT a.ua_id, a.nti_id, b.ut_id, b.ut_name, a.ua_status, d.nti_fname, d.nti_mname, d.nti_gname " +
														"FROM user_account a, user_type b, uaccount_utype_rel c, non_teacher_information d " +
														"WHERE a.nti_id <> 'N' and a.ti_id = 'N' and a.si_id = 'N' and a.ua_id = c.ua_id and c.ut_id = b.ut_id and c.uaut_status = 'A' and d.nti_id = a.nti_id and a.ua_id <> 1";
	
	public static final String getTechUserList = 	"SELECT a.ua_id, a.ti_id, c.fname, c.mname, c.gname, a.ua_status, d.ut_name, d.ut_id " +
													"FROM user_account a, teacher_information c, user_type d, uaccount_utype_rel e " +
													"WHERE a.ti_id <> 'N' and a.ti_id = c.ti_id and a.ua_id = e.ua_id and e.ut_id = d.ut_id and e.uaut_status = 'A'";
	
	public static final String getStudUserList = "SELECT a.ua_id, a.si_id, a.ut_id, b.ut_name, c.fname, c.mname, c.gname FROM user_account a, user_type b, stud_information c WHERE a.si_id <> 'N' and a.ut_id = b.ut_id and a.si_id = c.si_id";
	
	public static final String getUserTypeList = "SELECT ut_id, ut_name, ut_status FROM user_type WHERE ut_status = 'A' and related_with = 'NAC'";
	
	public static final String getUserTypeTeacherList = "SELECT ut_id, ut_name, ut_status FROM user_type WHERE ut_status = 'A' and related_with = 'TCHR'";
	
	public static final String saveNoneAcUser = "INSERT INTO user_account(user_name, password, nti_id, ua_status) VALUES(?, ?, ?, 'A')";
	
	//// must be revisited - must be accessed by one user only
	public static final String getLastSavedNoneAcUser = "SELECT max(ua_id) as last_id FROM user_account";
	
	public static final String saveUserAndUserTypeRelation = "INSERT INTO uaccount_utype_rel(ua_id, ut_id, uaut_status) VALUES(?, ?, 'A')";
	
	public static final String checkNoneAcUserNameChange = "SELECT c.nti_fname, c.nti_mname, c.nti_gname, b.ut_id FROM user_account a, uaccount_utype_rel b, non_teacher_information c WHERE a.ua_id = ? and a.ua_id = b.ua_id and b.uaut_status = 'A' and c.nti_id = a.nti_id";
	
	public static final String getPreviousUserAndUserTypeRelId = "select uaut_id from uaccount_utype_rel where ua_id = ? and uaut_status = 'A'";
	
	public static final String activateOldInactiveUAccountUTypeRel = "UPDATE uaccount_utype_rel SET uaut_status = 'A' WHERE uaut_id = ?";
	
	public static final String updateOldUAccountUTypeRel = " UPDATE uaccount_utype_rel SET uaut_status = 'I' WHERE uaut_id = ?";
	
	public static final String checkPreviousInactiveRelationship = "SELECT uaut_id FROM uaccount_utype_rel WHERE ua_id = ? and ut_id = ? and uaut_status = 'I'";
	
	public static final String checkPrevInactiveTuserAndUTypeRel = "SELECT uaut_id FROM uaccount_utype_rel WHERE ua_id = ? and ut_id = ? and uaut_status = 'I'";
	
	public static final String saveNewUAccountUTypeRel = "INSERT INTO uaccount_utype_rel(ua_id, ut_id, uaut_status) VALUES(?, ?, 'A')";
	
	public static final String updateNoneAcUser = "UPDATE user_account SET name = ? WHERE ua_id = ?";
	
	public static final String updateNoneAcUserStatus = "UPDATE user_account SET password = ?, ua_status = ? WHERE ua_id = ?";
	
	public static final String getActiveTeacherList = "select ti_id, fname, mname, gname from teacher_information where ti_status = 'A' and ti_id not in (select ti_id from user_account where ti_id <> 'N')";
	
	public static final String getRegisteredTeacherList = "SELECT ti_id, fname, mname, gname FROM teacher_information WHERE ti_status = 'A' and ti_id in (select ti_id from user_account where ti_id <> 'N')";
		
	public static final String saveTeacherUser = "INSERT INTO user_account(user_name, password, ti_id, ua_status) VALUES(?, ?, ?, 'A')";
	
	// must be revisited - must be accessed by one user only
	public static final String getLastSavedTeacherUser = "SELECT max(ua_id) FROM user_account";
	
	public static final String getTeacherId = "SELECT ti_id FROM user_account WHERE ua_id = ?";
	
	public static final String saveHroomTeacherAssignment = "INSERT INTO tech_hroom_assignment(ti_id, cl_id, cd_id, thra_status) VALUES (?, ?, ?, 'A')";
	
	public static final String getUserType = "SELECT a.ut_id FROM uaccount_utype_rel a, user_account b  WHERE a.ua_id = ? and b.ti_id = ? and a.ua_id = b.ua_id and a.uaut_status = 'A'";
	
	public static final String updateTeacherUserStatus = "UPDATE user_account SET password = ?, ua_status = ? WHERE ua_id = ?";
	
	public static final String getOldTeacherUserAndUserTypeRel = "SELECT uaut_id FROM uaccount_utype_rel WHERE ua_id = ? and uaut_status = 'A'";
		
	public static final String updateOldTeacherUserAndUserTypeRel = "UPDATE uaccount_utype_rel SET uaut_status = 'I' WHERE uaut_id = ?";
	
	public static final String updateOldInactiveTeacherUserAndUserTypeRel = "UPDATE uaccount_utype_rel SET uaut_status = 'A' WHERE uaut_id = ?";
	
	public static final String updateTeacherUser = "UPDATE user_account SET user_name = ?, password = ?, ti_id = ?, ua_status = ?, ut_id = ?  WHERE ua_id = ?";
	
	public static final String getAllUserList = "SELECT ut_id, ut_name, ut_status, related_with FROM user_type WHERE ut_id <> 1";
	
	public static final String saveUserList = "INSERT INTO user_type(ut_name, related_with, ut_status) VALUES(?, ?, 'A')";
	
	public static final String updateUserList = "UPDATE user_type SET ut_name = ?, ut_status = ?, related_with = ? WHERE ut_id = ?";
	
	public static final String getLoginUserFullName = "SELECT ua_id, ti_id, si_id, nti_id FROM user_account WHERE user_name = ?";
	
	public static final String getTeacherName = "SELECT fname, mname, gname FROM teacher_information WHERE ti_id = ?";
	
	public static final String getStudentName = "SELECT fname, mname, gname FROM stud_information WHERE si_id = ?";
	
	public static final String getNonteacherName = "SELECT nti_fname, nti_mname, nti_gname FROM non_teacher_information WHERE nti_id = ?";
	
	
	
	public static final String updateUserProfileInfo = "UPDATE user_account SET user_name = ?, password = ? WHERE ua_id = ?";
	
	public static final String getAnnualTermList = "SELECT at_id, at_name, at_status, academic_year FROM annual_terms";
	
	public static final String saveAnnualTerm = "INSERT INTO annual_terms(at_name, at_status, academic_year) VALUES(?, 'A', ?) ";
	
	public static final String updateAnnualTerm = "UPDATE annual_terms SET at_name = ?, at_status = ? WHERE at_id = ?";
	
	public static final String getModuleListPerGrade = 	"SELECT a.m_id, a.m_name, b.rel_status, b.utm_id " +
														"FROM u_module a, utype_module_rel b " +
														"WHERE a.m_id = b.m_id and b.ut_id = ?";
	
	public static final String getRemainingModuleList = "SELECT c.m_id, c.m_name FROM u_module c WHERE c.m_id not in (SELECT b.m_id FROM u_module a, utype_module_rel b WHERE a.m_id = b.m_id and b.ut_id = ?)and c.m_status = 'A'";
	
	public static final String saveModuleUtypeRel = "INSERT INTO utype_module_rel(UT_ID, M_ID, REL_STATUS) VALUES(?, ?, 'A')";
	
	public static final String updateModuleUtypeRel = "UPDATE utype_module_rel SET ut_id = ?, m_id = ?, rel_status = ? WHERE utm_id = ?";
	
	public static final String getAssignedHomeRoomTeacherList = "SELECT a.thra_id, a.ti_id, b.fname, b.mname, b.gname, a.cl_id, c.class_name, a.cd_id, d.cd_name, a.thra_status " +
																"FROM tech_hroom_assignment a, teacher_information b, class_list c, class_detail d " +
																"WHERE a.academic_year = ? and a.ti_id = b.ti_id and a.cl_id = c.cl_id and a.cd_id = d.cd_id";

	public static final String getUnassignedHomeRooomTeacherList = "SELECT ti_id, fname, mname, gname " +
																"FROM teacher_information " +
																"WHERE ti_status = 'A' AND ti_id not in (select ti_id from tech_hroom_assignment where thra_status = 'A'  and academic_year = ?)";
	
	public static final String checkClassRoomTeacherAssignment = "SELECT thra_id FROM tech_hroom_assignment WHERE cl_id = ? and cd_id = ? and academic_year = ? and thra_status = 'A'";
	
	public static final String saveHomeRoomTeacherAssignment = "INSERT INTO tech_hroom_assignment(ti_id, cl_id, cd_id, academic_year, thra_status) VALUES(?, ?, ?, ?, 'A')";
	
	public static final String getTeacherAssignmentInfo = "SELECT a.thra_id, a.ti_id, b.fname, b.mname, b.gname, a.cl_id, c.class_name, a.cd_id, d.cd_name, a.thra_status FROM tech_hroom_assignment a, teacher_information b, class_list c, class_detail d WHERE a.ti_id = b.ti_id and a.cl_id = c.cl_id and a.cd_id = d.cd_id and a.thra_id = ? ";
	
	public static final String updateTeacherAssignment = "UPDATE tech_hroom_assignment SET cl_id = ?, cd_id = ?, thra_status = ? WHERE thra_id = ?";

	public static final String getHomeRoomTeacherName = "SELECT a.thra_id, a.ti_id, b.fname, b.mname, b.gname, a.cl_id, c.class_name, a.cd_id, d.cd_name, a.thra_status " +
			"FROM tech_hroom_assignment a, teacher_information b, class_list c, class_detail d " +
			"WHERE a.cl_id = ? and a.cd_id = ? and a.academic_year = ? and a.ti_id = b.ti_id and a.cl_id = c.cl_id and a.cd_id = d.cd_id and thra_status = 'A'";
	
	public static final String resetUserPassword = "UPDATE user_account SET user_name = ?, password = ? WHERE ua_id = ? ";
}
