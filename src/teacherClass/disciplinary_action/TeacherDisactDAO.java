package teacherClass.disciplinary_action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class TeacherDisactDAO {
	
	public static List<TeacherDisactBean> getDisactList(TeacherDisactBean disact){
		List<TeacherDisactBean> da = new ArrayList<TeacherDisactBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherDisactQueries.getDisActList);
			ps.setString(1, disact.getTi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherDisactBean dis = new TeacherDisactBean();
				
				dis.setTda_id(rs.getString("TDA_ID"));
				dis.setTda_type(rs.getString("TDA_TYPE"));
				dis.setTda_reason(rs.getString("TDA_REASON"));
				dis.setTda_date(rs.getString("TDA_DATE"));
				dis.setTda_status(rs.getString("TDA_STATUS"));
				
				da.add(dis);
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
		
		return da;
	}
	
	public static boolean insertDisAct(TeacherDisactBean dis){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherDisactQueries.insertDisAct);
			ps.setString(1, dis.getTda_type());
			ps.setString(2, dis.getTda_reason());
			ps.setString(3, dis.getTda_date());
			ps.setString(4, dis.getTi_id());
			
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
	
	public static boolean updateDisAct(TeacherDisactBean dis){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherDisactQueries.updateDisAct);
			ps.setString(1, dis.getTda_type());
			ps.setString(2, dis.getTda_reason());
			ps.setString(3, dis.getTda_date());
			ps.setString(4, dis.getTda_status());
			ps.setString(5, dis.getTda_id());
			
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
