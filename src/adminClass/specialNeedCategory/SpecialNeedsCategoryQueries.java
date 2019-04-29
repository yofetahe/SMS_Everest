package adminClass.specialNeedCategory;

public class SpecialNeedsCategoryQueries {
	
	public static final String getSpecialNeedsCategoryList = "SELECT snc_id, snc_name, snc_status, pay_amount FROM special_need_category WHERE snc_status = 'A'";
	
	public static final String getAllSpecialNeedsCategoryList = "SELECT snc_id, snc_name, pay_amount, penality_max_date, penality_percent, snc_status FROM special_need_category";
	
	public static final String saveSpecialNeedsCategory = "INSERT INTO special_need_category(snc_name, pay_amount, penality_max_date, penality_percent, snc_status) VALUES(?, ?, ?, ?, 'A')";
	
	public static final String updateSpecialNeedsCategory = "UPDATE special_need_category SET snc_name = ?, pay_amount = ?, penality_max_date = ?, penality_percent = ?, snc_status = ? WHERE snc_id = ?";

}
