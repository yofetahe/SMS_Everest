package paymentFineClass;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import studentClass.StudentBean;
import studentClass.StudentClassBean;
import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import util.ConvertNumberToWords;
import util.DateConvertor;
import util.EmailForm;
import util.ReturnCurrentEthiopianYear;
import util.RoleValidator;
import util.SysConstant;
import util.TodayDate_YYYYMMDD;
import adminClass.AdminBean;
import adminClass.AdminDAO;
import reportClass.ReportBean;
import reportClass.ReportDAO;
import specialNeedRequired.SpecialNeedRequiredDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PaymentAction extends ActionSupport implements ModelDriven<PaymentBean>, SessionAware {
	
	private static final long serialVersionUID = 1L;
	private List<StudentClassBean> grade_rslt;
	private List<StudentBean> stud_rslt;
	private List<StudentClassDetailBean> class_detail;
	private List<PaymentBean> payment_table;
	private PaymentBean payment_table_per_student;
	private List<PaymentBean> payment_month;
	private List<PaymentBean> payment_amount;
	private List<PaymentBean> payment_type_list;
	private List<PaymentBean> payment_info;
	private List<PaymentBean> payment_month_selected;
	private List<PaymentBean> payment_info_more_than_amonth;
	private List<PaymentBean> payment_info_more_than_amonth_receipt;
	private List<PaymentBean> material_list;
	private List<PaymentBean> paid_material_list;
	private List<PaymentBean> payment_receipt_info;
	private List<PaymentBean> selected_material_list;
	private List<PaymentBean> school_information;
	private List<ReportBean> payment_collection;
	
	private List<AdminBean> usrRoleList;
	private Map<String, Object> sessionMap;
	
	PaymentBean pb = new PaymentBean();
	
	private String menutype = "payment";
	
	
	////****** Logged in user id *****////
	public String getLoggedInUser(String loggedInUserType){
		String loggedInUser = "";
		if(loggedInUserType.equals("NTHCR")){
			loggedInUser = (String) sessionMap.get("nti_id");
		}
		if(loggedInUserType.equals("THCR")){
			loggedInUser = (String) sessionMap.get("ti_id");
		}
		return loggedInUser;
	}
	////****** Logged in user id *****////
	

	public String getPaymentTem(){
		String loggedUserName = null;
		
		// role validation if the userName is already stored in the session
		if (sessionMap.containsKey("userName")) {
			loggedUserName = (String) sessionMap.get("userName");
			usrRoleList = AdminDAO.getLoginUserRoleList(loggedUserName);
			grade_rslt = StudentDAO.getClassList();
			
			String page = "Payment and Fin";
			boolean rslt = RoleValidator.validateRoleList(usrRoleList, page);
			
			if(!rslt){
				sessionMap.remove("userName");
				return "ACCESSDENIED";
			}		
            
			pb.setPay_tab("1");
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String paymentInfo(){
		if (sessionMap.containsKey("userName")) {
			grade_rslt = StudentDAO.getClassList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String paymentAdmin(){
		if (sessionMap.containsKey("userName")) {
			payment_info = PaymentDAO.getPaymentInfoList();		
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String paymentInfoCreateFrm(){
		if (sessionMap.containsKey("userName")) {
			grade_rslt = StudentDAO.getClassList();
			payment_type_list = PaymentDAO.getPaymentTypeList();
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String paymentInfoEditFrm(){
		if (sessionMap.containsKey("userName")) {
			grade_rslt = StudentDAO.getClassList();
			payment_type_list = PaymentDAO.getPaymentTypeList();
			
			payment_info = PaymentDAO.getPaymentInfoList();
			
			int in = Integer.parseInt(pb.getIndx());
			
			pb.setPc_id(payment_info.get(in).getPc_id());
			pb.setClass_id(payment_info.get(in).getClass_id());
			pb.setPt_id(payment_info.get(in).getPt_id());
			pb.setPay_amount(payment_info.get(in).getPay_amount());
			pb.setPenality_max_date(payment_info.get(in).getPenality_max_date());
			pb.setPenality_percent(payment_info.get(in).getPenality_percent());
			pb.setAcademic_year(payment_info.get(in).getAcademic_year());
			pb.setPc_status(payment_info.get(in).getPc_status());
			
			return SUCCESS;
		} else {
			return INPUT;
		}
		
	}
	
	public String paymentAdminInfoSave(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PaymentDAO.paymentAdminInfoSave(pb);
			if(rslt){
				payment_info = PaymentDAO.getPaymentInfoList();	
				return SUCCESS;
			} else {
				addFieldError("errMsg", "There is similar active record.");
				grade_rslt = StudentDAO.getClassList();
				payment_type_list = PaymentDAO.getPaymentTypeList();
				return ERROR;
			}
		} else {
			return INPUT;
		}				
	}
	
	public String paymentAdminInfoUpdate(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PaymentDAO.paymentAdminInfoUpdate(pb);
			if(rslt){
				payment_info = PaymentDAO.getPaymentInfoList();	
				return SUCCESS;
			} else {
				addFieldError("errMsg", "There is similar active record.");
				grade_rslt = StudentDAO.getClassList();
				payment_type_list = PaymentDAO.getPaymentTypeList();
				return ERROR;
			}
		} else {
			return INPUT;
		}		
	}
	
	public String classDetailList(){
		if (sessionMap.containsKey("userName")) {
			class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String studentPerGradeList(){
		
		if (sessionMap.containsKey("userName")) {
			
			stud_rslt = StudentDAO.getListPerGradeDetail(pb.getClass_id(), pb.getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			if(stud_rslt.size() > 0){
				
				payment_table = PaymentDAO.getPaymentTable(stud_rslt, pb);
			}
			
			class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String showStudentPaymentHistory(){
		
		if (sessionMap.containsKey("userName")) {
			
			stud_rslt = StudentDAO.getListPerGradeDetail(pb.getClass_id(), pb.getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			if(stud_rslt.size() > 0){
				
				payment_table = PaymentDAO.getPaymentTable(stud_rslt, pb);
			}
			
			class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
			
			for(int i = 0; i < payment_table.size(); i++){
				
				if(pb.getSi_id().equals(payment_table.get(i).getSi_id())){
					
					payment_table_per_student = payment_table.get(i);
				}
			}
			
			return SUCCESS;
			
		} else {
			
			return INPUT;
		}	
	}
	
	public String paymentFormEdu(){
		if (sessionMap.containsKey("userName")) {
			
			pb.setFm_receipt_no(PaymentDAO.getLastFMReceiptNo());
			pb.setPayment_type("1");
			
			payment_month = PaymentDAO.getPaymentMonth(pb);
			
			if(SpecialNeedRequiredDAO.checkStudSpecialNeedRequirment(pb.getSi_id()).size() > 0){
				payment_amount = PaymentDAO.getSpecialNeedPaymentAmount(pb);
			} else {						
				payment_amount = PaymentDAO.getPaymentAmount(pb);
			}
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	///*** selected month for normal class payment ***///
	public String paymentFormEduSelectedMonthNormalClass(){
		
		if (sessionMap.containsKey("userName")) {
			
			pb.setFm_receipt_no(PaymentDAO.getLastFMReceiptNo());
			
			payment_month_selected = PaymentDAO.collectMonthSelectedForNormalClassPayment(pb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	///*** selected month for tutorial payment ***///
	public String paymentFormEduSelectedMonthTutorial(){
		
		if (sessionMap.containsKey("userName")) {
			
			pb.setFm_receipt_no(PaymentDAO.getLastFMReceiptNo());
			
			payment_month_selected = PaymentDAO.collectMonthSelectedForTutorialPayment(pb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	///*** selected month for summer payment ***///
	public String paymentFormEduSelectedMonthSummer(){
	
	if (sessionMap.containsKey("userName")) {
		
		pb.setFm_receipt_no(PaymentDAO.getLastFMReceiptNo());
		
		payment_month_selected = PaymentDAO.collectMonthSelectedForSummerPayment(pb);
		
		return SUCCESS;
	} else {
		return INPUT;
	}		
}
	
	public String paymentFormEduRemoveMonth(){
		if (sessionMap.containsKey("userName")) {
			
			PaymentDAO.deleteMonthSelectedForPayment(pb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String paymentFormTut(){
		if (sessionMap.containsKey("userName")) {
			
			pb.setFm_receipt_no(PaymentDAO.getLastFMReceiptNo());
			pb.setPayment_type("2");
			
			payment_month = PaymentDAO.getPaymentMonth(pb);
			payment_amount = PaymentDAO.getTutorialPaymentAmount(pb);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String paymentFormSummer(){
		if (sessionMap.containsKey("userName")) {
			
			pb.setFm_receipt_no(PaymentDAO.getLastFMReceiptNo());
			pb.setPayment_type("3");
			
			payment_month = PaymentDAO.getPaymentMonth(pb);
			payment_amount = PaymentDAO.getSummerPaymentAmount(pb);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String paymentFormMaterial(){
		if (sessionMap.containsKey("userName")) {		
			pb.setFm_receipt_no(PaymentDAO.getLastFMReceiptNo());
			PaymentDAO.clearSelectedPaidMaterialList();
			material_list = PaymentDAO.getMaterialList(pb);
			paid_material_list = PaymentDAO.getPaidMaterialList(pb);
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String paymentAddSelectedMaterial(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PaymentDAO.addSelectedMaterialPayment(pb);
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	} 
	
	public String paymentRemoveSelectedMaterial(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PaymentDAO.removeSelectedMaterialPayment(pb);
			if(rslt){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String paymentSaveSelectedMaterial(){
		if (sessionMap.containsKey("userName")) {
			boolean rslt = PaymentDAO.saveSelectedMaterialPayment(pb, getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			if(rslt){
				selected_material_list = PaymentDAO.getSelectedMaterialList();
				
				double sub_total = 0.0;
				for(int i = 0; i < selected_material_list.size(); i++){
					sub_total += Double.parseDouble(selected_material_list.get(i).getMaterial_pay_amount());
				}
				pb.setPayment_sub_total(String.valueOf(sub_total));
				//pb.setVat(String.valueOf(sub_total * 0.15));
				pb.setPayment_grand_total(String.valueOf(sub_total));
				
				pb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(sub_total)));
				
				payment_receipt_info = PaymentDAO.getMaterialPaymentReceiptInfo(pb);
//				material_list = PaymentDAO.getMaterialList(pb);
//				paid_material_list = PaymentDAO.getPaidMaterialList(pb);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	public String materialpaymentstudentList(){		
		if (sessionMap.containsKey("userName")) {
			stud_rslt = StudentDAO.getListPerGradeDetail(pb.getClass_id(), pb.getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());	
			//class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	
	public String regFeeStudentList(){		
		if (sessionMap.containsKey("userName")) {
			
			stud_rslt = StudentDAO.getListPerGradeDetail(pb.getClass_id(), pb.getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());	
			if(stud_rslt.size() > 0){
				payment_table = PaymentDAO.getRegFeePaymentTable(stud_rslt);
			}
			class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String getRegFeePaymentForm(){
		
		if (sessionMap.containsKey("userName")) {
			
			pb.setFm_receipt_no(PaymentDAO.getLastFMReceiptNo());
						
			payment_amount = PaymentDAO.getRegistrationFeePaymentAmount(pb);
			
			if(payment_amount.size() > 0){
				pb.setPc_id(payment_amount.get(0).getPc_id());
				pb.setPay_amount(payment_amount.get(0).getPay_amount());
			}
			
			pb.setPayment_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			
			return SUCCESS;
		} else {
			return INPUT;
		}	
	}
	
	public String saveRegFeePaymentForm(){
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = PaymentDAO.saveRegistrationPayment(pb, getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			
			payment_receipt_info = PaymentDAO.getRegistrationPaymentReceiptInfo(pb);
			//pb.setPayment_date(DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat()));
			
			return SUCCESS;
		} else {
			return INPUT;
		}	
	}
	
	public String paidPaymentFormForRegFee(){
		if (sessionMap.containsKey("userName")) {
			
			payment_receipt_info = PaymentDAO.getPaidRegistrationFeeReceiptInfo(pb);
			pb.setIndx(String.valueOf(payment_receipt_info.size()-1));
			
			return SUCCESS;

		} else {
			return INPUT;
		}				
	}
	
	
	public String paymentMaterialCost(){
		if (sessionMap.containsKey("userName")) {
			
			material_list = PaymentDAO.getMaterialList(pb);
			
			for(PaymentBean i : material_list){
				if(i.getPtm_id().equals(pb.getPtm_id())){					
					pb.setMaterial_pay_amount(i.getMaterial_pay_amount());
				}
			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String paymentMaterialInfoSave(){
		if (sessionMap.containsKey("userName")) {
			stud_rslt = StudentDAO.getListPerGrade(pb.getClass_id(), pb.getAc_year());
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String paymentstudentList(){
		if (sessionMap.containsKey("userName")) {
			
			stud_rslt = StudentDAO.getListPerGradeDetail(pb.getClass_id(), pb.getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			if(stud_rslt.size() > 0){
				payment_table = PaymentDAO.getPaymentTable(stud_rslt, pb);
			}
			
			class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
			
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String tutorialpaymentstudentList(){
		if (sessionMap.containsKey("userName")) {
			stud_rslt = StudentDAO.getListPerGradeDetail(pb.getClass_id(), pb.getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			if(stud_rslt.size() > 0){
				payment_table = PaymentDAO.getTutorialPaymentTable(stud_rslt);
			}
			class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String summerpaymentstudentList(){
		if (sessionMap.containsKey("userName")) {
			stud_rslt = StudentDAO.getListPerGradeDetail(pb.getClass_id(), pb.getCd_id(), ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			if(stud_rslt.size() > 0){
				payment_table = PaymentDAO.getSummerPaymentTable(stud_rslt);
			}
			class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String paymentInfoSave(){
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = PaymentDAO.saveMonthlyPayment(pb, getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			
			if(rslt){
				
				payment_info_more_than_amonth_receipt = PaymentDAO.getPaidReceiptInfo(pb);
				//payment_receipt_info = PaymentDAO.getPaymentReceiptInfo(pb);
				
				///*** getting the total and amount in words ***///
				double grand_total = 0.0;
				
				for(int i = 0; i < payment_info_more_than_amonth_receipt.size(); i++){
					grand_total = grand_total + Double.parseDouble(payment_info_more_than_amonth_receipt.get(i).getTotal_payment());
				}
				
				pb.setPayment_grand_total(String.valueOf(grand_total));
				pb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(grand_total)));
				
				stud_rslt = StudentDAO.getListPerGrade(pb.getClass_id(), pb.getAc_year());
				
				if(stud_rslt.size() > 0){
					
					payment_table = PaymentDAO.getPaymentTable(stud_rslt, pb);					
				}
				
				class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
				
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}				
	}
	
	public String paidPaymentFormForEdu(){
		if (sessionMap.containsKey("userName")) {
											
			payment_info_more_than_amonth_receipt = PaymentDAO.getPaidReceiptInfo(pb);
			
			double grand_total = 0.0;
			for(int i = 0; i < payment_info_more_than_amonth_receipt.size(); i++){
				grand_total = grand_total + Double.parseDouble(payment_info_more_than_amonth_receipt.get(i).getTotal_payment());
			}
			pb.setPayment_grand_total(String.valueOf(grand_total));
			pb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(grand_total)));
				
			return SUCCESS;

		} else {
			return INPUT;
		}				
	}
	
	public String paymentTutorialInfoSave(){
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = PaymentDAO.saveMonthlyPayment(pb, getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			
			if(rslt){
				
				payment_info_more_than_amonth_receipt = PaymentDAO.getPaidReceiptInfo(pb);
				//PaymentDAO.getPaymentReceiptInfo(pb);
				
				///*** getting the total and amount in words ***///
				double grand_total = 0.0;
				
				for(int i = 0; i < payment_info_more_than_amonth_receipt.size(); i++){
					grand_total = grand_total + Double.parseDouble(payment_info_more_than_amonth_receipt.get(i).getTotal_payment());
				}
				
				pb.setPayment_grand_total(String.valueOf(grand_total));
				pb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(grand_total)));
				
				return SUCCESS;
			} else {
				return ERROR;
			}
		}  else {
			return INPUT;
		}	
	}
	
	public String paymentSummerInfoSave(){
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = PaymentDAO.saveMonthlyPayment(pb, getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			
			if(rslt){
				
				payment_info_more_than_amonth_receipt = PaymentDAO.getPaidReceiptInfo(pb);
				//payment_receipt_info  = PaymentDAO.getPaymentReceiptInfo(pb);
				
				///*** getting the total and amount in words ***///
				double grand_total = 0.0;
				
				for(int i = 0; i < payment_info_more_than_amonth_receipt.size(); i++){
					grand_total = grand_total + Double.parseDouble(payment_info_more_than_amonth_receipt.get(i).getTotal_payment());
				}
				
				pb.setPayment_grand_total(String.valueOf(grand_total));
				pb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(grand_total)));
				
				return SUCCESS;
			} else {
				return ERROR;
			}
		}  else {
			return INPUT;
		}
	}
	
	public String paymentInfoForMoreThanAMonthSave(){
		if (sessionMap.containsKey("userName")) {
			
			pb.setFm_receipt_no(PaymentDAO.getLastFMReceiptNo());
			
			if(SpecialNeedRequiredDAO.checkStudSpecialNeedRequirment(pb.getSi_id()).size() > 0){
				
				payment_info_more_than_amonth = PaymentDAO.specialPaymentInfoMoreThanAmonth(pb);
				
			} else {
				
				payment_info_more_than_amonth = PaymentDAO.paymentInfoMoreThanAmonth(pb);
				System.out.println(payment_info_more_than_amonth.size() + " last size");
			}
			return SUCCESS;
		} else {
			return INPUT;
		}		
	}
	
	public String paymentInfoForMoreThanAMonthSaveSubmite(){
		
		if (sessionMap.containsKey("userName")) {
			
			boolean rslt = PaymentDAO.paymentInfoMoreThanAmonthSubmit(pb, getLoggedInUser((String)sessionMap.get("loggedInUserType")));
			
			if(rslt){
				
				payment_info_more_than_amonth_receipt = PaymentDAO.getPaidReceiptInfo(pb);
				//PaymentDAO.getPaymentReceiptInfoMoreThanAmonth();
				
				double subTotal = 0.0;
				
				for(int i = 0; i < payment_info_more_than_amonth_receipt.size(); i++){
					
					subTotal += Double.parseDouble(payment_info_more_than_amonth_receipt.get(i).getTotal_payment());
				}
				
				pb.setPayment_sub_total(String.valueOf(subTotal));
				
				pb.setPayment_grand_total(String.valueOf(subTotal));
				
				pb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(subTotal)));
				
				payment_receipt_info = PaymentDAO.getPaidReceiptInfo(pb);
				
				//PaymentDAO.getPaymentReceiptInfoMoreMonths(pb);
				
//				stud_rslt = StudentDAO.getListPerGrade(pb.getClass_id(), pb.getAcademic_year());
//				if(stud_rslt.size() > 0){
//					if(pType.equalsIgnoreCase("1")){
//						payment_table = PaymentDAO.getPaymentTable(stud_rslt);
//					} else if(pType.equalsIgnoreCase("2")){
//						payment_table = PaymentDAO.getTutorialPaymentTable(stud_rslt);
//					} else if(pType.equalsIgnoreCase("3")){
//						payment_table = PaymentDAO.getSummerPaymentTable(stud_rslt);
//					}
//				}
//				class_detail = StudentDAO.getClassDetail(pb.getClass_id(), (String)sessionMap.get("userName"));
				
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return INPUT;
		}
		
	}
	
	public String getSchoolInfoList(){
		if(sessionMap.containsKey("userName")){
			
			school_information = PaymentDAO.getSchoolInformation();			

			for(int i = 0; i < school_information.size(); i++){
				
				pb.setSchool_name(school_information.get(i).getSchool_name());
				pb.setSchool_slogan(school_information.get(i).getSchool_slogan());
				pb.setTin_num(school_information.get(i).getTin_num());
				pb.setTelephone(school_information.get(i).getTelephone());
				pb.setFax(school_information.get(i).getFax());
				pb.setWeb(school_information.get(i).getWeb());
				pb.setEmail(school_information.get(i).getEmail());
				pb.setPobox(school_information.get(i).getPobox());
				pb.setFiscal_machine_no(school_information.get(i).getFiscal_machine_no());
				
			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String saveSchoolInformation(){
		if(sessionMap.containsKey("userName")){
			boolean rslt = PaymentDAO.saveSchoolInformation(pb);
			if(rslt){
				return SUCCESS;
			} else {
				addActionError("School Information is not saved. Please try again later.");
				return ERROR;
			}
		} else {
			return INPUT;
		}
	}
	
	
	public String getTodaysCollection(){
		if(sessionMap.containsKey("userName")){
			
			ReportBean rb = new ReportBean();
			
			rb.setCol_date_from(TodayDate_YYYYMMDD.getToday());
			rb.setCol_date_to(TodayDate_YYYYMMDD.getToday());
			
			payment_collection = ReportDAO.dailyPaymentCollection(rb);
			Double grandTotalPayment = 0.0;
			for(int i = 0; i < payment_collection.size(); i++){
				grandTotalPayment = grandTotalPayment + Double.parseDouble(payment_collection.get(i).getPayment_bean().getPay_amount());
			}
			rb.setGrand_total(String.valueOf(grandTotalPayment));
			rb.setCol_date_from(TodayDate_YYYYMMDD.getDayMonthYearFormat());
			
			pb.setReport_bean(rb);
			
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String emailTodaysCollection(){
		if(sessionMap.containsKey("userName")){
			
			String today_eth = DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getDayMonthYearFormat());
			
			String emailto = ReportDAO.getEmailToList();			
			String emailSubject = SysConstant.SCHOOL_FULL_NAME + " - " + today_eth + " / " + TodayDate_YYYYMMDD.getDayMonthYearFormat() + " Collection";
			String emailBody = "<table width=\"100%\" cellpadding=\"0\" rules=\"none\" cellspacing=\"0\" style=\"border: thin; border-color: gray; border-style: solid; border-width: thin; padding: 10px;\">" +
									"<tr>" +
										"<td align=\"center\" style=\"font-weight: bold; padding: 10px;\">" + SysConstant.SCHOOL_FULL_NAME + "</td>" +
									"</tr>" +
									"<tr>" +
										"<td align=\"center\">" +
											today_eth + " (" + TodayDate_YYYYMMDD.getDayMonthYearFormat() + ") till " + TodayDate_YYYYMMDD.getHour() + " <br/> Total Collection is " + pb.getReport_bean().getGrand_total() + " Birr " +
										"</td>" +
									"</tr>" +
								"</table>";
			
			return EmailForm.getEmailForm(emailto, emailSubject, emailBody);
			
		} else {
			
			return INPUT;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public PaymentBean getModel() {
		return pb;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public List<StudentClassBean> getGrade_rslt() {
		return grade_rslt;
	}

	public void setGrade_rslt(List<StudentClassBean> grade_rslt) {
		this.grade_rslt = grade_rslt;
	}

	public List<StudentBean> getStud_rslt() {
		return stud_rslt;
	}

	public void setStud_rslt(List<StudentBean> stud_rslt) {
		this.stud_rslt = stud_rslt;
	}

	public List<StudentClassDetailBean> getClass_detail() {
		return class_detail;
	}

	public void setClass_detail(List<StudentClassDetailBean> class_detail) {
		this.class_detail = class_detail;
	}

	public List<PaymentBean> getPayment_table() {
		return payment_table;
	}

	public void setPayment_table(List<PaymentBean> payment_table) {
		this.payment_table = payment_table;
	}

	public List<PaymentBean> getPayment_month() {
		return payment_month;
	}

	public void setPayment_month(List<PaymentBean> payment_month) {
		this.payment_month = payment_month;
	}

	public List<PaymentBean> getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(List<PaymentBean> payment_amount) {
		this.payment_amount = payment_amount;
	}	

	public List<PaymentBean> getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(List<PaymentBean> payment_info) {
		this.payment_info = payment_info;
	}

	public List<PaymentBean> getPayment_type_list() {
		return payment_type_list;
	}

	public void setPayment_type_list(List<PaymentBean> payment_type_list) {
		this.payment_type_list = payment_type_list;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap(sessionMap);		
	}

	public List<AdminBean> getUsrRoleList() {
		return usrRoleList;
	}

	public void setUsrRoleList(List<AdminBean> usrRoleList) {
		this.usrRoleList = usrRoleList;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<PaymentBean> getPayment_month_selected() {
		return payment_month_selected;
	}

	public void setPayment_month_selected(List<PaymentBean> payment_month_selected) {
		this.payment_month_selected = payment_month_selected;
	}

	public List<PaymentBean> getPayment_info_more_than_amonth() {
		return payment_info_more_than_amonth;
	}

	public void setPayment_info_more_than_amonth(
			List<PaymentBean> payment_info_more_than_amonth) {
		this.payment_info_more_than_amonth = payment_info_more_than_amonth;
	}

	public List<PaymentBean> getMaterial_list() {
		return material_list;
	}

	public void setMaterial_list(List<PaymentBean> material_list) {
		this.material_list = material_list;
	}

	public List<PaymentBean> getPaid_material_list() {
		return paid_material_list;
	}

	public void setPaid_material_list(List<PaymentBean> paid_material_list) {
		this.paid_material_list = paid_material_list;
	}

	public List<PaymentBean> getPayment_receipt_info() {
		return payment_receipt_info;
	}

	public void setPayment_receipt_info(List<PaymentBean> payment_receipt_info) {
		this.payment_receipt_info = payment_receipt_info;
	}

	public List<PaymentBean> getPayment_info_more_than_amonth_receipt() {
		return payment_info_more_than_amonth_receipt;
	}

	public void setPayment_info_more_than_amonth_receipt(List<PaymentBean> payment_info_more_than_amonth_receipt) {
		this.payment_info_more_than_amonth_receipt = payment_info_more_than_amonth_receipt;
	}

	public List<PaymentBean> getSelected_material_list() {
		return selected_material_list;
	}

	public void setSelected_material_list(List<PaymentBean> selected_material_list) {
		this.selected_material_list = selected_material_list;
	}

	public List<PaymentBean> getSchool_information() {
		return school_information;
	}

	public void setSchool_information(List<PaymentBean> school_information) {
		this.school_information = school_information;
	}


	public List<ReportBean> getPayment_collection() {
		return payment_collection;
	}


	public void setPayment_collection(List<ReportBean> payment_collection) {
		this.payment_collection = payment_collection;
	}
	
	public PaymentBean getPayment_table_per_student() {
		return payment_table_per_student;
	}

	public void setPayment_table_per_student(PaymentBean payment_table_per_student) {
		this.payment_table_per_student = payment_table_per_student;
	}

}
