package adminClass.behavioural_evaluation;

public class BehEval_Queries {
	
	public static final String getTraitList = "SELECT bt_id, bt_title, bt_desc, bt_status FROM beval_traits";
	
	public static final String saveTrait = "INSERT INTO beval_traits(bt_title, bt_desc, bt_status) VALUES(?, ?, 'A')";
	
	public static final String updateTrait = "UPDATE beval_traits SET bt_title = ?, bt_desc = ?, bt_status = ? WHERE bt_id = ? ";
	
	public static final String getGradeList = "SELECT bg_id, bg_name, bg_desc, bg_status FROM beval_grade";
	
	public static final String saveGrade = "INSERT INTO beval_grade(bg_name, bg_desc, bg_status) VALUES(?, ?, 'A')";
	
	public static final String updateGrade = "UPDATE beval_grade SET bg_name = ?, bg_desc = ?, bg_status = ? WHERE bg_id = ? ";
	
	public static final String getTraitGradeRelList = "SELECT b.btbg_id, a.bg_id, a.bg_name, a.bg_desc, b.rel_status " +
														"FROM beval_grade a, btraits_bgrades_rel b " +
														"WHERE bt_id = ? and a.bg_id = b.bg_id";
	
	public static final String getUnrelatedGradeList = "SELECT bg_id, bg_name, bg_desc, bg_status FROM beval_grade WHERE bg_id not in (SELECT a.bg_id " +
														"FROM beval_grade a, btraits_bgrades_rel b " +
														"WHERE bt_id = ? and a.bg_id = b.bg_id)";
	
	public static final String saveTraitGradeRel = "INSERT INTO btraits_bgrades_rel(bt_id, bg_id, rel_status) VALUES(?, ?, 'A')";
	
	public static final String updateTraitGradeRel = "UPDATE btraits_bgrades_rel SET rel_status = ? WHERE btbg_id = ?";
	
	public static final String getRelatedTraitList = "SELECT a.btc_id, b.bt_title, a.rel_status FROM btraits_class_rel a, beval_traits b WHERE a.bt_id = b.bt_id and a.cl_id = ?";
	
	public static final String getUnelatedTraitList = "SELECT bt_id, bt_title FROM beval_traits WHERE bt_id not in (SELECT bt_id FROM btraits_class_rel WHERE cl_id = ?)";
	
	public static final String saveClassTraitRel = "INSERT INTO btraits_class_rel(cl_id, bt_id, rel_status) VALUES(?, ?, 'A')";
	
	public static final String updateClassTraitRel = "UPDATE btraits_class_rel SET rel_status = ? WHERE btc_id = ?";
}
