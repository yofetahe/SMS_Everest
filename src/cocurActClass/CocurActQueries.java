package cocurActClass;

public class CocurActQueries {
	
	public static final String getClidByLoginUser = "SELECT responsibility_id " +
													"FROM cca_teacher_responsibility " +
													"WHERE ti_id in (select ti_id from user_account where user_name = ?)";
	
	public static final String getCocurActivity = 	"SELECT ca_id, ca_activity, ca_activity_desc, ca_datefrom, ca_dateto, academic_year, clubhead_comment, ca_status, clb_id " +
													"FROM cca_club_activities WHERE clb_id = ? and academic_year = ?";
	
	public static final String getAllCocurActivity = "SELECT ca_id, ca_activity, ca_activity_desc, ca_datefrom, ca_dateto, academic_year, clubhead_comment, ca_status, clb_id " +
													 "FROM cca_club_activities WHERE academic_year = ?";
	
	public static final String saveCocurActivity = "INSERT INTO cca_club_activities(clb_id, ca_activity, ca_activity_desc, ca_datefrom, ca_dateto, academic_year, ca_status) VALUES(?, ?, ?, ?, ?, ?, 'A')";
	
	public static final String updateCocurActivity = "UPDATE cca_club_activities SET ca_activity = ?, ca_activity_desc = ?, ca_datefrom = ?, ca_dateto = ?, academic_year = ?, ca_status = ? WHERE ca_id = ?";
	
	public static final String checkUserNameUserType = 	"SELECT a.ua_id, b.ut_id " +
														"FROM user_account a, uaccount_utype_rel b " +
														"WHERE a.ua_id = b.ua_id and a.user_name = ? and b.uaut_status = 'A'";
	
//	public static final String getMemberList = 	"SELECT a.cm_id, a.mc_id, a.m_id, b.fname, b.mname, b.gname, a.clb_id, c.clb_name, a.cm_reasontojoin, a.cm_evaluation, a.cm_status " +
//												"FROM cca_member a, stud_information b, cca_club c " +
//												"WHERE a.m_id = b.si_id and c.clb_id = a.clb_id and a.clb_id = ? and a.academic_year = ?";
	
	public static final String getMemberList =	"SELECT '' as cm_id, '' as mc_id, a.ti_id as m_id, a.fname, a.mname, a.gname, b.responsibility_id as clb_id, c.clb_name as clb_name, 'Participant teacher or stuff member' as cm_reasontojoin, '' as cm_evaluation, b.tr_status as cm_status, 'teacher' as label " +
												"FROM teacher_information a, cca_teacher_responsibility b,  cca_club c " +
												"WHERE b.responsibility_id = ? and role_id = 3 and c.clb_id = b.responsibility_id and a.ti_id = b.ti_id and b.academic_year = ? " +
												"union " +
												"SELECT a.cm_id, a.mc_id, a.m_id, b.fname, b.mname, b.gname, a.clb_id, c.clb_name, a.cm_reasontojoin, a.cm_evaluation, a.cm_status, 'student' as label " +
												"FROM cca_member a, stud_information b, cca_club c " +
												"WHERE a.m_id = b.si_id and c.clb_id = a.clb_id and a.clb_id = ? and a.academic_year = ?";
	
	public static final String saveClubMember = "INSERT INTO cca_member(mc_id, m_id, clb_id, cm_reasontojoin, cm_evaluation, academic_year, cm_status) VALUES(1, ?, ?, ?, ?, ?, 'A')";
	
	public static final String updateClubMember = "UPDATE cca_member SET cm_reasontojoin = ?, cm_status = ? WHERE cm_id = ?";
	
	public static final String addClubMemberEvaluation = "UPDATE cca_member SET cm_evaluation = ? WHERE cm_id = ?";
	
	public static final String updateClubActivitiesClubHeadCom = "UPDATE cca_club_activities SET clubhead_comment = ? WHERE ca_id = ?";
	
}
