package adminClass.curriculum_activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import connectionClass.Connector;
import util.ReturnCurrentEthiopianYear;

public class CurriculumAct_DAO {
	
	public static List<CurriculumAct_Bean> getDepartmentList(){
		
		List<CurriculumAct_Bean> rslt = new ArrayList<CurriculumAct_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.getDepartmentList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CurriculumAct_Bean cab = new CurriculumAct_Bean();
				
				cab.setDep_id(rs.getString("dep_id"));
				cab.setDep_name(rs.getString("dep_name"));
				cab.setDep_desc(rs.getString("dep_desc"));
				cab.setDep_status(rs.getString("dep_status"));
				
				rslt.add(cab);
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
	
	public static boolean saveDepartment(CurriculumAct_Bean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.saveDepartment);
			ps.setString(1, cab.getDep_name());
			ps.setString(2, cab.getDep_desc());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		
		return rslt;
	}
	
	public static boolean updateDepartment(CurriculumAct_Bean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.updateDepartment);
			ps.setString(1, cab.getDep_name());
			ps.setString(2, cab.getDep_desc());
			ps.setString(3, cab.getDep_status());
			ps.setString(4, cab.getDep_id());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		
		return rslt;
	}
	
	public static List<CurriculumAct_Bean> getClubList(){
		
		List<CurriculumAct_Bean> rslt = new ArrayList<CurriculumAct_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.getClubList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CurriculumAct_Bean cab = new CurriculumAct_Bean();
				
				cab.setClb_id(rs.getString("clb_id"));
				cab.setClb_name(rs.getString("clb_name"));
				cab.setClb_desc(rs.getString("clb_desc"));
				cab.setClb_status(rs.getString("clb_status"));
				cab.setDep_id(rs.getString("dep_id"));
				cab.setDep_name(rs.getString("dep_name"));
				
				rslt.add(cab);
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
	
	public static boolean saveClub(CurriculumAct_Bean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.saveClub);
			ps.setString(1, cab.getClb_name());
			ps.setString(2, cab.getClb_desc());
			ps.setString(3, cab.getDep_id());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		
		return rslt;
	}
	
	public static boolean updateClub(CurriculumAct_Bean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.updateClub);
			ps.setString(1, cab.getClb_name());
			ps.setString(2, cab.getClb_desc());
			ps.setString(3, cab.getClb_status());
			ps.setString(4, cab.getClb_id());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		
		return rslt;
	}
	
	public static List<CurriculumAct_Bean> getTeacherList(){
		//getting academic year --- starting from May to August the academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
					
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		String yr = util.DateConvertor.dateConvertor(day+"-"+month+"-"+year);
		
		List<CurriculumAct_Bean> rslt = new ArrayList<CurriculumAct_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.getTeacherList);
			ps.setString(1, yr);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CurriculumAct_Bean cab = new CurriculumAct_Bean();
				
				cab.setTi_id(rs.getString("ti_id"));
				cab.setTr_name(rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname"));
				
				rslt.add(cab);
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
	
	public static List<CurriculumAct_Bean> getTeacherRoleList(){
		List<CurriculumAct_Bean> rslt = new ArrayList<CurriculumAct_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.getTeacherRoleList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CurriculumAct_Bean cab = new CurriculumAct_Bean();
				
				cab.setRole_id(rs.getString("role_id"));
				cab.setRole_name(rs.getString("role_name"));
				
				rslt.add(cab);
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
	
	public static List<Integer> getYearList(){
		//getting academic year --- starting from May to August the academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
					
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		String yr = util.DateConvertor.dateConvertor(day+"-"+month+"-"+year);
		
		List<Integer> rslt = new ArrayList<Integer>();
		
		Integer acyear = Integer.parseInt(yr);
		
		rslt.add(acyear);
		rslt.add(acyear + 1);
		
		return rslt;
	}
	
	public static List<CurriculumAct_Bean> getResponsibilityList(CurriculumAct_Bean cab){
		List<CurriculumAct_Bean> rslt = new ArrayList<CurriculumAct_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			if(cab.getRole_id().equalsIgnoreCase("1")){
				
				ps = con.prepareStatement(CurriculumAct_Queries.getActiveDepartmentList);
				rs = ps.executeQuery();
				
				while(rs.next()){
					CurriculumAct_Bean ccab = new CurriculumAct_Bean();
					
					ccab.setDep_id(rs.getString("dep_id"));
					ccab.setDep_name(rs.getString("dep_name"));
					
					rslt.add(ccab);
				}
			} else if(cab.getRole_id().equalsIgnoreCase("2") || cab.getRole_id().equalsIgnoreCase("3")){
				
				ps = con.prepareStatement(CurriculumAct_Queries.getActiveClubList);
				rs = ps.executeQuery();
				
				while(rs.next()){
					CurriculumAct_Bean ccab = new CurriculumAct_Bean();					
					
					ccab.setClb_id(rs.getString("clb_id"));
					ccab.setClb_name(rs.getString("clb_name"));
					
					rslt.add(ccab);
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
	
	public static boolean saveTeacherResponsibility(CurriculumAct_Bean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.saveTeacherResponsibility);
			ps.setString(1, cab.getTi_id());
			ps.setString(2, cab.getRole_id());
			ps.setString(3, cab.getResponsibility_id());
			ps.setString(4, cab.getAcademic_year());
			
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
	
	public static List<CurriculumAct_Bean> getAssignTchRespList(){
		List<CurriculumAct_Bean> rslt = new ArrayList<CurriculumAct_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.getAssignTeacherRespList);
			ps.setInt(1, Integer.parseInt(yr));
			ps.setInt(2, Integer.parseInt(yr));
			rs = ps.executeQuery();
			
			while(rs.next()){
				CurriculumAct_Bean cab = new CurriculumAct_Bean();
				
				cab.setTi_id(rs.getString("ti_id"));
				cab.setTr_id(rs.getString("tr_id"));
				cab.setTr_name(rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname"));
				cab.setRole_name(rs.getString("role_name"));
				cab.setRole_id(rs.getString("role_id"));
				cab.setDep_or_club(rs.getString("dc"));
				cab.setAcademic_year(rs.getString("academic_year"));
				cab.setTr_status(rs.getString("tr_status"));
				
				rslt.add(cab);
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
	
	public static List<CurriculumAct_Bean> getAllTeacherList(){
				
		List<CurriculumAct_Bean> rslt = new ArrayList<CurriculumAct_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.getAllTeacherList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CurriculumAct_Bean cab = new CurriculumAct_Bean();
				
				cab.setTi_id(rs.getString("ti_id"));
				cab.setTr_name(rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname"));
				
				rslt.add(cab);
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
	
	public static boolean updateTeacherResponsibility(CurriculumAct_Bean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurriculumAct_Queries.updateTeacherResponsibility);
			ps.setString(1, cab.getTi_id());
			ps.setString(2, cab.getRole_id());
			ps.setString(3, cab.getResponsibility_id());
			ps.setString(4, cab.getAcademic_year());
			ps.setString(5, cab.getTr_status());
			ps.setString(6, cab.getTr_id());
			
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

}
