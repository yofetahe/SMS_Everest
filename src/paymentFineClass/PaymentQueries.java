package paymentFineClass;

public class PaymentQueries {
	
	public static final String getStudentPayment = "SELECT month_id FROM payment_stud_rel a, payment_class_rel b WHERE si_id = ? and a.pc_id = b.pc_id and b.pt_id = 1 and pc_status = 'A' and ac_year = ? "
			+ "union "
			+ "SELECT month_id FROM payment_stud_rel a WHERE a.si_id = ? and pc_id is null and month_id is not null and ac_year = ? ORDER BY month_id";
	
	public static final String getStudentRegFeePayment = "SELECT a.reg_fee FROM payment_stud_rel a, payment_class_rel b WHERE a.pc_id = b.pc_id and b.pt_id = 5 and a.si_id = ? and a.ac_year = ?";
	
	public static final String getStudentTutorialPayment = "select month_id from payment_stud_rel a, payment_class_rel b where si_id = ? and a.pc_id = b.pc_id and b.pt_id = 2 and a.ac_year = ?";
	
	public static final String getStudentSummerPayment = "select month_id from payment_stud_rel a, payment_class_rel b where si_id = ? and a.pc_id = b.pc_id and b.pt_id = 3 and a.ac_year = ?";
	
	public static final String getStudentMaterialPayment = "select a.sm_id from payment_stud_rel a, payment_class_rel b where a.si_id = ? and a.pc_id = b.pc_id and b.pt_id = 4";
	
	public static final String getPaymentMonth = "select month_id, month, short_name from payment_month";
	
	public static final String getPaymentAmount = "SELECT pc_id, cl_id, pt_id, pay_amount, penality_max_date, penality_percent, academic_year FROM payment_class_rel WHERE cl_id = ? and pc_status = 'A' and pt_id = 1";
	
	public static final String getSpecialNeedPaymentAmount = "SELECT a.snc_id, a.pay_amount, a.penality_max_date, a.penality_percent, b.ac_year as academic_year, e.pc_id "
														+ "FROM special_need_category a, stud_special_need_required b, stud_registration c, clist_cdetail_rel d, payment_class_rel e " +
															"WHERE a.snc_id = b.snc_id and b.si_id = ? and b.ssnr_status = 'A' and b.si_id = c.si_id and c.clcd_id = d.clcd_id and d.cl_id = e.cl_id ";
	
	public static final String getTutorialPaymentAmount = "select pc_id, cl_id, pt_id, pay_amount, penality_max_date, penality_percent, academic_year from payment_class_rel where cl_id = ? and pc_status = 'A' and pt_id = 2";
	
	public static final String getSummerPaymentAmount = "select pc_id, cl_id, pt_id, pay_amount, penality_max_date, penality_percent, academic_year from payment_class_rel where cl_id = ? and pc_status = 'A' and pt_id = 3";
	
	public static final String getMaterialPaymentAmount = "select pc_id, cl_id, pt_id, pay_amount, penality_max_date, penality_percent, academic_year from payment_class_rel where cl_id = ? and pc_status = 'A' and pt_id = 4";
	
	public static final String saveMonthlyPayment = "INSERT INTO payment_stud_rel (si_id, pc_id, penality_amount, total_payment, payment_date, fm_receipt_no, cust_vat_reg_no, cust_tin, payment_mode, cheque_no, paystud_status, month_id, ac_year, create_by, create_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'A', ?, ?, ?, ?)";
	
	public static final String saveRegistrationFee = "INSERT INTO payment_stud_rel (si_id, pc_id, total_payment, payment_date, fm_receipt_no, cust_vat_reg_no, cust_tin, payment_mode, cheque_no, paystud_status, ac_year, reg_fee, create_by, create_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, 'A', ?, 'Yes', ?, ?)";
	
	public static final String saveOtherFee = "INSERT INTO payment_stud_rel (si_id, total_payment, payment_date, fm_receipt_no, cust_vat_reg_no, cust_tin, payment_mode, cheque_no, paystud_status, ac_year, reg_fee, create_by, create_date) values (?, ?, ?, ?, ?, ?, ?, ?, 'A', ?, 'Yes', ?, ?)";

	public static final String getPaidRegistrationFeeReceiptInfo = "SELECT a.fname, a.mname, a.gname, b.class_name, e.pc_id, e.pmc_id, e.snc_id, e.penality_amount, e.total_payment, e.payment_date, e.fm_receipt_no, e.cust_vat_reg_no, e.cust_tin, e.payment_mode, e.cheque_no, e.month_id, e.sm_id " +
			"FROM stud_information a, class_list b, stud_registration c, clist_cdetail_rel d, payment_stud_rel e " +
			"WHERE a.si_id = c.si_id and b.cl_id = d.cl_id and c.clcd_id = d.clcd_id and a.si_id = ? and b.cl_id = ? and e.reg_fee = 'Yes' and c.stud_status = 'Active' and e.si_id = a.si_id";
	
	public static final String saveMonthlySpecialPayment = "INSERT INTO payment_stud_rel (si_id, snc_id, penality_amount, total_payment, payment_date, fm_receipt_no, cust_vat_reg_no, cust_tin, payment_mode, cheque_no, paystud_status, month_id, ac_year, create_by, create_date, pc_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'A', ?, ?, ?, ?, ?)";
	
	public static final String getPaymentTypeList = "select pt_id, pt_name from payment_type";
	
	public static final String getPaymentInfoList = "SELECT a.pc_id, a.cl_id, c.class_name, a.pt_id, b.pt_name, a.pay_amount, a.penality_max_date, a.penality_percent, a.academic_year, a.pc_status FROM payment_class_rel a, payment_type b, class_list c WHERE a.pt_id = b.pt_id and a.cl_id = c.cl_id";
	
	public static final String savePaymentAdminInfo = "insert into payment_class_rel(cl_id, pt_id, pay_amount, penality_max_date, penality_percent, academic_year, pc_status) values(?, ?, ?, ?, ?, ?, 'A')";
	
	public static final String updatePaymentAdminInfo = "update payment_class_rel set cl_id = ?, pt_id = ?, pay_amount = ?, penality_max_date = ?, penality_percent = ?, academic_year = ?, pc_status = ? where pc_id = ?";
	
	public static final String selectPaymentAdminInfo = "SELECT pc_id FROM payment_class_rel WHERE cl_id = ? and pt_id = ? and academic_year = ? and pc_status = 'A' and pc_id <> ?";
	
	public static final String getMaterialList = "SELECT a.ptm_id, a.ptm_name, b.pay_amount, b.pmc_id "
												+ "FROM payment_type_material_list a, payment_material_class_rel b "
												+ "WHERE a.ptm_id = b.ptm_id and b.cl_id = ? and a.ptm_status = 'A'"
												+ "and b.pmc_id not in ( select d.pmc_id from payment_stud_rel c, payment_material_class_rel d, payment_type_material_list e where c.pmc_id = d.pmc_id and e.ptm_id = d.ptm_id and c.si_id = ? )";
	
	public static final String getPaidMaterialList = "SELECT d.ptm_id, d.pmc_id, e.ptm_name, d.pay_amount " +
													"FROM payment_stud_rel c, payment_material_class_rel d, payment_type_material_list e " +
													"WHERE c.pmc_id = d.pmc_id and e.ptm_id = d.ptm_id and c.si_id = ?";
	
	public static final String saveSelectedMaterialPayment = "INSERT INTO payment_stud_rel(si_id, pmc_id, total_payment, payment_date, fm_receipt_no, cust_vat_reg_no, cust_tin, payment_mode, cheque_no, paystud_status, create_by, create_date, ac_year) VALUES (?,?,?,?,?,?,?,?,?,'A',?,?,?)";
	
	public static final String getReceiptStudentClassInformation = "SELECT a.fname, a.mname, a.gname, b.class_name "
			+ "FROM stud_information a, class_list b, stud_registration c, clist_cdetail_rel d "
			+ "WHERE a.si_id = c.si_id and b.cl_id = d.cl_id and c.clcd_id = d.clcd_id and a.si_id = ? and b.cl_id = ? and c.stud_status = 'Active'";
	
	public static final String getPaidReceiptStudentClassInformation = "SELECT a.fname, a.mname, a.gname, b.class_name, e.pc_id, e.pmc_id, e.snc_id, e.penality_amount, e.total_payment, e.payment_date, e.fm_receipt_no, e.cust_vat_reg_no, e.cust_tin, e.payment_mode, e.cheque_no, e.month_id, e.sm_id, f.month, g.pt_id, i.pt_name, k.ptm_name, k.ptm_id "
+ "FROM stud_information a, class_list b, stud_registration c, clist_cdetail_rel d, payment_stud_rel e left join payment_month f on e.month_id = f.month_id left join payment_class_rel g on e.pc_id = g.pc_id left join payment_type i on g.pt_id = i.pt_id left join payment_material_class_rel j on e.pmc_id = j.pmc_id left join payment_type_material_list k on j.ptm_id = k.ptm_id "
+ "WHERE a.si_id = c.si_id and b.cl_id = d.cl_id and c.clcd_id = d.clcd_id and a.si_id = ? and b.cl_id = ? and e.fm_receipt_no = ? and c.stud_status = 'Active' and e.si_id = a.si_id";
	
	
	public static final String getSelectedMonth = "SELECT month FROM payment_month WHERE month_id = ?";
	
	public static final String getFMReceiptNo = "SELECT fm_receipt_no FROM payment_stud_rel WHERE si_id = ? and month_id = ?";
	
	public static final String getSchoolInformation = "SELECT school_name, slogan, tin_num, telephone, fax, web, email, pobox, fiscal_machine_no FROM school_information WHERE sch_inf_status = 'A'";
	
	public static final String insertSchoolInformation = "INSERT INTO school_information(school_name, slogan, tin_num, telephone, fax, web, email, pobox, fiscal_machine_no, sch_inf_status) VALUES(?,?,?,?,?,?,?,?,?,'A')";
	
	public static final String saveSchoolInformation = "UPDATE school_information SET slogan = ?, telephone = ?, fax = ?, web = ?, email = ?, pobox = ? WHERE sch_inf_id = 1";

	public static final String getLastInsertedFMReceiptNo = "SELECT MAX(fm_receipt_no) fm_receipt_no  FROM payment_stud_rel ";
	
	public static final String getClassRegistrationFee = "SELECT pc_id, pay_amount FROM payment_class_rel WHERE cl_id = ? and pt_id = ? and academic_year = ? and pc_status = 'A'";
}
