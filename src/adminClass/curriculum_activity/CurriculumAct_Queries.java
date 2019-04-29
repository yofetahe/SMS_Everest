package adminClass.curriculum_activity;

public class CurriculumAct_Queries {
	
	public static final String getDepartmentList = "SELECT dep_id, dep_name, dep_desc, dep_status FROM cca_department";
	
	public static final String saveDepartment = "INSERT INTO cca_department(dep_name, dep_desc, dep_status) VALUES(?, ?, 'A')";
	
	public static final String getActiveDepartmentList = "SELECT dep_id, dep_name, dep_desc, dep_status FROM cca_department WHERE dep_status = 'A'";
	
	public static final String updateDepartment = "UPDATE cca_department SET dep_name = ?, dep_desc = ?, dep_status = ? WHERE dep_id = ?";
	
	public static final String getClubList = "SELECT a.clb_id, a.clb_name, a.clb_desc, a.clb_status, b.dep_name, b.dep_id FROM cca_club a, cca_department b WHERE a.dep_id = b.dep_id";
	
	public static final String getActiveClubList = "SELECT a.clb_id, a.clb_name, a.clb_desc, a.clb_status FROM cca_club a WHERE a.clb_status = 'A'";
	
	public static final String saveClub = "INSERT INTO cca_club(clb_name, clb_desc, dep_id, clb_status) VALUES(?, ?, ?, 'A')";
	
	public static final String updateClub = "UPDATE cca_club SET clb_name = ?, clb_desc = ?, clb_status = ? WHERE clb_id = ?";
	
	public static final String getTeacherList = "SELECT ti_id, fname, mname, gname FROM teacher_information WHERE ti_id not in (select ti_id from cca_teacher_responsibility where academic_year = ? and tr_status = 'A')";
	
	public static final String getAllTeacherList = "SELECT ti_id, fname, mname, gname FROM teacher_information WHERE ti_status = 'A'";
	
	public static final String getTeacherRoleList = "SELECT role_id, role_name FROM cca_role WHERE role_status = 'A'";
	
	public static final String saveTeacherResponsibility = "INSERT INTO cca_teacher_responsibility(ti_id, role_id, responsibility_id, academic_year, tr_status) VALUES(?, ?, ?, ?, 'A')";
	
	public static final String getAssignTeacherRespList = "SELECT b.ti_id, a.tr_id, b.fname, b.mname, b.gname, c.role_name, c.role_id, d.dep_name as dc, a.academic_year, a.tr_status "
			                                            + "FROM cca_teacher_responsibility a, teacher_information b, cca_role c, cca_department d "
			                                            + "WHERE a.role_id = 1 and a.ti_id = b.ti_id and a.role_id = c.role_id and d.dep_id = a.responsibility_id and a.academic_year = ? "
			                                            + "union " 
			                                            + "SELECT b.ti_id, a.tr_id, b.fname, b.mname, b.gname, c.role_name, c.role_id, d.clb_name as dc, a.academic_year, a.tr_status "
			                                            + "FROM cca_teacher_responsibility a, teacher_information b, cca_role c, cca_club d "
			                                            + "WHERE (a.role_id = 2 or a.role_id = 3) and a.ti_id = b.ti_id and a.role_id = c.role_id and d.clb_id = a.responsibility_id and a.academic_year = ?";
	
	public static final String updateTeacherResponsibility = "UPDATE cca_teacher_responsibility SET ti_id = ?, role_id = ?, responsibility_id = ?, academic_year = ?, tr_status = ? WHERE tr_id = ?";
}
