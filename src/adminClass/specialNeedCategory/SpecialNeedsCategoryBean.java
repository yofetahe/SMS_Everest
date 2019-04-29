package adminClass.specialNeedCategory;

public class SpecialNeedsCategoryBean {

	private String snc_id;
	private String snc_name;
	private String pay_amount;
	private String penality_max_date;
	private String penality_percent;
	private String snc_status;
	
	public String getSnc_id() {
		return snc_id;
	}
	public void setSnc_id(String snc_id) {
		this.snc_id = snc_id;
	}
	public String getSnc_name() {
		return snc_name;
	}
	public void setSnc_name(String snc_name) {
		this.snc_name = snc_name;
	}
	public String getSnc_status() {
		return snc_status;
	}
	public void setSnc_status(String snc_status) {
		this.snc_status = snc_status;
	}
	public String getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(String pay_amount) {
		this.pay_amount = pay_amount;
	}
	public String getPenality_max_date() {
		return penality_max_date;
	}
	public void setPenality_max_date(String penality_max_date) {
		this.penality_max_date = penality_max_date;
	}
	public String getPenality_percent() {
		return penality_percent;
	}
	public void setPenality_percent(String penality_percent) {
		this.penality_percent = penality_percent;
	}
	
}
