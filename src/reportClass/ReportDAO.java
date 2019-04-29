package reportClass;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cRoomClass.CRoomBean;
import connectionClass.Connector;
import examClass.exam_result.ExamResultBean;
import examClass.exam_result.ExamResultQueries;
import examClass.exam_type.ExamBean;
import paymentFineClass.PaymentBean;
import studentClass.StudentBean;
import studentClass.StudentClassBean;
import studentClass.StudentClassDetailBean;
import studentClass.StudentDAO;
import subjectClass.SubjectBean;
import util.AgeCalculator;
import util.DateConvertor;

public class ReportDAO {
	
	public static int getNoOfSubjectForAverage(ReportBean rb){
		int noOfSubject = 0;
		
		Connection con = null;
		PreparedStatement sub_ps = null;
		ResultSet sub_rs = null;
		
		try{
			
			///*** getting number of subject concerned for average
			con = Connector.connect();
			sub_ps = con.prepareStatement(ExamResultQueries.numOfSubPerGrade);
			sub_ps.setString(1, rb.getClass_bean().getCl_id());
			sub_rs = sub_ps.executeQuery();
			
			while(sub_rs.next()){
				noOfSubject = Integer.parseInt(sub_rs.getString("total"));
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
				con.close();
				} catch(Exception ex){}
			}
		}
		
		return noOfSubject;
	}
	
	public static List<ReportBean> getSubjectListPerClass(ReportBean rb){
		
		List<ReportBean> subList = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement sub_ps = null;
		ResultSet sub_rs = null;
		
		try{
			
			///*** getting subject list per class
			con = Connector.connect();
			sub_ps = con.prepareStatement(ReportQueries.subject_list_per_class);
			sub_ps.setString(1, rb.getClass_bean().getCl_id());
			sub_rs = sub_ps.executeQuery();
			
			while(sub_rs.next()){
				ReportBean rpb = new ReportBean();
				SubjectBean sj = new SubjectBean();
				
				sj.setSub_id(sub_rs.getString("sub_id"));
				sj.setSub_name(sub_rs.getString("sub_name"));
				
				rpb.setSub_bean(sj);
				subList.add(rpb);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
				con.close();
				} catch(Exception ex){}
			}
		}
		
		return subList;
	}
	
	public static List<ReportBean> getSubjectListPerClassExamSubRel(ReportBean rb){
		
		List<ReportBean> subList = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement sub_ps = null;
		ResultSet sub_rs = null;
		
		try{
			
			///*** getting subject list per class per exam_sub_rel
			con = Connector.connect();
			sub_ps = con.prepareStatement(ReportQueries.subject_list_per_class_exam_sub_rel);
			sub_ps.setString(1, rb.getClass_bean().getCl_id());
//			sub_ps.setString(2, rb.getCdetail_bean().getCd_id());
//			sub_ps.setString(3, rb.getAt_id());
//			sub_ps.setString(4, rb.getStud_bean().getAc_year());
//			sub_ps.setString(5, rb.getStud_bean().getAc_year());
			sub_rs = sub_ps.executeQuery();
			
			while(sub_rs.next()){
				
				int counter = 0;
				
				for(int j = 0; j < subList.size(); j++){
					
					if(sub_rs.getString("sub_id").equals(subList.get(j).getSub_bean().getSub_id())){
						
						counter++;
					}					
				}
				
				if(counter == 0){
					
					ReportBean rpb = new ReportBean();
					SubjectBean sb = new SubjectBean();
					
					sb.setSub_id(sub_rs.getString("sub_id"));
					sb.setSub_name(sub_rs.getString("sub_name"));
					
					rpb.setSub_bean(sb);
					subList.add(rpb);
				}
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
				con.close();
				} catch(Exception ex){}
			}
		}
				
		return subList;
	}
	
	public static List<SubjectBean> getSubjectListPerClassExamSubRelWithSubClId(ReportBean rb){
		
		List<SubjectBean> subList = new ArrayList<SubjectBean>();
		
		Connection con = null;
		PreparedStatement sub_ps = null;
		ResultSet sub_rs = null;
		
		try{
			
			///*** getting subject list per class per exam_sub_rel
			con = Connector.connect();
			sub_ps = con.prepareStatement(ReportQueries.subject_list_per_class_exam_sub_rel);
			sub_ps.setString(1, rb.getClass_bean().getCl_id());
//			sub_ps.setString(2, rb.getCdetail_bean().getCd_id());
//			sub_ps.setString(3, rb.getAt_id());
//			sub_ps.setString(4, rb.getStud_bean().getAc_year());
//			sub_ps.setString(5, rb.getStud_bean().getAc_year());
			sub_rs = sub_ps.executeQuery();
			
			while(sub_rs.next()){
				
				int counter = 0;
				
				for(int j = 0; j < subList.size(); j++){
					
					if(sub_rs.getString("sub_id").equals(subList.get(j).getSub_id())){
						
						counter++;
					}					
				}
				
				if(counter == 0){
					
					SubjectBean sb = new SubjectBean();
					
					sb.setSubcl_id(sub_rs.getString("subcl_id"));
					sb.setSub_id(sub_rs.getString("sub_id"));
					sb.setSub_name(sub_rs.getString("sub_name"));
					
					subList.add(sb);
				}
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
				con.close();
				} catch(Exception ex){}
			}
		}
				
		return subList;
	}
	
	public static List<ReportBean> getSubjectListPerClassCategory(ReportBean rb){
		
		List<ReportBean> subList = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement sub_ps = null;
		ResultSet sub_rs = null;
		
		try{
			String cl_id = "";
			if(rb.getClass_bean().getCl_id().equals("1")){
				cl_id = "1";
			} else if(rb.getClass_bean().getCl_id().equals("2")){
				cl_id = "5";
			} else if(rb.getClass_bean().getCl_id().equals("3")){
				cl_id = "7";
			} else if(rb.getClass_bean().getCl_id().equals("4")){
				cl_id = "9";
			}
			
			///*** getting subject list per class
			con = Connector.connect();
			sub_ps = con.prepareStatement(ReportQueries.subject_list_per_class);
			sub_ps.setString(1, cl_id);
			sub_rs = sub_ps.executeQuery();
			
			while(sub_rs.next()){
				ReportBean rpb = new ReportBean();
				SubjectBean sj = new SubjectBean();
				
				sj.setSub_id(sub_rs.getString("sub_id"));
				sj.setSub_name(sub_rs.getString("sub_name"));
				
				rpb.setSub_bean(sj);
				subList.add(rpb);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
				con.close();
				} catch(Exception ex){}
			}
		}
		
		return subList;
	}
	
	public static List<ReportBean> getStudListPerClass(ReportBean rb){
		
		List<ReportBean> studList = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement stud_ps = null;
		ResultSet stud_rs = null;
		
		try{
			
			///*** getting student list per class
			con = Connector.connect();
			
			if(rb.getEduoff_filter_status().equalsIgnoreCase("yes")){
				stud_ps = con.prepareStatement(ReportQueries.filtered_student_list_per_class);
			} else { 
				stud_ps = con.prepareStatement(ReportQueries.student_list_per_class);
			}
			stud_ps.setString(1, rb.getClass_bean().getCl_id());
			stud_ps.setString(2, rb.getCdetail_bean().getCd_id());
			stud_ps.setString(3, rb.getStud_bean().getAc_year());
			stud_rs = stud_ps.executeQuery();
			
			while(stud_rs.next()){
				ReportBean rpb = new ReportBean();
				StudentBean sb = new StudentBean();
				
				sb.setSi_id(stud_rs.getString("si_id"));
				sb.setFname(stud_rs.getString("fname"));
				sb.setMname(stud_rs.getString("mname"));
				sb.setGname(stud_rs.getString("gname"));
				sb.setSex(stud_rs.getString("sex"));
				sb.setDob(stud_rs.getString("dob"));
				
				rpb.setStud_bean(sb);				
				studList.add(rpb);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
				con.close();
				} catch(Exception ex){}
			}
		}
		
		return studList;
	}
	
	public static List<ReportBean> getClassQuarterMarkList(ReportBean rb){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		List<ReportBean> examtype = new ArrayList<ReportBean>();
		List<ReportBean> studList = new ArrayList<ReportBean>();
		List<ReportBean> markList = new ArrayList<ReportBean>();
		
		Connection con = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PreparedStatement stud_ps = null;
		ResultSet stud_rs = null;
		
		PreparedStatement mlist_ps = null;
		ResultSet mlist_rs = null;
		
		try{
			
			con = Connector.connect();
			
			///*** getting exam type related to subject selected
			ps = con.prepareStatement(ReportQueries.examtype_per_subject);
			ps.setString(1, rb.getClass_bean().getCl_id());
			ps.setString(2, rb.getSub_bean().getSub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ReportBean rpb = new ReportBean();
				ExamBean eb = new ExamBean();
				
				eb.setEt_id(rs.getString("et_id"));
				
				rpb.setExam_bean(eb);				
				examtype.add(rpb);
			}
			
			
			///*** getting student list per class			
			if(rb.getEduoff_filter_status().equalsIgnoreCase("yes")){
				stud_ps = con.prepareStatement(ReportQueries.filtered_student_list_per_class);
			} else {
				stud_ps = con.prepareStatement(ReportQueries.student_list_per_class);
			}
			stud_ps.setString(1, rb.getClass_bean().getCl_id());
			stud_ps.setString(2, rb.getCdetail_bean().getCd_id());
			stud_ps.setString(3, rb.getStud_bean().getAc_year());
			stud_rs = stud_ps.executeQuery();
			
			while(stud_rs.next()){
				ReportBean rpb = new ReportBean();
				StudentBean sb = new StudentBean();
				
				sb.setSi_id(stud_rs.getString("si_id"));
				
				rpb.setStud_bean(sb);				
				studList.add(rpb);
			}
			
			///*** getting mark list per subject and class
			if(rb.getEduoff_filter_status().equalsIgnoreCase("yes")){
				mlist_ps = con.prepareStatement(ReportQueries.filtered_quarter_mark_list_per_class_subject);
			} else {
				mlist_ps = con.prepareStatement(ReportQueries.quarter_mark_list_per_class_subject);
			}
			mlist_ps.setString(1, rb.getClass_bean().getCl_id());
			mlist_ps.setString(2, rb.getSub_bean().getSub_id());
			mlist_ps.setString(3, rb.getAt_id());
			mlist_ps.setString(4, rb.getCdetail_bean().getCd_id());
			mlist_ps.setString(5, rb.getStud_bean().getAc_year());
			mlist_rs = mlist_ps.executeQuery();
			
			while(mlist_rs.next()){
				
				ReportBean rpb = new ReportBean();
				StudentBean stb = new StudentBean();
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(mlist_rs.getString("si_id"));
				exb.setFname(mlist_rs.getString("fname"));
				exb.setMname(mlist_rs.getString("mname"));
				exb.setGname(mlist_rs.getString("gname"));
								
//				String dob = mlist_rs.getString("dob");
//				System.out.println(dob + " dob in dao ");
//				AgeCalculator.calculateAge(dob);
//				stb.setAge(String.valueOf(AgeCalculator.calculateAge(dob)));
//				System.out.println(stb.getAge() + " age");
//				exb.setStud(stb);
				
				stb.setSex(mlist_rs.getString("sex"));
				stb.setDob(mlist_rs.getString("dob"));				
				exb.setStud(stb);
				
				exb.setAt_id(mlist_rs.getString("at_id"));
				exb.setExsub_id(mlist_rs.getString("examsub_id"));
				exb.setResult(mlist_rs.getString("result"));
				exb.setEt_id(mlist_rs.getString("et_id"));
				
				rpb.setEx_rslt_bean(exb);
				markList.add(rpb);
			}
			
			///*** organizing the mark list
			int count = 0;
			for(int i = 0; i < studList.size(); i++){
				
				Double exam_one = 0.0;
				Double exam_two = 0.0;
				Double worksheet = 0.0;
				Double activity = 0.0;
				Double finalExam = 0.0;
				
				ReportBean rpt = new ReportBean();
				
				for(int j = 0; j < markList.size(); j++){
					
					if(studList.get(i).getStud_bean().getSi_id().equals(markList.get(j).getEx_rslt_bean().getSi_id())){						
						
						StudentBean sb = new StudentBean();
						
						sb.setSi_id(markList.get(j).getEx_rslt_bean().getSi_id());
						sb.setFname(markList.get(j).getEx_rslt_bean().getFname());
						sb.setMname(markList.get(j).getEx_rslt_bean().getMname());
						sb.setGname(markList.get(j).getEx_rslt_bean().getGname());
						sb.setSex(markList.get(j).getEx_rslt_bean().getStud().getSex());
						sb.setDob(markList.get(j).getEx_rslt_bean().getStud().getDob());
						
						rpt.setStud_bean(sb);
										
						for(int x = 0; x < markList.size(); x++){
							
							boolean val = studList.get(i).getStud_bean().getSi_id().equals(markList.get(x).getEx_rslt_bean().getSi_id());
													
							if(val && markList.get(x).getEx_rslt_bean().getEt_id().equals("1")){
								
								exam_one = Double.parseDouble(markList.get(x).getEx_rslt_bean().getResult());
								rpt.setExam_one(markList.get(x).getEx_rslt_bean().getResult());
							}
							if(val && markList.get(x).getEx_rslt_bean().getEt_id().equals("2")){
								
								exam_two = Double.parseDouble(markList.get(x).getEx_rslt_bean().getResult());
								rpt.setExam_two(markList.get(x).getEx_rslt_bean().getResult());
							}
							if(val && markList.get(x).getEx_rslt_bean().getEt_id().equals("3")){
								
								worksheet = Double.parseDouble(markList.get(x).getEx_rslt_bean().getResult());
								rpt.setWorksheet(markList.get(x).getEx_rslt_bean().getResult());
							}
							if(val && markList.get(x).getEx_rslt_bean().getEt_id().equals("4")){
								
								activity = Double.parseDouble(markList.get(x).getEx_rslt_bean().getResult());
								rpt.setActivity(markList.get(x).getEx_rslt_bean().getResult());
							}
							if(val && markList.get(x).getEx_rslt_bean().getEt_id().equals("5")){
								
								finalExam = Double.parseDouble(markList.get(x).getEx_rslt_bean().getResult());
								rpt.setFinalExam(markList.get(x).getEx_rslt_bean().getResult());
							}
							
							count++;
						}
						
						rpt.setSub_total(String.valueOf(exam_one + exam_two + worksheet + activity));
						rpt.setGrand_total(String.valueOf(exam_one + exam_two + worksheet + activity + finalExam));
												
					}
				}
				if(count > 0){
					rslt.add(rpt);
				}
				count = 0;
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){}
			}
		}
				
		return rslt;
	}
	
	public static List<ReportBean> getClassQuarterMarkList_New(ReportBean rb){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		List<ReportBean> studList = new ArrayList<ReportBean>();
		List<ExamBean> examtype = new ArrayList<ExamBean>();
		List<ReportBean> markList = new ArrayList<ReportBean>();
		List<ReportBean> sortedMarkList = new ArrayList<ReportBean>();
		
		Connection con = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PreparedStatement mlist_ps = null;
		ResultSet mlist_rs = null;
		
		try{
			
			con = Connector.connect();
			
			///*** getting exam type related to subject selected
			ps = con.prepareStatement(ReportQueries.examtype_per_subjectClassRel);
			ps.setString(1, rb.getClass_bean().getCl_id());
			ps.setString(2, rb.getSub_bean().getSub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ReportBean rpb = new ReportBean();
				ExamBean eb = new ExamBean();
				
				eb.setEt_id(rs.getString("et_id"));
				
				rpb.setExam_bean(eb);				
				examtype.add(eb);
			}
			
			///*** getting mark list per subject and class
			if(rb.getEduoff_filter_status().equalsIgnoreCase("yes")){
				mlist_ps = con.prepareStatement(ReportQueries.filtered_quarter_mark_list_per_class_subject);
			} else {
				mlist_ps = con.prepareStatement(ReportQueries.quarter_mark_list_per_class_subject);
			}
			mlist_ps.setString(1, rb.getClass_bean().getCl_id());
			mlist_ps.setString(2, rb.getSub_bean().getSub_id());
			mlist_ps.setString(3, rb.getAt_id());
			mlist_ps.setString(4, rb.getCdetail_bean().getCd_id());
			mlist_ps.setString(5, rb.getStud_bean().getAc_year());
			mlist_rs = mlist_ps.executeQuery();
			
			while(mlist_rs.next()){
				
				ReportBean rpb = new ReportBean();
				StudentBean stb = new StudentBean();
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(mlist_rs.getString("si_id"));
				exb.setFname(mlist_rs.getString("fname"));
				exb.setMname(mlist_rs.getString("mname"));
				exb.setGname(mlist_rs.getString("gname"));
				
				stb.setSex(mlist_rs.getString("sex"));
				stb.setDob(mlist_rs.getString("dob"));				
				exb.setStud(stb);
				
				exb.setAt_id(mlist_rs.getString("at_id"));
				exb.setExsub_id(mlist_rs.getString("examsub_id"));
				exb.setResult(mlist_rs.getString("result"));
				exb.setEt_id(mlist_rs.getString("et_id"));
				
				rpb.setEx_rslt_bean(exb);
				markList.add(rpb);
			}
			
			
			studList = getStudListPerClass(rb);
			
			for(int z = 0; z < studList.size(); z++){
				
				List<Integer> picked_index = new ArrayList<Integer>();
						
				for(int j = 0; j < markList.size(); j++){
					
					if(studList.get(z).getStud_bean().getSi_id().equals(markList.get(j).getEx_rslt_bean().getSi_id())){
						
						picked_index.add(j);
					}					
				}
					
				for(int i = 0; i < examtype.size(); i++){
					
					int check = -1;
					
					for(int y = 0; y < picked_index.size(); y++){
						
						if(examtype.get(i).getEt_id().equals(markList.get(picked_index.get(y)).getEx_rslt_bean().getEt_id())){
							
							check = y;
							break;
						}
					}
					
					if(check == -1){
						
						ReportBean rpb = new ReportBean();
						StudentBean stb = new StudentBean();
						ExamResultBean exb = new ExamResultBean();
						
						exb.setSi_id(studList.get(z).getStud_bean().getSi_id());
						exb.setFname(studList.get(z).getStud_bean().getFname());
						exb.setMname(studList.get(z).getStud_bean().getMname());
						exb.setGname(studList.get(z).getStud_bean().getGname());
						
						stb.setSex(studList.get(z).getStud_bean().getSex());
						stb.setDob(studList.get(z).getStud_bean().getDob());				
						exb.setStud(stb);
						
						exb.setAt_id("At_id");
						exb.setExsub_id("");
						exb.setResult("0.0");
						exb.setEt_id(examtype.get(i).getEt_id());
						
						rpb.setEx_rslt_bean(exb);
						sortedMarkList.add(rpb);
						
					} else {
						
						sortedMarkList.add(markList.get(picked_index.get(check)));
					}
				}
			}
			
			///*** organizing the mark list
			if(examtype.size() > 0){
				
				for(int j = 0; j < sortedMarkList.size(); j+=examtype.size()){					
						
					ReportBean rpt = new ReportBean();
					
					StudentBean sb = new StudentBean();
					
					sb.setSi_id(sortedMarkList.get(j).getEx_rslt_bean().getSi_id());
					sb.setFname(sortedMarkList.get(j).getEx_rslt_bean().getFname());
					sb.setMname(sortedMarkList.get(j).getEx_rslt_bean().getMname());
					sb.setGname(sortedMarkList.get(j).getEx_rslt_bean().getGname());
					sb.setSex(sortedMarkList.get(j).getEx_rslt_bean().getStud().getSex());
					sb.setDob(sortedMarkList.get(j).getEx_rslt_bean().getStud().getDob());
					
					rpt.setStud_bean(sb);
					
					List<String> resultSetPerStudent = new ArrayList<String>();
					
					double subject_total = 0.0, grand_total = 0.0;
					
					for(int i = 0; i < examtype.size(); i++){
						
						if(i < examtype.size() - 1){
							
							subject_total += Double.parseDouble(sortedMarkList.get(j+i).getEx_rslt_bean().getResult());
						}
							
						grand_total += Double.parseDouble(sortedMarkList.get(j+i).getEx_rslt_bean().getResult());
						
						if(i == examtype.size() - 1){
							
							resultSetPerStudent.add(String.valueOf(subject_total));
						}
						
						resultSetPerStudent.add(sortedMarkList.get(j+i).getEx_rslt_bean().getResult());
		
						if(i == examtype.size() - 1){
							
							resultSetPerStudent.add(String.valueOf(grand_total));
						}						
					}
					
					ExamResultBean erb = new ExamResultBean();
					erb.setAllExamResult(resultSetPerStudent);
					
					rpt.setEx_rslt_bean(erb);
					
					rslt.add(rpt);
				}
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){}
			}
		}
				
		return rslt;
	}
	
	public static List<ReportBean> getQuarterStudentRoster(ReportBean rb, List<ReportBean> subject_list){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		List<ReportBean> studList = new ArrayList<ReportBean>();
		List<ReportBean> subList = new ArrayList<ReportBean>();
		List<ReportBean> studMarkList = new ArrayList<ReportBean>();
		
		Connection con = null;
		
		PreparedStatement stud_ps = null;
		ResultSet stud_rs = null;
		
		PreparedStatement mark_ps = null;
		ResultSet mark_rs = null;
		
		try{
			
			con = Connector.connect();
			
			subList = subject_list;
			
			///*** getting stud general mark list
			if(rb.getEduoff_filter_status().equalsIgnoreCase("yes")){
				mark_ps = con.prepareStatement(ReportQueries.all_subject_filtered_student_mark_list);
			} else {
				mark_ps = con.prepareStatement(ReportQueries.all_subject_student_mark_list);
			}
			mark_ps.setString(1, rb.getClass_bean().getCl_id());
			mark_ps.setString(2, rb.getCdetail_bean().getCd_id());
			mark_ps.setString(3, rb.getAt_id());
			mark_ps.setString(4, rb.getStud_bean().getAc_year());
			mark_ps.setString(5, rb.getStud_bean().getAc_year());
			mark_rs = mark_ps.executeQuery();
			
			while(mark_rs.next()){
				
				ReportBean rpb = new ReportBean();
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(mark_rs.getString("si_id"));
				exb.setFname(mark_rs.getString("fname"));
				exb.setMname(mark_rs.getString("mname"));
				exb.setGname(mark_rs.getString("gname"));				
				exb.setAt_id(mark_rs.getString("at_id"));
				exb.setSub_id(mark_rs.getString("sub_id"));
				exb.setSub_name(mark_rs.getString("sub_name"));
				exb.setMark_status(mark_rs.getString("add_status"));
				exb.setConvert_to_grade(mark_rs.getString("convert_to_grade"));
				exb.setResult(mark_rs.getString("sub_total"));
				
				rpb.setEx_rslt_bean(exb);				
				studMarkList.add(rpb);
			}
			
			///*** organizing student roster information
			studList = getStudListPerClass(rb);
			
			for(int i = 0; i < studList.size(); i++){
				
				ReportBean rpt = new ReportBean();
				
				StudentBean sb = new StudentBean();
				
				sb.setSi_id(studList.get(i).getStud_bean().getSi_id());
				sb.setFname(studList.get(i).getStud_bean().getFname());
				sb.setMname(studList.get(i).getStud_bean().getMname());
				sb.setGname(studList.get(i).getStud_bean().getGname());
				sb.setSex(studList.get(i).getStud_bean().getSex());
				
				rpt.setStud_bean(sb);
				
				Double stud_total = 0.0;
				
				for(int j = 0; j < studMarkList.size(); j++){				
					
					if(studList.get(i).getStud_bean().getSi_id().equals(studMarkList.get(j).getEx_rslt_bean().getSi_id())){
						
						if(studMarkList.get(j).getEx_rslt_bean().getConvert_to_grade().equals("YES")){
							
							rpt.getSubject_total().add("A");
							
						} else {
						
							rpt.getSubject_total().add(studMarkList.get(j).getEx_rslt_bean().getResult());
						}
						
						if(studMarkList.get(j).getEx_rslt_bean().getMark_status().equals("Yes")){
							
							stud_total = stud_total + Double.parseDouble(studMarkList.get(j).getEx_rslt_bean().getResult());
						}
					} 
				}
				
				///*** when all subject mark is not available
				if(rpt.getSubject_total().size() == 0){					
					for(int x = 0; x < subList.size(); x++){						
						rpt.getSubject_total().add("0");
					}
				}				
				
				ExamResultBean er = new ExamResultBean();
				
				er.setTotal_mark(String.valueOf(stud_total));
				
				er.setAverage_mark(String.valueOf(stud_total/getNoOfSubjectForAverage(rb)));
				
				rpt.setEx_rslt_bean(er);
				
				if(rpt.getSubject_total().size() == subList.size()){
					
					rslt.add(rpt);
				}				
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	
	
	private static List<SubjectBean> sub_list = new ArrayList<SubjectBean>();
	
	public static void addSelectedSubject(ReportBean rb){
		SubjectBean sb = new SubjectBean();
		sb.setSub_id(rb.getSub_bean().getSub_id());
		sub_list.add(sb);		
	}
	
	public static void removeSelectedSubject(ReportBean rb){
		for(int i = 0; i < sub_list.size(); i++){
			if(sub_list.get(i).getSub_id().equals(rb.getSub_bean().getSub_id())){
				sub_list.remove(i);
			}
		}
	}
	
	public static void clearSelectedSubject(){
		
		sub_list.clear();
	}
	
	public static List<ReportBean> getStudNumberPerMarkRang(ReportBean rb){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		//List<ReportBean> studList = new ArrayList<ReportBean>();
		List<ReportBean> subList = new ArrayList<ReportBean>();
		List<ReportBean> tempSubList = new ArrayList<ReportBean>();
		List<ReportBean> studMarkList = new ArrayList<ReportBean>();
		
		Connection con = null;		
		PreparedStatement mark_ps = null;
		ResultSet mark_rs = null;
		
		try{
			
			con = Connector.connect();
			
			///*** get subject list per class
			if(sub_list.size() == 0){
				subList = getSubjectListPerClassCategory(rb);
			} else {
				tempSubList = getSubjectListPerClassCategory(rb);
				for(int i = 0; i < sub_list.size(); i++){
					ReportBean rpb = new ReportBean();
					SubjectBean sj = new SubjectBean();
					
					sj.setSub_id(sub_list.get(i).getSub_id());
					
					for(int j = 0; j < tempSubList.size(); j++){
						if(sub_list.get(i).getSub_id().equals(tempSubList.get(j).getSub_bean().getSub_id())){
							sj.setSub_name(tempSubList.get(j).getSub_bean().getSub_name());
						}
					}
					
					rpb.setSub_bean(sj);
					subList.add(rpb);
				}
			}
			
			///*** class category
			List<String> cl_cat = new ArrayList<String>();
			
			if(rb.getClass_bean().getCl_id().equals("1")){
				
				cl_cat.add("1"); cl_cat.add("2");cl_cat.add("3"); cl_cat.add("4");
				
			} else if(rb.getClass_bean().getCl_id().equals("2")){
				
				cl_cat.add("5"); cl_cat.add("6");
				
			} else if(rb.getClass_bean().getCl_id().equals("3")){
				
				cl_cat.add("7"); //cl_cat.add("8");
				
			} else if(rb.getClass_bean().getCl_id().equals("4")){
				
				cl_cat.add("9"); //cl_cat.add("10");
			}
			
			///*** get number of male and female students per class
			double total_male_num = 0.0;
			for(int i = 0; i < cl_cat.size(); i++){
				total_male_num += (double)getNumberOfStudentsPerSex(rb, cl_cat.get(i), "M");
			}
			
			double total_female_num =  0.0;
			for(int i = 0; i < cl_cat.size(); i++){
				total_female_num += (double)getNumberOfStudentsPerSex(rb, cl_cat.get(i), "F");
			}
			
			///*** getting stud general mark list
			
			for(int i = 0; i < cl_cat.size(); i++){
			if(rb.getEduoff_filter_status().equalsIgnoreCase("yes")){
				mark_ps = con.prepareStatement(ReportQueries.getTotalSubjectMarkPerClassFilteredStud);
			} else {
				mark_ps = con.prepareStatement(ReportQueries.getTotalSubjectMarkPerClass);
			}
			mark_ps.setString(1, cl_cat.get(i));			
			mark_ps.setString(2, rb.getAt_id());
			mark_ps.setString(3, rb.getStud_bean().getAc_year());
			mark_ps.setString(4, rb.getStud_bean().getAc_year());
			mark_rs = mark_ps.executeQuery();
			
			while(mark_rs.next()){
				ReportBean rpb = new ReportBean();
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(mark_rs.getString("si_id"));
				exb.setFname(mark_rs.getString("fname"));
				exb.setMname(mark_rs.getString("mname"));
				exb.setGname(mark_rs.getString("gname"));				
				exb.setAt_id(mark_rs.getString("at_id"));
				exb.setSub_id(mark_rs.getString("sub_id"));
				exb.setMark_status(mark_rs.getString("add_status"));
				exb.setConvert_to_grade(mark_rs.getString("convert_to_grade"));
				exb.setResult(mark_rs.getString("sub_total"));
				
				rpb.setEx_rslt_bean(exb);
				
				StudentBean stb = new StudentBean();
				stb.setSex(mark_rs.getString("sex"));
				
				rpb.setStud_bean(stb);
				
				studMarkList.add(rpb);
			}
			}
			
			////**** count per each subject ****////
			DecimalFormat fmt = new DecimalFormat("###.##");
						
			for(int i = 0; i < subList.size(); i++){
				int male_counter_cat1 = 0, female_counter_cat1 = 0, male_counter_cat2 = 0, female_counter_cat2 = 0, male_counter_cat3 = 0, female_counter_cat3 = 0;
				for(int j = 0; j < studMarkList.size(); j++){
					
					if(subList.get(i).getSub_bean().getSub_id().equals(studMarkList.get(j).getEx_rslt_bean().getSub_id())){
						
						if(Double.parseDouble(studMarkList.get(j).getEx_rslt_bean().getResult()) >= 50.0 && Double.parseDouble(studMarkList.get(j).getEx_rslt_bean().getResult()) < 75.0){
							if(studMarkList.get(j).getStud_bean().getSex().equals("M")){
								male_counter_cat1++;
							}
							if(studMarkList.get(j).getStud_bean().getSex().equals("F")){
								female_counter_cat1++;
							}
						}
						if(Double.parseDouble(studMarkList.get(j).getEx_rslt_bean().getResult()) >= 75.0 && Double.parseDouble(studMarkList.get(j).getEx_rslt_bean().getResult()) < 90.0){
							if(studMarkList.get(j).getStud_bean().getSex().equals("M")){
								male_counter_cat2++;
							}
							if(studMarkList.get(j).getStud_bean().getSex().equals("F")){
								female_counter_cat2++;
							}
						}
						if(Double.parseDouble(studMarkList.get(j).getEx_rslt_bean().getResult()) >= 90.0){
							if(studMarkList.get(j).getStud_bean().getSex().equals("M")){
								male_counter_cat3++;
							}
							if(studMarkList.get(j).getStud_bean().getSex().equals("F")){
								female_counter_cat3++;
							}
						}
					}
				}
				
				ReportBean rpb = new ReportBean();
				SubjectBean sbb = new SubjectBean();
				StudentBean stb = new StudentBean();
				
				int tm_num = (int)total_male_num;
				int tf_num = (int)total_female_num;
				
				stb.setNo_male(String.valueOf(tm_num));
				stb.setNo_female(String.valueOf(tf_num));
				stb.setTotal_number(String.valueOf(tm_num + tf_num));
				
				rpb.setStud_bean(stb);
								
				sbb.setSub_id(subList.get(i).getSub_bean().getSub_id());
				sbb.setSub_name(subList.get(i).getSub_bean().getSub_name());				
				rpb.setSub_bean(sbb);
				
				///**** category one ****///
				rpb.setMale_stat_percent_cat1(Double.parseDouble(fmt.format((male_counter_cat1 * 0.5)/100)));
				rpb.setFemale_stat_percent_cat1(Double.parseDouble(fmt.format((female_counter_cat1 * 0.5)/100)));
				rpb.setTotal_stat_percent_cat1(Double.parseDouble(fmt.format(((male_counter_cat1 + female_counter_cat1)*0.5)/100)));
				
				rpb.setMale_num_cat1(male_counter_cat1);
				rpb.setFemale_num_cat1(female_counter_cat1);
				rpb.setTotal_num_cat1(male_counter_cat1 + female_counter_cat1);
				
				String mp_cat1 = fmt.format((male_counter_cat1 * 100)/total_male_num);
				String fp_cat1 = fmt.format((female_counter_cat1 * 100)/total_female_num);
				String tp_cat1 = fmt.format(((male_counter_cat1 + female_counter_cat1) * 100)/(total_male_num + total_female_num));
				
				rpb.setMale_percent_cat1(Double.parseDouble(mp_cat1));
				rpb.setFemale_percent_cat1(Double.parseDouble(fp_cat1));
				rpb.setTotal_percent_cat1(Double.parseDouble(tp_cat1));
				
				///**** second category ****///
				rpb.setMale_stat_percent_cat2(Double.parseDouble(fmt.format((male_counter_cat2 * 0.35)/100)));
				rpb.setFemale_stat_percent_cat2(Double.parseDouble(fmt.format((female_counter_cat2 * 0.35)/100)));
				rpb.setTotal_stat_percent_cat2(Double.parseDouble(fmt.format(((male_counter_cat2 + female_counter_cat2)*0.35)/100)));
				
				rpb.setMale_num_cat2(male_counter_cat2);
				rpb.setFemale_num_cat2(female_counter_cat2);
				rpb.setTotal_num_cat2(male_counter_cat2 + female_counter_cat2);
				
				String mp_cat2 = fmt.format((male_counter_cat2 * 100)/total_male_num);
				String fp_cat2 = fmt.format((female_counter_cat2 * 100)/total_female_num);
				String tp_cat2 = fmt.format(((male_counter_cat2 + female_counter_cat2) * 100)/(total_male_num + total_female_num));
				
				rpb.setMale_percent_cat2(Double.parseDouble(mp_cat2));
				rpb.setFemale_percent_cat2(Double.parseDouble(fp_cat2));
				rpb.setTotal_percent_cat2(Double.parseDouble(tp_cat2));
				
				///**** category three ****///
				rpb.setMale_stat_percent_cat3(Double.parseDouble(fmt.format((male_counter_cat3 * 0.15)/100)));
				rpb.setFemale_stat_percent_cat3(Double.parseDouble(fmt.format((female_counter_cat3 * 0.15)/100)));
				rpb.setTotal_stat_percent_cat3(Double.parseDouble(fmt.format(((male_counter_cat3 + female_counter_cat3)*0.15)/100)));
								
				rpb.setMale_num_cat3(male_counter_cat3);
				rpb.setFemale_num_cat3(female_counter_cat3);
				rpb.setTotal_num_cat3(male_counter_cat3 + female_counter_cat3);
				
				String mp_cat3 = fmt.format((male_counter_cat3 * 100)/total_male_num);
				String fp_cat3 = fmt.format((female_counter_cat3 * 100)/total_female_num);
				String tp_cat3 = fmt.format(((male_counter_cat3 + female_counter_cat3) * 100)/(total_male_num + total_female_num));
				
				rpb.setMale_percent_cat3(Double.parseDouble(mp_cat3));
				rpb.setFemale_percent_cat3(Double.parseDouble(fp_cat3));
				rpb.setTotal_percent_cat3(Double.parseDouble(tp_cat3));
				
				///**** final Category ****///
				rpb.setMale_grt_50(male_counter_cat1 + male_counter_cat2 + male_counter_cat3);
				rpb.setFemale_grt_50(female_counter_cat1 + female_counter_cat2 + female_counter_cat3);
				rpb.setTotal_grt_50(male_counter_cat1 + male_counter_cat2 + male_counter_cat3 + female_counter_cat1 + female_counter_cat2 + female_counter_cat3);
				
				String m_grt_50 = fmt.format(((male_counter_cat1 + male_counter_cat2 + male_counter_cat3)* 100)/total_male_num);
				String f_grt_50 = fmt.format(((female_counter_cat1 + female_counter_cat2 + female_counter_cat3)* 100)/total_female_num);
				String t_grt_50 = fmt.format(((male_counter_cat1 + male_counter_cat2 + male_counter_cat3 + female_counter_cat1 + female_counter_cat2 + female_counter_cat3)*100)/(total_male_num + total_female_num));
				
				rpb.setMale_percent_grt_50(Double.parseDouble(m_grt_50));
				rpb.setFemale_percent_grt_50(Double.parseDouble(f_grt_50));
				rpb.setTotal_percent_grt_50(Double.parseDouble(t_grt_50));
				
				rslt.add(rpb);								
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	
	public static int getNumberOfStudentsPerSex(ReportBean rb, String cl_id, String sex){
		int stud_num = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			if(rb.getEduoff_filter_status().equalsIgnoreCase("yes")){
				ps = con.prepareStatement(ReportQueries.getNumberOfStudentPerSexFiltered);
			} else {
				ps = con.prepareStatement(ReportQueries.getNumberOfStudentPerSex);
			}
			ps.setString(1, cl_id);
			ps.setString(2, rb.getStud_bean().getAc_year());
			ps.setString(3, sex);
			rs = ps.executeQuery();
			
			while(rs.next()){
				stud_num = Integer.parseInt(rs.getString("total"));
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return stud_num;
	}
	
	
	public static List<ReportBean> getStudMarkListNumberPerMarkRang(ReportBean rb){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		//List<ReportBean> studList = new ArrayList<ReportBean>();
		List<ReportBean> tempSubList = new ArrayList<ReportBean>();
		List<ReportBean> subList = new ArrayList<ReportBean>();
		List<ReportBean> studMarkList = new ArrayList<ReportBean>();
		
		Connection con = null;		
		PreparedStatement mark_ps = null;
		ResultSet mark_rs = null;
		
		try{
			
			con = Connector.connect();
			
			///*** get subject list per class
			if(sub_list.size() == 0){
				subList = getSubjectListPerClassCategory(rb);
			} else {
				tempSubList = getSubjectListPerClassCategory(rb);
				for(int i = 0; i < sub_list.size(); i++){
					ReportBean rpb = new ReportBean();
					SubjectBean sj = new SubjectBean();
					
					sj.setSub_id(sub_list.get(i).getSub_id());
					
					for(int j = 0; j < tempSubList.size(); j++){
						if(sub_list.get(i).getSub_id().equals(tempSubList.get(j).getSub_bean().getSub_id())){
							sj.setSub_name(tempSubList.get(j).getSub_bean().getSub_name());
						}
					}
					
					rpb.setSub_bean(sj);
					subList.add(rpb);
				}
			}
			
			///*** class category
			List<String> cl_cat = new ArrayList<String>();
			
			if(rb.getClass_bean().getCl_id().equals("1")){
				
				cl_cat.add("1"); cl_cat.add("2");cl_cat.add("3"); cl_cat.add("4");
				
			} else if(rb.getClass_bean().getCl_id().equals("2")){
				
				cl_cat.add("5"); cl_cat.add("6");
				
			} else if(rb.getClass_bean().getCl_id().equals("3")){
				
				cl_cat.add("7"); //cl_cat.add("8");
				
			} else if(rb.getClass_bean().getCl_id().equals("4")){
				
				cl_cat.add("9"); //cl_cat.add("10");
			}
			
			///*** get number of male and female students per class
			double total_male_num = 0.0;
			for(int i = 0; i < cl_cat.size(); i++){
				total_male_num += (double)getNumberOfStudentsPerSex(rb, cl_cat.get(i), "M");
			}
			
			double total_female_num =  0.0;
			for(int i = 0; i < cl_cat.size(); i++){
				total_female_num += (double)getNumberOfStudentsPerSex(rb, cl_cat.get(i), "F");
			}
			
			///*** getting stud general mark list
			
			for(int i = 0; i < cl_cat.size(); i++){
				
				if(rb.getEduoff_filter_status().equalsIgnoreCase("yes")){
					mark_ps = con.prepareStatement(ReportQueries.getTotalSubjectMarkPerClassFilteredStud);
				} else {
					mark_ps = con.prepareStatement(ReportQueries.getTotalSubjectMarkPerClass);
				}
				mark_ps.setString(1, cl_cat.get(i));			
				mark_ps.setString(2, rb.getAt_id());
				mark_ps.setString(3, rb.getStud_bean().getAc_year());
				mark_rs = mark_ps.executeQuery();
				
				while(mark_rs.next()){
					ReportBean rpb = new ReportBean();
					ExamResultBean exb = new ExamResultBean();
					
					exb.setSi_id(mark_rs.getString("si_id"));
					exb.setFname(mark_rs.getString("fname"));
					exb.setMname(mark_rs.getString("mname"));
					exb.setGname(mark_rs.getString("gname"));				
					exb.setAt_id(mark_rs.getString("at_id"));
					exb.setSub_id(mark_rs.getString("sub_id"));
					exb.setMark_status(mark_rs.getString("add_status"));
					exb.setConvert_to_grade(mark_rs.getString("convert_to_grade"));
					exb.setResult(mark_rs.getString("sub_total"));
					
					rpb.setEx_rslt_bean(exb);
					
					StudentBean stb = new StudentBean();
					stb.setSex(mark_rs.getString("sex"));
					
					rpb.setStud_bean(stb);
					
					studMarkList.add(rpb);
				}
			}
			
			////**** count per each subject ****////
			DecimalFormat fmt = new DecimalFormat("###.##");
			
			int grt_50_male_total_cat1 = 0, grt_50_female_total_cat1 = 0, grt_50_total_cat1 = 0;
			int grt_75_male_total_cat2 = 0, grt_75_female_total_cat2 = 0, grt_75_total_cat2 = 0;
			int grt_90_male_total_cat3 = 0, grt_90_female_total_cat3 = 0, grt_90_total_cat3 = 0;
			
			for(int i = 0; i < subList.size(); i++){
				int male_counter_cat1 = 0, female_counter_cat1 = 0, male_counter_cat2 = 0, female_counter_cat2 = 0, male_counter_cat3 = 0, female_counter_cat3 = 0;
				for(int j = 0; j < studMarkList.size(); j++){
					
					if(subList.get(i).getSub_bean().getSub_id().equals(studMarkList.get(j).getEx_rslt_bean().getSub_id())){
						
						if(Double.parseDouble(studMarkList.get(j).getEx_rslt_bean().getResult()) >= 50.0){
							if(studMarkList.get(j).getStud_bean().getSex().equals("M")){
								male_counter_cat1++;
							}
							if(studMarkList.get(j).getStud_bean().getSex().equals("F")){
								female_counter_cat1++;
							}
						}
						if(Double.parseDouble(studMarkList.get(j).getEx_rslt_bean().getResult()) >= 75.0){
							if(studMarkList.get(j).getStud_bean().getSex().equals("M")){
								male_counter_cat2++;
							}
							if(studMarkList.get(j).getStud_bean().getSex().equals("F")){
								female_counter_cat2++;
							}
						}
						if(Double.parseDouble(studMarkList.get(j).getEx_rslt_bean().getResult()) >= 90.0){
							if(studMarkList.get(j).getStud_bean().getSex().equals("M")){
								male_counter_cat3++;
							}
							if(studMarkList.get(j).getStud_bean().getSex().equals("F")){
								female_counter_cat3++;
							}
						}
					}
				}
				
				grt_50_male_total_cat1 += male_counter_cat1;
				grt_50_female_total_cat1 += female_counter_cat1;
				grt_50_total_cat1 += male_counter_cat1 + female_counter_cat1;
				
				grt_75_male_total_cat2 += male_counter_cat2;
				grt_75_female_total_cat2 += female_counter_cat2;
				grt_75_total_cat2 += male_counter_cat2 + female_counter_cat2;
				
				grt_90_male_total_cat3 += male_counter_cat3;
				grt_90_female_total_cat3 += female_counter_cat3;
				grt_90_total_cat3 += male_counter_cat3 + female_counter_cat3;
				
				ReportBean rpb = new ReportBean();
				SubjectBean sbb = new SubjectBean();
				StudentBean stb = new StudentBean();
				
				int tm_num = (int)total_male_num;
				int tf_num = (int)total_female_num;
				
				stb.setNo_male(String.valueOf(tm_num));
				stb.setNo_female(String.valueOf(tf_num));
				stb.setTotal_number(String.valueOf(tm_num + tf_num));
				
				rpb.setStud_bean(stb);
								
				sbb.setSub_id(subList.get(i).getSub_bean().getSub_id());
				sbb.setSub_name(subList.get(i).getSub_bean().getSub_name());				
				rpb.setSub_bean(sbb);
				
				rpb.setMale_num_cat1(male_counter_cat1);
				rpb.setFemale_num_cat1(female_counter_cat1);
				rpb.setTotal_num_cat1(male_counter_cat1 + female_counter_cat1);
								
				if(studMarkList.size() > 0){
					String mp_cat1 = fmt.format((male_counter_cat1 * 100)/total_male_num);
					String fp_cat1 = fmt.format((female_counter_cat1 * 100)/total_female_num);
					String tp_cat1 = fmt.format(((male_counter_cat1 + female_counter_cat1) * 100)/(total_male_num + total_female_num));
					
					rpb.setMale_percent_cat1(Double.parseDouble(mp_cat1));
					rpb.setFemale_percent_cat1(Double.parseDouble(fp_cat1));
					rpb.setTotal_percent_cat1(Double.parseDouble(tp_cat1));
				}
				
				rpb.setMale_num_cat2(male_counter_cat2);
				rpb.setFemale_num_cat2(female_counter_cat2);
				rpb.setTotal_num_cat2(male_counter_cat2 + female_counter_cat2);
				
				if(studMarkList.size() > 0){
					String mp_cat2 = fmt.format((male_counter_cat2 * 100)/total_male_num);
					String fp_cat2 = fmt.format((female_counter_cat2 * 100)/total_female_num);
					String tp_cat2 = fmt.format(((male_counter_cat2 + female_counter_cat2) * 100)/(total_male_num + total_female_num));
					
					rpb.setMale_percent_cat2(Double.parseDouble(mp_cat2));
					rpb.setFemale_percent_cat2(Double.parseDouble(fp_cat2));
					rpb.setTotal_percent_cat2(Double.parseDouble(tp_cat2));
				}		
				
				rpb.setMale_num_cat3(male_counter_cat3);
				rpb.setFemale_num_cat3(female_counter_cat3);
				rpb.setTotal_num_cat3(male_counter_cat3 + female_counter_cat3);
				
				if(studMarkList.size() > 0){
					String mp_cat3 = fmt.format((male_counter_cat3 * 100)/total_male_num);
					String fp_cat3 = fmt.format((female_counter_cat3 * 100)/total_female_num);
					String tp_cat3 = fmt.format(((male_counter_cat3 + female_counter_cat3) * 100)/(total_male_num + total_female_num));
					
					rpb.setMale_percent_cat3(Double.parseDouble(mp_cat3));
					rpb.setFemale_percent_cat3(Double.parseDouble(fp_cat3));
					rpb.setTotal_percent_cat3(Double.parseDouble(tp_cat3));
				}
				
				rpb.setMale_grt_50(male_counter_cat1 + male_counter_cat2 + male_counter_cat3);
				rpb.setFemale_grt_50(female_counter_cat1 + female_counter_cat2 + female_counter_cat3);
				rpb.setTotal_grt_50(male_counter_cat1 + male_counter_cat2 + male_counter_cat3 + female_counter_cat1 + female_counter_cat2 + female_counter_cat3);
				
				if(studMarkList.size() > 0){
					String m_grt_50 = fmt.format(((male_counter_cat1 + male_counter_cat2 + male_counter_cat3)* 100)/total_male_num);
					String f_grt_50 = fmt.format(((female_counter_cat1 + female_counter_cat2 + female_counter_cat3)* 100)/total_female_num);
					String t_grt_50 = fmt.format(((male_counter_cat1 + male_counter_cat2 + male_counter_cat3 + female_counter_cat1 + female_counter_cat2 + female_counter_cat3)*100)/(total_male_num + total_female_num));
					
					rpb.setMale_percent_grt_50(Double.parseDouble(m_grt_50));
					rpb.setFemale_percent_grt_50(Double.parseDouble(f_grt_50));
					rpb.setTotal_percent_grt_50(Double.parseDouble(t_grt_50));
				}
				
				rslt.add(rpb);								
			}
			
			ReportBean rpb2 = new ReportBean();
			
			rpb2.setGrt_50_total_male_cat1(grt_50_male_total_cat1);
			rpb2.setGrt_50_total_female_cat1(grt_50_female_total_cat1);
			rpb2.setGrt_50_total_cat1(grt_50_total_cat1);			
			
			rpb2.setGrt_50_total_percent_male_cat1(Double.parseDouble(fmt.format((grt_50_male_total_cat1 * 100)/(total_male_num * subList.size()))));
			rpb2.setGrt_50_total_percent_female_cat1(Double.parseDouble(fmt.format((grt_50_female_total_cat1 * 100)/(total_female_num * subList.size()))));
			rpb2.setGrt_50_total_percent_cat1(Double.parseDouble(fmt.format((grt_50_total_cat1 * 100)/((total_male_num + total_female_num)*subList.size()))));
			
			rpb2.setGrt_75_total_male_cat2(grt_75_male_total_cat2);
			rpb2.setGrt_75_total_female_cat2(grt_75_female_total_cat2);
			rpb2.setGrt_75_total_cat2(grt_75_total_cat2);
			
			rpb2.setGrt_75_total_percent_male_cat2(Double.parseDouble(fmt.format((grt_75_male_total_cat2 * 100)/(total_male_num * subList.size()))));
			rpb2.setGrt_75_total_percent_female_cat2(Double.parseDouble(fmt.format((grt_75_female_total_cat2 * 100)/(total_female_num * subList.size()))));
			rpb2.setGrt_75_total_percent_cat2(Double.parseDouble(fmt.format((grt_75_total_cat2 * 100)/((total_male_num + total_female_num)*subList.size()))));
			
			rpb2.setGrt_90_total_male_cat3(grt_90_male_total_cat3);
			rpb2.setGrt_90_total_female_cat3(grt_90_female_total_cat3);
			rpb2.setGrt_90_total_cat3(grt_90_total_cat3);
			
			rpb2.setGrt_90_total_percent_male_cat3(Double.parseDouble(fmt.format((grt_90_male_total_cat3 * 100)/(total_male_num * subList.size()))));
			rpb2.setGrt_90_total_percent_female_cat3(Double.parseDouble(fmt.format((grt_90_female_total_cat3 * 100)/(total_female_num * subList.size()))));
			rpb2.setGrt_90_total_percent_cat3(Double.parseDouble(fmt.format((grt_90_total_cat3 * 100)/((total_male_num + total_female_num)*subList.size()))));
			
			rslt.add(rpb2);
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	
//	public static List<ReportBean> getTopFiveStudentPerClass(ReportBean rb, String userName){
//		
//		List<ReportBean> rslt = new ArrayList<ReportBean>();
//		
//		Connection con = null;
//		PreparedStatement stud_ps = null;
//		ResultSet stud_rs = null;
//		
//		try{
//		
//			con = Connector.connect();
//			
//			List<StudentClassBean> grade_rslt = new ArrayList<StudentClassBean>();
//			grade_rslt = StudentDAO.getClassList();
//			
//			List<StudentClassDetailBean> class_detail = new ArrayList<StudentClassDetailBean>();
//			
//			for(int i = 0; i < grade_rslt.size(); i++){
//				
//				class_detail = StudentDAO.getClassDetail(grade_rslt.get(i).getClass_id(), userName);
//				
//				for(int j = 0; j < class_detail.size(); j++){
//					
//					stud_ps = con.prepareStatement(ReportQueries.getTopFiveStudentPerClass);
//					stud_ps.setString(1, grade_rslt.get(i).getClass_id());
//					stud_ps.setString(2, class_detail.get(j).getCd_id());
//					stud_ps.setString(3, rb.getAt_id());
//					stud_ps.setString(4, rb.getStud_bean().getAc_year());
//					stud_rs = stud_ps.executeQuery();
//					
//					int rank = 1;
//					
//					while(stud_rs.next()){
//						
//						ReportBean rpt = new ReportBean();
//						ExamResultBean erb = new ExamResultBean();
//						CRoomBean crb = new CRoomBean();
//						
//						erb.setStud_rank(String.valueOf(rank));
//						erb.setFname(stud_rs.getString("fname"));
//						erb.setMname(stud_rs.getString("mname"));
//						erb.setGname(stud_rs.getString("gname"));
//						erb.setTotal_mark(stud_rs.getString("total"));
//						erb.setCl_name(grade_rslt.get(i).getClass_name());
//						erb.setCd_name(stud_rs.getString("cd_name"));
//						
//						crb.setCl_id(grade_rslt.get(i).getClass_id());
//						rb.setClass_bean(crb);						
//						Double avg_mark = Double.parseDouble(stud_rs.getString("total"))/getNoOfSubjectForAverage(rb);
//						
//						erb.setAverage_mark(String.valueOf(avg_mark));
//						
//						rpt.setEx_rslt_bean(erb);						
//						rslt.add(rpt);
//						
//						rank += 1;
//					}					
//				}				
//			}
//		
//		} catch(Exception ex){
//			ex.printStackTrace();
//		} finally {
//			if(con != null){
//				try{
//					con.close();
//				} catch(Exception ex){}
//			}
//		}
//		
//		return rslt;
//	}
	
	public static List<ReportBean> getTopNsStudent(ReportBean rb, String userName){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement stud_ps = null;
		ResultSet stud_rs = null;
		
		String quarters = rb.getSelectedQuarterList().toString();
		String qrtList = quarters.substring(1, quarters.length()-1);
		String[] qrtListArray = quarters.substring(1, quarters.length()-1).split(",");
		String grades = rb.getSelectedGradeList().toString();
		String[] grdList = grades.substring(1, grades.length()-1).split(",");
		
		try{
			
			con = Connector.connect();
			
			List<StudentClassDetailBean> class_detail = new ArrayList<StudentClassDetailBean>();
			
			for(int i = 0; i < grdList.length; i++){
				
				class_detail = StudentDAO.getClassDetail(grdList[i], userName);
				
				for(int j = 0; j < class_detail.size(); j++){
					
					String getTopNsStudentPerClass = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, b.fical_year, c.cl_id, g.cd_id, h.stud_status, g.cd_name, i.CLASS_NAME " +
							"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h, class_list i " +
							"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and i.CL_ID = c.CL_ID and b.si_id = e.si_id and c.add_status = 'Yes' and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id = " + grdList[i] + " and g.cd_id = " + class_detail.get(j).getCd_id() + " and b.at_id in (" + qrtList +") and b.fical_year = " + rb.getStud_bean().getAc_year() + " and h.ACADEMIC_YEAR = " + rb.getStud_bean().getAc_year() + " and h.STUD_STATUS in ('Active', 'Passed') " +
							"GROUP BY b.si_id ORDER BY total desc limit " + rb.getTopOf() ;
					
					stud_ps = con.prepareStatement(getTopNsStudentPerClass);
					stud_rs = stud_ps.executeQuery();
					
					int rank = 1;
					
					while(stud_rs.next()){
						
						ReportBean rpt = new ReportBean();
						ExamResultBean erb = new ExamResultBean();
						CRoomBean crb = new CRoomBean();
						
						erb.setStud_rank(String.valueOf(rank));
						erb.setFname(stud_rs.getString("fname"));
						erb.setMname(stud_rs.getString("mname"));
						erb.setGname(stud_rs.getString("gname"));
						
						double totalMark = Double.parseDouble(stud_rs.getString("total"))/qrtListArray.length;
						
						erb.setTotal_mark(String.valueOf(totalMark));
						erb.setCl_name(stud_rs.getString("class_name"));
						erb.setCd_name(stud_rs.getString("cd_name"));
						
						crb.setCl_id(grdList[i]);
						rb.setClass_bean(crb);						
						Double avg_mark = Double.parseDouble(stud_rs.getString("total"))/getNoOfSubjectForAverage(rb);
						
						erb.setAverage_mark(String.valueOf(avg_mark/qrtListArray.length));
						
						rpt.setEx_rslt_bean(erb);						
						rslt.add(rpt);
						
						rank += 1;
					}					
				}				
			}
		
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	
	public static List<ReportBean> getOverAllTopRankedStudent(ReportBean rb){
		
		String quarters = rb.getSelectedQuarterList().toString();
		String qrtList = quarters.substring(1, quarters.length()-1);
		String[] qrtListArray = quarters.substring(1, quarters.length()-1).split(",");
		String grades = rb.getSelectedGradeList().toString();
		String grdList = grades.substring(1, grades.length()-1);
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps_1 = null;
		ResultSet rs = null;
		int rs_2 = 0;
		
		try{
			
			con = Connector.connect();
			
			String updateCalendarYear = "UPDATE calendar_year SET calendar_year = " + rb.getStud_bean().getAc_year();
			ps_1 = con.prepareStatement(updateCalendarYear);
			rs_2 = ps_1.executeUpdate();
						
//			String getOverAllTopRankedStudent = "SELECT b.si_id, e.fname, e.mname, e.gname, b.at_id, sum(b.result) as total, sum(b.result)/(select count(subcl_id) from subject_class_rel where cl_id = c.cl_id and add_status = 'Yes' ) as average, b.fical_year, c.cl_id, g.cd_id, h.stud_status, g.cd_name, i.CLASS_NAME " +
//					"FROM exam_sub_rel a, exam_result b, subject_class_rel c, subject_list d, stud_information e, clist_cdetail_rel f, class_detail g, stud_registration h, class_list i " +
//					"WHERE a.exsub_id = b.examsub_id and a.subcl_id = c.subcl_id and c.sub_id = d.sub_id and i.CL_ID = c.CL_ID and b.si_id = e.si_id and c.add_status = 'Yes' and c.cl_id = f.cl_id and f.cd_id = g.cd_id and e.si_id = h.si_id and f.clcd_id = h.clcd_id and c.cl_id in (" + grdList +") and b.at_id in (" + qrtList + ") and b.fical_year = " + rb.getStud_bean().getAc_year() + " and h.STUD_STATUS in ('Active', 'Passed') " +
//					"GROUP BY b.si_id ORDER BY total desc LIMIT " + rb.getTopOf();
			
			if(rs_2 > 0){
				
				String getOverAllTopRankedStudent_1 = "SELECT a.si_id, f.FNAME, f.MNAME, f.GNAME, d.CLASS_NAME, e.CD_NAME, format((SUM(a.total)/count(distinct a.at_id)),2) As total, format(AVG(a.total),2) AS average " +
													"FROM v_current_year_students_total_exam_result a, stud_registration b, clist_cdetail_rel c, class_list d, class_detail e, stud_information f "+
													"WHERE b.CLCD_ID = c.CLCD_ID and b.ACADEMIC_YEAR = " + rb.getStud_bean().getAc_year() + " AND b.STUD_STATUS in ('Active' , 'Passed') AND c.CL_ID = d.CL_ID AND c.CD_ID = e.CD_ID " +
													"AND a.si_id = b.si_id AND a.si_id = f.si_id AND a.at_id IN (" + qrtList + ") AND d.CL_ID in (" + grdList + ") " +
													"GROUP BY a.si_id ORDER BY AVG(a.total) DESC LIMIT "  + rb.getTopOf();
				
				ps = con.prepareStatement(getOverAllTopRankedStudent_1);
	
				rs = ps.executeQuery();
				
				int rank = 1;
				
				while(rs.next()){
					
					ReportBean rtp = new ReportBean();
					ExamResultBean erb = new ExamResultBean();
					
					erb.setStud_rank(String.valueOf(rank));
					erb.setFname(rs.getString("fname"));
					erb.setMname(rs.getString("mname"));
					erb.setGname(rs.getString("gname"));
					
					//double totalMark = Double.parseDouble(rs.getString("total"))/qrtListArray.length;
					//double averageMark = Double.parseDouble(rs.getString("average"))/qrtListArray.length;
					
					erb.setTotal_mark(rs.getString("total"));
					erb.setAverage_mark(rs.getString("average"));
					erb.setCl_name(rs.getString("class_name"));
					erb.setCd_name(rs.getString("cd_name"));
					
					rtp.setEx_rslt_bean(erb);
					rslt.add(rtp);
					
					rank++;
				}
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}		
		
		return rslt;
	}
	
	public static List<ReportBean> getTopTwentyRankedStudent(ReportBean rb){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ReportQueries.getTopTwentyRankedStudent);
			ps.setString(1, rb.getAt_id());
			ps.setString(2, rb.getStud_bean().getAc_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ReportBean rtp = new ReportBean();
				ExamResultBean erb = new ExamResultBean();
				
				erb.setFname(rs.getString("fname"));
				erb.setMname(rs.getString("mname"));
				erb.setGname(rs.getString("gname"));
				erb.setTotal_mark(rs.getString("total"));
				erb.setAverage_mark(rs.getString("average"));
				erb.setCl_name(rs.getString("class_name"));
				erb.setCd_name(rs.getString("cd_name"));
				
				rtp.setEx_rslt_bean(erb);
				rslt.add(rtp);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		
		return rslt;
	}
	
	public static List<ReportBean> dailyPaymentCollection(ReportBean rb){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ReportQueries.getDailyPaymentCollection);
			ps.setString(1, rb.getCol_date_from());
			ps.setString(2, rb.getCol_date_to());
			ps.setString(3, DateConvertor.returnConvertedEthiopianYear(rb.getCol_date_from()));
			rs = ps.executeQuery();
			
			while(rs.next()){
				ReportBean rpb = new ReportBean();
				PaymentBean pb = new PaymentBean();
				
				pb.setFm_receipt_no(Integer.parseInt(rs.getString("fm_receipt_no")));
				pb.setFname(rs.getString("fname"));
				pb.setMname(rs.getString("mname"));
				pb.setGname(rs.getString("gname"));
				pb.setClass_name(rs.getString("class_name"));
				pb.setCd_name(rs.getString("cd_name"));
				pb.setPt_name(rs.getString("pt_name"));
				pb.setPayment_date(rs.getString("payment_date"));
				pb.setPay_amount(rs.getString("total_payment"));
								
				rpb.setPayment_bean(pb);
				
				rslt.add(rpb);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	
	public static List<ReportBean> monthlyPaymentCollection(ReportBean rb){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ReportQueries.getMonthlyPaymentCollection);
			ps.setString(1, rb.getCol_month_from());
			ps.setString(2, rb.getCol_month_to());
			ps.setString(3, rb.getPayment_bean().getAc_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ReportBean rpb = new ReportBean();
				PaymentBean pb = new PaymentBean();
				
				pb.setFm_receipt_no(Integer.parseInt(rs.getString("fm_receipt_no")));
				pb.setFname(rs.getString("fname"));
				pb.setMname(rs.getString("mname"));
				pb.setGname(rs.getString("gname"));
				pb.setClass_name(rs.getString("class_name"));
				pb.setCd_name(rs.getString("cd_name"));
				pb.setPt_name(rs.getString("pt_name"));
				pb.setPayment_date(rs.getString("payment_date"));
				pb.setPay_amount(rs.getString("total_payment"));
								
				rpb.setPayment_bean(pb);
				
				rslt.add(rpb);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	
	public static List<ReportBean> getUnpaidStudentList(ReportBean rb){
		
		List<ReportBean> rslt = new ArrayList<ReportBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ReportQueries.getUnpaidStudentList);
			ps.setString(1, rb.getClass_bean().getCl_id());
			ps.setString(2, rb.getCol_month_from());
			ps.setString(3, rb.getCol_month_to());
			ps.setString(4, rb.getPayment_bean().getAc_year());
			ps.setString(5, rb.getPayment_bean().getAc_year());
			ps.setString(6, rb.getClass_bean().getCl_id());
			ps.setString(7, rb.getPayment_bean().getAc_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ReportBean rpb = new ReportBean();
				PaymentBean pb = new PaymentBean();
				
				pb.setFname(rs.getString("fname"));
				pb.setMname(rs.getString("mname"));
				pb.setGname(rs.getString("gname"));
				pb.setClass_name(rs.getString("class_name"));
				pb.setCd_name(rs.getString("cd_name"));
								
				rpb.setPayment_bean(pb);
				
				rslt.add(rpb);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	
	public static String getEmailToList(){
		String rslt = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ReportQueries.getEmailToList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				if(rslt.length() > 0){
					rslt = rslt + "; " + rs.getString("osm_email_address");
				} else {
					rslt = rslt + rs.getString("osm_email_address");
				}
			}
			
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	

}
