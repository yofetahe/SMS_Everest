package examClass.exam_result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import adminClass.certificateDefaultComment.CertDefaultComBean;
import adminClass.certificateDefaultComment.CertDefaultComQueries;
import attendanceClass.AttendanceQueries;
import behHolisticEvaluationClass.BevalHolisticBean;
import behHolisticEvaluationClass.BevalHolisticQueries;
import studentClass.StudentBean;
import studentClass.StudentDAO;
import studentClass.StudentQueries;
import subjectClass.SubjectBean;
import util.AgeCalculator;
import util.ReturnCurrentEthiopianYear;
import util.TodayDate_YYYYMMDD;
import connectionClass.Connector;
import reportClass.ReportBean;
import reportClass.ReportQueries;

public class ExamResultDAO {
	
	
	static List<ExamResultBean> examrslt = new ArrayList<ExamResultBean>();
	
	static List<ExamResultBean> fullYearStudRslt = new ArrayList<ExamResultBean>();
	
	static List<List<ExamResultBean>> studentEachQrtTotalRslt = new ArrayList<List<ExamResultBean>>();
	
	static List<ExamResultBean> teachersQuarterCommentForAllStudentsPerClass = new ArrayList<ExamResultBean>();
	
	static List<CertDefaultComBean> defaultCommentList = new ArrayList<CertDefaultComBean>();
	
	
	
	public static void clearExamrslt(){
		examrslt.clear();
	}
	
	public static String checkRsltMax(ExamResultBean exmrslt){
		String rslt = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.selectMaxRslt);
			ps.setString(1, exmrslt.getExsub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				rslt = rs.getString("TOTAL_MARK");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	///>>> Exam result add methods <<<///
	public static synchronized boolean addStudExamRslt(ExamResultBean exmrslt){
		
		int x = examrslt.size();
		boolean rslt = false;
		
		ExamResultBean exr = new ExamResultBean();
		
		exr.setSi_id(exmrslt.getSi_id());
		exr.setResult(exmrslt.getResult());
		exr.setExsub_id(exmrslt.getExsub_id());
		exr.setAcademic_year(exmrslt.getAcademic_year());
		exr.setAt_id(exmrslt.getAt_id());
		
		examrslt.add(exr);
		
		if(examrslt.size() > x){
			rslt = true;
		} 
		
		return rslt;		
	}
	
	public static synchronized boolean removeStudExamRslt(ExamResultBean erslt){
		
		int x = examrslt.size();
		boolean rslt = false;
		
		for(int i = 0; i < examrslt.size(); i++){
			if(examrslt.get(i).getSi_id().equals(erslt.getSi_id())){
				examrslt.remove(i);
			}
		}
		
		if(examrslt.size() < x){
			rslt = true;
		} 
		
		return rslt;		
	}
	
	public static synchronized boolean saveStudExamRslt(String loggedInUser){
		
		clearDuplicateInsert();
		
		if(examrslt.size() == 0){
			return false;
		}
		
		boolean rslt = false;
		int rwcount = 1;
			
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			con = Connector.connect();
			
			for(int i = 0; i < examrslt.size(); i++){
				
				if(examrslt.get(i).getResult().length() != 0){
					
					ps = con.prepareStatement(ExamResultQueries.insertStudExamRslt);
					ps.setString(1, examrslt.get(i).getSi_id());
					ps.setString(2, examrslt.get(i).getExsub_id());
					ps.setString(3, examrslt.get(i).getResult());
					ps.setInt(4, Integer.parseInt(yr));
					ps.setString(5, examrslt.get(i).getAt_id());
					ps.setString(6, loggedInUser);
					ps.setString(7, TodayDate_YYYYMMDD.getToday());
					rs = ps.executeUpdate();
					
					if(rs > 0){
						rwcount++;
					}				
				}				
			}
							
			rslt = true;
							
			examrslt.clear();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean saveStudExamRsltFromJavaScriptArray(ExamResultBean er, String loggedInUserId){
		
		boolean rslt = false;
		
		List<ExamResultBean> exrslt = new ArrayList<ExamResultBean>();
		
		String markList = er.getStudMarkList().toString();
		
		String[] studMarkList = markList.substring(1, markList.length()-1).split(",");
		
		for(int i = 0; i < studMarkList.length; i += 4){
			
			ExamResultBean exr = new ExamResultBean();
			
			exr.setSi_id(studMarkList[i]);
			exr.setExsub_id(studMarkList[i+1]);
			exr.setResult(studMarkList[i+2]);			
			exr.setAt_id(studMarkList[i+3]);
			
			exrslt.add(exr);
		}
		
		///>>>>><<<<<///
		int rwcount = 1;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		String yr = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear();
		
		try{
			con = Connector.connect();
			
			for(int i = 0; i < exrslt.size(); i++){
				
				if(exrslt.get(i).getResult().length() != 0 && checkRecordExistatnceOnDB(exrslt.get(i))){
					
					ps = con.prepareStatement(ExamResultQueries.insertStudExamRslt);
					ps.setString(1, exrslt.get(i).getSi_id());
					ps.setString(2, exrslt.get(i).getExsub_id());
					ps.setString(3, exrslt.get(i).getResult());
					ps.setInt(4, Integer.parseInt(yr));
					ps.setString(5, exrslt.get(i).getAt_id());
					ps.setString(6, loggedInUserId);
					ps.setString(7, TodayDate_YYYYMMDD.getToday());
					rs = ps.executeUpdate();
					
					if(rs > 0){
						rwcount++;
					}				
				}				
			}
							
			rslt = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		///>>>>><<<<<///
				
		
		return rslt;
	}	
	///>>> Exam result add methods <<<///
	
	
	
	public static boolean saveStudExamRsltFromJavaScriptArray_Batch(ExamResultBean er, String loggedInUserId){
		
		boolean rslt = false;
		
		List<ExamResultBean> exrslt = new ArrayList<ExamResultBean>();
		
		String markList = er.getStudMarkList().toString();
		
		String[] studMarkList = markList.substring(1, markList.length()-1).split(",");
		
		for(int i = 0; i < studMarkList.length; i += 4){
			
			ExamResultBean exr = new ExamResultBean();
			
			exr.setSi_id(studMarkList[i]);
			exr.setExsub_id(studMarkList[i+1]);
			exr.setResult(studMarkList[i+2]);			
			exr.setAt_id(studMarkList[i+3]);
			
			exrslt.add(exr);
		}
		
		///>>>>><<<<<///
		int rwcount = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		String yr = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear();
		
		try{
			con = Connector.connect();
			
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(ExamResultQueries.insertStudExamRslt);
			
			for(int i = 0; i < exrslt.size(); i++){
				
				if(exrslt.get(i).getResult().length() != 0 && checkRecordExistatnceOnDB(exrslt.get(i))){
					
					ps.setString(1, exrslt.get(i).getSi_id());
					ps.setString(2, exrslt.get(i).getExsub_id());
					ps.setString(3, exrslt.get(i).getResult());
					ps.setInt(4, Integer.parseInt(yr));
					ps.setString(5, exrslt.get(i).getAt_id());
					ps.setString(6, loggedInUserId);
					ps.setString(7, TodayDate_YYYYMMDD.getToday());
					ps.addBatch();
					
					rwcount++;
				}				
			}
			
			int[] rslts = ps.executeBatch();
			
			if(rslts.length == rwcount){
				
				con.commit();
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					ps.close();
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		///>>>>><<<<<///
		
		return rslt;
	}	
	///>>> Exam result add methods <<<///
	
	
	public static void addStudExamRsltFromExcel(ExamResultBean exmrslt){
		
		boolean check = true;
		
		//check if there is a duplication on the added row or not
		boolean check2 = checkDuplicateOnAddedExcelRows(exmrslt);
		
		if(!check2){
			//check whether the record exist on the database or not
			check = checkExcelRecordExistatnceOnDB(exmrslt);
		}
		
		if(!check){
			
			ExamResultBean exr = new ExamResultBean();
			
			exr.setSi_id(exmrslt.getSi_id());
			exr.setResult(exmrslt.getResult());
			exr.setExsub_id(exmrslt.getExsub_id());
			exr.setAcademic_year(exmrslt.getAcademic_year());
			exr.setAt_id(exmrslt.getAt_id());
			
			examrslt.add(exr);			 
		}		
	}
	
	public static void addStudExamRsltForUpdateFromExcel(ExamResultBean exmrslt){
		
		//check if there is a duplication on the added row or not
		boolean check = checkDuplicateOnAddedExcelRows(exmrslt);
		
		if(!check){
			
			ExamResultBean exr = new ExamResultBean();
			
			exr.setSi_id(exmrslt.getSi_id());
			exr.setResult(exmrslt.getResult());
			exr.setExsub_id(exmrslt.getExsub_id());
			exr.setAcademic_year(exmrslt.getAcademic_year());
			exr.setAt_id(exmrslt.getAt_id());
			
			examrslt.add(exr);			 
		}		
	}
	
	//*** assumption the excel holds one year record only
	public static boolean checkDuplicateOnAddedExcelRows(ExamResultBean exrslt){
		
		boolean rslt = false;
		
		for(int i = 0; i < examrslt.size() - 1; i++){
			if(examrslt.get(i).getSi_id().equals(exrslt.getSi_id()) && 
					examrslt.get(i).getExsub_id().equals(exrslt.getExsub_id()) && 
					examrslt.get(i).getAt_id().equals(exrslt.getAt_id())){
				rslt = true;
			}
		}
		
		return rslt;
	}
	
	public static void clearDuplicateInsert(){
		
		for(int i = 0; i < examrslt.size() - 1; i++){
			for(int j = i + 1; j < examrslt.size(); j++){
				
				if(examrslt.get(i).getSi_id().equals(examrslt.get(j).getSi_id())){
					examrslt.remove(j);
				}
				
			}
		}
	}
	
	public static boolean saveStudExamRsltFromExcel(String loggedInUser){
		
		if(examrslt.size() == 0){
			return false;
		}
		
		clearDuplicateExcelRows();
		
		boolean rslt = false;
		int rwcount = 1;
			
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		//String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			con = Connector.connect();
			
			for(int i = 0; i < examrslt.size(); i++){
				
				if(examrslt.get(i).getResult().length() != 0){
					
					ps = con.prepareStatement(ExamResultQueries.insertStudExamRslt);
					ps.setString(1, examrslt.get(i).getSi_id());
					ps.setString(2, examrslt.get(i).getExsub_id());
					ps.setString(3, examrslt.get(i).getResult());
					ps.setString(4, examrslt.get(i).getAcademic_year());
					ps.setString(5, examrslt.get(i).getAt_id());
					ps.setString(6, loggedInUser);
					ps.setString(7, TodayDate_YYYYMMDD.getToday());
					rs = ps.executeUpdate();
					
					if(rs > 0){
						rwcount++;
					}				
				}				
			}
			
			if(rwcount > 0){
				rslt = true;
			}
							
			examrslt.clear();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean updateStudExamRsltFromExcel(String loggedInUser){
		
		if(examrslt.size() == 0){
			return false;
		}
		
		clearDuplicateExcelRows();
		
		boolean rslt = false;
		int rwcount = 1;
			
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			con = Connector.connect();
			
			for(int i = 0; i < examrslt.size(); i++){
				
				if(examrslt.get(i).getResult().length() != 0){
					
					ps = con.prepareStatement(ExamResultQueries.updateStudExamRslt);
					
					ps.setString(1, examrslt.get(i).getResult());
					ps.setString(2, loggedInUser);
					ps.setString(3, TodayDate_YYYYMMDD.getToday());
					
					ps.setString(4, examrslt.get(i).getSi_id());
					ps.setString(5, examrslt.get(i).getExsub_id());					
					ps.setInt(6, Integer.parseInt(yr));
					ps.setString(7, examrslt.get(i).getAt_id());
					
					rs = ps.executeUpdate();
					
					if(rs > 0){
						rwcount++;
					}				
				}				
			}
			
			if(rwcount > 0){
				rslt = true;
			}
							
			examrslt.clear();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}	
	
	public static void clearDuplicateExcelRows(){
		
		for(int i = 0; i < examrslt.size() - 1; i++){
			for(int j = i + 1; j < examrslt.size(); j++){
				
				if(examrslt.get(i).getSi_id().equals(examrslt.get(j).getSi_id()) && examrslt.get(i).getExsub_id().equals(examrslt.get(j).getExsub_id())){
					examrslt.remove(j);
				}
				
			}
		}
	}
	
	public static boolean checkRecordExistatnceOnDB(ExamResultBean erslt){
		
		boolean rslt = true;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear();
		
		try{
			con = Connector.connect();
				
			ps = con.prepareStatement(ExamResultQueries.checkDuplicatedStudExamRslt);
			ps.setString(1, erslt.getSi_id());
			ps.setString(2, erslt.getExsub_id());
			ps.setString(3, erslt.getAt_id());
			ps.setInt(4, Integer.parseInt(yr));			
			rs = ps.executeQuery();
			
			while(rs.next()){
				rslt = false;
			}				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean checkExcelRecordExistatnceOnDB(ExamResultBean erslt){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			con = Connector.connect();
				
			ps = con.prepareStatement(ExamResultQueries.checkDuplicatedStudExamRslt);
			ps.setString(1, erslt.getSi_id());
			ps.setString(2, erslt.getExsub_id());
			ps.setString(3, erslt.getAt_id());
			ps.setInt(4, Integer.parseInt(yr));			
			rs = ps.executeQuery();
			
			while(rs.next()){
				rslt = true;
			}				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	/////**** not used at the moment(it is corrected/enhanced by the below code)
	public static List<ExamResultBean> getTotalSumStudRslt(List<StudentBean> std, String cl_id, String ac_year, String at_id){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet r = null;
		
		double numOfSub = 0.0;
		
		try{
			con = Connector.connect();
			
			ps = con.prepareStatement(ExamResultQueries.numOfSubPerGrade);
			ps.setString(1, cl_id);
			r = ps.executeQuery();
			
			while(r.next()){
				numOfSub = Double.parseDouble(r.getString("total"));
			}
			
			for(int i = 0; i < std.size(); i++){
				
				ps = con.prepareStatement(ExamResultQueries.selectStudTotalExamRslt);
				ps.setString(1, std.get(i).getSi_id());
				ps.setString(2, ac_year);
				ps.setString(3, at_id);
				rs = ps.executeQuery();
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSi_id(std.get(i).getSi_id());
				ex.setFname(std.get(i).getFname());
				ex.setMname(std.get(i).getMname());
				ex.setGname(std.get(i).getGname());
				
				while(rs.next()){
										
					if(rs.getString("total_mark") == null){
						ex.setTotal_mark("0.0");
						ex.setAverage_mark("0.0");
					} else {
						ex.setTotal_mark(rs.getString("total_mark"));
						double avg = Double.parseDouble(rs.getString("total_mark"))/numOfSub;						
						ex.setAverage_mark(String.valueOf(avg));
					}
					//ex.setStudRslt_status(rs.getString("stud_status"));
					
				}
				
				rslt.add(ex);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	/////**** not used at the moment(it is corrected/enhanced by the below code)
	public static List<ExamResultBean> getStudFullYearRslt(List<StudentBean> std, String cl_id, String ac_year){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs3 = null;		
		
		double numOfSub = 0.0;
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(ExamResultQueries.numOfSubPerGrade);
			ps.setString(1, cl_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				numOfSub = Double.parseDouble(rs.getString("total"));
			}
			
			for(int i = 0; i < std.size(); i++){
				ps3 = con.prepareStatement(ExamResultQueries.getStudFullYearResult);
				ps3.setString(1, ac_year);
				ps3.setString(2, ac_year);
				ps3.setString(3, std.get(i).getSi_id());				
				rs3 = ps3.executeQuery();
				
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(std.get(i).getSi_id());
				exb.setFname(std.get(i).getFname());
				exb.setMname(std.get(i).getMname());
				exb.setGname(std.get(i).getGname());
				exb.setStudRslt_status(std.get(i).getRslt_status());
				
				while(rs3.next()){
					double firstavg = 0.0;
					double secondavg = 0.0;
					
					String fterm = rs3.getString("first_term");
					String sterm = rs3.getString("second_term");					
					
					if(rs3.getString("first_term") != null){
						exb.setFirstSemisterTotal(fterm);
						firstavg = Double.parseDouble(fterm)/numOfSub;
						exb.setFirstSemisterAvg(String.valueOf(firstavg));
					} else {
						exb.setFirstSemisterTotal("0.0");
						exb.setFirstSemisterAvg("0.0");
					}
					
					if(rs3.getString("second_term") != null){
						exb.setSecondSemisterTotal(sterm);
						secondavg = Double.parseDouble(sterm)/numOfSub;
						exb.setSecondSemisterAvg(String.valueOf(secondavg));
					} else {
						exb.setSecondSemisterTotal("0.0");
						exb.setSecondSemisterAvg("0.0");
					}
					
					double totalavg = (firstavg + secondavg)/2;
					exb.setFullYearAvg(String.valueOf(totalavg));
					
				}
				
				rslt.add(exb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	/*
	 * not used at the moment( it is enhanced by the next method using query from view) 
	 * remove connecting to the database for each student(for loop)
	 */
	public static List<ExamResultBean> getStudEachQuarterTotalRslt(List<StudentBean> std, String cl_id, String ac_year){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs3 = null;
		
		double numOfSub = 0.0;
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(ExamResultQueries.numOfSubPerGrade);
			ps.setString(1, cl_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				numOfSub = Double.parseDouble(rs.getString("total"));
			}
			
			for(int i = 0; i < std.size(); i++){
				
				ps3 = con.prepareStatement(ExamResultQueries.getStudentQuarterTotalResult);
				ps3.setString(1, std.get(i).getSi_id());
				ps3.setString(2, ac_year);	
				rs3 = ps3.executeQuery();
				
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(std.get(i).getSi_id());
				exb.setFname(std.get(i).getFname());
				exb.setMname(std.get(i).getMname());
				exb.setGname(std.get(i).getGname());
				exb.setStudRslt_status(std.get(i).getRslt_status());
				
				while(rs3.next()){
					
					String qrt_one = rs3.getString("qrt_one")!= null?rs3.getString("qrt_one"):"0.0";
					String qrt_two = rs3.getString("qrt_two")!= null?rs3.getString("qrt_two"):"0.0";
					String qrt_three = rs3.getString("qrt_three")!= null?rs3.getString("qrt_three"):"0.0";
					String qrt_four = rs3.getString("qrt_four")!= null?rs3.getString("qrt_four"):"0.0";
					
					Double yr_avg = ((Double.parseDouble(qrt_one) + Double.parseDouble(qrt_two) + Double.parseDouble(qrt_three) + Double.parseDouble(qrt_four))/numOfSub)/4;
					
					exb.setQrt_one_total(qrt_one);
					exb.setQrt_two_total(qrt_two);
					exb.setQrt_three_total(qrt_three);
					exb.setQrt_four_total(qrt_four);
					
					exb.setAverage_quarter_mark(String.valueOf(yr_avg));	
				}
				
				if(exb.getQrt_one_total() == null){
					exb.setQrt_one_total("0.0");
				}
				if(exb.getQrt_two_total() == null){
					exb.setQrt_two_total("0.0");
				}
				if(exb.getQrt_three_total() == null){
					exb.setQrt_three_total("0.0");
				}
				if(exb.getQrt_four_total() == null){
					exb.setQrt_four_total("0.0");
				}
				if(exb.getAverage_quarter_mark() == null){
					exb.setAverage_quarter_mark("0.0");
				}
				
				rslt.add(exb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getStudentTotalRsltPerEachQuarter(ExamResultBean exrslt){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		List<ExamResultBean> organizaedrslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		
		int numOfSub = 1;
		
		try{
			
			con = Connector.connect();
			
			///*** getting the number of subject ***///
			ps1 = con.prepareStatement(ExamResultQueries.numOfSubPerGrade);
			ps1.setString(1, exrslt.getCl_id());
			rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				numOfSub = Integer.parseInt(rs1.getString("total"));
			}
			
			///*** getting the total mark of student ***///
			ps = con.prepareStatement(ExamResultQueries.getStudentTotalRsltPerEachQuarter);
			ps.setString(1, exrslt.getCl_id());
			ps.setString(2, exrslt.getCd_id());
			ps.setString(3, exrslt.getCl_id());
			ps.setString(4, exrslt.getAcademic_year());			
			
			ps.setString(5, exrslt.getCl_id());
			ps.setString(6, exrslt.getCd_id());
			ps.setString(7, exrslt.getAcademic_year());
			ps.setString(8, exrslt.getAcademic_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(rs.getString("si_id"));
				exb.setFname(rs.getString("fname"));
				exb.setMname(rs.getString("mname"));
				exb.setGname(rs.getString("gname"));
				exb.setStudRslt_status(rs.getString("stud_status"));
				exb.setAt_id(rs.getString("at_id"));
				exb.setQuarter_total((rs.getString("qrt_total")+"").equals("null")?"0.0":rs.getString("qrt_total"));
				
				rslt.add(exb);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(con != null){
				try{
					con.close();
				} catch(Exception e){}
			}
		}
		
		
		///*** organizing the students quarter mark ***///
		List<ExamResultBean> studlist = new ArrayList<ExamResultBean>();
		
		String prev_si_id = "";
		
		for(int j = 0; j < rslt.size(); j++){
			
			int counter = 0;
			
			if(!"".equals(prev_si_id) && rslt.get(j).getSi_id().equals(prev_si_id)){
					
				counter++;				
			}
			
			if(counter == 0){
				
				prev_si_id = rslt.get(j).getSi_id();
				
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(rslt.get(j).getSi_id());
				exb.setFname(rslt.get(j).getFname());
				exb.setMname(rslt.get(j).getMname());
				exb.setGname(rslt.get(j).getGname());
				exb.setStudRslt_status(rslt.get(j).getStudRslt_status());
				
				studlist.add(exb);
			}
		}
				
		for(int i = 0; i < studlist.size(); i++){
			
			ExamResultBean exb = new ExamResultBean();
			
			exb.setSi_id(studlist.get(i).getSi_id());
			exb.setFname(studlist.get(i).getFname());
			exb.setMname(studlist.get(i).getMname());
			exb.setGname(studlist.get(i).getGname());
			exb.setStudRslt_status(studlist.get(i).getStudRslt_status());
			
			int sem_num = 0;
			
			for(int j = 0; j < rslt.size(); j++){
				
				if(rslt.get(j).getSi_id().equals(studlist.get(i).getSi_id())){
					
					if(rslt.get(j).getAt_id().equals("1")){
						
						exb.setQrt_one_total(rslt.get(j).getQuarter_total());
						
						if(!exb.getQrt_one_total().equals("0.0")){
							sem_num++;
						}
					}
					if(rslt.get(j).getAt_id().equals("2")){
						
						exb.setQrt_two_total(rslt.get(j).getQuarter_total());
						
						if(!exb.getQrt_two_total().equals("0.0")){
							sem_num++;
						}
					}
					if(rslt.get(j).getAt_id().equals("3")){
						
						exb.setQrt_three_total(rslt.get(j).getQuarter_total());
						
						if(!exb.getQrt_three_total().equals("0.0")){
							sem_num++;
						}
					}
					if(rslt.get(j).getAt_id().equals("4")){
						
						exb.setQrt_four_total(rslt.get(j).getQuarter_total());
						
						if(!exb.getQrt_four_total().equals("0.0")){
							sem_num++;
						}
					}
				}
			}
			
			//>> in case if it returns 0
			if(sem_num == 0){
				sem_num = 1;
			}
			
			double qrtOneTotal = exb.getQrt_one_total() != null?Double.parseDouble(exb.getQrt_one_total()):0.0;
			double qrtTwoTotal = exb.getQrt_two_total() != null?Double.parseDouble(exb.getQrt_two_total()):0.0;
			double qrtThreeTotal = exb.getQrt_three_total() != null?Double.parseDouble(exb.getQrt_three_total()):0.0;
			double qrtFourTotal = exb.getQrt_four_total() != null?Double.parseDouble(exb.getQrt_four_total()):0.0;
			
			double allQrtSum = qrtOneTotal + qrtTwoTotal + qrtThreeTotal + qrtFourTotal;
			
			exb.setTotal_mark(String.valueOf(allQrtSum));
			
			Double yr_avg = (allQrtSum/numOfSub)/sem_num;
			exb.setAverage_quarter_mark(String.valueOf(yr_avg));
			
			organizaedrslt.add(exb);
		}
				
		return organizaedrslt;
	}
	
	/* 
	 * Previously used and currently replaced with
	 * getStudentTotalRsltPerEachQuarter() method
	 */
	public static List<ExamResultBean> getEachQuarterStudTotalRslt(ExamResultBean exrslt){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
						
		double numOfSub = 0.0;
		
		try{
			con = Connector.connect();
			
			ps = con.prepareStatement(ExamResultQueries.numOfSubPerGrade);
			ps.setString(1, exrslt.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				numOfSub = Double.parseDouble(rs.getString("total"));
			}
			
			List<StudentBean> studList = StudentDAO.getListPerGradeDetail(exrslt.getCl_id(), exrslt.getCd_id(), exrslt.getAcademic_year());
			
			for(int j = 0; j < studList.size(); j++){
				
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(studList.get(j).getSi_id());
				exb.setFname(studList.get(j).getFname());
				exb.setMname(studList.get(j).getMname());
				exb.setGname(studList.get(j).getGname());
				exb.setStudRslt_status(studList.get(j).getRslt_status());
				
				for(int i = 1; i < 5; i++){
					
					PreparedStatement ps2 = null;
					ResultSet rs2 = null;
					
					ps2 = con.prepareStatement(ExamResultQueries.getEachQuarterTotalRsltPerStudent);
					ps2.setString(1, exrslt.getCl_id());
					ps2.setString(2, exrslt.getCd_id());
					ps2.setString(3, String.valueOf(i));
					ps2.setString(4, exrslt.getAcademic_year());
					ps2.setString(5, exrslt.getAcademic_year());
					ps2.setString(6, studList.get(j).getSi_id());
					rs2 = ps2.executeQuery();
					
					while(rs2.next()){
							
						if(i == 1){
							
							exb.setQrt_one_total(rs2.getString("total"));
						}
						if(i == 2){
							
							exb.setQrt_two_total(rs2.getString("total"));
						}
						if(i == 3){
							
							exb.setQrt_three_total(rs2.getString("total"));
						}
						if(i == 4){
							
							exb.setQrt_four_total(rs2.getString("total"));
						}
					}					
				}
				
				String qrt_one = exb.getQrt_one_total()!= null?exb.getQrt_one_total():"0.0";
				String qrt_two = exb.getQrt_two_total()!= null?exb.getQrt_two_total():"0.0";
				String qrt_three = exb.getQrt_three_total()!= null?exb.getQrt_three_total():"0.0";
				String qrt_four = exb.getQrt_four_total()!= null?exb.getQrt_four_total():"0.0";
				
				int sem_num = 0;
				
				if(!qrt_one.equals("0.0")){
					sem_num += 1;
				}
				if(!qrt_two.equals("0.0")){
					sem_num += 1;
				}
				if(!qrt_three.equals("0.0")){
					sem_num += 1;
				}
				if(!qrt_four.equals("0.0")){
					sem_num += 1;
				}
				
				Double yr_avg = ((Double.parseDouble(qrt_one) + Double.parseDouble(qrt_two) + Double.parseDouble(qrt_three) + Double.parseDouble(qrt_four))/numOfSub)/sem_num;
				exb.setAverage_quarter_mark(String.valueOf(yr_avg));
				
				if(exb.getQrt_one_total() == null){
					exb.setQrt_one_total("0.0");
				}
				if(exb.getQrt_two_total() == null){
					exb.setQrt_two_total("0.0");
				}
				if(exb.getQrt_three_total() == null){
					exb.setQrt_three_total("0.0");
				}
				if(exb.getQrt_four_total() == null){
					exb.setQrt_four_total("0.0");
				}
				if(exb.getAverage_quarter_mark() == null){
					exb.setAverage_quarter_mark("0.0");
				}
				
				rslt.add(exb);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getQuarterTotalSumStudRslt(ExamResultBean exrslt){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
				
		double numOfSub = 0.0;
		
		try{
			con = Connector.connect();
			
			ps = con.prepareStatement(ExamResultQueries.numOfSubPerGrade);
			ps.setString(1, exrslt.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				numOfSub = Double.parseDouble(rs.getString("total"));
			}
			
			if(exrslt.getAcademic_year().equals(ReturnCurrentEthiopianYear.getCurrentEthiopianYear())){
				
				ps1 = con.prepareStatement(ExamResultQueries.getEachQuarterStudAllTotalRsltCurrentYear);
				
			} else {
				
				ps1 = con.prepareStatement(ExamResultQueries.getEachQuarterStudAllTotalRslt);
			}
						
			ps1.setString(1, exrslt.getCl_id());
			ps1.setString(2, exrslt.getCd_id());
			ps1.setString(3, exrslt.getAt_id());
			ps1.setString(4, exrslt.getAcademic_year());
			ps1.setString(5, exrslt.getAcademic_year());
			rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(rs1.getString("si_id"));
				exb.setFname(rs1.getString("fname"));
				exb.setMname(rs1.getString("mname"));
				exb.setGname(rs1.getString("gname"));
				exb.setTotal_mark(rs1.getString("total"));				
				double avg = Double.parseDouble(rs1.getString("total"))/numOfSub;				
				exb.setAverage_mark(String.valueOf(avg));
				
				rslt.add(exb);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getQuarterTotalSumBelowAverageStudRslt(ExamResultBean exrslt){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
				
		double numOfSub = 0.0;
		
		try{
			con = Connector.connect();
			
			ps = con.prepareStatement(ExamResultQueries.numOfSubPerGrade);
			ps.setString(1, exrslt.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				numOfSub = Double.parseDouble(rs.getString("total"));
			}
			
			if(exrslt.getAcademic_year().equals(ReturnCurrentEthiopianYear.getCurrentEthiopianYear())){
				
				ps1 = con.prepareStatement(ExamResultQueries.getEachQuarterStudAllTotalRsltCurrentYear);
				
			} else {
				
				ps1 = con.prepareStatement(ExamResultQueries.getEachQuarterStudAllTotalRslt);
			}
			
			ps1.setString(1, exrslt.getCl_id());
			ps1.setString(2, exrslt.getCd_id());
			ps1.setString(3, exrslt.getAt_id());
			ps1.setString(4, exrslt.getAcademic_year());
			ps1.setString(5, exrslt.getAcademic_year());
			rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				
				double avg = Double.parseDouble(rs1.getString("total"))/numOfSub;
				
				if(avg <= Double.parseDouble(exrslt.getAverage_mark())){
					
					ExamResultBean exb = new ExamResultBean();
					
					exb.setSi_id(rs1.getString("si_id"));
					exb.setFname(rs1.getString("fname"));
					exb.setMname(rs1.getString("mname"));
					exb.setGname(rs1.getString("gname"));
					exb.setTotal_mark(rs1.getString("total"));				
									
					exb.setAverage_mark(String.valueOf(avg));
					
					rslt.add(exb);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getCertificateRsltView(ExamResultBean exb, List<SubjectBean> sub){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		double fSemSum = 0.0;
		double sSemSum = 0.0;
		double avgSum = 0.0;
		
		try{
			
			con = Connector.connect();
			for(int i = 0; i < sub.size(); i++){
				
				ps = con.prepareStatement(ExamResultQueries.getCertRsltView);
				ps.setString(1, exb.getAcademic_year());
				ps.setString(2, sub.get(i).getSub_id());
				ps.setString(3, exb.getCl_id());
				ps.setString(4, exb.getSi_id());
				ps.setString(5, exb.getAcademic_year());
				ps.setString(6, sub.get(i).getSub_id());
				ps.setString(7, exb.getCl_id());
				ps.setString(8, exb.getSi_id());				
				rs = ps.executeQuery();
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSub_id(sub.get(i).getSub_id());
				ex.setSub_name(sub.get(i).getSub_name());
				
				while(rs.next()){
					double fsemMark = 0.0;
					double ssemMark = 0.0;
					double subavg = 0.0;
					
					if(rs.getString("fsem_subtotal") != null){
						fsemMark = Double.parseDouble(rs.getString("fsem_subtotal"));						
						ex.setFirstSemSubjectTotal(rs.getString("fsem_subtotal"));
					} else {
						ex.setFirstSemSubjectTotal("0.0");
					}
					
					if(rs.getString("ssem_subtotal") != null){
						ssemMark = Double.parseDouble(rs.getString("ssem_subtotal"));
						ex.setSecondSemSubjectTotal(rs.getString("ssem_subtotal"));
					} else {
						ex.setSecondSemSubjectTotal("0.0");
					}
					
					subavg = (fsemMark + ssemMark)/2;
					ex.setSubjectFullYearAvg(String.valueOf(subavg));
					
					fSemSum = fSemSum + fsemMark;
					ex.setFirstSemisterTotal(String.valueOf(fSemSum));
					
					sSemSum = sSemSum + ssemMark;
					ex.setSecondSemisterTotal(String.valueOf(sSemSum));
					
					avgSum = avgSum + subavg;
					ex.setAverageMarkTotal(String.valueOf(avgSum));
				}
				
				rslt.add(ex);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean saveStudentRsltFinalDescion(ExamResultBean sb, String loggedUser){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.saveStudentRsltFinalDecision);
			ps.setString(1, sb.getStudRslt_status());
			ps.setString(2, loggedUser);
			ps.setString(3, TodayDate_YYYYMMDD.getToday());
			ps.setString(4, sb.getSi_id());	
			ps.setString(5, sb.getAcademic_year());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getSortedTotalStudMark(List<ExamResultBean> exrslt){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		int[] rank = new int[exrslt.size()];
		int ind = 0;
		int lrg = Integer.parseInt(exrslt.get(ind).getTotal_mark());
		
		for(int j = 0; j < exrslt.size(); j++){
			
			int lrg_index = 0;
			
			for(int i = 0; i < exrslt.size(); i++){
				if(lrg < Integer.parseInt(exrslt.get(i).getTotal_mark())){
					lrg = Integer.parseInt(exrslt.get(i).getTotal_mark());
					lrg_index = i;
				}			
			}
			rank[ind] = lrg_index;
			ind++;			
			lrg = Integer.parseInt(exrslt.get(ind).getTotal_mark());			
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getSubjectList(ExamResultBean exrslt){
		List<ExamResultBean> sub = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getSubListPerClass);
			ps.setString(1, exrslt.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean sb = new ExamResultBean();
				
				sb.setSub_id(rs.getString("SUB_ID"));
				sb.setSub_name(rs.getString("SUB_NAME"));
				sb.setSubcl_id(rs.getString("subcl_id"));
				sb.setSub_included_in_total_mark(rs.getString("add_status"));
				sb.setConvert_to_grade(rs.getString("convert_to_grade"));
				
				sub.add(sb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return sub;
	}
	
	public static List<ExamResultBean> getSubjectTotalMarkList(ExamResultBean exrslt, List<ExamResultBean> subList){
		
		List<ExamResultBean> sub = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();			
			
			for(int i = 0; i < subList.size(); i++){
				
				ps = con.prepareStatement(ExamResultQueries.getSubjectTotal);
				ps.setString(1, subList.get(i).getSub_id());
				ps.setString(2, exrslt.getCl_id());
				ps.setString(3, exrslt.getSi_id());
				ps.setString(4, exrslt.getAcademic_year());
				ps.setString(5, exrslt.getAt_id());
				rs = ps.executeQuery();
				
				ExamResultBean sb = new ExamResultBean();
				
				sb.setSub_id(subList.get(i).getSub_id());
				sb.setSub_name(subList.get(i).getSub_name());
				
				while(rs.next()){					
					
					sb.setSubjectTotal_mark(rs.getString("sub_total"));
					sb.setGrandTotal_mark(rs.getString("total"));
					sb.setPass_mark(rs.getString("pass_total"));
					
					if(rs.getString("sub_total") != null && rs.getString("pass_total") != null){
						if(Double.parseDouble(rs.getString("sub_total")) >= Double.parseDouble(rs.getString("pass_total"))){
							sb.setMark_status("Passed");
						} else {
							sb.setMark_status("Failed");
						}
					}
					
					sub.add(sb);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return sub;
	}
	
	public static List<ExamResultBean> getSubjectTotalMarkListWithStudName(ExamResultBean exrslt, List<ExamResultBean> subList){
		
		List<ExamResultBean> sub = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PreparedStatement ps_1 = null;
		ResultSet rs_1 = null;
		
		try{
			
			con = Connector.connect();
			
//			ps_1 = con.prepareStatement(ExamResultQueries.getAllStudentsQuarterTotalResult);
//			ps_1.setString(1, exrslt.getCl_id());
//			ps_1.setString(2, exrslt.getCd_id());
//			ps_1.setString(3, exrslt.getAt_id());
//			ps_1.setString(4, exrslt.getAcademic_year());
//			rs_1 = ps_1.executeQuery();
//			
			int rank = 0, x = 1;
//			
//			while(rs_1.next()){
//				
//				if(rs_1.getString("si_id").equals(exrslt.getSi_id())){
//					
//					rank = x;
//				}
//				
//				x++;
//			}
			
			double qrt_total = 0.0;
			int added_subject_count = 0;
			
			for(int i = 0; i < subList.size(); i++){
				
				ps = con.prepareStatement(ExamResultQueries.getSubjectTotalWithStudName);
				ps.setString(1, subList.get(i).getSub_id());
				ps.setString(2, exrslt.getCl_id());
				ps.setString(3, exrslt.getSi_id());
				ps.setString(4, exrslt.getAcademic_year());
				ps.setString(5, exrslt.getAt_id());
				rs = ps.executeQuery();
				
				ExamResultBean sb = new ExamResultBean();
				
				sb.setSub_id(subList.get(i).getSub_id());
				sb.setSub_name(subList.get(i).getSub_name());
				
				while(rs.next()){
					
					sb.setSi_id(exrslt.getSi_id());
					sb.setFname(rs.getString("fname"));
					sb.setMname(rs.getString("mname"));
					sb.setGname(rs.getString("gname"));
					sb.setConvert_to_grade(subList.get(i).getConvert_to_grade());
					sb.setSub_included_in_total_mark(subList.get(i).getSub_included_in_total_mark());
					sb.setStud_rank(String.valueOf(rank));
					
					StudentBean stud = new StudentBean();
					stud.setSex(rs.getString("sex"));
					stud.setDob(rs.getString("dob"));
					stud.setAge(AgeCalculator.calculateAge(stud.getDob()));
					
					sb.setStud(stud);
					if(exrslt.getAt_id().equals("1")){
						sb.setAt_id("I");
					} else if(exrslt.getAt_id().equals("2")){
						sb.setAt_id("II");
					} else if(exrslt.getAt_id().equals("3")){
						sb.setAt_id("III");
					} else if(exrslt.getAt_id().equals("4")){
						sb.setAt_id("IV");
					}
					
					if(subList.get(i).getConvert_to_grade().equals("YES")){
						if(qrt_total > 0.0){
							sb.setSubjectTotal_mark("A");
						}
					} else {
						sb.setSubjectTotal_mark(rs.getString("sub_total"));
					}
					
					if(subList.get(i).getSub_included_in_total_mark().equals("Yes")){
						qrt_total = qrt_total + Double.parseDouble(sb.getSubjectTotal_mark() == null?"0.0":sb.getSubjectTotal_mark());
						added_subject_count++;
					}
					
					sub.add(sb);
				}
			}
			
			ExamResultBean sb1 = new ExamResultBean();
			sb1.setQuarter_grand_total(String.valueOf(qrt_total));
			sb1.setAverageMarkTotal(String.format("%,.2f", Double.parseDouble(sb1.getQuarter_grand_total() == null?"0.0":sb1.getQuarter_grand_total())/added_subject_count));
			
			sub.add(sb1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return sub;
	}
	
	public static List<ExamResultBean> getSubjectTotalMarkListWithAllStudName(ExamResultBean exrslt, List<SubjectBean> subList){
		
		List<ExamResultBean> sub = new ArrayList<ExamResultBean>();
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			
			int rank = 0;
			
			double qrt_total = 0.0;
			int added_subject_count = 0;
				
			ps = con.prepareStatement(ExamResultQueries.getAllSubjectTotalWithAllStudName);
			ps.setString(1, exrslt.getCl_id());
			ps.setString(2, exrslt.getCd_id());
			ps.setString(3, exrslt.getCl_id());
			ps.setString(4, exrslt.getAcademic_year());
			
			ps.setString(5, exrslt.getCl_id());
			ps.setString(6, exrslt.getCd_id());
			ps.setString(7, exrslt.getAcademic_year());
			ps.setString(8, exrslt.getAcademic_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean sb = new ExamResultBean();
				
				sb.setSi_id(rs.getString("si_id"));
				sb.setFname(rs.getString("fname"));
				sb.setMname(rs.getString("mname"));
				sb.setGname(rs.getString("gname"));
				sb.setConvert_to_grade(rs.getString("CONVERT_TO_GRADE"));
				sb.setSub_included_in_total_mark(rs.getString("add_status"));
				sb.setStud_rank(String.valueOf(rank));
				
				sb.setSub_id(rs.getString("sub_id"));
				sb.setSub_name(rs.getString("sub_name"));
				
				StudentBean stud = new StudentBean();
				stud.setSex(rs.getString("sex"));
				stud.setDob(rs.getString("dob"));				
				stud.setAge(AgeCalculator.calculateAge(stud.getDob()));					
				sb.setStud(stud);
				
				if(rs.getString("at_id").equals("1")){
					sb.setAt_id("I");
				} else if(rs.getString("at_id").equals("2")){
					sb.setAt_id("II");
				} else if(rs.getString("at_id").equals("3")){
					sb.setAt_id("III");
				} else if(rs.getString("at_id").equals("4")){
					sb.setAt_id("IV");
				}
				
				if(rs.getString("CONVERT_TO_GRADE").equals("YES")){
					//if(qrt_total > 0.0){
						sb.setSubjectTotal_mark("A");
					//}
				} else {
					sb.setSubjectTotal_mark(rs.getString("sub_total")==""?"0":rs.getString("sub_total"));
				}
				
//				if(sb.getSub_included_in_total_mark().equals("Yes")){
//					
//					qrt_total = qrt_total + Double.parseDouble(sb.getSubjectTotal_mark() == null?"0.0":sb.getSubjectTotal_mark());
//					
//					added_subject_count++;
//				}
									
//				sb1.setQuarter_grand_total(String.valueOf(qrt_total));
//				sb1.setAverageMarkTotal(String.format("%,.2f", Double.parseDouble(sb1.getQuarter_grand_total() == null?"0.0":sb1.getQuarter_grand_total())/added_subject_count));
				
				sb.setQuarter_grand_total("");
				sb.setAverageMarkTotal("");
									
				sub.add(sb);									
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		///*** filter the result list based on subject list		
		for(int i = 0; i < sub.size(); i++){
			
			for(int j = 0; j < subList.size(); j++){
				
				if(sub.get(i).getSub_id().equals(subList.get(j).getSub_id())){
					
					rslt.add(sub.get(i));					
				}				
			}
		}
		
		return rslt;
	}
		
	public static List<ExamResultBean> getExamTypeList(ExamResultBean exrslt){
		List<ExamResultBean> exl = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getExamTypeList);
			ps.setString(1, exrslt.getSub_id());
			ps.setString(2, exrslt.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean sb = new ExamResultBean();
				
				sb.setEt_id(rs.getString("ET_ID"));
				sb.setEt_name(rs.getString("ET_NAME"));
				sb.setExsub_id(rs.getString("exsub_id"));
				
				exl.add(sb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return exl;
	}
	
	public static List<ExamResultBean> getMarkPerExamType(List<ExamResultBean> rslt, ExamResultBean exrslt){
		
		List<ExamResultBean> exl = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			for(int i = 0; i < rslt.size(); i++){
				
				ps = con.prepareStatement(ExamResultQueries.getStudentMarkPerExamType);
				ps.setString(1, exrslt.getSi_id());
				ps.setString(2, rslt.get(i).getExsub_id());
				ps.setString(3, exrslt.getAcademic_year());
				ps.setString(4, exrslt.getAt_id());
				
				rs = ps.executeQuery();
				
				ExamResultBean sb = new ExamResultBean();
				sb.setEt_id(rslt.get(i).getEt_id());
				sb.setEt_name(rslt.get(i).getEt_name());
				
				while(rs.next()){
					sb.setResult(rs.getString("result"));
					sb.setEr_id(rs.getString("er_id"));
					sb.setSub_passmark(rs.getString("sub_passmark"));
					sb.setSub_totalmark(rs.getString("sub_totalmark"));
					
					if(rs.getString("result") != null && rs.getString("sub_totalmark") != null){
						if(Double.parseDouble(rs.getString("result")) >= Double.parseDouble(rs.getString("sub_passmark"))){
							sb.setSubmark_status("Passed");
						} else {
							sb.setSubmark_status("Failed");
						}
					}
				}
				
				exl.add(sb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return exl;
	}
	
	public static boolean updateStudResult(ExamResultBean exrslt, String loggedInUser){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.updateStudResult);
			ps.setString(1, exrslt.getResult());
			ps.setString(2, loggedInUser);
			ps.setString(3, TodayDate_YYYYMMDD.getToday());
			ps.setString(4, exrslt.getEr_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getStudListPerGrade(ExamResultBean exrslt){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getStudListPerGrade);
			ps.setString(1, exrslt.getCl_id());
			ps.setString(2, exrslt.getAcademic_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(rs.getString("si_id"));
				exb.setFname(rs.getString("fname"));
				exb.setMname(rs.getString("mname"));
				exb.setGname(rs.getString("gname"));
				
				rslt.add(exb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean validateExamDate(String exsub_id){
		
		boolean validation = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getSubjectExamDate);
			ps.setString(1, exsub_id);
			ps.setInt(2, Integer.parseInt(yr));
			rs = ps.executeQuery();
			
			while(rs.next()){
								
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				Date examDate = sdf.parse(rs.getString(1));
				Date dt = new Date();
				String td = sdf.format(dt);
				Date today = sdf.parse(td);
				
				if(today.compareTo(examDate) > 0){
					validation = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return validation;
		
	}
	
	public static List<ExamResultBean> getStudListPerGradeDetail(ExamResultBean exrslt){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		boolean examDateValidation = validateExamDate(exrslt.getExsub_id());			
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.StudentListForExamRslt);
			ps.setString(1, exrslt.getCl_id());
			ps.setString(2, exrslt.getCd_id());
			ps.setInt(3, Integer.parseInt(exrslt.getAcademic_year()));
			ps.setString(4, exrslt.getExsub_id());
			ps.setString(5, exrslt.getAt_id());
			ps.setInt(6, Integer.parseInt(exrslt.getAcademic_year()));
			ps.setString(7, exrslt.getCl_id());
			ps.setString(8, exrslt.getCd_id());
			ps.setString(9, exrslt.getExsub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(rs.getString("si_id"));
				exb.setFname(rs.getString("fname"));
				exb.setMname(rs.getString("mname"));
				exb.setGname(rs.getString("gname"));
				exb.setTotal_mark(rs.getString("total_mark"));
				exb.setExamDate_status(String.valueOf(examDateValidation));
				
				rslt.add(exb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getStudRsltList(List<ExamResultBean> studList, ExamResultBean exrslt){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getMarkPerExamType);
			ps.setString(1, exrslt.getAt_id());
			ps.setInt(2, Integer.parseInt(exrslt.getAcademic_year()));
			ps.setInt(3, Integer.parseInt(exrslt.getAcademic_year()));
			ps.setString(4, exrslt.getCl_id());
			ps.setString(5, exrslt.getCd_id());
			ps.setString(6, exrslt.getExsub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSi_id(rs.getString("si_id"));
				exb.setFname(rs.getString("fname"));
				exb.setMname(rs.getString("mname"));
				exb.setGname(rs.getString("gname"));	
				exb.setSubjectTotal_mark(rs.getString("result"));
				exb.setEr_id(rs.getString("er_id"));
				exb.setSub_passmark(rs.getString("pass_mark"));
				exb.setSub_totalmark(rs.getString("total_mark"));
				
				rslt.add(exb);
			}
			
//			for(int i = 0; i < studList.size(); i++){
//				
//				ps = con.prepareStatement(ExamResultQueries.getMarkPerExamType);
//				ps.setString(1, studList.get(i).getSi_id());
//				ps.setString(2, exrslt.getExsub_id());
//				ps.setString(3, exrslt.getAcademic_year());
//				//ps.setString(4, exrslt.getAt_id());
//				rs = ps.executeQuery();
//				
//				while(rs.next()){
//					ExamResultBean exb = new ExamResultBean();
//					
//					exb.setSi_id(studList.get(i).getSi_id());
//					exb.setFname(studList.get(i).getFname());
//					exb.setMname(studList.get(i).getMname());
//					exb.setGname(studList.get(i).getGname());	
//					exb.setSubjectTotal_mark(rs.getString("result"));
//					exb.setEr_id(rs.getString("er_id"));
//					exb.setSub_passmark(rs.getString("sub_passmark"));
//					exb.setSub_totalmark(rs.getString("sub_totalmark"));
//					
//					rslt.add(exb);
//				}				
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getGradeDetail(ExamResultBean exrslt){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getGradeDetail);
			ps.setString(1, exrslt.getCl_id());			
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean exb = new ExamResultBean();
				
				exb.setCd_id(rs.getString("cd_id"));
				exb.setCd_name(rs.getString("cd_name"));
				
				rslt.add(exb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	} 
	
	public static List<ExamResultBean> getGradeDetailForExamReg(ExamResultBean exrslt, String ti_id){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getGradeDetailForExamReg);
			ps.setString(1, exrslt.getCl_id());
			ps.setString(2, ti_id);
			ps.setString(3, exrslt.getCl_id());
			ps.setString(4, ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean exb = new ExamResultBean();
				
				exb.setCd_id(rs.getString("cd_id"));
				exb.setCd_name(rs.getString("cd_name"));
				
				rslt.add(exb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
		
	public static List<ExamResultBean> getSemisterListFullYear(String ac_year){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getSemisterList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean exb = new ExamResultBean();
				
				exb.setAt_id(rs.getString("at_id"));
				exb.setAt_name(rs.getString("at_name"));
				
				rslt.add(exb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getSemisterList(String ac_year){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getSemisterList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean exb = new ExamResultBean();
				
				exb.setAt_id(rs.getString("at_id"));
				exb.setAt_name(rs.getString("at_name"));
				
				rslt.add(exb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static String getQuarterName(String at_id){
		String rslt = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getQuarterName);
			ps.setString(1, at_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				rslt = rs.getString("at_name");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getFullYearCertificateRsltView(ExamResultBean exr){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		Double  grand_total = 0.0;
		int subcount = 0;
				
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getStudQuarterResult);
			ps.setString(1, exr.getSi_id());
			ps.setString(2, exr.getAt_id());
			ps.setString(3, exr.getAcademic_year());
			ps.setString(4, exr.getSi_id());
			ps.setString(5, exr.getAt_id());
			ps.setString(6, exr.getAcademic_year());
			ps.setString(7, exr.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSub_id(rs.getString("sub_id"));
				ex.setSub_name(rs.getString("sub_name"));
				ex.setSub_included_in_total_mark(rs.getString("add_status"));
				ex.setConvert_to_grade(rs.getString("convert_to_grade"));
				ex.setExsub_id(rs.getString("examsub_id"));
				
				if(ex.getConvert_to_grade().equals("NO")){
					
					String cumm = rs.getString("cummulative");
					String finalMark = rs.getString("finalExam");					
					Double quarter_total = Double.parseDouble(cumm) + Double.parseDouble(finalMark);					
					ex.setQuarter_total(String.valueOf(quarter_total));
					
					if(ex.getSub_included_in_total_mark().equals("Yes")){
						subcount++;
						grand_total += quarter_total;
					}
					
				} else {
					
					String cumm = "" + rs.getString("cummulative");					
					String finalMark = "" + rs.getString("finalExam");
					
					Double quarter_total = 0.0;
					
					if(("null".compareTo(cumm)) != 0 && ("null".compareTo(finalMark)) != 0){
						quarter_total = Double.parseDouble(cumm) + Double.parseDouble(finalMark);
					} else if(("null".compareTo(cumm)) == 0 && ("null".compareTo(finalMark)) != 0) {
						quarter_total = Double.parseDouble(finalMark);
					} else if(("null".compareTo(cumm)) != 0 && ("null".compareTo(finalMark)) == 0){
						quarter_total = Double.parseDouble(cumm);
					}					
					
					ps1 = con.prepareStatement(ExamResultQueries.getSubjectGradingResult);
					ps1.setString(1, ex.getExsub_id());
					ps1.setString(2, String.valueOf(quarter_total));
					ps1.setString(3, String.valueOf(quarter_total));
					rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						ex.setGrading_result(rs1.getString("eg_value"));
					}		
				}
				rslt.add(ex);
			}
			
			ExamResultBean ex = new ExamResultBean();
			ex.setConvert_to_grade("1");
			ex.setQuarter_grand_total(String.valueOf(grand_total));
			ex.setAverage_quarter_mark(String.valueOf(grand_total/subcount));
			
			rslt.add(ex);
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getFullYearCertificateRsltView_Update(ExamResultBean exr){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		Double  grand_total = 0.0;
		int subcount = 0;
				
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getStudentQrtResult_QryUpdate);
			ps.setString(1, exr.getCl_id());
			ps.setString(2, exr.getCl_id());
			ps.setString(3, exr.getSi_id());
			ps.setString(4, exr.getAt_id());
			ps.setString(5, exr.getAcademic_year());
						
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSub_id(rs.getString("sub_id"));
				ex.setSub_name(rs.getString("sub_name"));
				ex.setSub_included_in_total_mark(rs.getString("add_status"));
				ex.setConvert_to_grade(rs.getString("convert_to_grade"));
				ex.setExsub_id(rs.getString("exsub_id"));
				
				if(ex.getConvert_to_grade().equals("NO")){
					
					Double quarter_total = Double.parseDouble(rs.getString("total")== null?"0.0":rs.getString("total"));					
					ex.setQuarter_total(rs.getString("total"));
					
					if(ex.getSub_included_in_total_mark().equals("Yes")){
						subcount++;
						grand_total += quarter_total;
					}
					
				} else {
					
					Double quarter_total = Double.parseDouble(rs.getString("total")== null?"0.0":rs.getString("total"));
					
					ps1 = con.prepareStatement(ExamResultQueries.getSubjectGradingResult);
					ps1.setString(1, ex.getExsub_id());
					ps1.setString(2, String.valueOf(quarter_total));
					ps1.setString(3, String.valueOf(quarter_total));
					rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						ex.setGrading_result(rs1.getString("eg_value"));
					}		
				}
				rslt.add(ex);
			}
			
			ExamResultBean ex = new ExamResultBean();
			ex.setConvert_to_grade("1");
			ex.setQuarter_grand_total(String.valueOf(grand_total));
			ex.setAverage_quarter_mark(String.valueOf(grand_total/subcount));
			
			rslt.add(ex);
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getPDFFullYearCertificateRsltView(ExamResultBean exr){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		Double  grand_total = 0.0;
		int subcount = 0;
		String at_id = "";
		
		if(exr.getQr1_at_id().equals("1")){
			
			at_id = exr.getQr1_at_id();
			
		} 
		if(exr.getQr2_at_id().equals("2")){
			
			at_id = exr.getQr2_at_id();
			
		} 
		if(exr.getQr3_at_id().equals("3")){
			
			at_id = exr.getQr3_at_id();
		}
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getStudQuarterResult);
			
			ps.setString(1, exr.getSi_id());
			ps.setString(2, at_id);
			ps.setString(3, exr.getAcademic_year());
			ps.setString(4, exr.getSi_id());
			ps.setString(5, at_id);
			ps.setString(6, exr.getAcademic_year());
			ps.setString(7, exr.getCl_id());
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSub_id(rs.getString("sub_id"));
				ex.setSub_name(rs.getString("sub_name"));
				ex.setSub_included_in_total_mark(rs.getString("add_status"));
				ex.setConvert_to_grade(rs.getString("convert_to_grade"));
				
				if(ex.getConvert_to_grade().equals("NO")){
					
					String cumm = rs.getString("cummulative");
					String finalMark = rs.getString("finalExam");					
					Double quarter_total = Double.parseDouble(cumm) + Double.parseDouble(finalMark);					
					ex.setQuarter_total(String.valueOf(quarter_total));
					
					if(ex.getSub_included_in_total_mark().equals("Yes")){
						subcount++;
						grand_total += quarter_total;
					}
				} else {
					
					String cumm = "" + rs.getString("cummulative");					
					String finalMark = "" + rs.getString("finalExam");
					
					Double quarter_total = 0.0;
					
					if(("null".compareTo(cumm)) != 0 && ("null".compareTo(finalMark)) != 0){
						quarter_total = Double.parseDouble(cumm) + Double.parseDouble(finalMark);
					} else if(("null".compareTo(cumm)) == 0 && ("null".compareTo(finalMark)) != 0) {
						quarter_total = Double.parseDouble(finalMark);
					} else if(("null".compareTo(cumm)) != 0 && ("null".compareTo(finalMark)) == 0){
						quarter_total = Double.parseDouble(cumm);
					}					
					
					ps1 = con.prepareStatement(ExamResultQueries.getSubjectGradingResult);
					//ps1.setString(1, ex.getSub_id());
					//ps1.setString(2, exr.getCl_id());
					ps1.setString(1, rs.getString("examsub_id"));
					ps1.setString(2, String.valueOf(quarter_total));
					ps1.setString(3, String.valueOf(quarter_total));
					rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						ex.setGrading_result(rs1.getString("eg_value"));
					}		
				}
				rslt.add(ex);
			}
			
			ExamResultBean ex = new ExamResultBean();
			ex.setConvert_to_grade("1");
			ex.setQuarter_grand_total(String.valueOf(grand_total));
			ex.setAverage_quarter_mark(String.valueOf(grand_total/subcount));
			
			rslt.add(ex);
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getCertificateAverage(List<ExamResultBean> first, List<ExamResultBean> second, List<ExamResultBean> third, List<ExamResultBean> fourth){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		List<ExamResultBean> forLooping = first;
		
		int[] size = {first.size(), second.size(), third.size(), fourth.size()};
		int ind = -1;
		for(int i = 0; i < size.length; i++){
			if(forLooping.size() < size[i]){
				ind = i;
			}
		}
		
		int sem_count = 0;
		
		if(first.size() > 4){
			sem_count += 1;
		}
		if(second.size() > 4){
			sem_count += 1;
		}
		if(third.size() > 4){
			sem_count += 1;
		}
		if(fourth.size() > 4){
			sem_count += 1;
		}
		
		if(ind == 1){
			forLooping = second;
		} else if(ind == 2){
			forLooping = third;
		} else if(ind == 3){
			forLooping = fourth;
		}
		
		Double  grand_total = 0.0;
		int subcount = 0;
		
		String f_qtotal = "0";
		String s_qtotal = "0";
		String t_qtotal = "0";
		String fr_qtotal = "0";
		
		for(int i = 0; i < forLooping.size(); i++){
			
			ExamResultBean ex = new ExamResultBean();
			
			ex.setSub_id(forLooping.get(i).getSub_id());
			ex.setSub_name(forLooping.get(i).getSub_name());
			ex.setSub_included_in_total_mark(forLooping.get(i).getSub_included_in_total_mark());
			ex.setConvert_to_grade(forLooping.get(i).getConvert_to_grade());
			
			if(ex.getConvert_to_grade().equals("NO")){
				
				f_qtotal = first.size() > i&&first.get(i).getQuarter_total() != null?first.get(i).getQuarter_total():"0";
				s_qtotal = second.size() > i&&second.get(i).getQuarter_total() != null?second.get(i).getQuarter_total():"0";
				t_qtotal = third.size() > i&&third.get(i).getQuarter_total() != null?third.get(i).getQuarter_total():"0";
				fr_qtotal = fourth.size() > i&&fourth.get(i).getQuarter_total() != null?fourth.get(i).getQuarter_total():"0";
				
				
				Double total = Double.parseDouble(f_qtotal) + Double.parseDouble(s_qtotal) + Double.parseDouble(t_qtotal) + Double.parseDouble(fr_qtotal);
				Double year_avg = total/sem_count;					
				ex.setQuarter_total(String.format("%,.2f", year_avg));
				
				if(ex.getSub_included_in_total_mark().equals("Yes")){
					subcount++;
					grand_total += year_avg;
				}
			} else {
				if(!ex.getConvert_to_grade().equals("1"))
					ex.setQuarter_total("-");
			}
			
			rslt.add(ex);
		}
		
		ExamResultBean ex = new ExamResultBean();
//		ex.setQuarter_grand_total(String.format("%,.2f", grand_total));
		ex.setQuarter_grand_total(String.valueOf(grand_total));
//		ex.setAverage_quarter_mark(String.format("%,.2f", grand_total/subcount));
		ex.setAverage_quarter_mark(String.valueOf(grand_total/subcount));
		
		rslt.add(ex);
		
		return rslt;
	}
	
	public static List<ExamResultBean> getCertificateAverageForFullYearCertificate(List<ExamResultBean> first, List<ExamResultBean> second, List<ExamResultBean> third, List<ExamResultBean> fourth, int subject_counter){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		List<ExamResultBean> forLooping = first;
		
		int[] size = {first.size(), second.size(), third.size(), fourth.size()};
		int ind = -1;
		for(int i = 0; i < size.length; i++){
			if(forLooping.size() < size[i]){
				ind = i;
			}
		}
		
		int sem_count = 0, sub_counter = 0, sem_counter = 0;
		
//		if(first.size() > 4){
//			sem_count += 1;
//		}
//		if(second.size() > 4){
//			sem_count += 1;
//		}
//		if(third.size() > 4){
//			sem_count += 1;
//		}
//		if(fourth.size() > 4){
//			sem_count += 1;
//		}
		
		if(ind == 1){
			forLooping = second;
		} else if(ind == 2){
			forLooping = third;
		} else if(ind == 3){
			forLooping = fourth;
		}
		
		Double  grand_total = 0.0;
		int subcount = 0;
		
		String f_qtotal = "0";
		String s_qtotal = "0";
		String t_qtotal = "0";
		String fr_qtotal = "0";
		
		for(int i = 0; i < forLooping.size(); i++){
			
			if((forLooping.get(i).getSub_name()+"").length() != 0){
			
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSub_id(forLooping.get(i).getSub_id());
				ex.setSub_name(forLooping.get(i).getSub_name());
				ex.setSub_included_in_total_mark(forLooping.get(i).getSub_included_in_total_mark());
				ex.setConvert_to_grade(forLooping.get(i).getConvert_to_grade());
				
				if(ex.getConvert_to_grade().equals("NO")){
					
					f_qtotal = first.size() > i&&first.get(i).getQuarter_total() != null?first.get(i).getQuarter_total():"0";
					s_qtotal = second.size() > i&&second.get(i).getQuarter_total() != null?second.get(i).getQuarter_total():"0";
					t_qtotal = third.size() > i&&third.get(i).getQuarter_total() != null?third.get(i).getQuarter_total():"0";
					fr_qtotal = fourth.size() > i&&fourth.get(i).getQuarter_total() != null?fourth.get(i).getQuarter_total():"0";
					
					if(Double.parseDouble(f_qtotal) > 0.0){ sem_counter++; }
					if(Double.parseDouble(s_qtotal) > 0.0){ sem_counter++; }
					if(Double.parseDouble(t_qtotal) > 0.0){ sem_counter++; }
					if(Double.parseDouble(fr_qtotal) > 0.0){ sem_counter++; }
					if(sem_counter == 0){ sem_count = 1; }
					
					Double total = Double.parseDouble(f_qtotal) + Double.parseDouble(s_qtotal) + Double.parseDouble(t_qtotal) + Double.parseDouble(fr_qtotal);
					Double year_avg = total/sem_counter;					
					ex.setQuarter_total(String.format("%,.2f", year_avg));
					
					if(ex.getSub_included_in_total_mark().equals("Yes")){
						subcount++;
						grand_total += year_avg;
					}
				} else {
					if(!ex.getConvert_to_grade().equals("1"))
						ex.setQuarter_total("-");
				}			
				
				rslt.add(ex);
			}
			
			sem_counter = 0;
			
			sub_counter++;
			
			if(sub_counter == subject_counter){
				
				ExamResultBean exb = new ExamResultBean();
				
				exb.setSub_id("");
				exb.setSub_name("");
				exb.setSub_included_in_total_mark("");
				exb.setConvert_to_grade("");
				exb.setQuarter_total("");
				exb.setQuarter_grand_total(String.valueOf(grand_total));
				exb.setAverage_quarter_mark(String.valueOf(grand_total/subcount));
				
				rslt.add(exb);
				
				sub_counter = 0;
			}
			
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getStudentRankPerQuareter(ExamResultBean exr){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String qrt_rank = "";
		
		try{
			
			con = Connector.connect();
			
			for(int i = 1; i < 5; i++){
				
				ExamResultBean exrr = new ExamResultBean();
				
				ps = con.prepareStatement(ExamResultQueries.getStudentQuarterRank);
				ps.setString(1, exr.getCl_id());
				ps.setString(2, exr.getCd_id());
				ps.setString(3, String.valueOf(i));
				ps.setString(4, exr.getAcademic_year());
				ps.setString(5, exr.getSi_id());
				rs = ps.executeQuery();
				
				while(rs.next()){
					qrt_rank = rs.getString("rank");
				}
				
				exrr.setQuarter_rank(qrt_rank.length() != 0?qrt_rank:"-");
				rslt.add(exrr);
				qrt_rank = "";
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		qrt_rank = "";
		return rslt;
		
	}
	
	public static List<ExamResultBean> getStudentTotalMarkPerQuarterForRanking(ExamResultBean exr){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getStudentTotalMarkPerQuarterForRanking);
			ps.setString(1, exr.getAcademic_year());
			ps.setString(2, exr.getAcademic_year());
			ps.setString(3, exr.getCl_id());
			ps.setString(4, exr.getCd_id());			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setTotal_mark(rs.getString("total"));
				ex.setSi_id(rs.getString("si_id"));
				ex.setAt_id(rs.getString("at_id"));
				
				rslt.add(ex);
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
	
	public static String getEachStudentRankPerQuareter(ExamResultBean exr){
		
		String rslt = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(ExamResultQueries.getStudentQuarterRank);
			ps.setString(1, exr.getCl_id());
			ps.setString(2, exr.getCd_id());
			ps.setString(3, exr.getAt_id());
			ps.setString(4, exr.getAcademic_year());
			ps.setString(5, exr.getSi_id());
			rs = ps.executeQuery();
				
			while(rs.next()){
				
				rslt = rs.getString("rank");												
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
		
	}
	
	public static List<ExamResultBean> getQuarterResult(ExamResultBean exr){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getStudQuarterResult);
			ps.setString(1, exr.getSi_id());
			ps.setString(2, exr.getAt_id());
			ps.setString(3, exr.getAcademic_year());
			ps.setString(4, exr.getSi_id());
			ps.setString(5, exr.getAt_id());
			ps.setString(6, exr.getAcademic_year());
			ps.setString(7, exr.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSub_id(rs.getString("sub_id"));
				ex.setSub_name(rs.getString("sub_name"));
				ex.setSub_included_in_total_mark(rs.getString("add_status"));
				ex.setConvert_to_grade(rs.getString("convert_to_grade"));
				ex.setAt_name(rs.getString("at_name"));
				ex.setAt_id(rs.getString("at_id"));
				
				if(ex.getConvert_to_grade().equals("NO")){
					
					String cumm = rs.getString("cummulative");
					String finalMark = rs.getString("finalExam");
					
					ex.setCummulative_mark(cumm);
					ex.setFinal_exam_mark(finalMark);
					
					Double quarter_total = Double.parseDouble(cumm) + Double.parseDouble(finalMark);
					
					ex.setQuarter_total(String.valueOf(quarter_total));
					
				} else {
					
					String cumm = "" + rs.getString("cummulative");					
					String finalMark = "" + rs.getString("finalExam");
					
					Double quarter_total = 0.0;
					
					if(("null".compareTo(cumm)) != 0 && ("null".compareTo(finalMark)) != 0){
						quarter_total = Double.parseDouble(cumm) + Double.parseDouble(finalMark);
					} else if(("null".compareTo(cumm)) == 0 && ("null".compareTo(finalMark)) != 0) {
						quarter_total = Double.parseDouble(finalMark);
					} else if(("null".compareTo(cumm)) != 0 && ("null".compareTo(finalMark)) == 0){
						quarter_total = Double.parseDouble(cumm);
					}					
					
					ps1 = con.prepareStatement(ExamResultQueries.getSubjectGradingResult);
					//ps1.setString(1, ex.getSub_id());
					//ps1.setString(2, exr.getCl_id());
					ps1.setString(1, rs.getString("examsub_id"));
					ps1.setString(2, String.valueOf(quarter_total));
					ps1.setString(3, String.valueOf(quarter_total));
					rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						ex.setGrading_result(rs1.getString("eg_value"));
					}
					
					///*** override the grading based on the total result ***///
					ex.setGrading_result("A");
				}
				rslt.add(ex);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
				
		return rslt;
	}
	
	public static List<BevalHolisticBean> getHolisticEvaluationResult(ExamResultBean exrslt){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getStudentsBehaviouralHolisticComment);
			ps.setString(1, exrslt.getSi_id());
			ps.setString(2, exrslt.getCl_id());
			ps.setString(3, exrslt.getAt_id());
			ps.setString(4, exrslt.getAcademic_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhsr_id(rs.getString("bhsr_id"));
				bhb.setBhc_id(rs.getString("bhc_id"));
				bhb.setBhc_name(rs.getString("bhc_name"));
				bhb.setBhp_content(rs.getString("bhp_content"));
				bhb.setBeval_comment(rs.getString("beval_comment"));
				
				rslt.add(bhb);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		
		return rslt;
	}
	
	public static boolean checkCommentExistance(ExamResultBean exrslt){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.checkCommentExistance);
			ps.setString(1, exrslt.getSi_id());
			ps.setString(2, exrslt.getCl_id());
			ps.setString(3, exrslt.getAt_id());
			ps.setString(4, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				count++;
			}
			
			if(count > 0){
				rslt = true;
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
		
	}
	
	public static boolean saveTeachersComment(ExamResultBean exrslt, String loggedInUserType, String actor){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.saveTeachersComment);
			ps.setString(1, exrslt.getSi_id());
			ps.setString(2, exrslt.getCl_id());
			ps.setString(3, exrslt.getTeacher_comment());
			ps.setString(4, actor);
			ps.setString(5, loggedInUserType);
			ps.setString(6, TodayDate_YYYYMMDD.getTodayDayMonthYearFormat());
			ps.setString(7, exrslt.getAt_id());
			ps.setString(8, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getTeacherComment(ExamResultBean exrslt){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getTeacherComment);
			ps.setString(1, exrslt.getSi_id());
			ps.setString(2, exrslt.getCl_id());
			ps.setString(3, exrslt.getAt_id());
			ps.setString(4, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean erb = new ExamResultBean();
				
				erb.setErc_id(rs.getString("erc_id"));
				erb.setErc_content(rs.getString("erc_content"));
				
				rslt.add(erb);
				
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
		
	}
	
	public static boolean updateTeachersComment(ExamResultBean exrslt, String loggedInUserType, String actor){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.updateTeachersComment);
			ps.setString(1, exrslt.getErc_content());
			ps.setString(2, exrslt.getErc_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getQuarterTeacherComment(ExamResultBean erb){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getTeachersQuarterComment);
			ps.setString(1, erb.getSi_id());
			ps.setString(2, erb.getAt_id());
			ps.setString(3, erb.getCl_id());
			ps.setString(4, erb.getAcademic_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean erbb = new ExamResultBean();
				
				erbb.setErc_content(rs.getString("erc_content"));
				
				rslt.add(erbb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
		
	}
	
	public static String getHomeroomTeacherName(ExamResultBean erx){
		
		String homeroomTeacherName = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getHomeroomTeachersName);
			ps.setString(1, erx.getCl_id());
			ps.setString(2, erx.getCd_id());
			ps.setString(3, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				homeroomTeacherName = rs.getString("fname") + " " + rs.getString("mname");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return homeroomTeacherName;
	}
	
	public static String getParentsEmailAddress(ExamResultBean erb){
		String parentemail = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getParentsEmailAddress);
			ps.setString(1, erb.getSi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){				
				parentemail = rs.getString("email");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return parentemail;
	}

	public static List<ExamResultBean> getStudentAbsentDayList(ExamResultBean erb){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		return rslt;
		
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		try{
//			
//			con = Connector.connect();
//			ps = con.prepareStatement(AttendanceQueries.getStudAbsentDateList);
//			ps.setString(1, erb.getSi_id());
//			ps.setString(2, erb.getAcademic_year());
//			ps.setString(3, erb.getAt_id());
//			rs = ps.executeQuery();
//			
//			ExamResultBean ex = new ExamResultBean();
//			int counter = 0;
//			String dateList = "";
//			while(rs.next()){
//				counter++;
//				if(dateList.length() == 0){
//					dateList = rs.getString("att_date");
//				} else {
//					dateList = dateList + ", " + rs.getString("att_date");
//				}
//			}
//			
//			ex.setNoOfAbsentDay(String.valueOf(counter));
//			ex.setAbsentDayList(dateList);
//			
//			rslt.add(ex);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(con != null){
//				try{
//					con.close();
//				} catch (Exception e) {
//					
//				}
//			}
//		}
//		
//		return rslt;
	}
	
	public static List<ExamResultBean> getOrderedDescStudentAnnualTotalResult(ExamResultBean erb){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getOrderedDescStudentAnnualTotalResult);
			ps.setString(1, erb.getAcademic_year());
			ps.setString(2, erb.getAcademic_year());
			ps.setString(3, erb.getCl_id());
			ps.setString(4, erb.getCd_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSi_id(rs.getString("si_id"));
				ex.setTotal_mark(rs.getString("total"));
				
				rslt.add(ex);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	} 
	
	public static List<ExamResultBean> getStudentActiveSemisterList(ExamResultBean erb){
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getStudentActiveSemisterList);
			ps.setString(1, erb.getAcademic_year());
			ps.setString(2, erb.getCl_id());
			ps.setString(3, erb.getCd_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSi_id(rs.getString("si_id"));
				ex.setAt_id(rs.getString("at_id"));
				
				rslt.add(ex);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getSummerizedStudCountPerMarkInserted(ReportBean rb){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getSummerizedStudCountPerMarkInserted);
			ps.setString(1, rb.getStud_bean().getAc_year());
			ps.setString(2, rb.getAt_id());
			ps.setString(3, rb.getEt_id());
			ps.setString(4, rb.getClass_bean().getCl_id());
			ps.setString(5, rb.getStud_bean().getAc_year());
			ps.setString(6, rb.getClass_bean().getCl_id());
			ps.setString(7, rb.getCdetail_bean().getCd_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSub_name(rs.getString("sub_name"));
				ex.setStud_count(rs.getString("stud_count"));
				
				rslt.add(ex);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	
	public static List<ExamResultBean> getStudentResultForTranscript(String si_id, String cl_id, String cd_id, String ac_year){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ReportQueries.getAnnualTotalMarkPerGrade);
			ps.setString(1, si_id);
			ps.setString(2, cl_id);
			ps.setString(3, cd_id);
			ps.setString(4, ac_year);
			
			ps.setString(5, si_id);
			ps.setString(6, cl_id);
			ps.setString(7, cd_id);
			ps.setString(8, ac_year);
			
			ps.setString(9, ac_year);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean exrslt = new ExamResultBean();
								
				StudentBean stbean = new StudentBean();				
				stbean.setSi_id(rs.getString("si_id"));
				stbean.setFname(rs.getString("fname"));
				stbean.setMname(rs.getString("mname"));
				stbean.setGname(rs.getString("gname"));
				stbean.setSex(rs.getString("sex"));
				stbean.setDob(rs.getString("dob"));
				exrslt.setStud(stbean);
				
				exrslt.setSub_id(rs.getString("sub_id"));
				exrslt.setSub_name(rs.getString("sub_name"));
				exrslt.setSubject_total(rs.getString("sub_total"));
				exrslt.setConvert_to_grade(rs.getString("convert_to_grade"));
				exrslt.setSubmark_status(rs.getString("add_status"));
				exrslt.setAt_id(rs.getString("at_id"));				
				exrslt.setSubcl_id(rs.getString("subcl_id"));
				
				rslt.add(exrslt);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(con != null){
				try{
					con.close();
				} catch(Exception e){}
			}
		}
		
		
		///*** Ordering the result ***///
		
		List<ExamResultBean> rslt_ordered = new ArrayList<ExamResultBean>();
		
		int subject_counter_for_avg = 0, active_quarter_count = 0;
		
		double first_qrt_total = 0.0, second_qrt_total = 0.0, third_qrt_total = 0.0, fourth_qrt_total = 0.0;
		
		for(int i = 0; i < rslt.size(); i+=4){
			
			int qrt_counter = 0;
			
			ExamResultBean exrslt = new ExamResultBean();
			
			exrslt.setSub_name(rslt.get(i).getSub_name());
			
			if(rslt.get(i).getConvert_to_grade().equals("NO")){
				
				exrslt.setQrt_one_total(rslt.get(i).getSubject_total() == null?"0.0":rslt.get(i).getSubject_total());
				exrslt.setQrt_two_total(rslt.get(i+1).getSubject_total() == null?"0.0":rslt.get(i+1).getSubject_total());
				exrslt.setQrt_three_total(rslt.get(i+2).getSubject_total() == null?"0.0":rslt.get(i+2).getSubject_total());
				exrslt.setQrt_four_total(rslt.get(i+3).getSubject_total()== null?"0.0":rslt.get(i+3).getSubject_total());
				
				if(Double.parseDouble(exrslt.getQrt_one_total()) > 0.0){
					qrt_counter++;
				}
				if(Double.parseDouble(exrslt.getQrt_two_total()) > 0.0){
					qrt_counter++;
				}
				if(Double.parseDouble(exrslt.getQrt_three_total()) > 0.0){
					qrt_counter++;
				}
				if(Double.parseDouble(exrslt.getQrt_four_total()) > 0.0){
					qrt_counter++;
				}
				
				double qrtTotal = Double.parseDouble(exrslt.getQrt_one_total()) + Double.parseDouble(exrslt.getQrt_two_total()) + Double.parseDouble(exrslt.getQrt_three_total()) + Double.parseDouble(exrslt.getQrt_four_total());
				
				exrslt.setAverage_quarter_mark(String.format("%,.2f", qrtTotal/qrt_counter));
				
				active_quarter_count = qrt_counter == 0?1:qrt_counter;
				
			} else {
				
				exrslt.setQrt_one_total(rslt.get(i).getSubject_total() == null?"-":rslt.get(i).getSubject_total() == "0.0"?"-":"A");
				exrslt.setQrt_two_total(rslt.get(i+1).getSubject_total() == null?"-":rslt.get(i).getSubject_total() == "0.0"?"-":"A");
				exrslt.setQrt_three_total(rslt.get(i+2).getSubject_total() == null?"-":rslt.get(i).getSubject_total() == "0.0"?"-":"A");
				exrslt.setQrt_four_total(rslt.get(i+3).getSubject_total() == null?"-":rslt.get(i).getSubject_total() == "0.0"?"-":"A");
				exrslt.setAverage_quarter_mark("-");
			}
			
			if(rslt.get(i).getSubmark_status().equals("Yes")){
				
				first_qrt_total += Double.parseDouble(exrslt.getQrt_one_total());
				second_qrt_total += Double.parseDouble(exrslt.getQrt_two_total());
				third_qrt_total += Double.parseDouble(exrslt.getQrt_three_total());
				fourth_qrt_total += Double.parseDouble(exrslt.getQrt_four_total());
				
				subject_counter_for_avg++;
			}
			
			rslt_ordered.add(exrslt);
		}
		
		///*** Adding the total mark of each quarter ***///
		ExamResultBean exrslt_total = new ExamResultBean();
		exrslt_total.setSub_name("Total");
		exrslt_total.setQrt_one_total(String.format("%,.2f", first_qrt_total));
		exrslt_total.setQrt_two_total(String.format("%,.2f", second_qrt_total));
		exrslt_total.setQrt_three_total(String.format("%,.2f", third_qrt_total));
		exrslt_total.setQrt_four_total(String.format("%,.2f", fourth_qrt_total));		
		double avg_total = (first_qrt_total + second_qrt_total + third_qrt_total + fourth_qrt_total)/active_quarter_count;
		exrslt_total.setAverage_quarter_mark(String.format("%,.2f", avg_total));
		rslt_ordered.add(exrslt_total);
		///*** END - Adding the total mark of each quarter ***///
		
		
		///*** Adding the AVERAGE mark of each quarter ***///
		double first_qrt_average = first_qrt_total/subject_counter_for_avg;
		double second_qrt_average = second_qrt_total/subject_counter_for_avg;
		double third_qrt_average = third_qrt_total/subject_counter_for_avg;
		double fourth_qrt_average = fourth_qrt_total/subject_counter_for_avg;
		
		ExamResultBean exrslt_avg = new ExamResultBean();
		exrslt_avg.setSub_name("Average");
		exrslt_avg.setQrt_one_total(String.format("%,.2f", first_qrt_average));
		exrslt_avg.setQrt_two_total(String.format("%,.2f", second_qrt_average));
		exrslt_avg.setQrt_three_total(String.format("%,.2f", third_qrt_average));
		exrslt_avg.setQrt_four_total(String.format("%,.2f", fourth_qrt_average));		
		double qrt_avg = (first_qrt_average + second_qrt_average + third_qrt_average + fourth_qrt_average)/active_quarter_count;
		exrslt_avg.setAverage_quarter_mark(String.format("%,.2f", qrt_avg));		
		rslt_ordered.add(exrslt_avg);
		///*** END - Adding the AVERAGE mark of each quarter ***///
		
			
		
		///*** Adding the rank of each quarter ***///		
		ExamResultBean exr = new ExamResultBean();
		exr.setSi_id(si_id);
		exr.setCl_id(cl_id);
		exr.setCd_id(cd_id);
		exr.setAcademic_year(ac_year);
		
		List<ExamResultBean> studentTotalMarkPerQuarter = ExamResultDAO.getStudentTotalMarkPerQuarterForRanking(exr);
		
		ExamResultBean exrslt_rank = new ExamResultBean();
		exrslt_rank.setSub_name("Rank");
		int fq_rank = 1, sq_rank = 1, tq_rank = 1, frq_rank = 1;
						
		for(int i = 0; i < studentTotalMarkPerQuarter.size(); i++){
			
			///*** rank for First Quarter ***///
			if(studentTotalMarkPerQuarter.get(i).getAt_id().equals("1")){
				
				if(studentTotalMarkPerQuarter.get(i).getSi_id().equals(si_id)){
					exrslt_rank.setQrt_one_total(String.valueOf(fq_rank));
				}
				fq_rank++;				
			}
			
			///*** rank for Second Quarter ***///
			if(studentTotalMarkPerQuarter.get(i).getAt_id().equals("2")){
				
				if(studentTotalMarkPerQuarter.get(i).getSi_id().equals(si_id) ){
					exrslt_rank.setQrt_two_total(String.valueOf(sq_rank));
				}
				sq_rank++;
			}
			
			///*** rank for third Quarter ***///
			if(studentTotalMarkPerQuarter.get(i).getAt_id().equals("3")){
				
				if(studentTotalMarkPerQuarter.get(i).getSi_id().equals(si_id)){
					exrslt_rank.setQrt_three_total(String.valueOf(tq_rank));
				}
				tq_rank++;
			}
			
			///*** rank for Fourth Quarter ***///
			if(studentTotalMarkPerQuarter.get(i).getAt_id().equals("4")){
				
				if(studentTotalMarkPerQuarter.get(i).getSi_id().equals(si_id)){
					exrslt_rank.setQrt_four_total(String.valueOf(frq_rank));
				}
				frq_rank++;
			}			
		}
		
		///>>> to get the rank for average result <<<///
		List<StudentBean> stud_list = StudentDAO.getListPerGradeDetail(cl_id, cd_id, ac_year);
		
		List<ExamResultBean> studentTotalAverageMarkPerQuarter = new ArrayList<ExamResultBean>();
		
		for(int j = 0; j < stud_list.size(); j++){
			
			ExamResultBean erb = new ExamResultBean();
			erb.setSi_id(stud_list.get(j).getSi_id());
			
			double average_total = 0.0;
			
			for(int i = 0; i < studentTotalMarkPerQuarter.size(); i++){
				
				if(stud_list.get(j).getSi_id().equals(studentTotalMarkPerQuarter.get(i).getSi_id())){
					
					average_total += studentTotalMarkPerQuarter.get(i).getTotal_mark() == null?0.0:Double.parseDouble(studentTotalMarkPerQuarter.get(i).getTotal_mark());
				}
			}
			
			erb.setTotal_mark(String.valueOf(average_total));
			
			studentTotalAverageMarkPerQuarter.add(erb);
		}
		Collections.sort(studentTotalAverageMarkPerQuarter, new Comparator<ExamResultBean>() {
			public int compare(ExamResultBean a, ExamResultBean b){
									
				return b.getTotal_mark().compareTo(a.getTotal_mark());
			}
		});
		
		for(int k = 0; k < studentTotalAverageMarkPerQuarter.size(); k++){
			
			if(si_id.equals(studentTotalAverageMarkPerQuarter.get(k).getSi_id())){
				
				exrslt_rank.setAverage_quarter_mark(String.valueOf(k + 1));
			}
		}
		///>>> end - to get the rank for average result <<<///
		
		if(exrslt_avg.getAverage_quarter_mark().equals("0.00")){
			exrslt_rank.setAverage_quarter_mark("");
		}
				
		rslt_ordered.add(exrslt_rank);
		///*** END - Adding the rank of each quarter ***///
		
		
		///*** Adding conduct GRADING based on the student rank ***///
		ExamResultBean exrslt_conduct = new ExamResultBean();
		exrslt_conduct.setSub_name("Conduct");
		
		if(exrslt_rank.getQrt_one_total() != null){
			if(Integer.parseInt(exrslt_rank.getQrt_one_total()) <= 30){
				exrslt_conduct.setQrt_one_total("A");
			} else {
				exrslt_conduct.setQrt_one_total("B");
			}
//			else if(Integer.parseInt(exrslt_rank.getQrt_one_total()) > 10 && Integer.parseInt(exrslt_rank.getQrt_one_total()) <= 25){
//				exrslt_conduct.setQrt_one_total("B");
//			} else if(Integer.parseInt(exrslt_rank.getQrt_one_total()) > 25 && Integer.parseInt(exrslt_rank.getQrt_one_total()) <= 40){
//				exrslt_conduct.setQrt_one_total("C");
//			} else if(Integer.parseInt(exrslt_rank.getQrt_one_total()) > 40){
//				exrslt_conduct.setQrt_one_total("D");
//			}
		} else {
			exrslt_conduct.setQrt_one_total("-");
		}
		
		if(exrslt_rank.getQrt_two_total() != null){
			if(Integer.parseInt(exrslt_rank.getQrt_two_total()) <= 30){
				exrslt_conduct.setQrt_two_total("A");
			} else {
				exrslt_conduct.setQrt_one_total("B");
			}
//			else if(Integer.parseInt(exrslt_rank.getQrt_two_total()) > 10 && Integer.parseInt(exrslt_rank.getQrt_two_total()) <= 25){
//				exrslt_conduct.setQrt_two_total("B");
//			} else if(Integer.parseInt(exrslt_rank.getQrt_two_total()) > 25 && Integer.parseInt(exrslt_rank.getQrt_two_total()) <= 40){
//				exrslt_conduct.setQrt_two_total("C");
//			} else if(Integer.parseInt(exrslt_rank.getQrt_two_total()) > 40){
//				exrslt_conduct.setQrt_two_total("D");
//			}
		} else {
			exrslt_conduct.setQrt_two_total("-");
		}
		
		if(exrslt_rank.getQrt_three_total() != null){
			if(Integer.parseInt(exrslt_rank.getQrt_three_total()) <= 30){
				exrslt_conduct.setQrt_three_total("A");
			} else {
				exrslt_conduct.setQrt_one_total("B");
			}
//			else if(Integer.parseInt(exrslt_rank.getQrt_three_total()) > 10 && Integer.parseInt(exrslt_rank.getQrt_three_total()) <= 25){
//				exrslt_conduct.setQrt_three_total("B");
//			} else if(Integer.parseInt(exrslt_rank.getQrt_three_total()) > 25 && Integer.parseInt(exrslt_rank.getQrt_three_total()) <= 40){
//				exrslt_conduct.setQrt_three_total("C");
//			} else if(Integer.parseInt(exrslt_rank.getQrt_three_total()) > 40){
//				exrslt_conduct.setQrt_three_total("D");
//			}
		} else {
			exrslt_conduct.setQrt_three_total("-");
		}
		
		if(exrslt_rank.getQrt_four_total() != null){
			if(Integer.parseInt(exrslt_rank.getQrt_four_total()) <= 30){
				exrslt_conduct.setQrt_four_total("A");
			} else {
				exrslt_conduct.setQrt_one_total("B");
			}
//			else if(Integer.parseInt(exrslt_rank.getQrt_four_total()) > 10 && Integer.parseInt(exrslt_rank.getQrt_four_total()) <= 25){
//				exrslt_conduct.setQrt_four_total("B");
//			} else if(Integer.parseInt(exrslt_rank.getQrt_four_total()) > 25 && Integer.parseInt(exrslt_rank.getQrt_four_total()) <= 40){
//				exrslt_conduct.setQrt_four_total("C");
//			} else if(Integer.parseInt(exrslt_rank.getQrt_four_total()) > 40){
//				exrslt_conduct.setQrt_four_total("D");
//			}
		} else {
			exrslt_conduct.setQrt_four_total("-");
		}
		
		if(exrslt_rank.getAverage_quarter_mark().length() != 0){
			if(Integer.parseInt(exrslt_rank.getAverage_quarter_mark()) <= 30){
				exrslt_conduct.setAverage_quarter_mark("A");
			} else {
				exrslt_conduct.setQrt_one_total("B");
			}
//			else if(Integer.parseInt(exrslt_rank.getAverage_quarter_mark()) > 10 && Integer.parseInt(exrslt_rank.getAverage_quarter_mark()) <= 25){
//				exrslt_conduct.setAverage_quarter_mark("B");
//			} else if(Integer.parseInt(exrslt_rank.getAverage_quarter_mark()) > 25 && Integer.parseInt(exrslt_rank.getAverage_quarter_mark()) <= 40){
//				exrslt_conduct.setAverage_quarter_mark("C");
//			} else if(Integer.parseInt(exrslt_rank.getAverage_quarter_mark()) > 40){
//				exrslt_conduct.setAverage_quarter_mark("D");
//			}
		} else {
			exrslt_conduct.setAverage_quarter_mark("-");
		}
		
		rslt_ordered.add(exrslt_conduct);
		///*** END - Adding conduct GRADING based on the student rank ***///
		
		return rslt_ordered;
	}
	
	
	///*** ---------- Revised Certificate Processing DAO methods ---------- ***///
	
	public static List<ExamResultBean> getAllStudentsFullYearResultForCertificate(ExamResultBean exr, int sub_count){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		
		Double  grand_total = 0.0;
		int subcount = 0, subject_counter = 0;
		StringBuffer siid = new StringBuffer();
		StringBuffer atid = new StringBuffer();
				
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getAllStudentsSubjectTotalPerEachQuarterForCertificate2);
			ps.setString(1, exr.getCl_id());
			ps.setString(2, exr.getCd_id());
			ps.setString(3, exr.getAcademic_year());
			ps.setString(4, exr.getCl_id());
			
			ps.setString(5, exr.getCl_id());
			ps.setString(6, exr.getCd_id());
			ps.setString(7, exr.getAcademic_year());
			ps.setString(8, exr.getAcademic_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamResultBean ex = new ExamResultBean();
				
				///*** assign student profile info ***///
				StudentBean studbean = new StudentBean();
				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));				
				studbean.setGname(rs.getString("GNAME"));				
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setPhoto_path(rs.getString("PHOTO_PATH"));
				studbean.setPhoto_name(rs.getString("PHOTO_NAME"));
				studbean.setRslt_status(rs.getString("STUD_STATUS"));
				ex.setStud(studbean);
				///*** --------------------------- ***///
				
				siid = new StringBuffer(rs.getString("SI_ID"));
				atid = new StringBuffer(rs.getString("at_id"));
				
				ex.setSub_id(rs.getString("sub_id"));
				ex.setSub_name(rs.getString("sub_name"));
				ex.setSub_included_in_total_mark(rs.getString("add_status"));
				ex.setConvert_to_grade(rs.getString("convert_to_grade"));
				ex.setExsub_id(rs.getString("examsub_id"));
				ex.setAt_id(rs.getString("at_id"));
				
				if(ex.getConvert_to_grade().equals("NO")){
					
					Double quarter_total = Double.parseDouble((rs.getString("subject_total")+"").equals("null")?"0.0":rs.getString("subject_total"));					
					ex.setQuarter_total(String.valueOf(quarter_total));
					
					if(ex.getSub_included_in_total_mark().equals("Yes")){
						subcount++;
						grand_total += quarter_total;
					}
					
				} else {
					
					Double quarter_total = 0.0;
					
					if(!(rs.getString("subject_total")+"").equals("null")){
						quarter_total = Double.parseDouble(rs.getString("subject_total"));
					}					
					
					ps1 = con.prepareStatement(ExamResultQueries.getSubjectGradingResult);
					ps1.setString(1, ex.getExsub_id());
					ps1.setString(2, String.valueOf(quarter_total));
					ps1.setString(3, String.valueOf(quarter_total));
					rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						ex.setGrading_result(rs1.getString("eg_value"));
					}		
				}
				
				fullYearStudRslt.add(ex);
				rslt.add(ex);
				
				subject_counter++;
				
				if(subject_counter == sub_count){
					
					ExamResultBean exb = new ExamResultBean();
					
					StudentBean stb = new StudentBean();
					stb.setSi_id(siid.toString());					
					exb.setStud(stb);
					
					exb.setAt_id(atid.toString());
					
					exb.setSub_id("");
					exb.setSub_name("");
					exb.setSub_included_in_total_mark("");
					exb.setConvert_to_grade("1");
					exb.setExsub_id("");
					exb.setQuarter_total("0.0");
					exb.setGrading_result("");
										
					exb.setQuarter_grand_total(String.valueOf(grand_total));
					exb.setAverage_quarter_mark(String.valueOf(grand_total/subcount));
					
					grand_total = 0.0;
					subcount = 0;
					subject_counter = 0;
					
					fullYearStudRslt.add(exb);
					rslt.add(exb);
				}
				
			}
						
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<List<ExamResultBean>> getStudentEachQuarterTotalResult(List<StudentBean> std){
		
		studentEachQrtTotalRslt.clear();
		
		List<List<ExamResultBean>> rslt = new ArrayList<List<ExamResultBean>>();
		
		List<ExamResultBean> rslt_1 = new ArrayList<ExamResultBean>();
		List<ExamResultBean> rslt_2 = new ArrayList<ExamResultBean>();
		List<ExamResultBean> rslt_3 = new ArrayList<ExamResultBean>();
		List<ExamResultBean> rslt_4 = new ArrayList<ExamResultBean>();
		List<ExamResultBean> rslt_5 = new ArrayList<ExamResultBean>();
		
		double first_qrt_total = 0.0, second_qrt_total = 0.0, third_qrt_total = 0.0, fourth_qrt_total = 0.0;
		
		for(int j = 0; j < std.size(); j++){
			
			for(int i = 0; i < fullYearStudRslt.size(); i++){
				//*** first quarter total
				if(std.get(j).getSi_id().equals(fullYearStudRslt.get(i).getStud().getSi_id()) && "1".equals(fullYearStudRslt.get(i).getAt_id())){
					
					if("Yes".equals(fullYearStudRslt.get(i).getSub_included_in_total_mark())){
						
						first_qrt_total += Double.parseDouble((fullYearStudRslt.get(i).getQuarter_total()+"").equals("null")?"0.0":fullYearStudRslt.get(i).getQuarter_total());
					}
				}
				//*** second quarter total
				if(std.get(j).getSi_id().equals(fullYearStudRslt.get(i).getStud().getSi_id()) && "2".equals(fullYearStudRslt.get(i).getAt_id())){
					
					if("Yes".equals(fullYearStudRslt.get(i).getSub_included_in_total_mark())){
						
						second_qrt_total += Double.parseDouble((fullYearStudRslt.get(i).getQuarter_total()+"").equals("null")?"0.0":fullYearStudRslt.get(i).getQuarter_total());
					}
				}
				//*** third quarter total
				if(std.get(j).getSi_id().equals(fullYearStudRslt.get(i).getStud().getSi_id()) && "3".equals(fullYearStudRslt.get(i).getAt_id())){
					
					if("Yes".equals(fullYearStudRslt.get(i).getSub_included_in_total_mark())){
						
						third_qrt_total += Double.parseDouble((fullYearStudRslt.get(i).getQuarter_total()+"").equals("null")?"0.0":fullYearStudRslt.get(i).getQuarter_total());
					}
				}
				//*** fourth quarter total
				if(std.get(j).getSi_id().equals(fullYearStudRslt.get(i).getStud().getSi_id()) && "4".equals(fullYearStudRslt.get(i).getAt_id())){
					
					if("Yes".equals(fullYearStudRslt.get(i).getSub_included_in_total_mark())){
						
						fourth_qrt_total += Double.parseDouble((fullYearStudRslt.get(i).getQuarter_total()+"").equals("null")?"0.0":fullYearStudRslt.get(i).getQuarter_total());
					}
				}
			}
			
			int qrt_counter = 0;
			
			if(first_qrt_total > 0.0){
				qrt_counter++;
			}
			if(second_qrt_total > 0.0){
				qrt_counter++;
			}
			if(third_qrt_total > 0.0){
				qrt_counter++;
			}
			if(fourth_qrt_total > 0.0){
				qrt_counter++;
			}
			
			///*** 1st quarter result
			ExamResultBean ex_1 = new ExamResultBean();			
			ex_1.setSi_id(std.get(j).getSi_id());
			ex_1.setQrt_one_total(String.valueOf(first_qrt_total));			
			rslt_1.add(ex_1);
			
			///*** 2nd quarter result
			ExamResultBean ex_2 = new ExamResultBean();
			ex_2.setSi_id(std.get(j).getSi_id());
			ex_2.setQrt_two_total(String.valueOf(second_qrt_total));
			rslt_2.add(ex_2);
			
			///*** 3rd quarter result
			ExamResultBean ex_3 = new ExamResultBean();
			ex_3.setSi_id(std.get(j).getSi_id());
			ex_3.setQrt_three_total(String.valueOf(third_qrt_total));
			rslt_3.add(ex_3);
			
			///*** 4th quarter result
			ExamResultBean ex_4 = new ExamResultBean();
			ex_4.setSi_id(std.get(j).getSi_id());
			ex_4.setQrt_four_total(String.valueOf(fourth_qrt_total));
			rslt_4.add(ex_4);
			
			///*** Quarter average result
			ExamResultBean ex_5 = new ExamResultBean();
			ex_5.setSi_id(std.get(j).getSi_id());
			ex_5.setAverageMarkTotal(String.valueOf((first_qrt_total + second_qrt_total + third_qrt_total + fourth_qrt_total)/qrt_counter));
			rslt_5.add(ex_5);
			
			//studentEachQrtTotalRslt.add(ex);
						
			first_qrt_total = 0.0; second_qrt_total = 0.0; third_qrt_total = 0.0; fourth_qrt_total = 0.0;
		}
		
		///*** -------- SORTING -------- ***///
		
		///*** Sort 1st Quarter result for ranking ***///		
		Collections.sort(rslt_1, new Comparator<ExamResultBean>() {
			public int compare(ExamResultBean a, ExamResultBean b){
				int rslt = 0;
				
				if(Double.parseDouble(b.getQrt_one_total()) > Double.parseDouble(a.getQrt_one_total())){
					rslt = 1;
				}
				if(Double.parseDouble(b.getQrt_one_total()) < Double.parseDouble(a.getQrt_one_total())){
					rslt = -1;
				}
				if(Double.parseDouble(b.getQrt_one_total()) == Double.parseDouble(a.getQrt_one_total())){
					rslt = 0;
				}				
				return rslt;
			}
		});
		
		///*** Sort 2nd Quarter result for ranking ***///		
		Collections.sort(rslt_2, new Comparator<ExamResultBean>() {
			public int compare(ExamResultBean a, ExamResultBean b){
				int rslt = 0;
				
				if(Double.parseDouble(b.getQrt_two_total()) > Double.parseDouble(a.getQrt_two_total())){
					rslt = 1;
				}
				if(Double.parseDouble(b.getQrt_two_total()) < Double.parseDouble(a.getQrt_two_total())){
					rslt = -1;
				}
				if(Double.parseDouble(b.getQrt_two_total()) == Double.parseDouble(a.getQrt_two_total())){
					rslt = 0;
				}				
				return rslt;
			}
		});
		
		///*** Sort 3rd Quarter result for ranking ***///		
		Collections.sort(rslt_3, new Comparator<ExamResultBean>() {
			public int compare(ExamResultBean a, ExamResultBean b){
				int rslt = 0;
				
				if(Double.parseDouble(b.getQrt_three_total()) > Double.parseDouble(a.getQrt_three_total())){
					rslt = 1;
				}
				if(Double.parseDouble(b.getQrt_three_total()) < Double.parseDouble(a.getQrt_three_total())){
					rslt = -1;
				}
				if(Double.parseDouble(b.getQrt_three_total()) == Double.parseDouble(a.getQrt_three_total())){
					rslt = 0;
				}				
				return rslt;
			}
		});
		
		///*** Sort 4th Quarter result for ranking ***///		
		Collections.sort(rslt_4, new Comparator<ExamResultBean>() {
			public int compare(ExamResultBean a, ExamResultBean b){
				int rslt = 0;
				
				if(Double.parseDouble(b.getQrt_four_total()) > Double.parseDouble(a.getQrt_four_total())){
					rslt = 1;
				}
				if(Double.parseDouble(b.getQrt_four_total()) < Double.parseDouble(a.getQrt_four_total())){
					rslt = -1;
				}
				if(Double.parseDouble(b.getQrt_four_total()) == Double.parseDouble(a.getQrt_four_total())){
					rslt = 0;
				}				
				return rslt;
			}
		});
		
		Collections.sort(rslt_5, new Comparator<ExamResultBean>() {
			public int compare(ExamResultBean a, ExamResultBean b){
				int rslt = 0;
				
				if(Double.parseDouble(b.getAverageMarkTotal()) > Double.parseDouble(a.getAverageMarkTotal())){
					rslt = 1;
				}
				if(Double.parseDouble(b.getAverageMarkTotal()) < Double.parseDouble(a.getAverageMarkTotal())){
					rslt = -1;
				}
				if(Double.parseDouble(b.getAverageMarkTotal()) == Double.parseDouble(a.getAverageMarkTotal())){
					rslt = 0;
				}				
				return rslt;
			}
		});
		
		rslt.add(rslt_1);
		rslt.add(rslt_2);
		rslt.add(rslt_3);
		rslt.add(rslt_4);
		rslt.add(rslt_5);		
		
		studentEachQrtTotalRslt.add(rslt_1);
		studentEachQrtTotalRslt.add(rslt_2);
		studentEachQrtTotalRslt.add(rslt_3);
		studentEachQrtTotalRslt.add(rslt_4);
		studentEachQrtTotalRslt.add(rslt_5);
		
//		for(int j = 0; j < rslt_1.size(); j++){
//		
//			System.out.print(rslt.get(0).get(j).getSi_id() + " " + rslt.get(0).get(j).getQrt_one_total() + " - ");
//			System.out.print(rslt.get(1).get(j).getSi_id() + " " + rslt.get(1).get(j).getQrt_two_total() + " - ");
//			System.out.print(rslt.get(2).get(j).getSi_id() + " " + rslt.get(2).get(j).getQrt_three_total() + " - ");
//			System.out.print(rslt.get(3).get(j).getSi_id() + " " + rslt.get(3).get(j).getQrt_four_total() + " - ");
//			System.out.println(rslt.get(4).get(j).getSi_id() + " " + rslt.get(4).get(j).getAverageMarkTotal());			
//		}
		
		return rslt;
	}
	
	public static List<StudentBean> getStudentProfileByAcademicYearFromExecutedQuery(StudentBean std) {	
		
		List<StudentBean> studlist = new ArrayList<StudentBean>();
		
		int counter = 0;
		
		for(int i = 0; i < fullYearStudRslt.size(); i++){
			
			if(std.getSi_id().equals(fullYearStudRslt.get(i).getStud().getSi_id())){
				
				StudentBean studbean = new StudentBean();
	
				studbean.setSi_id(fullYearStudRslt.get(i).getStud().getSi_id());
				studbean.setFname(fullYearStudRslt.get(i).getStud().getFname());
				studbean.setMname(fullYearStudRslt.get(i).getStud().getMname());			
				studbean.setGname(fullYearStudRslt.get(i).getStud().getGname());			
				studbean.setMother_name(fullYearStudRslt.get(i).getStud().getMother_name());
				studbean.setSex(fullYearStudRslt.get(i).getStud().getSex());
				studbean.setDob(fullYearStudRslt.get(i).getStud().getDob());
				studbean.setPob(fullYearStudRslt.get(i).getStud().getPob());
				studbean.setNationality(fullYearStudRslt.get(i).getStud().getNationality());
				studbean.setId_no(fullYearStudRslt.get(i).getStud().getId_no());
				studbean.setSi_status(fullYearStudRslt.get(i).getStud().getSi_status());
				studbean.setPhoto_path(fullYearStudRslt.get(i).getStud().getPhoto_path());
				studbean.setPhoto_name(fullYearStudRslt.get(i).getStud().getPhoto_name());
				studbean.setRslt_status(fullYearStudRslt.get(i).getStud().getRslt_status());
				
				studlist.add(studbean);
				
				counter++;
			}
			
			if(counter > 0){
				break;
			}
		}
		
		return studlist;
	}
	
	public static List<ExamResultBean> getStudentFullYearCertificateRsltView(ExamResultBean exr){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Double  grand_total = 0.0;
		int subcount = 0;
		
		for(int i = 0; i < fullYearStudRslt.size(); i++){
			
			if(exr.getSi_id().equals(fullYearStudRslt.get(i).getStud().getSi_id()) && exr.getAt_id().equals(fullYearStudRslt.get(i).getAt_id()) && (fullYearStudRslt.get(i).getSub_name()+"").length() != 0){
				
				ExamResultBean ex = new ExamResultBean();
				
				ex.setSub_id(fullYearStudRslt.get(i).getSub_id());
				ex.setSub_name(fullYearStudRslt.get(i).getSub_name());
				ex.setSub_included_in_total_mark(fullYearStudRslt.get(i).getSub_included_in_total_mark());
				ex.setConvert_to_grade(fullYearStudRslt.get(i).getConvert_to_grade());
				ex.setExsub_id(fullYearStudRslt.get(i).getExsub_id());
				ex.setQuarter_total(fullYearStudRslt.get(i).getQuarter_total());
				ex.setGrading_result(fullYearStudRslt.get(i).getGrading_result());
				
				if("Yes".equals(ex.getSub_included_in_total_mark())){
					subcount++;
					grand_total += Double.parseDouble(fullYearStudRslt.get(i) == null?"0.0":fullYearStudRslt.get(i).getQuarter_total());
				}
				
				rslt.add(ex);
			}
		}
		
		ExamResultBean ex = new ExamResultBean();
		
		StudentBean stb = new StudentBean();
		stb.setSi_id(exr.getSi_id());					
		ex.setStud(stb);
		
		ex.setAt_id(exr.getAt_id());
		
		ex.setSub_id("");
		ex.setSub_name("");
		ex.setSub_included_in_total_mark("");
		ex.setConvert_to_grade("1");
		ex.setExsub_id("");
		ex.setQuarter_total("0.0");
		ex.setGrading_result("");
		
		ex.setQuarter_grand_total(String.valueOf(grand_total));
		ex.setAverage_quarter_mark(String.valueOf(grand_total/subcount));
		
		rslt.add(ex);
		
		return rslt;
	}
	
	public static List<ExamResultBean> getQuarterTeacherCommentForAllStudentsPerClass(ExamResultBean erb){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getTeachersQuarterCommentForAllStudentsPerClass);
			ps.setString(1, erb.getCl_id());
			ps.setString(2, erb.getAcademic_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamResultBean erbb = new ExamResultBean();
				
				erbb.setSi_id(rs.getString("si_id"));
				erbb.setErc_content(rs.getString("erc_content"));
				erbb.setAt_id(rs.getString("at_id"));
				
				teachersQuarterCommentForAllStudentsPerClass.add(erbb);
				rslt.add(erbb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
		
	}
	
	public static List<ExamResultBean> getQuarterTeacherCommentPerStudent(ExamResultBean erb){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		int counter = 0;
		
		for(int i = 0; i < teachersQuarterCommentForAllStudentsPerClass.size(); i++){
			
			if(erb.getSi_id().equals(teachersQuarterCommentForAllStudentsPerClass.get(i).getSi_id()) && erb.getAt_id().equals(teachersQuarterCommentForAllStudentsPerClass.get(i).getAt_id())){
			
				ExamResultBean erbb = new ExamResultBean();
				
				erbb.setErc_content(teachersQuarterCommentForAllStudentsPerClass.get(i).getErc_content());
				
				rslt.add(erbb);
				
				counter++;
			}
			
			if(counter > 0){
				break;
			}
		}
		
		return rslt;
	}
	
	public static List<CertDefaultComBean> getActiveDefaultCommentList(){
		
		List<CertDefaultComBean> rslt = new ArrayList<CertDefaultComBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CertDefaultComQueries.getActiveDefaultComment);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				CertDefaultComBean cdc = new CertDefaultComBean();
				
				cdc.setEdc_content(rs.getString("edc_content"));
				cdc.setRank_from(rs.getString("rank_from"));
				cdc.setRank_to(rs.getString("rank_to"));
				
				defaultCommentList.add(cdc);
				rslt.add(cdc);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<CertDefaultComBean> getDefaultCommentPerStudent(String stud_avg){
		
		List<CertDefaultComBean> rslt = new ArrayList<CertDefaultComBean>();
		
		int counter = 0;
		
		for(int i = 0; i < defaultCommentList.size(); i++){
			
			if(Double.parseDouble(defaultCommentList.get(i).getRank_from()) >= Double.parseDouble(stud_avg) && Double.parseDouble(defaultCommentList.get(i).getRank_to()) < Double.parseDouble(stud_avg)){
			
				CertDefaultComBean erbb = new CertDefaultComBean();
				
				erbb.setEdc_content(defaultCommentList.get(i).getEdc_content());
				
				rslt.add(erbb);
				
				counter++;
			}
			
			if(counter > 0){
				break;
			}
		}
		
		return rslt;
	}
	
	public static List<ExamResultBean> getStudentRankPerQuareterForCertificate(ExamResultBean exr){
		
		List<ExamResultBean> rslt = new ArrayList<ExamResultBean>();
		
		int size = studentEachQrtTotalRslt.get(0).size();
		
		///*** student 1st Quarter rank
		for(int j = 0; j < size; j++){
			
			if(studentEachQrtTotalRslt.get(0).get(j).getSi_id().equals(exr.getSi_id())){
				
				ExamResultBean exrr = new ExamResultBean();
				exrr.setQuarter_rank(String.valueOf(j+1));
				rslt.add(exrr);
				
				break;
			}
		}
		
		///*** student 2nd Quarter rank
		for(int j = 0; j < size; j++){
			
			if(studentEachQrtTotalRslt.get(1).get(j).getSi_id().equals(exr.getSi_id())){
				
				ExamResultBean exrr = new ExamResultBean();
				exrr.setQuarter_rank(String.valueOf(j+1));
				rslt.add(exrr);
				
				break;
			}			
		}
		
		///*** student 3rd Quarter rank
		for(int j = 0; j < size; j++){
			
			if(studentEachQrtTotalRslt.get(2).get(j).getSi_id().equals(exr.getSi_id())){
				
				ExamResultBean exrr = new ExamResultBean();
				exrr.setQuarter_rank(String.valueOf(j+1));
				rslt.add(exrr);
				
				break;
			}			
		}
		
		///*** student 4th Quarter rank
		for(int j = 0; j < size; j++){
			
			if(studentEachQrtTotalRslt.get(3).get(j).getSi_id().equals(exr.getSi_id())){
				
				ExamResultBean exrr = new ExamResultBean();
				exrr.setQuarter_rank(String.valueOf(j+1));
				rslt.add(exrr);
				
				break;
			}
		}
		
		///*** student 4th Quarter rank
		for(int j = 0; j < size; j++){
			
			if(studentEachQrtTotalRslt.get(4).get(j).getSi_id().equals(exr.getSi_id())){
				
				ExamResultBean exrr = new ExamResultBean();
				exrr.setQuarter_rank(String.valueOf(j+1));
				rslt.add(exrr);
				
				break;
			}
		}
		
		return rslt;
	}
	
}
