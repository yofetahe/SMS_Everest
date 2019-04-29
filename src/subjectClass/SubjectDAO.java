package subjectClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import adminClass.classPeriodAssignment.ClassPeriodAssignBean;
import preExamQstClass.PreExamBean;

import cRoomClass.CRoomBean;


import connectionClass.Connector;
import examClass.exam_result.ExamResultBean;

public class SubjectDAO {
	
	public static List<SubjectBean> getActiveSubjectList(){
		List<SubjectBean> sub = new ArrayList<SubjectBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getActiveSubList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				SubjectBean sb = new SubjectBean();
				
				sb.setSub_id(rs.getString("SUB_ID"));
				sb.setSub_name(rs.getString("SUB_NAME"));
				sb.setSub_status(rs.getString("SUB_STATUS"));
				
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
	
	public static List<SubjectBean> getSubjectList(){
		
		List<SubjectBean> sub = new ArrayList<SubjectBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getSubList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				SubjectBean sb = new SubjectBean();
				
				sb.setSub_id(rs.getString("SUB_ID"));
				sb.setSub_name(rs.getString("SUB_NAME"));
				sb.setSub_status(rs.getString("SUB_STATUS"));
				
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
	
	public static boolean insertSubject(SubjectBean sub){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
						
			con = Connector.connect();
			//String subname = new String(sub.getSub_name().getBytes(), "UTF-8");
			ps = con.prepareStatement(SubjectQueries.insertSubject);
			ps.setString(1, sub.getSub_name());
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
	
	public static boolean updateSubject(SubjectBean sub){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.updateSubject);
			ps.setString(1, sub.getSub_name());
			ps.setString(2, sub.getSub_status());
			ps.setString(3, sub.getSub_id());
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
	
	public static boolean checkSubjectExistanceBeforeSave(SubjectBean sub){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.validateSubjectBeforeSave);
			ps.setString(1, sub.getSub_name());
			rs = ps.executeQuery();
			
			while(rs.next()){
				count++;
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
		
		return rslt;
	}
	
	public static boolean checkSubjectExistanceBeforeUpdate(SubjectBean sub){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.validateSubjectBeforeUpdate);
			ps.setString(1, sub.getSub_name());
			ps.setString(2, sub.getSub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				count++;
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
		
		return rslt;
	}
	
	public static List<SubjectBean> getUnselectedSubList(CRoomBean crm){
		
		List<SubjectBean> sb = new ArrayList<SubjectBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getUnselectedSubject);System.out.println(crm.getCl_id());
			ps.setString(1, crm.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				SubjectBean sub = new SubjectBean();
				
				sub.setSub_id(rs.getString("SUB_ID"));
				sub.setSub_name(rs.getString("SUB_NAME"));
				
				sb.add(sub);
				
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
		
		return sb;
	}
		
	public static List<SubjectBean> getSelectedRelatedSubList(ExamResultBean exrslt, String ti_id){
		
		List<SubjectBean> sb = new ArrayList<SubjectBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getSelectedRelatedSubject);
			ps.setString(1, exrslt.getCl_id());
			ps.setString(2, ti_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				SubjectBean sub = new SubjectBean();
				
				sub.setSubcl_id(rs.getString("SUBCL_ID"));
				sub.setSub_id(rs.getString("SUB_ID"));
				sub.setSub_name(rs.getString("SUB_NAME"));
				
				sb.add(sub);
				
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
		
		return sb;
	}
	
	public static List<SubjectBean> getSelectedSubList(ExamResultBean exrslt){
		List<SubjectBean> sb = new ArrayList<SubjectBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getSelectedSubject);
			ps.setString(1, exrslt.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				SubjectBean sub = new SubjectBean();
				
				sub.setSubcl_id(rs.getString("SUBCL_ID"));
				sub.setSub_id(rs.getString("SUB_ID"));
				sub.setSub_name(rs.getString("SUB_NAME"));
				
				sb.add(sub);
				
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
		
		return sb;
	}
	
	public static List<SubjectBean> getSubjectListPerClass(PreExamBean clid){
		
		List<SubjectBean> subList = new ArrayList<SubjectBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getSubListPerClass);
			ps.setString(1, clid.getCl_id());
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
	
	public static int getSubjectNumberPerClass(String clid){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int counter = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getSubListPerClass);
			ps.setString(1, clid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				counter++;
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
		
		return counter;
	}
	
	public static List<SubjectBean> getSubjectListPerClassByClassId(String clid){
		
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
				
				ClassPeriodAssignBean pab = new ClassPeriodAssignBean();
				
				SubjectBean sb = new SubjectBean();
				
				sb.setSub_id(rs.getString("SUB_ID"));
				sb.setSub_name(rs.getString("SUB_NAME"));
				sb.setSub_status(rs.getString("SUB_STATUS"));				
				sb.setSubcl_id(rs.getString("subcl_id"));
				
				pab.setSubjectBean(sb);
				pab.setPeriod_per_week(rs.getInt("period_per_week"));
				
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
	
	public static String getSubjectName(String sub_id, String cl_id){
		
		String subjectName = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getSubjectName);
			ps.setString(1, sub_id);
			ps.setString(2, cl_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				subjectName = rs.getString("sub_name");
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
		
		return subjectName;
	}
	
	public static String getAssignedTeacherForSubject(String cl_id, String cd_id, String sub_id){
		
		String teacherName = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getAssignedTeacherForSubject);
			ps.setString(1, sub_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				teacherName = rs.getString("fname") + " " + rs.getString("mname");
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
		
		return teacherName;
	}
}
