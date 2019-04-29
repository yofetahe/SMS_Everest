package adminClass.behavioural_evaluation;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import studentClass.StudentClassBean;
import studentClass.StudentDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BehEval_Action extends ActionSupport implements ModelDriven<BehEval_Bean>, SessionAware{

	private static final long serialVersionUID = 1L;
	
	private List<BehEval_Bean> traitList;
	private List<BehEval_Bean> gradeList;
	private List<BehEval_Bean> unrelatedGradeList;
	private List<BehEval_Bean> traitGradeRelList;
	private List<StudentClassBean> grade_rslt;
	private List<BehEval_Bean> traitListPerClass;
	private List<BehEval_Bean> unrelatedTraitList;
	
	private Map<String, Object> sessionMap;
	BehEval_Bean beb = new BehEval_Bean();
	
	public String getBehaviouralEvaluationTemplate(){
		if(sessionMap.containsKey("userName")){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getBehaviouralEvaluationTraitsList(){
		if(sessionMap.containsKey("userName")){
			traitList = BehEval_DAO.getTraitList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getBehaviouralEvaluationTraitsSave(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = BehEval_DAO.saveBevalTrait(beb);
			if(rslt){
				traitList = BehEval_DAO.getTraitList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getBehaviouralEvaluationTraitsEditForm(){
		if(sessionMap.containsKey("userName")){
			
			traitList = BehEval_DAO.getTraitList();
			
			for(int i = 0; i < traitList.size(); i++){
				if(beb.getBt_id().equalsIgnoreCase(traitList.get(i).getBt_id())){
					beb.setBt_title(traitList.get(i).getBt_title());
					beb.setBt_desc(traitList.get(i).getBt_desc());
					beb.setBt_status(traitList.get(i).getBt_status());
				}
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getBehaviouralEvaluationTraitsUpdate(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = BehEval_DAO.updateBevalTrait(beb);
			if(rslt){
				traitList = BehEval_DAO.getTraitList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getBehaviouralEvaluationGradeList(){
		if(sessionMap.containsKey("userName")){
			gradeList = BehEval_DAO.getGradeList();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getBehaviouralEvaluationGradeSave(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = BehEval_DAO.saveBevalGrade(beb);
			if(rslt){
				gradeList = BehEval_DAO.getGradeList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getBehaviouralEvaluationGradeEditForm(){
		if(sessionMap.containsKey("userName")){
			
			gradeList = BehEval_DAO.getGradeList();
			
			for(int i = 0; i < gradeList.size(); i++){
				if(beb.getBg_id().equalsIgnoreCase(gradeList.get(i).getBg_id())){
					beb.setBg_name(gradeList.get(i).getBg_name());
					beb.setBg_desc(gradeList.get(i).getBg_desc());
					beb.setBg_status(gradeList.get(i).getBg_status());
				}
			}
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String getBehaviouralEvaluationGradeUpdate(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = BehEval_DAO.updateBevalGrade(beb);
			if(rslt){
				gradeList = BehEval_DAO.getGradeList();
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String getBehaviouralEvaluationTraitGradeRelList(){
		if(sessionMap.containsKey("userName")){
			traitGradeRelList = BehEval_DAO.getTraitGradeRelList(beb);
			unrelatedGradeList = BehEval_DAO.getUnrelatedGradeList(beb);
			return SUCCESS;			
		} else {
			return INPUT;
		}
	}
	
	public String saveBehaviouralEvaluationTraitGradeRel(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BehEval_DAO.saveBehaviouralEvaluationTraitGradeRel(beb);
			
			traitGradeRelList = BehEval_DAO.getTraitGradeRelList(beb);
			unrelatedGradeList = BehEval_DAO.getUnrelatedGradeList(beb);
			if(rslt){			
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String editFormBehaviouralEvaluationTraitGradeRel(){
		if(sessionMap.containsKey("userName")){
			
			traitGradeRelList = BehEval_DAO.getTraitGradeRelList(beb);
			
			for(int i = 0; i < traitGradeRelList.size(); i++){
				
				if(beb.getBtbg_id().equalsIgnoreCase(traitGradeRelList.get(i).getBtbg_id())){					
					beb.setBg_name(traitGradeRelList.get(i).getBg_name());
					beb.setBtbg_rel(traitGradeRelList.get(i).getBtbg_rel());
				}
			}
						
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String updateBehaviouralEvaluationTraitGradeRel(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BehEval_DAO.updateBehaviouralEvaluationTraitGradeRel(beb);
			
			traitGradeRelList = BehEval_DAO.getTraitGradeRelList(beb);
			unrelatedGradeList = BehEval_DAO.getUnrelatedGradeList(beb);
			if(rslt){			
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String classTraitRel(){
		if(sessionMap.containsKey("userName")){
			
			grade_rslt = StudentDAO.getClassList();			
			return SUCCESS;
			
		} else {
			return INPUT;
		}
	}
	
	public String classTraitRelList(){
		if(sessionMap.containsKey("userName")){
			traitListPerClass = BehEval_DAO.getTraitListPerGrade(beb);
			unrelatedTraitList = BehEval_DAO.getUnrelatedTraitList(beb);
			return SUCCESS;			
		} else {
			return INPUT;
		}
	}
	
	public String saveClassTraitRel(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BehEval_DAO.saveClassTraitRel(beb);
			traitListPerClass = BehEval_DAO.getTraitListPerGrade(beb);
			
			unrelatedTraitList = BehEval_DAO.getUnrelatedTraitList(beb);
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String classTraitRelEditForm(){
		if(sessionMap.containsKey("userName")){
			
			traitListPerClass = BehEval_DAO.getTraitListPerGrade(beb);
			unrelatedTraitList = BehEval_DAO.getUnrelatedTraitList(beb);
			
			for(int i = 0; i < traitListPerClass.size(); i++){
				if(beb.getBtc_id().equalsIgnoreCase(traitListPerClass.get(i).getBtc_id())){
					beb.setRel_status(traitListPerClass.get(i).getRel_status());
					beb.setBt_title(traitListPerClass.get(i).getBt_title());
				}
			}
			
			return SUCCESS;			
		} else {
			return INPUT;
		}
	}
	
	public String classTraitRelUpdate(){
		if(sessionMap.containsKey("userName")){
			
			boolean rslt = BehEval_DAO.updateClassTraitRel(beb);
			traitListPerClass = BehEval_DAO.getTraitListPerGrade(beb);
			
			unrelatedTraitList = BehEval_DAO.getUnrelatedTraitList(beb);
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public BehEval_Bean getModel() {
		return beb;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<BehEval_Bean> getTraitList() {
		return traitList;
	}

	public void setTraitList(List<BehEval_Bean> traitList) {
		this.traitList = traitList;
	}

	public List<BehEval_Bean> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<BehEval_Bean> gradeList) {
		this.gradeList = gradeList;
	}

	public List<BehEval_Bean> getUnrelatedGradeList() {
		return unrelatedGradeList;
	}

	public void setUnrelatedGradeList(List<BehEval_Bean> unrelatedGradeList) {
		this.unrelatedGradeList = unrelatedGradeList;
	}

	public List<BehEval_Bean> getTraitGradeRelList() {
		return traitGradeRelList;
	}

	public void setTraitGradeRelList(List<BehEval_Bean> traitGradeRelList) {
		this.traitGradeRelList = traitGradeRelList;
	}

	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}

	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}

	public List<BehEval_Bean> getTraitListPerClass() {
		return traitListPerClass;
	}

	public void setTraitListPerClass(List<BehEval_Bean> traitListPerClass) {
		this.traitListPerClass = traitListPerClass;
	}

	public List<BehEval_Bean> getUnrelatedTraitList() {
		return unrelatedTraitList;
	}

	public void setUnrelatedTraitList(List<BehEval_Bean> unrelatedTraitList) {
		this.unrelatedTraitList = unrelatedTraitList;
	}


}
