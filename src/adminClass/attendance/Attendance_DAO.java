package adminClass.attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class Attendance_DAO {
	
	public static List<Attendance_Bean> getAttendanceTypeList(){
		
		List<Attendance_Bean> rslt = new ArrayList<Attendance_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(Attendance_Queries.getAttendanceList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Attendance_Bean ab = new Attendance_Bean();
				
				ab.setAttype_id(rs.getString("attype_id"));
				ab.setAttype_description(rs.getString("attype_description"));
				ab.setAttype_code(rs.getString("attype_code"));
				ab.setAttype_status(rs.getString("attype_status"));
				
				rslt.add(ab);
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

	public static boolean saveAttendanceType(Attendance_Bean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(Attendance_Queries.saveAttendanceType);
			ps.setString(1, ab.getAttype_description());
			ps.setString(2, ab.getAttype_code());
			rs = ps.executeUpdate();
			
			if(rs > 0){
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
	
	public static boolean updateAttendanceType(Attendance_Bean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(Attendance_Queries.updateAttendanceType);
			
			ps.setString(1, ab.getAttype_description());
			ps.setString(2, ab.getAttype_code());
			ps.setString(3, ab.getAttype_status());
			ps.setString(4, ab.getAttype_id());
			
			rs = ps.executeUpdate();
			
			if(rs > 0){
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
}
