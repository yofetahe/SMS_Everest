package cRoomClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;
import reportClass.ReportQueries;
import util.ReturnCurrentEthiopianYear;

public class CRoomDAO {
	
	public static List<CRoomBean> getActiveRelatedClass(String ti_id){
		
		List<CRoomBean> cr = new ArrayList<CRoomBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getActiveRelatedCroom);
			ps.setString(1, ti_id);
			ps.setString(2, ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				CRoomBean crm = new CRoomBean();
				
				crm.setCl_id(rs.getString("CL_ID"));
				crm.setClass_name(rs.getString("CLASS_NAME"));
				crm.setClass_status(rs.getString("CLASS_STATUS"));
				
				cr.add(crm);
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
		
		return cr;
	}
	
	public static List<CRoomBean> getActiveRelatedClassForExamReg(String ti_id){
		
		List<CRoomBean> cr = new ArrayList<CRoomBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getActiveCroomForExamRsltReg);
			ps.setString(1, ti_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CRoomBean crm = new CRoomBean();
				
				crm.setCl_id(rs.getString("CL_ID"));
				crm.setClass_name(rs.getString("CLASS_NAME"));
				crm.setClass_status(rs.getString("CLASS_STATUS"));
				
				cr.add(crm);
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
		
		return cr;
	}

	public static List<CRoomBean> getActiveClass(){
		List<CRoomBean> cr = new ArrayList<CRoomBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getActiveCroom);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CRoomBean crm = new CRoomBean();
				
				crm.setCl_id(rs.getString("CL_ID"));
				crm.setClass_name(rs.getString("CLASS_NAME"));
				crm.setClass_status(rs.getString("CLASS_STATUS"));
				
				cr.add(crm);
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
		
		return cr;
	}
	
	public static List<CRoomBean> getActiveClassForTranscript(){
		
		List<CRoomBean> cr = new ArrayList<CRoomBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getActiveCroomForTranscript);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				CRoomBean crm = new CRoomBean();
				
				crm.setCl_id(rs.getString("CL_ID"));
				crm.setClass_name(rs.getString("CLASS_NAME"));
				crm.setClass_status(rs.getString("CLASS_STATUS"));
				
				cr.add(crm);
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
		
		return cr;
	}
	
	public static List<CRoomBean> getClassList(){
		List<CRoomBean> cr = new ArrayList<CRoomBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getCroomList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CRoomBean crm = new CRoomBean();
				
				crm.setCl_id(rs.getString("CL_ID"));
				crm.setClass_name(rs.getString("CLASS_NAME"));
				crm.setClass_status(rs.getString("CLASS_STATUS"));
				crm.setClass_number(rs.getInt("class_number"));
				
				cr.add(crm);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return cr;
	}
	
	public static boolean checkClassExistance(CRoomBean cr){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.checkCroomExistance);
			ps.setString(1, cr.getClass_name().toLowerCase());
			ps.setString(2, cr.getClass_name().toUpperCase());
			ps.setString(3, cr.getClass_name());
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
	
	public static boolean insertClass(CRoomBean cr){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.insertCroom);
			ps.setString(1, cr.getClass_name());
			ps.setInt(2, cr.getClass_number());
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
	
	public static boolean updateClass(CRoomBean cr){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.updateCroom);
			ps.setString(1, cr.getClass_name());
			ps.setInt(2, cr.getClass_number());
			ps.setString(3, cr.getClass_status());
			ps.setString(4, cr.getCl_id());
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
	
	public static List<CRoomBean> getClassSubList(CRoomBean crm){
		List<CRoomBean> classsub = new ArrayList<CRoomBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getClassSubRel);
			ps.setString(1, crm.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				CRoomBean clsub = new CRoomBean();
				
				clsub.setSubcl_id(rs.getString("SUBCL_ID"));
				clsub.setSub_id(rs.getString("SUB_ID"));
				clsub.setSub_name(rs.getString("SUB_NAME"));
				clsub.setRel_status(rs.getString("REL_STATUS"));
				clsub.setAdd_to_total(rs.getString("add_status"));
				clsub.setCon_to_grade(rs.getString("CONVERT_TO_GRADE"));
				
				classsub.add(clsub);
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
		
		return classsub;
	}
	
	public static boolean checkRelationExistance(CRoomBean crm){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.checkClSubRelExistance);
			ps.setString(1, crm.getSub_id());
			ps.setString(2, crm.getCl_id());
			rs = ps.executeQuery();
			
			int counter = 0;
			
			while(rs.next()){
				
				counter = Integer.parseInt(rs.getString("sub_id"));
			}
			
			if(counter > 0){
				
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
	
	
	public static boolean insertClSubRel(CRoomBean crm){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.insertClSubRel);
			ps.setString(1, crm.getSub_id());
			ps.setString(2, crm.getCl_id());
			ps.setString(3, crm.getAdd_to_total());
			ps.setString(4, crm.getCon_to_grade());
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
	
	public static boolean updateClSubRel(CRoomBean crm){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.updateClSubRel);
			ps.setString(1, crm.getAdd_to_total());
			ps.setString(2, crm.getCon_to_grade());
			ps.setString(3, crm.getRel_status());
			ps.setString(4, crm.getSubcl_id());
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
	
	public static String getClassName(String cl_id){
		String className = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getClassName);
			ps.setString(1, cl_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				className = rs.getString("class_name");
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
		
		if(className.length() == 0){
			className = "Grade " + cl_id;
		}
		
		return className;
		
	}
	
	public static List<CRoomBean> getExamTypeTotalMarkList(String subcl_id){
		
		List<CRoomBean> rslt = new ArrayList<CRoomBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getExamTypeTotalMarkList);
			ps.setString(1, subcl_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				CRoomBean cr = new CRoomBean();
				cr.setTotal_mark(rs.getString("total_mark"));
				rslt.add(cr);
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
	
	public static int getClassNumberByClId(String cl_id){
		
		int rslt = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getClassNumberByClId);
			ps.setString(1, cl_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				rslt = rs.getInt("class_number");
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
