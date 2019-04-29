package examClass.exam_subject_rel;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import examClass.exam_type.ExamBean;

public class ExamSubjectRelAction extends ActionSupport implements ModelDriven<ExamSubjectRelBean>, SessionAware {
	
	private static final long serialVersionUID = -5503592736047026160L;
	private String exsub_id;
	private String et_id;
	private String et_name;
	private String subcl_id;
	private String total_mark;
	private String pass_mark;
	private String rel_status;
	
	private String cl_id;
	private String sub_id;
	
	private List<ExamSubjectRelBean> examtype_subrel_rslt;
	private List<ExamBean> examtypelist_rslt;
	private Map<String, Object> sessionMap;
	
	ExamSubjectRelBean exsub = new ExamSubjectRelBean();
	
	public String classSubjectRelSave(){
		if (sessionMap.containsKey("userName")) {
			int x = 0;
			
			if(exsub.getEt_id().equalsIgnoreCase("0")){
				x++;
				addFieldError("examtypesubcreateError", "Please select exam type");
			}
			if(exsub.getTotal_mark().trim().length() == 0){
				x++;
				addFieldError("examtypesubcreateError", "The total mark is blank.");
			}
			if(exsub.getPass_mark().trim().length() == 0){
				x++;
				addFieldError("examtypesubcreateError", "The pass mark is blank.");
			}
			if(Double.parseDouble(exsub.getPass_mark()) > Double.parseDouble(exsub.getTotal_mark())){
				x++;
				addFieldError("examtypesubcreateError", "The pass mark can't be greater than total mark.");
			}
			
			if(x > 0){
				
				examtypelist_rslt = ExamSubjectRelDAO.getUnselectedExamTypeList(exsub);
				return "CREATEFORM";
			} else {
				
				boolean rslt = false;
//				boolean validateTotalMark = ExamSubjectRelDAO.validateSubjectExamTotalMark(exsub);
//				if(validateTotalMark){
					rslt = ExamSubjectRelDAO.insertClSubExmRel(exsub);
//				} else {
//					examtypelist_rslt = ExamSubjectRelDAO.getUnselectedExamTypeList(exsub);
//					addFieldError("examtypesubcreateError", "The total mark exceed the maximum mark. please correct the amount.");
//					return ERROR;
//				}
				
				if(rslt){
					examtype_subrel_rslt = ExamSubjectRelDAO.getExSubjectRel(exsub);
					return SUCCESS;
				} else {
					examtypelist_rslt = ExamSubjectRelDAO.getUnselectedExamTypeList(exsub);
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
		
	}
	
	/*
	 * Notice: exam_sub_rel total mark and pass mark can't be updated from web. it is only possible to make inactive
	 * the existing recored and create a new one. This helps not to affect (by the change) other records that are using
	 * the existing record. if the total mark and pass mark needed to be updated then it only can be updated on database directly.
	 */
	public String classSubjectRelUpdate(){
		if (sessionMap.containsKey("userName")) {
						
			if(exsub.getRel_status().equals("A") && ExamSubjectRelDAO.checkActiveExamSubjectRel(exsub)){
				
				examtypelist_rslt = ExamSubjectRelDAO.getUnselectedExamTypeList(exsub);
				addFieldError("examtypesubcreateError", "There is active relationship between selected exam and subject.");
				return "UPDATEFORM";
				
			} else {
				
				boolean rslt = ExamSubjectRelDAO.updateClSubExmRel(exsub);
				if(rslt){
					examtype_subrel_rslt = ExamSubjectRelDAO.getExSubjectRel(exsub);
					return SUCCESS;
				} else {
					examtypelist_rslt = ExamSubjectRelDAO.getUnselectedExamTypeList(exsub);
					return ERROR;
				}
			}
		} else {
			return INPUT;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String getExsub_id() {
		return exsub_id;
	}
	public void setExsub_id(String exsub_id) {
		this.exsub_id = exsub_id;
	}
	public String getEt_id() {
		return et_id;
	}
	public void setEt_id(String et_id) {
		this.et_id = et_id;
	}
	public String getSubcl_id() {
		return subcl_id;
	}
	public void setSubcl_id(String subcl_id) {
		this.subcl_id = subcl_id;
	}
	public String getPass_mark() {
		return pass_mark;
	}
	public void setPass_mark(String pass_mark) {
		this.pass_mark = pass_mark;
	}
	public String getRel_status() {
		return rel_status;
	}
	public void setRel_status(String rel_status) {
		this.rel_status = rel_status;
	}
	public String getEt_name() {
		return et_name;
	}
	public void setEt_name(String et_name) {
		this.et_name = et_name;
	}

	@Override
	public ExamSubjectRelBean getModel() {
		// TODO Auto-generated method stub
		return exsub;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public List<ExamSubjectRelBean> getExamtype_subrel_rslt() {
		return examtype_subrel_rslt;
	}

	public void setExamtype_subrel_rslt(List<ExamSubjectRelBean> examtype_subrel_rslt) {
		this.examtype_subrel_rslt = examtype_subrel_rslt;
	}
	public List<ExamBean> getExamtypelist_rslt() {
		return examtypelist_rslt;
	}
	public void setExamtypelist_rslt(List<ExamBean> examtypelist_rslt) {
		this.examtypelist_rslt = examtypelist_rslt;
	}

	public String getTotal_mark() {
		return total_mark;
	}

	public void setTotal_mark(String total_mark) {
		this.total_mark = total_mark;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	

}
