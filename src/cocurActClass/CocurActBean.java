package cocurActClass;

public class CocurActBean {
	
	//>>> cca_club_activities table object
	private String ca_id;
	private String clb_id;
	private String ca_activity;
	private String ca_activity_desc;
	private String ca_datefrom;
	private String ca_dateto;
	private String academic_year;
	private String clubhead_comment;	
	private String ca_status;
		
	//>>> cca_member table object
	private String cm_id;
	private String mc_id;
	private String m_id;
	private String clbid;
	private String cm_reasontojoin;
	private String cm_evaluation;
	private String cm_status;
	private String cm_acyear;
	
	//>>> cca_club table object
	private String clbname;

	//additional objects
	private String com_length;
	private boolean editStatus;
	private String participant_label;
	private String mem_name;
	
	//stud_information table object
	private String si_id;
	
	//class_list table object
	private String cl_id;
	private String cd_id;
	
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getCm_id() {
		return cm_id;
	}
	public void setCm_id(String cm_id) {
		this.cm_id = cm_id;
	}
	public String getMc_id() {
		return mc_id;
	}
	public void setMc_id(String mc_id) {
		this.mc_id = mc_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getClbid() {
		return clbid;
	}
	public void setClbid(String clbid) {
		this.clbid = clbid;
	}
	public String getCm_reasontojoin() {
		return cm_reasontojoin;
	}
	public void setCm_reasontojoin(String cm_reasontojoin) {
		this.cm_reasontojoin = cm_reasontojoin;
	}
	public String getCm_evaluation() {
		return cm_evaluation;
	}
	public void setCm_evaluation(String cm_evaluation) {
		this.cm_evaluation = cm_evaluation;
	}
	public String getCm_status() {
		return cm_status;
	}
	public void setCm_status(String cm_status) {
		this.cm_status = cm_status;
	}
	public String getCa_id() {
		return ca_id;
	}
	public void setCa_id(String ca_id) {
		this.ca_id = ca_id;
	}
	public String getClb_id() {
		return clb_id;
	}
	public void setClb_id(String clb_id) {
		this.clb_id = clb_id;
	}
	public String getCa_activity() {
		return ca_activity;
	}
	public void setCa_activity(String ca_activity) {
		this.ca_activity = ca_activity;
	}
	public String getCa_activity_desc() {
		return ca_activity_desc;
	}
	public void setCa_activity_desc(String ca_activity_desc) {
		this.ca_activity_desc = ca_activity_desc;
	}
	public String getCa_datefrom() {
		return ca_datefrom;
	}
	public void setCa_datefrom(String ca_datefrom) {
		this.ca_datefrom = ca_datefrom;
	}
	public String getCa_dateto() {
		return ca_dateto;
	}
	public void setCa_dateto(String ca_dateto) {
		this.ca_dateto = ca_dateto;
	}
	public String getAcademic_year() {
		return academic_year;
	}
	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
	}
	public String getClubhead_comment() {
		return clubhead_comment;
	}
	public void setClubhead_comment(String clubhead_comment) {
		this.clubhead_comment = clubhead_comment;
	}
	public String getCa_status() {
		return ca_status;
	}
	public void setCa_status(String ca_status) {
		this.ca_status = ca_status;
	}
	
	public String getClbname() {
		return clbname;
	}
	public void setClbname(String clbname) {
		this.clbname = clbname;
	}
	public String getSi_id() {
		return si_id;
	}
	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}
	public String getCd_id() {
		return cd_id;
	}
	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}
	public String getCm_acyear() {
		return cm_acyear;
	}
	public void setCm_acyear(String cm_acyear) {
		this.cm_acyear = cm_acyear;
	}
	public String getCom_length() {
		return com_length;
	}
	public void setCom_length(String com_length) {
		this.com_length = com_length;
	}
	public boolean getEditStatus() {
		return editStatus;
	}
	public void setEditStatus(boolean editStatus) {
		this.editStatus = editStatus;
	}
	public String getParticipant_label() {
		return participant_label;
	}
	public void setParticipant_label(String participant_label) {
		this.participant_label = participant_label;
	}

}
