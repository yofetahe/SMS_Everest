package studentClass.disciplinary_action;

public class DisciplinaryBean {
	
	private String sda_id;
	private String sda_type;
	private String sda_reason;
	private String sda_date;
	private String sda_status;
	private String si_id;
	
	private String successful_save;
	private String successful_update;
	
	public String getSda_id() {
		return sda_id;
	}
	public void setSda_id(String sda_id) {
		this.sda_id = sda_id;
	}
	public String getSda_type() {
		return sda_type;
	}
	public void setSda_type(String sda_type) {
		this.sda_type = sda_type;
	}
	public String getSda_reason() {
		return sda_reason;
	}
	public void setSda_reason(String sda_reason) {
		this.sda_reason = sda_reason;
	}
	public String getSda_date() {
		return sda_date;
	}
	public void setSda_date(String sda_date) {
		this.sda_date = sda_date;
	}
	public String getSda_status() {
		return sda_status;
	}
	public void setSda_status(String sda_status) {
		this.sda_status = sda_status;
	}
	public String getSi_id() {
		return si_id;
	}
	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}
	public String getSuccessful_save() {
		return successful_save;
	}
	public void setSuccessful_save(String successful_save) {
		this.successful_save = successful_save;
	}
	public String getSuccessful_update() {
		return successful_update;
	}
	public void setSuccessful_update(String successful_update) {
		this.successful_update = successful_update;
	}
	
}
