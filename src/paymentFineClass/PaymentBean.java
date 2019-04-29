package paymentFineClass;

import reportClass.ReportBean;

public class PaymentBean {

	//>>> payment_class_rel table object
	private String class_id;
	private String pt_id;
	private String pay_amount;
	private String penality_max_date;
	private String penality_percent;
	private String academic_year;
	private String pc_status;

	//>>> payment_stud_rel table object
	private String pc_id;
	private String si_id;
	private String snc_id;
	private String pcm_id;
	private String penality_amount;
	private String ac_year;
	private String total_payment;
	private String payment_date;
	private int fm_receipt_no;
	private String cust_vat_reg_no;
	private int cust_tin;
	private String payment_mode;
	private String cheque_no;

	//>>> payment_material_class_rel table object
	//>>> class_id also needed
	private String ptm_id;
	private String material_pay_amount;

	//>>> payment_type_material_list table object
	private String ptm_name;

	//>>> payment_type table object
	private String pt_name;
	private String payment_type;

	//>>> payment_month table object
	private String month_id;
	private String month;
	private String month_full;
	private String month_short;

	private String sep;
	private String oct;
	private String nov;
	private String dec;
	private String jan;
	private String feb;
	private String mar;
	private String apr;
	private String may;
	private String jun;
	private String jul;
	private String aug;

	// school_information table object
	private String school_name;
	private String school_slogan;
	private String tin_num;
	private String telephone;
	private String fax;
	private String web;
	private String email;
	private String pobox;
	private String fiscal_machine_no;

	// class_detail table object
	private String cd_id;
	private String cd_name;

	// class_list table object
	private String class_name;

	// stud_information table object
	private String fname;
	private String mname;
	private String gname;

	// additional objects
	private String pay_tab;
	private String payment_sub_total;
	private String vat;
	private String payment_grand_total;
	private String payment_grand_total_in_word;
	private String indx;
	private String reg_fee;
	private String other_payment;

	private ReportBean report_bean;

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getSep() {
		return sep;
	}

	public void setSep(String sep) {
		this.sep = sep;
	}

	public String getOct() {
		return oct;
	}

	public void setOct(String oct) {
		this.oct = oct;
	}

	public String getNov() {
		return nov;
	}

	public void setNov(String nov) {
		this.nov = nov;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public String getJan() {
		return jan;
	}

	public void setJan(String jan) {
		this.jan = jan;
	}

	public String getFeb() {
		return feb;
	}

	public void setFeb(String feb) {
		this.feb = feb;
	}

	public String getMar() {
		return mar;
	}

	public void setMar(String mar) {
		this.mar = mar;
	}

	public String getApr() {
		return apr;
	}

	public void setApr(String apr) {
		this.apr = apr;
	}

	public String getMay() {
		return may;
	}

	public void setMay(String may) {
		this.may = may;
	}

	public String getJun() {
		return jun;
	}

	public void setJun(String jun) {
		this.jun = jun;
	}

	public String getJul() {
		return jul;
	}

	public void setJul(String jul) {
		this.jul = jul;
	}

	public String getAug() {
		return aug;
	}

	public void setAug(String aug) {
		this.aug = aug;
	}

	public String getSi_id() {
		return si_id;
	}

	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getPc_id() {
		return pc_id;
	}

	public void setPc_id(String pc_id) {
		this.pc_id = pc_id;
	}

	public String getPt_id() {
		return pt_id;
	}

	public void setPt_id(String pt_id) {
		this.pt_id = pt_id;
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

	public String getAcademic_year() {
		return academic_year;
	}

	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
	}

	public String getPenality_amount() {
		return penality_amount;
	}

	public void setPenality_amount(String penality_amount) {
		this.penality_amount = penality_amount;
	}

	public String getMonth_full() {
		return month_full;
	}

	public void setMonth_full(String month_full) {
		this.month_full = month_full;
	}

	public String getMonth_short() {
		return month_short;
	}

	public void setMonth_short(String month_short) {
		this.month_short = month_short;
	}

	public String getTotal_payment() {
		return total_payment;
	}

	public void setTotal_payment(String total_payment) {
		this.total_payment = total_payment;
	}

	public String getMonth_id() {
		return month_id;
	}

	public void setMonth_id(String month_id) {
		this.month_id = month_id;
	}

	public String getPt_name() {
		return pt_name;
	}

	public void setPt_name(String pt_name) {
		this.pt_name = pt_name;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getPc_status() {
		return pc_status;
	}

	public void setPc_status(String pc_status) {
		this.pc_status = pc_status;
	}

	public String getIndx() {
		return indx;
	}

	public void setIndx(String indx) {
		this.indx = indx;
	}

	public String getPay_tab() {
		return pay_tab;
	}

	public void setPay_tab(String pay_tab) {
		this.pay_tab = pay_tab;
	}

	public String getAc_year() {
		return ac_year;
	}

	public void setAc_year(String ac_year) {
		this.ac_year = ac_year;
	}

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

	public String getMaterial_pay_amount() {
		return material_pay_amount;
	}

	public void setMaterial_pay_amount(String material_pay_amount) {
		this.material_pay_amount = material_pay_amount;
	}

	public String getPcm_id() {
		return pcm_id;
	}

	public void setPcm_id(String pcm_id) {
		this.pcm_id = pcm_id;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getPayment_sub_total() {
		return payment_sub_total;
	}

	public void setPayment_sub_total(String payment_sub_total) {
		this.payment_sub_total = payment_sub_total;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getPayment_grand_total() {
		return payment_grand_total;
	}

	public void setPayment_grand_total(String payment_grand_total) {
		this.payment_grand_total = payment_grand_total;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getSchool_slogan() {
		return school_slogan;
	}

	public void setSchool_slogan(String school_slogan) {
		this.school_slogan = school_slogan;
	}

	public String getTin_num() {
		return tin_num;
	}

	public void setTin_num(String tin_num) {
		this.tin_num = tin_num;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPobox() {
		return pobox;
	}

	public void setPobox(String pobox) {
		this.pobox = pobox;
	}

	public String getFiscal_machine_no() {
		return fiscal_machine_no;
	}

	public void setFiscal_machine_no(String fiscal_machine_no) {
		this.fiscal_machine_no = fiscal_machine_no;
	}

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	public String getSnc_id() {
		return snc_id;
	}

	public void setSnc_id(String snc_id) {
		this.snc_id = snc_id;
	}

	public int getFm_receipt_no() {
		return fm_receipt_no;
	}

	public void setFm_receipt_no(int fm_receipt_no) {
		this.fm_receipt_no = fm_receipt_no;
	}

	public String getCust_vat_reg_no() {
		return cust_vat_reg_no;
	}

	public void setCust_vat_reg_no(String cust_vat_reg_no) {
		this.cust_vat_reg_no = cust_vat_reg_no;
	}

	public int getCust_tin() {
		return cust_tin;
	}

	public void setCust_tin(int cust_tin) {
		this.cust_tin = cust_tin;
	}

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public String getCheque_no() {
		return cheque_no;
	}

	public void setCheque_no(String cheque_no) {
		this.cheque_no = cheque_no;
	}

	public String getPayment_grand_total_in_word() {
		return payment_grand_total_in_word;
	}

	public void setPayment_grand_total_in_word(String payment_grand_total_in_word) {
		this.payment_grand_total_in_word = payment_grand_total_in_word;
	}

	public String getCd_name() {
		return cd_name;
	}

	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}

	public ReportBean getReport_bean() {
		return report_bean;
	}

	public void setReport_bean(ReportBean report_bean) {
		this.report_bean = report_bean;
	}

	public String getReg_fee() {
		return reg_fee;
	}

	public void setReg_fee(String reg_fee) {
		this.reg_fee = reg_fee;
	}

	public String getOther_payment() {
		return other_payment;
	}

	public void setOther_payment(String other_payment) {
		this.other_payment = other_payment;
	}

}
