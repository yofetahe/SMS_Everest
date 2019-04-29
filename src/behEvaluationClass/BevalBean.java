package behEvaluationClass;

import java.util.List;
import java.util.Map;

public class BevalBean {

	// >>> beval_stud_result table objects
	private String bsr_id;
	private String si_id;
	private String cl_id;
	private String bt_id;
	private String bg_id;
	private String qr_id;
	private String ac_year;
	private String bsr_status;

	// >>> beval_traits table object
	private String bt_title;

	// >>> beval_grade table object
	private String bg_name;

	// stud_information table objects
	private String fname;
	private String mname;
	private String gname;

	// class_list table object
	private String class_name;

	// class_detail table object
	private String cd_id;

	// annual_quarters table object
	private String qr_name;

	// annual_term table object
	private String at_id;
	private String at_name;

	// additional object
	private String quarter;

	private Map<String, String> eval_grd_list;

	private List<BevalBean> evalgrd_list;

	private List<Object> studbevalrslt_list;

	public String getCl_id() {
		return cl_id;
	}

	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	public String getBt_title() {
		return bt_title;
	}

	public void setBt_title(String bt_title) {
		this.bt_title = bt_title;
	}

	public String getBt_id() {
		return bt_id;
	}

	public void setBt_id(String bt_id) {
		this.bt_id = bt_id;
	}

	public String getBg_name() {
		return bg_name;
	}

	public void setBg_name(String bg_name) {
		this.bg_name = bg_name;
	}

	public String getBg_id() {
		return bg_id;
	}

	public void setBg_id(String bg_id) {
		this.bg_id = bg_id;
	}

	public String getSi_id() {
		return si_id;
	}

	public void setSi_id(String si_id) {
		this.si_id = si_id;
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

	public String getAc_year() {
		return ac_year;
	}

	public void setAc_year(String ac_year) {
		this.ac_year = ac_year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getQr_id() {
		return qr_id;
	}

	public void setQr_id(String qr_id) {
		this.qr_id = qr_id;
	}

	public String getQr_name() {
		return qr_name;
	}

	public void setQr_name(String qr_name) {
		this.qr_name = qr_name;
	}

	public String getBsr_id() {
		return bsr_id;
	}

	public void setBsr_id(String bsr_id) {
		this.bsr_id = bsr_id;
	}

	public String getBsr_status() {
		return bsr_status;
	}

	public void setBsr_status(String bsr_status) {
		this.bsr_status = bsr_status;
	}

	public String getAt_id() {
		return at_id;
	}

	public void setAt_id(String at_id) {
		this.at_id = at_id;
	}

	public String getAt_name() {
		return at_name;
	}

	public void setAt_name(String at_name) {
		this.at_name = at_name;
	}

	public Map<String, String> getEval_grd_list() {
		return eval_grd_list;
	}

	public void setEval_grd_list(Map<String, String> eval_grd_list) {
		this.eval_grd_list = eval_grd_list;
	}

	public List<BevalBean> getEvalgrd_list() {
		return evalgrd_list;
	}

	public void setEvalgrd_list(List<BevalBean> evalgrd_list) {
		this.evalgrd_list = evalgrd_list;
	}

	public List<Object> getStudbevalrslt_list() {
		return studbevalrslt_list;
	}

	public void setStudbevalrslt_list(List<Object> studbevalrslt_list) {
		this.studbevalrslt_list = studbevalrslt_list;
	}

}
