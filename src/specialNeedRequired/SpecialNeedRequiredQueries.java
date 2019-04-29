package specialNeedRequired;

public class SpecialNeedRequiredQueries {
	
	public static final String getStudSpecialNeedRequirment = "SELECT ssnr_id, si_id, snc_id, ac_year, ssnr_status FROM stud_special_need_required WHERE si_id = ? and ssnr_status = 'A'";
	
	public static final String saveStudSpecialNeedRequirment = "INSERT INTO stud_special_need_required(si_id, snc_id, ac_year, create_by, create_date, ssnr_status) VALUES (?, ?, ?, ?, ?, 'A')";
	
	public static final String updateStudSpecialNeedRequirment = "UPDATE stud_special_need_required SET snc_id = ?, ac_year = ?, update_by = ?, update_date = ? WHERE ssnr_id = ?";
	
	public static final String disableStudSpecialNeedRequirment = "UPDATE stud_special_need_required SET ssnr_status = 'I', update_by = ?, update_date = ? WHERE ssnr_id = ?";
}
