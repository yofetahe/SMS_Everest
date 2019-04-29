package behHolisticEvaluationClass;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import examClass.exam_result.ExamResultBean;
import examClass.exam_result.ExamResultDAO;
import studentClass.StudentClassBean;
import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import util.ReturnCurrentEthiopianYear;

public class BevalHolisticAction extends ActionSupport implements ModelDriven<BevalHolisticBean>, SessionAware{

	private static final long serialVersionUID = 3973822246111836913L;
	BevalHolisticBean bhb = new BevalHolisticBean();
	private Map<String, Object> sessionMap;
	private List<StudentClassBean> grade_rslt;
	private List<BevalHolisticBean> holistic_cat_list;
	private List<BevalHolisticBean> holistic_point_list;
	private List<BevalHolisticBean> unrelated_point_list;
	private List<BevalHolisticBean> related_point_list;
	private List<BevalHolisticBean> unrelated_catagory_list;
	private List<BevalHolisticBean> related_catagory_list;
	private List<StudentClassDetailBean> class_detail;
	private List<BevalHolisticBean> uncommented_category_list;
	private List<BevalHolisticBean> commented_category_list;
	private List<ExamResultBean> sem_list;
	private List<BevalHolisticBean> stud_holist_beval_comment;
	
	public String getClassBehHolisticTemp(){
		if(sessionMap.containsKey("userName")){
			grade_rslt = StudentDAO.getClassList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	public String getHolisticCategoryList(){
		if(sessionMap.containsKey("userName")){
			
			holistic_cat_list = BevalHolisticDAO.getHolisticCategoryList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveHolisticCategory(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.saveHolisticCategory(bhb);
			holistic_cat_list = BevalHolisticDAO.getHolisticCategoryList();
			
			if(rslt){
				return SUCCESS;
			} else {
				addActionError("Holistic category is not saved. Please try again.");
				return INPUT;
			}
		} else {
			return INPUT;
		}
	}
	
	public String holisticCategoryEditForm(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateHolisticCategory(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.updateHolisticCategory(bhb);
			holistic_cat_list = BevalHolisticDAO.getHolisticCategoryList();
			
			if(rslt){
				return SUCCESS;
			} else {
				addActionError("Holistic category is not updated. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getHolisticPointList(){
		if(sessionMap.containsKey("userName")){
			
			holistic_point_list = BevalHolisticDAO.getHolisticPointList();
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveHolisticPoint(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.saveHolisticPoint(bhb);
			holistic_point_list = BevalHolisticDAO.getHolisticPointList();
			
			if(rslt){
				return SUCCESS;
			} else {
				addActionError("Holistic point is not saved. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String holisticPointEditForm(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateHolisticPoint(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.updateHolisticPoint(bhb);
			holistic_point_list = BevalHolisticDAO.getHolisticPointList();
			
			if(rslt){
				return SUCCESS;
			} else {
				addActionError("Holistic category is not updated. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getCategoryPointRelList(){
		if(sessionMap.containsKey("userName")){
			
			related_point_list = BevalHolisticDAO.getRelatedPointList(bhb);
			unrelated_point_list = BevalHolisticDAO.getUnrelatedPointList(bhb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveCategoryPointRel(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.saveCategoryPointRel(bhb);
			
			if(rslt){			
				related_point_list = BevalHolisticDAO.getRelatedPointList(bhb);
				unrelated_point_list = BevalHolisticDAO.getUnrelatedPointList(bhb);
				
				return SUCCESS;
			} else {
				
				addActionError("The relationship is not saved. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String categoryPointRelUpdateForm(){
		if(sessionMap.containsKey("userName")){
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateCategoryPointRel(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.updateCategoryPointRel(bhb);
			
			if(rslt){			
				related_point_list = BevalHolisticDAO.getRelatedPointList(bhb);
				unrelated_point_list = BevalHolisticDAO.getUnrelatedPointList(bhb);
				
				return SUCCESS;
			} else {
				
				addActionError("The relationship is not saved. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	public String getCategoryPerGradeList(){
		if(sessionMap.containsKey("userName")){
			
			related_catagory_list = BevalHolisticDAO.getRelatedCategoryList(bhb);
			unrelated_catagory_list = BevalHolisticDAO.getUnrelatedCategoryList(bhb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveClassCategoryRel(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.saveClassCategoryRel(bhb);
			
			related_catagory_list = BevalHolisticDAO.getRelatedCategoryList(bhb);
			unrelated_catagory_list = BevalHolisticDAO.getUnrelatedCategoryList(bhb);
			
			if(rslt){
				
				return SUCCESS;
			} else {
				
				addActionMessage("The class-category relation is not saved. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String classCategoryRelUpdateForm(){
		if(sessionMap.containsKey("userName")){
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String updateClassCategoryRel(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.updateClassCategoryRel(bhb);
			
			related_catagory_list = BevalHolisticDAO.getRelatedCategoryList(bhb);
			unrelated_catagory_list = BevalHolisticDAO.getUnrelatedCategoryList(bhb);
			
			if(rslt){
				
				return SUCCESS;
			} else {
				
				addActionMessage("The class-category relation is not saved. Please try again.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	public String getHolBehEvalTemp(){
		if(sessionMap.containsKey("userName")){
			grade_rslt = StudentDAO.getClassList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getClassDetailList(){
		if (sessionMap.containsKey("userName")) {
			class_detail = StudentDAO.getClassDetail(bhb.getCl_id(), (String)sessionMap.get("userName"));
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getStudentBevalList(){
		if (sessionMap.containsKey("userName")) {		
			
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			bhb.setAc_year(yr);
			
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			for(int i = 0; i < sem_list.size(); i++){
				if(bhb.getAt_id().equals(sem_list.get(i).getAt_id())){
					bhb.setAt_name(sem_list.get(i).getAt_name());
				}
			}
			
			related_catagory_list = BevalHolisticDAO.getRelatedCategoryList(bhb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getCategoryPointsList(){
		if(sessionMap.containsKey("userName")){
			
			//related_point_list = BevalHolisticDAO.getActiveRelatedPointList(bhb);
			uncommented_category_list = BevalHolisticDAO.getUncommentedCategoryPointList(bhb);
						
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String savePointsCommentGiven(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.savePointsCommentGiven(bhb);
			if(rslt){			
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String updatePointsCommentGiven(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BevalHolisticDAO.updatePointsCommentGiven(bhb);
			if(rslt){			
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getStudentHolisticBevalResult(){
		if(sessionMap.containsKey("userName")){
			
			String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
			bhb.setAc_year(yr);
			
			sem_list = ExamResultDAO.getSemisterListFullYear(yr);
			for(int i = 0; i < sem_list.size(); i++){
				if(bhb.getAt_id().equals(sem_list.get(i).getAt_id())){
					bhb.setAt_name(sem_list.get(i).getAt_name());
				}
			}
			
			stud_holist_beval_comment = BevalHolisticDAO.getStudentsHolisticComment(bhb);
			related_catagory_list = BevalHolisticDAO.getRelatedCategoryList(bhb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public BevalHolisticBean getModel() {
		return bhb;
	}
	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}
	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}


	public List<BevalHolisticBean> getHolistic_cat_list() {
		return holistic_cat_list;
	}


	public void setHolistic_cat_list(List<BevalHolisticBean> holistic_cat_list) {
		this.holistic_cat_list = holistic_cat_list;
	}


	public List<BevalHolisticBean> getHolistic_point_list() {
		return holistic_point_list;
	}


	public void setHolistic_point_list(List<BevalHolisticBean> holistic_point_list) {
		this.holistic_point_list = holistic_point_list;
	}


	public List<BevalHolisticBean> getUnrelated_point_list() {
		return unrelated_point_list;
	}


	public void setUnrelated_point_list(List<BevalHolisticBean> unrelated_point_list) {
		this.unrelated_point_list = unrelated_point_list;
	}


	public List<BevalHolisticBean> getRelated_point_list() {
		return related_point_list;
	}


	public void setRelated_point_list(List<BevalHolisticBean> related_point_list) {
		this.related_point_list = related_point_list;
	}


	public List<BevalHolisticBean> getUnrelated_catagory_list() {
		return unrelated_catagory_list;
	}


	public void setUnrelated_catagory_list(List<BevalHolisticBean> unrelated_catagory_list) {
		this.unrelated_catagory_list = unrelated_catagory_list;
	}


	public List<BevalHolisticBean> getRelated_catagory_list() {
		return related_catagory_list;
	}


	public void setRelated_catagory_list(List<BevalHolisticBean> related_catagory_list) {
		this.related_catagory_list = related_catagory_list;
	}


	public List<StudentClassDetailBean> getClass_detail() {
		return class_detail;
	}


	public void setClass_detail(List<StudentClassDetailBean> class_detail) {
		this.class_detail = class_detail;
	}


	public List<ExamResultBean> getSem_list() {
		return sem_list;
	}


	public void setSem_list(List<ExamResultBean> sem_list) {
		this.sem_list = sem_list;
	}


	public List<BevalHolisticBean> getUncommented_category_list() {
		return uncommented_category_list;
	}


	public void setUncommented_category_list(List<BevalHolisticBean> uncommented_category_list) {
		this.uncommented_category_list = uncommented_category_list;
	}


	public List<BevalHolisticBean> getCommented_category_list() {
		return commented_category_list;
	}


	public void setCommented_category_list(List<BevalHolisticBean> commented_category_list) {
		this.commented_category_list = commented_category_list;
	}


	public List<BevalHolisticBean> getStud_holist_beval_comment() {
		return stud_holist_beval_comment;
	}


	public void setStud_holist_beval_comment(List<BevalHolisticBean> stud_holist_beval_comment) {
		this.stud_holist_beval_comment = stud_holist_beval_comment;
	}

}
