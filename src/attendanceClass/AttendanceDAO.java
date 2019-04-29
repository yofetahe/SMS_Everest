package attendanceClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import studentClass.StudentBean;

import connectionClass.Connector;

public class AttendanceDAO {
	
	public static String getClassSize(AttendanceBean atb){
		String rslt = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AttendanceQueries.getClassSize);
			ps.setString(1, atb.getCl_id());
			ps.setString(2, atb.getCd_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				rslt = rs.getString("cl_capacity");
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
	
	public static boolean getCheckAbsentDataExistance(AttendanceBean atb, String acYear, Object username){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AttendanceQueries.getClCdId);
			ps.setString(1, atb.getCl_id());
			ps.setString(2, atb.getCd_id());
			ps.setString(3, atb.getAttendance_date());
			ps.setString(4, atb.getSi_id());
			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next()){
				count++;
			}
			if(count > 0){
				rslt = true;
			} else {
				rslt = saveAbsentData(atb, acYear, username);
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
	
	public static List<AttendanceBean> getAttendanceType(){
		
		List<AttendanceBean> rslt = new ArrayList<AttendanceBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AttendanceQueries.getAttendanceTypeList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				AttendanceBean atbean = new AttendanceBean();
				
				atbean.setAttype_id(rs.getString("attype_id"));
				atbean.setAttype_description(rs.getString("attype_description"));
				atbean.setAttype_code(rs.getString("attype_code"));
				
				rslt.add(atbean);
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
	
	public static boolean saveAbsentData(AttendanceBean atb, String acYear, Object username){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AttendanceQueries.saveAttendanceData);
			ps.setString(1, atb.getSi_id());
			ps.setString(2, atb.getAttendance_date());
			ps.setString(3, "N");
			ps.setString(4, atb.getCl_id());
			ps.setString(5, atb.getCd_id());
			ps.setString(6, acYear);
			ps.setInt(7, Integer.parseInt(atb.getAttype_id()));
			ps.setInt(8, Integer.parseInt(atb.getAt_id()));
			ps.setString(9, (String)username);
			
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
	
	public static List<AttendanceBean> getAbsentStudentList(AttendanceBean atb){
		List<AttendanceBean> rslt = new ArrayList<AttendanceBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AttendanceQueries.getAbsentStudentList);
			ps.setString(1, atb.getCl_id());
			ps.setString(2, atb.getCd_id());
			ps.setString(3, atb.getAttendance_date());
			rs = ps.executeQuery();
			
			while(rs.next()){
				AttendanceBean attb = new AttendanceBean();
				
				attb.setAtt_id(rs.getString("att_id"));
				attb.setFirstName(rs.getString("fname"));
				attb.setFatherName(rs.getString("mname"));
				attb.setGfName(rs.getString("gname"));
				attb.setAttendance_date(rs.getString("att_date"));
				attb.setAttendacen_reason(rs.getString("att_reason"));
				
				rslt.add(attb);
				
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
	
	public static List<AttendanceBean> getAggregateStudentAbsetList(List<StudentBean> studlist, String acYear){
		List<AttendanceBean> rslt = new ArrayList<AttendanceBean>();		
		
		Connection con = null;
		
		try{
			con = Connector.connect();
			
			for(int i = 0; i < studlist.size(); i++){
				
				PreparedStatement ps = null;
				ResultSet rs = null;
				AttendanceBean ab = new AttendanceBean();
				
				ab.setFirstName(studlist.get(i).getFname());
				ab.setFatherName(studlist.get(i).getMname());
				ab.setGfName(studlist.get(i).getGname());
				
				ps = con.prepareStatement(AttendanceQueries.getAllStudAbsentDateList);
				ps.setString(1, studlist.get(i).getSi_id());
				ps.setString(2, acYear);
				rs = ps.executeQuery();				
				
				int counter = 0;
				String dateList = "";
				while(rs.next()){
					counter++;
					if(dateList.length() == 0){
						dateList = rs.getString("att_date");
					} else {
						dateList = dateList + ", " + rs.getString("att_date");
					}
				}
				ab.setNoofdays(String.valueOf(counter));				
				ab.setDateList(dateList);
				
				rslt.add(ab);
				
				rs.close();				
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
	
	public static boolean saveAbsentReason(AttendanceBean atb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AttendanceQueries.saveAttendaceReason);
			ps.setString(1, atb.getAttendacen_reason());
			ps.setString(2, atb.getAtt_id());
			rs = ps.executeUpdate();
			
			if(rs > 1){
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
