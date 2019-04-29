package adminClass.student_material;

public class StudentMaterialQueries {
	
	public static final String getMaterialList = "SELECT ptm_id, ptm_name, ptm_desc, ptm_status FROM payment_type_material_list";
	
	public static final String saveStudentMaterial = "INSERT INTO payment_type_material_list(ptm_name, ptm_desc, ptm_status) VALUES(?, ?, 'A')";
	
	public static final String updateStudentMaterial = "UPDATE payment_type_material_list SET ptm_name = ?, ptm_desc = ?, ptm_status = ? WHERE ptm_id = ?";
	
	public static final String getMaterialClassRelList = "SELECT  b.cl_id, b.class_name, c.ptm_id, c.ptm_name, a.pmc_id, a.pmc_status, a.pay_amount " +
														"FROM payment_material_class_rel a, class_list b, payment_type_material_list c " +
														"WHERE a.cl_id = b.cl_id and c.ptm_id = a.ptm_id " +
														"ORDER BY b.cl_id";

	public static final String getActiveMaterialList = "SELECT ptm_id, ptm_name, ptm_desc, ptm_status FROM payment_type_material_list WHERE ptm_status = 'A'";
	
	public static final String saveMaterialClassRel = "INSERT INTO payment_material_class_rel(ptm_id, cl_id, pay_amount, pmc_status) VALUES(?, ?, ?, 'A')";
	
	public static final String checkMaterialClassRel = "SELECT ptm_id FROM payment_material_class_rel WHERE ptm_id = ? and cl_id = ?";
	
	public static final String checkActiveMaterialClassRel = "SELECT ptm_id FROM payment_material_class_rel WHERE ptm_id = ? and cl_id = ? and pmc_status = ?";
	
	public static final String updateMaterialClassRel = "UPDATE payment_material_class_rel SET ptm_id = ?, cl_id = ?, pay_amount = ?, pmc_status = ? WHERE pmc_id = ?";
}
