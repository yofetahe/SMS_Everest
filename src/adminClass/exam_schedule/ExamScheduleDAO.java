package adminClass.exam_schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import subjectClass.SubjectBean;
import subjectClass.SubjectQueries;
import util.DateConvertor;
import util.ReturnCurrentEthiopianYear;
import util.TodayDate_YYYYMMDD;
import cRoomClass.CRoomBean;
import cRoomClass.CRoomQueries;

import connectionClass.Connector;
import examClass.exam_subject_rel.ExamSubjectRelBean;
import examClass.exam_subject_rel.ExamSubjectRelQueries;

public class ExamScheduleDAO {
	
	public static List<ExamScheduleBean> getExSchList(String curYear){
		List<ExamScheduleBean> exschlist = new ArrayList<ExamScheduleBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamScheduleQueries.getExSchList);
			ps.setString(1, curYear);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamScheduleBean ex = new ExamScheduleBean();
				
				ex.setEs_id(rs.getString("es_id"));
				ex.setEt_id(rs.getString("et_id"));
				ex.setEt_name(rs.getString("et_name"));
				ex.setCl_id(rs.getString("cl_id"));
				ex.setCl_name(rs.getString("class_name"));
				ex.setSub_id(rs.getString("sub_id"));
				ex.setSub_name(rs.getString("sub_name"));
				ex.setEs_ethio_date(rs.getString("es_ethio_date"));
				ex.setEs_greg_date(rs.getString("es_greg_date"));
				ex.setAt_name(rs.getString("at_name"));
				ex.setEs_status(rs.getString("es_status"));
				
				exschlist.add(ex);
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
		
		return exschlist;
	}
	
	public static List<ExamScheduleBean> filterExamScheduleList(ExamScheduleBean esb){
		
		List<ExamScheduleBean> rslt = new ArrayList<ExamScheduleBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			
			if(esb.getCl_id().equals("0")){
				ps = con.prepareStatement(ExamScheduleQueries.getExSchList);
				ps.setString(1, esb.getAcademic_year());
			} else {
				ps = con.prepareStatement(ExamScheduleQueries.getFilteredExamScheduleList);
				ps.setString(1, esb.getCl_id());
				ps.setString(2, esb.getAcademic_year());
				ps.setString(3, esb.getAt_id());
			}
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamScheduleBean ex = new ExamScheduleBean();
				
				ex.setEs_id(rs.getString("es_id"));
				ex.setEt_id(rs.getString("et_id"));
				ex.setEt_name(rs.getString("et_name"));
				ex.setCl_id(rs.getString("cl_id"));
				ex.setCl_name(rs.getString("class_name"));
				ex.setSub_id(rs.getString("sub_id"));
				ex.setSub_name(rs.getString("sub_name"));
				ex.setEs_ethio_date(rs.getString("es_ethio_date"));
				ex.setEs_greg_date(rs.getString("es_greg_date"));
				ex.setAt_name(rs.getString("at_name"));
				ex.setEs_status(rs.getString("es_status"));
				ex.setTime_from(rs.getString("time_from"));
				ex.setTime_to(rs.getString("time_to"));
				
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
	
	public static List<ExamScheduleBean> dbGetExSchList(){
		List<ExamScheduleBean> exschlist = new ArrayList<ExamScheduleBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamScheduleQueries.dbExSchList);
			ps.setString(1, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamScheduleBean ex = new ExamScheduleBean();
				
				ex.setEs_id(rs.getString("es_id"));
				ex.setEt_id(rs.getString("et_id"));
				ex.setEt_name(rs.getString("et_name"));
				ex.setCl_id(rs.getString("cl_id"));
				ex.setCl_name(rs.getString("class_name"));
				ex.setSub_id(rs.getString("sub_id"));
				ex.setSub_name(rs.getString("sub_name"));
				ex.setEs_ethio_date(rs.getString("es_ethio_date"));
				ex.setEs_greg_date(rs.getString("es_greg_date"));
				ex.setEs_status(rs.getString("es_status"));
				
				exschlist.add(ex);
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
		
		return exschlist;
	}
	
	public static List<CRoomBean> getClassList(){
		List<CRoomBean> clist = new ArrayList<CRoomBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getActiveCroom);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CRoomBean cl = new CRoomBean();
				
				cl.setCl_id(rs.getString("cl_id"));
				cl.setClass_name(rs.getString("class_name"));
				
				clist.add(cl);
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
		
		return clist;
	}
	
	public static List<SubjectBean> getSubjectListPerClass(String clid){
		
		List<SubjectBean> subList = new ArrayList<SubjectBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getSubListPerClass);
			ps.setString(1, clid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				SubjectBean sb = new SubjectBean();
				
				sb.setSub_id(rs.getString("SUB_ID"));
				sb.setSub_name(rs.getString("SUB_NAME"));
				sb.setSub_status(rs.getString("SUB_STATUS"));
				sb.setSubcl_id(rs.getString("subcl_id"));
				
				subList.add(sb);
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
		
		return subList;
	}
	
	public static List<ExamSubjectRelBean> getExamSubjectRel(String cl_id, String sub_id){
		List<ExamSubjectRelBean> exsub = new ArrayList<ExamSubjectRelBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamSubjectRelQueries.getExamTypeList);
			ps.setString(1, cl_id);
			ps.setString(2, sub_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamSubjectRelBean exsub1 = new ExamSubjectRelBean();
				
				exsub1.setExsub_id(rs.getString("EXSUB_ID"));
				exsub1.setEt_id(rs.getString("ET_ID"));
				exsub1.setEt_name(rs.getString("ET_NAME"));
				exsub1.setTotal_mark(rs.getString("TOTAL_MARK"));
				exsub1.setPass_mark(rs.getString("PASS_MARK"));
				exsub1.setRel_status(rs.getString("REL_STATUS"));
				exsub1.setSubcl_id(rs.getString("SUBCL_ID"));
				
				exsub.add(exsub1);
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
		
		return exsub;
	}
	
	public static boolean checkExamScheduleExistance(ExamScheduleBean esb){
		boolean rslt = false;
		
		String year = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamScheduleQueries.checkExamSchedule);
			ps.setString(1, esb.getEt_id());
			ps.setString(2, year);
			ps.setString(3, esb.getAt_id());
			ps.setString(4, esb.getCl_id());
			ps.setString(5, esb.getSub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				count++;
			}
			
			if(count == 0){
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
	
	private static List<ExamScheduleBean> selectedExamList = new ArrayList<ExamScheduleBean>();
	
	public static void clearSelectedExamSchedule(){
		
		selectedExamList.clear();
	}
	
	public static boolean addSelectedExamSchedule(ExamScheduleBean esb){
		
		boolean rslt = false;
		String ethioDate = DateConvertor.gregToEthioDateConvertor(esb.getEs_greg_date());
		String year = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		ExamScheduleBean exsb = new ExamScheduleBean();
		
		exsb.setCl_id(esb.getCl_id());
		exsb.setSub_id(esb.getSub_id());
		exsb.setEt_id(esb.getEt_id());
		exsb.setEs_ethio_date(ethioDate);
		exsb.setEs_greg_date(esb.getEs_greg_date());
		exsb.setTime_from(esb.getTime_from());
		exsb.setTime_to(esb.getTime_to());
		exsb.setAcademic_year(year);
		exsb.setAt_id(esb.getAt_id());
		
		selectedExamList.add(exsb);
		
		return rslt;
		
	}
	
	public static boolean saveExamSchedule(){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0, count = 0;
		
		try{
			
			con = Connector.connect();
			
			for(int i = 0; i < selectedExamList.size(); i++){
				
				if(checkExamScheduleExistance(selectedExamList.get(i))){
					
					ps = con.prepareStatement(ExamScheduleQueries.saveExamSchedule);
					ps.setString(1, selectedExamList.get(i).getCl_id());
					ps.setString(2, selectedExamList.get(i).getSub_id());
					ps.setString(3, selectedExamList.get(i).getEt_id());
					ps.setString(4, selectedExamList.get(i).getEs_ethio_date());
					ps.setString(5, selectedExamList.get(i).getEs_greg_date());
					ps.setString(6, selectedExamList.get(i).getTime_from());
					ps.setString(7, selectedExamList.get(i).getTime_to());
					ps.setString(8, selectedExamList.get(i).getAcademic_year());
					ps.setString(9, selectedExamList.get(i).getAt_id());
					rs = ps.executeUpdate();
					
					if(rs > 0){
						count++;
					}
				}
			}
			
			if(count > 0){
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
		clearSelectedExamSchedule();
		return rslt;
	}
	
	public static boolean saveExamSchedule(ExamScheduleBean esb){
		boolean rslt = false;
		
		String ethioDate = DateConvertor.gregToEthioDateConvertor(esb.getEs_greg_date());
		String year = ethioDate.substring(ethioDate.length()-4, ethioDate.length());
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamScheduleQueries.saveExamSchedule);
			ps.setString(1, esb.getCl_id());
			ps.setString(2, esb.getSub_id());
			ps.setString(3, esb.getEt_id());
			ps.setString(4, ethioDate);
			ps.setString(5, esb.getEs_greg_date());
			ps.setString(6, year);
			ps.setString(7, esb.getAt_id());
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
	
	public static boolean updateExamSchedule(ExamScheduleBean esb){
		boolean rslt = false;
		
		String ethioDate = DateConvertor.gregToEthioDateConvertor(esb.getEs_greg_date());
		String year = ethioDate.substring(ethioDate.length()-4, ethioDate.length());
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamScheduleQueries.updateExamSchedule);
			ps.setString(1, esb.getCl_id());
			ps.setString(2, esb.getSub_id());
			ps.setString(3, esb.getEt_id());
			ps.setString(4, ethioDate);
			ps.setString(5, esb.getEs_greg_date());
			ps.setString(6, year);
			ps.setString(7, esb.getEs_status());
			ps.setString(8, esb.getTime_from());
			ps.setString(9, esb.getTime_to());
			ps.setString(10, esb.getEs_id());
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
	
	public static List<ExamScheduleBean> ReportExamSchedulePerQuarterExamType(String at_id, String et_id){
		
		List<ExamScheduleBean> rslt = new ArrayList<ExamScheduleBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamSubjectRelQueries.ExamScheduleReportPerQuarterExamType);
			ps.setString(1, at_id);
			ps.setString(2, et_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamScheduleBean exs = new ExamScheduleBean();
				
				exs.setSub_name(rs.getString("sub_name"));
				exs.setEs_ethio_date(rs.getString("es_ethio_date"));
				exs.setEs_greg_date(rs.getString("es_greg_date"));
				
				exs.setDay_of_week(TodayDate_YYYYMMDD.getNameOfDay(rs.getString("es_greg_date")));
				
				exs.setTime_from(rs.getString("time_from"));
				exs.setTime_to(rs.getString("time_to"));
				exs.setCl_name(rs.getString("class_name"));
				exs.setAt_name(rs.getString("at_name"));
				exs.setEt_name(rs.getString("et_name"));
				
				rslt.add(exs);
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
