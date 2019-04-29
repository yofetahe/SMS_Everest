package adminClass.student_material;

public class StudentMaterialBean {

	//>>> payment_type_material_list table object
	private String ptm_id;
	private String ptm_name;
	private String ptm_desc;
	private String ptm_status;
	
	//>>> payment_material_class_rel table object
	//>>> ptm_id shared
	private String pmc_id;
	private String cl_id;	
	private String payment_amount;
	private String pmc_status;
	
	//class_list table object
	private String cl_name;
	
	public String getPtm_id() {
		return ptm_id;
	}
	public void setPtm_id(String ptm_id) {
		this.ptm_id = ptm_id;
	}
	public String getPtm_name() {
		return ptm_name;
	}
	public void setPtm_name(String ptm_name) {
		this.ptm_name = ptm_name;
	}
	public String getPtm_desc() {
		return ptm_desc;
	}
	public void setPtm_desc(String ptm_desc) {
		this.ptm_desc = ptm_desc;
	}
	public String getPtm_status() {
		return ptm_status;
	}
	public void setPtm_status(String ptm_status) {
		this.ptm_status = ptm_status;
	}
	public String getPmc_id() {
		return pmc_id;
	}
	public void setPmc_id(String pmc_id) {
		this.pmc_id = pmc_id;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public String getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(String payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getPmc_status() {
		return pmc_status;
	}
	public void setPmc_status(String pmc_status) {
		this.pmc_status = pmc_status;
	}
	public String getCl_name() {
		return cl_name;
	}
	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}
	
	
}
