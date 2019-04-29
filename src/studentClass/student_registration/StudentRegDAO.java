package studentClass.student_registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cRoomClass.class_detail.CRDetailBean;
import connectionClass.Connector;
import examClass.exam_result.ExamResultBean;
import studentClass.StudentBean;
import studentClass.StudentQueries;
import util.DateConvertor;
import util.ReturnCurrentEthiopianYear;
import util.TodayDate_YYYYMMDD;

public class StudentRegDAO {
	
	public static List<StudentRegBean> getStudList(String clid, String ac_year){
		
		List<StudentRegBean> studlist = new ArrayList<StudentRegBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			//the older class
			int prev_cl = Integer.parseInt(clid) - 1;
			int last_year = Integer.parseInt(ac_year) - 1;
			String fullName = "";
			con = Connector.connect();
			
			if(clid.equals("1")){
				
				ps = con.prepareStatement(StudentRegQueries.getNewStudList);
				ps.setString(1, clid);
				ps.setInt(2, last_year);
				ps.setString(3, clid);
				ps.setInt(4, Integer.parseInt(ac_year));
				
			} else {
				
				ps = con.prepareStatement(StudentRegQueries.getStudList);
				///>>> passed
				ps.setInt(1, prev_cl);
				ps.setInt(2, last_year);
				ps.setInt(3, Integer.parseInt(ac_year));
				///>>> failed
				ps.setString(4, clid);
				ps.setInt(5, last_year);
				ps.setInt(6, last_year);
				ps.setInt(7, last_year);
				///>>> return
				ps.setInt(8, Integer.parseInt(ac_year));
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				StudentRegBean std = new StudentRegBean();
				fullName = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname");
				std.setSi_id(rs.getString("si_id"));
				std.setStud_name(fullName);
				std.setRegStudStatus(rs.getString("status"));
				
				studlist.add(std);
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
		
		return studlist;		
	}
	
	public static List<StudentRegBean> getSectionList(String clid){
		//getting academic year --- starting from May to August the academic year consider the next year
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
				
		String yr = util.DateConvertor.dateConvertor(day+"-"+month+"-"+year);
		Integer acyear = 0;
		if(month == 4 || month == 5 || month == 6 || month == 7){
			acyear = Integer.parseInt(yr) + 1;
		} else {
			acyear = Integer.parseInt(yr);
		}
		
//		StudentRegBean sc = new StudentRegBean();
//		sc.setAcademic_year(String.valueOf(acyear));
				
		
		//getting section list per selected grade
		List<StudentRegBean> seclist = new ArrayList<StudentRegBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentRegQueries.getActiveSecList);
			ps.setString(1, clid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				StudentRegBean sec = new StudentRegBean();
				
				sec.setSec_id(rs.getString("cd_id"));
				sec.setSec_name(rs.getString("cd_name"));
				sec.setAcademic_year(String.valueOf(acyear));
				
				seclist.add(sec);
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
		
		return seclist;
	}
	
	
	static List<StudentRegBean> selstudlist = new ArrayList<StudentRegBean>();
	
	public static List<StudentRegBean> selStudList(String stud_id, String stud_name, String cl_id){	
				
		StudentRegBean stud = new StudentRegBean();
		stud.setSelstud_id(stud_id);
		stud.setSelstud_name(stud_name);
		stud.setSelcl_id(cl_id);
		stud.setClcd_id("");
		
		selstudlist.add(stud);
		
		return selstudlist;
		
	}
	
	public static List<StudentRegBean> getSelectedStudentListForRegistration(List<Object> studentList){	
		
		String[] studInfo = studentList.toString().substring(1, studentList.toString().length()-1).split(",");
		
		for(int i = 0; i < studInfo.length; i+=3){
			
			StudentRegBean stud = new StudentRegBean();
			stud.setSelstud_id(studInfo[i]);
			stud.setSelstud_name(studInfo[i+1]);
			stud.setSelcl_id(studInfo[i+2]);
			stud.setClcd_id("");
			
			selstudlist.add(stud);
		}
		
		return selstudlist;		
	}
	
	public static List<StudentRegBean> removeStudList(String inde){
		selstudlist.remove(Integer.parseInt(inde));
		return selstudlist;
	}
	
	public static List<StudentRegBean> getClCdId(String cdid, String clid){
		
		List<StudentRegBean> studreg = new ArrayList<StudentRegBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentRegQueries.getClSecRelId);
			ps.setString(1, cdid);
			ps.setString(2, clid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				StudentRegBean clseid = new StudentRegBean();				
				clseid.setClcd_id(rs.getString("clcd_id"));
				
				CRDetailBean crdBean = new CRDetailBean();
				crdBean.setCl_capacity(rs.getString("cl_capacity"));
				clseid.setCrdetailBean(crdBean);
				studreg.add(clseid);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return studreg;
	}
	
	public static Boolean registerStudList(String clcdid, StudentRegBean crb, int stud_count, int cl_capacity){
		
		Boolean regrslt = false;
		
		String today = TodayDate_YYYYMMDD.getToday();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		///*** Check if there is any removed student ***///
		if(!crb.getRemoveStudentFromRegistrationList().toString().substring(1, crb.getRemoveStudentFromRegistrationList().toString().length()-1).equals("")){
		
			String[] removeStudList = crb.getRemoveStudentFromRegistrationList().toString().substring(1, crb.getRemoveStudentFromRegistrationList().toString().length()-1).split(",");
		
			List<Integer> removedStudListIndex = new ArrayList<Integer>();
					
			for(int i = 0; i < selstudlist.size(); i++){
				
				for(int j = 0; j < removeStudList.length; j++){
					
					if(selstudlist.get(i).getSi_id().equals(removeStudList[j])){
						
						removedStudListIndex.add(i);
					}
				}
			}
			
			for(int i = 0; i < removedStudListIndex.size(); i++){
				
				selstudlist.remove(removedStudListIndex.get(i));
			}
		}
		///*** END - Check if there is any removed student ***///
		
		try{
			
			int ln = cl_capacity - stud_count;
			
			int loopsize = 0;
			
			if(selstudlist.size() <= ln){
				loopsize = selstudlist.size();
			} else {
				loopsize = ln;
			}
			
			con = Connector.connect();
			
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(StudentRegQueries.registerStudList);
			
			int rowcount = 0;
			
			for(int i = 0; i < loopsize; i++){
				
				ps.setString(1, clcdid);
				ps.setString(2, today);
				ps.setString(3, selstudlist.get(i).getSelstud_id());
				ps.setString(4, String.valueOf(crb.getAcademic_year()));
				ps.addBatch();
				
				rowcount++;
			}
			
			int[] rslts = ps.executeBatch();
			
			if(rowcount == rslts.length){
				
				con.commit();
				regrslt = true;
			}
			
			clearSelStudList();
			
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
		
		return regrslt;
	}
	
	public static void clearSelStudList(){
		
		selstudlist.clear();
	}
	
	public static boolean changeStudentCurrentClassroom(StudentRegBean stud){
		boolean rslt = false;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentRegQueries.updateStudentClassRoom);
			ps.setString(1, stud.getClcd_id());
			ps.setString(2, stud.getSi_id());
			ps.setString(3, yr);
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
	
	public static boolean saveStudentFinalResultStatus(ExamResultBean exrslt, List<ExamResultBean> studList){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		double max = 0, min = 0;
		
		if(exrslt.getRank_from() > exrslt.getRank_to()){
			
			max = exrslt.getRank_from() == 100?exrslt.getRank_from()+1:exrslt.getRank_from();
			min = exrslt.getRank_to();
						
		} else {
			
			max = exrslt.getRank_to() == 100?exrslt.getRank_to()+1:exrslt.getRank_to();
			min = exrslt.getRank_from();
		}
		
		try{
			int rowCount = 0;
			
			con = Connector.connect();
			
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(StudentRegQueries.updateStudentFullYearResultStatus);
			
			for(int i = 0; i < studList.size(); i++){
				
				if(studList.get(i).getStudRslt_status().equals("Active")){
					
					if(Double.parseDouble(studList.get(i).getAverage_quarter_mark()) <= max && Double.parseDouble(studList.get(i).getAverage_quarter_mark()) >= min){
						
						ps.setString(1, "Passed");
						ps.setString(2, studList.get(i).getSi_id());
						ps.setString(3, exrslt.getAcademic_year());
						
						ps.addBatch();
						
						rowCount++;
						
					} else {
						
						ps.setString(1, "Failed");
						ps.setString(2, studList.get(i).getSi_id());
						ps.setString(3, exrslt.getAcademic_year());
						
						ps.addBatch();
						
						rowCount++;
					}
				}
			}
			
			int[] rslts = ps.executeBatch();
			
			if(rslts.length == rowCount){
				
				con.commit();
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
	
	public static boolean updateStudentFinalResultStatus(ExamResultBean exrslt, List<ExamResultBean> studList){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		double max = 0;
		double min = 0;
		
		if(exrslt.getRank_from() > exrslt.getRank_to()){
			max = exrslt.getRank_from() == 100?exrslt.getRank_from()+1:exrslt.getRank_from();
			min = exrslt.getRank_to();
		} else {
			max = exrslt.getRank_to() == 100?exrslt.getRank_to()+1:exrslt.getRank_to();
			min = exrslt.getRank_from();
		}
		
		try{
			int rowCount = 0;
			
			con = Connector.connect();
			
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(StudentRegQueries.updateStudentFullYearResultStatus);
			
			for(int i = 0; i < studList.size(); i++){
				
				if(Double.parseDouble(studList.get(i).getAverage_quarter_mark()) <= max && Double.parseDouble(studList.get(i).getAverage_quarter_mark()) >= min){
					
					ps.setString(1, "Passed");
					ps.setString(2, studList.get(i).getSi_id());
					ps.setString(3, exrslt.getAcademic_year());
					
					ps.addBatch();
					
					rowCount++;
					
				} else {
					
					ps.setString(1, "Failed");
					ps.setString(2, studList.get(i).getSi_id());
					ps.setString(3, exrslt.getAcademic_year());
					
					ps.addBatch();
					
					rowCount++;
				}
			}
			
			int[] rslts = ps.executeBatch();
			
			if(rslts.length == rowCount){
				
				con.commit();
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
	
	public static String getStudentClassIDByAcademicYearSiId(String si_id, String academic_year){
		
		String rslt = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentRegQueries.getStudentClassIDByAcademicYearSiId);
			ps.setString(1, academic_year);
			ps.setString(2, si_id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				rslt = rs.getString("cl_id");
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

}
